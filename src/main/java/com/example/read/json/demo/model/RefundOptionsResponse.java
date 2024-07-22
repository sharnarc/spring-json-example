package com.example.read.json.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class RefundOptionsResponse {

    public List<BusinessAreaCurrency> businessAreaCurrencyList;
    public RefundType refundType;
    public String s4PaymentMethod;
    public String paymentMethod;


}
