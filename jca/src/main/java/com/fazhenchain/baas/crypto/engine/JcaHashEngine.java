package com.fazhenchain.baas.crypto.engine;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author simon
 */
public class JcaHashEngine implements HashEngine {
    @Override
    public byte[] sha256(byte[] input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(input);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    @Override
    public byte[] sha3(byte[] input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA3-256");
            return md.digest(input);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    @Override
    public byte[] keccak256(byte[] input) {
        try {
            MessageDigest md = MessageDigest.getInstance("KECCAK-256");
            return md.digest(input);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    @Override
    public byte[] sm3(byte[] input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SM3");
            return md.digest(input);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

}
