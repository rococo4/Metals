package com.example.metals;

import com.example.metals.OracleUpdater.OracleAddress;
import com.example.metals.OracleUpdater.PriceUpdaterService;
import org.aspectj.weaver.ast.Or;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.web3j.crypto.Credentials;
import org.web3j.model.Oracle;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

@SpringBootApplication
public class MetalsApplication {

    public static void main(String[] args) throws Exception {
        String key = "0x162923a4b02ef06f625df1c4bb507e9ea5d9f65fe1862d232996a5f135f170cd";
        Web3j web3j = Web3j.build(new HttpService("http://127.0.0.1:7545"));
        Credentials credentials = Credentials.create(key);
        DefaultGasProvider gasProvider = new DefaultGasProvider();
        Oracle oracle = Oracle.deploy(web3j, credentials, gasProvider).send();
        OracleAddress.oracleAddress = oracle.getContractAddress();
        PriceUpdaterService priceUpdaterService = new PriceUpdaterService(key, OracleAddress.oracleAddress);

        SpringApplication.run(MetalsApplication.class, args);
    }

}
