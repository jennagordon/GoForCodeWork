package com.sg.dao;

import com.sg.dto.Order;
import com.sg.dto.Product;
import com.sg.dto.Tax;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class FlooringDaoOrderJdbcTemplateImpl implements FlooringDaoOrder {

    JdbcTemplate jt;

    public void setJdbcTemplate(JdbcTemplate jt) {
        this.jt = jt;
    }

    private static final String SQL_INSERT_ORDER = "insert into Orders (" +
            "OrderDate, CustomerName, Area, TotalMaterialCost, TotalLaborCost, TotalTax," +
            "TotalCost, TaxID, ProductID) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_ORDER = "delete from Orders where OrderID = ?";

    private static final String SQL_UPDATE_ORDER = "update Orders set OrderDate = ?, CustomerName = ?, Area = ?," +
            "TotalMaterialCost = ?, TotalLaborCost = ?, TotalTax = ?, TotalCost = ?, TaxID = ?, ProductID = ?";

    private static final String SQL_SELECT_ORDER_BY_ID = "select * from Orders where OrderID = ?";

    private static final String SQL_SELECT_ALL_ORDERS = "select * from Orders";

    @Override
    public List<Order> retrieveAllOrders() throws FlooringPersistenceException {
        return jt.query(SQL_SELECT_ALL_ORDERS, new OrderMapper());
    }

    @Override
    public Order retrieveOrderByDateAndId(Order order) throws FlooringPersistenceException {
        return jt.queryForObject(SQL_SELECT_ORDER_BY_ID, new OrderMapper(), order.getOrderId());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Order createOrder(Order order) throws FlooringPersistenceException {
        jt.update(SQL_INSERT_ORDER, Date.valueOf(order.getOrderDate()), order.getCustomerName(), order.getArea(),
                order.getTotalMaterialCost(), order.getTotalLaborCost(), order.getTotalTax(), order.getTotalCost(),
                order.getTaxObject().getTaxId(), order.getProductObject().getProductId());

        int id = jt.queryForObject("select LAST_INSERT_ID()", Integer.class);
        order.setOrderId(id);
        return order;
    }

    @Override
    public void updateOrder(Order order) throws FlooringPersistenceException {

        jt.update(SQL_UPDATE_ORDER, order.getOrderId(), Date.valueOf(order.getOrderDate()), order.getArea(),
                order.getTotalMaterialCost(), order.getTotalCost(), order.getTotalTax(), order.getTotalCost(), order.getTaxObject().getTaxId(),
                order.getProductObject().getProductId());

    }

    @Override
    public void removeOrder(Order order) throws FlooringPersistenceException {
        jt.update(SQL_DELETE_ORDER, order.getOrderId());

    }

    @Override
    public void saveOrder() throws FlooringPersistenceException {

    }

    private static final class OrderMapper implements RowMapper<Order> {

        @Override
        public Order mapRow(ResultSet rs, int i) throws SQLException {
            Order order = new Order();
            Product product = new Product();
            Tax tax = new Tax();

            // LAZY LOADING, setting up fake objects
           product.setProductId(rs.getInt("ProductID"));
           tax.setTaxId(rs.getInt("TaxID"));
           order.setProductObject(product);
           order.setTaxObject(tax);

           order.setOrderId(rs.getInt("OrderID"));
           order.setOrderDate(rs.getDate("OrderDate").toLocalDate());
           order.setCustomerName("CustomerName");
           order.setArea(rs.getBigDecimal("Area"));
           order.setTotalMaterialCost(rs.getBigDecimal("TotalMaterialCost"));
           order.setTotalLaborCost(rs.getBigDecimal("TotalLaborCost"));
           order.setTotalTax(rs.getBigDecimal("TotalTax"));
           order.setTotalCost(rs.getBigDecimal("TotalCost"));

            return order;
        }

    }
}
