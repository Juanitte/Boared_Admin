package com.juanite.model.domain;

import com.juanite.model.domain.interfaces.iAdmin;

import java.util.Objects;

public class Admin implements iAdmin {

    private String email;
    private String name;
    private String password;

    public Admin(){
        this("", "", "");
    }

    public Admin(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(email, admin.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }


    @Override
    public Admin create(String email, String name, String password) {
        Admin admin = new Admin(email, name, password);


        return admin;
    }

    @Override
    public Admin update(Admin admin, String email, String name, String password) {
        if(admin != null) {
            admin.setEmail(email);
            admin.setName(name);
            admin.setPassword(password);

        }

        return admin;
    }

    @Override
    public Admin remove(Admin admin) {
        return admin;
    }
}