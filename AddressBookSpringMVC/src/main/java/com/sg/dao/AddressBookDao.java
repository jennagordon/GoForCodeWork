package com.sg.dao;

import com.sg.model.Address;

import java.util.List;
import java.util.Map;

public interface AddressBookDao {

    //Adds an address
    Address addAddress(Address address);

    //View an address
    Address retrieveAddress(int addressId);

    //View number of addresses
    List<Address> retrieveAllAddresses();

    //Delete an address
    void removeAddress(int addressId);

    //edit an address
    void editAddress(Address address);
}
