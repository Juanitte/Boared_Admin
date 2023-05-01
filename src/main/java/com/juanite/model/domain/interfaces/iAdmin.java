package com.juanite.model.domain.interfaces;

import com.juanite.model.domain.Admin;

public interface iAdmin {

    Admin create(String email, String name, String password) throws Exception;
    Admin update(String email, String name, String password) throws Exception;
    Admin remove() throws Exception;
    Admin save() throws Exception;

}
