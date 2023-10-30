package com.example.ghumantu.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RazorpayResponse {
    private String razorpay_order_id;
    private String razorpay_payment_id;
    private String razorpay_signature;

}
