package com.fazhenchain.baas.crypto.example;

import com.fazhenchain.baas.crypto.HashUtil;
import com.fazhenchain.baas.crypto.engine.BcHashEngine;
import com.fazhenchain.baas.crypto.engine.BcHexEngine;
import com.fazhenchain.baas.crypto.engine.CommonsCodecHexCodecEngine;
import com.fazhenchain.baas.crypto.engine.FabricHashEngine;
import com.fazhenchain.baas.crypto.engine.JcaHashEngine;
import com.fazhenchain.baas.crypto.engine.Web3HashEngine;
import com.fazhenchain.baas.crypto.engine.Web3HexCodecEngine;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;
import java.util.HashMap;
import java.util.Map;

public class HashUtilCheck {

    private Map<String, HashUtil> hashUtilMap = new HashMap<>(5);
    private byte[] input;
    public static final String JCA = "JCA";
    public static final String BC = "BC";
    public static final String WEB3 = "Web3";
    public static final String FABRIC = "Fabric";

    private void init(byte[] input) {
        Security.addProvider(new BouncyCastleProvider());
        HashUtil jcaHashUtil = new HashUtil(new JcaHashEngine(), new CommonsCodecHexCodecEngine());
        HashUtil bcHashUtil = new HashUtil(new BcHashEngine(), new BcHexEngine());
        HashUtil web3HashUtil = new HashUtil(new Web3HashEngine(), new Web3HexCodecEngine());
        HashUtil fabricHashUtil = new HashUtil(new FabricHashEngineForTest(), new CommonsCodecHexCodecEngine());
        hashUtilMap.put(JCA, jcaHashUtil);
        hashUtilMap.put(BC, bcHashUtil);
        hashUtilMap.put(WEB3, web3HashUtil);
        hashUtilMap.put(FABRIC, fabricHashUtil);
        this.input = input;
    }

    private void checkSha256(String type) {
        byte[] hash;
        String hashString;
        HashUtil hashUtil = hashUtilMap.get(type);
        hash = hashUtil.sha256(input);
        hashString = hashUtil.toHexString(hash);
        System.out.println(formatOut(type, "SHA-256", hashString));
    }

    private void checkSha3(String type) {
        byte[] hash;
        String hashString;
        HashUtil hashUtil = hashUtilMap.get(type);
        hash = hashUtil.sha3(input);
        hashString = hashUtil.toHexString(hash);
        System.out.println(formatOut(type, "SHA3-256", hashString));
    }

    private void checkSm3(String type) {
        byte[] hash;
        String hashString;
        HashUtil hashUtil = hashUtilMap.get(type);
        hash = hashUtil.sm3(input);
        hashString = hashUtil.toHexString(hash);
        System.out.println(formatOut(type, "SM3", hashString));
    }

    private void checkKeccak256(String type) {
        byte[] hash;
        String hashString;
        HashUtil hashUtil = hashUtilMap.get(type);
        hash = hashUtil.keccak256(input);
        hashString = hashUtil.toHexString(hash);
        System.out.println(formatOut(type, "KECCAK-256", hashString));
    }

    private String formatOut(String type, String algorithm, String hash) {
        return String.format("%6s %10s %32s", type, algorithm, hash);
    }

    public static void main(String[] args) {
        byte[] plaintext = "plaintext".getBytes();

        HashUtilCheck check = new HashUtilCheck();
        check.init(plaintext);

        // SHA-256
        check.checkSha256(JCA);
        check.checkSha256(BC);
        check.checkSha256(WEB3);
        check.checkSha256(FABRIC);

        // SHA3-256
        check.checkSha3(JCA);
        check.checkSha3(BC);
        check.checkSha3(FABRIC);

        // SM3
        check.checkSm3(JCA);
        check.checkSm3(BC);

        // KECCAK-256
        check.checkKeccak256(JCA);
        check.checkKeccak256(BC);
        check.checkKeccak256(WEB3);
    }

    static class FabricHashEngineForTest extends FabricHashEngine {
        FabricHashEngineForTest() {
            forTest();
        }
    }

}
