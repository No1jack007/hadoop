package com.zhang.hadoop.testMain;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhang.hadoop.util.BASE64Encoder;
import com.zhang.hadoop.util.Base64;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.json.JsonParser;
import jodd.json.JsonSerializer;
import org.springframework.util.StringUtils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

/**
 * @author: zhang yufei
 * @create: 2019-11-19 09:48
 **/
public class TestApi {

    /**
     * 主机厂平台地址
     * 此地址改为系统部署后的映射地址，以下地址位北理工对外地址，可首次测试使用
     */
    public static String ip1 = "http://10.11.9.14:8993";

    //主机厂调用接口令牌，由乙方技术支持提供，且需由甲方提供主机厂全称及统一厂商社会信用代码
    public static String vehicle_token = "C3kNIVJWfOxLkRDydrJXsA4ff6oMaTOP";

    //主机厂调用接口密钥，由乙方技术支持提供
    public static String vehicle_key = "U2TohGfvuck0v5Xa";

    //电池厂调用接口令牌，由乙方技术支持提供，且需由甲方提供电池厂全称及统一厂商社会信用代码
    public static String factory_token = "pOAJJSBmAThb8kPJh4VAX+zs/YAHFqxd";

    //电池厂调用接口密钥，由乙方技术支持提供
    public static String factory_key = "vAREamtPvwanVe2M";

    public static void main(String args[]) {
        long start = System.currentTimeMillis();
        //1.电池厂电池生产
        //createBattery();
        //2.主机厂车辆生产
        //createVehicle();
        //3.主机厂车辆销售
        //createSale();
        //4.电池厂售后
        //createRepairBattery();
        //5.主机厂维修
        //createRepairVehicle();
        //6.电池厂维修
        //createFactoryRepair();
        //7.主机厂回收网点入库
        //createRecoverStorage();
        //8.主机厂回收网点退役
        //createRetireVehicle();
        //9.主机厂车辆换电入库
        //createReplaceBattery();
        //10.主机厂车辆换电记录
        //createReplaceBatteryRecord();
        //11.主机厂车辆换电出库
        //createReplaceStorageOut();
        //12.主机厂车辆换电退役
        //createReplaceRetire();
        //13.电池厂电池退役
        //createRetireFactory();
        //testLogin();
        //请求政府上报数量
        //requestProduceSaleData();
        //testPost();
        testThread();

        long end = System.currentTimeMillis();
        System.out.println("完成" + (end - start));
    }

    public static void createBattery() {
        List<String> cellList1 = new ArrayList<>();
        cellList1.add("C00000000000000000000003");
        Map<String, Object> module1 = new HashMap<>();
        module1.put("code", "M00000000000000000000003");
        module1.put("cellList", cellList1);
        module1.put("modelId", "zhangM");
        module1.put("cellModelId", "zhangC");
        List<Map<String, Object>> moduleList1 = new ArrayList<>();
        moduleList1.add(module1);
        Map<String, Object> pack1 = new HashMap<>();
        pack1.put("code", "P00000000000000000000003");
        pack1.put("moduleList", moduleList1);
        pack1.put("serial", "1");
        pack1.put("modelId", "zhangP");
        pack1.put("systemId", "S001");
        pack1.put("systemModelId", "zhangS");
        pack1.put("orderNo", "o001");
        List<Map<String, Object>> packList = new ArrayList<>();
        packList.add(pack1);
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/vehicle/receiveBatteryProduce", packList, factory_token, factory_key);
        String result = parsToMap(response, factory_key);
        System.out.println(result);
    }

    public static void createVehicle() {
        List<String> packList = new ArrayList<>();
        packList.add("P00000000000000000000003");
        Map<String, Object> vinMap = new HashMap<>();
        vinMap.put("vin", "V0000000000000003");
        vinMap.put("vehModelName", "zhangV");
        vinMap.put("packCodeList", packList);
        vinMap.put("systemCode", "S00000000000000000000000");
        vinMap.put("offlineProductionTime", "2018-01-01 00:00:00");
        vinMap.put("vehicleBrand", "奔驰");
        vinMap.put("vehicleName", "奔驰");
        vinMap.put("vehicleType", "1");
        List<Map<String, Object>> vinList = new ArrayList<>();
        vinList.add(vinMap);
        Map<String, Object> data = new HashMap<>();
        data.put("vinList", vinList);
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/vehicle/receiveVehicleProduce", data, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createSale() {
        List<Map<String, Object>> vehicleList = new ArrayList<>();
        Map<String, Object> vehicle = new HashMap<>();
        vehicle.put("vin", "V0000000000000003");
        vehicle.put("licensePlate", "京BZ0418");
        vehicle.put("vehTypeName", "111808");
        vehicle.put("saleTime", "2018-01-05");
        vehicle.put("saleArea", "北京市昌平区");
        //        vehicle.put("ownerName", "张君宝");
        //        vehicle.put("Idnum","150204197111111810");
        vehicle.put("epname", "吉利汽车");
        vehicle.put("epaddress", "北京市");
        vehicle.put("epcode", "123456789123456789");
        vehicleList.add(vehicle);
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/receiveSaleVehicle", vehicleList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createRepairBattery() {
        Map<String, Object> cell1 = new HashMap<>();
        cell1.put("code", "C00000000000000000000001");
        cell1.put("type", "C");
        List<Map<String, Object>> cellList1 = new ArrayList<>();
        cellList1.add(cell1);
        Map<String, Object> module1 = new HashMap<>();
        module1.put("code", "M00000000000000000000001");
        module1.put("childCodeList", cellList1);
        module1.put("modelId", "zhangM");
        module1.put("type", "M");
        List<Map<String, Object>> moduleList1 = new ArrayList<>();
        moduleList1.add(module1);
        Map<String, Object> pack1 = new HashMap<>();
        pack1.put("code", "P00000000000000000000001");
        pack1.put("childCodeList", moduleList1);
        pack1.put("modelId", "zhangP");
        pack1.put("type", "P");
        pack1.put("orderCode", "R001");
        List<Map<String, Object>> packList = new ArrayList<>();
        packList.add(pack1);
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/repair/receiveRepairBattery", packList, factory_token, factory_key);
        String result = parsToMap(response, factory_key);
        System.out.println(result);
    }

    public static void createRepairVehicle() {
        List<Map<String, Object>> recordList = new ArrayList<>();
        Map<String, Object> record = new HashMap<>();
        record.put("vin", "V0000000000000000");
        record.put("replaceDate", "2018-01-05");
        record.put("oldCode", "P00000000000000000000000");
        record.put("whereaboutsId", "123456789123456789");
        record.put("whereaboutsName", "维修厂");
        record.put("newCode", "P00000000000000000000001");
        record.put("batterySpecies", "P");
        recordList.add(record);
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/repair/receiveRepairVehicle", recordList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createFactoryRepair() {
        //电池单体
        Map<String, Object> cell2 = new HashMap<String, Object>();
        cell2.put("code", "C311R2");
        cell2.put("type", "C");
        List<Map<String, Object>> cellList2 = new ArrayList<Map<String, Object>>();
        cellList2.add(cell2);
        //电池模组
        Map<String, Object> module2 = new HashMap<String, Object>();
        module2.put("code", "M310R2");
        module2.put("childCodeList", cellList2);
        module2.put("modelId", "zhangM");
        module2.put("type", "M");
        module2.put("orderCode", "234567898765");
        List<Map<String, Object>> repairList = new ArrayList<Map<String, Object>>();
        //车辆电池数据集
        Map<String, Object> repair = new HashMap<String, Object>();
        repair.put("vin", "LGWEESK55SHE6CYMD");
        repair.put("replaceDate", "2018-01-05");
        repair.put("oldCode", "LP-HM-1");
        repair.put("whereaboutsId", "123456789123456789");
        repair.put("whereaboutsName", "维修厂");
        repair.put("newCodeBean", module2);
        repair.put("batterySpecies", "M");
        repairList.add(repair);
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/repair/receiveRepairVehicleAdditional", repairList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createReplaceBattery() {
        List<String> cellList1 = new ArrayList<>();
        cellList1.add("C00000000000000000000001");
        Map<String, Object> module1 = new HashMap<>();
        module1.put("code", "M00000000000000000000001");
        module1.put("cellList", cellList1);
        module1.put("modelId", "zhangM");
        module1.put("cellModelId", "zhangC");
        List<Map<String, Object>> moduleList1 = new ArrayList<>();
        moduleList1.add(module1);
        Map<String, Object> pack1 = new HashMap<>();
        pack1.put("code", "P00000000000000000000001");
        pack1.put("inStorageDate", "2018-08-18");
        pack1.put("moduleList", moduleList1);
        pack1.put("modelId", "zhangP");
        pack1.put("replaceUnitCode", "123456789123456789");
        pack1.put("replaceUnitName", "换电厂");

        List<String> cellList2 = new ArrayList<>();
        cellList2.add("C00000000000000000000002");
        Map<String, Object> module2 = new HashMap<>();
        module2.put("code", "M00000000000000000000002");
        module2.put("cellList", cellList2);
        module2.put("modelId", "zhangM");
        module2.put("cellModelId", "zhangC");
        List<Map<String, Object>> moduleList2 = new ArrayList<>();
        moduleList2.add(module2);
        Map<String, Object> pack2 = new HashMap<>();
        pack2.put("code", "P00000000000000000000002");
        pack2.put("inStorageDate", "2018-08-18");
        pack2.put("moduleList", moduleList2);
        pack2.put("modelId", "zhangP");
        pack2.put("replaceUnitCode", "123456789123456789");
        pack2.put("replaceUnitName", "换电厂");

        List<Map<String, Object>> packList = new ArrayList<>();
        packList.add(pack1);
        packList.add(pack2);
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/replaceBattery/receiveReplaceBattery", packList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createReplaceBatteryRecord() {
        Map<String, Object> record1 = new HashMap<>();
        record1.put("vin", "V0000000000000000");
        record1.put("newCode", "P00000000000000000000001");
        record1.put("oldCode", "P00000000000000000000000");
        record1.put("replaceDate", "2020-08-18 17:33:42");
        record1.put("replaceUnitCode", "123456789123456789");
        record1.put("replaceUnitName", "换电厂");
        List<Map<String, Object>> recordList = new ArrayList<>();
        recordList.add(record1);
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/replaceBattery/receiveReplaceBatteryRecord", recordList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createReplaceStorageOut() {
        Map<String, Object> record = new HashMap<>();
        record.put("code", "P00000000000000000000002");
        record.put("storageOutDate", "2020-10-14");
        record.put("replaceUnitCode", "123456789123456789");
        record.put("replaceUnitName", "换电厂");
        record.put("whereaboutsCode", "123456789111111111");
        record.put("whereaboutsName", "换电出库单位");
        List<Map<String, Object>> recordList = new ArrayList<>();
        recordList.add(record);
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/replaceBattery/ReplaceBatteryOut", recordList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createReplaceRetire() {
        Map<String, Object> record = new HashMap<>();
        record.put("code", "P00000000000000000000002");
        record.put("whereaboutsCode", "123456789123412341");
        record.put("whereaboutsName", "维修厂");
        record.put("retireDate", "2018-01-05");
        record.put("batterySpecies", "P");
        record.put("retireUnitCode", "123456789123123123");
        record.put("retireUnitName", "换电退役厂商");
        record.put("factoryType", "4");
        record.put("batteryType", "A");
        record.put("batteryWeight", "2.5");
        List<Map<String, Object>> recordList = new ArrayList<>();
        recordList.add(record);
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/replaceBattery/receiveBatteryRetired", recordList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void requestProduceSaleData() {
        Map<String, Object> request = new HashMap<>();
        request.put("creditCode", "123456");
        HttpResponse response = send("http://127.0.0.1:8004" + "/bitnei/api/governmentData/getProduceSaleData", request, "82454fbeea5840d8897f3a76e6a7df16", "5eafb3f2fcbf4cff", "82229f89b61f4331");
        String result = parsToMap(response, "5eafb3f2fcbf4cff");
        System.out.println(result);
    }

    public static HttpResponse send(String url, Object data, String token, String key) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HttpResponse send(String url, Object data, String token, String key, String signKey) {
        try {
            Map<String, Object> requestMap = new HashMap<String, Object>();
            String requestMsg = new JsonSerializer().deep(true).serialize(data);
            String enStr = encrypt(requestMsg, key);
            requestMap.put("requestMsg", enStr);
            requestMap.put("timestamp", System.currentTimeMillis());
            String sign = byteArrayToHexString(encryptHMAC(("requestMsg" + requestMap.get("requestMsg") + "timestamp" + requestMap.get("timestamp")).getBytes(), signKey));
            requestMap.put("sign", sign);
            HttpResponse response = getRequest(url, token, requestMap).send().unzip().unzip();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String parsToMap(HttpResponse response, String key) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map map = mapper.readValue(response.body(), Map.class);
            String result = "";
            if (isNotEmpty(map.get("data"))) {
                result = decrypt(map.get("data").toString(), key);
            } else {
                result = decrypt(map.get("msg").toString(), key);
            }
            return "code:" + map.get("code") + "" + result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
        return o != null && !"null".equals(o.toString()) ? o.toString().trim() : "";
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

    public static HttpRequest getRequest(String url, String accessToken, Map<String, Object> params) {
        HttpRequest request = HttpRequest.post(url);
        request.header("Authorization", "Bearer " + accessToken);
        request.header("Content-Type", "application/json;charset=utf-8");
        request.acceptEncoding("gzip");
        request.body((new JsonSerializer()).serialize(params));
        //        System.out.println((new JsonSerializer()).serialize(params));
        return request;
    }

    public static void testThread() {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> testPost());
            thread.start();
        }
    }

    public static void testPost() {
        //String url1 = "http://127.0.0.1:8080/delay/message/receive";
        String url1 = "http://op-delayedservice-delaymessage.zpidc.com/delay/message/receive";
        for (int i = 0; i < 300; i++) {
            JSONObject param = new JSONObject();
            param.put("businessKey", "mot_growth");
            param.put("content", "test");
            param.put("msgForwardingConfigId", 1);
            JSONArray jsonArray = new JSONArray();
            jsonArray.add("2021-04-30 11:11:11");
            param.put("sendTimeList", jsonArray);
            send(url1, param);
        }
    }

    public static void send(String url1, JSONObject param) {
        try {
            //创建连接
            URL url = new URL(url1);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();
            // POST请求
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            String json = param.toString();
            out.writeBytes(json);
            out.flush();
            out.close();
            // 读取响应
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(connection.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = URLDecoder.decode(lines, "utf-8");
                sb.append(lines);
            }
            //System.out.println(sb);
            reader.close();
            // 断开连接
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
