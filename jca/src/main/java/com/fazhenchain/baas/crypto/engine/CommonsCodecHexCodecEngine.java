package com.fazhenchain.baas.crypto.engine;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

/**
 * @author simon
 */
public class CommonsCodecHexCodecEngine implements HexCodecEngine {

    @Override
    public String toHexString(byte[] value) {
        String hexString = Hex.encodeHexString(value, true);
        return "0x" + hexString;
    }


    @Override
    public byte[] fromHexString(String hexString) {
        try {
            return Hex.decodeHex(hexString.replaceFirst("0x", ""));
        } catch (DecoderException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

}
