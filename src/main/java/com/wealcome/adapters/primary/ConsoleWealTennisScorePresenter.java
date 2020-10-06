package com.wealcome.adapters.primary;

import com.wealcome.corelogic.usecases.TennisScorePresenter;

public class ConsoleWealTennisScorePresenter implements TennisScorePresenter {

    private String scorePlayer1;
    private String scorePlayer2;

    public void presentTennisScore(String scorePlayer1, String scorePlayer2) {
        this.scorePlayer1 = scorePlayer1;
        this.scorePlayer2 = scorePlayer2;
    }

    public String score() {
        if (scorePlayer1.equals(scorePlayer2)) {
            if(scorePlayer1.equals("FORTY"))
                return "DEUCE";
            return scorePlayer1.toUpperCase() + " - ALL";
        }
        return scorePlayer1.toUpperCase() + " - " + scorePlayer2.toUpperCase();
    }
}
