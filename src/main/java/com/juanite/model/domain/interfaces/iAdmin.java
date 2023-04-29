package com.juanite.model.domain.interfaces;

import com.juanite.model.domain.Admin;

public interface iAdmin {

    Admin create(String email, String name, String password);
    Admin update(Admin admin, String email, String name, String password);
    Admin remove(Admin admin);

}
