package com.juanite.model.DTO;

import java.util.HashSet;
import java.util.Set;

public class RepoUsersDTO {

    private Set<UserDTO> users;

    public RepoUsersDTO(){
        this(new HashSet<UserDTO>());
    }
    public RepoUsersDTO(Set<UserDTO> users) {
        this.users = users;
    }

    public Set<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDTO> users) {
        this.users = users;
    }
}
