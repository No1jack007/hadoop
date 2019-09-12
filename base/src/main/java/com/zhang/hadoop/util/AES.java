package com.zhang.hadoop.util;

import jodd.util.StringUtil;
import sun.misc.BASE64Decoder;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class AES {

    public static String AES_KEY = "NAT@)!%";
    /**
     * 加密,加密的结果经过Base64处理
     *
     * @param content 需要加密的内容
     * @return
     */
    public static String encrypt(String content,String slat) {
        try {
            if (StringUtil.isEmpty(content))
                return "";

            String password = AES_KEY+slat;
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            String str = Base64.encode(result);
            return str; // 加密
        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
//            e.printStackTrace();
        } catch (InvalidKeyException e) {
//            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
//            e.printStackTrace();
        } catch (BadPaddingException e) {
//            e.printStackTrace();
        }
        return null;
    }




    /**
     * 解密
     * @param str 待解密内容
     * @return
     */
    public static String decrypt(String str,String slat) {
        try {
            String password = AES_KEY+slat;
            byte[] content = Base64.decode(str);
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(content);
            return new String(result); // 加密
        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
//            e.printStackTrace();
        } catch (InvalidKeyException e) {
//            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
//            e.printStackTrace();
        } catch (BadPaddingException e) {
//            e.printStackTrace();
        }catch (Exception e){
//            e.printStackTrace();
        }
        return "";
    }



    /**解密
     * @param str 待解密内容
     * @return
     */
    public static String decryptA(String str,String slat) {
        try {
            String password = "1234567890abcdef";
            byte[] content = Base64.decode(str);
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "1234567890abcdef");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(content);
            return new String(result); // 加密
        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
//            e.printStackTrace();
        } catch (InvalidKeyException e) {
//            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
//            e.printStackTrace();
        } catch (BadPaddingException e) {
//            e.printStackTrace();
        }catch (Exception e){
//            e.printStackTrace();
        }
        return "";
    }


    public static String decryptCBC(String sSrc) throws Exception {
        try {
            String sKey = "1234567890abcdef";
            String ivParameter = "1234567890abcdef";
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);//先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original,"utf-8");
            return originalString;
        } catch (Exception ex) {
            return null;
        }
    }


//   public static void main(String[] argc) throws Exception {
//
//        String re = "lRb4xDglBxCAs3tQZco+fzaVXwsAZV86VY/7ZCCTNud15cbx2KDbUHuymp2juP2F";
//        String s = decrypt(re,"test123");
//        System.out.println(s);
//    }





    /**将二进制转换成16进制
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * 加密
     *
     * @param content 需要加密的内容
     * @param password  加密密码
     * @return
     */
    public static byte[] encrypt2(String content, String password) {
        try {
            SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 新加密
     * @param content
     * @return
     */
    public static String encryptNew(String content,String recKey) {
        try {
            if (StringUtil.isEmpty(content))
                return "";

            String password = recKey;
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            String str = Base64.encode(result);
            return str; // 加密
        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
//            e.printStackTrace();
        } catch (InvalidKeyException e) {
//            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
//            e.printStackTrace();
        } catch (BadPaddingException e) {
//            e.printStackTrace();
        }
        return null;
    }

    /**
     * 新解密
     * @param str
     * @return
     */
    public static String decryptNew(String str,String recKey) {
        try {
            String password = recKey;
            byte[] content = Base64.decode(str);
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(content);
            return new String(result); // 加密
        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
//            e.printStackTrace();
        } catch (InvalidKeyException e) {
//            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
//            e.printStackTrace();
        } catch (BadPaddingException e) {
//            e.printStackTrace();
        }catch (Exception e){
//            e.printStackTrace();
        }
        return "";
    }

}