package com.zhang.hadoop.testMain;

import com.zhang.hadoop.util.AESUtil;
import com.zhang.hadoop.util.DateUtil;
import com.zhang.hadoop.util.SecurityUtil;
import jodd.json.JsonSerializer;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhang yufei on 2019/1/8.
 */
public class test {


    public static void main(String args[]) {
        Pattern p = Pattern.compile("^[A-Z0-9]{18}$|^[0-9]{9}$");
        System.out.println(p.matcher("91310000329555773R").matches());
        String a = "123456";
        System.out.println(a.substring(0, 3));

        String date = "2018.10";
        String a1[] = date.split("\\.");
        System.out.println(a1[0] + "\t" + a1[1]);

        Pattern p1 = Pattern.compile("^[\\u4e00-\\u9fa5A-Za-z()（）]+");
        System.out.println(p1.matcher("三星SDI匈牙利制造和经销封闭式股份公司").matches());


        String s1 = SecurityUtil.getMd5("123456");
        String enPsw = AESUtil.encrypt(s1, "vehicle");
        System.out.println(enPsw);

        //^[A-Z0-9]{18}$|^[0-9]{9}$
        //^[\S]{8,16}$
        Pattern p2 = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[\\S]{8,16}$");
        System.out.println("密码2：" + p2.matcher("123456Aa").matches());

        Pattern p3 = Pattern.compile("^([A-Z]*)[\\S]{0,16}$");
        System.out.println("密码3：" + p3.matcher("123456").matches());

        Long dayNum = DateUtil.getQuot(DateUtil.getNow(), "2017-02-18 00:00:00");
        System.out.println("间隔：" + dayNum + "天");

        String s2 = ";123";
        String s3[] = s2.split(";");
        System.out.println(s3[1]);


        Pattern p4 = Pattern.compile("(?m)^.*$");
        System.out.println("编码：" + p4.matcher("123456").matches());


        Pattern p5 = Pattern.compile("^(.*)$");
        System.out.println("编码：" + p5.matcher("").matches());

        Pattern p6 = Pattern.compile("^(.*)$");
        System.out.println("汉字：" + p6.matcher("张").matches());


        Pattern p7 = Pattern.compile("^[\\u4e00-\\u9fa5A-Za-z()（）]+");
        System.out.println("厂商名称：" + p7.matcher("DraexlmaierAutomotivprodukteGmbH").matches());

        try {
            Random rand = SecureRandom.getInstanceStrong();
            System.out.println(rand.nextInt(1000000000) + "");
        } catch (NoSuchAlgorithmException e) {

        }

        Pattern p8 = Pattern.compile("^[A-Z0-9]{3}[PMC]{1}[A-GZ]{1}[A-Z0-9]{2}[A-Z0-9]{7}[1-9A-HJ-NPR-TV-Y]{1}[1-9A-C]{1}[0-9A-HJ-NPR-TV-Y]{1}[0-9]{7}$");
        System.out.println("汉字：" + p8.matcher("001PABB12345671111234567").matches());


        List<Map<String,Object> > list=new LinkedList<>();
        Map<String,Object> map1=new HashMap<>();
        map1.put("modelId","M1");
        map1.put("num","2");
        list.add(map1);
        Map<String,Object> map2=new HashMap<>();
        map2.put("modelId","M2");
        map2.put("num","3");
        list.add(map2);
        Map<String,Object> map3=new HashMap<>();
        map3.put("modelInfo",new JsonSerializer().serialize(list));
        System.out.println(new JsonSerializer().deep(true).serialize(map3));

        List l1=new ArrayList(){{
            add("1");
            add("2");
            add("3");
            add("4");
            add("5");
            add("6");
            add("7");
            add("8");
            add("9");
            add("10");
        }};
        for(int i=0;i<l1.size();i++){
            for (int j=i+1;j<l1.size();j++){
//                System.out.println(l1.get(i)+"\t"+l1.get(j));
            }
        }

        List<String> list1=new ArrayList<>();
        System.out.println(new JsonSerializer().serialize(list1));
    }

    @Test
    public void test(){
        System.out.println(checkDecimal("10123.01",10,0));
    }

    public static boolean checkDecimal(String number,int length,int decimal){
        if(number.length()>length){
            return false;
        }
        Pattern p = Pattern.compile("^\\+{0,1}[1-9]\\d*");
        Matcher m = p.matcher(number);
        if (!m.matches()){
            if(decimal>0){
                Pattern p1 = Pattern.compile("\\+{0,1}[0]\\.[1-9]*|\\+{0,1}[1-9]\\d*\\.\\d*");
                Matcher m1 = p1.matcher(number);
                if (!m1.matches()){
                    return false;
                }
                String s1[]=number.split("\\.");
                if(s1[1].length()>decimal){
                    return false;
                }
            }else {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test1(){
        Map<String,List<String>> map=new HashMap<>();
        List<String> s1=new LinkedList<>();
        s1.add("n1");
        s1.add("c1");
        map.put("s1", s1);
        List<String> s2=new LinkedList<>();
        s2.add("n2");
        s2.add("c2");
        map.put("s2", s2);
        String result=new JsonSerializer().deep(true).serialize(map);
        System.out.println(result);
    }

    @Test
    public void test2(){
        String code="001PBANC000001A6F1000003 ";
        Pattern p = Pattern.compile("^[A-Z0-9]{24}$");
        Matcher m = p.matcher(code);
        System.out.println(m.matches());
    }

    public static boolean isCarNo(String carNo){
        Pattern p = Pattern.compile("^([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z0-9]{5,7}|[0-9]{6})[A-Z0-9挂学警港澳使领]{1}$|^民航[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼A-Z]{1}[A-Z0-9]{4,5}$");
        Matcher m = p.matcher(carNo);
        if (!m.matches()){
            return false;
        }
        return true;
    }

    @Test
    public void test3(){
        String carNo="民航A103J";
        System.out.println(isCarNo(carNo));
    }

    @Test
    public void test6(){
        int a=0;
        for(int i=2;i<=100;i++){
            if((i%2)==0){
                a=a+i;
            }else{
                a=a-i;
            }
        }
        System.out.println(a);
    }
}
