package com.sg.dao;

import com.sg.dto.Tax;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class FlooringDaoTaxesTest {

    private FlooringDaoTaxes taxesDao;

    public FlooringDaoTaxesTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
         taxesDao = ctx.getBean("taxDao", FlooringDaoTaxes.class);
    }

    @Before
    public void setUp() throws Exception {
        List<Tax> taxList = taxesDao.retrieveAllTaxes();

        for(Tax tempTax : taxList) {
            taxesDao.removeTax(tempTax);
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testRetrieveTaxByState() throws FlooringPersistenceException {
        // arrange: create item
        Tax testTax = new Tax("OH", new BigDecimal("6.25"));
        taxesDao.createTax(testTax);
        // act: get item by state
        // assert: testTax is equal on my map
        assertEquals(testTax, taxesDao.retrieveTaxByState("OH"));
    }

    @Test
    public void testRetrieveAllTaxes() throws FlooringPersistenceException {
        Tax testTax = new Tax("OH", new BigDecimal("6.25"));
        taxesDao.createTax(testTax);

        assertEquals(1, taxesDao.retrieveAllTaxes().size());
    }

    @Test
    public void createTax() throws FlooringPersistenceException {
        Tax testTax1 = new Tax("MI", new BigDecimal("5.75"));
        taxesDao.createTax(testTax1);

        // testing first object was added
        assertEquals(1, taxesDao.retrieveAllTaxes().size());

        Tax testTax2 = new Tax("IN", new BigDecimal("6.00"));
        taxesDao.createTax(testTax2);

        // testing second object was added
        assertEquals(2, taxesDao.retrieveAllTaxes().size());

    }

    // Don't need because it is the exact same as create
    //@Test
    //public void updateTax() {
    //}

    @Test
    public void testRemoveTax() throws FlooringPersistenceException {
        Tax testTax1 = new Tax("OH", new BigDecimal("6.25"));
        taxesDao.createTax(testTax1);

        Tax testTax2 = new Tax("PA", new BigDecimal("6.25"));
        taxesDao.createTax(testTax2);

        taxesDao.removeTax(testTax1);
        assertEquals(1, taxesDao.retrieveAllTaxes().size());

        taxesDao.removeTax(testTax2);
        assertEquals(0, taxesDao.retrieveAllTaxes().size());

    }
}