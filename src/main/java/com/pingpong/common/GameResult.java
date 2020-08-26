package com.pingpong.common;

import com.pingpong.bootstrap.SeedData;
import com.pingpong.domain.Game;
import com.pingpong.domain.Player;
import com.pingpong.services.GameService;
import com.pingpong.services.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class GameResult {

    private static final Logger log = LoggerFactory.getLogger(GameResult.class);

    @Autowired
    PlayerService playerService;
    @Autowired
    GameService gameService;

    public void newGame (Player winner, Player loser, int winnerScore, int loserScore, LocalDate gameDate){
        int eloDiff = Math.abs(winner.getEloRating() - loser.getEloRating());

        //determining the expected outcome for each player (1 being sure win, 0 being sure loss, etc)
        double winnerExpectedOutcome = RatingAdjust.expectedOutcome(winner.getEloRating(), loser.getEloRating());
        double loserExpectedOutcome = RatingAdjust.expectedOutcome(loser.getEloRating(), winner.getEloRating());

        //create new game
        Game newGame = new Game();
        newGame.setGameDate(gameDate);
        newGame.setWinner(winner);
        newGame.setLoser(loser);
        newGame.setWinnerScore(winnerScore);
        newGame.setLoserScore(loserScore);
        newGame.setWinnerEloBefore(winner.getEloRating());
        newGame.setLoserEloBefore(loser.getEloRating());
        newGame.setUpset(winner.getEloRating() < loser.getEloRating());
        newGame.setUpsetPercent((int)(winnerExpectedOutcome * 100));
        newGame.setEloDiff(eloDiff);

        //adjust respective ELO ratings
        winner.setEloRating(RatingAdjust.newRating(winner.getEloRating(), winnerExpectedOutcome, 1));
        playerService.savePlayer(winner);

        loser.setEloRating(RatingAdjust.newRating(loser.getEloRating(), loserExpectedOutcome, 0));
        playerService.savePlayer(loser);

        //add adjusted scores to game and save it
        newGame.setWinnerEloAfter(winner.getEloRating());
        newGame.setLoserEloAfter(loser.getEloRating());
        gameService.saveGame(newGame);
        log.info(newGame.getWinner().getUsername());
    }

//    public void gameTest(Game gameToSave){
//        gameService.saveGame(gameToSave);
//
//        List<Game> gameList = gameService.findAllGames();
//
//        for (Game game : gameList){
//            log.info("Game winner: " + game.getWinner().getUsername());
//        }
//    }

}
