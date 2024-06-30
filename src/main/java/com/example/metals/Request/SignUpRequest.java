package com.example.metals.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SignUpRequest {
    private String username;
    private String password;
    @JsonProperty("private_key")
    private String privateKey;
    private String email;
}
