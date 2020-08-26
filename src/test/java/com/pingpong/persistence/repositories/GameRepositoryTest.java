package com.pingpong.persistence.repositories;

import javax.persistence.*;

import com.pingpong.configuration.RepositoryConfiguration;
import com.pingpong.domain.Game;
import com.pingpong.domain.Player;
import com.pingpong.repositories.GameRepository;
import com.pingpong.repositories.PlayerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class GameRepositoryTest {

    @Autowired
    private GameRepository gameRepository;

    @Test
    public void testSave(){
        Game game = new Game();
        game.setWinnerEloBefore(1600);
        game.setLoserEloBefore(1500);

        assertNull(game.getId());
        gameRepository.save(game);
        assertNotNull(game.getId());

        //fetch
        Game fetchedGame = gameRepository.findOne(game.getId());
        assertNotNull(fetchedGame);
        assertEquals(game.getId(), fetchedGame.getId());

        //update
        fetchedGame.setLoserEloBefore(1800);
        gameRepository.save(fetchedGame);

        Game updatedGame = gameRepository.findOne(fetchedGame.getId());
        assertEquals(java.util.Optional.of(1800), java.util.Optional.of(updatedGame.getLoserEloBefore()));

    }


}