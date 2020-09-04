package com.pingpong.domain;

import org.springframework.data.redis.core.RedisHash;

//import javax.persistence.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.index.Indexed;


@RedisHash("player")
public class Player {
    @Id
    private String Id;

    String firstName;
    String lastName;
    @Indexed
    String username;
    String name;
    @Indexed
    Integer eloRating = 1200;
    Integer wins = 0;
    Integer losses = 0;
    Player bestWin;

    //region CONSTRUCTORS
    public Player(){}

    public Player (String username){
        setUsername(username);
    }
    //endregion

    //region GETTERS/SETTERS
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getEloRating() {
        return eloRating;
    }

    public void setEloRating(Integer eloRating) {
        this.eloRating = eloRating;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    public Player getBestWin() {
        return bestWin;
    }

    public void setBestWin(Player bestWin) {
        this.bestWin = bestWin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion
}