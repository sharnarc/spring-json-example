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
public class RefundOptionsRequest {

    private static final String MANDATORY_PARAM_MISSING = "Invalid request. Please provide all mandatory parameters";

    List<BusinessAreaCurrency> businessAreaCurrencyList;
    public String modeOfPayment;

}
