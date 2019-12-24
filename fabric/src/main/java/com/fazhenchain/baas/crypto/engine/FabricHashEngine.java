package com.fazhenchain.baas.crypto.engine;

import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.helper.Config;
import org.hyperledger.fabric.sdk.security.CryptoPrimitives;

import java.lang.reflect.Field;
import java.util.Properties;

import static org.hyperledger.fabric.sdk.helper.Config.HASH_ALGORITHM;


/**
 * @author simon
 */
public class FabricHashEngine implements HashEngine {
    @Override
    public byte[] sha256(byte[] input) {
        if (forTest) {
            setHashAlg("SHA2");
        }
        if (isSha3()) {
            throw new UnsupportedOperationException();
        }
        return hash(input);
    }

    @Override
    public byte[] sha3(byte[] input) {
        if (forTest) {
            setHashAlg("SHA3");
        }
        if (!isSha3()) {
            throw new UnsupportedOperationException();
        }
        return hash(input);
    }

    private void setHashAlg(String hashAlg) {
        getSdkProperties().setProperty(HASH_ALGORITHM, hashAlg);
    }

    private static Properties sdkProperties;

    private static Properties getSdkProperties() {
        if (sdkProperties == null) {
            try {
                Config config = Config.getConfig();
                Class<?> clazz = config.getClass();
                Field field = clazz.getDeclaredField("sdkProperties");
                field.setAccessible(true);
                sdkProperties = (Properties) field.get(config);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return sdkProperties;
    }

    @Override
    public byte[] keccak256(byte[] input) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte[] sm3(byte[] input) {
        throw new UnsupportedOperationException();
    }

    private boolean isSha3() {
        return "SHA3".equals(Config.getConfig().getHashAlgorithm());
    }

    private byte[] hash(byte[] input) {
        try {
            CryptoPrimitives cryptoPrimitives = new CryptoPrimitives();
            cryptoPrimitives.init();
            return cryptoPrimitives.hash(input);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvalidArgumentException | CryptoException e) {
            throw new UnsupportedOperationException(e.getMessage(), e);
        }
    }

    /**
     * 注意：仅用可于单线程测试。开启后同时可以使用SHA-256和SHA3-256算法，不支持并发。
     */
    protected void forTest() {
        forTest = true;
    }

    private boolean forTest = false;
}
