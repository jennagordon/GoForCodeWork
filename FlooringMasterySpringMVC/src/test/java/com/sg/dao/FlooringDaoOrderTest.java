package com.sg.dao;

import com.sg.dto.Order;
import com.sg.dto.Product;
import com.sg.dto.Tax;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FlooringDaoOrderTest {

    FlooringDaoOrder orderDao;
    LocalDate orderDate = LocalDate.parse("02202000", DateTimeFormatter.ofPattern("MMddyyyy"));


    public FlooringDaoOrderTest() {

    }

    @Before
    public void setUp() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        orderDao = ctx.getBean("orderDao", FlooringDaoOrder.class);

        List<Order> orderList = orderDao.retrieveAllOrders();

        for (Order tempOrder : orderList) {
                orderDao.removeOrder(tempOrder);
        }


    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testRetrieveAllOrdersByDate() throws FlooringPersistenceException {
        Tax testTax = new Tax();
        testTax.setTaxId(1);
        Product testProduct = new Product();
        testProduct.setProductId(1);

        Order testOrder = new Order();
        testOrder.setOrderDate(orderDate);
        testOrder.setCustomerName("Test Name");
        testOrder.setArea(new BigDecimal("5"));
        testOrder.setTaxObject(testTax);
        testOrder.setProductObject(testProduct);
        testOrder.setTotalMaterialCost(new BigDecimal("2"));
        testOrder.setTotalLaborCost(new BigDecimal("2"));
        testOrder.setTotalTax(new BigDecimal("2"));
        testOrder.setTotalCost(new BigDecimal("2"));

        orderDao.createOrder(testOrder);
        assertEquals(1, orderDao.retrieveAllOrders().size());

        Tax testTax2 = new Tax();
        testTax2.setTaxId(2);
        Product testProduct2 = new Product();
        testProduct2.setProductId(2);

        Order testOrder2 = new Order();
        testOrder2.setOrderDate(orderDate);
        testOrder2.setCustomerName("Jodi");
        testOrder2.setArea(new BigDecimal("5"));
        testOrder2.setTaxObject(testTax);
        testOrder2.setProductObject(testProduct);
        testOrder2.setTotalMaterialCost(new BigDecimal("5"));
        testOrder2.setTotalLaborCost(new BigDecimal("5"));
        testOrder2.setTotalTax(new BigDecimal("5"));
        testOrder2.setTotalCost(new BigDecimal("5"));

        orderDao.createOrder(testOrder2);

        assertEquals(2, orderDao.retrieveAllOrders().size());
    }

    @Test
    public void testRetrieveOrderByDateAndId() throws FlooringPersistenceException {
        Tax testTax = new Tax();
        testTax.setTaxId(1);
        Product testProduct = new Product();
        testProduct.setProductId(1);

        Order testOrder = new Order();
        testOrder.setOrderDate(orderDate);
        testOrder.setCustomerName("Test Name");
        testOrder.setArea(new BigDecimal("5"));
        testOrder.setTaxObject(testTax);
        testOrder.setProductObject(testProduct);
        testOrder.setTotalMaterialCost(new BigDecimal("2"));
        testOrder.setTotalLaborCost(new BigDecimal("2"));
        testOrder.setTotalTax(new BigDecimal("2"));
        testOrder.setTotalCost(new BigDecimal("2"));

        orderDao.createOrder(testOrder);

        Order readOrder = orderDao.retrieveOrderByDateAndId(testOrder);

        assertEquals(testOrder.getOrderId(), readOrder.getOrderId());
        assertEquals(testOrder.getOrderDate(), readOrder.getOrderDate());
        assertEquals(testOrder.getCustomerName(), readOrder.getCustomerName());
    }

    // Don't really need because we are testing it inside of retrieve
    @Test
    public void createOrder() throws FlooringPersistenceException {
        Tax testTax = new Tax("OH", new BigDecimal("6.25"));
        Product testProduct = new Product("Wood", new BigDecimal("5.15"), new BigDecimal("4.75"));

        Order testOrder = new Order();
        testOrder.setCustomerName("Test Name");
        testOrder.setArea(new BigDecimal("5"));
        testOrder.setTaxObject(testTax);
        testOrder.setProductObject(testProduct);
        testOrder.setTotalMaterialCost(new BigDecimal("50"));
        testOrder.setTotalLaborCost(new BigDecimal("10"));
        testOrder.setTotalTax(new BigDecimal("62"));
        testOrder.setTotalCost(new BigDecimal("5000"));

        orderDao.createOrder(testOrder);

        assertEquals(1, orderDao.retrieveAllOrders().size());
    }

    @Test
    public void updateOrder() throws FlooringPersistenceException {
        Tax testTax = new Tax("OH", new BigDecimal("6.25"));
        Product testProduct = new Product("Wood", new BigDecimal("5.15"), new BigDecimal("4.75"));

        Order testOrder = new Order();
        testOrder.setCustomerName("Test Name");
        testOrder.setArea(new BigDecimal("5"));
        testOrder.setTaxObject(testTax);
        testOrder.setProductObject(testProduct);
        testOrder.setTotalMaterialCost(new BigDecimal("2"));
        testOrder.setTotalLaborCost(new BigDecimal("10"));
        testOrder.setTotalTax(new BigDecimal("62"));
        testOrder.setTotalCost(new BigDecimal("1000"));

        orderDao.createOrder(testOrder);
        orderDao.updateOrder(testOrder);

        assertEquals(1, orderDao.retrieveAllOrders().size());

    }

    @Test
    public void testRemoveOrder() throws FlooringPersistenceException {
        Tax testTax = new Tax("OH", new BigDecimal("6.25"));
        Product testProduct = new Product("Wood", new BigDecimal("5.15"), new BigDecimal("4.75"));

        Order testOrder = new Order();
        testOrder.setCustomerName("Test Name");
        testOrder.setArea(new BigDecimal("5"));
        testOrder.setTaxObject(testTax);
        testOrder.setProductObject(testProduct);
        testOrder.setTotalMaterialCost(new BigDecimal("2"));
        testOrder.setTotalLaborCost(new BigDecimal("10"));
        testOrder.setTotalTax(new BigDecimal("62"));
        testOrder.setTotalCost(new BigDecimal("2"));


        orderDao.createOrder(testOrder);

        orderDao.removeOrder(testOrder);
        // test the size
        assertEquals(0, orderDao.retrieveAllOrders().size());

        // test that our exception was thrown because there is no order existing
        try {
            orderDao.retrieveOrderByDateAndId(testOrder);
            fail("FlooringPersistenceException was not thrown");
        } catch (FlooringPersistenceException e) {
            return;

        }
    }
}