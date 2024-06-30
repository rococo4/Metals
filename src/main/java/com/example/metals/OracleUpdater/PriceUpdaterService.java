package com.example.metals.OracleUpdater;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.model.Oracle;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;

public class PriceUpdaterService implements Runnable {

    private final Oracle oracle;

    public PriceUpdaterService(String privateKey, String oracleAddress) throws Exception {
        Web3j web3j = Web3j.build(new HttpService("http://127.0.0.1:7545"));
        Credentials credentials = Credentials.create(privateKey);

        ContractGasProvider gasProvider = new DefaultGasProvider();

        oracle = Oracle.load(oracleAddress, web3j, credentials, gasProvider);
    }

    @Override
    public void run() {
        try {
            // Пример обновления цен
            String metal = "gold";
            BigInteger price = getPriceFromExternalSource(metal);
            oracle.updateRate(metal, price).send();
            System.out.println("Updated price for " + metal + ": " + price);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BigInteger getPriceFromExternalSource(String metal) {

        return BigInteger.valueOf(1800); // Пример цены в центах
    }
}
