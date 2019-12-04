package com.zhang.hadoop.testMain;

import com.zhang.hadoop.util.AES;
import com.zhang.hadoop.util.DateUtil;
import com.zhang.hadoop.util.SecurityUtil;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
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
        String enPsw = AES.encrypt(s1, "vehicle");
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

        Pattern p6 = Pattern.compile("^[A-Z0-9]{3}[PMC]{1}[A-GZ]{1}[A-Z0-9]{2}[A-Z0-9]{7}[1-9A-HJ-NPR-TV-Y]{1}[1-9A-C]{1}[0-9A-HJ-NPR-TV-Y]{1}[0-9]{7}$");
        System.out.println("电池编码：" + p6.matcher("001PABB12345671111234567").matches());

        try {
            Random rand = SecureRandom.getInstanceStrong();
            System.out.println(rand.nextInt(1000000000) + "");
        } catch (NoSuchAlgorithmException e) {

        }

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
}
