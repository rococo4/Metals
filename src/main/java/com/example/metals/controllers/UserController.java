package com.example.metals.controllers;

import com.example.metals.Request.BalanceRequest;
import com.example.metals.Request.BuyRequest;
import com.example.metals.Request.DepositRequest;
import com.example.metals.Request.SellRequest;
import com.example.metals.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/buy")
    public TransactionReceipt buyMetal(@RequestBody BuyRequest buyRequest) {
        try {
            return userService.buyMetal(buyRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/sell")
    public TransactionReceipt sellMetal(@RequestBody SellRequest sellRequest) {
        try {
            return userService.sellMetal(sellRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @PostMapping("/deposit")
    public TransactionReceipt deposit(@RequestBody DepositRequest depositRequest) {
        try {
            return userService.deposit(depositRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/withdraw")
    public TransactionReceipt withdraw(@RequestBody DepositRequest depositRequest) {
        try {
            return userService.withdraw(depositRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/balance")
    public BigInteger getBalance(@RequestBody BalanceRequest balanceRequest) {
        try {
            return userService.getBalance(balanceRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
