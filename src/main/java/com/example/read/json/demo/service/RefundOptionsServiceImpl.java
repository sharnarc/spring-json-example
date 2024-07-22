package com.example.read.json.demo.service;


import com.example.read.json.demo.model.BusinessAreaCurrency;
import com.example.read.json.demo.model.PaymentType;
import com.example.read.json.demo.model.PaymentValue;
import com.example.read.json.demo.model.RefundDataOption;
import com.example.read.json.demo.model.RefundOptions;
import com.example.read.json.demo.model.RefundOptionsRequestDto;
import com.example.read.json.demo.model.RefundOptionsResponse;
import com.example.read.json.demo.model.RefundType;
import com.example.read.json.demo.repository.RefundOptionsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RefundOptionsServiceImpl{
    public static final String CHEQUE = "Cheque";
    private final RefundOptionsRepository repository;

    public Flux<RefundOptionsResponse> getCheckRefundOptions(RefundOptionsRequestDto refundOptionsRequestDto) {
        return repository.findAll()
                .flatMapMany(refundOptions -> {
                    List<RefundOptionsResponse> refundOptionsResponses = new ArrayList<>();
                    refundOptionsRequestDto.getBusinessAreaCurrencyList().forEach(businessAreaCurrency -> refundOptions.getRefundDataOptions().stream()
                            .filter(refundDataOption -> isMatchingRefundDataOption(refundDataOption, businessAreaCurrency))
                            .forEach(refundDataOption -> addToRefundOptionsResponses(refundOptionsResponses, refundDataOption, businessAreaCurrency)));
                    return Flux.fromIterable(refundOptionsResponses);
                });
    }
    private boolean isMatchingRefundDataOption(RefundDataOption refundDataOption, BusinessAreaCurrency businessAreaCurrency) {
        return refundDataOption.getBusinessArea().equals(businessAreaCurrency.getBusinessArea()) &&
                isCurrencyPresent(refundDataOption, businessAreaCurrency);
    }

    private void addToRefundOptionsResponses(List<RefundOptionsResponse> refundOptionsResponses, RefundDataOption refundDataOption, BusinessAreaCurrency businessAreaCurrency) {
        RefundType refundType = mapRefundTypes(refundDataOption);
        String paymentType = mapPaymentTypes(refundDataOption,businessAreaCurrency);
        Optional<RefundOptionsResponse> existingRefundOptionsResponse = refundOptionsResponses.stream()
                .filter(response -> response.getRefundType() == refundType)
                .findFirst();

        if (existingRefundOptionsResponse.isPresent()) {
            existingRefundOptionsResponse.get().getBusinessAreaCurrencyList().add(businessAreaCurrency);
        } else {
            RefundOptionsResponse newRefundOption = RefundOptionsResponse.builder()
                    .businessAreaCurrencyList(Collections.singletonList(businessAreaCurrency))
                    .refundType(refundType)
                    .s4PaymentMethod(paymentType)
                    .build();
            refundOptionsResponses.add(newRefundOption);
        }
    }

    public Mono<Optional<PaymentValue>> getPaymentMethod(String businessArea, String isoCurrencyCode,String modeOfPayment) {
        return repository
                .findAll()
                .map(RefundOptions::getRefundDataOptions)
                .map(refundDataOptions ->
                        refundDataOptions.stream()
                                .filter(refundDataOption -> refundDataOption.businessArea.equals(businessArea))
                                .filter(refundDataOption -> refundDataOption.modeOfPayment.equalsIgnoreCase(modeOfPayment))
                                .map(RefundDataOption::getPaymentValue)
                                .flatMap(List::stream)
                                .filter(paymentValue -> paymentValue.currency.equals(isoCurrencyCode))
                                .findFirst()
                );
    }

    private String mapPaymentTypes(RefundDataOption refundDataOption,BusinessAreaCurrency businessAreaCurrency) {

      return   refundDataOption.getPaymentValue()
                .stream()
                .filter(paymentValue -> paymentValue.getCurrency().equals(businessAreaCurrency.getIsoCurrencyCode()))
                .map(PaymentValue::getS4PaymentMethod) // Extract the S4PaymentMethod from the matching PaymentValue
                .findFirst() // Get the first matching value, if any
                .orElse(null);

    }

    private boolean isCurrencyPresent(RefundDataOption refundDataOption, BusinessAreaCurrency businessAreaCurrency) {
        return refundDataOption.getPaymentValue()
                .stream()
                .anyMatch(paymentValue -> paymentValue.getCurrency().equals(businessAreaCurrency.getIsoCurrencyCode()));
    }

    private RefundType mapRefundTypes(RefundDataOption dataOption) {
        if (CHEQUE.equals(dataOption.getModeOfPayment())) {
            return RefundType.CHEQUE_PICKUP;
        } else {
            return RefundType.BANK_TRANSFER;
        }
    }


}




/*    private Flux<RefundOptionsResponse> getOptionsResponse() {
        return Flux.just(RefundOptionsResponse.builder()
                .businessAreaCurrencyList(List.of(BusinessAreaCurrency.builder().businessArea("EG00").isoCurrencyCode("USD").build()))
                .refundType(RefundType.CHEQUE_PICKUP)
                .build());
    }*/





/*    private Flux<RefundOptions> createRefundOptions() {

        return Flux.just(RefundOptions.builder()
                .refundDataOptions(List.of(RefundDataOption.builder()
                        .modeOfPayment("Cheque")
                        .businessArea("EG00")
                        .paymentValue(List.of(PaymentValue.builder()
                                .currency("USD")
                                .paymentMethod("D")
                                .build()))
                        .build()))
                .build());
    }*/













