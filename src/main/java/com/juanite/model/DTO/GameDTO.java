package com.juanite.model.DTO;

import com.juanite.model.domain.Tags;

import java.util.HashSet;
import java.util.Set;

public class GameDTO {

    private String title;
    private String logo;
    private Set<Tags> tags;
    private double price;
    private DeveloperDTO developer;

    public GameDTO(){
        this("", "", new HashSet<Tags>(), -1, null);
    }
    public GameDTO(String title, String logo, Set<Tags> tags, double price, DeveloperDTO developer) {
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

    public DeveloperDTO getDeveloper() {
        return developer;
    }

    public void setDeveloper(DeveloperDTO developer) {
        this.developer = developer;
    }
}
