package com.juanite.model.domain;

import com.juanite.model.domain.interfaces.iRepo;

import java.util.HashSet;
import java.util.Set;

public class RepoDeveloper implements iRepo {

    private Set<Developer> developers;
    private static RepoDeveloper _newInstance;

    private RepoDeveloper(){
        this.developers = new HashSet<Developer>();
    }
    public RepoDeveloper getInstance(){
        if(_newInstance==null) {
            _newInstance = new RepoDeveloper();
        }
        return _newInstance;
    }

    public Set<Developer> getUsers() {
        return developers;
    }

    public void setUsers(Set<Developer> developers) {
        this.developers = developers;
    }

    @Override
    public boolean add(Object entity) {
        return this.developers.add((Developer) entity);
    }

    @Override
    public boolean contains(Object entity) {
        return this.developers.contains((Developer) entity);
    }

    @Override
    public boolean remove(Object entity) {
        return this.developers.remove((Developer) entity);
    }
}
