package com.example.metals.services;


import com.example.metals.Auth.JwtService;
import com.example.metals.Auth.PasswordEncoderConfig;
import com.example.metals.OracleUpdater.OracleAddress;
import com.example.metals.Request.SignInRequest;
import com.example.metals.Request.SignUpRequest;
import com.example.metals.store.entities.UserEntity;
import com.example.metals.store.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.web3j.crypto.Credentials;
import org.web3j.model.Wallet;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoderConfig passwordEncoderConfig;
    private final JwtService jwtService;

    public String Register(SignUpRequest signUpRequest) throws Exception {
        if (signUpRequest.getUsername() == null || signUpRequest.getPassword() == null) {
            throw new BadRequestException("Username and password are required");
        }
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new BadRequestException("Username already exists");
        }
        Web3j web3j = Web3j.build(new HttpService("http://127.0.0.1:7545"));
        Credentials credentials = Credentials.create(signUpRequest.getPrivateKey());

        ContractGasProvider gasProvider = new DefaultGasProvider();

        Wallet wallet = Wallet.deploy(
                web3j, credentials, gasProvider, OracleAddress.oracleAddress
        ).send();
        UserEntity user = userRepository.saveAndFlush(UserEntity.builder().
                username(signUpRequest.getUsername())
                .password(passwordEncoderConfig.getPasswordEncoder().encode(signUpRequest.getPassword()))
                .email(signUpRequest.getEmail())
                .walletAddress(wallet.getContractAddress())
                .build());
        return jwtService.generateToken(user);
    }
    public String LogIn(SignInRequest signInRequest) {
        UserEntity user = userRepository.findByUsername(signInRequest.getUsername()).orElseThrow();
        if (! passwordEncoderConfig.getPasswordEncoder().matches(signInRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }
        return jwtService.generateToken(user);
    }
}
