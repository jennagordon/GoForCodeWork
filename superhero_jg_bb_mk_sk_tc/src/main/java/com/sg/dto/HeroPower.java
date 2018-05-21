package com.sg.dto;

import java.util.Objects;

public class HeroPower {

    private Long heroPowerId;
    private Hero hero;
    private Power power;

    public Long getHeroPowerId() {
        return heroPowerId;
    }

    public void setHeroPowerId(Long heroPowerId) {
        this.heroPowerId = heroPowerId;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Power getPower() {
        return power;
    }

    public void setPower(Power power) {
        this.power = power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HeroPower)) return false;
        HeroPower heroPower = (HeroPower) o;
        return Objects.equals(heroPowerId, heroPower.heroPowerId) &&
                Objects.equals(hero, heroPower.hero) &&
                Objects.equals(power, heroPower.power);
    }

    @Override
    public int hashCode() {

        return Objects.hash(heroPowerId, hero, power);
    }
}
