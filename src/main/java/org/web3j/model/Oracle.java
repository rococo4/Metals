package org.web3j.model;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
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
public class Oracle extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50600080546001600160a01b03191633179055610407806100326000396000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c80631e77b2e0146100515780636c0daed8146100fb578063705f964b146101b35780638da5cb5b14610259575b600080fd5b6100f96004803603604081101561006757600080fd5b81019060208101813564010000000081111561008257600080fd5b82018360208201111561009457600080fd5b803590602001918460018302840111640100000000831117156100b657600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550509135925061027d915050565b005b6101a16004803603602081101561011157600080fd5b81019060208101813564010000000081111561012c57600080fd5b82018360208201111561013e57600080fd5b8035906020019184600183028401116401000000008311171561016057600080fd5b91908080601f01602080910402602001604051908101604052809392919081815260200183838082843760009201919091525092955061033d945050505050565b60408051918252519081900360200190f35b6101a1600480360360208110156101c957600080fd5b8101906020810181356401000000008111156101e457600080fd5b8201836020820111156101f657600080fd5b8035906020019184600183028401116401000000008311171561021857600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295506103a5945050505050565b6102616103c2565b604080516001600160a01b039092168252519081900360200190f35b6000546001600160a01b031633146102d5576040805162461bcd60e51b81526020600482015260166024820152752737ba103a34329031b7b73a3930b1ba1037bbb732b960511b604482015290519081900360640190fd5b806001836040518082805190602001908083835b602083106103085780518252601f1990920191602091820191016102e9565b51815160209384036101000a600019018019909216911617905292019485525060405193849003019092209290925550505050565b60006001826040518082805190602001908083835b602083106103715780518252601f199092019160209182019101610352565b51815160209384036101000a6000190180199092169116179052920194855250604051938490030190922054949350505050565b805160208183018101805160018252928201919093012091525481565b6000546001600160a01b03168156fea2646970667358221220b69708e8622aa008f95bbd2c592319f61e0d613f9211e606e6eeb5af537f464964736f6c63430007060033";

    private static String librariesLinkedBinary;

    public static final String FUNC_GETRATE = "getRate";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_RATES = "rates";

    public static final String FUNC_UPDATERATE = "updateRate";

    @Deprecated
    protected Oracle(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Oracle(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Oracle(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Oracle(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<BigInteger> getRate(String asset) {
        final Function function = new Function(FUNC_GETRATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(asset)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> rates(String param0) {
        final Function function = new Function(FUNC_RATES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> updateRate(String asset, BigInteger rate) {
        final Function function = new Function(
                FUNC_UPDATERATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(asset), 
                new org.web3j.abi.datatypes.generated.Uint256(rate)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Oracle load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Oracle(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Oracle load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Oracle(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Oracle load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Oracle(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Oracle load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Oracle(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Oracle> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Oracle.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    public static RemoteCall<Oracle> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Oracle.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<Oracle> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Oracle.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<Oracle> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Oracle.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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
}
