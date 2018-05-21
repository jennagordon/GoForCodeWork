package com.sg.dto;

import java.util.Objects;

public class Location {

    private Long locationId;
    private String description;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String latitude;
    private String longitude;

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return Objects.equals(locationId, location.locationId) &&
                Objects.equals(description, location.description) &&
                Objects.equals(street, location.street) &&
                Objects.equals(city, location.city) &&
                Objects.equals(state, location.state) &&
                Objects.equals(zip, location.zip) &&
                Objects.equals(latitude, location.latitude) &&
                Objects.equals(longitude, location.longitude);
    }

    @Override
    public int hashCode() {

        return Objects.hash(locationId, description, street, city, state, zip, latitude, longitude);
    }
}
