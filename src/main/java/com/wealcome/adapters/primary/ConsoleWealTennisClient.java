package com.wealcome.adapters.primary;

import com.wealcome.adapters.secondary.gateways.InMemoryTennisScoreRepository;
import com.wealcome.corelogic.models.TennisScore;
import com.wealcome.corelogic.usecases.TennisScorePresenter;
import com.wealcome.corelogic.usecases.WealTennisUseCase;

public class ConsoleWealTennisClient {

    public static void main(String[] args) {
        InMemoryTennisScoreRepository tennisScoreRepository = new InMemoryTennisScoreRepository();
        tennisScoreRepository.init("123abc", new TennisScore("123abc"));
        ConsoleWealTennisScorePresenter tennisScorePresenter = new ConsoleWealTennisScorePresenter();
        WealTennisUseCase wealTennisUseCase = new WealTennisUseCase(tennisScoreRepository);
        hitWinningPoints(wealTennisUseCase, tennisScorePresenter, "Player1", 3);
        hitWinningPoints(wealTennisUseCase, tennisScorePresenter, "Player2", 5);
        System.out.println(tennisScorePresenter.score());
    }

    private static void hitWinningPoints(WealTennisUseCase wealTennisUseCase,
                                         TennisScorePresenter tennisScorePresenter,
                                         String playerName,
                                         int times) {
        for (int i = 0; i < times; i++)
            wealTennisUseCase.hitWinningPoints(
                    tennisScorePresenter,
                    "123abc",
                    playerName
            );

    }
}
