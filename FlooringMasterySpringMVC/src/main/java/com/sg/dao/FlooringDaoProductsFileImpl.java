package com.sg.dao;

import com.sg.dto.Product;


import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class FlooringDaoProductsFileImpl implements FlooringDaoProducts {

    private Map<String, Product> productMap = new HashMap<>();
    private static String FILE_NAME;
    private static final String STRING_DELIMITER = ",";

    public FlooringDaoProductsFileImpl(String FILE_NAME) {
        this.FILE_NAME = FILE_NAME;
    }

    @Override
    public Product retrieveProductByMaterial(String material) throws FlooringPersistenceException {
        loadProducts();
        return productMap.get(material);
    }

    @Override
    public List<Product> retrieveAllProducts() throws FlooringPersistenceException {
        loadProducts();
        return new ArrayList<>(productMap.values());
    }

    @Override
    public Product createProduct(Product productObject) throws FlooringPersistenceException {

        productMap.put(productObject.getProductType(), productObject);
        writeProducts();

        return productObject;

    }

    @Override
    public Product updateProduct(Product productObject) throws FlooringPersistenceException {

        productMap.put(productObject.getProductType(), productObject);
        writeProducts();

        return productObject;

    }

    @Override
    public void removeProduct(Product productObject) throws FlooringPersistenceException {

        productMap.remove(productObject.getProductType());
        writeProducts();

    }

    private void loadProducts() throws FlooringPersistenceException {
        // load products into map
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(FILE_NAME)));

        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException("Could not load products data into memory", e);
        }

        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(STRING_DELIMITER);

            Product productObject = new Product(currentTokens[0], new BigDecimal(currentTokens[1]), new BigDecimal(currentTokens[2]));

            productMap.put(productObject.getProductType(), productObject);
        }
        scanner.close();
    }

    private void writeProducts() throws FlooringPersistenceException {

        PrintWriter out;

        try{
            out = new PrintWriter(new FileWriter(FILE_NAME));

        } catch (IOException e) {
            throw new FlooringPersistenceException("Could not load products data into memory");
        }

        List<Product> productList = this.retrieveAllProducts();

        for(Product tempProduct : productList) {
            out.println(tempProduct.getProductType() + STRING_DELIMITER
            + tempProduct.getMaterialCostPerSquareFoot()
            + STRING_DELIMITER + tempProduct.getLaborCostPerSquareFoot());

            out.flush();
        }
        out.close();

    }
}
