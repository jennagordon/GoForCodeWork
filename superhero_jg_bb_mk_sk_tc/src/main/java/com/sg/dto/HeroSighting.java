package com.sg.dto;

import java.util.Objects;

public class HeroSighting {

    private Long heroSightingId;
    private Hero hero;
    private Sighting sighting;

    public Long getHeroSightingId() {
        return heroSightingId;
    }

    public void setHeroSightingId(Long heroSightingId) {
        this.heroSightingId = heroSightingId;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Sighting getSighting() {
        return sighting;
    }

    public void setSighting(Sighting sighting) {
        this.sighting = sighting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HeroSighting)) return false;
        HeroSighting that = (HeroSighting) o;
        return Objects.equals(heroSightingId, that.heroSightingId) &&
                Objects.equals(hero, that.hero) &&
                Objects.equals(sighting, that.sighting);
    }

    @Override
    public int hashCode() {

        return Objects.hash(heroSightingId, hero, sighting);
    }
}
