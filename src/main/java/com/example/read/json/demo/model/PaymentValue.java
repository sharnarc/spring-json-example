package com.example.read.json.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentValue {
    public String currency;
    public String paymentMethod;
    public String s4PaymentMethod;
}
