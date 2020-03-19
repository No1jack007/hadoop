package com.zhang.hadoop.testMain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhang.hadoop.util.BASE64Encoder;
import com.zhang.hadoop.util.Base64;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.json.JsonSerializer;
import org.springframework.util.StringUtils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhang yufei
 * @create: 2020-03-18 17:18
 **/
public class Test1 {

    public static void main(String args[]) {
        long start = System.currentTimeMillis();
        boolean flag=true;
        while (flag){
            try {
                Thread.sleep(10*1000);
                send();
                flag=false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("完成" + (end - start));
    }

    public static void send() throws Exception{
        String data="[{\"driveMode\":null,\"offlineProductionTime\":\"2018-12-01 18:30:35\",\"packList\":[{\"autofactoryCode\":\"VEHICLE12345678901\",\"autofactoryName\":\"主机厂A\",\"batteryTypeCode\":\"\",\"cellCodeSize\":0,\"code\":\"TEST101WCB-014\",\"createBy\":\"\",\"createTime\":\"2020-03-18 16:04:05\",\"factModelCode\":\"\",\"factoryCode\":\"\",\"factorySystem\":\"\",\"id\":\"2aa10d9ac8bc4b7ab2e19da7306bfd95\",\"manufactoryCode\":\"TEST11223344556677\",\"manufactoryName\":\"测试厂商\",\"modelId\":\"\",\"moduleCodeSize\":3,\"moduleList\":[{\"autofactoryCode\":\"VEHICLE12345678901\",\"autofactoryName\":\"主机厂A\",\"batteryTypeCode\":null,\"cellCodeList\":\"[]\",\"cellCodeSize\":0,\"cellList\":[],\"cellModelId\":\"\",\"cellSpecification\":null,\"code\":\"TEST101WCM-040\",\"createBy\":\"\",\"createTime\":\"2020-03-18 16:04:05\",\"factModelCode\":null,\"factoryCode\":null,\"id\":\"2374159563e446d09cf0319205b01a75\",\"manufactoryCode\":\"TEST11223344556677\",\"manufactoryName\":\"测试厂商\",\"modelId\":\"\",\"packId\":\"TEST101WCB-014\",\"productionDate\":\"\",\"specification\":null,\"tails\":{},\"updateBy\":null,\"updateTime\":null},{\"autofactoryCode\":\"VEHICLE12345678901\",\"autofactoryName\":\"主机厂A\",\"batteryTypeCode\":null,\"cellCodeList\":\"[]\",\"cellCodeSize\":0,\"cellList\":[],\"cellModelId\":\"\",\"cellSpecification\":null,\"code\":\"TEST101WCM-042\",\"createBy\":\"\",\"createTime\":\"2020-03-18 16:04:05\",\"factModelCode\":null,\"factoryCode\":null,\"id\":\"8640164fb2314bb195918d436ae9c954\",\"manufactoryCode\":\"TEST11223344556677\",\"manufactoryName\":\"测试厂商\",\"modelId\":\"\",\"packId\":\"TEST101WCB-014\",\"productionDate\":\"\",\"specification\":null,\"tails\":{},\"updateBy\":null,\"updateTime\":null},{\"autofactoryCode\":\"VEHICLE12345678901\",\"autofactoryName\":\"主机厂A\",\"batteryTypeCode\":null,\"cellCodeList\":\"[]\",\"cellCodeSize\":0,\"cellList\":[],\"cellModelId\":\"\",\"cellSpecification\":null,\"code\":\"TEST101WCM-041\",\"createBy\":\"\",\"createTime\":\"2020-03-18 16:04:05\",\"factModelCode\":null,\"factoryCode\":null,\"id\":\"b3bf9410046149d19c60a853e130fd79\",\"manufactoryCode\":\"TEST11223344556677\",\"manufactoryName\":\"测试厂商\",\"modelId\":\"\",\"packId\":\"TEST101WCB-014\",\"productionDate\":\"\",\"specification\":null,\"tails\":{},\"updateBy\":null,\"updateTime\":null}],\"orderCode\":\"\",\"productionDate\":\"\",\"serial\":\"\",\"specification\":\"\",\"syncState\":\"2\",\"systemId\":\"\",\"tails\":{},\"updateBy\":null,\"updateTime\":null,\"vin\":\"TESTWCWCK55HE0132\"},{\"autofactoryCode\":\"VEHICLE12345678901\",\"autofactoryName\":\"主机厂A\",\"batteryTypeCode\":\"\",\"cellCodeSize\":0,\"code\":\"TEST101WCB-015\",\"createBy\":\"\",\"createTime\":\"2020-03-18 16:04:05\",\"factModelCode\":\"\",\"factoryCode\":\"\",\"factorySystem\":\"\",\"id\":\"2fc0608ef73e4d54a24ba60954dc4f04\",\"manufactoryCode\":\"TEST11223344556677\",\"manufactoryName\":\"测试厂商\",\"modelId\":\"\",\"moduleCodeSize\":3,\"moduleList\":[{\"autofactoryCode\":\"VEHICLE12345678901\",\"autofactoryName\":\"主机厂A\",\"batteryTypeCode\":null,\"cellCodeList\":\"[]\",\"cellCodeSize\":0,\"cellList\":[],\"cellModelId\":\"\",\"cellSpecification\":null,\"code\":\"TEST101WCM-044\",\"createBy\":\"\",\"createTime\":\"2020-03-18 16:04:05\",\"factModelCode\":null,\"factoryCode\":null,\"id\":\"4cb62fc0c484494e86cbdd5fd4b3e4bc\",\"manufactoryCode\":\"TEST11223344556677\",\"manufactoryName\":\"测试厂商\",\"modelId\":\"\",\"packId\":\"TEST101WCB-015\",\"productionDate\":\"\",\"specification\":null,\"tails\":{},\"updateBy\":null,\"updateTime\":null},{\"autofactoryCode\":\"VEHICLE12345678901\",\"autofactoryName\":\"主机厂A\",\"batteryTypeCode\":null,\"cellCodeList\":\"[]\",\"cellCodeSize\":0,\"cellList\":[],\"cellModelId\":\"\",\"cellSpecification\":null,\"code\":\"TEST101WCM-045\",\"createBy\":\"\",\"createTime\":\"2020-03-18 16:04:05\",\"factModelCode\":null,\"factoryCode\":null,\"id\":\"8a56d0dc1f8f4a4bb6eba08240730f3b\",\"manufactoryCode\":\"TEST11223344556677\",\"manufactoryName\":\"测试厂商\",\"modelId\":\"\",\"packId\":\"TEST101WCB-015\",\"productionDate\":\"\",\"specification\":null,\"tails\":{},\"updateBy\":null,\"updateTime\":null},{\"autofactoryCode\":\"VEHICLE12345678901\",\"autofactoryName\":\"主机厂A\",\"batteryTypeCode\":null,\"cellCodeList\":\"[]\",\"cellCodeSize\":0,\"cellList\":[],\"cellModelId\":\"\",\"cellSpecification\":null,\"code\":\"TEST101WCM-043\",\"createBy\":\"\",\"createTime\":\"2020-03-18 16:04:05\",\"factModelCode\":null,\"factoryCode\":null,\"id\":\"f6ab95b6fba0486095419b6ad2387dd6\",\"manufactoryCode\":\"TEST11223344556677\",\"manufactoryName\":\"测试厂商\",\"modelId\":\"\",\"packId\":\"TEST101WCB-015\",\"productionDate\":\"\",\"specification\":null,\"tails\":{},\"updateBy\":null,\"updateTime\":null}],\"orderCode\":\"\",\"productionDate\":\"\",\"serial\":\"\",\"specification\":\"\",\"syncState\":\"2\",\"systemId\":\"\",\"tails\":{},\"updateBy\":null,\"updateTime\":null,\"vin\":\"TESTWCWCK55HE0132\"},{\"autofactoryCode\":\"VEHICLE12345678901\",\"autofactoryName\":\"主机厂A\",\"batteryTypeCode\":\"\",\"cellCodeSize\":0,\"code\":\"TEST101WCB-013\",\"createBy\":\"\",\"createTime\":\"2020-03-18 16:04:05\",\"factModelCode\":\"\",\"factoryCode\":\"\",\"factorySystem\":\"\",\"id\":\"fd78d4627aac4cc1a33382ed1289ee84\",\"manufactoryCode\":\"TEST11223344556677\",\"manufactoryName\":\"测试厂商\",\"modelId\":\"\",\"moduleCodeSize\":3,\"moduleList\":[{\"autofactoryCode\":\"VEHICLE12345678901\",\"autofactoryName\":\"主机厂A\",\"batteryTypeCode\":null,\"cellCodeList\":\"[]\",\"cellCodeSize\":0,\"cellList\":[],\"cellModelId\":\"\",\"cellSpecification\":null,\"code\":\"TEST101WCM-038\",\"createBy\":\"\",\"createTime\":\"2020-03-18 16:04:05\",\"factModelCode\":null,\"factoryCode\":null,\"id\":\"526d7f4618b14ed79162285b98093d1d\",\"manufactoryCode\":\"TEST11223344556677\",\"manufactoryName\":\"测试厂商\",\"modelId\":\"\",\"packId\":\"TEST101WCB-013\",\"productionDate\":\"\",\"specification\":null,\"tails\":{},\"updateBy\":null,\"updateTime\":null},{\"autofactoryCode\":\"VEHICLE12345678901\",\"autofactoryName\":\"主机厂A\",\"batteryTypeCode\":null,\"cellCodeList\":\"[]\",\"cellCodeSize\":0,\"cellList\":[],\"cellModelId\":\"\",\"cellSpecification\":null,\"code\":\"TEST101WCM-039\",\"createBy\":\"\",\"createTime\":\"2020-03-18 16:04:05\",\"factModelCode\":null,\"factoryCode\":null,\"id\":\"5e32149eb76b4df59263113ff8bea68d\",\"manufactoryCode\":\"TEST11223344556677\",\"manufactoryName\":\"测试厂商\",\"modelId\":\"\",\"packId\":\"TEST101WCB-013\",\"productionDate\":\"\",\"specification\":null,\"tails\":{},\"updateBy\":null,\"updateTime\":null},{\"autofactoryCode\":\"VEHICLE12345678901\",\"autofactoryName\":\"主机厂A\",\"batteryTypeCode\":null,\"cellCodeList\":\"[]\",\"cellCodeSize\":0,\"cellList\":[],\"cellModelId\":\"\",\"cellSpecification\":null,\"code\":\"TEST101WCM-037\",\"createBy\":\"\",\"createTime\":\"2020-03-18 16:04:05\",\"factModelCode\":null,\"factoryCode\":null,\"id\":\"5f62c40bae4d430c9c60bbc5f8661b67\",\"manufactoryCode\":\"TEST11223344556677\",\"manufactoryName\":\"测试厂商\",\"modelId\":\"\",\"packId\":\"TEST101WCB-013\",\"productionDate\":\"\",\"specification\":null,\"tails\":{},\"updateBy\":null,\"updateTime\":null}],\"orderCode\":\"\",\"productionDate\":\"\",\"serial\":\"\",\"specification\":\"\",\"syncState\":\"2\",\"systemId\":\"\",\"tails\":{},\"updateBy\":null,\"updateTime\":null,\"vin\":\"TESTWCWCK55HE0132\"}],\"vehModelName\":\"TEST01车\",\"vehTypeName\":null,\"vehicleBrand\":\"宝马\",\"vehicleName\":\"纯电动轿车\",\"vehicleType\":\"2\",\"vin\":\"TESTWCWCK55HE0132\"}]";
        Map<String, Object> vinMap = new HashMap<>();
        vinMap.put("vin", "123456");
        vinMap.put("vehModelName", "vehModel01");

        vinMap.put("systemCode", "S001");
        vinMap.put("offlineProductionTime", "2018-01-01 00:00:00");
        vinMap.put("vehicleBrand", "奔驰");
        vinMap.put("vehicleName", "奔驰");
        vinMap.put("vehicleType", "1");

        List<String> cellList1=new ArrayList<>();
        for(int i=0;i<5000000;i++){
            cellList1.add("C111"+i);
        }
        Map<String,Object> module1=new HashMap<>();
        module1.put("code","M110");
        module1.put("cellList",cellList1);
        module1.put("modelId","zhangM");
        module1.put("cellModelId","zhangC");
        List<Map<String,Object>> moduleList1=new ArrayList<>();
        moduleList1.add(module1);
        Map<String, Object> pack1 = new HashMap<>();
        pack1.put("code", "P100");
        pack1.put("moduleList",moduleList1);
        pack1.put("serial", "1");
        pack1.put("modelId", "1号包");
        pack1.put("systemId", "S001");
        pack1.put("systemModelId", "zhangS");
        pack1.put("orderNo", "o001");
        List<Map<String, Object>> packList = new ArrayList<>();
        packList.add(pack1);

        vinMap.put("packCodeList", packList);

        List<Map<String, Object>> vinList = new ArrayList<>();
        vinList.add(vinMap);
        String url = "http://evmam-tbrat.com:9000/bitnei/v1.0/battery/receiveVehicleOld";
//        String url = "http://61.149.8.148:9000/bitnei/v1.0/battery/receiveVehicleOld";
        String token="L4TrJ4ZXjGbjcQ2EOUkiBBDP9A8ZtS/U";
        String key="fJqH4hYBwUf5hft5";
        Map<String, Object> requestMap = new HashMap<String, Object>();
//        String enStr = encrypt(data, key);
        String enStr = encrypt(new JsonSerializer().deep(true).serialize(vinList), key);
//        System.out.println(decrypt(enStr,key));
        requestMap.put("requestMsg", enStr);
        requestMap.put("timestamp", System.currentTimeMillis());
        String sign = null;
            sign = byteArrayToHexString(encryptHMAC(enStr.getBytes(), key));
            requestMap.put("sign", sign);
            HttpResponse response = getRequest(url, token, requestMap).send();
            response.unzip();
            ObjectMapper mapper = new ObjectMapper();
            Map map = mapper.readValue(response.body(), Map.class);
            String result = "";
            if (map.get("data")!=null) {
                result = decrypt(map.get("data").toString(), key);
            } else {
                result = decrypt(map.get("msg").toString(), key);
            }
            System.out.println(map.get("code") + "" + result);
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
            BASE64Encoder coder = new BASE64Encoder();
            coder.encode(enCodeFormat);
            Cipher cipher = Cipher.getInstance("AES");
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(1, key);
            byte[] result = cipher.doFinal(byteContent);
            String str = Base64.encode(result);
            return str;
        } catch (NoSuchAlgorithmException var13) {
            var13.printStackTrace();
        } catch (NoSuchPaddingException var14) {
            var14.printStackTrace();
        } catch (InvalidKeyException var15) {
            var15.printStackTrace();
        } catch (UnsupportedEncodingException var16) {
            var16.printStackTrace();
        } catch (IllegalBlockSizeException var17) {
            var17.printStackTrace();
        } catch (BadPaddingException var18) {
            var18.printStackTrace();
        }
        return null;
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
            var10.printStackTrace();
        } catch (NoSuchPaddingException var11) {
            var11.printStackTrace();
        } catch (InvalidKeyException var12) {
            var12.printStackTrace();
        } catch (IllegalBlockSizeException var13) {
            var13.printStackTrace();
        } catch (BadPaddingException var14) {
            var14.printStackTrace();
        } catch (Exception var15) {
            var15.printStackTrace();
        }
        return "";
    }

    public static String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        byte[] var2 = bytes;
        int var3 = bytes.length;
        for (int var4 = 0; var4 < var3; ++var4) {
            byte aByte = var2[var4];
            int v = aByte & 255;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString();
    }

    public static byte[] encryptHMAC(byte[] data, String key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "HmacMD5");
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return mac.doFinal(data);
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
