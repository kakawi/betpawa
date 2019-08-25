package com.hlebon.betpawa.config;

import io.grpc.Server;
import org.springframework.boot.CommandLineRunner;

public class GrpcServerRunner implements CommandLineRunner {

    private final Server server;

    public GrpcServerRunner(Server server) {
        this.server = server;
    }


    @Override
    public void run(String... args) throws Exception {
        server.start();
    }
}
