package com.fazhenchain.baas.crypto.example;

import com.fazhenchain.baas.crypto.HashUtil;
import com.fazhenchain.baas.crypto.engine.CommonsCodecHexCodecEngine;
import com.fazhenchain.baas.crypto.engine.JcaHashEngine;

public class HashUtilExample {
    public static void main(String[] args) {
        byte[] plaintext = "plaintext".getBytes();
        HashUtil hashUtil = new HashUtil(new JcaHashEngine(), new CommonsCodecHexCodecEngine());
        byte[] hash;
        String hashString;
        // SHA-256
        hash = hashUtil.sha256(plaintext);
        hashString = hashUtil.toHexString(hash);
        System.out.println("SHA-256 " + hashString);
    }
}
