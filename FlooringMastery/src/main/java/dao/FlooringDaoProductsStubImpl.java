package dao;

import dto.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FlooringDaoProductsStubImpl implements FlooringDaoProducts {

    Product stubTestProduct;
    List<Product> productList = new ArrayList<>();


    public FlooringDaoProductsStubImpl() {
        stubTestProduct = new Product("Wood", new BigDecimal("5.15"), new BigDecimal("4.75"));
        productList.add(stubTestProduct);
    }

    @Override
    public Product retrieveProductByMaterial(String material) throws FlooringPersistenceException {
        if(stubTestProduct.getProductType().equals(material)) {
            return stubTestProduct;
        } else {
            return null;
        }
    }

    @Override
    public List<Product> retrieveAllProducts() throws FlooringPersistenceException {
        return productList;
    }

    @Override
    public void createProduct(Product productObject) throws FlooringPersistenceException {

    }

    @Override
    public void updateProduct(Product productObject) throws FlooringPersistenceException {

    }

    @Override
    public void removeProduct(Product productObject) throws FlooringPersistenceException {

    }
}
