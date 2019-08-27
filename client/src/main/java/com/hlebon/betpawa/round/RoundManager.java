package com.hlebon.betpawa.round;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@Component
public class RoundManager {

    private final List<Round> allRounds = new ArrayList<>();
    private final Random random = new Random();

    @Autowired
    public RoundManager(final Collection<Round> rounds) {
        allRounds.addAll(rounds);
    }

    public Round getRound() {
        int size = allRounds.size();
        int index = random.nextInt(size);
        return allRounds.get(index);
    }
}
