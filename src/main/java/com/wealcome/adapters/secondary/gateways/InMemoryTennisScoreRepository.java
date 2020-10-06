package com.wealcome.adapters.secondary.gateways;

import com.wealcome.corelogic.gateways.TennisScoreRepository;
import com.wealcome.corelogic.models.TennisScore;

import java.util.*;

public class InMemoryTennisScoreRepository implements TennisScoreRepository {

    private final Map<String, TennisScore> tennisScores = new HashMap<>();

    @Override
    public Optional<TennisScore> byId(String tennisScoreId) {
        return Optional.ofNullable(tennisScores.get(tennisScoreId));
    }

    public Collection<TennisScore> scores() {
        return Collections.unmodifiableCollection(tennisScores.values());
    }

    public void init(String tennisScoreId, TennisScore tennisScore) {
        tennisScores.put(tennisScoreId, tennisScore);
    }
}
