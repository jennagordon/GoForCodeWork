package com.sg.dao;

import com.sg.model.Address;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AddressBookDaoTest {

    AddressBookDao dao;


    @Before
    public void setUp() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("dao", AddressBookDao.class);

        List<Address> addressList = dao.retrieveAllAddresses();
        for (Address currentAddress : addressList) {
           dao.removeAddress(currentAddress.getAddressId());
        }

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addAddress() {
        Address address = new Address();
        address.setFirstName("Joe");
        address.setLastName("Schmoe");
        address.setStreetAddress("123 Main St");
        address.setCity("Deerfield");
        address.setState("MA");
        address.setZipCode("01373");

        dao.addAddress(address);

        assertEquals(1, dao.retrieveAllAddresses().size());

        Address retrieveAddress = dao.retrieveAddress(address.getAddressId());

        assertEquals(address.getFirstName(), retrieveAddress.getFirstName());
    }

    @Test
    public void retrieveAddress() {
        Address address = new Address();
        address.setFirstName("Joe");
        address.setLastName("Schmoe");
        address.setStreetAddress("123 Main St");
        address.setCity("Deerfield");
        address.setState("MA");
        address.setZipCode("01373");

        dao.addAddress(address);

        assertEquals(1, dao.retrieveAllAddresses().size());

        Address retrieveAddress = dao.retrieveAddress(address.getAddressId());

        assertEquals(address.getFirstName(), retrieveAddress.getFirstName());
        assertEquals(address.getLastName(), retrieveAddress.getLastName());
        assertEquals(address.getStreetAddress(), retrieveAddress.getStreetAddress());
    }

    @Test
    public void retrieveAllAddresses() {
        Address address = new Address();
        address.setFirstName("Joe");
        address.setLastName("Schmoe");
        address.setStreetAddress("123 Main St");
        address.setCity("Deerfield");
        address.setState("MA");
        address.setZipCode("01373");

        dao.addAddress(address);
        assertEquals(1, dao.retrieveAllAddresses().size());

        Address address2 = new Address();
        address.setFirstName("Julie");
        address.setLastName("Schmoe");
        address.setStreetAddress("123 Main St");
        address.setCity("Deerfield");
        address.setState("MA");
        address.setZipCode("01373");
        dao.addAddress(address2);
        assertEquals(2, dao.retrieveAllAddresses().size());

    }

    @Test
    public void removeAddress() {
        Address address = new Address();
        address.setFirstName("Joe");
        address.setLastName("Schmoe");
        address.setStreetAddress("123 Main St");
        address.setCity("Deerfield");
        address.setState("MA");
        address.setZipCode("01373");

        dao.addAddress(address);
        assertEquals(1, dao.retrieveAllAddresses().size());
        dao.removeAddress(address.getAddressId());
        assertEquals(dao.retrieveAllAddresses().size(), 0);
    }

    @Test
    public void editAddress() {
        Address address = new Address();
        address.setFirstName("Joe");
        address.setLastName("Schmoe");
        address.setStreetAddress("123 Main St");
        address.setCity("Deerfield");
        address.setState("MA");
        address.setZipCode("01373");

        dao.addAddress(address);

        Address addressToEdit = dao.retrieveAddress(address.getAddressId());

        addressToEdit.setCity("South Deerfield");
        dao.editAddress(addressToEdit);
        assertEquals("South Deerfield", addressToEdit.getCity());
    }
}