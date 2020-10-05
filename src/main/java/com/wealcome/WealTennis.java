package com.wealcome;

import java.util.HashMap;
import java.util.Map;

public class WealTennis {

    private static final String DEUCE = "Deuce";
    private static final String ALL = " All";
    private static final String WON = "Won!";
    public static final String SEPARATOR = " ";
    private final Map<String, Integer> hitsByPlayers = new HashMap<>(Map.of("Player1", 0, "Player2", 0));
    private final String[] orderedScore = {"Love", "Fifteen", "Thirty", "Forty", "Advantage"};

    public String score() {
        int player1Hits = hitsByPlayers.get("Player1");
        int player2Hits = hitsByPlayers.get("Player2");
        return player1Hits == player2Hits ?
                (player1Hits >= 3 ? DEUCE : orderedScore[player1Hits] + ALL) :
                atLeastOnePlayerHasMarkedFourPoints(player1Hits, player2Hits)
                        && differenceOfTwoPoints(player1Hits, player2Hits) ? WON :
                        orderedScore[player1Hits] + SEPARATOR + orderedScore[player2Hits];
    }

    public void hitWinningPoints(String playerName) {
        hitsByPlayers.merge(playerName, 1, Integer::sum);
    }

    private boolean differenceOfTwoPoints(int player1Hits, int player2Hits) {
        return Math.abs(player1Hits - player2Hits) >= 2;
    }

    private boolean atLeastOnePlayerHasMarkedFourPoints(int player1Hits, int player2Hits) {
        return Math.max(player1Hits, player2Hits) >= 4;
    }
}
