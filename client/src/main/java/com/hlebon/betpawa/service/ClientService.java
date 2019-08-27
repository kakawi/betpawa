package com.hlebon.betpawa.service;

import com.google.protobuf.Empty;
import com.hlebon.betpawa.protos.CreateUserRequest;
import com.hlebon.betpawa.protos.UserServiceGrpc;
import com.hlebon.betpawa.round.RoundManager;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class ClientService implements CommandLineRunner {

    @Value("${port:8087}")
    public int port = 8087;

    @Value("${host:localhost}")
    public String host = "localhost";

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
        prepareUsers();
        ExecutorService usersExecutor = Executors.newFixedThreadPool(numberOfUsers);

        for (int userId = 1; userId <= numberOfUsers; userId++) {
            UserExec userExec = new UserExec(concurrentThreadsPerUser, roundPerThread, roundManager, userId, port, host);
            usersExecutor.submit(userExec);
        }

        try {
            usersExecutor.shutdown();
            usersExecutor.awaitTermination(1, TimeUnit.DAYS);
        } catch (final InterruptedException e) {
            // do nothing
        }
    }

    private void prepareUsers() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);
        try {
            stub.deleteAll(Empty.newBuilder().build());
            for (int i = 1; i <= numberOfUsers; i++) {
                stub.create(CreateUserRequest.newBuilder()
                        .setId(i)
                        .setName("user#" + i)
                        .build());
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        channel.shutdown();
    }
}
