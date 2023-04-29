package com.juanite.model.domain.interfaces;

import com.juanite.model.domain.Countries;
import com.juanite.model.domain.Developer;
import com.juanite.model.domain.Game;

import java.util.Date;

public interface iDeveloper {

    Developer create();
    Developer update(String name, String description, Date birthDate, Countries country, String logo);
    Developer remove();
    boolean addGame(Game game);
    boolean removeGame(Game game);
}
