package com.example.ppojinreceiver;

import com.example.ppojinreceiver.grpcServer.CalculatorService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class PpojinReceiverApplication {

    public static void main(String[] args) {
        try {
            runGrpcServer();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        SpringApplication.run(PpojinReceiverApplication.class, args);
    }

    private static void runGrpcServer() throws IOException, InterruptedException {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> grpc server starting");

        final int grpcPort = 6565;

        // build gRPC server
        Server server = ServerBuilder.forPort(grpcPort)
                .addService(new CalculatorService())
                .build();
        // start
        server.start();

        // shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("gRPC server is shutting down!");
            server.shutdown();
        }));

        server.awaitTermination();

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> grpc server started");
    }

}
