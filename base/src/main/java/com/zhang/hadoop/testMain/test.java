package com.zhang.hadoop.testMain;

import java.util.regex.Pattern;

/**
 * Created by zhang yufei on 2019/1/8.
 */
public class test {

    public static void main(String args[]){
        Pattern p = Pattern.compile("^[A-Z0-9]{18}$|^[0-9]{9}$");
        System.out.println(p.matcher("91310000329555773R").matches());
        String a="123456";
        System.out.println(a.substring(0,3));

        String date="2018.10";
        String a1[]=date.split("\\.");
        System.out.println(a1[0]+"\t"+a1[1]);
    }
}
