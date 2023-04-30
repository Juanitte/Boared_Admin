package com.juanite.model.domain;

import com.juanite.model.domain.interfaces.iRepo;

import java.util.HashSet;
import java.util.Set;

public class RepoUser implements iRepo {

    private Set<User> users;
    private static RepoUser _newInstance;

    private RepoUser(){
        this.users = new HashSet<User>();
    }
    public RepoUser getInstance(){
        if(_newInstance==null) {
            _newInstance = new RepoUser();
        }
        return _newInstance;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean add(Object entity) {
        return this.users.add((User) entity);
    }

    @Override
    public boolean contains(Object entity) {
        return this.users.contains((User) entity);
    }

    @Override
    public boolean remove(Object entity) {
        return this.users.remove((User) entity);
    }
}
