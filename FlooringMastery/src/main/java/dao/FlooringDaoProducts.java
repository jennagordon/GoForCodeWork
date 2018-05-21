package dao;

import dto.Product;

import java.util.List;

public interface FlooringDaoProducts {

    Product retrieveProductByMaterial(String material) throws FlooringPersistenceException;

    List<Product> retrieveAllProducts() throws FlooringPersistenceException;

    void createProduct(Product productObject) throws FlooringPersistenceException;

    void updateProduct(Product productObject) throws FlooringPersistenceException;

    void removeProduct(Product productObject) throws FlooringPersistenceException;
}
