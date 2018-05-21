package com.sg.dao;

import com.sg.dto.Product;


import java.util.List;

public interface FlooringDaoProducts {

    Product retrieveProductByMaterial(String material) throws FlooringPersistenceException;

    List<Product> retrieveAllProducts() throws FlooringPersistenceException;

    Product createProduct(Product productObject) throws FlooringPersistenceException;

    Product updateProduct(Product productObject) throws FlooringPersistenceException;

    void removeProduct(Product productObject) throws FlooringPersistenceException;
}
