package com.ran.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESEnc {
    public static String encode(String key, String s) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException
    {
//        String key = "N6AG2WHLH74S5WC5";
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

    	byte[] iv = new byte[16];
    	IvParameterSpec ivSpec = new IvParameterSpec(iv);  
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"), ivSpec);
        return Base64New.encode_str(cipher.doFinal(s.getBytes("UTF-8")));
    }
}
