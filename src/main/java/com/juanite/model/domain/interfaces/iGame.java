package com.juanite.model.domain.interfaces;

import com.juanite.model.domain.Developer;
import com.juanite.model.domain.Game;
import com.juanite.model.domain.Tags;
import com.juanite.model.domain.User;

import java.sql.Date;
import java.util.Set;

public interface iGame {

    Game create() throws Exception;
    Game update(Game game) throws Exception;
    void remove() throws Exception;
    boolean addTag(Tags tag);
    boolean removeTag(Tags tag);
    boolean addImage(String image);
    boolean removeImage(String image);
    boolean addPlayer(User user);
    boolean removePlayer(User user);

}
