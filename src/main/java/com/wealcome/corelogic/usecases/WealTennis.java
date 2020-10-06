package com.wealcome.corelogic.usecases;
import com.wealcome.corelogic.models.TennisScore;

import java.util.HashMap;
import java.util.Map;

public class WealTennis {

    private final Map<String, Integer> hitsByPlayers = new HashMap<>(Map.of("Player1", 0, "Player2", 0));
    private final TennisScore score;

    public WealTennis(TennisScore score) {
        this.score = score;
    }

    public void hitWinningPoints(String playerName) {
        hitsByPlayers.merge(playerName, 1, Integer::sum);
        score.computeNewScore(hitsByPlayers.get("Player1"), hitsByPlayers.get("Player2"));
    }
}
