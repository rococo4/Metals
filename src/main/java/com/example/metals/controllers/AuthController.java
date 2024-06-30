package com.example.metals.controllers;

import com.example.metals.Request.SignInRequest;
import com.example.metals.Request.SignUpRequest;
import com.example.metals.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PutMapping("/sign-in")
    public String signIn(@RequestBody SignInRequest signInRequest) {
        return authService.LogIn(signInRequest);
    }
    @PostMapping("/sign-up")
    public String logIn(@RequestBody SignUpRequest signUpRequest) throws Exception {
        return authService.Register(signUpRequest);
    }
}
