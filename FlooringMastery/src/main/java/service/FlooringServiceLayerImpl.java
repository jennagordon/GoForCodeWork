package service;

import dao.*;
import dto.Order;
import dto.Product;
import dto.Tax;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class FlooringServiceLayerImpl implements FlooringServiceLayer {

    private FlooringDaoOrder orderDao;
    private FlooringDaoProducts productsDao;
    private FlooringDaoTaxes taxesDao;

    public FlooringServiceLayerImpl(FlooringDaoOrder orderDao, FlooringDaoProducts productsDao, FlooringDaoTaxes taxesDao) {
        this.orderDao = orderDao;
        this.productsDao = productsDao;
        this.taxesDao = taxesDao;
    }

    @Override
    public List<Order> retrieveAllOrdersByDate(LocalDate orderDate) throws FlooringPersistenceException, OrderNotFoundException {

        validateOrdersExistForDate(orderDate);
        return orderDao.retrieveAllOrdersByDate(orderDate);
    }

    @Override
    public List<Product> retrieveAllProducts() throws FlooringPersistenceException {
        return productsDao.retrieveAllProducts();
    }

    @Override
    public List<Tax> retrieveAllTaxes() throws FlooringPersistenceException {
        return taxesDao.retrieveAllTaxes();
    }

    @Override
    public Order processOrder(Order orderObject) throws FlooringPersistenceException, TaxStateNotFoundException, ProductMaterialNotFoundException {

        // check that tax state exists
        // getting state from the user which was set on the orderObject passed in
        // setting it as a variable
        String orderState = orderObject.getTaxObject().getState();

        // if order state is null throw the exception
       if(retrieveTaxObject(orderState) == null){
           throw new TaxStateNotFoundException("We Don't Support This State");
        // otherwise set the tax object on the orderObject passed in
       } else {
           orderObject.setTaxObject(retrieveTaxObject(orderState));
       }

        // check that product material exists
        // getting material from the user which was set on the orderObject passed in
        // setting it as a variable
       String orderMaterial = orderObject.getProductObject().getProductType();
        // if order material is null throw the exception
       if(retrieveProductObject(orderMaterial) == null) {
           throw new ProductMaterialNotFoundException("Material Entered Does Not Exist");
       // otherwise set the product object on the orderObject passed in
       } else {
           orderObject.setProductObject(retrieveProductObject(orderMaterial));
       }

       // returning the complete orderObject with set Tax and Product
       return orderObject;

    }

    @Override
    public Order addOrder(Order orderObject) throws FlooringPersistenceException, TaxStateNotFoundException, ProductMaterialNotFoundException {
        // call process order to run checks before we do calculations
        orderObject = processOrder(orderObject);


        // calculations and set for total material cost from method
        orderObject = calculateAndSetTotalMaterialCost(orderObject);

        // calculations and set for total labor cost from method
        orderObject = calculateAndSetTotalLaborCost(orderObject);

        // calculations and set for total tax cost from method
        orderObject = calculateAndSetTotalTax(orderObject);

        // calculations and set total cost from method
        orderObject = calculateAndSetTotalCost(orderObject);

        return orderDao.createOrder(orderObject.getOrderDate(), orderObject);
    }

    @Override
    public Order retrieveOrderByDateAndId(LocalDate orderDate, String orderNumber) throws FlooringPersistenceException, OrderNotFoundException {
        Order order;
        validateOrdersExistForDate(orderDate);
        order = orderDao.retrieveOrderByDateAndId(orderDate, orderNumber);

        if(order == null) {
            throw new OrderNotFoundException("No Orders Exist With Provided Order Number");
        }

        return order;
    }

    @Override
    public void removeOrder(LocalDate orderDate, String orderNumber) throws FlooringPersistenceException, OrderNotFoundException {


        validateOrdersExistForDate(orderDate);

        orderDao.removeOrder(orderDate, orderNumber);

    }

    @Override
    public Order editOrder(LocalDate date, Order orderObject) throws FlooringPersistenceException, TaxStateNotFoundException, ProductMaterialNotFoundException {

        // call process order to run checks before we do calculations
        orderObject = processOrder(orderObject);

        // calculations and set for total material cost from method
        orderObject = calculateAndSetTotalMaterialCost(orderObject);

        // calculations and set for total labor cost from method
        orderObject = calculateAndSetTotalLaborCost(orderObject);

        // calculations and set for total tax cost from method
        orderObject = calculateAndSetTotalTax(orderObject);

        // calculations and set total cost from method
        orderObject = calculateAndSetTotalCost(orderObject);

        // if the date passed in doesn't match the date on the object
        // remove the order from the original file (date)
        // add it to the new file
        if (date != orderObject.getOrderDate()) {
            orderDao.removeOrder(date, orderObject.getOrderNumber());
            orderDao.createOrder(orderObject.getOrderDate(), orderObject);
        } else {
            // if the dates match just update the order
            orderDao.updateOrder(orderObject.getOrderDate(), orderObject);
        }

        return orderObject ;
    }

    @Override
    public void saveAllOrders() throws FlooringPersistenceException {
        orderDao.saveOrder();

    }

    @Override
    public void activateTrainingMode(boolean userSelection) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        if (userSelection) {
            // if train call training impl
            orderDao = ctx.getBean("orderTrainingDao", FlooringDaoOrder.class);
        } else {
            // if prod call prod impl
            orderDao = ctx.getBean("orderProdDao", FlooringDaoOrder.class);
        }

    }


    private void validateOrdersExistForDate(LocalDate orderDate) throws FlooringPersistenceException, OrderNotFoundException {

        List<Order> orderList = orderDao.retrieveAllOrdersByDate(orderDate);
        // checking if there are orders for the date
        if(orderList.size() == 0) {
            throw new OrderNotFoundException("Sorry! There are no orders for this date.");
        }

    }

    private Tax retrieveTaxObject(String state) throws FlooringPersistenceException {
        return taxesDao.retrieveTaxByState(state);
    }

    private Product retrieveProductObject(String materialProductType) throws FlooringPersistenceException {
        return productsDao.retrieveProductByMaterial(materialProductType);
    }

    private Order calculateAndSetTotalMaterialCost(Order orderObject){
        // total material cost = materialCostPerSqFoot * Area
       BigDecimal area = orderObject.getArea();
       BigDecimal materialSqFt = orderObject.getProductObject().getMaterialCostPerSquareFoot();

       BigDecimal totalMaterialCost = area.multiply(materialSqFt);

       orderObject.setTotalMaterialCost(totalMaterialCost);

       return orderObject;
    }

    private Order calculateAndSetTotalLaborCost(Order orderObject){
        // total labor cost = laborCostPerSqFoot * Area
        BigDecimal area = orderObject.getArea();
        BigDecimal laborSqFt = orderObject.getProductObject().getLaborCostPerSquareFoot();

        BigDecimal totalLaborCost = area.multiply(laborSqFt);

        orderObject.setTotalLaborCost(totalLaborCost);

        return orderObject;
    }

    private Order calculateAndSetTotalTax(Order orderObject){
        // total tax cost = (material cost + labor cost) * tax rate for state
        BigDecimal totalMaterialCost = orderObject.getTotalMaterialCost();
        BigDecimal totalLaborCost = orderObject.getTotalLaborCost();
        BigDecimal taxRate = (orderObject.getTaxObject().getTaxRate()).movePointLeft(2);

        BigDecimal sumOfMaterialAndLaborCost = totalLaborCost.add(totalMaterialCost);

        BigDecimal totalTax = sumOfMaterialAndLaborCost.multiply(taxRate);

        orderObject.setTotalTax(totalTax);

        return orderObject;
    }

    private Order calculateAndSetTotalCost(Order orderObject){
        // total = total tax + total labor + total material
        BigDecimal totalTax = orderObject.getTotalTax();
        BigDecimal totalLabor = orderObject.getTotalLaborCost();
        BigDecimal totalMaterial = orderObject.getTotalMaterialCost();

        BigDecimal totalCost = ((totalTax.add(totalLabor)).add(totalMaterial));

        orderObject.setTotalCost(totalCost);

        return orderObject;
    }

}
