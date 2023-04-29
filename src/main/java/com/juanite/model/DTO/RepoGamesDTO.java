package com.juanite.model.DTO;

import java.util.HashSet;
import java.util.Set;

public class RepoGamesDTO {

    private Set<GameDTO> games;

    public RepoGamesDTO(){
        this(new HashSet<GameDTO>());
    }
    public RepoGamesDTO(Set<GameDTO> games) {
        this.games = games;
    }

    public Set<GameDTO> getGames() {
        return games;
    }

    public void setGames(Set<GameDTO> games) {
        this.games = games;
    }
}
