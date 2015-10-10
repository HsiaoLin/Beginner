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

public class AESDec {
    public static String decode(String key, String s) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException
    {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//        String key = "N6AG2WHLH74S5WC5";
        
    	byte[] iv = new byte[16];
    	IvParameterSpec ivSpec = new IvParameterSpec(iv);          	
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"), ivSpec);
        return new String(cipher.doFinal(Base64New.decode(s)/*Base64.decode(s)*/), "UTF8");            
    }
}
