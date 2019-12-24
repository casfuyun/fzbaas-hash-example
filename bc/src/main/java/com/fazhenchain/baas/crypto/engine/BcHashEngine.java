package com.fazhenchain.baas.crypto.engine;

import org.bouncycastle.jcajce.provider.digest.BCMessageDigest;
import org.bouncycastle.jcajce.provider.digest.Keccak;
import org.bouncycastle.jcajce.provider.digest.SHA256;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.jcajce.provider.digest.SM3;

/**
 * @author simon
 */
public class BcHashEngine implements HashEngine {
    @Override
    public byte[] sha256(byte[] input) {
        BCMessageDigest digest = new SHA256.Digest();
        return digest.digest(input);
    }

    @Override
    public byte[] sha3(byte[] input) {
        BCMessageDigest digest = new SHA3.Digest256();
        return digest.digest(input);
    }

    @Override
    public byte[] keccak256(byte[] input) {
        BCMessageDigest digest = new Keccak.Digest256();
        return digest.digest(input);
    }

    @Override
    public byte[] sm3(byte[] input) {
        BCMessageDigest digest = new SM3.Digest();
        return digest.digest(input);
    }
}
