package com.fazhenchain.baas.crypto.engine;

/**
 * @author simon
 */
public interface HexCodecEngine {
    String toHexString(byte[] input);

    byte[] fromHexString(String hexString);
}
