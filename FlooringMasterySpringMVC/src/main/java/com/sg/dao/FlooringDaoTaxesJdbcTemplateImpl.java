package com.sg.dao;

import com.sg.dto.Tax;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FlooringDaoTaxesJdbcTemplateImpl implements FlooringDaoTaxes {

    JdbcTemplate jt;

    public void setJdbcTemplate(JdbcTemplate jt) {
        this.jt = jt;
    }

    private static final String SQL_INSERT_TAX = "insert into Taxes (State, TaxRate) values(?,?)";

    private static final String SQL_DELETE_TAX = "delete from Taxes where TaxID = ?";

    private static final String SQL_UPDATE_TAX = "update Taxes set State = ?, TaxRate = ? where TaxID = ?";

    private static final String SQL_SELECT_TAX = "select * from Taxes where State = ?";

    private static final String SQL_SELECT_ALL_TAX = "select * from Taxes";

    @Override
    public Tax retrieveTaxByState(String state) throws FlooringPersistenceException {
        return jt.queryForObject(SQL_SELECT_TAX, new TaxMapper(), state);
    }

    @Override
    public List<Tax> retrieveAllTaxes() throws FlooringPersistenceException {
        return jt.query(SQL_SELECT_ALL_TAX, new TaxMapper());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Tax createTax(Tax taxObject) throws FlooringPersistenceException {

        jt.update(SQL_INSERT_TAX, taxObject.getState(), taxObject.getTaxRate());

        int id = jt.queryForObject("select LAST_INSERT_ID()", Integer.class);

        taxObject.setTaxId(id);

        return taxObject;

    }

    @Override
    public Tax updateTax(Tax taxObject) throws FlooringPersistenceException {

        jt.update(SQL_UPDATE_TAX, taxObject.getState(), taxObject.getTaxRate(), taxObject.getTaxId());

        return taxObject;

    }

    @Override
    public void removeTax(Tax taxObject) throws FlooringPersistenceException {
        jt.update(SQL_DELETE_TAX, taxObject.getTaxId());

    }

    private static final class TaxMapper implements RowMapper<Tax> {

        @Override
        public  Tax mapRow(ResultSet rs, int i) throws SQLException {
            Tax tax = new Tax();
            tax.setState(rs.getString("State"));
            tax.setTaxRate(rs.getBigDecimal("TaxRate"));
            tax.setTaxId(rs.getInt("TaxID"));

            return tax;
        }
    }
}
