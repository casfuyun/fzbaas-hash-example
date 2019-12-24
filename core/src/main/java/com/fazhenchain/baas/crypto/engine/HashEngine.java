package com.fazhenchain.baas.crypto.engine;

/**
 * @author simon
 */
public interface HashEngine {
    byte[] sha256(byte[] input);

    byte[] sha3(byte[] input);

    byte[] keccak256(byte[] input);

    byte[] sm3(byte[] input);
}
