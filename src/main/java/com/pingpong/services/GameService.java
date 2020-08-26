package com.pingpong.services;

import com.pingpong.domain.Game;

import java.util.List;

public interface GameService {

    Game saveGame(Game game);

    Game findGameById(Integer id);

    List<Game> findAllGames();

    void deleteGame(Integer id);

}
