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
public class RefundOptionsRequestDto {

    List<BusinessAreaCurrency> businessAreaCurrencyList;
}
