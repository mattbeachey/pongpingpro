package com.pingpong.controllers;

import com.pingpong.domain.Player;
import com.pingpong.services.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/player")
public class PlayerController {

    private static final Logger log = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    private PlayerService playerService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Player>> getAllPlayers(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(playerService.findAllPlayers());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
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
