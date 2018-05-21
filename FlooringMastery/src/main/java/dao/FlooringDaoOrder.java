package dao;


import dto.Order;


import java.time.LocalDate;
import java.util.List;

public interface FlooringDaoOrder {

    List<Order> retrieveAllOrdersByDate(LocalDate orderDate) throws FlooringPersistenceException;

    Order retrieveOrderByDateAndId(LocalDate orderDate, String id) throws FlooringPersistenceException;

    Order createOrder(LocalDate orderDate, Order orderObject) throws FlooringPersistenceException;

    void updateOrder(LocalDate orderDate, Order orderObject) throws FlooringPersistenceException;

    void removeOrder(LocalDate orderDate, String orderNumber) throws FlooringPersistenceException;

    void saveOrder () throws FlooringPersistenceException;



}