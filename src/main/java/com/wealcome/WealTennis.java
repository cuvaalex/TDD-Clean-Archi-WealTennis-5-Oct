package com.wealcome;

public class WealTennis {

    private int player1 = 0;

    public String score() {
        if (player1 == 0)
            return "Love All";
        if (player1 == 2)
            return "Thirty Love";
        return "Fifteen Love";
    }

    public void scorePlayer1() {
        player1 += 1;
    }
}
