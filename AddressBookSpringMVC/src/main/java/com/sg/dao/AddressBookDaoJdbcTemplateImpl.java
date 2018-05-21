package com.sg.dao;

import com.sg.model.Address;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AddressBookDaoJdbcTemplateImpl implements AddressBookDao {

    JdbcTemplate jt;

    public void setJdbcTemplate(JdbcTemplate jt) {
        this.jt = jt;
    }

    private static final String SQL_INSERT_ADDRESS = "insert into Address (FirstName, LastName, Street," +
            "City, State, Zip) Values(?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_ADDRESS = "delete from Address where AddressID = ?";

    private static final String SQL_UPDATE_ADDRESS = "update Address set FirstName = ?, LastName = ?," +
            "Street = ?, City = ?, State = ?, Zip = ? where AddressID = ?";

    private static final String SQL_SELECT_ADDRESS_BY_ID = "select * from Address where AddressID = ?";

    private static final String SQL_SELECT_ALL_ADDRESSES = "select * from Address";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Address addAddress(Address address) {
        jt.update(SQL_INSERT_ADDRESS, address.getFirstName(), address.getLastName(), address.getStreetAddress(),
                address.getCity(), address.getState(), address.getZipCode());

        int id = jt.queryForObject("select LAST_INSERT_ID()", Integer.class);

        address.setAddressId(id);

        return address;
    }

    @Override
    public Address retrieveAddress(int addressId) {
        return jt.queryForObject(SQL_SELECT_ADDRESS_BY_ID, new AddressMapper(), addressId);
    }

    @Override
    public List<Address> retrieveAllAddresses() {
        return jt.query(SQL_SELECT_ALL_ADDRESSES, new AddressMapper());
    }

    @Override
    public void removeAddress(int addressId) {
        jt.update(SQL_DELETE_ADDRESS, addressId);

    }

    @Override
    public void editAddress(Address address) {
        jt.update(SQL_UPDATE_ADDRESS, address.getFirstName(), address.getLastName(), address.getStreetAddress(),
                address.getCity(), address.getState(), address.getZipCode(), address.getAddressId());

    }

    private static final class AddressMapper implements RowMapper<Address> {

        @Override
        public Address mapRow(ResultSet rs, int i) throws SQLException {
            Address address = new Address();
            address.setFirstName(rs.getString("FirstName"));
            address.setLastName(rs.getString("LastName"));
            address.setStreetAddress(rs.getString("Street"));
            address.setCity(rs.getString("City"));
            address.setState(rs.getString("State"));
            address.setZipCode(rs.getString("Zip"));
            address.setAddressId(rs.getInt("AddressID"));

            return address;
        }
    }
}
