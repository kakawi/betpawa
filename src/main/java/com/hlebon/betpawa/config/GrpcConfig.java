package com.hlebon.betpawa.config;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class GrpcConfig {

    @Bean
    public GrpcServerRunner runner(Server server) {
        return new GrpcServerRunner(server);
    }

    @Bean
    public Server grpcServer(List<BindableService> services) {
        ServerBuilder<?> serverBuilder = ServerBuilder.forPort(8087);
        services.forEach(serverBuilder::addService);
        return serverBuilder.build();
    }
}
