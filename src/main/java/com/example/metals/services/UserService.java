package com.example.metals.services;

import com.example.metals.OracleUpdater.OracleAddress;
import com.example.metals.Request.BalanceRequest;
import com.example.metals.Request.BuyRequest;
import com.example.metals.Request.DepositRequest;
import com.example.metals.Request.SellRequest;
import com.example.metals.store.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.model.Wallet;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private Wallet loadWallet(String privateKey) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails userDetails) {
                Web3j web3j = Web3j.build(new HttpService("http://127.0.0.1:7545"));
                Credentials credentials = Credentials.create(privateKey);
                String walletAddress = userRepository.findByUsername(userDetails.getUsername())
                        .orElseThrow()
                        .getWalletAddress();
                DefaultGasProvider gasProvider = new DefaultGasProvider();
                return Wallet.load(walletAddress, web3j, credentials, gasProvider);
            } else {
                throw new RuntimeException("Principal is not an instance of UserDetails");
            }
        } else {
            throw new RuntimeException("Authentication is null");
        }
    }

    public TransactionReceipt buyMetal(BuyRequest buyRequest) throws Exception {
        Wallet wallet = loadWallet(buyRequest.getPrivateKey());
        return wallet.purchase(buyRequest.getName(), BigInteger.valueOf(buyRequest.getQuantity())).send();
    }

    public TransactionReceipt sellMetal(SellRequest sellRequest) throws Exception {
        Wallet wallet = loadWallet(sellRequest.getPrivateKey());
        return wallet.sell(sellRequest.getName(), BigInteger.valueOf(sellRequest.getQuantity())).send();
    }

    public TransactionReceipt deposit(DepositRequest depositRequest) throws Exception {
        System.out.println(depositRequest.getPrivateKey());
        Wallet wallet = loadWallet(depositRequest.getPrivateKey());

        return wallet.deposit(BigInteger.valueOf(depositRequest.getQuantity())).send();
    }

    public TransactionReceipt withdraw(DepositRequest depositRequest) throws Exception {

        Wallet wallet = loadWallet(depositRequest.getPrivateKey());

        return wallet.withdraw(BigInteger.valueOf(depositRequest.getQuantity())).send();
    }

    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username).orElseThrow();
    }

    public BigInteger getBalance(BalanceRequest balanceRequest) {
        Wallet wallet = loadWallet(balanceRequest.getPrivateKey());
        try {
            Credentials credentials = Credentials.create(balanceRequest.getPrivateKey());
            return wallet.getBalance(credentials.getAddress()).send();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
