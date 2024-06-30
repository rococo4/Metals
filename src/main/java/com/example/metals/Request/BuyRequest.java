package com.example.metals.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BuyRequest {
    private String name;
    private Integer quantity;
    @JsonProperty("private_key")
    private String privateKey;
}
