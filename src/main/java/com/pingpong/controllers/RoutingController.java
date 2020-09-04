package com.pingpong.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class RoutingController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String testAvail(){
        return "<h2>sup</h2>";
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
