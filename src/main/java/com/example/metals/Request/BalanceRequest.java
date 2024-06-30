package com.example.metals.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BalanceRequest {
    @JsonProperty("private_key")
    private String privateKey;
}
