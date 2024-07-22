package com.example.read.json.demo.router;

import com.example.read.json.demo.handler.RefundRequestHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class RefundRequestRouter {
    @Bean
    public RouterFunction<ServerResponse> route(RefundRequestHandler refundRequestHandler) {
        return RouterFunctions.route()
                .path("/refund", builder -> builder
                        .POST("/options", accept(MediaType.APPLICATION_JSON), refundRequestHandler::getChequeRefundOptions)
                        .POST("/options/payment", accept(MediaType.APPLICATION_JSON), refundRequestHandler::getPaymentMethod))
                .build();

    }
}
