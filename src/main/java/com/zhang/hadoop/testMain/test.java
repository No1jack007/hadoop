package com.zhang.hadoop.testMain;

import java.util.regex.Pattern;

/**
 * Created by zhang yufei on 2019/1/8.
 */
public class test {

    public static void main(String args[]){
        Pattern p = Pattern.compile("^[A-Z0-9]{18}$|^[0-9]{9}$");
        System.out.print(p.matcher("91310000329555773R").matches());
    }
}
