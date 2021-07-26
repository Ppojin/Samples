package com.example.ppojinreceiver.grpcServer;

import com.fleta.calculator.Input;
import com.fleta.calculator.Output;
import com.fleta.calculator.ReactorCalculatorServiceGrpc;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class CalculatorService extends ReactorCalculatorServiceGrpc.CalculatorServiceImplBase {
    @Override
    public Mono<Output> findSquare(Mono<Input> request) {
        return request.map((Input v) ->{
            int number = v.getNumber();
            log.info(String.format("findSquare requested: %d", number));
            return number;
        }).map(
                (Integer i) -> Output.newBuilder().setResult(i * i).build()
        );
    }

    @Override
    public Flux<Output> findFactors(Mono<Input> request) {
        return request.map((Input v) ->{
            int number = v.getNumber();
            log.info(String.format("findFactors requested: %d", number));
            return number;
        }).filter(i -> i > 0)
            .flatMapMany(input -> Flux.range(2, input / 2)
                .filter(f -> input % f == 0))
            .map(o -> Output.newBuilder().setResult(o).build());
    }

    @Override
    public Mono<Output> findSum(Flux<Input> request) {
        return request.map((Input v) ->{
            int number = v.getNumber();
            log.info(String.format("findSum requested: %d", number));
            return number;
        }).reduce(Integer::sum)
            .map(i -> Output.newBuilder().setResult(i).build());
    }
}
