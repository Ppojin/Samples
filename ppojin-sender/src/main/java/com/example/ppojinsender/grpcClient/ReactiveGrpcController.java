package com.example.ppojinsender.grpcClient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fleta.calculator.Input;
import com.fleta.calculator.Output;
import com.fleta.calculator.ReactorCalculatorServiceGrpc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/grpctest")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Slf4j
public class ReactiveGrpcController {

    @Autowired
    private ReactorCalculatorServiceGrpc.ReactorCalculatorServiceStub serviceStub;

    @GetMapping("/square/{number}")
    public Mono<ResponseEntity> findSquareTest(@PathVariable Integer number){
        log.info("square("+number+")");

        Input input = Input.newBuilder().setNumber(number).build();

        return this.serviceStub.findSquare(input)
                .map((Output v) -> {
                    log.info(String.format("response: %d", v.getResult()));
                    return ResponseEntity.status(418).body(v.getResult());
                });
    }

    @GetMapping("/factors/{number}")
    public ResponseEntity<Flux<Output>> findFactorsTest(@PathVariable Integer number){
        log.info("factors("+number+")");

        Input input = Input.newBuilder().setNumber(number).build();

        return ResponseEntity.status(418).body(
                this.serviceStub.findFactors(input)
        );
    }

    @GetMapping("/sumAll/{number}")
    public ResponseEntity<Mono<Output>> sumAllTest(@PathVariable Integer number){
        log.info("sumAll("+number+")");

        Flux<Input> inputFlux = Flux.range(1, number)
            .map(i -> Input.newBuilder().setNumber(i).build());

        return ResponseEntity.status(418).body(
                this.serviceStub.findSum(inputFlux)
        );
    }
}
