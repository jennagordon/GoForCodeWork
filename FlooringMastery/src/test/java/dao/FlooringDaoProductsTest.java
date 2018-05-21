package dao;

import dto.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class FlooringDaoProductsTest {
    private FlooringDaoProducts productsDao;

    public FlooringDaoProductsTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        productsDao = ctx.getBean("productsDao", FlooringDaoProducts.class);
    }

    @Before
    public void setUp() throws Exception {
        List<Product> productList = productsDao.retrieveAllProducts();

        for(Product tempProduct : productList) {
            productsDao.removeProduct(tempProduct);
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testRetrieveProductByMaterial() throws FlooringPersistenceException {
        Product testProduct = new Product("Wood", new BigDecimal("5.15"), new BigDecimal("4.75"));
        productsDao.createProduct(testProduct);

        assertEquals(testProduct, productsDao.retrieveProductByMaterial("Wood"));
    }

    @Test
    public void testRetrieveAllProducts() throws FlooringPersistenceException {
        Product testProduct1 = new Product("Wood", new BigDecimal("5.15"), new BigDecimal("4.75"));
        productsDao.createProduct(testProduct1);

        Product testProduct2 = new Product("Carpet", new BigDecimal("2.15"), new BigDecimal("2.10"));
        productsDao.createProduct(testProduct2);

        assertEquals(2, productsDao.retrieveAllProducts().size());
    }

    @Test
    public void testCreateProduct() throws FlooringPersistenceException {
        Product testProduct1 = new Product("Wood", new BigDecimal("5.15"), new BigDecimal("4.75"));
        productsDao.createProduct(testProduct1);

        assertEquals(1, productsDao.retrieveAllProducts().size());

        Product testProduct2 = new Product("Tile", new BigDecimal("3.50"), new BigDecimal("4.15"));
        productsDao.createProduct(testProduct2);

        assertEquals(2, productsDao.retrieveAllProducts().size());
    }

    // Do not need since it does the same thing as create
    //@Test
    //public void updateProduct() {
    //}

    @Test
    public void testRemoveProduct() throws FlooringPersistenceException {
        Product testProduct1 = new Product("Laminate", new BigDecimal("1.75"), new BigDecimal("2.10"));
        productsDao.createProduct(testProduct1);

        Product testProduct2 = new Product("Tile", new BigDecimal("3.50"), new BigDecimal("4.15"));
        productsDao.createProduct(testProduct2);

        productsDao.removeProduct(testProduct1);
        assertEquals(1, productsDao.retrieveAllProducts().size());
        assertNull(productsDao.retrieveProductByMaterial(testProduct1.getProductType()));

        productsDao.removeProduct(testProduct2);
        assertEquals(0, productsDao.retrieveAllProducts().size());
        assertNull(productsDao.retrieveProductByMaterial(testProduct2.getProductType()));
    }
}