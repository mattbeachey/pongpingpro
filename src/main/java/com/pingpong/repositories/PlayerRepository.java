package com.pingpong.repositories;

import com.pingpong.domain.Player;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlayerRepository extends CrudRepository<Player, String> {

    Player findByUsername (String username);

    Player findPlayerByFirstName (String firstName);

    Player findPlayerByName (String name);

    Player findPlayerByEloRating (int elo);

    Player findByEloRating (int elo);

    List<Player> findPlayersByEloRating (Integer eloRating);

}
