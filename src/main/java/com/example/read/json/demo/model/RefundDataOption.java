package com.example.read.json.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefundDataOption {
    public String businessArea;
    public String modeOfPayment;
    public List<PaymentValue> paymentValue;
}
