package com.wealcome.corelogic.models;

import java.util.Arrays;
import java.util.Objects;

import static com.wealcome.corelogic.models.ScoreEnum.*;

public class TennisScore {

    private final String id;
    private int player1Hits;
    private int player2Hits;
    private ScoreEnum scorePlayer1;
    private ScoreEnum scorePlayer2;
    private final ScoreEnum[] orderedScore = {LOVE, FIFTEEN, THIRTY, FORTY, ADVANTAGE};

    public TennisScore(String id) {
        this.id = id;
        player1Hits = 0;
        player2Hits = 0;
        scorePlayer1 = LOVE;
        scorePlayer2 = LOVE;
    }

    public TennisScore(String id,
                       ScoreEnum scorePlayer1,
                       ScoreEnum scorePlayer2) {
        this.id = id;
        this.scorePlayer1 = scorePlayer1;
        this.scorePlayer2 = scorePlayer2;
        this.player1Hits = 0;
        this.player2Hits = 0;
    }

    public void computeNewScore(String playerName) {
        this.player1Hits = playerName.equals("Player1") ? this.player1Hits + 1 : this.player1Hits;
        this.player2Hits = playerName.equals("Player2") ? this.player2Hits + 1 : this.player2Hits;
        if (areScoresEqual(player1Hits, player2Hits))
            handleEqualScores(player1Hits);
        else if (isThereAWinner(player1Hits, player2Hits))
            determineWinner(player1Hits, player2Hits);
        else
            assignNewScores(orderedScore[player1Hits], orderedScore[player2Hits]);
    }

    public void giveUp(String playerName) {
        if (playerName.equals("Player1"))
            scorePlayer2 = WON;
        else
            scorePlayer1 = WON;
    }

    private boolean areScoresEqual(int player1Hits, int player2Hits) {
        return player1Hits == player2Hits;
    }

    private void handleEqualScores(int player1Hits) {
        if (player1Hits >= 3)
            assignNewScores(FORTY, FORTY);
        else
            assignNewScores(orderedScore[player1Hits], orderedScore[player1Hits]);
    }

    private boolean isThereAWinner(int player1Hits, int player2Hits) {
        return atLeastOnePlayerHasMarkedFourPoints(player1Hits, player2Hits)
                && differenceOfTwoPoints(player1Hits, player2Hits);
    }

    private void determineWinner(int player1Hits, int player2Hits) {
        if (player1Hits > player2Hits)
            assignNewScores(WON, orderedScore[player2Hits]);
        else
            assignNewScores(orderedScore[player1Hits], ScoreEnum.WON);
    }

    private void assignNewScores(ScoreEnum scorePlayer1, ScoreEnum scorePlayer2) {
        this.scorePlayer1 = scorePlayer1;
        this.scorePlayer2 = scorePlayer2;
    }

    private boolean differenceOfTwoPoints(int player1Hits, int player2Hits) {
        return Math.abs(player1Hits - player2Hits) >= 2;
    }

    private boolean atLeastOnePlayerHasMarkedFourPoints(int player1Hits, int player2Hits) {
        return Math.max(player1Hits, player2Hits) >= 4;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TennisScore that = (TennisScore) o;
        return Objects.equals(id, that.id) &&
                scorePlayer1 == that.scorePlayer1 &&
                scorePlayer2 == that.scorePlayer2 &&
                Arrays.equals(orderedScore, that.orderedScore);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, scorePlayer1, scorePlayer2);
        result = 31 * result + Arrays.hashCode(orderedScore);
        return result;
    }

    @Override
    public String toString() {
        return "TennisScore{" +
                "id='" + id + '\'' +
                ", player1Hits=" + player1Hits +
                ", player2Hits=" + player2Hits +
                ", scorePlayer1=" + scorePlayer1 +
                ", scorePlayer2=" + scorePlayer2 +
                ", orderedScore=" + Arrays.toString(orderedScore) +
                '}';
    }

    public String getScorePlayer1() {
        return scorePlayer1.name();
    }

    public String getScorePlayer2() {
        return scorePlayer2.name();
    }
}
