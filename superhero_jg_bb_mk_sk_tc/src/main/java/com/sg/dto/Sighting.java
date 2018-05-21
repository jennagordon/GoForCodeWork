package com.sg.dto;

import java.time.LocalDate;
import java.util.Objects;

public class Sighting {

    private Long sightingId;
    private LocalDate date;
    private String description;
    private Location location;

    public Long getSightingId() {
        return sightingId;
    }

    public void setSightingId(Long sightingId) {
        this.sightingId = sightingId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sighting)) return false;
        Sighting sighting = (Sighting) o;
        return Objects.equals(sightingId, sighting.sightingId) &&
                Objects.equals(date, sighting.date) &&
                Objects.equals(description, sighting.description) &&
                Objects.equals(location, sighting.location);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sightingId, date, description, location);
    }
}
