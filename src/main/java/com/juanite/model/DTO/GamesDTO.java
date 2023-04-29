package com.juanite.model.DTO;

import java.util.HashSet;
import java.util.Set;

public class GamesDTO {

    private Set<GameDTO> games;

    public GamesDTO(){
        this(new HashSet<GameDTO>());
    }
    public GamesDTO(Set<GameDTO> games) {
        this.games = games;
    }

    public Set<GameDTO> getGames() {
        return games;
    }

    public void setGames(Set<GameDTO> games) {
        this.games = games;
    }
}
