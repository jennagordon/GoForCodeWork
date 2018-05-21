package com.sg.service;

import com.sg.dao.FlooringPersistenceException;
import com.sg.dto.Order;
import com.sg.dto.Product;
import com.sg.dto.Tax;

import java.time.LocalDate;
import java.util.List;

public interface FlooringServiceLayer {

    List<Order> retrieveAllOrdersByDate(LocalDate orderDate) throws FlooringPersistenceException, OrderNotFoundException;

    List<Product> retrieveAllProducts() throws FlooringPersistenceException;

    List<Tax> retrieveAllTaxes() throws FlooringPersistenceException;

    Order processOrder(Order orderObject) throws FlooringPersistenceException, TaxStateNotFoundException, ProductMaterialNotFoundException;

    Order addOrder(Order orderObject) throws FlooringPersistenceException, TaxStateNotFoundException, ProductMaterialNotFoundException;

    Order retrieveOrderByDateAndId(LocalDate orderDate, String orderNumber) throws FlooringPersistenceException, OrderNotFoundException;

    void removeOrder(LocalDate orderDate, String orderNumber) throws FlooringPersistenceException, OrderNotFoundException;

    Order editOrder(LocalDate date, Order orderObject) throws FlooringPersistenceException, TaxStateNotFoundException, ProductMaterialNotFoundException;

    void saveAllOrders() throws FlooringPersistenceException;

    void activateTrainingMode(boolean userSelection);

}
