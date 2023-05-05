package com.juanite.model.domain;

import com.juanite.model.DAO.AdminDAO;
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

    /**
     * Method that creates an Admin and stores it at the database.
     * @param email , the provided email.
     * @param name , the provided name.
     * @param password , the provided password.
     * @return the created Admin.
     */
    @Override
    public Admin create(String email, String name, String password) throws Exception {
        Admin admin = new Admin(email, name, password);
            try (AdminDAO adao = new AdminDAO()) {
                if(!admin.getEmail().equals("") && !admin.getName().equals("") && !admin.getPassword().equals("")) {
                    adao.save(admin);
                    return admin;
                }
            }

            return null;
    }

    /**
     * Method that updates an Admin and stores it at the database.
     * @param email , the provided email.
     * @param name , the provided name.
     * @param password , the provided password.
     * @return the updated Admin.
     */
    @Override
    public Admin update(String email, String name, String password) throws Exception {
        try (AdminDAO adao = new AdminDAO()) {
            if (this != null) {
                if(!email.equals("") || !name.equals("") || !password.equals("")) {
                    if (!email.equals("")) {
                        this.email = email;
                    }
                    if (!name.equals("")) {
                        this.name = name;
                    }
                    if (!password.equals("")) {
                        this.password = password;
                    }
                    adao.save(this);
                    return this;
                }
            }
        }

        return null;
    }

    /**
     * Method that removes an Admin from the database.
     * @return the removed Admin.
     */
    @Override
    public Admin remove() throws Exception {
        try (AdminDAO adao = new AdminDAO()) {
            if(this.equals(adao.find(adao.getId(this)))) {
                adao.delete(this);
                return this;
            }
        }

        return null;
    }

    /**
     * Method that saves an Admin in the database.
     * @return the saved Admin.
     */
    @Override
    public Admin save() throws Exception {
        try (AdminDAO adao = new AdminDAO()) {
            if(adao.save(this) != null) {
                return this;
            }
        }

        return null;
    }
}