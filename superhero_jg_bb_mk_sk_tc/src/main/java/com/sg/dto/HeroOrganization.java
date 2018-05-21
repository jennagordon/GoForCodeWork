package com.sg.dto;

import java.util.Objects;

public class HeroOrganization {

    private Long heroOrganizationId;
    private Hero hero;
    private Organization organization;

    public Long getHeroOrganizationId() {
        return heroOrganizationId;
    }

    public void setHeroOrganizationId(Long heroOrganizationId) {
        this.heroOrganizationId = heroOrganizationId;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HeroOrganization)) return false;
        HeroOrganization that = (HeroOrganization) o;
        return Objects.equals(heroOrganizationId, that.heroOrganizationId) &&
                Objects.equals(hero, that.hero) &&
                Objects.equals(organization, that.organization);
    }

    @Override
    public int hashCode() {

        return Objects.hash(heroOrganizationId, hero, organization);
    }
}
