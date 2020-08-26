package com.pingpong.persistence.repositories;

import javax.persistence.*;

import com.pingpong.configuration.RepositoryConfiguration;
import com.pingpong.domain.Player;
import com.pingpong.repositories.PlayerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void testSave(){
        Player player = new Player();
        player.setUsername("beecheese");
        player.setEloRating(1500);

        assertNull(player.getId());
        playerRepository.save(player);
        assertNotNull(player.getId());

        //fetch
        Player fetchedPlayer = playerRepository.findOne(player.getId());
        assertNotNull(fetchedPlayer);
        assertEquals(player.getId(), fetchedPlayer.getId());

        //update
        fetchedPlayer.setEloRating(1600);
        playerRepository.save(fetchedPlayer);

        Player updatedPlayer = playerRepository.findOne(fetchedPlayer.getId());
        assertEquals(java.util.Optional.of(1600), java.util.Optional.of(updatedPlayer.getEloRating()));
    }

}