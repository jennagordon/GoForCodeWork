package com.sg.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Objects;

public class Contact {

    private long contactId;

    @NotEmpty(message = "You must supply a value for First Name." )
    @Length(max = 50, message = "First Name must be no more than 50 characters in length." )
    private String firstName;

    @NotEmpty(message = "You must supply a value for Last Name.")
    @Length(max = 50, message = "Last Name must be no more than 50 characters in length.")
    private String lastName;

    @NotEmpty(message = "You must supply a value for Company.")
    @Length(max = 50, message = "Company must be no more than 50 characters in length.")
    private String company;

    @NotEmpty(message = "You must supply a value for Phone.")
    @Length(max = 10, message = "Phone must be no more than 10 characters in length")
    private String phone;

    @Email(message = "Please enter a valid email address.")
    @Length(max = 50, message = "Email must be no more than 50 characters in length.")
    private String email;

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return contactId == contact.contactId &&
                Objects.equals(firstName, contact.firstName) &&
                Objects.equals(lastName, contact.lastName) &&
                Objects.equals(company, contact.company) &&
                Objects.equals(phone, contact.phone) &&
                Objects.equals(email, contact.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(contactId, firstName, lastName, company, phone, email);
    }
}
