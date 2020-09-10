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
import java.util.stream.IntStream;

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

        if(playerService.findAllPlayers().size() == 0){
            log.info("no players on file, generating test data");
            generateSeedPlayers(20);

        }

//        gameResult.newGame(playerService.findPlayerById(2), playerService.findPlayerById(1), 21, 16, LocalDate.now());

        IntStream.range(1, 5).forEach(i -> {
            gameResult.newGame(playerService.findByUsername("Player 1"), playerService.findByUsername("Player 2"), 21, 19, LocalDate.now());
        });

     }

    private void generateSeedPlayers(int numberOfPlayers){
        System.out.println("generating " + numberOfPlayers + " players");
        for (int i = 0; i < numberOfPlayers; i++){
            Player player = new Player("Player " + (i + 1));
            if (i % 4 == 0 && i != 0)
                player.setEloRating(110);
            if (i == 0) {
                player.setFirstName("dude");
                player.setEloRating(2000);
            }
            player.setName("Name " + i);
            log.info("Player number " + i + ": " + player.getFirstName());
            playerService.savePlayer(player);
        }
    }


//    private void gameTest(){
//        Player winner = playerService.findPlayerById(5);
//        Player loser = playerService.findPlayerById(6);
//        Game newGame = new Game();
//        newGame.setWinner(winner);
//        newGame.setLoser(loser);
//        gameService.saveGame(newGame);
//        log.info("Game winner from database: " + gameService.findGameById(1).getWinner().getUsername());
//
//    }

}
