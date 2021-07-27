package com.example.ppojinreceiver.grpcServer;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class CalculatorServer implements ApplicationRunner {

    @Value("${grpc.port}")
    public int port;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ServerBuilder.forPort(port)
                .addService(new CalculatorService())
                .build().start();
    }
}