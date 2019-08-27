package com.hlebon.betpawa.round;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RoundManager {

    private final List<Round> allRounds = new ArrayList<>();
    private final Random random = new Random();

    public RoundManager(final Round... rounds) {
        allRounds.addAll(Arrays.asList(rounds));
    }

    public Round getRound() {
        int size = allRounds.size();
        int index = random.nextInt(size);
        return allRounds.get(index);
    }
}
