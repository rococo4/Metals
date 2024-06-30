package com.example.metals.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SignInRequest {
    private String username;
    @JsonProperty("private_key")
    private String privateKey;
    private String password;
}
