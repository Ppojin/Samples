package com.example.ppojinsender.grpcClient;

import com.fleta.calculator.ReactorCalculatorServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcStub {

    @Value("${grpc.host}")
    public String host;

    @Value("${grpc.port}")
    public int port;

    @Bean
    public ReactorCalculatorServiceGrpc.ReactorCalculatorServiceStub serviceStub() {
        return ReactorCalculatorServiceGrpc.newReactorStub(ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build());
    }

}
