package com.juanite.model.domain;

import com.juanite.model.domain.interfaces.iRepo;

import java.util.HashSet;
import java.util.Set;

public class RepoReview implements iRepo {

    private Set<Review> reviews;
    private static RepoReview _newInstance;

    private RepoReview(){
        this.reviews = new HashSet<Review>();
    }
    public RepoReview getInstance(){
        if(_newInstance==null) {
            _newInstance = new RepoReview();
        }
        return _newInstance;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public boolean add(Object entity) {
        return this.reviews.add((Review) entity);
    }

    @Override
    public boolean contains(Object entity) {
        return this.reviews.contains((Review) entity);
    }

    @Override
    public boolean remove(Object entity) {
        return this.reviews.remove((Review) entity);
    }
}
