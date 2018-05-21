package com.sg.dao;

import com.sg.dto.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.List;

public class FlooringDaoProductsJdbcTemplateImpl implements FlooringDaoProducts {

    JdbcTemplate jt;

    public void setJdbcTemplate(JdbcTemplate jt) {
        this.jt = jt;
    }

    private static final String SQL_INSERT_PRODUCT = "insert into Products (ProductType, MaterialPerSqFt, LaborCostPerSqFt) values(" +
            "?, ?, ?)";

    private static final String SQL_DELETE_PRODUCT = "delete from Products where ProductID = ?";

    private static final String SQL_UPDATE_PRODUCT = "update Products set ProductType = ?, MaterialPerSqFt = ?, LaborCostPerSqFt = ?";

    private static final String SQL_SELECT_PRODUCT = "select * from Products where ProductType = ?";

    private static final String SQL_SELECT_ALL_PRODUCT = "select * from Products";

    @Override
    public Product retrieveProductByMaterial(String material) throws FlooringPersistenceException {
        return jt.queryForObject(SQL_SELECT_PRODUCT, new ProductMapper(), material);
    }

    @Override
    public List<Product> retrieveAllProducts() throws FlooringPersistenceException {
        return jt.query(SQL_SELECT_ALL_PRODUCT, new ProductMapper());
    }

    @Override
    public Product createProduct(Product productObject) throws FlooringPersistenceException {
        jt.update(SQL_INSERT_PRODUCT, productObject.getProductType(), productObject.getMaterialCostPerSquareFoot(),
                productObject.getLaborCostPerSquareFoot());

        int id = jt.queryForObject("select LAST_INSERT_ID()", Integer.class);

        productObject.setProductId(id);

        return productObject;

    }

    @Override
    public Product updateProduct(Product productObject) throws FlooringPersistenceException {

        jt.update(SQL_UPDATE_PRODUCT, productObject.getProductType(), productObject.getLaborCostPerSquareFoot(),
                productObject.getMaterialCostPerSquareFoot(), productObject.getProductId());

        return productObject;

    }

    @Override
    public void removeProduct(Product productObject) throws FlooringPersistenceException {

        jt.update(SQL_DELETE_PRODUCT, productObject.getProductId());

    }

    private static final class ProductMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int i) throws SQLException {
            Product product = new Product();

            product.setProductType(rs.getString("ProductType"));
            product.setMaterialCostPerSquareFoot(rs.getBigDecimal("MaterialPerSqFt"));
            product.setLaborCostPerSquareFoot(rs.getBigDecimal("LaborCostPerSqFt"));
            product.setProductId(rs.getInt("ProductID"));

            return product;
        }
    }
}
