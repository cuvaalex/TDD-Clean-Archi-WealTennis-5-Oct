package com.wealcome.unit;

import com.wealcome.adapters.secondary.gateways.InMemoryTennisScoreRepository;
import com.wealcome.corelogic.models.TennisScore;
import com.wealcome.corelogic.usecases.TennisScorePresenter;
import com.wealcome.corelogic.usecases.WealTennisUseCase;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.wealcome.corelogic.models.ScoreEnum.*;
import static org.assertj.core.api.Assertions.assertThat;

public class WealTennisUseCaseTest {

    private final InMemoryTennisScoreRepository tennisScoreRepository = new InMemoryTennisScoreRepository();
    private final SimpleTennisScorePresenter simpleTennisScorePresenter = new SimpleTennisScorePresenter();
    private WealTennisUseCase wealTennisUseCase = new WealTennisUseCase(tennisScoreRepository);

    @Test
    void startTheGameWithDefaultScore() {
        assertScore(() -> {
        }, new TennisScore("123abc", LOVE, LOVE));
    }

    @Nested
    class OnePlayerMakesBlankGame {

        @Test
        void shouldReportTheGameScore() {
            assertScore(() -> hitP1(1), new TennisScore("123abc", FIFTEEN, LOVE));
            assertScore(() -> hitP1(2), new TennisScore("123abc", THIRTY, LOVE));
            assertScore(() -> hitP1(3), new TennisScore("123abc", FORTY, LOVE));
            assertScore(() -> hitP1(4), new TennisScore("123abc", WON, LOVE));
        }

    }

    @Nested
    class BothPlayersScoreSomePoints {

        @Test
        void shouldReportTheGameScore() {
            assertScore(() -> {
                hitP1(1);
                hitP2(1);
            }, new TennisScore("123abc", FIFTEEN, FIFTEEN));
            assertScore(() -> {
                hitP1(2);
                hitP2(1);
            }, new TennisScore("123abc", THIRTY, FIFTEEN));
            assertScore(() -> {
                hitP1(3);
                hitP2(3);
            }, new TennisScore("123abc", FORTY, FORTY));
            assertScore(() -> {
                hitP1(4);
                hitP2(3);
            }, new TennisScore("123abc", ADVANTAGE, FORTY));
            assertScore(() -> {
                hitP1(4);
                hitP2(4);
            }, new TennisScore("123abc", FORTY, FORTY));
            assertScore(() -> {
                hitP1(3);
                hitP2(4);
            }, new TennisScore("123abc", FORTY, ADVANTAGE));
            assertScore(() -> {
                hitP1(3);
                hitP2(4);
                hitP2(1);
            }, new TennisScore("123abc", FORTY, WON));
        }

        @Test
        void shouldConsiderAPlayerWinningIfTheOtherGivesUp() {
            assertScore(() -> {
                hitP1(1);
                hitP2(1);
                giveUpP1();
            }, new TennisScore("123abc", FIFTEEN, WON));
            assertScore(() -> {
                hitP1(1);
                hitP2(1);
                giveUpP2();
            }, new TennisScore("123abc", WON, FIFTEEN));
        }

    }

    private void assertScore(Runnable hitsFn, TennisScore expected) {
        tennisScoreRepository.init("123abc", new TennisScore("123abc"));
        hitsFn.run();
        assertThat(simpleTennisScorePresenter.score())
                .isEqualTo(expected.getScorePlayer1() + " - " + expected.getScorePlayer2());
        wealTennisUseCase = new WealTennisUseCase(tennisScoreRepository);
    }

    private void hit(String playerName, int times) {
        for (int i = 0; i < times; i++)
            wealTennisUseCase.hitWinningPoints(simpleTennisScorePresenter, "123abc", playerName);
    }

    private void hitP1(int times) {
        hit("Player1", times);
    }

    private void hitP2(int times) {
        hit("Player2", times);
    }

    private void giveUp(String playerName) {
        wealTennisUseCase.giveUp(simpleTennisScorePresenter, "123abc", playerName);
    }

    private void giveUpP1() {
        giveUp("Player1");
    }

    private void giveUpP2() {
        giveUp("Player2");
    }
}

class SimpleTennisScorePresenter implements TennisScorePresenter {

    private String scorePlayer1 = "LOVE";
    private String scorePlayer2 = "LOVE";

    @Override
    public void presentTennisScore(String scorePlayer1, String scorePlayer2) {
        this.scorePlayer1 = scorePlayer1;
        this.scorePlayer2 = scorePlayer2;
    }

    public String score() {
        return scorePlayer1 + " - " + scorePlayer2;
    }
}
