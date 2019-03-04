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
 * Created by zhang yufei on 2018/7/3.
 */
public class module {

    /**
     * 主机厂平台地址
     * 此地址改为系统部署后的映射地址，以下地址位北理工对外地址，可首次测试使用
     */
    public static String ip1="http://114.251.179.117:8993";
    //主机厂调用接口令牌，由乙方技术支持提供，且需由甲方提供主机厂全称及统一厂商社会信用代码
    public static String vehicle_token="C3kNIVJWfOxLkRDydrJXsA4ff6oMaTOP";
    //主机厂调用接口密钥，由乙方技术支持提供
    public static String vehicle_key="U2TohGfvuck0v5Xa";
    //电池厂调用接口令牌，由乙方技术支持提供，且需由甲方提供电池厂全称及统一厂商社会信用代码
    public static String factory_token="AZQSSAD3rSyQkULMEqOHrHuDohjKs2S9";
    //电池厂调用接口密钥，由乙方技术支持提供
    public static String factory_key="pxrf27g1vO6KcdYf";

    public static void main(String args[]) {
        long start = System.currentTimeMillis();

        //1.主机厂车辆生产
        //createVehiclePack();
        //2.电池厂电池生产
        //createBatteryPack();
        //3.主机厂车辆销售
        //createSale();
        //4.电池厂售后
        //createReplaceBattery();
        //5.主机厂维修
        //createReplaceRecord();
        //6.电池厂维修
        //createFactoryRepair();
        //7.主机厂回收网点入库
        //createRecoverStorage();
        //8.主机厂回收网点退役
        //createRetireVehicle();
        //9.主机厂车辆换电入库
        //createReplaceBatteryInStorage();
        //10.主机厂车辆换电记录
        //createReplaceReplacement();
        //11.主机厂车辆换电维修
        //createReplaceRepair();
        //12.主机厂车辆换电退役
        //createReplaceRetire();
        //13.电池厂电池退役
        //createRetireFactory();

//        test();

        //getBatteryIsImport();
        //getBatteryIsBindingsVIN();

        String vehicle=encrypt("vehicle","0162ae481a7f411e86b1eadf5cc838eb");
        System.out.println(vehicle);

        long end = System.currentTimeMillis();
        System.out.println("完成" + (end - start));
    }

    public static void createVehiclePack(){
        List<String> packList=new ArrayList<>();
        packList.add("P100");
        Map<String,Object> vinMap=new HashMap<>();
        vinMap.put("vin","");
        vinMap.put("vehModelName","vehModel01");
        vinMap.put("packCodeList",packList);
        vinMap.put("systemCode","S001");
        vinMap.put("offlineProductionTime","2018-01-01 00:00:00");
        vinMap.put("vehicleBrand","奔驰");
        vinMap.put("vehicleName","奔驰");
        vinMap.put("vehicleType","1");
        List<Map<String,Object>> vinList=new ArrayList<>();
        vinList.add(vinMap);
        Map<String,Object> vehicleMap=new HashMap<>();
        vehicleMap.put("vinList",vinList);
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/vehicle/receiveVehicleProduce", vehicleMap, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createBatteryPack(){
//        List<String> cellList1=new ArrayList<>();
//        cellList1.add("C111");
//        Map<String,Object> module1=new HashMap<>();
//        module1.put("code","M110");
//        module1.put("cellList",cellList1);
//        module1.put("modelId","zhangM");
//        module1.put("cellModelId","zhangC");
//        List<Map<String,Object>> moduleList1=new ArrayList<>();
//        moduleList1.add(module1);
        Map<String,Object> pack1=new HashMap<>();
        pack1.put("code","P100");
//        pack1.put("moduleList",moduleList1);
        pack1.put("serial","1");
        pack1.put("modelId","1号包");
        pack1.put("systemId","S001");
        pack1.put("systemModelId","zhangS");
        pack1.put("orderNo","o001");
        List<Map<String,Object>> packList=new ArrayList<>();
        packList.add(pack1);
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/vehicle/receiveBatteryProduce", packList, factory_token, factory_key);
        String result = parsToMap(response, factory_key);
        System.out.println(result);
    }

    public static void createSale(){
        List<Map<String,Object>> vehicleList=new ArrayList<>();
        Map<String,Object> vehicle=new HashMap<>();
        vehicle.put("vin", "LNBSCB3F0DD130955");
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
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/receiveSaleVehicle", vehicleList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createReplaceBattery(){
        Map<String,Object> cell1=new HashMap<>();
        cell1.put("code","C211");
        cell1.put("type","C");
        List<Map<String,Object>> cellList1=new ArrayList<>();
        cellList1.add(cell1);
        Map<String,Object> module1=new HashMap<>();
        module1.put("code","M210");
        module1.put("childCodeList",cellList1);
        module1.put("modelId","zhangM");
        module1.put("type","M");
        List<Map<String,Object>> moduleList1=new ArrayList<>();
        moduleList1.add(module1);
        Map<String,Object> pack1=new HashMap<>();
        pack1.put("code","P200");
        pack1.put("childCodeList",moduleList1);
        pack1.put("modelId","zhangP");
        pack1.put("type","P");
        pack1.put("orderCode","R001");
        List<Map<String,Object>> packList=new ArrayList<>();
        packList.add(pack1);
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/sail/receiveReplaceBattery", packList, factory_token, factory_key);
        String result = parsToMap(response, factory_key);
        System.out.println(result);
    }

    public static void createReplaceRecord(){
        List<Map<String,Object>> recordList=new ArrayList<>();
        Map<String,Object> record=new HashMap<>();
        record.put("vin", "LNBSCB3F0DD130955");
        record.put("replaceDate", "2018-01-05");
        record.put("oldCode", "P100");
        record.put("whereaboutsId", "123456789123456789");
        record.put("whereaboutsName", "维修厂");
        record.put("newCode", "P200");
        record.put("batterySpecies", "P");
        recordList.add(record);
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/sail/receiveReplaceRecord", recordList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createFactoryRepair(){
        Map<String,Object> cell1=new HashMap<>();
        cell1.put("code","C311");
        cell1.put("type","C");
        List<Map<String,Object>> cellList1=new ArrayList<>();
        cellList1.add(cell1);
        Map<String,Object> module1=new HashMap<>();
        module1.put("code","M310");
        module1.put("childCodeList",cellList1);
//        module1.put("modelId","zhangM");
        module1.put("type","M");
        List<Map<String,Object>> moduleList1=new ArrayList<>();
        moduleList1.add(module1);
        Map<String,Object> pack1=new HashMap<>();
        pack1.put("code","P300");
        pack1.put("childCodeList",moduleList1);
//        pack1.put("modelId","zhangP");
        pack1.put("type","P");
        pack1.put("orderCode","R001");
        List<Map<String,Object>> repairList=new ArrayList<>();
        Map<String,Object> repair=new HashMap<>();
        repair.put("vin", "LNBSCB3F0DD130955");
        repair.put("replaceDate", "2018-01-05");
        repair.put("oldCode", "P200");
        repair.put("whereaboutsId", "123456789123456789");
        repair.put("whereaboutsName", "维修厂");
        repair.put("newCodeBean", pack1);
        repair.put("batterySpecies", "P");
        repairList.add(repair);
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/sail/receiveReplaceOld", repairList, factory_token, factory_key);
        String result = parsToMap(response, factory_key);
        System.out.println(result);
    }

    public static void createRecoverStorage(){
        List<Map<String,Object>> recoverStorageList=new ArrayList<>();
        Map<String,Object> recoverStorage=new HashMap<>();
        recoverStorage.put("storageDate", "2018-01-05");
        recoverStorage.put("recoverUnitCode", "123456789123456789");
        recoverStorage.put("recoverUnitName", "维修厂");
        recoverStorage.put("code", "P400");
        recoverStorage.put("batterySpecies", "P");
        recoverStorageList.add(recoverStorage);
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/recover/receiveRecoverStorage", recoverStorageList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createRetireVehicle(){
        List<Map<String,Object>> retireVehicleList=new ArrayList<>();
        Map<String,Object> retireVehicle=new HashMap<>();
        retireVehicle.put("code", "P400");
        retireVehicle.put("whereaboutsCode", "123456789123412341");
        retireVehicle.put("whereaboutsName", "维修厂");
        retireVehicle.put("retireDate", "2018-01-05");
        retireVehicle.put("batterySpecies", "P");
        retireVehicle.put("retireUnitCode", "123456789123123123");
        retireVehicle.put("retireUnitName", "退役厂商");
        retireVehicle.put("factoryType", "5");
        retireVehicle.put("batteryType", "A");
        retireVehicle.put("batteryWeight", "2.5");
        retireVehicleList.add(retireVehicle);
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/sail/receiveRetiredOldVehicle", retireVehicleList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createReplaceBatteryInStorage(){
        List<String> cellList1=new ArrayList<>();
        cellList1.add("C511");
        Map<String,Object> module1=new HashMap<>();
        module1.put("code","M510");
        module1.put("cellList",cellList1);
        module1.put("cellCodeSize","1");
        List<Map<String,Object>> moduleList1=new ArrayList<>();
        moduleList1.add(module1);
        Map<String,Object> pack1=new HashMap<>();
        pack1.put("code","P500");
        pack1.put("moduleList",moduleList1);
        pack1.put("replaceUnitCode","123456789123412342");
        pack1.put("replaceUnitName","换电厂");

        List<String> cellList2=new ArrayList<>();
        cellList2.add("C611");
        Map<String,Object> module2=new HashMap<>();
        module2.put("code","M610");
        module2.put("cellList",cellList2);
        module2.put("cellCodeSize","1");
        List<Map<String,Object>> moduleList2=new ArrayList<>();
        moduleList2.add(module2);
        Map<String,Object> pack2=new HashMap<>();
        pack2.put("code","P600");
        pack2.put("moduleList",moduleList2);
        pack2.put("replaceUnitCode","123456789123412342");
        pack2.put("replaceUnitName","换电厂");

        List<Map<String,Object>> packList=new ArrayList<>();
        packList.add(pack1);
        packList.add(pack2);

        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/replaceBattery/batteryInStorage", packList, vehicle_token, vehicle_key);
        String result = parsToMap(response, factory_key);
        System.out.println(result);
    }

    public static void createReplaceReplacement(){
        List<Map<String,Object>> recordList=new ArrayList<>();
        Map<String,Object> record=new HashMap<>();
        record.put("vin", "LNBSCB3F0DD130955");
        record.put("oldCode", "P500");
        record.put("newCode", "P600");
        record.put("replaceUnitCode","123456789123412342");
        record.put("replaceUnitName","换电厂");
        record.put("replaceDate", "2018-01-05");
        recordList.add(record);
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/replaceBattery/replacementBattery", recordList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createReplaceRepair(){
        Map<String,Object> cell1=new HashMap<>();
        cell1.put("code","C711");
        cell1.put("type","C");
        List<Map<String,Object>> cellList1=new ArrayList<>();
        cellList1.add(cell1);
        Map<String,Object> module1=new HashMap<>();
        module1.put("code","M710");
        module1.put("childCodeList",cellList1);
        module1.put("type","M");
        List<Map<String,Object>> moduleList1=new ArrayList<>();
        moduleList1.add(module1);
        Map<String,Object> pack1=new HashMap<>();
        pack1.put("code","P700");
        pack1.put("childCodeList",moduleList1);
        pack1.put("type","P");
        List<Map<String,Object>> repairList=new ArrayList<>();
        Map<String,Object> repair=new HashMap<>();
        repair.put("vin", "LNBSCB3F0DD130955");
        repair.put("repairDate", "2018-01-05");
        repair.put("oldCode", "P600");
        repair.put("newCode", "P700");
        repair.put("newCodeBean", pack1);
        repair.put("retireUnitCode", "123456789123412341");
        repair.put("retireUnitName", "维修厂");
        repair.put("whereaboutsCode", "123456789123412342");
        repair.put("whereaboutsName", "出库厂");
        repairList.add(repair);
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/replaceBattery/replaceRepairBattery", repairList, vehicle_token, vehicle_key);
        String result = parsToMap(response, factory_key);
        System.out.println(result);
    }

    public static void createReplaceRetire(){
        List<Map<String,Object>> retireVehicleList=new ArrayList<>();
        Map<String,Object> retireVehicle=new HashMap<>();
        retireVehicle.put("code", "P500");
        retireVehicle.put("whereaboutsCode", "123456789123456789");
        retireVehicle.put("whereaboutsName", "电池厂");
        retireVehicle.put("retireDate", "2018-01-05");
        retireVehicle.put("batterySpecies", "P");
        retireVehicle.put("retireUnitCode", "123456789123123123");
        retireVehicle.put("retireUnitName", "退役厂商");
        retireVehicle.put("factoryType", "4");
        retireVehicle.put("batteryType", "A");
        retireVehicle.put("batteryWeight", "2.5");
        retireVehicleList.add(retireVehicle);
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/sail/receiveRetiredOldReplace", retireVehicleList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createRetireFactory(){
        List<Map<String,Object>> retireVehicleList=new ArrayList<>();
        Map<String,Object> retireVehicle=new HashMap<>();
        retireVehicle.put("code", "P200");
        retireVehicle.put("whereaboutsCode", "123456789123456789");
        retireVehicle.put("whereaboutsName", "电池厂");
        retireVehicle.put("retireDate", "2018-01-05");
        retireVehicle.put("batterySpecies", "P");
        retireVehicle.put("retireUnitCode", "123456789123123123");
        retireVehicle.put("retireUnitName", "退役厂商");
        retireVehicle.put("factoryType", "2");
        retireVehicle.put("batteryType", "A");
        retireVehicle.put("batteryWeight", "2.5");
        retireVehicleList.add(retireVehicle);
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/sail/receiveRetiredOld", retireVehicleList, factory_token, factory_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

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
            if(StringUtils.isEmpty(content)) {
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

    public static void test(){
        try {
            StringBuffer data=new StringBuffer("[\n" +
                    "    {\n" +
                    "        \"vin\": \"L938C4D4XJ0000093\",\n" +
                    "        \"replaceDate\": \"2018-11-01\",\n" +
                    "        \"oldCode\": \"04AME0201010119110000028\",\n" +
                    "        \"whereaboutsId\": \"91131022319834621T\",\n" +
                    "        \"whereaboutsName\": \"航天钧和科技有限公司\",\n" +
                    "        \"newCodeBean\": {\n" +
                    "            \"childCodeList\": [\n" +
                    "                {\n" +
                    "                    \"code\": \"101CB22585616554E000XXXX\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050000\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050001\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050002\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050003\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050004\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050005\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050006\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050007\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050008\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050009\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050010\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050011\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050012\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050013\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050014\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050015\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050016\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050017\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050018\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050019\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050020\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050021\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050022\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050023\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050024\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050025\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050026\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050027\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050028\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050029\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050030\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050031\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050032\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050033\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050034\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050035\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050036\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050037\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050038\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050039\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050040\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050041\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050042\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050043\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050044\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050045\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050046\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050047\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050048\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050049\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050050\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050051\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050052\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050053\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050054\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050055\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050056\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050057\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050058\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050059\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050060\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050061\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050062\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050063\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050064\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050065\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050066\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050067\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050068\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050069\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050070\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050071\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050072\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050073\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050074\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050075\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050076\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050077\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050078\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050079\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050080\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050081\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050082\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050083\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050084\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050085\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050086\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050087\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050088\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050089\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050090\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050091\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050092\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050093\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050094\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050095\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050096\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050097\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050098\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050099\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050100\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050101\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050102\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050103\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050104\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050105\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050106\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050107\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050108\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050109\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050110\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050111\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050112\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050113\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050114\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050115\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050116\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050117\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050118\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050119\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050120\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050121\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050122\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050123\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050124\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050125\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050126\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050127\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050128\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050129\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050130\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050131\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050132\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050133\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050134\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050135\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050136\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050137\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050138\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050139\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050140\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050141\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050142\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050143\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050144\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050145\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050146\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050147\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050148\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050149\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050150\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050151\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050152\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050153\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050154\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"code\": \"01VCEAGZ1111A154E0050155\",\n" +
                    "                    \"type\": \"C\"\n" +
                    "                }\n" +
                    "            ],\n" +
                    "            \"type\": \"M\",\n" +
                    "            \"orderCode\": \"JHSH20180101003\",\n" +
                    "            \"code\": \"04AME0201010119110000200\"\n" +
                    "        },\n" +
                    "        \"maintainstatus\": \"0\",\n" +
                    "        \"batterySpecies\": \"M\"\n" +
                    "    }\n" +
                    "]");
            String url="http://127.0.0.1:8993"+"/bitnei/v1.0/battery/sail/receiveReplaceOld";
            String token="pOAJJSBmAThb8kPJh4VAX+zs/YAHFqxd";
            String key="vAREamtPvwanVe2M";
            Map<String, Object> requestMap = new HashMap<String, Object>();
            String enStr = encrypt(data.toString(), key);
            requestMap.put("requestMsg", enStr);
            requestMap.put("timestamp", System.currentTimeMillis());
            String sign = byteArrayToHexString(encryptHMAC(enStr.getBytes(), key));
            requestMap.put("sign", sign);
            HttpResponse response = getRequest(url, token, requestMap).send().unzip().unzip();
            ObjectMapper mapper = new ObjectMapper();
            Map map = mapper.readValue(response.body(), Map.class);
            String result="";
            if(isNotEmpty(map.get("data"))){
                result = decrypt(map.get("data").toString(), key);
            }else {
                result = decrypt(map.get("msg").toString(), key);
            }
            System.out.println(map.get("code")+""+result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void getBatteryIsImport(){
        List<String> codeList=new ArrayList<>();
        codeList.add("P001");
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/vehicle/getBatteryIsImport", codeList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void getBatteryIsBindingsVIN(){
        List<Map<String,Object>> codeList=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        map.put("code","P001");
        map.put("type","P");
        map.put("vin","LNBSCB3F0DD130955");
        codeList.add(map);
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/vehicle/getBatteryIsBindingsVIN", codeList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }
}
