package com.example.metals.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigInteger;

@Data
public class DepositRequest {
    private Integer quantity;
    @JsonProperty("private_key")
    private String privateKey;
}
