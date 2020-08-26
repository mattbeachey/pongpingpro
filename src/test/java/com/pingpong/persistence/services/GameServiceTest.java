package com.pingpong.persistence.services;

import javax.persistence.*;

import com.pingpong.Application;
import com.pingpong.domain.Game;
import com.pingpong.domain.Player;
import com.pingpong.services.GameService;
import com.pingpong.services.PlayerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.access.method.P;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
public class GameServiceTest {

    @Autowired
    private GameService gameService;
    @Autowired
    private PlayerService playerService;

    @Test
    public void testSave() {
        Game game = new Game();
        game.setWinnerEloBefore(1600);
        game.setLoserEloBefore(1500);
        Player winner = new Player("asdf");
        Player loser = new Player("jkl;");

        playerService.savePlayer(winner);
        playerService.savePlayer(loser);

        System.out.println("Player ID: " + playerService.findPlayerByUsername("asdf").getId());

        game.setWinner(playerService.findPlayerByUsername("asdf"));
        game.setLoser(playerService.findPlayerByUsername("jkl;"));

        assertNull(game.getId());
        gameService.saveGame(game);
        assertNotNull(game.getId());
        System.out.println("Saved game winner: " + gameService.findGameById(1).getWinner().getUsername());

        //fetch
        Game fetchedGame = gameService.findGameById(game.getId());
        assertNotNull(fetchedGame);
        assertEquals(game.getId(), fetchedGame.getId());

        //update
        fetchedGame.setLoserEloBefore(1800);
        gameService.saveGame(fetchedGame);

        Game updatedGame = gameService.findGameById(fetchedGame.getId());
        assertEquals(java.util.Optional.of(1800), java.util.Optional.of(updatedGame.getLoserEloBefore()));

    }

}