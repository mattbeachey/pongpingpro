package com.pingpong.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GameId")
    private Integer Id;

    @Version
    private Integer version;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Player winner;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Player loser;

    private Integer winnerScore;
    private Integer loserScore;
    private Integer winnerEloBefore;
    private Integer loserEloBefore;
    private Integer winnerEloAfter;
    private Integer loserEloAfter;
    private Boolean upset;
    private Integer upsetPercent;
    private Integer eloDiff;

    private LocalDate gameDate;


    public Game(){}
    public Game(Player winner, Player loser){
        setWinner(winner);
        setLoser(loser);
    }


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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
}