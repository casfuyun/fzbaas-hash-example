package com.fazhenchain.baas.crypto;

import com.fazhenchain.baas.crypto.engine.HashEngine;
import com.fazhenchain.baas.crypto.engine.HexCodecEngine;

/**
 * @author simon
 */
public class HashUtil implements HashEngine, HexCodecEngine {
    private final HashEngine hashEngine;
    private final HexCodecEngine hexCodecEngine;

    public HashUtil(HashEngine hashEngine, HexCodecEngine hexCodecEngine) {
        this.hashEngine = hashEngine;
        this.hexCodecEngine = hexCodecEngine;
    }

    @Override
    public byte[] sha256(byte[] input) {
        return hashEngine.sha256(input);
    }

    @Override
    public byte[] sha3(byte[] input) {
        return hashEngine.sha3(input);
    }

    @Override
    public byte[] keccak256(byte[] input) {
        return hashEngine.keccak256(input);
    }

    @Override
    public byte[] sm3(byte[] input) {
        return hashEngine.sm3(input);
    }

    public boolean validHash(byte[] hash) {
        return hash != null && hash.length == HASH_BYTE_LENGTH;
    }

    /**
     * the hash code of the block-chain must be 256bits.
     */
    public static final int HASH_BYTE_LENGTH = 32;

    @Override
    public String toHexString(byte[] input) {
        if (!validHash(input)) {
            throw new IllegalArgumentException("Invalid hash value. Must be 32 bytes.");
        }
        return hexCodecEngine.toHexString(input);
    }

    public static final String HEX_PREFIX = "0x";

    @Override
    public byte[] fromHexString(String hexString) {
        if (hexString == null || !hexString.startsWith(HEX_PREFIX)) {
            throw new IllegalArgumentException("Invalid hex string. Must be start with '0x'.");
        }
        return hexCodecEngine.fromHexString(hexString);
    }

}
