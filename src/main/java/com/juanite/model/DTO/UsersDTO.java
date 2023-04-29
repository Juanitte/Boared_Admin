package com.juanite.model.DTO;

import java.util.HashSet;
import java.util.Set;

public class UsersDTO {

    private Set<UserDTO> users;

    public UsersDTO(){
        this(new HashSet<UserDTO>());
    }
    public UsersDTO(Set<UserDTO> users) {
        this.users = users;
    }

    public Set<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDTO> users) {
        this.users = users;
    }
}
