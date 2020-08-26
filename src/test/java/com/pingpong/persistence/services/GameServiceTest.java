package com.pingpong.persistence.services;

import javax.persistence.*;

import com.pingpong.Application;
import com.pingpong.domain.Game;
import com.pingpong.domain.Player;
import com.pingpong.services.GameService;
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

    @Test
    public void testSave() {
        Game game = new Game();
        game.setWinnerEloBefore(1600);
        game.setLoserEloBefore(1500);
        game.setWinner(new Player("beecheese"));
        game.setLoser(new Player("doofus"));

        assertNull(game.getId());
        gameService.saveGame(game);
        assertNotNull(game.getId());

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