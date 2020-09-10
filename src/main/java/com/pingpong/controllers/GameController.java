package com.pingpong.controllers;

import com.pingpong.common.GameResult;
import com.pingpong.common.RatingAdjust;
import com.pingpong.domain.Game;
import com.pingpong.domain.Player;
import com.pingpong.services.GameService;
import com.pingpong.services.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/game")
public class GameController {

    private static final Logger log = LoggerFactory.getLogger(GameController.class);

//  Constructor Injection
    GameService gameService;
    PlayerService playerService;
    GameResult gameResult;
    public GameController (GameService gameService, PlayerService playerService, GameResult gameResult){
        this.gameService = gameService;
        this.playerService = playerService;
        this.gameResult = gameResult;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Game>>  getAllGames(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(gameService.findAllGames());
    }

    @GetMapping("{id}")
    public ResponseEntity<Game> getOneGame(@PathVariable int id){
        try {
            Game foundGame = gameService.findGameById(id);
            log.info("Game no. " + id + " requested: " + foundGame.getWinner().getUsername() + " vs. " + foundGame.getLoser().getUsername());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(foundGame);
        } catch (Exception ex){
            log.error(String.valueOf(ex));
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(null);
        }
    }

    @GetMapping("/new/{p1Id}/{p2Id}")
    public ResponseEntity<Integer> setUpGame(@PathVariable String p1Id,
                                             @PathVariable String p2Id){
        try {
            Player player1, player2;
            if (playerService.findPlayerById(p1Id).getEloRating() <= playerService.findPlayerById(p2Id).getEloRating()){
                player1 = playerService.findPlayerById(p1Id);
                player2 = playerService.findPlayerById(p2Id);
            } else {
                player1 = playerService.findPlayerById(p2Id);
                player2 = playerService.findPlayerById(p1Id);
            }
            int winOdds = gameResult.winOdds(player1.getEloRating(), player2.getEloRating());
            log.info(player1.getUsername() + " has a " + winOdds + " percent chance of beating " + player2.getUsername());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(winOdds);
        } catch (Exception ex){
            log.error(String.valueOf(ex));
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(null);
        }
    }

    @PostMapping("/new/{winId}/{loseId}/{winScore}/{loseScore}")
    public ResponseEntity<Game> saveFinalGame(  @PathVariable String winId,
                                                @PathVariable String loseId,
                                                @PathVariable int winScore,
                                                @PathVariable int loseScore){
        log.info("New game, " + playerService.findPlayerById(winId).getUsername() + " vs. " + playerService.findPlayerById(loseId).getUsername());
        try {
            gameResult.newGame(
                    playerService.findPlayerById(winId),
                    playerService.findPlayerById(loseId),
                    winScore,
                    loseScore,
                    LocalDate.now());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
        } catch (Exception ex){
            log.error(String.valueOf(ex));
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(null);

        }
    }
}
