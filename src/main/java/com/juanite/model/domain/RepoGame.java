package com.juanite.model.domain;

import com.juanite.model.domain.interfaces.iRepo;

import java.util.HashSet;
import java.util.Set;

public class RepoGame implements iRepo {

    private Set<Game> games;
    private static RepoGame _newInstance;

    private RepoGame(){
        this.games = new HashSet<Game>();
    }
    public RepoGame getInstance(){
        if(_newInstance==null) {
            _newInstance = new RepoGame();
        }
        return _newInstance;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setUsers(Set<Game> games) {
        this.games = games;
    }

    @Override
    public boolean add(Object entity) {
        return this.games.add((Game) entity);
    }

    @Override
    public boolean contains(Object entity) {
        return this.games.contains((Game) entity);
    }

    @Override
    public boolean remove(Object entity) {
        return this.games.remove((Game) entity);
    }
}
