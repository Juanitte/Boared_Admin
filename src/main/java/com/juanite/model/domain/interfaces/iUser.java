package com.juanite.model.domain.interfaces;

import com.juanite.model.domain.Countries;
import com.juanite.model.domain.Game;
import com.juanite.model.domain.User;

import java.util.Date;

public interface iUser {

    User create();
    User update(String username, String password, String name, String surname, String email, Date birthDate, Countries country, String town, String address, String phoneNumber, String avatar);
    User remove();
    boolean addGame(Game game);
    boolean removeGame(Game game);
    boolean addFriend(User user);
    boolean removeFriend(User user);
    boolean addFriendRequest(User user, boolean isSender);
    boolean removeFriendRequest(User user);

}
