package com.zhang.hadoop.util;

import jodd.util.StringUtil;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class AESUtil {

    public AESUtil() {
    }

    public static String encrypt(String content, String password) {
        try {
            if (StringUtils.isEmpty(content)) {
                return "";
            }
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            sun.misc.BASE64Encoder coder = new sun.misc.BASE64Encoder();
            coder.encode(enCodeFormat);
            Cipher cipher = Cipher.getInstance("AES");
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(1, key);
            byte[] result = cipher.doFinal(byteContent);
            String str = Base64.encode(result);
            return str;
        } catch (NoSuchAlgorithmException var13) {
            ;
        } catch (NoSuchPaddingException var14) {
            ;
        } catch (InvalidKeyException var15) {
            ;
        } catch (UnsupportedEncodingException var16) {
            ;
        } catch (IllegalBlockSizeException var17) {
            ;
        } catch (BadPaddingException var18) {
            ;
        }

        return null;
    }

    public static String decrypt(String str, String password) {
        try {
            BASE64Decoder coder = new BASE64Decoder();
            byte[] content = coder.decodeBuffer(str);
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(2, key);
            byte[] result = cipher.doFinal(content);
            return new String(result, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String decryptCBC(String sSrc, String key, String iv) throws Exception {
        try {
            byte[] raw = key.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivps = new IvParameterSpec(iv.getBytes());
            cipher.init(2, skeySpec, ivps);
            byte[] encrypted1 = (new BASE64Decoder()).decodeBuffer(sSrc);
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception var10) {
            return null;
        }
    }

    public static String encryptCBC(String sSrc, String key, String iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = key.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec ivps = new IvParameterSpec(iv.getBytes());
        cipher.init(1, skeySpec, ivps);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
        return (new BASE64Encoder()).encode(encrypted);
    }



}