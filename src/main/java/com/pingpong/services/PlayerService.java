package com.pingpong.services;

import com.pingpong.domain.Player;

import java.util.List;

public interface PlayerService {

    Player savePlayer(Player player);

    Player findPlayerById(String id);

    Player findByUsername(String username);

    Player findPlayerByName (String name);

    List<Player> findAllPlayers();

    List<Player> findPlayersByEloRating(Integer eloRating);

    Player findPlayerByEloRating (int elo);

    Player findByEloRating (int elo);

    Player findPlayerByFirstName (String firstName);

    void deletePlayer(String id);

}
