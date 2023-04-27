package com.juanite.model.domain;

import com.juanite.interfaces.iGame;

import java.util.*;

public class Game implements iGame {

    private String title;
    private String description;
    private Set<Genres> genres;
    private int releaseYear;
    private double price;
    private String logo;
    private double score;
    private List<String> images;
    private Developer developer;

    public Game() {
        this("", "", new HashSet<Genres>(), 0, 0, "", 0, new ArrayList<String>(), null);
    }

    public Game(String title, String description, Set<Genres> genres, int releaseYear, double price, String logo, Developer developer) {
        this(title, description, genres, releaseYear, price, logo, 0, new ArrayList<String>(), developer);
    }

    public Game(String title, String description, Set<Genres> genres, int releaseYear, double price, String logo, double score, List<String> images, Developer developer) {
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.price = price;
        this.logo = logo;
        this.images = images;
        this.developer = developer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Genres> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genres> genres) {
        this.genres = genres;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(title, game.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}