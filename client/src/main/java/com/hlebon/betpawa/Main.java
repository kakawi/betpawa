package com.hlebon.betpawa;

import com.hlebon.betpawa.round.RoundA;
import com.hlebon.betpawa.round.RoundB;
import com.hlebon.betpawa.round.RoundC;
import com.hlebon.betpawa.round.RoundManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        int numberOfUsers = 5; // (number of concurrent users emulated)
        int concurrentThreadsPerUser = 11; // (number of concurrent requests a user will make)
        int roundPerThread = 12; // (number of rounds each thread is executing)

        RoundManager roundManager = new RoundManager(
                new RoundA(),
                new RoundB(),
                new RoundC()
        );

        ExecutorService usersExecutor = Executors.newFixedThreadPool(numberOfUsers);

        for (int userId = 1; userId <= numberOfUsers; userId++) {
            UserExec userExec = new UserExec(concurrentThreadsPerUser, roundPerThread, roundManager, userId);
            usersExecutor.submit(userExec);
        }

        try {
            usersExecutor.shutdown();
            usersExecutor.awaitTermination(1, TimeUnit.DAYS);
        } catch (final InterruptedException e) {
            // do nothing
        }
    }
}
