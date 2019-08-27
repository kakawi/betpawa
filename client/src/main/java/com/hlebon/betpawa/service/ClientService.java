package com.hlebon.betpawa.service;

import com.hlebon.betpawa.round.RoundManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class ClientService implements CommandLineRunner {

    @Value("${number_of_users:5}")
    private int numberOfUsers;

    @Value("${concurrent_threads_per_user:11}")
    private int concurrentThreadsPerUser;

    @Value("${round_per_thread:12}")
    private int roundPerThread;

    private final RoundManager roundManager;

    @Autowired
    public ClientService(RoundManager roundManager) {
        this.roundManager = roundManager;
    }

    @Override
    public void run(final String... args) throws Exception {
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
