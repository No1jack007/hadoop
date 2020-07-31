package com.zhang.hadoop.testMain;

import com.alibaba.fastjson.JSONObject;
import com.zhang.hadoop.util.AESUtil;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: zhang yufei
 * @create: 2020-04-16 09:16
 **/
public class Test2 {

    public static void main(String args[]) {
        //test1()
        //test2();
        //test3();
        //test4();
        //test5();
        //test6();
        test7();
        //test8();
    }

    public static void test1() {
        String s1 = "涨涨涨涨";
        try {
            String s2 = java.net.URLEncoder.encode(s1, "UTF-8");
            System.out.println(s2);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void test2() {
        String key = "1234567812345678";
        String data = JSONObject.toJSONString(new HashMap<String, String>() {
            {
                put("username", "vehicle");
                put("password", "123456Aa");
            }
        });
        String result = AESUtil.encrypt(data, key);
        System.out.println(result);
    }

    public static void test3() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date inDate = null;
        try {
            inDate = sdf.parse("2020-6-27");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String outDate = sdf.format(inDate);
        System.out.println(inDate.getDay() + "\t" + outDate);
    }

    public static void test4() {
        String start = "2020-06-24";
        String end = "2020-06-29";
        String holidays = "2011-01-03,2011-02-02,2011-02-03,2011-02-04,2011-02-07,2011-02-08,2012-01-02,2012-01-03,2012-01-23,2012-01-24,2012-01-25,2012-01-26,2012-01-27,2013-01-01,2013-01-02,2013-01-03,2013-02-11,2013-02-12,2013-02-13,2013-02-14,2013-02-15,2014-01-01,2014-01-31,2014-02-03,2014-02-04,2014-02-05,2014-02-06,2015-01-01,2015-01-02,2015-02-18,2015-02-19,2015-02-20,2015-02-23,2015-02-24,2016-01-01,2016-02-08,2016-02-09,2016-02-10,2016-02-11,2016-02-12,2017-01-02,2017-01-27,2017-01-30,2017-01-31,2017-02-01,2017-02-02,2018-01-01,2018-02-15,2018-02-16,2018-02-19,2018-02-20,2018-02-21,2019-05-01,2019-05-02,2019-05-03,2019-06-07,2020-01-01,2020-01-24,2020-01-27,2020-01-28,2020-01-29,2020-01-30,2020-01-31,2020-04-06,2020-05-01,2020-05-04,2020-05-05,2020-06-25,2020-06-26,2020-10-01,2020-10-02,2020-10-05,2020-10-06,2020-10-07,2020-10-08,";
        String workdays = "2011-01-30,2011-02-12,2011-12-31,2012-01-21,2012-01-29,2013-01-05,2013-01-06,2013-02-16,2013-02-17,2014-01-26,2014-02-08,2015-01-04,2015-02-15,2015-02-28,2016-02-06,2016-02-14,2017-01-22,2017-02-04,2018-02-11,2018-02-24,2019-04-28,2019-05-05,2020-01-19,2020-04-26,2020-05-09,2020-06-28,2020-09-27,2020-10-10,";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = df.parse(start);
            endDate = df.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int result = 0;
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        while (startCalendar.getTime().getTime() < endDate.getTime()) {
            if (startCalendar.get(Calendar.DAY_OF_WEEK) != 1 && startCalendar.get(Calendar.DAY_OF_WEEK) != 7) {
                result++;
                if (holidays.indexOf(df.format(startCalendar.getTime())) > -1) {
                    result--;
                }
            } else {
                if (workdays.indexOf(df.format(startCalendar.getTime())) > -1) {
                    result++;
                }
            }
            startCalendar.add(startCalendar.DATE, 1);
        }
        System.out.println(result);
    }

    public static void test5() {
        final String TIME_1 = "^(((20[0-3][0-9]-(0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|(20[0-3][0-9]-(0[2469]|11)-(0[1-9]|[12][0-9]|30))) (20|21|22|23|[0-1][0-9]):[0-5][0-9]:[0-5][0-9])$";
        Pattern p = Pattern.compile(TIME_1);
        Matcher m = p.matcher("    ");
        System.out.println(m.matches());
    }

    public static void test6() {
        String str = "2020-05";
        System.out.println(str.substring(5, 7));
    }

    public static void test7() {
        String s = "1594717263674";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        String res = simpleDateFormat.format(date);
        System.out.println(res);
    }

    public static void test8(){
        long nowMillis = System.currentTimeMillis();
        final Long ACCESS_TOKEN_TIME=10*60L;
        Long l1=nowMillis + ACCESS_TOKEN_TIME * 1000L;
        final Long REFRESH_TOKEN_TIME=4*60*60L;
        Long l2=nowMillis + REFRESH_TOKEN_TIME * 1000L;
        System.out.println(l1+"\t"+l2);
    }
}
