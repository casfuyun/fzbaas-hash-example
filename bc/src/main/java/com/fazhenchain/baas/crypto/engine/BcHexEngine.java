package com.fazhenchain.baas.crypto.engine;

import org.bouncycastle.util.encoders.Hex;

/**
 * @author simon
 */
public class BcHexEngine implements HexCodecEngine {
    @Override
    public String toHexString(byte[] input) {
        return "0x" + Hex.toHexString(input);
    }

    @Override
    public byte[] fromHexString(String hexString) {
        return Hex.decodeStrict(hexString.replaceFirst("0x", ""));
    }
}
