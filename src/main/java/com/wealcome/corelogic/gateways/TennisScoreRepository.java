package com.wealcome.corelogic.gateways;

import com.wealcome.corelogic.models.TennisScore;

import java.util.Optional;

public interface TennisScoreRepository {
    Optional<TennisScore> byId(String tennisScoreId);
}
