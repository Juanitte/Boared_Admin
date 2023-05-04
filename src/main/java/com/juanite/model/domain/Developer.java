package com.juanite.model.domain;

import com.juanite.model.DAO.DeveloperDAO;
import com.juanite.model.domain.interfaces.iDeveloper;
import com.juanite.util.AppData;
import com.juanite.util.Utils;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Developer extends Entity implements iDeveloper, Observable {
    private String description;
    private String logo;

    public Developer() {
        this.name = "";
        this.description = "";
        this.country = Countries.NONE;
        this.birthDate = null;
        this.logo = "";
        this.games = new HashSet<Game>();
    }
    public Developer(String name, String description, Countries country , Date birthdate, String logo) {
        this.name = name;
        this.description = description;
        this.country = country;
        this.birthDate = birthdate;
        this.logo = logo;
        this.games = new HashSet<Game>();
    }
    public Developer(String name, String description, Countries country , Date birthdate, String logo, HashSet<Game> games) {
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
    public Date getBirthDate() {
        return this.birthDate;
    }

    @Override
    public void setBirthDate(Date birthDate) {
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
    public Developer create() throws Exception {
        try (DeveloperDAO ddao = new DeveloperDAO()) {
            ddao.save(this);
        }
        return this;
    }

    @Override
    public Developer update(Developer developer) throws Exception {
        try (DeveloperDAO ddao = new DeveloperDAO()) {
            if(ddao.find(developer.getName()) != null){
                ddao.save(developer);
            }else{
                ddao.save(developer, this.getName());
            }
        }

        return developer;
    }

    @Override
    public void remove() throws Exception {
        try (DeveloperDAO ddao = new DeveloperDAO()) {
            if(!ddao.hasGames(this)){
                ddao.delete(this);
                AppData.setDeveloper(null);
            }
        }
    }

    @Override
    public boolean addGame(Game game) {
        return this.games.add(game);
    }

    @Override
    public boolean removeGame(Game game) {
        return this.games.remove(game);
    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {

    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {

    }

    @Override
    public String toString() {
        return name;
    }
}