package com.pingpong.domain;

import org.springframework.data.redis.core.RedisHash;

//import javax.persistence.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.index.Indexed;


@RedisHash("player")
public class Player {
    @Id
    private String Id;

    @Indexed
    private String authToken;
    private String firstName;
    private String lastName;
    @Indexed
    private String username;
    private String name;
    @Indexed
    private Integer eloRating = 1200;
    private Integer wins = 0;
    private Integer losses = 0;
    private String bestWinPlayerId;
    private Integer bestWinElo = 0;
    private Integer currentStreak = 0;
    private Integer bestStreak = 0;
    private Boolean wonPreviousGame = false;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Integer getCurrentStreak() {
        return currentStreak;
    }

    public void setCurrentStreak(Integer currentStreak) {
        this.currentStreak = currentStreak;
    }

    public Integer getBestStreak() {
        return bestStreak;
    }

    public void setBestStreak(Integer bestStreak) {
        this.bestStreak = bestStreak;
    }

    public Integer getBestWinElo() {
        return bestWinElo;
    }

    public void setBestWinElo(Integer bestWinElo) {
        this.bestWinElo = bestWinElo;
    }

    public Boolean getWonPreviousGame() {
        return wonPreviousGame;
    }

    public void setWonPreviousGame(Boolean wonPreviousGame) {
        this.wonPreviousGame = wonPreviousGame;
    }

    public String getBestWinPlayerId() {
        return bestWinPlayerId;
    }

    public void setBestWinPlayerId(String bestWinPlayerId) {
        this.bestWinPlayerId = bestWinPlayerId;
    }

    //endregion
}