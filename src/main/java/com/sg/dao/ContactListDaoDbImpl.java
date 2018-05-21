package com.sg.dao;

import com.sg.model.Contact;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ContactListDaoDbImpl implements ContactListDao {

    // creating prepared statements
    private static final String SQL_INSERT_CONTACT = "insert into contacts " +
            "(FirstName, LastName, Company, Phone, Email) " +
            "values (?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_CONTACT = "delete from contacts where ContactID = ?";
    private static final String SQL_SELECT_CONTACT = "select * from contacts where ContactID =?";
    private static final String SQL_UPDATE_CONTACT = "update contacts set " + "FirstName = ?, LastName = ?," +
            "Company = ?, Phone = ?, Email = ? " + "where ContactId = ?";
    private static final String SQL_SELECT_ALL_CONTACTS = "select * from Contacts";
    private static final String SQL_SELECT_CONTACTS_BY_LAST_NAME = "select * from contacts where LastName = ?";
    private static final String SQL_SELECT_CONTACTS_BY_COMPANY = "select * from contacts where Company = ?";

    // injecting the JDBCTemplate so we can execute against the database
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Contact addContact(Contact contact) {
        jdbcTemplate.update(SQL_INSERT_CONTACT, contact.getFirstName(), contact.getLastName(), contact.getCompany(),
                contact.getPhone(), contact.getEmail());

        // query the database for the id that was just assigned to the new
        // row in the database
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        // set the new id value on the contact object and return it
        contact.setContactId(newId);
        return contact;
    }

    @Override
    public void removeContact(long contactId) {
        jdbcTemplate.update(SQL_DELETE_CONTACT, contactId);
    }

    @Override
    public void updateContact(Contact contact) {
        jdbcTemplate.update(SQL_UPDATE_CONTACT, contact.getFirstName(), contact.getLastName(),
                contact.getCompany(), contact.getPhone(), contact.getEmail(), contact.getContactId());
    }

    @Override
    public List<Contact> retrieveAllContacts() {
        return jdbcTemplate.query(SQL_SELECT_ALL_CONTACTS, new ContactMapper());
    }

    @Override
    public Contact retrieveContactById(long contactId) {
        try{
            return jdbcTemplate.queryForObject(SQL_SELECT_CONTACT, new ContactMapper(), contactId);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given contact id - we just
            // want to return null in this case
            return null;
        }
    }

    @Override
    public List<Contact> searchContacts(Map<SearchTerm, String> criteria) {
        if (criteria.isEmpty()) {
            return retrieveAllContacts();
        } else {
            // build a prepared statement based on the user's search
            // terms
            StringBuilder sQuery =
                    new StringBuilder("select * from contacts where ");
            // build the where clause
            int numParams = criteria.size();
            int paramPosition = 0;
            // we'll put the positional parameters into an array, the
            // order of the parameters will match the order in which we
            // get the search criteria from the map
            String[] paramVals = new String[numParams];
            Set<SearchTerm> keySet = criteria.keySet();
            Iterator<SearchTerm> iter = keySet.iterator();
            // build up the where clause based on the key/value pairs in
            // the map build where clause and positional parameter array
            while (iter.hasNext()) {
                SearchTerm currentKey = iter.next();
                // if we are not the first one in, we must add an AND to
                // the where clause
                if (paramPosition > 0) {
                    sQuery.append(" and ");
                }
                // now append our criteria name
                sQuery.append(currentKey);
                sQuery.append(" = ? ");
                // grab the value for this search criteria and put it into
                // the paramVals array
                paramVals[paramPosition] = criteria.get(currentKey);
                paramPosition++;
            }

            return jdbcTemplate.query(sQuery.toString(),
                    new ContactMapper(),
                    paramVals);
        }
    }

    private static final class ContactMapper implements RowMapper<Contact> {

        @Override
        public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {

            Contact contact = new Contact();
            contact.setContactId(rs.getLong("ContactID"));
            contact.setFirstName(rs.getString("FirstName"));
            contact.setLastName(rs.getString("LastName"));
            contact.setCompany(rs.getString("Company"));
            contact.setPhone(rs.getString("Phone"));
            contact.setEmail(rs.getString("Email"));

            return contact;
        }
    }
}
