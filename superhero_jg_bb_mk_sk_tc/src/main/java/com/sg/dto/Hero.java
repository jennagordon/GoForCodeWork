package com.sg.dto;

import java.util.Objects;

public class Hero {

    private Long heroId;
    private String name;
    private String description;

    public Long getHeroId() {
        return heroId;
    }

    public void setHeroId(Long heroId) {
        this.heroId = heroId;
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
        if (!(o instanceof Hero)) return false;
        Hero hero = (Hero) o;
        return Objects.equals(heroId, hero.heroId) &&
                Objects.equals(name, hero.name) &&
                Objects.equals(description, hero.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(heroId, name, description);
    }
}
