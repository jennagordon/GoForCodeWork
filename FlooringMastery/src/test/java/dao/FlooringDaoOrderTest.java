package dao;

import dto.Order;
import dto.Product;
import dto.Tax;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.OrderNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.*;

public class FlooringDaoOrderTest {

    FlooringDaoOrder orderDao;
    LocalDate orderDate = LocalDate.parse("02202000", DateTimeFormatter.ofPattern("MMddyyyy"));


    public FlooringDaoOrderTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        orderDao = ctx.getBean("orderProdDao", FlooringDaoOrder.class);
    }

    @Before
    public void setUp() throws Exception {
        List<Order> orderList = orderDao.retrieveAllOrdersByDate(orderDate);

        for (Order tempOrder : orderList) {
                orderDao.removeOrder(orderDate, tempOrder.getOrderNumber());
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testRetrieveAllOrdersByDate() throws FlooringPersistenceException {
        Tax testTax = new Tax("OH", new BigDecimal("6.25"));
        Product testProduct = new Product("Wood", new BigDecimal("5.15"), new BigDecimal("4.75"));

        Order testOrder = new Order();
        testOrder.setCustomerName("Test Name");
        testOrder.setArea(new BigDecimal("5"));
        testOrder.setTaxObject(testTax);
        testOrder.setProductObject(testProduct);
        testOrder.setTotalMaterialCost(new BigDecimal("2"));
        testOrder.setTotalLaborCost(new BigDecimal("2"));
        testOrder.setTotalTax(new BigDecimal("2"));
        testOrder.setTotalCost(new BigDecimal("2"));

        orderDao.createOrder(orderDate, testOrder);

        Tax testTax2 = new Tax("OH", new BigDecimal("6.25"));
        Product testProduct2 = new Product("Wood", new BigDecimal("5.15"), new BigDecimal("4.75"));

        Order testOrder2 = new Order();
        testOrder2.setCustomerName("Test Name");
        testOrder2.setArea(new BigDecimal("5"));
        testOrder2.setTaxObject(testTax2);
        testOrder2.setProductObject(testProduct2);
        testOrder2.setTotalMaterialCost(new BigDecimal("50"));
        testOrder2.setTotalLaborCost(new BigDecimal("10"));
        testOrder2.setTotalTax(new BigDecimal("62"));
        testOrder2.setTotalCost(new BigDecimal("5000"));

        orderDao.createOrder(orderDate, testOrder2);

        assertEquals(2, orderDao.retrieveAllOrdersByDate(orderDate).size());
    }

    @Test
    public void testRetrieveOrderByDateAndId() throws FlooringPersistenceException {
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
        testOrder.setOrderDate(orderDate);

        orderDao.createOrder(orderDate, testOrder);

        assertEquals(testOrder, orderDao.retrieveOrderByDateAndId(orderDate, testOrder.getOrderNumber()));
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

        orderDao.createOrder(orderDate, testOrder);

        assertEquals(1, orderDao.retrieveAllOrdersByDate(orderDate).size());
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

        orderDao.createOrder(orderDate, testOrder);
        orderDao.updateOrder(orderDate, testOrder);

        assertEquals(1, orderDao.retrieveAllOrdersByDate(orderDate).size());

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


        orderDao.createOrder(orderDate, testOrder);

        orderDao.removeOrder(orderDate, testOrder.getOrderNumber());
        // test the size
        assertEquals(0, orderDao.retrieveAllOrdersByDate(orderDate).size());

        // test that our exception was thrown because there is no order existing
        try {
            orderDao.retrieveOrderByDateAndId(orderDate, testOrder.getOrderNumber());
            fail("FlooringPersistenceException was not thrown");
        } catch (FlooringPersistenceException e) {
            return;

        }
    }
}