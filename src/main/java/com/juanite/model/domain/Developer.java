package com.juanite.model.domain;

import com.juanite.model.domain.interfaces.iDeveloper;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Developer extends Entity implements iDeveloper {
    private String description;
    private String logo;

    public Developer() {
        this.name = "";
        this.description = "";
        this.country = Countries.NONE;
        this.birthDate = "";
        this.logo = "";
        this.games = new HashSet<Game>();
    }
    public Developer(String name, String description, Countries country , String birthdate, String logo) {
        this.name = name;
        this.description = description;
        this.country = country;
        this.birthDate = birthdate;
        this.logo = logo;
        this.games = new HashSet<Game>();
    }
    public Developer(String name, String description, Countries country , String birthdate, String logo, HashSet<Game> games) {
        this.name = name;
        this.description = description;
        this.country = country;
        this.birthDate = birthdate;
        this.logo = logo;
        this.games = games;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getBirthDate() {
        return this.birthDate;
    }

    @Override
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public Set<Game> getGames() {
        return this.games;
    }

    @Override
    public void setGames(Set<Game> games) {
        this.games = games;
    }

    @Override
    public Countries getCountry() {
        return this.country;
    }

    @Override
    public void setCountry(Countries country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Developer developer = (Developer) o;
        return Objects.equals(name, developer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }



    @Override
    public Developer create() {
        return this;
    }

    @Override
    public Developer update(String name, String description, String birthDate, Countries country, String logo) {
        this.name = name;
        this.description = description;
        this.birthDate = birthDate;
        this.country = country;
        this.logo = logo;

        return this;
    }

    @Override
    public Developer remove() {
        return this;
    }

    @Override
    public boolean addGame(Game game) {
        return this.games.add(game);
    }

    @Override
    public boolean removeGame(Game game) {
        return this.games.remove(game);
    }

}