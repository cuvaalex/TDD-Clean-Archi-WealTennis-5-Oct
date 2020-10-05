package com.wealcome.unit;

import com.wealcome.WealTennis;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WealTennisTest {

    // 0   15    30     40   Win
    // 40 - 40 =>   0   15   30  40 Advantage Win

    // 0 0   Love All
    // 15 0    Fifteen Love
    // 15 15    Fifteen All
    // 30 15    Thirty Fifteen
    // 40 15    Forty Fifteen
    // Won

    @Test
    void startTheGameWithDefaultScore() {
        assertThat(new WealTennis().score()).isEqualTo("Love All");
    }

}
