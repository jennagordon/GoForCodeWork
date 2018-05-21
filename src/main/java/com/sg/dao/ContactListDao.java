package com.sg.dao;

import com.sg.model.Contact;

import java.util.List;
import java.util.Map;

public interface ContactListDao {
    // add the given Contact to the data store
    public Contact addContact(Contact contact);

    // remove the contact with the given id from the data store
    public void removeContact(long contactId);

    // update the given contact in the data store
    public void updateContact(Contact contact);

    // retrieve all contacts from the data store
    public List<Contact> retrieveAllContacts();

    // retrieve the contact with the given id from the data store
    public Contact retrieveContactById(long contactId);

    // search for contact by the given search criteria values
    public List<Contact> searchContacts(Map<SearchTerm, String> criteria);
}
