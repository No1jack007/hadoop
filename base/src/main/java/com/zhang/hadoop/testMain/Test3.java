package com.zhang.hadoop.testMain;

import jodd.json.JsonSerializer;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.util.*;

public class Test3 {

    public static void main(String args[]) {
//        int arr1[] = {5, 2, 1, 4, 3};
//        for (int i = 0; i < arr1.length; i++) {
////            System.out.println("外");
//            for (int j = i + 1; j < arr1.length; j++) {
////                System.out.println("内");
//                if (arr1[i] > arr1[j]) {
//                    int a = arr1[i];
//                    arr1[i] = arr1[j];
//                    arr1[j] = a;
//                }
//                for(int c:arr1){
//                    System.out.print(c+"\t");
//                }
//                System.out.println();
//            }
//
//        }
//        for (int k = 0; k < arr1.length; k++) {
//            System.out.println(arr1[k]);
//        }

        String token = getToken();
        sendData(token);
    }

    public static String getToken() {
        String url = "https://evb.dfmc.com.cn:9030/EVBService/dataapi/v1";
        String token = "";
        String appKey = "46e5a0a1951344f1b2aa9f0902dec248";
        String appSecret = "a2027893344f823f1e69eb2e946634ff";
        String getTokenUrl = url + "/Auth/getToken";
        Long timestamp = System.currentTimeMillis();
        String digest = getHexMd5(appKey + "-" + timestamp + "-" + appSecret);
        TokenRequestVO tokenRequestVO = new TokenRequestVO(appKey, timestamp, digest);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=UTF-8");
        HttpEntity<TokenRequestVO> httpEntity = new HttpEntity(tokenRequestVO, headers);
        System.out.println(new JsonSerializer().deep(true).serialize(httpEntity));
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InterfaceResultVO<TokenSessionVO>> resp =
                restTemplate.exchange(
                        getTokenUrl,
                        HttpMethod.POST,
                        httpEntity,
                        new ParameterizedTypeReference<InterfaceResultVO<TokenSessionVO>>() {
                        });
        if (resp.getStatusCode() == HttpStatus.OK && resp.getBody().getCode() == 0 && resp.getBody().getData().size() > 0) {
            TokenSessionVO tokenVo = resp.getBody().getData().get(0);
            token = tokenVo.getToken();
        }
        System.out.println("token----------" + token);
        return token;
    }

    public static String getHexMd5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(inStr.getBytes("UTF-8"));
        } catch (Exception e) {
            return "";
        }

        byte[] md5Bytes = md5.digest();
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static void sendData(String token) {
        List<Map<String,Object>> list=new LinkedList<>();
        for(int i=0;i<10000;i++){
            Map<String,Object> map=new HashMap<>();
            for(int j=0;j<100;j++){
                map.put("vin"+j,"12345678901234567890"+j);
            }
            list.add(map);
        }

        String sendVehicleProduceUrl = "https://evb.dfmc.com.cn:9030/EVBService/dataapi/v1/battery/sendVehicleProduce";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=UTF-8");
        headers.add("Authorization", token);
        HttpEntity<InterfacePostDataVO<PushVehiclePackBean>> httpEntity =
                new HttpEntity(list, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InterfaceResultVO<VehicleProdResultVO>> resp =
                restTemplate.exchange(
                        sendVehicleProduceUrl,
                        HttpMethod.POST,
                        httpEntity,
                        new ParameterizedTypeReference<InterfaceResultVO<VehicleProdResultVO>>() {
                        });
        System.out.println(resp.getStatusCode());
    }
}

class TokenRequestVO {
    String appkey;

    Long timestamp;

    String digest;

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public TokenRequestVO(String appkey, Long timestamp, String digest) {
        this.setAppkey(appkey);
        this.setDigest(digest);
        this.setTimestamp(timestamp);
    }
}

class TokenSessionVO {
    String appKey;
    Long tenantId;
    Long orgId;
    String token;
    Date expireTime;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }
}

class InterfaceResultVO<T> {
    int code;

    String message;

    List<T> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}

class PushVehiclePackBean {

    /**
     * 车辆VIN
     **/
    private String vin;
    /**
     * 车辆制造时间
     **/
    private Date product_time;
    /**
     * 车辆型号
     **/
    private String vehicleModel;
    /**
     * 车辆类型
     **/
    private String vehicleType;
    /**
     * 车辆名称
     */
    private String vehicleName;
    /**
     * 车辆品牌
     **/
    private String vehicle_brand;
    /**
     * 储能装配编码
     **/
    private String storageenergycode;
    /**
     * 电池包集
     **/
    private List batterypacks;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Date getProduct_time() {
        return product_time;
    }

    public void setProduct_time(Date product_time) {
        this.product_time = product_time;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicle_brand() {
        return vehicle_brand;
    }

    public void setVehicle_brand(String vehicle_brand) {
        this.vehicle_brand = vehicle_brand;
    }

    public String getStorageenergycode() {
        return storageenergycode;
    }

    public void setStorageenergycode(String storageenergycode) {
        this.storageenergycode = storageenergycode;
    }

    public List getBatterypacks() {
        return batterypacks;
    }

    public void setBatterypacks(List batterypacks) {
        this.batterypacks = batterypacks;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }
}

class InterfacePostDataVO<T> {

    List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}

class VehicleProdResultVO {
    String vin;
    Integer receivecode;
    StringBuilder receivemsg;
    List<BatteryPackageResultVO> batterypacks;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Integer getReceivecode() {
        return receivecode;
    }

    public void setReceivecode(Integer receivecode) {
        this.receivecode = receivecode;
    }

    public StringBuilder getReceivemsg() {
        return receivemsg;
    }

    public void setReceivemsg(StringBuilder receivemsg) {
        this.receivemsg = receivemsg;
    }

    public List<BatteryPackageResultVO> getBatterypacks() {
        return batterypacks;
    }

    public void setBatterypacks(List<BatteryPackageResultVO> batterypacks) {
        this.batterypacks = batterypacks;
    }
}

class BatteryPackageResultVO {
    String batterypackcode;
    Integer receivecode;
    StringBuilder receivemsg;
    List<BatteryModuleResultVO> batterymodules;

    public String getBatterypackcode() {
        return batterypackcode;
    }

    public void setBatterypackcode(String batterypackcode) {
        this.batterypackcode = batterypackcode;
    }

    public Integer getReceivecode() {
        return receivecode;
    }

    public void setReceivecode(Integer receivecode) {
        this.receivecode = receivecode;
    }

    public StringBuilder getReceivemsg() {
        return receivemsg;
    }

    public void setReceivemsg(StringBuilder receivemsg) {
        this.receivemsg = receivemsg;
    }

    public List<BatteryModuleResultVO> getBatterymodules() {
        return batterymodules;
    }

    public void setBatterymodules(List<BatteryModuleResultVO> batterymodules) {
        this.batterymodules = batterymodules;
    }
}

class BatteryModuleResultVO {
    String batterymodulecode;
    Integer receivecode;
    StringBuilder receivemsg;
    List<BatteryCellResultVO> batterycells;
}

class BatteryCellResultVO {
    String batterycellcode;
    Integer receivecode;
    StringBuilder receivemsg;
}