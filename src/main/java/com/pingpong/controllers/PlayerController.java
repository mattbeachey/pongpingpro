package com.pingpong.controllers;

import com.pingpong.domain.Player;
import com.pingpong.services.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/player")
public class PlayerController {

    private static final Logger log = LoggerFactory.getLogger(PlayerController.class);

    //Constructor Injection
    PlayerService playerService;
    public PlayerController (PlayerService playerService){
        this.playerService = playerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Player>> getAllPlayers(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(playerService.findAllPlayers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable String id){
        try {
            Player foundPlayer = playerService.findPlayerById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(foundPlayer);
        } catch (Exception ex){
            log.error(String.valueOf(ex));
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(null);
        }
    }
}
