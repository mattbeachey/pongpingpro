package com.pingpong.bootstrap;

import com.pingpong.common.GameResult;
import com.pingpong.domain.Game;
import com.pingpong.domain.Player;
import com.pingpong.repositories.GameRepository;
import com.pingpong.repositories.PlayerRepository;
import com.pingpong.services.GameService;
import com.pingpong.services.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class SeedData implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger log = LoggerFactory.getLogger(SeedData.class);

    @Autowired
    private GameService gameService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameResult gameResult;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

//        log.info("message from SeedData class");
//        generateSeedPlayers(20);

        //generate only if database is empty
        if(playerService.findAllPlayers().size() == 0){
            log.info("no players on file, generating test data");
            generateSeedPlayers(20);
        }
        log.info(playerService.findPlayerById(2).getUsername()
                + " (ELO of "
                + playerService.findPlayerById(2).getEloRating()
                + ") beats "
                + playerService.findPlayerById(1).getUsername()
                + " (ELO of " + playerService.findPlayerById(1).getEloRating()
                + ")");

        gameResult.newGame(playerService.findPlayerById(2), playerService.findPlayerById(1), 21, 16, LocalDate.now());

        log.info(playerService.findPlayerById(2).getUsername()
                + " now has an ELO of "
                + playerService.findPlayerById(2).getEloRating()
                + " and "
                + playerService.findPlayerById(1).getUsername()
                + " now has an ELO of " + playerService.findPlayerById(1).getEloRating());

     }

    private void generateSeedPlayers(int numberOfPlayers){
        System.out.println("generating " + numberOfPlayers + " players");
        for (int i = 0; i < numberOfPlayers; i++){
            Player player = new Player("Player " + (i + 1));
            if (i % 4 == 0)
                player.setEloRating((int)(player.getEloRating() * 1.1));
            playerService.savePlayer(player);
        }
    }


    private void gameTest(){
        Player winner = playerService.findPlayerById(5);
        Player loser = playerService.findPlayerById(6);
        Game newGame = new Game();
        newGame.setWinner(winner);
        newGame.setLoser(loser);
        gameService.saveGame(newGame);
        log.info("Game winner from database: " + gameService.findGameById(1).getWinner().getUsername());

    }

}
