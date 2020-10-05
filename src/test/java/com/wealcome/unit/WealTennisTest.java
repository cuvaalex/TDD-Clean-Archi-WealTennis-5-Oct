package com.wealcome.unit;

import com.wealcome.WealTennis;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WealTennisTest {

    private WealTennis wealTennis = new WealTennis();

    @Test
    void startTheGameWithDefaultScore() {
        assertScore(() -> {
        }, "Love All");
    }

    @Nested
    class OnePlayerMakesBlankGame {

        @Test
        void shouldReportTheGameScore() {
            assertScore(() -> hitP1(1), "Fifteen Love");
            assertScore(() -> hitP1(2), "Thirty Love");
            assertScore(() -> hitP1(3), "Forty Love");
            assertScore(() -> hitP1(4), "Won!");
        }

    }

    @Nested
    class BothPlayersScoreSomePoints {

        @Test
        void shouldReportTheGameScore() {
            assertScore(() -> {
                hitP1(1);
                hitP2(1);
            }, "Fifteen All");
            assertScore(() -> {
                hitP1(2);
                hitP2(1);
            }, "Thirty Fifteen");
            assertScore(() -> {
                hitP1(3);
                hitP2(3);
            }, "Deuce");
            assertScore(() -> {
                hitP1(4);
                hitP2(3);
            }, "Advantage Forty");
            assertScore(() -> {
                hitP1(4);
                hitP2(4);
            }, "Deuce");
            assertScore(() -> {
                hitP1(3);
                hitP2(4);
            }, "Forty Advantage");
        }

    }

    private void assertScore(Runnable hitsFn, String expected) {
        hitsFn.run();
        assertThat(wealTennis.score()).isEqualTo(expected);
        wealTennis = new WealTennis();
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
