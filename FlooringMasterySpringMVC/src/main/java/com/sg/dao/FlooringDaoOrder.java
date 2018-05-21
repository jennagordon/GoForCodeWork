package com.sg.dao;


import com.sg.dto.Order;

import java.time.LocalDate;
import java.util.List;

public interface FlooringDaoOrder {

    List<Order> retrieveAllOrders() throws FlooringPersistenceException;

    Order retrieveOrderByDateAndId(Order order) throws FlooringPersistenceException;

    Order createOrder(Order order) throws FlooringPersistenceException;

    void updateOrder(Order order) throws FlooringPersistenceException;

    void removeOrder(Order order) throws FlooringPersistenceException;

    void saveOrder() throws FlooringPersistenceException;



}