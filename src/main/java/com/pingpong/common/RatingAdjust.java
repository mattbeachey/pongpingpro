package com.pingpong.common;

public class RatingAdjust {
    //helper methods for expected outcome and ELO change


    static double expectedOutcome (double aRating, double bRating){
        //using formula described here: https://en.wikipedia.org/wiki/Elo_rating_system#Theory
        return (1 / (1 + Math.pow(10, ((bRating - aRating)/400))));
    }

    static int newRating(int currentRating, double expectedOutcome, double outcome){
        //new rating concept explained here: https://youtu.be/AsYfbmp0To0

        //checking to see if ELO should round up before returning as int
        if ((currentRating + (32 * (outcome - expectedOutcome))) - Math.floor((currentRating + (32 * (outcome - expectedOutcome)))) >= 0.5){
            return (int)Math.ceil(currentRating + (32 * (outcome - expectedOutcome)));
        }

        //otherwise cast as int to round down
        return (int)(currentRating + (32 * (outcome - expectedOutcome)));
    }

}
