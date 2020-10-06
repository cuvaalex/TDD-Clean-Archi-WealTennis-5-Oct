package com.wealcome.unit;

import com.wealcome.corelogic.models.TennisScore;
import com.wealcome.corelogic.usecases.WealTennis;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.wealcome.corelogic.models.ScoreEnum.*;
import static org.assertj.core.api.Assertions.assertThat;

public class WealTennisTest {

    private TennisScore tennisScore = new TennisScore(LOVE, LOVE);
    private WealTennis wealTennis = new WealTennis(tennisScore);

    @Test
    void startTheGameWithDefaultScore() {
        assertScore(() -> {
        }, new TennisScore(LOVE, LOVE));
    }

    @Nested
    class OnePlayerMakesBlankGame {

        @Test
        void shouldReportTheGameScore() {
            assertScore(() -> hitP1(1), new TennisScore(FIFTEEN, LOVE));
            assertScore(() -> hitP1(2), new TennisScore(THIRTY, LOVE));
            assertScore(() -> hitP1(3), new TennisScore(FORTY, LOVE));
            assertScore(() -> hitP1(4), new TennisScore(WON, LOVE));
        }

    }

    @Nested
    class BothPlayersScoreSomePoints {

        @Test
        void shouldReportTheGameScore() {
            assertScore(() -> {
                hitP1(1);
                hitP2(1);
            }, new TennisScore(FIFTEEN, FIFTEEN));
            assertScore(() -> {
                hitP1(2);
                hitP2(1);
            }, new TennisScore(THIRTY, FIFTEEN));
            assertScore(() -> {
                hitP1(3);
                hitP2(3);
            }, new TennisScore(FORTY, FORTY));
            assertScore(() -> {
                hitP1(4);
                hitP2(3);
            }, new TennisScore(ADVANTAGE, FORTY));
            assertScore(() -> {
                hitP1(4);
                hitP2(4);
            }, new TennisScore(FORTY, FORTY));
            assertScore(() -> {
                hitP1(3);
                hitP2(4);
            }, new TennisScore(FORTY, ADVANTAGE));
            assertScore(() -> {
                hitP1(3);
                hitP2(4);
                hitP2(1);
            }, new TennisScore(FORTY, WON));
        }

    }

    private void assertScore(Runnable hitsFn, TennisScore expected) {
        hitsFn.run();
        assertThat(tennisScore).isEqualTo(expected);
        tennisScore = new TennisScore(LOVE, LOVE);
        wealTennis = new WealTennis(tennisScore);
    }

    private void hit(String playerName, int times) {
        for (int i = 0; i < times; i++)
            wealTennis.hitWinningPoints(playerName);
    }

    private void hitP1(int times) {
        hit("Player1", times);
    }

    private void hitP2(int times) {
        hit("Player2", times);
    }
}
