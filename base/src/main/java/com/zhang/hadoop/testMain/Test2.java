package com.zhang.hadoop.testMain;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhang.hadoop.util.AESUtil;
import com.zhang.hadoop.util.HttpClientUtils;
import org.junit.Test;

import javax.swing.*;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        //test7();
        //test8();
        //test9();
        //test10();
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

    public static void test8() {
        long nowMillis = System.currentTimeMillis();
        final Long ACCESS_TOKEN_TIME = 10 * 60L;
        Long l1 = nowMillis + ACCESS_TOKEN_TIME * 1000L;
        final Long REFRESH_TOKEN_TIME = 4 * 60 * 60L;
        Long l2 = nowMillis + REFRESH_TOKEN_TIME * 1000L;
        System.out.println(l1 + "\t" + l2);
    }

    public static void test9() {
        int now = 5;
        int last = 3;
        double margin = 10;
        //        NumberFormat numberFormat = NumberFormat.getInstance();
        //        numberFormat.setMaximumFractionDigits(2);
        //        String saleProportion = numberFormat.format((margin/last)*100);
        //        System.out.println(saleProportion);
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println((margin / last) * 100);
        System.out.println(df.format((margin / last) * 100));
    }

    @Test
    public void test10() {
        List<Integer> data = new LinkedList<>();
        for (int i = 1; i <= 5; i++) {
            data.add(i);
        }

        Integer all = data.size();
        int size = 10;
        int limit = all / size;
        if ((all % size) != 0) {
            limit = limit + 1;
        }
        System.out.println("limit:" + limit);
        List<List<Integer>> groupData = Stream.iterate(0, n -> n + 1).limit(limit).map(a -> {
            System.out.println("组号:" + a);
            List<Integer> sendList = data.stream().skip(a * size).limit(size).collect(Collectors.toList());
            return sendList;
        }).collect(Collectors.toList());
        for (List<Integer> list : groupData) {
            for (Integer i : list) {
                System.out.print(i + "\t");
            }
            System.out.println("------");
        }
        //        Stream.iterate(0, n -> n + 1).limit(1).forEach(a->{
        //            System.out.println(a);
        //        });
    }

    @Test
    public void test11() {
        String code = "123456781234567812345678";
        Pattern p = Pattern.compile("^[A-Z0-9]{24}$");
        Matcher m = p.matcher(code);
        System.out.println(m.matches());
    }

    @Test
    public void test12() {
        int a = 11;
        int b = 20;
        System.out.println(a % b + 1);
    }

    @Test
    public void test13() {
        int a = 0;
        synchronized (this) {
            a++;
        }
    }

    @Test
    public void test14() {
        System.out.println(String.format("%02d", 7));
    }

    @Test
    public void test15() {
        String s1 = "corn-job_01";
        String s2 = "corn-job";
        String s3 = s1.substring(s2.length() + 1, s1.length());
        System.out.println(s3);
    }

    @Test
    public void test16() {
        int a = 20;
        int b = 10;
        int c = 2;
        for (int i = 0; i < 1000; i++) {
            System.out.print("数值-");
            System.out.print(i + "\t");
            System.out.print("队列-");
            System.out.print(i % a + 1 + "\t");
            System.out.print("数据表-");
            System.out.print(i % b + 1 + "\t");
            System.out.print("数据库-");
            System.out.print(i % c + 1 + "\t");
            System.out.println();
        }
    }

    @Test
    public void test17() {
        int a = 10;
        int b = 25;
        for (int i = 0; i < b; i++) {
            System.out.println("分片编号：" + i + "\t所属队列：" + (i % a + 1));
        }

        System.out.println("----");

        int c = 25;
        int d = 10;
        for (int i = 0; i < c; i++) {
            System.out.println("分片编号：" + (i % a) + "\t所属队列：" + (i + 1));
        }

        System.out.println("----");

        int f = 25;
        int j = 10;
        for (int i = 0; i < j; i++) {
            System.out.print("分片编号：" + i);
            int e = i;
            boolean flag = true;
            while (flag) {
                if (e < f) {
                    System.out.print("\t队列编号：" + (e + 1));
                    e = e + j;
                } else {
                    flag = false;
                }
            }
            System.out.println();
        }
    }

    @Test
    public void test18() {
        Map<String, Object> map = new ConcurrentHashMap<>();
        for (int i = 1; i <= 100; i++) {
            map.put(i + "", "key");
        }
        for (String key : map.keySet()) {
            System.out.println(key);
        }
    }

    @Test
    public void test19() {
        //获得当前时间
        Instant instant = Instant.now();
        // 以ISO-8601输出
        System.out.println(instant);
        System.out.println(instant.toString());
        System.out.println(instant.toEpochMilli());
        System.out.println(instant.getNano());
        System.out.println(instant.atOffset(ZoneOffset.ofHours(8)).toString());
        System.out.println(Instant.parse("2007-12-03T10:15:30.00Z"));
    }

    @Test
    public void test20() {
        System.out.println(10 % 0);
    }

    @Test
    public void test22() {
        AtomicInteger counter = new AtomicInteger(0);
        for (int i = 0; i < 3000; i++) {
            System.out.println(counter.incrementAndGet());
        }
    }

    @Test
    public void test23() {
        AtomicInteger counter = new AtomicInteger(0);
        System.out.println(counter.addAndGet(10));
        System.out.println(String.format("%s-%s", "persistent://op/growth/waiting_update_delay_message_status", "growth"));
    }

    @Test
    public void test24() {
        List<String> list = new ArrayList<String>();
        list.add("abc2003");
        list.add("abc2005");
        list.add("abc2001");
        list.add("abc2007");
        System.out.println(list);

        Collections.sort(list, (o1, o2) -> {
            // 返回相反的compare
            String no1 = o1.substring(4, 7);
            System.out.println(no1);
            String no2 = o2.substring(4, 7);
            System.out.println(no2);
            return no1.compareTo(no2);
        });
        System.out.println(list);
    }

    @Test
    public void test25() {
        List<String> queueList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            queueList.add("dev_" + "growth_delayed_msg_queue_" + String.format("%02d", i));
        }
        Collections.sort(queueList, (redisKey1, redisKey2) -> {

            String[] num1array = redisKey1.split("_");
            String[] num2array = redisKey2.split("_");
            Integer num1 = Integer.parseInt(redisKey1.substring(("dev_" + "growth_delayed_msg_queue_").length()));
            System.out.println(num1);
            Integer num2 = Integer.parseInt(redisKey2.substring(("dev_" + "growth_delayed_msg_queue_").length()));
            System.out.println(num2);
            return num1 - num2;
        });
        System.out.println(queueList);
    }

    @Test
    public void test26() {
        System.out.println("dev_growth_delayed_msg_queue_10".substring(("dev_" + "growth_delayed_msg_queue_").length()));
    }

    @Test
    public void test27() {
        String s1 = "[\n" +
                "        {\n" +
                "            \"floorId\": \"f00001\",\n" +
                "            \"floorType\": \"editor\",\n" +
                "            \"templateBody\": \"xxxxadq2312${xxx.xxx}asdasdas\",\n" +
                "            \"dataSource\": {\n" +
                "                \"from\": \"operating\",\n" +
                "                \"bizType\": \"\",\n" +
                "                \"bizId\": \"\",\n" +
                "                \"dataSourceType\": \"job_card_image\",\n" +
                "                \"dataSourceId\": \"000001\",\n" +
                "                \"pageSize\": 10,\n" +
                "                \"filter\": {}\n" +
                "            }\n" +
                "        },\n" +
                "        {}\n" +
                "    ]\n";

        List<JSONObject> templates = JSONArray.parseArray(s1, JSONObject.class);
        System.out.println(templates);
    }

    @Test
    public void test28() {
        int threadPoolSize = 10;
        int queueSize = 20;
        threadPoolSize = threadPoolSize > queueSize ? queueSize : threadPoolSize;
        System.out.println(threadPoolSize);
    }

    @Test
    public void test29() {
        int core = Runtime.getRuntime().availableProcessors();
        System.out.println(core);
    }

    @Test
    public void test30() {
        List<Integer> list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(i);
            list.add(i);
            list.add(i);
            list.add(i);
        }
        list = list.stream().collect(
                Collectors.toMap(v -> v, v -> v, (v1, v2) -> v1))
                .values().stream().collect(Collectors.toList());
        System.out.println(list.toString());
    }

    @Test
    public void test31() {
        System.out.println("活报名数据导出" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
    }

    @Test
    public void test32() {
        String idString = "[\n" +
                "\"10008514348\",\n" +
                "\"10008520118\",\n" +
                "\"10004162774\",\n" +
                "\"10004436802\",\n" +
                "\"10004875589\",\n" +
                "\"10003415652\",\n" +
                "\"10005295249\",\n" +
                "\"10006060110\",\n" +
                "\"10007531619\",\n" +
                "\"10008484140\",\n" +
                "\"10002726858\",\n" +
                "\"10001182260\",\n" +
                "\"10003827577\",\n" +
                "\"10001102873\",\n" +
                "\"10003145099\",\n" +
                "\"10003895316\",\n" +
                "\"10001921132\",\n" +
                "\"10002247783\",\n" +
                "\"10008801240\",\n" +
                "\"10007437080\",\n" +
                "\"10000925601\",\n" +
                "\"10001861346\",\n" +
                "\"10002175627\",\n" +
                "\"10008525570\",\n" +
                "\"10001385830\",\n" +
                "\"10003450215\",\n" +
                "\"10002031419\",\n" +
                "\"10001514393\",\n" +
                "\"10008612250\",\n" +
                "\"10007164598\",\n" +
                "\"10001643824\",\n" +
                "\"10004118094\",\n" +
                "\"10006093874\",\n" +
                "\"10006827430\",\n" +
                "\"10003062204\",\n" +
                "\"10004282743\",\n" +
                "\"10006776223\",\n" +
                "\"10003147781\",\n" +
                "\"10007347498\",\n" +
                "\"10002497310\",\n" +
                "\"10000316007\",\n" +
                "\"10005634947\",\n" +
                "\"10003207079\",\n" +
                "\"10003845063\",\n" +
                "\"10007431766\",\n" +
                "\"10004538747\",\n" +
                "\"10003530409\",\n" +
                "\"10002821334\",\n" +
                "\"10004611542\",\n" +
                "\"10007306775\",\n" +
                "\"10007048095\",\n" +
                "\"10004800632\",\n" +
                "\"10007118345\",\n" +
                "\"10003668489\",\n" +
                "\"10003355567\",\n" +
                "\"10006647942\",\n" +
                "\"10000851191\",\n" +
                "\"10008776019\",\n" +
                "\"10000493507\",\n" +
                "\"10000807793\",\n" +
                "\"10000788101\",\n" +
                "\"10001151925\",\n" +
                "\"10006327321\",\n" +
                "\"10004542976\",\n" +
                "\"10001210580\",\n" +
                "\"10002958509\",\n" +
                "\"10001015016\",\n" +
                "\"10002200123\",\n" +
                "\"10006990896\",\n" +
                "\"10000664782\",\n" +
                "\"10005050014\",\n" +
                "\"10002558673\",\n" +
                "\"10001640208\",\n" +
                "\"10006002163\",\n" +
                "\"10006315362\",\n" +
                "\"10007803469\",\n" +
                "\"10007742836\",\n" +
                "\"10005184511\",\n" +
                "\"10003091633\",\n" +
                "\"10006034693\",\n" +
                "\"10002991513\",\n" +
                "\"10002642087\",\n" +
                "\"10005341976\",\n" +
                "\"10008445817\",\n" +
                "\"10004758639\",\n" +
                "\"10007871192\",\n" +
                "\"10007156097\",\n" +
                "\"10002563900\",\n" +
                "\"10005820416\",\n" +
                "\"10008122518\",\n" +
                "\"10005198292\",\n" +
                "\"10007241499\",\n" +
                "\"10005166818\",\n" +
                "\"10004897790\",\n" +
                "\"10001665462\",\n" +
                "\"10000877339\",\n" +
                "\"10001751794\",\n" +
                "\"10006661913\",\n" +
                "\"10005248927\",\n" +
                "\"10001428806\",\n" +
                "\"10002885383\",\n" +
                "\"10006673664\",\n" +
                "\"10002175348\",\n" +
                "\"10001861688\",\n" +
                "\"10003768851\",\n" +
                "\"10002506870\",\n" +
                "\"10001502272\",\n" +
                "\"10003696124\",\n" +
                "\"10006710140\",\n" +
                "\"10004217687\",\n" +
                "\"10008032678\",\n" +
                "\"10005911520\",\n" +
                "\"10004294304\",\n" +
                "\"10006303654\",\n" +
                "\"10003818126\",\n" +
                "\"10000080453\",\n" +
                "\"10000201362\",\n" +
                "\"10007990469\",\n" +
                "\"10008011965\",\n" +
                "\"10003836860\",\n" +
                "\"10001618800\",\n" +
                "\"10003255444\",\n" +
                "\"10007434722\",\n" +
                "\"10006175167\",\n" +
                "\"10000662192\",\n" +
                "\"10001338622\",\n" +
                "\"10001904663\",\n" +
                "\"10008127309\",\n" +
                "\"10004537410\",\n" +
                "\"10002301367\",\n" +
                "\"10001594634\",\n" +
                "\"10006262464\",\n" +
                "\"10007534731\",\n" +
                "\"10003867808\",\n" +
                "\"10000683897\",\n" +
                "\"10004256527\",\n" +
                "\"10005967541\",\n" +
                "\"10003023524\",\n" +
                "\"10004134731\",\n" +
                "\"10004526463\",\n" +
                "\"10007908422\",\n" +
                "\"10005314216\",\n" +
                "\"10000690089\",\n" +
                "\"10003562179\",\n" +
                "\"10001497907\",\n" +
                "\"10008861246\",\n" +
                "\"10006971491\",\n" +
                "\"10000037790\",\n" +
                "\"10008572622\",\n" +
                "\"10003870191\",\n" +
                "\"10006673078\",\n" +
                "\"10002835736\",\n" +
                "\"10005198034\",\n" +
                "\"10007574417\",\n" +
                "\"10005043011\",\n" +
                "\"10008760543\",\n" +
                "\"10004035944\",\n" +
                "\"10006344012\",\n" +
                "\"10000525440\",\n" +
                "\"10007622838\",\n" +
                "\"10003701262\",\n" +
                "\"10008063361\",\n" +
                "\"10006408762\",\n" +
                "\"10008419058\",\n" +
                "\"10005667279\",\n" +
                "\"10001813919\",\n" +
                "\"10006701455\",\n" +
                "\"10006266977\",\n" +
                "\"10003394375\",\n" +
                "\"10002895954\",\n" +
                "\"10000739009\",\n" +
                "\"10007133429\",\n" +
                "\"10008421384\",\n" +
                "\"10008567458\",\n" +
                "\"10002863626\",\n" +
                "\"10001798097\",\n" +
                "\"10005901233\",\n" +
                "\"10004210302\",\n" +
                "\"10000832426\",\n" +
                "\"10006727254\",\n" +
                "\"10006790251\",\n" +
                "\"10000801165\",\n" +
                "\"10005965829\",\n" +
                "\"10007454696\",\n" +
                "\"10005244119\",\n" +
                "\"10001276492\",\n" +
                "\"10007842599\",\n" +
                "\"10004525014\",\n" +
                "\"10006383288\",\n" +
                "\"10005255813\",\n" +
                "\"10002553599\",\n" +
                "\"10000740321\",\n" +
                "\"10006080908\",\n" +
                "\"10006818973\",\n" +
                "\"10005091269\",\n" +
                "\"10005540533\",\n" +
                "\"10002988627\",\n" +
                "\"10004176718\",\n" +
                "\"10006046127\",\n" +
                "\"10002621263\"\n" +
                "]";
        try {
            List<String> list = JSONObject.parseObject(idString, List.class);
            List<String> result=new ArrayList<>();
            for (String s : list) {
                String mobile = s;
                java.util.Map<String, String> headerMap = new HashMap<>();
                headerMap.put("x-zp-inner-authentication", "QmVhcmVyIGV5SmhiR2NpT2lKSVV6VXhNaUo5LmV5SnpkV0lpT2lJeE16STRPRGd6TVNJc0ltVjRjQ0k2TVRZek1URTFNelE0TTMwLkVKRDZxUExLTkJ5U3hIQkJoZi10cnB6RjF6clc4bzlUckh1VHd0OGZLV3EzVTRBaVkzVFZsdFh1allBaF9paVExNVM1VWJYSC0xcDlxRENWb1M3US13");
                Map<String, Object> params = new HashMap<>();
                params.put("motPhone", mobile);
                String response = HttpClientUtils.doGet("http://op-mofang-tianti.zpidc.com/console/gid/sms/select", headerMap, params, "UTF-8");
                System.out.println(response);
                JSONObject jsonObject=JSONObject.parseObject(response);
                result.add(mobile+"    "+jsonObject.getJSONObject("data").get("motPhoneGid"));
            }
            for(String s:result){
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
