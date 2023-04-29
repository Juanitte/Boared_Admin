package com.juanite.model.domain.interfaces;

import com.juanite.model.domain.Developer;
import com.juanite.model.domain.Game;
import com.juanite.model.domain.Tags;
import com.juanite.model.domain.User;

import java.util.Date;
import java.util.Set;

public interface iGame {

    Game create();
    Game update(String title, String description, Set<Tags> genres, Date releaseDate, double price, String logo, Developer developer);
    Game remove();
    boolean addTag(Tags tag);
    boolean removeTag(Tags tag);
    boolean addImage(String image);
    boolean removeImage(String image);
    boolean addPlayer(User user);
    boolean removePlayer(User user);

}
