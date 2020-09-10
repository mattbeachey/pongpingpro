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

        //adjust winner's best win
        //if winner beat a player with a higher ELO of their previous best win, set this game's opponent as new best win
        if (winner.getBestWinPlayerId() != null && loser.getEloRating() > winner.getBestWinElo()){
            winner.setBestWinPlayerId(loser.getId());
            winner.setBestWinElo(loser.getEloRating());
        }
        if (winner.getBestWinPlayerId() == null){
            winner.setBestWinPlayerId(loser.getId());
            winner.setBestWinElo(loser.getEloRating());
        }

        //adjust respective ELO ratings and wins/losses
        winner.setEloRating(RatingAdjust.newRating(winner.getEloRating(), winnerExpectedOutcome, 1));
        int adjustedWins = winner.getWins() == null ? 1 : winner.getWins() + 1;
        winner.setWins(adjustedWins);
        loser.setEloRating(RatingAdjust.newRating(loser.getEloRating(), loserExpectedOutcome, 0));
        int adjustedLosses = loser.getLosses() == null ? 1 : loser.getLosses() + 1;
        loser.setLosses(adjustedLosses);

        //adjust win streaks
        if (winner.getWonPreviousGame()){
            winner.setCurrentStreak(winner.getCurrentStreak() + 1);
        } else {
            winner.setCurrentStreak(1);
        }
        winner.setBestStreak(winner.getCurrentStreak() > winner.getBestStreak() ? winner.getCurrentStreak() : winner.getBestStreak());
        winner.setWonPreviousGame(true);
        loser.setWonPreviousGame(false);

        //save player adjustments
        playerService.savePlayer(winner);
        playerService.savePlayer(loser);

        //add adjusted scores to game and save it
        newGame.setWinnerEloAfter(winner.getEloRating());
        newGame.setLoserEloAfter(loser.getEloRating());
        gameService.saveGame(newGame);
    }

    public int winOdds (int eloLow, int eloHigh){
        return (int)(RatingAdjust.expectedOutcome(eloLow, eloHigh) * 100);
    }
}







//        log.info(winner.getUsername()
//                + " (ELO of "
//                + winner.getEloRating()
//                + ") beats "
//                + loser.getUsername()
//                + " (ELO of " + loser.getEloRating()
//                + ")");
//        log.info(winner.getUsername()
//                + " now has an ELO of "
//                + winner.getEloRating()
//                + " and "
//                + loser.getUsername()
//                + " now has an ELO of " + loser.getEloRating());