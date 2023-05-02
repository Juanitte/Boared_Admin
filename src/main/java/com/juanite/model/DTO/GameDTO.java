package com.juanite.model.DTO;

import com.juanite.model.domain.Tags;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.util.HashSet;
import java.util.Set;

public class GameDTO implements Observable {

    private String title;
    private String logo;
    private Set<Tags> tags;
    private double price;
    private String developer;

    public GameDTO(){
        this("", "", new HashSet<Tags>(), -1, "");
    }
    public GameDTO(String title, String logo, Set<Tags> tags, double price, String developer) {
        this.title = title;
        this.logo = logo;
        this.tags = tags;
        this.price = price;
        this.developer = developer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Set<Tags> getTags() {
        return tags;
    }

    public void setTags(Set<Tags> tags) {
        this.tags = tags;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {

    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {

    }
}
