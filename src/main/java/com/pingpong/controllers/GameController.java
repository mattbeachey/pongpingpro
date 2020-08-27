package com.pingpong.controllers;

import com.pingpong.common.GameResult;
import com.pingpong.domain.Game;
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

////  Constructor Injection issues???
//    GameService gameService;
//    PlayerService playerService;
//
//    public GameController (GameService gameService, PlayerService playerService){
//        this.gameService = gameService;
//        this.playerService = playerService;
//    }
    @Autowired
    private GameService gameService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private GameResult gameResult;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Game> getAllGames(){
        return gameService.findAllGames();
    }

    @RequestMapping(value = "{id}", method  =RequestMethod.GET)
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

    @RequestMapping(value = "/new/{winId}/{loseId}/{winScore}/{loseScore}", method = RequestMethod.POST)
    public ResponseEntity<Game> addNewGame(@PathVariable int winId,
                                           @PathVariable int loseId,
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
