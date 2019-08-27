package com.hlebon.betpawa.service;

import com.hlebon.betpawa.protos.BetpawaServiceGrpc;
import com.hlebon.betpawa.round.Round;
import com.hlebon.betpawa.round.RoundManager;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class UserExec implements Runnable {

    private final int concurrentThreadsPerUser;
    private final int roundPerThread;
    private final RoundManager roundManager;
    private final int userId;
    private final int port;
    private final String host;

    public UserExec(
            final int concurrentThreadsPerUser,
            final int roundPerThread,
            final RoundManager roundManager,
            final int userId,
            final int port,
            final String host
    ) {
        this.concurrentThreadsPerUser = concurrentThreadsPerUser;
        this.roundPerThread = roundPerThread;
        this.roundManager = roundManager;
        this.userId = userId;
        this.port = port;
        this.host = host;
    }

    @Override
    public void run() {
        ExecutorService perUserExecutor = Executors.newFixedThreadPool(concurrentThreadsPerUser);

        for (int i = 0; i < concurrentThreadsPerUser; i++) {
            perUserExecutor.submit(() -> {
                for (int j = 0; j < roundPerThread; j++) {
                    startRound();
                }
            });
        }
        try {
            perUserExecutor.shutdown();
            perUserExecutor.awaitTermination(1, TimeUnit.DAYS);
        } catch (final InterruptedException e) {
            // do nothing
        }
    }

    private void startRound() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        BetpawaServiceGrpc.BetpawaServiceBlockingStub stub = BetpawaServiceGrpc.newBlockingStub(channel);
        Round round = roundManager.getRound();
        try {
            round.start(stub, userId);
            channel.shutdown();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
