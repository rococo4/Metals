package org.web3j.model;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.11.3.
 */
@SuppressWarnings("rawtypes")
public class Wallet extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5060405161080a38038061080a8339818101604052602081101561003357600080fd5b5051600080546001600160a01b039092166001600160a01b03199092169190911790556107a5806100656000396000f3fe6080604052600436106100705760003560e01c80639f6563211161004e5780639f65632114610117578063aa38e38c146101cc578063d0e30db014610281578063f8b2cb4f1461028957610070565b806327e235e3146100755780632e1a7d4d146100ba5780637dc0d1d0146100e6575b600080fd5b34801561008157600080fd5b506100a86004803603602081101561009857600080fd5b50356001600160a01b03166102bc565b60408051918252519081900360200190f35b3480156100c657600080fd5b506100e4600480360360208110156100dd57600080fd5b50356102ce565b005b3480156100f257600080fd5b506100fb6103a2565b604080516001600160a01b039092168252519081900360200190f35b34801561012357600080fd5b506100e46004803603604081101561013a57600080fd5b81019060208101813564010000000081111561015557600080fd5b82018360208201111561016757600080fd5b8035906020019184600183028401116401000000008311171561018957600080fd5b91908080601f01602080910402602001604051908101604052809392919081815260200183838082843760009201919091525092955050913592506103b1915050565b3480156101d857600080fd5b506100e4600480360360408110156101ef57600080fd5b81019060208101813564010000000081111561020a57600080fd5b82018360208201111561021c57600080fd5b8035906020019184600183028401116401000000008311171561023e57600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295505091359250610550915050565b6100e4610705565b34801561029557600080fd5b506100a8600480360360208110156102ac57600080fd5b50356001600160a01b0316610754565b60016020526000908152604090205481565b33600090815260016020526040902054811115610329576040805162461bcd60e51b8152602060048201526014602482015273496e73756666696369656e742062616c616e636560601b604482015290519081900360640190fd5b33600081815260016020526040808220805485900390555183156108fc0291849190818181858888f19350505050158015610368573d6000803e3d6000fd5b5060408051828152905133917f7fcf532c15f0a6db0bd6d0e038bea71d30d808c7d98cb3bf7268a95bf5081b65919081900360200190a250565b6000546001600160a01b031681565b60008054604051630d81b5db60e31b81526020600482018181528651602484015286516001600160a01b0390941693636c0daed89388938392604490920191908501908083838b5b838110156104115781810151838201526020016103f9565b50505050905090810190601f16801561043e5780820380516001836020036101000a031916815260200191505b509250505060206040518083038186803b15801561045b57600080fd5b505afa15801561046f573d6000803e3d6000fd5b505050506040513d602081101561048557600080fd5b5051336000818152600160209081526040808320805488870290810190915581518084018990528281528951928101929092528851959650947f64ea50dbcfbb858a91c62e06d611216f022c31aeaa3d6079a6965e2dbfdae60193899389939283926060840192870191908190849084905b8381101561050f5781810151838201526020016104f7565b50505050905090810190601f16801561053c5780820380516001836020036101000a031916815260200191505b50935050505060405180910390a250505050565b60008054604051630d81b5db60e31b81526020600482018181528651602484015286516001600160a01b0390941693636c0daed89388938392604490920191908501908083838b5b838110156105b0578181015183820152602001610598565b50505050905090810190601f1680156105dd5780820380516001836020036101000a031916815260200191505b509250505060206040518083038186803b1580156105fa57600080fd5b505afa15801561060e573d6000803e3d6000fd5b505050506040513d602081101561062457600080fd5b50513360009081526001602052604090205490915082820290811115610688576040805162461bcd60e51b8152602060048201526014602482015273496e73756666696369656e742062616c616e636560601b604482015290519081900360640190fd5b33600081815260016020908152604080832080548690039055805180830188905281815288519181019190915287517f7a5116c78a9e1ad902408eee81d2bc878da4de341babc5c94da8bb189d924dab9389938993928392606084019287019190819084908490831561050f5781810151838201526020016104f7565b33600081815260016020908152604091829020805434908101909155825190815291517fe1fffcc4923d04b559f4d29a8bfc6cda04eb5b0d3c460751c2402c5c5cc9109c9281900390910190a2565b6001600160a01b03166000908152600160205260409020549056fea26469706673582212202210ded1b438da413df1cda4897e769ad4d38613dfe0c20e634fe0346d8767ac64736f6c63430007060033";

    private static String librariesLinkedBinary;

    public static final String FUNC_BALANCES = "balances";

    public static final String FUNC_DEPOSIT = "deposit";

    public static final String FUNC_GETBALANCE = "getBalance";

    public static final String FUNC_ORACLE = "oracle";

    public static final String FUNC_PURCHASE = "purchase";

    public static final String FUNC_SELL = "sell";

    public static final String FUNC_WITHDRAW = "withdraw";

    public static final Event DEPOSIT_EVENT = new Event("Deposit", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event PURCHASE_EVENT = new Event("Purchase", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event SALE_EVENT = new Event("Sale", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event WITHDRAWAL_EVENT = new Event("Withdrawal", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected Wallet(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Wallet(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Wallet(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Wallet(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<DepositEventResponse> getDepositEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(DEPOSIT_EVENT, transactionReceipt);
        ArrayList<DepositEventResponse> responses = new ArrayList<DepositEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DepositEventResponse typedResponse = new DepositEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static DepositEventResponse getDepositEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(DEPOSIT_EVENT, log);
        DepositEventResponse typedResponse = new DepositEventResponse();
        typedResponse.log = log;
        typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<DepositEventResponse> depositEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getDepositEventFromLog(log));
    }

    public Flowable<DepositEventResponse> depositEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DEPOSIT_EVENT));
        return depositEventFlowable(filter);
    }

    public static List<PurchaseEventResponse> getPurchaseEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(PURCHASE_EVENT, transactionReceipt);
        ArrayList<PurchaseEventResponse> responses = new ArrayList<PurchaseEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PurchaseEventResponse typedResponse = new PurchaseEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.asset = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static PurchaseEventResponse getPurchaseEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(PURCHASE_EVENT, log);
        PurchaseEventResponse typedResponse = new PurchaseEventResponse();
        typedResponse.log = log;
        typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.asset = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<PurchaseEventResponse> purchaseEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getPurchaseEventFromLog(log));
    }

    public Flowable<PurchaseEventResponse> purchaseEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PURCHASE_EVENT));
        return purchaseEventFlowable(filter);
    }

    public static List<SaleEventResponse> getSaleEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(SALE_EVENT, transactionReceipt);
        ArrayList<SaleEventResponse> responses = new ArrayList<SaleEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SaleEventResponse typedResponse = new SaleEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.asset = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static SaleEventResponse getSaleEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(SALE_EVENT, log);
        SaleEventResponse typedResponse = new SaleEventResponse();
        typedResponse.log = log;
        typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.asset = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<SaleEventResponse> saleEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getSaleEventFromLog(log));
    }

    public Flowable<SaleEventResponse> saleEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SALE_EVENT));
        return saleEventFlowable(filter);
    }

    public static List<WithdrawalEventResponse> getWithdrawalEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(WITHDRAWAL_EVENT, transactionReceipt);
        ArrayList<WithdrawalEventResponse> responses = new ArrayList<WithdrawalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            WithdrawalEventResponse typedResponse = new WithdrawalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static WithdrawalEventResponse getWithdrawalEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(WITHDRAWAL_EVENT, log);
        WithdrawalEventResponse typedResponse = new WithdrawalEventResponse();
        typedResponse.log = log;
        typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<WithdrawalEventResponse> withdrawalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getWithdrawalEventFromLog(log));
    }

    public Flowable<WithdrawalEventResponse> withdrawalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(WITHDRAWAL_EVENT));
        return withdrawalEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> balances(String param0) {
        final Function function = new Function(FUNC_BALANCES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> deposit(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_DEPOSIT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<BigInteger> getBalance(String user) {
        final Function function = new Function(FUNC_GETBALANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, user)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> oracle() {
        final Function function = new Function(FUNC_ORACLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> purchase(String asset, BigInteger amount) {
        final Function function = new Function(
                FUNC_PURCHASE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(asset), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> sell(String asset, BigInteger amount) {
        final Function function = new Function(
                FUNC_SELL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(asset), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> withdraw(BigInteger amount) {
        final Function function = new Function(
                FUNC_WITHDRAW, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Wallet load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Wallet(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Wallet load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Wallet(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Wallet load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Wallet(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Wallet load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Wallet(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Wallet> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String oracleAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, oracleAddress)));
        return deployRemoteCall(Wallet.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<Wallet> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String oracleAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, oracleAddress)));
        return deployRemoteCall(Wallet.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Wallet> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String oracleAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, oracleAddress)));
        return deployRemoteCall(Wallet.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Wallet> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String oracleAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, oracleAddress)));
        return deployRemoteCall(Wallet.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    public static void linkLibraries(List<Contract.LinkReference> references) {
        librariesLinkedBinary = linkBinaryWithReferences(BINARY, references);
    }

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }

    public static class DepositEventResponse extends BaseEventResponse {
        public String user;

        public BigInteger amount;
    }

    public static class PurchaseEventResponse extends BaseEventResponse {
        public String user;

        public String asset;

        public BigInteger amount;
    }

    public static class SaleEventResponse extends BaseEventResponse {
        public String user;

        public String asset;

        public BigInteger amount;
    }

    public static class WithdrawalEventResponse extends BaseEventResponse {
        public String user;

        public BigInteger amount;
    }
}
