package com.juanite.model.domain;

import java.sql.Date;
import java.util.Set;

public abstract class Entity {

    protected String name;
    protected Date birthDate;
    protected Set<Game> games;
    protected Countries country;

    public abstract String getName();
    public abstract void setName(String name);
    public abstract Date getBirthDate();
    public abstract void setBirthDate(Date birthDate);
    public abstract Set<Game> getGames();
    public abstract void setGames(Set<Game> games);
    public abstract Countries getCountry();
    public abstract void setCountry(Countries country);
    public abstract Entity create() throws Exception;
    public abstract void remove() throws Exception;
}