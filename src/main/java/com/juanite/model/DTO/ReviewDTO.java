package com.juanite.model.DTO;

public class ReviewDTO {

    private String username;
    private String game;
    private String review;
    private double score;

    public ReviewDTO() {
        this("", "", "", -1);
    }
    public ReviewDTO(String username, String game, String review, double score) {
        this.username = username;
        this.game = game;
        this.review = review;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
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
}
