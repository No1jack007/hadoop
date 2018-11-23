package com.zhang.hadoop.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.json.JsonSerializer;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhang yufei on 2018/10/26.
 */
public class HttpUtil {

    public static HttpResponse send(String url, Object data, String token, String key){
        try {
            Map<String, Object> requestMap = new HashMap<String, Object>();
            String requestMsg = new JsonSerializer().deep(true).serialize(data);
            String enStr = encrypt(requestMsg, key);
            requestMap.put("requestMsg", enStr);
            requestMap.put("timestamp", System.currentTimeMillis());
            String sign = byteArrayToHexString(encryptHMAC(enStr.getBytes(), key));
            requestMap.put("sign", sign);
            HttpResponse response = getRequest(url, token, requestMap).send().unzip().unzip();
            return response;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String parsToMap( HttpResponse response,String key){
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map map = mapper.readValue(response.body(), Map.class);
            String result="";
            if(isNotEmpty(map.get("data"))){
                result = decrypt(map.get("data").toString(), key);
            }else {
                result = decrypt(map.get("msg").toString(), key);
            }
            return "code:"+map.get("code")+""+result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String encrypt(String content, String password) {
        try {
            if(content==null && "".equals(content)) {
                return "";
            }
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            BASE64Encoder coder = new BASE64Encoder();
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

    public static boolean isNotEmpty(Object o) {
        if (o == null) {
            return false;
        }
        if ("".equals(FilterNull(o.toString()))) {
            return false;
        } else {
            return true;
        }
    }

    public static String FilterNull(Object o) {
        return o != null && !"null".equals(o.toString()) ? o.toString().trim() : "" ;
    }

    public static String decrypt(String str, String password) {
        try {
            byte[] content = Base64.decode(str);
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
        } catch (NoSuchAlgorithmException var10) {
            ;
        } catch (NoSuchPaddingException var11) {
            ;
        } catch (InvalidKeyException var12) {
            ;
        } catch (IllegalBlockSizeException var13) {
            ;
        } catch (BadPaddingException var14) {
            ;
        } catch (Exception var15) {
            ;
        }
        return "";
    }

    public static byte[] encryptHMAC(byte[] data, String key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "HmacMD5");
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return mac.doFinal(data);
    }

    public static String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        byte[] var2 = bytes;
        int var3 = bytes.length;
        for(int var4 = 0; var4 < var3; ++var4) {
            byte aByte = var2[var4];
            int v = aByte & 255;
            if(v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString();
    }

    public static HttpRequest getRequest(String url, String accessToken, Map<String, Object> params) {
        HttpRequest request = HttpRequest.post(url);
        request.header("Authorization", "Bearer " + accessToken);
        request.header("Content-Type", "application/json; charset=utf-8");
        request.acceptEncoding("gzip");
        request.body((new JsonSerializer()).serialize(params));
        return request;
    }
}
