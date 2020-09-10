package com.pingpong.services.impl;

import com.pingpong.domain.Game;
import com.pingpong.repositories.GameRepository;
import com.pingpong.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {


    GameRepository gameRepository;
    public GameServiceImpl (GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }


    @Override
    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Game findGameById(Integer id) {
        return gameRepository.findById(id).orElse(null);
    }

    @Override
    public List<Game> findAllGames() {
        List<Game> gameList = new ArrayList<>();
        gameRepository.findAll().forEach(gameList::add);
        return gameList;
    }

    @Override
    public void deleteGame(Integer id) {
        gameRepository.deleteById(id);
    }
}
