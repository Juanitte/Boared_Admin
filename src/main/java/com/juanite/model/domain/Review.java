package com.juanite.model.domain;

import com.juanite.model.domain.interfaces.iReview;

import java.util.Objects;

public class Review implements iReview {

    private User user;
    private Game game;
    private String review;
    private double score;

    public Review() {
        this(null, null, "", -1);
    }

    public Review(User user, Game game) {
        this(user, game, "", -1);
    }

    public Review(User user, Game game, String review, double score) {
        this.user = user;
        this.game = game;
        this.review = review;
        this.score = score;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(user, review.user) && Objects.equals(game, review.game);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, game);
    }
}
