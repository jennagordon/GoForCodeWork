package com.sg.model;

import java.util.Objects;

public class Author {

    private int authorId;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return authorId == author.authorId &&
                Objects.equals(firstName, author.firstName) &&
                Objects.equals(lastName, author.lastName) &&
                Objects.equals(street, author.street) &&
                Objects.equals(city, author.city) &&
                Objects.equals(state, author.state) &&
                Objects.equals(zip, author.zip) &&
                Objects.equals(phone, author.phone);
    }

    @Override
    public int hashCode() {

        return Objects.hash(authorId, firstName, lastName, street, city, state, zip, phone);
    }
}
