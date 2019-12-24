package com.fazhenchain.baas.crypto.engine;

import org.web3j.crypto.Hash;

/**
 * @author simon
 */
public class Web3HashEngine implements HashEngine {
    @Override
    public byte[] sha256(byte[] input) {
        return Hash.sha256(input);
    }

    @Override
    public byte[] sha3(byte[] input) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte[] keccak256(byte[] input) {
        return Hash.sha3(input);
    }

    @Override
    public byte[] sm3(byte[] input) {
        throw new UnsupportedOperationException();
    }
}
