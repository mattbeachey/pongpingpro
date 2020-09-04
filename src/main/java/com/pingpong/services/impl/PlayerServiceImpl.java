package com.pingpong.services.impl;

import com.pingpong.domain.Player;
import com.pingpong.repositories.PlayerRepository;
import com.pingpong.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Override
    public Player findPlayerByEloRating(int elo) {
        return playerRepository.findPlayerByEloRating(elo);
    }

    @Override
    public Player findByEloRating(int elo) {
        return playerRepository.findByEloRating(elo);
    }

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Player findPlayerByName(String name) {
        return playerRepository.findPlayerByName(name);
    }

    @Override
    public Player findPlayerById(String id) {
        return playerRepository.findById(id).orElse(null);
    }

    @Override
    public Player findByUsername(String username) {
        return playerRepository.findByUsername(username);
    }

    @Override
    public List<Player> findAllPlayers() {
        List<Player> playerList = new ArrayList<>();
        playerRepository.findAll().forEach(playerList::add);
        return playerList;
    }

    @Override
    public List<Player> findPlayersByEloRating(Integer eloRating) {
        List<Player> playerList = new ArrayList<>();
        playerRepository.findPlayersByEloRating(eloRating).forEach(playerList::add);
        return playerList;
    }

    @Override
    public Player findPlayerByFirstName(String firstName) {
        return playerRepository.findPlayerByFirstName(firstName);
    }

    @Override
    public void deletePlayer(String id) {
        playerRepository.deleteById(id);
    }
}
