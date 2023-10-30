package com.example.ghumantu.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequest {
    String customerName;
    String email;
    String phoneNumber;
    BigInteger amount;
}
