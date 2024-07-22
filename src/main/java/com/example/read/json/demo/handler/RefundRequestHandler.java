package com.example.read.json.demo.handler;

import com.example.read.json.demo.model.PaymentOption;
import com.example.read.json.demo.model.RefundOptionsRequest;
import com.example.read.json.demo.model.RefundOptionsRequestDto;
import com.example.read.json.demo.model.RefundOptionsResponse;
import com.example.read.json.demo.service.RefundOptionsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class RefundRequestHandler {

    private final RefundOptionsServiceImpl optionsService;
    private static final String REQUEST_PARAM_VALIDATED_MESSAGE = "Request param validated";


    public Mono<ServerResponse> getChequeRefundOptions(ServerRequest request) {
        log.info("Getting Refund Options");

        Mono<RefundOptionsRequest> optionsRequestMono = request.bodyToMono(RefundOptionsRequest.class);
        return optionsRequestMono.flatMap(refundOptionsRequest -> {
            if (CollectionUtils.isEmpty(refundOptionsRequest.getBusinessAreaCurrencyList())) {
                return Mono.error(new RuntimeException("DATA_ERROR_MESSAGE"));
            }

            log.info(REQUEST_PARAM_VALIDATED_MESSAGE);
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                    .body(optionsService.getCheckRefundOptions(mapRefundOptionsToRequestDto(refundOptionsRequest)), RefundOptionsResponse.class);

        });

    }

    private RefundOptionsRequestDto mapRefundOptionsToRequestDto(RefundOptionsRequest refundOptionsRequest) {
        return RefundOptionsRequestDto.builder()
                .businessAreaCurrencyList(refundOptionsRequest.getBusinessAreaCurrencyList())
                .build();
    }

    public Mono<ServerResponse> getPaymentMethod(ServerRequest serverRequest) {

        Mono<RefundOptionsRequest> optionsRequestMono = serverRequest.bodyToMono(RefundOptionsRequest.class);
        return optionsRequestMono.flatMap(refundOptionsRequest -> {
            if (CollectionUtils.isEmpty(refundOptionsRequest.getBusinessAreaCurrencyList())) {
                return Mono.error(new RuntimeException("DATA_ERROR_MESSAGE"));
            }

            log.info(REQUEST_PARAM_VALIDATED_MESSAGE);
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                    .body(optionsService.getPaymentMethod(refundOptionsRequest.getBusinessAreaCurrencyList()
                            .get(0).businessArea, refundOptionsRequest.getBusinessAreaCurrencyList()
                            .get(0).isoCurrencyCode,refundOptionsRequest.getModeOfPayment()), PaymentOption.class);

        });

    }
}
