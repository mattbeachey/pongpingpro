package com.pingpong.persistence.services;

//import javax.persistence.*;

import com.pingpong.Application;
import com.pingpong.domain.Player;
import com.pingpong.services.PlayerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = {Application.class})
@SpringBootTest
@WebAppConfiguration
public class PlayerServiceTest {

    @Autowired
    private PlayerService playerService;

    @Test
    public void testSave(){
        Player player = new Player();
        player.setUsername("beecheese");
        player.setEloRating(1500);

        assertNull(player.getId());
        playerService.savePlayer(player);
        assertNotNull(player.getId());

        //fetch
        Player fetchedPlayer = playerService.findPlayerById(player.getId());
        assertNotNull(fetchedPlayer);
        assertEquals(player.getId(), fetchedPlayer.getId());

        //update
        fetchedPlayer.setEloRating(1600);
        playerService.savePlayer(fetchedPlayer);

        Player updatedPlayer = playerService.findPlayerById(fetchedPlayer.getId());
        assertEquals(java.util.Optional.of(1600), java.util.Optional.of(updatedPlayer.getEloRating()));

        generateSeedPlayers(20);
        System.out.println(player.getUsername() + ", ID: " + player.getId());
//        Player otherPlayer = playerService.findPlayerById(12);
//        System.out.println(otherPlayer.getUsername() + ", ID: " + otherPlayer.getId());
    }


    private void generateSeedPlayers(int numberOfPlayers){
        for (int i = 0; i < numberOfPlayers; i++){
            Player player = new Player("Player " + (i + 1));
            playerService.savePlayer(player);
        }
    }

}