package com.wealcome.corelogic.models;

import java.util.Arrays;
import java.util.Objects;

import static com.wealcome.corelogic.models.ScoreEnum.*;

public class TennisScore {

    private ScoreEnum scorePlayer1;
    private ScoreEnum scorePlayer2;
    private final ScoreEnum[] orderedScore = {LOVE, FIFTEEN, THIRTY, FORTY, ADVANTAGE};

    public TennisScore(ScoreEnum scorePlayer1, ScoreEnum scorePlayer2) {
        this.scorePlayer1 = scorePlayer1;
        this.scorePlayer2 = scorePlayer2;
    }

    public void computeNewScore(int player1Hits, int player2Hits) {
        if (areScoresEqual(player1Hits, player2Hits))
            handleEqualScores(player1Hits);
        else if (isThereAWinner(player1Hits, player2Hits))
            determineWinner(player1Hits, player2Hits);
        else
            assignNewScores(orderedScore[player1Hits], orderedScore[player2Hits]);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TennisScore that = (TennisScore) o;
        return scorePlayer1 == that.scorePlayer1 &&
                scorePlayer2 == that.scorePlayer2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(scorePlayer1, scorePlayer2);
    }

    @Override
    public String toString() {
        return "TennisScore{" +
                "scorePlayer1=" + scorePlayer1 +
                ", scorePlayer2=" + scorePlayer2 +
                ", orderedScore=" + Arrays.toString(orderedScore) +
                '}';
    }

    private boolean differenceOfTwoPoints(int player1Hits, int player2Hits) {
        return Math.abs(player1Hits - player2Hits) >= 2;
    }

    private boolean atLeastOnePlayerHasMarkedFourPoints(int player1Hits, int player2Hits) {
        return Math.max(player1Hits, player2Hits) >= 4;
    }
}
