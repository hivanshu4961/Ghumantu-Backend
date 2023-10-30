package com.example.ghumantu.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderResponse {
    String secretKey;
    String razorpayOrderId;
    String applicationFee;
    String secretId;
    String pgName;

}
