package com.sg.dto;

import java.util.Objects;

public class Power {

    private Long powerId;
    private String name;
    private String description;

    public Long getPowerId() {
        return powerId;
    }

    public void setPowerId(Long powerId) {
        this.powerId = powerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Power)) return false;
        Power power = (Power) o;
        return Objects.equals(powerId, power.powerId) &&
                Objects.equals(name, power.name) &&
                Objects.equals(description, power.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(powerId, name, description);
    }
}
