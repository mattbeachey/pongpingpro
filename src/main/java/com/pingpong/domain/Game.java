package com.pingpong.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

//import javax.persistence.*;
import java.time.LocalDate;


//@Entity
@RedisHash("game")
public class Game {
    @Id
    private Integer Id;

    @Indexed
    private Player winner;
    @Indexed
    private Player loser;
    private Integer winnerScore;
    private Integer loserScore;
    private Integer winnerEloBefore;
    private Integer loserEloBefore;
    private Integer winnerEloAfter;
    private Integer loserEloAfter;
    @Indexed
    private Boolean upset;
    @Indexed
    private Integer upsetPercent;
    @Indexed
    private Integer eloDiff;
    @Indexed
    private LocalDate gameDate;


    //region CONSTRUCTORS
    public Game(){}
    public Game(Player winner, Player loser){
        setWinner(winner);
        setLoser(loser);
    }
    //endregion


    //region GETTERS/SETTERS

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Player getLoser() {
        return loser;
    }

    public void setLoser(Player loser) {
        this.loser = loser;
    }

    public Integer getWinnerScore() {
        return winnerScore;
    }

    public void setWinnerScore(Integer winnerScore) {
        this.winnerScore = winnerScore;
    }

    public Integer getLoserScore() {
        return loserScore;
    }

    public void setLoserScore(Integer loserScore) {
        this.loserScore = loserScore;
    }

    public LocalDate getGameDate() {
        return gameDate;
    }

    public void setGameDate(LocalDate gameDate) {
        this.gameDate = gameDate;
    }

    public Integer getWinnerEloBefore() {
        return winnerEloBefore;
    }

    public void setWinnerEloBefore(Integer winnerEloBefore) {
        this.winnerEloBefore = winnerEloBefore;
    }

    public Integer getLoserEloBefore() {
        return loserEloBefore;
    }

    public void setLoserEloBefore(Integer loserEloBefore) {
        this.loserEloBefore = loserEloBefore;
    }

    public Integer getWinnerEloAfter() {
        return winnerEloAfter;
    }

    public void setWinnerEloAfter(Integer winnerEloAfter) {
        this.winnerEloAfter = winnerEloAfter;
    }

    public Integer getLoserEloAfter() {
        return loserEloAfter;
    }

    public void setLoserEloAfter(Integer loserEloAfter) {
        this.loserEloAfter = loserEloAfter;
    }

    public Boolean getUpset() {
        return upset;
    }

    public void setUpset(Boolean upset) {
        this.upset = upset;
    }

    public int getUpsetPercent() {
        return upsetPercent;
    }

    public void setUpsetPercent(Integer upsetPercent) {
        this.upsetPercent = upsetPercent;
    }

    public Integer getEloDiff() {
        return eloDiff;
    }

    public void setEloDiff(Integer eloDiff) {
        this.eloDiff = eloDiff;
    }

    //endregion
}