package com.zhang.hadoop.testMain;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhang.hadoop.util.BASE64Encoder;
import com.zhang.hadoop.util.Base64;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.json.JsonParser;
import jodd.json.JsonSerializer;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.util.StringUtils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by zhang yufei on 2018/7/3.
 */
public class Module {

    /**
     * 主机厂平台地址
     * 此地址改为系统部署后的映射地址，以下地址位北理工对外地址，可首次测试使用
     */
    public static String ip1 = "http://192.168.1.111:8993";
    //主机厂调用接口令牌，由乙方技术支持提供，且需由甲方提供主机厂全称及统一厂商社会信用代码
    public static String vehicle_token = "C3kNIVJWfOxLkRDydrJXsA4ff6oMaTOP";
    //主机厂调用接口密钥，由乙方技术支持提供
    public static String vehicle_key = "U2TohGfvuck0v5Xa";
    //电池厂调用接口令牌，由乙方技术支持提供，且需由甲方提供电池厂全称及统一厂商社会信用代码
    public static String factory_token = "AZQSSAD3rSyQkULMEqOHrHuDohjKs2S9";
    //电池厂调用接口密钥，由乙方技术支持提供
    public static String factory_key = "pxrf27g1vO6KcdYf";

    public static void main(String args[]) {
        long start = System.currentTimeMillis();

        //1.主机厂车辆生产
//        createVehiclePack();
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

//        String vehicle=encrypt("vehicle","0162ae481a7f411e86b1eadf5cc838eb");
//        System.out.println(vehicle);

//        String s1 = createVehiclePackRandom("vehModelName", 10, 1000, "U2TohGfvuck0v5Xa");
//        System.out.println(s1);


//        String s1 = createChassisRandom("vehModelName", 10, 1, "U2TohGfvuck0v5Xa");
//        System.out.println(s1);
//        HttpRequest request = HttpRequest.post(ip1+"/bitnei/v1.0/battery/vehicle/receiveChassis");
//        request.header("Authorization", "Bearer " + vehicle_token);
//        request.header("Content-Type", "application/json; charset=utf-8");
//        request.acceptEncoding("gzip");
//        request.body(s1);
//        request.send().unzip().unzip();
        String s3="{\"requestMsg\":\"zAsDQbUqXt9EGADulQyTMvzBlP1WfT8GkQVQkgoGt+PUHRfOpxrfuxVKE+3ifttRl6kK\\/ncbzgvr91LbYYb50F9kisPeBOtmhat+M\\/wNdnLeQ5hVwtUJuV63IxYZonlxzI9s4LCRHi9J1I+00+Kuu1JKD37\\/2G5Wuo1StURR5a0c2YCqS0xcU24SyZPPf+rHdI3OwQUD\\/q1H\\/ePecl7IE+JMyWO0tr2nAol+vh2R6kvcAItHsz3y+S9aIU3R3g6WuB2SMeyqSpd\\/u01qyeVwkIbcVfyxBfO1gMvCXLsBeNvpeDjVaJ5dsa3mOtmCDoAFYF6qUX+skVCMUtIcZLxlS0OuuK+rqzdh9S6rjx4CIkes+t6WU88cOHV\\/JieW8YizR\\/Q5UctJN4x83V5ZRGQpTJAenZPKOwGhNlcGbrrYsfN94R7vFNodEDiAitMMDtIyRPthNZXfnQEoSQ+Z4VbvxBSvlE4oImqfVAohXt\\/E7bitvo\\/X\\/K8cazqgDvVxBQS0tXklHkBKkcA+hgagS9IVwz5Zfl83r3iZjhJWIR4+XqOpgHTMA7RSP+GlGx4V4s79XbEZUT4MXR8Gu5C1eWg8\\/AHUXQWSrpl7Ql53sgfDyPfkb+DE1awjNZVmUlrXKdSyzaPIKKDKdRKH4qbaxoGa+xnJwKATQx39IjakxEGQ0ps26zQVtLl1hHnZ4dhhqjku2gE4YlhBKyUlAv5sbFHCfTuZP7HXDbIqH5K3jvja0rlbtn5i\\/38SOQdm8clGBh32\\/lNDfaLN0lU2iMZqhEgDrni420Mx98trTjXQ08awI9wGWspJ+kfYCdAwMjabr4thYGjzMU08k09DmroaUp8HAES6NbGGRzXGraNFkDUeXR8garFVvw9c3aqifK2QMaBHna9Df1LQRol7bxPdWN6Qs4VIUtYEe1ai2hzq0skl3vB7P\\/sQpnQx7\\/HucEL9+Mjbr\\/rbFES5rBnWYZzKNNjQsBAcuX+4rpxF59a0zBSGYG79MIlXe1024M4fqMlBJ5TiCjjfGYbbrVzFuNAAL+1OtvR6Tt+wknH1LqGW42yYU2WPkXsCDiob10A2sWWV7iKCBbFbrebxGB41XWSy2828cSsu3rAEMtXTvBj1qBxPXqdfkSQIfT0GfBXAtoGCoTYbLf3osxyxJWqcd4Zrkw4dWGul2jA9einado1SAnNF3qLfwhTg88DT\\/PI9NwGJK6CRTICB\\/5FqHKxfsrlaAXc71auYvPCX4tYvRNou73x5IzBPHv\\/Ryir3S65bmCpeQvIHKK4eDBhvcPnDV+qKDXYUIWEFna8+hVuZcMIDlkMMnGPpEJilCpgoB7POKzeso771kagZ\\/GS4olWVMA5YqBYNYo8I7VFuyF1yEb3NycuhBCG49CbvVSMARfRlN8\\/p5MogjI5yuCwEDns\\/ShXalf3O0mFDhJUJtW5Y3OnVaj2dIgrbzHXQ0xkq7BiIGuA\\/mda\\/NCtJzmQe2NbvN2a+h2Z0SkI7CjUUEnWjqXnShuHi5h\\/1vHtS4RbI0SEe0okhaBFvpk16RQwcAdkr6s1030xIZWdpEZ4Xh4OPm9oCjd+onxIBL5MRF+20KUuqKq2gddkKqbAUuCAdnAIVz43xtCGYy6q+4DqDtQIrXu8KcqgOFBDp71VVpHNV0ne9idm3iqVcIVBefL63qNdMeIDIFf4FX2W9fXsGng\\/BmJEq3xkUoQ+4nBMFrVHyOIV6EhCw5XxFak9PTyBm9s2EgoSIbs9yBm8fRkh07unDRgPP\\/f89ei3BeSElRylVHzuYAcD774kYCxHebcSNL252i2vNKXLEusl6yMIv2v7w8Vp5FsEc43e7zR5ttlc84Y7hJVpIl7riCZy6p9TLCLB8NsCAQ+qgFadhp\\/EAU4hlbZtg5qXY5P2XWL1jkbNTi4rKrboq5W4eHvYHfozisXb9NEv6EmyP\\/5sdwe8h2DXndwbDy1aoyUgqJKXIjdAm6UsTFLxUmyWT4DiLE1VTw4jEVVlSDe5vEc9bIJ\\/OvV\\/FZmv6zFjMZ5CmzqYQ1vDA9jJ9i51TUpWhWm4J\\/VTFyyPzoyIhjaZTJVy9NYDR+Zup7magLVtK4iMAULi8de3VCW2Am+SIQ1KUz2JgudI2rjM6RDcJcsqGoJvmvx0rnASIdZJ5oylMiYo8Fw+9\\/lefzmxeprnPwRUzihWkUvVyjzb8vz160xC0R8XGcMWm4+4qUORH\\/ykCMZKU\\/C0hhc\\/37BTqH1LBSoEpTVj8eu1Uh0d38L\\/wNsX7QC99SUXF1Wtxeo5FOFXorHbNcs+E5+x1003sAiPSkXmpsdTi\\/0cj1Crf5QMUMjVOH49dhC+F2V5rPNIezico3UFE3x5OJycsGmIOGf6EFO1GF36UtAOMIxZInb9rRQlfmes5rQVTzTAV79WTkTQGm9jPdCR1716VPpv8pN19pQbKiDTqESQn6jNCSAbeNIVdJeBFoXSX0RdOdlVevpjdaUsjw\\/3MmMexuX6tT2n9+zy8itjLmuiL6mrTteJM2+AFI6sdRSWrF5ygCfbjwktmS0UosuaEjV4xblWFtpmt9\\/KBOL6Js6WED7i++m0Ytl1y6oFGyVbru2sgiwNJx3HaWz5uXYY8xQVU8OU1VIf7eeOWkY6VjZ4ryW2eNGAqAQlhAevJVPmeTfeL5jPmmdm6SBZjV2GrqZnHZWfzi+7\\/jYwU52GZ4HNbQmuDaa9D7AxzO2ANxNTEOFue6+\\/HKdePHdjWO99gVIcFDeQ9EDhGlyoMPXW\\/m3mcjd3Dskm4GVsrSzO2xTJFThi\\/5Nfo\\/7sIHHTu+mncKrZXZsjqzMgmmxxWJAAO+SVxIvEbXvFccHZX5cIUxvZrLdfUoAeVhSNWBZ5KJAqsMmskc1kuqq7+xvfBk97TxJPRCUByap7P5nAhgVOPKBU1JqUMluptghghFr0FU1r+\\/9q+MykrYWurRdeZjs\\/9wdmAAVlrbR+gdnbDVfeoOHxncShxGXzliYowDT7KlaQtIZlfJsSMX+CLeMrejEAF+kn7FC2DsXswslsb8v8jfjU1qxYNqAgw4rt06PX+vyVnl9Cb1B3G5w7pU83AvYeaxLYT6SNjApmG\\/5T\\/ggsdrBewJTn3QXQVIxLXcZEfebyiCJLLXFKVVYRVD1T5Ykq7JV6nFTlR39q6cjeIfZLQ31gVtuv7Fku2YTh1YbzDGZ3mRnycY2ae2SeWhwjlCoNLSeL0WZMOHx8IZlmTGMXQW2oF5lRPeMmVG7FZ6ze+nicsdu5bJpUm3GyPyll7DySKjFunbC8Pc0o3AKmSvRIGrfi5SrKKGr+aBFoShub3ASR\\/eDW2p4PRkYGH1koL4PETiWqfR3J4wdAJZAl\\/PjYzdECwjJ0vvt0H1wfW9SrxdA8hGZxmHM4Pe59rUkxpZ1VCGkVY\\/8rvp+DBLoYiz+nQ5OZWqj\\/1biBtBfmonHtsM11vM42Ww2mKWbiHHJ59sTkuyDjoh+CcIFTK4WbiQPgST946Fng3e2INen+1V3Z1Xon\\/w2isDrhF7TOiCnqdKKRpZf2FGUvAKEE+flWVOIGTMTA\\/mRicJRPhnIfk17QnzzMXvQlo7RyaRPknUQMKWZaFH3bn1W2Sseg5Kk2vwlNGVgUxud83iYzmwZwepEJiDbKzZpGhZGuBG\\/BlNZBUvNgST1HcCXgQmzJ3z1tCRN\\/OfrUeMn8K8pJLK+Ea9SHGWB+PLYajKSReob6aUcY2zBfZLYKzF3kfXnQIf92asHR7DYoY7BbGbGRCUEO9VyWSaPuYTq3Zupsz\\/uv5RS3vGk+cxALUlFit5G\\/\\/aDcSagKryNODomDxtwclZml2XxNkEF61Hmze7Z5Ii+I3t036w2pC\\/WoeST\\/YIfiNwWxgi1Km\\/ko6IPWNqRGYMOejrgfosXmFQpBaw6BpHgg4Aw+4RS3vI5AVQ69lP4t9NA8XUVb1wxsNtTOxaQRt8IV9sH5oAUINL9OtRMeRpDJxyoqyEqNO6VJJ6Ex3olhsY2kYXwluDIISM+SeJyeCi0UVclh6Hv0XP9De1UoDc\\/9l0KpwlPw+0AKwp4K8HoNFFChUPTM2zNyxCR8YVaUX0CZfQ\\/AFIhkyU+gcgpfzaajP76xu\\/VCavS7u+5dNX0pYrslcL3rtMlG3ZwE+NhtXXc04M\\/Hj\\/lDVAJEl2rbWck3hslmwEVq2eeT6O+EqLpjPVnniOFCda97R8WaLCXe3qh3KrPcAyVa3P27JQWnlrgfOR\\/w3Qngse6HKOVk8\\/dGiScgKrR7iBjbJrgSBko6eC7M1ceL0feRG1voqkJWrksvVOEcHOmq4hnA\\/AB8fd\\/JedRBtCGkg23sknZ8h3qBfaRmzfmGyMYUbpUio0U\\/\\/eP1y8FmgBmJAVXHtuMxNEjVY+B8aIsqaxv9LvORX6ysUx\\/YBiFY5UCxebkCBQOZF0IAuG8G9Hk50KtBQCBio+uj9KbetjzkHWfDZ9L9LWe+tQjG9y4f+toutZaWUO0XbUDRN8PL026kDA0yQXva3Zx766VAofYlX9BU6IBlQUzS+bW1lq5dK0bcTJY8obGGJi31Ohey2J06gG+Zj4eqRBShh2oUYp0mXp\\/K9\\/l8a5reJ4sSRJBeziq5fK\\/aBgVJmIeH\\/sLyxbhSlPJoxSaM4N7yd1e1DVvhvMaxfy2mr4IylyXQ6Wh\\/29DquTZf70niDZicWE23aeIEWbcdkEfmhrqcaEdfMaij+vi8JKSNGXX2flT6zceUru8l+ucy7WQ7rgcM\\/AfJ7Hq+5zL\\/Q32EC5D9rxDhCDFuo60DTQuNhHQWDeWp7KrDgQzRA\\/30lOFfefLyd8B47eNTZByxhmL8NMacHImRc7zMIE9YpmrT6YtdmBSD5GPL4llurSbk+SMrPhddU7I7q7e1guWHTB6eN5QcE8YVC+AuceC\\/PHEsDnN5huzQQGR7Yp04MQR4GbZe4Htb1h58sJCqkkuJz+pL43DfEyE5lX78eEMSgP80O5JXvYNnIaivoSOf\\/d1G7\\/mhgnkgmRryX9N1TSoeiabK0M+ev6\\/nMZ7jesw5cgDdSpljC1SZAv05XTl\\/rM\\/bRri203WKcaa8Ifkb\\/kCdbWsWF47SAtItTqahqnVtIJEbXRwlgSOfq0rXbgMxlgigvnujOct14HGdIRGGS6uwKNnCtHweMeva8\\/i86zMWk\\/YYZy2gQCNFhUqolQvxfyxhpdSQk6xoJ3govik3cclAW\\/UFW+AOyJTWbku\\/D0SpikkwTMfHBGlCe7XlE\\/\\/x0ndWhbYG5p4OsTkZvd2tL4WyYAdZTFiVcwQUepkZ3JSMC0TpQ9rDaraZ3iOl+es1TLsBPRp96k7kIC3MhVzPCERmsKNH+yj1gmM2exROiaL4TDcUoGatuOgVe03ImyN3DtGaADxFLfcRQcG4IM\\/v031r9fgUcERMJwWX+hsGzrwIFgxW3s16AI0VPEX1iX6IQFZgJYDVkOAI1FJvGxoLRU1mIbdXnY\\/TJlHB\\/31Sf9Ea8ym3RDtFPHxGYlUOf3m4bjAw453CWGu2n\\/Zz2JJQTMDCC2eluHg1XMTy6p5zb02\\/hcatii2dAg8SmgWMX1Wk8vYHHqBBn43LyrYQnwxnVTYwSt7sxnQU8lC3iVHvPr8UDx9m06pQa8Y1aidbxxZWVSdFT7uMzdpkhPeH2Y1HYkWqJ9jDau1LpWQ+iWL339rpdbxDzWee2qutpTVxdQ5hZCN5ilEdnAHULp+hE\"}\n";
        Map<String,Object> map=new JsonParser().parse(s3);
        String s2 = decrypt(map.get("requestMsg").toString()
                , "U2TohGfvuck0v5Xa");
        System.out.println("解密" + s2);


        long end = System.currentTimeMillis();
        System.out.println("完成" + (end - start));
    }

    public static void createVehiclePack() {
        List<String> packList = new ArrayList<>();
        packList.add("P100");
        Map<String, Object> vinMap = new HashMap<>();
        vinMap.put("vin", "");
        vinMap.put("vehModelName", "vehModel01");
        vinMap.put("packCodeList", packList);
        vinMap.put("systemCode", "S001");
        vinMap.put("offlineProductionTime", "2018-01-01 00:00:00");
        vinMap.put("vehicleBrand", "奔驰");
        vinMap.put("vehicleName", "奔驰");
        vinMap.put("vehicleType", "1");
        List<Map<String, Object>> vinList = new ArrayList<>();
        vinList.add(vinMap);
        Map<String, Object> vehicleMap = new HashMap<>();
        vehicleMap.put("vinList", vinList);
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/vehicle/receiveVehicleProduce", vehicleMap, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createBatteryPack() {
//        List<String> cellList1=new ArrayList<>();
//        cellList1.add("C111");
//        Map<String,Object> module1=new HashMap<>();
//        module1.put("code","M110");
//        module1.put("cellList",cellList1);
//        module1.put("modelId","zhangM");
//        module1.put("cellModelId","zhangC");
//        List<Map<String,Object>> moduleList1=new ArrayList<>();
//        moduleList1.add(module1);
        Map<String, Object> pack1 = new HashMap<>();
        pack1.put("code", "P100");
//        pack1.put("moduleList",moduleList1);
        pack1.put("serial", "1");
        pack1.put("modelId", "1号包");
        pack1.put("systemId", "S001");
        pack1.put("systemModelId", "zhangS");
        pack1.put("orderNo", "o001");
        List<Map<String, Object>> packList = new ArrayList<>();
        packList.add(pack1);
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/vehicle/receiveBatteryProduce", packList, factory_token, factory_key);
        String result = parsToMap(response, factory_key);
        System.out.println(result);
    }

    public static void createSale() {
        List<Map<String, Object>> vehicleList = new ArrayList<>();
        Map<String, Object> vehicle = new HashMap<>();
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
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/receiveSaleVehicle", vehicleList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createReplaceBattery() {
        Map<String, Object> cell1 = new HashMap<>();
        cell1.put("code", "C211");
        cell1.put("type", "C");
        List<Map<String, Object>> cellList1 = new ArrayList<>();
        cellList1.add(cell1);
        Map<String, Object> module1 = new HashMap<>();
        module1.put("code", "M210");
        module1.put("childCodeList", cellList1);
        module1.put("modelId", "zhangM");
        module1.put("type", "M");
        List<Map<String, Object>> moduleList1 = new ArrayList<>();
        moduleList1.add(module1);
        Map<String, Object> pack1 = new HashMap<>();
        pack1.put("code", "P200");
        pack1.put("childCodeList", moduleList1);
        pack1.put("modelId", "zhangP");
        pack1.put("type", "P");
        pack1.put("orderCode", "R001");
        List<Map<String, Object>> packList = new ArrayList<>();
        packList.add(pack1);
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/sail/receiveReplaceBattery", packList, factory_token, factory_key);
        String result = parsToMap(response, factory_key);
        System.out.println(result);
    }

    public static void createReplaceRecord() {
        List<Map<String, Object>> recordList = new ArrayList<>();
        Map<String, Object> record = new HashMap<>();
        record.put("vin", "LNBSCB3F0DD130955");
        record.put("replaceDate", "2018-01-05");
        record.put("oldCode", "P100");
        record.put("whereaboutsId", "123456789123456789");
        record.put("whereaboutsName", "维修厂");
        record.put("newCode", "P200");
        record.put("batterySpecies", "P");
        recordList.add(record);
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/sail/receiveReplaceRecord", recordList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createFactoryRepair() {
        Map<String, Object> cell1 = new HashMap<>();
        cell1.put("code", "C311");
        cell1.put("type", "C");
        List<Map<String, Object>> cellList1 = new ArrayList<>();
        cellList1.add(cell1);
        Map<String, Object> module1 = new HashMap<>();
        module1.put("code", "M310");
        module1.put("childCodeList", cellList1);
//        module1.put("modelId","zhangM");
        module1.put("type", "M");
        List<Map<String, Object>> moduleList1 = new ArrayList<>();
        moduleList1.add(module1);
        Map<String, Object> pack1 = new HashMap<>();
        pack1.put("code", "P300");
        pack1.put("childCodeList", moduleList1);
//        pack1.put("modelId","zhangP");
        pack1.put("type", "P");
        pack1.put("orderCode", "R001");
        List<Map<String, Object>> repairList = new ArrayList<>();
        Map<String, Object> repair = new HashMap<>();
        repair.put("vin", "LNBSCB3F0DD130955");
        repair.put("replaceDate", "2018-01-05");
        repair.put("oldCode", "P200");
        repair.put("whereaboutsId", "123456789123456789");
        repair.put("whereaboutsName", "维修厂");
        repair.put("newCodeBean", pack1);
        repair.put("batterySpecies", "P");
        repairList.add(repair);
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/sail/receiveReplaceOld", repairList, factory_token, factory_key);
        String result = parsToMap(response, factory_key);
        System.out.println(result);
    }

    public static void createRecoverStorage() {
        List<Map<String, Object>> recoverStorageList = new ArrayList<>();
        Map<String, Object> recoverStorage = new HashMap<>();
        recoverStorage.put("storageDate", "2018-01-05");
        recoverStorage.put("recoverUnitCode", "123456789123456789");
        recoverStorage.put("recoverUnitName", "维修厂");
        recoverStorage.put("code", "P400");
        recoverStorage.put("batterySpecies", "P");
        recoverStorageList.add(recoverStorage);
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/recover/receiveRecoverStorage", recoverStorageList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createRetireVehicle() {
        List<Map<String, Object>> retireVehicleList = new ArrayList<>();
        Map<String, Object> retireVehicle = new HashMap<>();
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
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/sail/receiveRetiredOldVehicle", retireVehicleList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createReplaceBatteryInStorage() {
        List<String> cellList1 = new ArrayList<>();
        cellList1.add("C511");
        Map<String, Object> module1 = new HashMap<>();
        module1.put("code", "M510");
        module1.put("cellList", cellList1);
        module1.put("cellCodeSize", "1");
        List<Map<String, Object>> moduleList1 = new ArrayList<>();
        moduleList1.add(module1);
        Map<String, Object> pack1 = new HashMap<>();
        pack1.put("code", "P500");
        pack1.put("moduleList", moduleList1);
        pack1.put("replaceUnitCode", "123456789123412342");
        pack1.put("replaceUnitName", "换电厂");

        List<String> cellList2 = new ArrayList<>();
        cellList2.add("C611");
        Map<String, Object> module2 = new HashMap<>();
        module2.put("code", "M610");
        module2.put("cellList", cellList2);
        module2.put("cellCodeSize", "1");
        List<Map<String, Object>> moduleList2 = new ArrayList<>();
        moduleList2.add(module2);
        Map<String, Object> pack2 = new HashMap<>();
        pack2.put("code", "P600");
        pack2.put("moduleList", moduleList2);
        pack2.put("replaceUnitCode", "123456789123412342");
        pack2.put("replaceUnitName", "换电厂");

        List<Map<String, Object>> packList = new ArrayList<>();
        packList.add(pack1);
        packList.add(pack2);

        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/replaceBattery/batteryInStorage", packList, vehicle_token, vehicle_key);
        String result = parsToMap(response, factory_key);
        System.out.println(result);
    }

    public static void createReplaceReplacement() {
        List<Map<String, Object>> recordList = new ArrayList<>();
        Map<String, Object> record = new HashMap<>();
        record.put("vin", "LNBSCB3F0DD130955");
        record.put("oldCode", "P500");
        record.put("newCode", "P600");
        record.put("replaceUnitCode", "123456789123412342");
        record.put("replaceUnitName", "换电厂");
        record.put("replaceDate", "2018-01-05");
        recordList.add(record);
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/replaceBattery/replacementBattery", recordList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createReplaceRepair() {
        Map<String, Object> cell1 = new HashMap<>();
        cell1.put("code", "C711");
        cell1.put("type", "C");
        List<Map<String, Object>> cellList1 = new ArrayList<>();
        cellList1.add(cell1);
        Map<String, Object> module1 = new HashMap<>();
        module1.put("code", "M710");
        module1.put("childCodeList", cellList1);
        module1.put("type", "M");
        List<Map<String, Object>> moduleList1 = new ArrayList<>();
        moduleList1.add(module1);
        Map<String, Object> pack1 = new HashMap<>();
        pack1.put("code", "P700");
        pack1.put("childCodeList", moduleList1);
        pack1.put("type", "P");
        List<Map<String, Object>> repairList = new ArrayList<>();
        Map<String, Object> repair = new HashMap<>();
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
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/replaceBattery/replaceRepairBattery", repairList, vehicle_token, vehicle_key);
        String result = parsToMap(response, factory_key);
        System.out.println(result);
    }

    public static void createReplaceRetire() {
        List<Map<String, Object>> retireVehicleList = new ArrayList<>();
        Map<String, Object> retireVehicle = new HashMap<>();
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
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/sail/receiveRetiredOldReplace", retireVehicleList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createRetireFactory() {
        List<Map<String, Object>> retireVehicleList = new ArrayList<>();
        Map<String, Object> retireVehicle = new HashMap<>();
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
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/sail/receiveRetiredOld", retireVehicleList, factory_token, factory_key);
//        HttpResponse response = send("http://192.168.1.111:8993"+"/bitnei/v1.0/battery/sail/receiveRetiredOld", "[{\"vehModelName\":\"ZN6453V1YBEV\",\"vin\":\"LNBSCB3F0DD130955\",\"packCodeList\":[\"P100\"]}]", "C3kNIVJWfOxLkRDydrJXsA4ff6oMaTOP", "U2TohGfvuck0v5Xa");
        String result = parsToMap(response, vehicle_key);
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
        request.header("Content-Type", "application/json; charset=utf-8");
        request.acceptEncoding("gzip");
        request.body((new JsonSerializer()).serialize(params));
//        System.out.println((new JsonSerializer()).serialize(params));
        return request;
    }

    public static void test() {
        try {
            StringBuffer data = new StringBuffer();
            String url = "http://127.0.0.1:8993" + "/bitnei/v1.0/battery/jingMa/getModelByPackCode";
//            String token="pOAJJSBmAThb8kPJh4VAX+zs/YAHFqxd";
//            String key="vAREamtPvwanVe2M";
            Map<String, Object> requestMap = new HashMap<String, Object>();
            String enStr = encrypt(data.toString(), vehicle_key);
            requestMap.put("requestMsg", enStr);
            requestMap.put("timestamp", System.currentTimeMillis());
            String sign = byteArrayToHexString(encryptHMAC(enStr.getBytes(), vehicle_key));
            requestMap.put("sign", sign);
            HttpResponse response = getRequest(url, vehicle_token, requestMap).send().unzip().unzip();
            ObjectMapper mapper = new ObjectMapper();
            Map map = mapper.readValue(response.body(), Map.class);
            String result = "";
            if (isNotEmpty(map.get("data"))) {
                result = decrypt(map.get("data").toString(), vehicle_key);
            } else {
                result = decrypt(map.get("msg").toString(), vehicle_key);
            }
            System.out.println(map.get("code") + "" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getBatteryIsImport() {
        List<String> codeList = new ArrayList<>();
        codeList.add("P001");
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/vehicle/getBatteryIsImport", codeList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void getBatteryIsBindingsVIN() {
        List<Map<String, Object>> codeList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("code", "P001");
        map.put("type", "P");
        map.put("vin", "LNBSCB3F0DD130955");
        codeList.add(map);
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/vehicle/getBatteryIsBindingsVIN", codeList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static String createVehiclePackRandom(String vehModelName, int packNum, int vehicleNum, String key) {
//        Random rand = new Random();
//        IntStream.range(0,10).forEach(value -> System.out.println(RandomStringUtils.random(11,"QWERTYUIOP123456789")));
        List<Map<String, Object>> vinList = new ArrayList<>();
//        Calendar now = Calendar.getInstance();
//        int i1=(now.get(Calendar.HOUR_OF_DAY));
//        int i2=now.get(Calendar.MINUTE);
//        int i3=now.get(Calendar.SECOND);
//        Long l4=now.getTimeInMillis();
//        String vin="VIN"+i1+i2+i3+l4;
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmssSSS");
        String dateNowStr = sdf.format(d);
        String vin = "VIN" + dateNowStr + "0";
        for (int i = 0; i < vehicleNum; i++) {
            List<String> packList = new ArrayList<>();
            for (int j = 0; j < packNum; j++) {
                String packId = UUID.randomUUID().toString().replaceAll("-", "");
                packList.add(packId);
            }
            Map<String, Object> vinMap = new HashMap<>();
//            vinMap.put("vin","VINVINVIN"+rand.nextInt(100000000));
            if (i < 10) {
                vinMap.put("vin", vin + "000" + i);
            } else if (i >= 10 && i < 100) {
                vinMap.put("vin", vin + "00" + i);
            } else if (i >= 100 && i < 1000) {
                vinMap.put("vin", vin + "0" + i);
            } else if (i >= 1000 && i < 10000) {
                vinMap.put("vin", vin + "" + i);
            }
            vinMap.put("vehModelName", vehModelName);
            vinMap.put("packCodeList", packList);
            vinMap.put("systemCode", "S001");
            vinMap.put("offlineProductionTime", "2018-01-01 00:00:00");
            vinMap.put("vehicleBrand", "奔驰");
            vinMap.put("vehicleName", "奔驰");
            vinMap.put("vehicleType", "1");
            vinList.add(vinMap);
        }
        Map<String, Object> vehicleMap = new HashMap<>();
        vehicleMap.put("vinList", vinList);
        String requestMsg = new JsonSerializer().deep(true).serialize(vehicleMap);
        String enStr = encrypt(requestMsg, key);
        Map<String, Object> requestMap = new HashMap<String, Object>();
        requestMap.put("requestMsg", enStr);
        return new JsonSerializer().serialize(requestMap);
    }

    public static String createSaleRandom(String dateNowStr, int vehicleNum, String key) {
        List<Map<String, Object>> vehicleList = new ArrayList<>();
        String vin = "VIN" + dateNowStr + "0";
        for (int i = 0; i < vehicleNum; i++) {
            Map<String, Object> vehicle = new HashMap<>();
            if (i < 10) {
                vehicle.put("vin", vin + "000" + i);
            } else if (i >= 10 && i < 100) {
                vehicle.put("vin", vin + "00" + i);
            } else if (i >= 100 && i < 1000) {
                vehicle.put("vin", vin + "0" + i);
            } else if (i >= 1000 && i < 10000) {
                vehicle.put("vin", vin + "" + i);
            }
            vehicle.put("vin", vin);
            vehicle.put("licensePlate", "京BZ0418");
            vehicle.put("vehTypeName", "111808");
            vehicle.put("saleTime", "2018-01-05");
            vehicle.put("saleArea", "北京市昌平区");
            vehicle.put("epname", "吉利汽车");
            vehicle.put("epaddress", "北京市");
            vehicle.put("epcode", "123456789123456789");
            vehicleList.add(vehicle);
        }
        String requestMsg = new JsonSerializer().deep(true).serialize(vehicleList);
        String enStr = encrypt(requestMsg, key);
        Map<String, Object> requestMap = new HashMap<String, Object>();
        requestMap.put("requestMsg", enStr);
        return new JsonSerializer().serialize(requestMap);
    }

    public static String createChassisRandom(String vehModelName, int packNum, int vehicleNum, String key) {
        List<Map<String, Object>> vinList = new ArrayList<>();
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmssSSS");
        String dateNowStr = sdf.format(d);
        String vin = "VIN" + dateNowStr + "0";
        for (int i = 0; i < vehicleNum; i++) {
            List<String> packList = new ArrayList<>();
            for (int j = 0; j < packNum; j++) {
                String packId = UUID.randomUUID().toString().replaceAll("-", "");
                packList.add(packId);
            }
            Map<String, Object> vinMap = new HashMap<>();
            if (i < 10) {
                vinMap.put("vin", vin + "000" + i);
            } else if (i >= 10 && i < 100) {
                vinMap.put("vin", vin + "00" + i);
            } else if (i >= 100 && i < 1000) {
                vinMap.put("vin", vin + "0" + i);
            } else if (i >= 1000 && i < 10000) {
                vinMap.put("vin", vin + "" + i);
            }
            vinMap.put("vehModelName", vehModelName);
            vinMap.put("packCodeList", packList);
            vinList.add(vinMap);
        }
        String requestMsg = new JsonSerializer().deep(true).serialize(vinList);
        String enStr = encrypt(requestMsg, key);
        Map<String, Object> requestMap = new HashMap<String, Object>();
        requestMap.put("requestMsg", enStr);
        return new JsonSerializer().serialize(requestMap);
    }

    public static String createData(String data, String key) {
        String enStr = encrypt(data, key);
        Map<String, Object> requestMap = new HashMap<String, Object>();
        requestMap.put("requestMsg", enStr);
        return new JsonSerializer().serialize(requestMap);
    }


}
