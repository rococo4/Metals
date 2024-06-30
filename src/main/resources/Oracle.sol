// SPDX-License-Identifier: MIT
pragma solidity ^0.7.3;

contract Oracle {
    address public owner;
    mapping(string => uint256) public rates;

    modifier onlyOwner() {
        require(msg.sender == owner, "Not the contract owner");
        _;
    }

    constructor() {
        owner = msg.sender;
    }

    function updateRate(string memory asset, uint256 rate) public onlyOwner {
        rates[asset] = rate;
    }

    function getRate(string memory asset) public view returns (uint256) {
        return rates[asset];
    }
}
