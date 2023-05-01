package com.juanite.model.domain;

import com.juanite.model.DAO.AdminDAO;
import com.juanite.model.domain.interfaces.iRepo;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class RepoAdmin implements iRepo {

    private Set<Admin> admins;
    private static RepoAdmin _newInstance;

    private RepoAdmin() {
        this.admins = new HashSet<Admin>();
    }
    public static RepoAdmin getInstance() {
        if(_newInstance==null) {
            _newInstance = new RepoAdmin();
        }
        return _newInstance;
    }

    public Set<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(Set<Admin> admins) {
        this.admins = admins;
    }

    @Override
    public boolean add(Object entity) {
        return this.admins.add((Admin) entity);
    }

    @Override
    public boolean contains(Object entity) {
        return this.admins.contains((Admin) entity);
    }

    @Override
    public boolean remove(Object entity) {
        return this.admins.remove((Admin) entity);
    }
}
