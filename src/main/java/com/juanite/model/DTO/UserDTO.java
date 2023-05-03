package com.juanite.model.DTO;

import com.juanite.model.domain.Countries;

public class UserDTO {

    private String username;
    private Countries country;
    private String avatar;
    private boolean banned;

    public UserDTO(){
        this("", Countries.NONE, "", false);
    }
    public UserDTO(String username, Countries country, String avatar, boolean banned) {
        this.username = username;
        this.country = country;
        this.avatar = avatar;
        this.banned = banned;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Countries getCountry() {
        return country;
    }

    public void setCountry(Countries country) {
        this.country = country;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }
}
