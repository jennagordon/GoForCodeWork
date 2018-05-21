package dao;

import dto.Order;
import dto.Product;
import dto.Tax;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FlooringDaoOrderStubImpl implements FlooringDaoOrder {

    Order stubTestOrder;
    Tax stubTestTax;
    Product stubTestProduct;
    List<Order> orderList = new ArrayList<>();
    List<Order> blankOrderList = new ArrayList<>();
    LocalDate orderDate = LocalDate.parse("02202000", DateTimeFormatter.ofPattern("MMddyyyy"));

    public FlooringDaoOrderStubImpl() {
        stubTestTax = new Tax("OH", new BigDecimal("6.25"));

        stubTestProduct = new Product("Wood", new BigDecimal("5.15"), new BigDecimal("4.75"));

        stubTestOrder = new Order();
        stubTestOrder.setCustomerName("Test Name");
        stubTestOrder.setArea(new BigDecimal("5"));
        stubTestOrder.setTaxObject(stubTestTax);
        stubTestOrder.setProductObject(stubTestProduct);
//        stubTestOrder.setTotalMaterialCost(new BigDecimal("2"));
//        stubTestOrder.setTotalLaborCost(new BigDecimal("2"));
//        stubTestOrder.setTotalTax(new BigDecimal("2"));
//        stubTestOrder.setTotalCost(new BigDecimal("2"));
        stubTestOrder.setOrderDate(orderDate);
        stubTestOrder.setOrderNumber("1");

        orderList.add(stubTestOrder);
    }

    @Override
    public List<Order> retrieveAllOrdersByDate(LocalDate orderDate) throws FlooringPersistenceException {
        if(stubTestOrder.getOrderDate().equals(orderDate)) {
            return orderList;
        } else {
            return blankOrderList;
        }

    }

    @Override
    public Order retrieveOrderByDateAndId(LocalDate orderDate, String orderNumber) throws FlooringPersistenceException {
        if(stubTestOrder.getOrderDate().equals(orderDate) && stubTestOrder.getOrderNumber().equals(orderNumber)) {
            return stubTestOrder;
        } else {
            return null;
        }
    }

    @Override
    public Order createOrder(LocalDate orderDate, Order orderObject) throws FlooringPersistenceException {
        return stubTestOrder;
    }

    @Override
    public void updateOrder(LocalDate orderDate, Order orderObject) throws FlooringPersistenceException {

    }

    @Override
    public void removeOrder(LocalDate orderDate, String orderNumber) throws FlooringPersistenceException {

    }

    @Override
    public void saveOrder() {

    }
}
