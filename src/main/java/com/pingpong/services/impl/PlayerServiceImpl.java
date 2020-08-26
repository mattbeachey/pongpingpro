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

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Player findPlayerById(Integer id) {
        return playerRepository.findOne(id);
    }

    @Override
    public Player findPlayerByUsername(String username) {
        return playerRepository.findPlayerByUsername(username);
    }

    @Override
    public List<Player> findAllPlayers() {
        List<Player> playerList = new ArrayList<>();
        playerRepository.findAll().forEach(playerList::add);
        return playerList;
    }

    @Override
    public void deletePlayer(Integer id) {
        playerRepository.delete(id);
    }
}
