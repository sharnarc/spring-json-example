package com.example.read.json.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class BusinessAreaCurrency {

    private static final String MANDATORY_PARAM_MISSING = "Invalid request. Please provide all mandatory parameters";

    public String businessArea;
    public String isoCurrencyCode;
}
