package com.fazhenchain.baas.crypto.engine;

import org.web3j.utils.Numeric;

/**
 * @author simon
 */
public class Web3HexCodecEngine implements HexCodecEngine {
    @Override
    public String toHexString(byte[] input) {
        return Numeric.toHexString(input);
    }

    @Override
    public byte[] fromHexString(String hexString) {
        return Numeric.hexStringToByteArray(hexString);
    }
}
