package com.wealcome.adapters.secondary.gateways;

import com.wealcome.corelogic.gateways.TennisScoreRepository;
import com.wealcome.corelogic.models.TennisScore;

import java.util.Optional;

public class SqlTennisScoreRepository implements TennisScoreRepository {

    @Override
    public Optional<TennisScore> byId(String tennisScoreId) {
        // SELECT * from tennisscore where id = tennisScoreId;
        System.out.println("Do an SQL QUERY for retrieving " + tennisScoreId);
        return Optional.empty();
    }

}
