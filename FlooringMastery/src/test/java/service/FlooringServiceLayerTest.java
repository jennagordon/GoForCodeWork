package service;

import dao.FlooringDaoOrder;
import dao.FlooringDaoProducts;
import dao.FlooringDaoTaxes;
import dao.FlooringPersistenceException;
import dto.Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class FlooringServiceLayerTest {

   FlooringServiceLayer service;
   LocalDate orderDate = LocalDate.parse("02202000", DateTimeFormatter.ofPattern("MMddyyyy"));

    public FlooringServiceLayerTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("service", FlooringServiceLayer.class);
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testRetrieveAllOrdersByDate() throws FlooringPersistenceException, OrderNotFoundException {
        assertEquals(1, service.retrieveAllOrdersByDate(orderDate).size());
    }

    @Test
    public void testRetrieveAllProducts() throws FlooringPersistenceException {
        assertEquals(1, service.retrieveAllProducts().size());
    }

    @Test
    public void testRetrieveAllTaxes() throws FlooringPersistenceException {
        assertEquals(1, service.retrieveAllTaxes().size());
    }

    @Test
    public void testProcessOrder() throws FlooringPersistenceException, OrderNotFoundException, TaxStateNotFoundException, ProductMaterialNotFoundException {
        Order processOrder= service.processOrder(service.retrieveOrderByDateAndId(orderDate, "1"));

        assertEquals("OH", processOrder.getTaxObject().getState());
        assertEquals("Wood", processOrder.getProductObject().getProductType());
        assertEquals(BigDecimal.valueOf(5), processOrder.getArea());
    }

    @Test
    public void testAddOrder() throws FlooringPersistenceException, OrderNotFoundException, TaxStateNotFoundException, ProductMaterialNotFoundException {
        service.addOrder(service.retrieveOrderByDateAndId(orderDate, "1"));
    }

    @Test
    public void testRetrieveOrderByDateAndId() throws FlooringPersistenceException, OrderNotFoundException {

        assertNotNull(service.retrieveOrderByDateAndId(orderDate, "1"));


        assertNull(service.retrieveOrderByDateAndId(orderDate, "23"));
    }

    @Test
    public void testRemoveOrder() throws FlooringPersistenceException, OrderNotFoundException {
        service.removeOrder(orderDate, "1");
    }

    @Test
    public void testEditOrder() throws FlooringPersistenceException, OrderNotFoundException, TaxStateNotFoundException, ProductMaterialNotFoundException {
        service.editOrder(orderDate, service.retrieveOrderByDateAndId(orderDate, "1"));
    }

    @Test
    public void testSaveAllOrders() throws FlooringPersistenceException {
        service.saveAllOrders();
    }

    // will need to update test... I think
    @Test
    public void testActivateTrainingMode() {
        service.activateTrainingMode(true);
    }

    @Test
    public void testCalculateAndSetTotalMaterialCost() throws FlooringPersistenceException, OrderNotFoundException, TaxStateNotFoundException, ProductMaterialNotFoundException {

        Order order = service.retrieveOrderByDateAndId(orderDate, "1");
        service.addOrder(order);

        assertEquals(new BigDecimal("25.75"), order.getTotalMaterialCost());
    }

    @Test
    public void testCalculateAndSetTotalLaborCost() throws TaxStateNotFoundException, FlooringPersistenceException, ProductMaterialNotFoundException, OrderNotFoundException {
        Order order = service.retrieveOrderByDateAndId(orderDate, "1");
        service.addOrder(order);

        assertEquals(new BigDecimal("23.75"), order.getTotalLaborCost());

    }

    @Test
    public void testCalculateAndSetTotalTax() throws TaxStateNotFoundException, FlooringPersistenceException, ProductMaterialNotFoundException, OrderNotFoundException {
        Order order = service.retrieveOrderByDateAndId(orderDate, "1");
        service.addOrder(order);

        assertEquals(new BigDecimal("3.093750"), order.getTotalTax());

    }

    @Test
    public void testCalculateAndSetTotalCost() throws TaxStateNotFoundException, FlooringPersistenceException, ProductMaterialNotFoundException, OrderNotFoundException {
        Order order = service.retrieveOrderByDateAndId(orderDate, "1");
        service.addOrder(order);

        assertEquals(new BigDecimal("52.593750"), order.getTotalCost());

    }

    @Test
    public void testProcessOrderExists() throws FlooringPersistenceException, OrderNotFoundException, TaxStateNotFoundException, ProductMaterialNotFoundException {
        service.processOrder(service.retrieveOrderByDateAndId(orderDate, "1"));

    }

    @Test
    public void testProcessOrderTaxStateNotFoundException() throws FlooringPersistenceException, OrderNotFoundException, TaxStateNotFoundException, ProductMaterialNotFoundException {
        Order order = service.retrieveOrderByDateAndId(orderDate, "1");
        order.getTaxObject().setState("MA");

        try {
            service.addOrder(order);
            fail("TaxStateNotFoundException was not thrown");
        } catch (TaxStateNotFoundException e) {
            return;
        }

    }

    @Test
    public void testProcessOrderProductMaterialNotFoundException() throws FlooringPersistenceException, OrderNotFoundException, TaxStateNotFoundException {
        Order order = service.retrieveOrderByDateAndId(orderDate, "1");
        order.getProductObject().setProductType("Tile");

        try {
            service.addOrder(order);
            fail("ProductMaterialNotFoundException was not thrown");
        } catch (ProductMaterialNotFoundException e) {
            return;

        }
    }
    @Test
    public void testProcessOrderNotFoundException() throws FlooringPersistenceException, OrderNotFoundException {
        LocalDate badOrderDate = LocalDate.parse("12201990", DateTimeFormatter.ofPattern("MMddyyyy"));

        try {
            service.retrieveAllOrdersByDate(badOrderDate);
            fail("OrderNotFoundException was not thrown");
        } catch (OrderNotFoundException e) {
            return;

        }
    }

}