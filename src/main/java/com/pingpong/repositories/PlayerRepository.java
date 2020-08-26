package com.pingpong.repositories;

import com.pingpong.domain.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Integer> {

    Player findPlayerByUsername (String username);

}
