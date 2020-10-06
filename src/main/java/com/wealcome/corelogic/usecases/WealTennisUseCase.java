package com.wealcome.corelogic.usecases;

import com.wealcome.corelogic.gateways.TennisScoreRepository;

public class WealTennisUseCase {

    private final TennisScoreRepository tennisScoreRepository;

    public WealTennisUseCase(TennisScoreRepository tennisScoreRepository) {
        this.tennisScoreRepository = tennisScoreRepository;
    }

    public void hitWinningPoints(TennisScorePresenter tennisScorePresenter,
                                 String tennisScoreId,
                                 String playerName) {
        tennisScoreRepository.byId(tennisScoreId).ifPresent(
                tennisScore -> {
                    tennisScore.computeNewScore(playerName);
                    tennisScorePresenter.presentTennisScore(
                            tennisScore.getScorePlayer1(),
                            tennisScore.getScorePlayer2()
                    );
                }
        );
    }

    public void giveUp(TennisScorePresenter tennisScorePresenter,
                       String tennisScoreId,
                       String playerName) {
        tennisScoreRepository.byId(tennisScoreId).ifPresent(
                tennisScore -> {
                    tennisScore.giveUp(playerName);
                    tennisScorePresenter.presentTennisScore(
                            tennisScore.getScorePlayer1(),
                            tennisScore.getScorePlayer2()
                    );
                }
        );
    }
}
