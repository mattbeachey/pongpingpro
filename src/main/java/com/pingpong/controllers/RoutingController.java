package com.pingpong.controllers;

import com.pingpong.domain.Player;
import com.pingpong.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class RoutingController {

    @Autowired
    private PlayerService playerService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Player>> testAvail(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(playerService.findAllPlayers());
    }

    @RequestMapping(value = "/restricted", method = RequestMethod.GET)
    public String testRestricted(){
        return "<h2>secret</h2>";
    }




//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String giveLogin(){
//        return  "login";
//    }

}
