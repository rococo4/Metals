// SPDX-License-Identifier: MIT
pragma solidity ^0.7.3;

import "./Oracle.sol";

contract Wallet {
    Oracle public oracle;
    mapping(address => uint256) public balances;

    event Deposit(address indexed user, uint256 amount);
    event Withdrawal(address indexed user, uint256 amount);
    event Purchase(address indexed user, string asset, uint256 amount);
    event Sale(address indexed user, string asset, uint256 amount);

    constructor(address oracleAddress) {
        oracle = Oracle(oracleAddress);
    }

    function deposit() public payable {
        balances[msg.sender] += msg.value;
        emit Deposit(msg.sender, msg.value);
    }

    function withdraw(uint256 amount) public {
        require(balances[msg.sender] >= amount, "Insufficient balance");
        balances[msg.sender] -= amount;
        payable(msg.sender).transfer(amount);
        emit Withdrawal(msg.sender, amount);
    }

    function purchase(string memory asset, uint256 amount) public {
        uint256 rate = oracle.getRate(asset);
        uint256 cost = amount * rate;
        require(balances[msg.sender] >= cost, "Insufficient balance");

        balances[msg.sender] -= cost;
        emit Purchase(msg.sender, asset, amount);
    }

    function sell(string memory asset, uint256 amount) public {
        uint256 rate = oracle.getRate(asset);
        uint256 revenue = amount * rate;
        
        balances[msg.sender] += revenue;
        emit Sale(msg.sender, asset, amount);
    }
    function getBalance(address user) public view returns (uint256) {
        return balances[user];
    }
}
