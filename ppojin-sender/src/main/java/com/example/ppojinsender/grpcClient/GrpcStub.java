package com.example.ppojinsender.grpcClient;

import com.fleta.calculator.ReactorCalculatorServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcStub {
    static final String host = "localhost";
    static final int grpcPort = 6565;

    @Bean
    public ManagedChannel channel() {
        return ManagedChannelBuilder
            .forAddress(this.host, this.grpcPort)
            .usePlaintext()
            .build();
    }

    @Bean
    public ReactorCalculatorServiceGrpc.ReactorCalculatorServiceStub serviceStub() {
        return ReactorCalculatorServiceGrpc.newReactorStub(channel());
    }

}
