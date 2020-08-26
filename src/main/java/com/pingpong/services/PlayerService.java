package com.pingpong.services;

import com.pingpong.domain.Player;

import java.util.List;

public interface PlayerService {

    Player savePlayer(Player player);

    Player findPlayerById(Integer id);

    Player findPlayerByUsername(String username);

    List<Player> findAllPlayers();

    void deletePlayer(Integer id);

}
