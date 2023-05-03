package com.juanite.model.domain.interfaces;

import com.juanite.model.domain.Countries;
import com.juanite.model.domain.Developer;
import com.juanite.model.domain.Game;

import java.sql.SQLException;
import java.util.Date;

public interface iDeveloper {

    Developer create() throws Exception;
    Developer update(Developer developer) throws Exception;
    void remove() throws Exception;
    boolean addGame(Game game);
    boolean removeGame(Game game);
}
