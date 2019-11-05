package com.zhang.hadoop.testMain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhang.hadoop.util.Base64;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhang yufei on 2018/7/3.
 */
public class Module2 {

    //主机厂平台地址
    public static String ip1 = "http://localhost:8993";
     //"http://192.168.1.111:8993";

    //主机厂调用接口令牌
    public static String vehicle_token="C3kNIVJWfOxLkRDydrJXsA4ff6oMaTOP";
    //主机厂调用接口密钥
    public static String vehicle_key="U2TohGfvuck0v5Xa";


    //电池厂调用接口令牌
    public static String factory_token = "pOAJJSBmAThb8kPJh4VAX+zs/YAHFqxd";
    //电池厂调用接口密钥b
    public static String factory_key = "vAREamtPvwanVe2M";



    public static void main(String args[]) {
        long start = System.currentTimeMillis();

        //1.主机厂车辆生产
       // createVehiclePack();
        //2.电池厂电池生产
      //createBatteryPack();
        //3.主机厂车辆销售
        // createSale();
        //4.电池厂售后
       //createReplaceBattery();
        //5.主机厂维修
      //createReplaceRecord();
        //6.电池厂维修
        //createFactoryRepair();
        //7.主机厂回收网点入库
        //createRecoverStorage();
        //8.主机厂回收网点退役
       // createRetireVehicle();
        //9.主机厂车辆换电入库   wufanhui
        //createReplaceBatteryInStorage();
        //10.主机厂车辆换电记录
       // createReplaceReplacement();
        //11.主机厂车辆换电维修   wufanhui
        //createReplaceRepair();
        //12.主机厂车辆换电退役
      // createReplaceRetire();
        //13.电池厂电池退役  wufanhui
        //createRetireFactory();
        //14.江铃判断导入的电池包编码是否存在与电池生产入库中
        //createVehicleImportCode();
        //15.江铃判断导入的车辆维修记录中电池包。模块是否存在与售后服务信息中
        //createReplaceUpdateCode();
        //16.江铃在导入车辆维修信息时判断更换前编码与对应的VIN是绑定的
        //createVehicleBindingsVIN();
        //17.厦门金龙通过储能装置型号查询包编码型号 返回值问题
        //findPackBySystem();
        //18.上传vin
        //replaceVehicle();
        //19.主机厂车辆换电出库
        //replaceBatteryOut();


        //20.广汽本田通过vin查询包编码
        //findPackByVin();
        //21.广汽本田查询售后库存有没有指定编码
        //FindCodeBySail();
        //22.东风根据vin取车和包信息
        //findVinByVehicle();
        //28.晶马包编码查包型号
        //getModelByPackCode();
        //29.导入车辆底盘
        //receiveChassis();
        //30.导入整车信息
       // receiveVehicleFull();
        //31.导入底盘推送
        //receiveDivideChassis();
        //32.导入售后推送
        //receiveDivideRepairBattery();
        //33.导入维修推送
        //receiveDivideRepair();
        //34.导入电池厂退役推送
        //receiveDivideRetired();
        //35.获取推送底盘电池数据
        //getDivideChassis();
        //36.获取推送售后电池信息
        //getDivideRepairBattery();
        //37.获取推送维修信息
        //getDivideRepair();

        //38.依据单体厂商规格查询单体信息
        //getCellsByCode();
        //39.依据模块厂商规格查询模块信息
        //getModulesByCode();
        //40.依据包厂商规格查询包信息
        //getPacksByCode();
        //41.依据vin查询车辆生产信息
        //getVehiclesByVin();
        //42.依据vin查询车辆维修信息
        //getReplaceInformationByVin();
        //43.依据oldCode查询退役信息
       // getRetiredInfoByOldCode();
        //44.依据vin查询电池信息
       //getBatteryInfoByVin();
        //45.获取推送退役信息
        //getDivideRetired();

        //47.依据vin查询车辆销售信息
        //getVehiclesSailByVin();

        //48.依据配置号查询车辆型号信息
        //getVehModelByVehModelId();

        //依据时间查询电话号码，和提示信息
        //selectMobileAndContent();

        test();

        long end = System.currentTimeMillis();
        System.out.println("完成" + (end - start));
    }

    public static void createVehiclePack() {
        //电池包数据集
        List<String> packList = new ArrayList<String>();
        packList.add("P09180");
        packList.add("P09181");
        //车辆电池数据
        Map<String, Object> vinMap = new HashMap<String, Object>();
        vinMap.put("vin", "LGWEESK55SH210918");
        vinMap.put("vehModelName", "JLCAR4");
        vinMap.put("packCodeList", packList);
        vinMap.put("vehicleType", "1");
        vinMap.put("offlineProductionTime", "2019-09-27 00:00:00");
        vinMap.put("vehicleBrand", "奔驰");
        vinMap.put("vehicleName", "测试车");

        //电池包数据集
        List<String> packList1 = new ArrayList<String>();
        packList1.add("P09270");
        //车辆电池数据
        Map<String, Object> vinMap1 = new HashMap<String, Object>();
        vinMap1.put("vin", "TESTDDDDK55HE0130");
        vinMap1.put("vehModelName", "TEST01车");
        vinMap1.put("packCodeList", packList1);
        vinMap1.put("vehicleType", "1");
        vinMap1.put("offlineProductionTime", "2019-09-27 00:00:00");
        vinMap1.put("vehicleBrand", "奔驰");
        vinMap1.put("vehicleName", "测试车");



        List<Map<String, Object>> vinList = new ArrayList<Map<String, Object>>();
        vinList.add(vinMap);
        vinList.add(vinMap1);
        Map<String, Object> vehicleMap = new HashMap<String, Object>();
        vehicleMap.put("vinList", vinList);

        String data=new JsonSerializer().deep(true).serialize(packList);

        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/vehicle/receiveVehicleProduce", vehicleMap, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createBatteryPack() {

        //电池单体数据集
        List<String> cellList = new ArrayList<String>();
        cellList.add("C101212");
        //moduleList数据集
        Map<String, Object> module = new HashMap<String, Object>();
        module.put("code", "M101212");
        module.put("cellList", cellList);
        module.put("modelId", "11模块");
        module.put("cellModelId", "1102DT");
        //电池模组数据集
        List<Map<String, Object>> moduleList = new ArrayList<Map<String, Object>>();
        moduleList.add(module);
        //requestMsg数据集
        Map<String, Object> pack = new HashMap<String, Object>();
        pack.put("code", "P101212");
        pack.put("moduleList", moduleList);
        pack.put("serial", "1");
        pack.put("modelId", "11号包");
        pack.put("systemId", "S001");
        pack.put("systemModelId", "zhangS");
        pack.put("orderNo", "o001");
        pack.put("factorySystem", "00001012201912");



        //电池单体数据集
        List<String> cellList1 = new ArrayList<String>();
        cellList1.add("C09261");
        //moduleList数据集
        Map<String, Object> module1 = new HashMap<String, Object>();
        module1.put("code", "M09261");
        module1.put("cellList", cellList1);
        module1.put("modelId", "11模块");
        module1.put("cellModelId", "1102DT");
        //电池模组数据集
        List<Map<String, Object>> moduleList1 = new ArrayList<Map<String, Object>>();
        moduleList1.add(module1);
        //requestMsg数据集
        Map<String, Object> pack1 = new HashMap<String, Object>();
        pack1.put("code", "P09261");
        pack1.put("moduleList", moduleList1);
        pack1.put("serial", "1");
        pack1.put("modelId", "11号包");
        pack1.put("systemId", "S001");
        pack1.put("systemModelId", "zhangS");
        pack1.put("orderNo", "o001");

        List<Map<String, Object>> packList = new ArrayList<Map<String, Object>>();
        //packList.add(pack1);
        packList.add(pack);

        String data=new JsonSerializer().deep(true).serialize(packList);
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/vehicle/receiveBatteryProduce", packList, factory_token, factory_key);
        String result = parsToMap(response, factory_key);
        System.out.println(result);
    }

    public static void createSale() {
        List<Map<String, Object>> vehicleList = new ArrayList<Map<String, Object>>();
        Map<String, Object> vehicle = new HashMap<String, Object>();
        vehicle.put("vin", "TESTDDDDK55HE0130");
        vehicle.put("licensePlate", "京EZ0911");
        vehicle.put("vehTypeName", "111808");
        vehicle.put("saleTime", "2019-09-18");
        vehicle.put("saleArea", "北京市昌平区");
        vehicle.put("epname", "吉利汽车");
        vehicle.put("epaddress", "济南市天桥区西工商河路13号翡翠郡南区32号楼1单元613室");
        vehicle.put("epcode", "123456789123456789");

        Map<String, Object> vehicle1 = new HashMap<String, Object>();
        vehicle1.put("vin", "LGWEESK55SH210910");
        vehicle1.put("licensePlate", "京EZ0912");
        vehicle1.put("vehTypeName", "111808");
        vehicle1.put("saleTime", "2019-09-18");
        vehicle1.put("saleArea", "北京市昌平区");
        vehicle1.put("epname", "吉利汽车");
        vehicle1.put("epaddress", "济南市天桥区西工商河路13号翡翠郡南区32号楼1单元613室");
        vehicle1.put("epcode", "123456789123456789");

        vehicleList.add(vehicle);
        vehicleList.add(vehicle1);

        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/receiveSaleVehicle", vehicleList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createReplaceBattery() {
        //电池单体
        Map<String,Object> cell1=new HashMap<String,Object>();
        cell1.put("code","CR9171");
        cell1.put("type","C");
        List<Map<String,Object>> cellList1=new ArrayList<Map<String,Object>>();
        cellList1.add(cell1);
        //电池模块
        Map<String,Object> module1=new HashMap<String,Object>();
        module1.put("code","MR9117");
        module1.put("childCodeList",cellList1);
        module1.put("modelId","11模块");
        module1.put("type","M");
        List<Map<String,Object>> moduleList1=new ArrayList<Map<String,Object>>();
        moduleList1.add(module1);
        //电池包
        Map<String,Object> pack1=new HashMap<String,Object>();
        pack1.put("code","PR09171");
        pack1.put("childCodeList",moduleList1);
        pack1.put("modelId","11号包");
        pack1.put("type","P");
        pack1.put("orderCode","R001");

//        //模块
//        //电池单体
//        Map<String, Object> cell = new HashMap<String, Object>();
//        cell.put("code", "C09162");
//        cell.put("type", "C");
//        List<Map<String, Object>> cellList = new ArrayList<Map<String, Object>>();
//        cellList.add(cell);
//        //电池模块
//        Map<String, Object> pack1 = new HashMap<String, Object>();
//        pack1.put("code", "M09162");
//        pack1.put("childCodeList", cellList);
//        pack1.put("modelId", "M1");
//        pack1.put("type", "M");
//        pack1.put("orderCode", "00000001");
//
        List<Map<String, Object>> packList = new ArrayList<Map<String, Object>>();
        packList.add(pack1);
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/sail/receiveReplaceBattery", packList, factory_token, factory_key);
        String result = parsToMap(response, factory_key);
        System.out.println(result);
    }

    public static void createReplaceRecord() {
        List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();

        Map<String, Object> record2 = new HashMap<String, Object>();
        record2.put("vin", "TESTDDDDK55HE0130");
        record2.put("replaceDate", "2018-09-26");
        record2.put("oldCode", "TEST101DDB-009");
        record2.put("whereaboutsId", "123456789123456789");
        record2.put("whereaboutsName", "回收网点");
        record2.put("newCode", "PR09171");
        record2.put("batterySpecies", "P");

        recordList.add(record2);
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/sail/receiveReplaceRecord", recordList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createFactoryRepair() {
//        //电池单体
//        Map<String, Object> cell1 = new HashMap<String, Object>();
//        cell1.put("code", "C311R");
//        cell1.put("type", "C");
//        List<Map<String, Object>> cellList1 = new ArrayList<Map<String, Object>>();
//        cellList1.add(cell1);
//        //电池模组
//        Map<String, Object> module1 = new HashMap<String, Object>();
//        module1.put("code", "M310R");
//        module1.put("childCodeList", cellList1);
//        module1.put("type", "M");
//        List<Map<String, Object>> moduleList1 = new ArrayList<Map<String, Object>>();
//        moduleList1.add(module1);
//        //电池包
//        Map<String, Object> pack1 = new HashMap<String, Object>();
//        pack1.put("code", "P900R");
//        pack1.put("childCodeList", moduleList1);
//        pack1.put("type", "P");
//        pack1.put("orderCode", "234567898765");
//        List<Map<String, Object>> repairList = new ArrayList<Map<String, Object>>();
//        //车辆电池数据集
//        Map<String, Object> repair = new HashMap<String, Object>();
//        repair.put("vin", "LNBSCBF0201909112");
//        repair.put("replaceDate", "2018-12-11");
//        repair.put("oldCode", "P900");
//        repair.put("whereaboutsId", "123456789123456789");
//        repair.put("whereaboutsName", "维修厂");
//        repair.put("newCodeBean", pack1);
//        repair.put("batterySpecies", "P");
//        repair.put("maintainstatus", "0");
//        repairList.add(repair);


        //更换后编码是纸机厂维修换下的编码
//        Map<String,Object> cell1=new HashMap<String,Object>();
//        cell1.put("code","C100");
//        cell1.put("type","C");
//        List<Map<String,Object>> cellList1=new ArrayList<Map<String,Object>>();
//        cellList1.add(cell1);
//        //电池模组
//        Map<String,Object> module1=new HashMap<String,Object>();
//        module1.put("code","M100");
//        module1.put("childCodeList",cellList1);
//        module1.put("type","M");
//        List<Map<String,Object>> moduleList1=new ArrayList<Map<String,Object>>();
//        moduleList1.add(module1);
//        //电池包
//        Map<String,Object> pack1=new HashMap<String,Object>();
//        pack1.put("code","P100");
//        pack1.put("childCodeList",moduleList1);
//        pack1.put("type","P");
//        pack1.put("orderCode","234567898765");
//        List<Map<String,Object>> repairList=new ArrayList<Map<String,Object>>();
//        //车辆电池数据集
//        Map<String,Object> repair=new HashMap<String,Object>();
//        repair.put("vin", "LNBSCB3F0DD130955");
//        repair.put("replaceDate", "2018-01-05");
//        repair.put("oldCode", "P200");
//        repair.put("whereaboutsId", "123456789123456789");
//        repair.put("whereaboutsName", "维修厂");
//        repair.put("newCodeBean", pack1);
//        repair.put("batterySpecies", "P");
        //模块
        //电池单体
        Map<String,Object> cell2=new HashMap<String,Object>();
        cell2.put("code","C311R2");
        cell2.put("type","C");
        List<Map<String,Object>> cellList2=new ArrayList<Map<String,Object>>();
        cellList2.add(cell2);
        //电池模组
        Map<String,Object> module2=new HashMap<String,Object>();
        module2.put("code","M310R2");
        module2.put("childCodeList",cellList2);
        module2.put("modelId","zhangM");
        module2.put("type","M");
        module2.put("orderCode","234567898765");
        List<Map<String,Object>> repairList=new ArrayList<Map<String,Object>>();
        //车辆电池数据集
        Map<String,Object> repair=new HashMap<String,Object>();
        repair.put("vin", "LGWEESK55SHE6CYMD");
        repair.put("replaceDate", "2018-01-05");
        repair.put("oldCode", "LP-HM-1");
        repair.put("whereaboutsId", "123456789123456789");
        repair.put("whereaboutsName", "维修厂");
        repair.put("newCodeBean", module2);
        repair.put("batterySpecies", "M");
        repairList.add(repair);
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/sail/receiveReplaceOld", repairList, factory_token, factory_key);
        String result = parsToMap(response, factory_key);
        System.out.println(result);
    }

    public static void createRecoverStorage() {
        List<Map<String, Object>> recoverStorageList = new ArrayList<Map<String, Object>>();
        Map<String, Object> recoverStorage = new HashMap<String, Object>();
        recoverStorage.put("storageDate", "2018-01-05");
        recoverStorage.put("recoverUnitCode", "123456789123456789");
        recoverStorage.put("recoverUnitName", "维修厂");
        recoverStorage.put("code", "P911LL2");
        recoverStorage.put("batterySpecies", "P");

        Map<String, Object> recoverStorage2 = new HashMap<String, Object>();
        recoverStorage2.put("storageDate", "2018-11-05");
        recoverStorage2.put("recoverUnitCode", "123456789123456789");
        recoverStorage2.put("recoverUnitName", "维修厂");
        recoverStorage2.put("code", "M911LL2");
        recoverStorage2.put("batterySpecies", "M");

        recoverStorageList.add(recoverStorage);
        recoverStorageList.add(recoverStorage2);
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/recover/receiveRecoverStorage", recoverStorageList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createRetireVehicle() {
        List<Map<String, Object>> retireVehicleList = new ArrayList<Map<String, Object>>();
        Map<String, Object> retireVehicle = new HashMap<String, Object>();
        retireVehicle.put("code", "TEST101DDB-009");
        retireVehicle.put("whereaboutsCode", "123456789123412341");
        retireVehicle.put("whereaboutsName", "维修厂");
        retireVehicle.put("retireDate", "2018-01-05");
        retireVehicle.put("batterySpecies", "P");
        retireVehicle.put("retireUnitCode", "123456789123123123");
        retireVehicle.put("retireUnitName", "退役厂商");
        retireVehicle.put("factoryType", "5");
        retireVehicle.put("batteryType", "A");
        retireVehicle.put("batteryWeight", "1.05");

//        Map<String, Object> retireVehicle2 = new HashMap<String, Object>();
//        retireVehicle2.put("code", "M911LL2");
//        retireVehicle2.put("whereaboutsCode", "123456789123412341");
//        retireVehicle2.put("whereaboutsName", "维修厂");
//        retireVehicle2.put("retireDate", "2018-11-05");
//        retireVehicle2.put("batterySpecies", "M");
//        retireVehicle2.put("retireUnitCode", "123456789123123123");
//        retireVehicle2.put("retireUnitName", "退役厂商");
//        retireVehicle2.put("factoryType", "5");
//        retireVehicle2.put("batteryType", "A");
//        retireVehicle2.put("batteryWeight", "1.05");

        retireVehicleList.add(retireVehicle);
//        retireVehicleList.add(retireVehicle2);

        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/sail/receiveRetiredOldVehicle", retireVehicleList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createReplaceBatteryInStorage() {
        List<String> cellList1 = new ArrayList<String>();
        cellList1.add("C09121");
        Map<String, Object> module1 = new HashMap<String, Object>();
        module1.put("code", "M09121");
        module1.put("cellList", cellList1);
        module1.put("cellCodeSize", "1");
        List<Map<String, Object>> moduleList1 = new ArrayList<Map<String, Object>>();
        moduleList1.add(module1);
        Map<String, Object> pack1 = new HashMap<String, Object>();
        pack1.put("code", "P09121");
        pack1.put("moduleList", moduleList1);
        pack1.put("replaceUnitCode", "123456789123412342");
        pack1.put("replaceUnitName", "换电厂");

        List<String> cellList2 = new ArrayList<String>();
        cellList2.add("C09122");
        Map<String, Object> module2 = new HashMap<String, Object>();
        module2.put("code", "M09122");
        module2.put("cellList", cellList2);
        module2.put("cellCodeSize", "1");
        List<Map<String, Object>> moduleList2 = new ArrayList<Map<String, Object>>();
        moduleList2.add(module2);
        Map<String, Object> pack2 = new HashMap<String, Object>();
        pack2.put("code", "P09122");
        pack2.put("moduleList", moduleList2);
        pack2.put("replaceUnitCode", "123456789123412342");
        pack2.put("replaceUnitName", "换电厂");

        List<Map<String, Object>> packList = new ArrayList<Map<String, Object>>();
//        packList.add(pack1);
        packList.add(pack2);

        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/replaceBattery/batteryInStorage", packList, vehicle_token, vehicle_key);
        String result = parsToMap(response, factory_key);
        System.out.println(result);
    }

    public static void createReplaceReplacement() {
        List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();
        Map<String, Object> record = new HashMap<String, Object>();
        record.put("vin", "TESTJ29Y0KDA62486");
        record.put("oldCode", "A01PAC812345UYYBC2345788");
        record.put("newCode", "P09122");
        record.put("replaceUnitCode", "123456789123412342");
        record.put("replaceUnitName", "换电厂");
        record.put("replaceDate", "2018-01-05 12:23:00");
        recordList.add(record);
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/replaceBattery/replacementBattery", recordList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createReplaceRepair() {
        Map<String, Object> cell1 = new HashMap<String, Object>();
        cell1.put("code", "C09200");
        cell1.put("type", "C");
        List<Map<String, Object>> cellList1 = new ArrayList<Map<String, Object>>();
        cellList1.add(cell1);
        Map<String, Object> module1 = new HashMap<String, Object>();
        module1.put("code", "M09200");
        module1.put("childCodeList", cellList1);
        module1.put("type", "M");
        List<Map<String, Object>> moduleList1 = new ArrayList<Map<String, Object>>();
        moduleList1.add(module1);
        Map<String, Object> pack1 = new HashMap<String, Object>();
        pack1.put("code", "P09200");
        pack1.put("childCodeList", moduleList1);
        pack1.put("type", "P");
        List<Map<String, Object>> repairList = new ArrayList<Map<String, Object>>();
        Map<String, Object> repair = new HashMap<String, Object>();
      //  repair.put("repaireNo", "LNBSCB3F0DD130955");
        repair.put("repairDate", "2018-01-05");
        repair.put("oldCode", "HDZPB556HDZS5546DHXP0044");
        repair.put("newCode", "P09200");
        repair.put("newCodeBean", pack1);
        repair.put("replaceUnitCode", "123456789123412342");
        repair.put("replaceUnitName", "换电厂");
        repair.put("whereaboutsCode", "123456789123412341");
        repair.put("whereaboutsName", "维修厂");
        //repair.put("repaireNo", "111111");
        repairList.add(repair);
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/replaceBattery/replaceRepairBattery", repairList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createReplaceRetire() {
        List<Map<String, Object>> retireVehicleList = new ArrayList<Map<String, Object>>();
        Map<String, Object> retireVehicle = new HashMap<String, Object>();
        retireVehicle.put("code", "P0920");
        retireVehicle.put("whereaboutsCode", "123456789123412342");
        retireVehicle.put("whereaboutsName", "换电厂");
        retireVehicle.put("retireDate", "2018-09-20");
        retireVehicle.put("batterySpecies", "P");
        retireVehicle.put("retireUnitCode", "123456789123123123");
        retireVehicle.put("retireUnitName", "退役厂商");
        retireVehicle.put("factoryType", "4");
        retireVehicle.put("batteryType", "A");
        retireVehicle.put("batteryWeight", "1.05");
        retireVehicleList.add(retireVehicle);
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/sail/receiveRetiredOldReplace", retireVehicleList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createRetireFactory() {
        List<Map<String, Object>> retireVehicleList = new ArrayList<Map<String, Object>>();
        Map<String, Object> retireVehicle = new HashMap<String, Object>();
        retireVehicle.put("code", "P091723456");
        retireVehicle.put("whereaboutsCode", "123456789123456789");
        retireVehicle.put("whereaboutsName", "电池厂");
        retireVehicle.put("retireDate", "2018-01-05");
        retireVehicle.put("batterySpecies", "P");
        retireVehicle.put("retireUnitCode", "123456789123123123");
        retireVehicle.put("retireUnitName", "退役厂商");
        retireVehicle.put("factoryType", "2");
        retireVehicle.put("batteryType", "A");
        retireVehicle.put("batteryWeight", "1.09");
        retireVehicleList.add(retireVehicle);
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/sail/receiveRetiredOld", retireVehicleList, factory_token, factory_key);
        String result = parsToMap(response, factory_key);
        System.out.println(result);
    }

    //在导入车辆生产信息时查询生产电池入库中有没有电池包编码
    public static void createVehicleImportCode() {
        List<String> code = new ArrayList<String>();
        code.add("TEST101DDB-014");
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/vehicle/getBatteryIsImport", code, factory_token, factory_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);

    }

    //在导入车辆维修信息时判断更换后的编码是否在售后服务信息中存在
    public static void createReplaceUpdateCode() {
        List<String> packCode = new ArrayList<String>();
        packCode.add("P900");
       // packCode.add("M711");
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/vehicle/getReplaceUpdate", packCode, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);

    }

    //在导入车辆维修信息时判断更换前编码与对应的VIN是绑定的
    public static void createVehicleBindingsVIN() {
        List<Map<String, Object>> BindingsVIN = new ArrayList<Map<String, Object>>();
        Map<String, Object> packModelVin = new HashMap<String, Object>();
        packModelVin.put("code", "TSSS101DDBSHA-9910");
//        packModelVin.put("model","M711");
        packModelVin.put("vin", "TESTSHADK55HE0203");
        BindingsVIN.add(packModelVin);
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/vehicle/getBatteryIsBindingsVIN", BindingsVIN, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);

    }


    //厦门金龙根据储能装置型号查询包型号
    public static void findPackBySystem() {
        List<String> factoryCode = new ArrayList<String>();
        factoryCode.add("CN03181556");
//        factoryCode.add("222");
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/vehicle/findPackBySystem", factoryCode, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);

    }

    //上传vin
    public static void replaceVehicle() {
        List<Map<String, Object>> replaceVehicleVin = new ArrayList<Map<String, Object>>();
        Map<String, Object> vin = new HashMap<String, Object>();
        vin.put("vin", "LNBSCBF0201909112");
        vin.put("replaceFactoryCode", "123456789123123123");
        vin.put("replaceFactoryName", "测试换电厂商");
        replaceVehicleVin.add(vin);
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/replaceBattery/replaceVehicle", replaceVehicleVin, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    //换电维修出库
    public static void replaceBatteryOut() {
        List<Map<String, Object>> replaceVehicleVin = new ArrayList<Map<String, Object>>();
        Map<String, Object> vin = new HashMap<String, Object>();
        vin.put("repairDate", "2018-01-01");
        vin.put("replaceUnitCode", "123456789123123123");
        vin.put("replaceUnitName", "测试换电厂商");
        vin.put("oldCode", "TSSS101DDBSHA-9913");
        vin.put("whereaboutsName", "换电厂");
        vin.put("whereaboutsCode", "123456789123412342");
        replaceVehicleVin.add(vin);
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/replaceBattery/ReplaceBatteryOut", replaceVehicleVin, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    //通过vin查询包编码
    public static void findPackByVin() {
        List<String> vin = new ArrayList<String>();
        vin.add("TESTDDDDK55HE0130");
        vin.add("00Y8GJ82S9EN49XXM");
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/vehicle/findPackByVin", vin, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    //广汽本田查询售后库存有没有指定包编码
    public static void FindCodeBySail() {
        List<String> code = new ArrayList<String>();
        code.add("SS-DCB-A-05");
        code.add("HCY2018090809HCYILY0007");
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/vehicle/findCodeBySail", code, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }


    //东风商用根据vin查询车辆电池信息
    public static void findVinByVehicle() {
        List<String> vin = new ArrayList<String>();
        vin.add("TESTDDDDK55HE0131");

        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/vehicle/findVinByVehicle", vin, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    //晶马查询包编码查询包型号
    public static void getModelByPackCode() {
        List<String> code = new ArrayList<String>();
        code.add("TEST101DDB-014");
        code.add("2");

        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/jingMa/getModelByPackCode", code, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    //导入车辆底盘
    public static void receiveChassis() {
        //电池包数据集
        List<String> packList = new ArrayList<String>();
        packList.add("P091723456");
        //车辆电池数据
        Map<String, Object> vinMap = new HashMap<String, Object>();
        vinMap.put("vin", "LGWEESK5520199171");
        vinMap.put("vehModelName", "ZN6453V1YBEV");
        vinMap.put("packCodeList", packList);
        List<Map<String, Object>> vinList = new ArrayList<Map<String, Object>>();
        vinList.add(vinMap);

        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/vehicle/receiveChassis", vinList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    //整车信息接口
    public static void receiveVehicleFull() {
        //车辆数据
        Map<String, Object> vinMap = new HashMap<String, Object>();
        vinMap.put("vin", "LGWEESK55SHE20916");
        vinMap.put("vehModelName", "ZN6453V1YBEV");
        vinMap.put("systemCode","S001");
        vinMap.put("vehicleType", "1");
        vinMap.put("offlineProductionTime", "2018-01-31 00:00:00");
        vinMap.put("vehicleBrand", "奔驰");
        vinMap.put("vehicleName", "测试车");
        List<Map<String, Object>> vinList = new ArrayList<Map<String, Object>>();
        vinList.add(vinMap);

        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/vehicle/receiveVehicleFull", vinList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    //导入底盘推送
    public static void receiveDivideChassis() {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "LGWEESK5520199171");
        map.put("directionCode", "92140727MA0JWCXXXX");
        map.put("directionName", "XXX改装厂");
        map.put("isUse", "1");
        List<Map<String, Object>> vinList = new ArrayList<Map<String, Object>>();
        vinList.add(map);

        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/vehicle/receiveDivideChassis", vinList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    //售后推送
    public static void receiveDivideRepairBattery() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "PR09171");
        map.put("directionCode", "92140727MA0JWCXXXX");
        map.put("directionName", "XXX改装厂");
        List<Map<String, Object>> vinList = new ArrayList<Map<String, Object>>();
        vinList.add(map);

        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/vehicle/receiveDivideRepairBattery", vinList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    //维修推送
    public static void receiveDivideRepair() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "PR09171");
        map.put("directionCode", "92140727MA0JWCXXXX");
        map.put("directionName", "XXX改装厂");
        map.put("isUse", "1");
        map.put("repairDate", "2018-11-05");
        List<Map<String, Object>> vinList = new ArrayList<Map<String, Object>>();
        vinList.add(map);

        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/vehicle/receiveDivideRepair", vinList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    //导入电池厂退役推送
    public static void receiveDivideRetired() {
        List<String> vin = new ArrayList<String>();
        vin.add("P091723456");

        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/vehicle/receiveDivideRetired", vin, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    //获取推送底盘电池数据
    public static void getDivideChassis() {
        List<String> vin = new ArrayList<String>();
        vin.add("LGWEESK55SH210916");

        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/vehicle/getDivideChassis", vin, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    //获取售后推送
    public static void getDivideRepairBattery() {
        List<String> code = new ArrayList<String>();
        code.add("M09161");

        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/vehicle/getDivideRepairBattery", code, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void getDivideRepair() {
        List<Map<String, Object>> code = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("vin", "LGWEESK5520199171");
        map.put("replaceDate", "2018-11-05");
        code.add(map);
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/vehicle/getDivideRepair", code, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void getCellsByCode(){
        //测试数据
        List<Map<String,Object>> cells=new ArrayList<Map<String,Object>>();

        Map<String,Object> cell1=new HashMap<String,Object>();
        cell1.put("modelCode","HDDT-001");

        Map<String,Object> cell2=new HashMap<String,Object>();
        cell2.put("modelCode","BSJCYY7");


        cells.add(cell1);
        cells.add(cell2);

        //模拟请求
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/vehicle/getCellsByCode",cells, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        //结果
        System.out.println("结果:\n");
        System.out.println(result);
    }

    public static void getModulesByCode(){
        //测试数据
        List<Map<String,Object>> modules=new ArrayList<Map<String,Object>>();

        Map<String,Object> module1=new HashMap<String,Object>();
        module1.put("modelCode","HDMK-001");

        Map<String,Object> module2=new HashMap<String,Object>();
        module2.put("modelCode","MFMM1");



        modules.add(module1);
        modules.add(module2);

        //模拟请求
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/vehicle/getModulesByCode",modules, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        //结果
        System.out.println("结果:\n");
        System.out.println(result);
    }

    public static void getPacksByCode(){
        //测试数据
        List<Map<String,Object>> packs=new ArrayList<Map<String,Object>>();

        Map<String,Object> pack1=new HashMap<String,Object>();
        pack1.put("modelCode","HDBP-001");

//        Map<String,Object> pack2=new HashMap<String,Object>();
//        pack2.put("modelCode","hanxiaoqing");
//
//        Map<String,Object> pack3=new HashMap<String,Object>();
//        pack3.put("modelCode",null);

        packs.add(pack1);
//        packs.add(pack2);
//        packs.add(pack3);

        //模拟请求
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/vehicle/getPacksByCode",packs, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        //结果
        System.out.println("结果:\n");
        System.out.println(result);
    }

    //吉利第三方 生产
    public static void getVehiclesByVin(){
        //测试数据
        List<Map<String,Object>> Vehicles=new ArrayList<Map<String,Object>>();
        //1.正确vin
        Map<String,Object> Vehicle1=new HashMap<String,Object>();
        Vehicle1.put("vin","TESTDDDDK55HE0130");
        //2.系统未录入vin
        Map<String,Object> Vehicle2=new HashMap<String,Object>();
        Vehicle2.put("vin","TESTDDDDK55HE0135");
        //3.前后带空格
        Map<String,Object> Vehicle3=new HashMap<String,Object>();
        Vehicle3.put("vin","TESTDDDDK55HE0131 ");
        //4.中间带空格
        Map<String,Object> Vehicle4=new HashMap<String,Object>();
        Vehicle4.put("vin","TESTDDDDK 55HE0132");
        //5.匹配一部分
        Map<String,Object> Vehicle5=new HashMap<String,Object>();
        Vehicle5.put("vin","TESTDDDDK55HE0131");
        //6.已匹配完成
        Map<String,Object> Vehicle6=new HashMap<String,Object>();
        Vehicle6.put("vin","TESTDDDDK55HE0132");

//        Vehicles.add(Vehicle1);
//        Vehicles.add(Vehicle2);
//        Vehicles.add(Vehicle3);
//        Vehicles.add(Vehicle4);
          Vehicles.add(Vehicle5);
          Vehicles.add(Vehicle6);
        //模拟请求
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/vehicle/getVehiclesByVin",Vehicles, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        //结果
        System.out.println("结果:\n");
        System.out.println(result);
    }
    //吉利第三方 销售
    public static void getVehiclesSailByVin(){
        //测试数据
        List<Map<String,Object>> Vehicles=new ArrayList<Map<String,Object>>();
        //1.正确
        Map<String,Object> Vehicle1=new HashMap<String,Object>();
        Vehicle1.put("vin","TESTDDDDK55HE0132");
        //2.错误
        Map<String,Object> Vehicle2=new HashMap<String,Object>();
        Vehicle2.put("vin","TESTDDDDK55HE0130");
        //3.前后带空格
        Map<String,Object> Vehicle3=new HashMap<String,Object>();
        Vehicle3.put("vin","TESTDDDDK55HE0133 ");
        //4.中间带空格
        Map<String,Object> Vehicle4=new HashMap<String,Object>();
        Vehicle4.put("vin","TESTDDD DK55HE0134");

        Vehicles.add(Vehicle1);
        Vehicles.add(Vehicle2);
        Vehicles.add(Vehicle3);
        Vehicles.add(Vehicle4);

        //模拟请求
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/vehicle/getVehiclesSailByVin",Vehicles, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        //结果
        System.out.println("结果:\n");
        System.out.println(result);
    }

    //吉利第三方 车辆型号
    public static void getVehModelByVehModelId(){
        //测试数据
        List<Map<String,Object>> vehmodels=new ArrayList<Map<String,Object>>();
        //1.正确
        Map<String,Object> vehmodel1=new HashMap<String,Object>();
        vehmodel1.put("vehModelId","TEST001");
        //2.错误
        Map<String,Object> vehmodel2=new HashMap<String,Object>();
        vehmodel2.put("vehModelId","TEST0025");
        //3.前后带空格
        Map<String,Object> vehmodel3=new HashMap<String,Object>();
        vehmodel3.put("vehModelId","MFBSJ001 ");
        //4.中间带空格
        Map<String,Object> vehmodel4=new HashMap<String,Object>();
        vehmodel4.put("vehModelId","MFBS J001");

        vehmodels.add(vehmodel1);
        //vehmodels.add(vehmodel2);
        //vehmodels.add(vehmodel3);
        //vehmodels.add(vehmodel4);


        //模拟请求
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/vehicle/getVehModelByVehModelId",vehmodels, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        //结果
        System.out.println("结果:\n");
        System.out.println(result);
    }

    public static void getReplaceInformationByVin(){
        //测试数据
        List<Map<String,Object>> replaces=new ArrayList<Map<String,Object>>();

        Map<String,Object> replace1=new HashMap<String,Object>();
        replace1.put("vin","LNBSCBF0201909112");



        replaces.add(replace1);

        //模拟请求
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/vehicle/getReplaceInformationByVin",replaces, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        //结果
        System.out.println("结果:\n");
        System.out.println(result);
    }

    //退役
    public static void getRetiredInfoByOldCode(){
        //测试数据
        List<Map<String,Object>> retireds=new ArrayList<Map<String,Object>>();

        Map<String,Object> retired1=new HashMap<String,Object>();
        retired1.put("oldCode","P09122");

        Map<String,Object> retired2=new HashMap<String,Object>();
        retired2.put("oldCode","MF-MK-4");

        Map<String,Object> retired3=new HashMap<String,Object>();
        retired3.put("oldCode","MDMD-A-1");

        retireds.add(retired1);
        retireds.add(retired2);
        retireds.add(retired3);
        //模拟请求
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/vehicle/getRetiredInfoByOldCode",retireds, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        //结果
        System.out.println("结果:\n");
        System.out.println(result);
    }

    //vin查询电池信息
    public static void getBatteryInfoByVin(){
        //测试数据
        List<Map<String,Object>> packs=new ArrayList<Map<String,Object>>();

        Map<String,Object> pack1=new HashMap<String,Object>();
        pack1.put("vin","TESTDDDDK55HE0131");

        Map<String,Object> pack2=new HashMap<String,Object>();
        pack2.put("vin","ZHENGCHEVIN123450");



        packs.add(pack1);
        packs.add(pack2);



        //模拟请求
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/vehicle/getBatteryInfoByVin",packs, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        //结果
        System.out.println("结果:\n");
        System.out.println(result);
    }

    //查电话信息和提示内容
    public static void selectMobileAndContent(){
        //测试数据
        Object param=null;

        //模拟请求
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/sweepReportVehivles/selectMobileAndContent",param, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        //结果
        System.out.println("结果:\n");
        System.out.println(result);
    }


    public static void getDivideRetired() {
        List<String> code = new ArrayList<String>();
        code.add("P09122");
        HttpResponse response = send(ip1 + "/bitnei/v1.0/battery/vehicle/getDivideRetired", code, vehicle_token, vehicle_key);
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

            System.out.println("requestData:"+requestMsg);
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
    //加密
    public static String encrypt(String content, String password) {
        try {
            if (isEmpty(content)) {
                return "";
            }
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
//            System.out.println(enCodeFormat);
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            BASE64Encoder coder = new BASE64Encoder();
            coder.encode(enCodeFormat);
//            System.out.println("coder.encode(enCodeFormat)  ==   "+coder.encode(enCodeFormat));
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
        return o != null && !"null".equals(o.toString()) ? o.toString().trim() : "";
    }
    //解密
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
        return request;
    }

    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }
        if ("".equals(FilterNull(o.toString()))) {
            return true;
        } else {
            return false;
        }
    }


    public static void test() {
        try {



            //String data=new JsonSerializer().deep(true).serialize(packList);
            String data="{\"vinList\":[{\"offlineProductionTime\":\"2018-01-01 00:00:00\",\"vehModelName\":\"1111\",\"vehicleBrand\":\"奔驰\",\"vehicleName\":\"测试车\",\"systemCode\":\"S001\",\"vin\":\"LNBSCB3F0DD130955\",\"vehicleType\":\"1\",\"packCodeList\":[\"P100\"]}]}";
            String token = "1GAHd1NqM+PdLTA2UeFqOzbONHntnR+Z";
            String key = "wdMSfIBVfa5qTb3J";
            String url = "http://218.17.29.94:7980/bitnei/v1.0/battery/vehicle/receiveVehicleProduce";
            //String url = "http://p-awsbj-tsp-baty-trace-987963663.cn-north-1.elb.amazonaws.com.cn:8080/bitnei/v1.0/battery/replaceBattery/batteryInStorage";
//            //数据解密
//            String testStr="Plaz3zmbj1c2JT677svz0g==";
//            String data2=decrypt(testStr,key);
//            System.out.println("data2"+data2);


            Map<String, Object> requestMap = new HashMap<String, Object>();
            String enStr = encrypt(data, key);
            String ceshiStr = encrypt("12345678","i+lWdDYqHx1DaTEK");
            System.out.println("加密后"+ceshiStr);

            requestMap.put("requestMsg", enStr);
            String sign = byteArrayToHexString(encryptHMAC(enStr.getBytes(), key));
            requestMap.put("sign", sign);

           // for(int i=0;i<50;i++){
                requestMap.put("timestamp", System.currentTimeMillis());
                HttpResponse response = getRequest(url, token, requestMap).send().unzip().unzip();
                ObjectMapper mapper = new ObjectMapper();
                Map map = mapper.readValue(response.body(), Map.class);
                String result = "";
                if (isNotEmpty(map.get("data"))) {
                    result = decrypt(map.get("data").toString(), key);
                } else {
                    result = decrypt(map.get("msg").toString(), key);
                }
                System.out.println(map.get("code") + "" + result);
            //}


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
