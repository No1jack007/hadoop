package com.zhang.hadoop.util;

import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zhang yufei on 2017/12/26.
 */
public class DateUtil {
    public static final int SECOND = 1000;
    public static final int MINUTE = 60000;
    public static final int HOUR = 3600000;
    public static final long DAY = 86400000L;
    public static final long WEEK = 604800000L;
    public static final long YEAR = 30758400000L;
    public static final String DEFAULT_VALID_TIME = "2099-12:30 00:00:00";
    public static final String FULL_ST_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String FULL_J_FORMAT = "yyyy/MM/dd HH:mm:ss";
    public static final String CURRENCY_ST_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String CURRENCY_J_FORMAT = "yyyy/MM/dd HH:mm";
    public static final String DATA_FORMAT = "yyyyMMddHHmmss";
    public static final String ST_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String ST_CN_FORMAT = "yyyy年MM月dd日 HH:mm";
    public static final String ST_CN_FORMATS = "yyyy年MM月dd日";
    public static final String CN_FORMAT = "yy年MM月dd日HH:mm";
    public static final String DAY_FORMAT = "yyyy-MM-dd";
    public static final String SHORT_DATE_FORMAT = "yy-MM-dd";
    public static final String YEAR_FORMAT = "yyyy";

    public DateUtil() {
    }


    public static String getShortDate(long milliS) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        return sdf.format(new Date(milliS));
    }


    public static String getNows() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return sdf.format(new Date());
    }

    public static String format(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }


    public static String getYearMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        return sdf.format(new Date());
    }

    public static String  getNowForFilePath() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }


    /**
     *
     * @param time1
     * @param time2
     * @return
     */
    public static long getQuots(String time1, String time2) {
        long quot = 0L;
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date e = ft.parse(time1);
            Date date2 = ft.parse(time2);
            quot = e.getTime() - date2.getTime();
            quot = quot / 1000L / 60L / 60L / 24L;
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return quot;
    }


    /**
     *
     * @param dateStr
     * @return
     */
    public static Date strToDate_ex_full(String dateStr) {
        if(dateStr == null) {
            return null;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            try {
                return sdf.parse(dateStr);
            } catch (ParseException var4) {
                var4.printStackTrace();
                return null;
            }
        }
    }

    /**
     *
     * @param dateStr
     * @return
     */
    public static Date strToDate_ex(String dateStr) {
        if(dateStr == null) {
            return null;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

            try {
                return sdf.parse(dateStr);
            } catch (ParseException var4) {
                var4.printStackTrace();
                return null;
            }
        }
    }


    /**
     *
     * @return
     */
    public static List<Date> weekByDate() {
        Calendar cal = Calendar.getInstance();
        int cur = cal.get(7);
        Date start = null;
        Date end = null;
        boolean bfday = false;
        if(cur == 1) {
            cal.add(6, 1);
            end = cal.getTime();
            cal.add(6, -3);
            start = cal.getTime();
        } else {
            int bfday1 = 6 - cur;
            cal.add(6, bfday1);
            start = cal.getTime();
            cal.add(6, 3);
            end = cal.getTime();
        }

        ArrayList dates = new ArrayList();
        dates.add(start);
        dates.add(end);
        return dates;
    }

    /**
     *
     * @param datestr
     * @return
     */
    public static long getSeconds(String datestr) {
        Date date = strToDate_ex(datestr);
        return date.getTime() / 1000L;
    }

    /**
     *
     * @param date
     * @return
     */
    public static long getSeconds(Date date) {
        return date.getTime() / 1000L;
    }

    /**
     *
     * @param time
     * @return
     */
    public static Long getLongTimeNum(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;

        try {
            date = format.parse(time);
        } catch (ParseException var5) {
            var5.printStackTrace();
        }

        long re = date.getTime();
        return Long.valueOf(re);
    }

    /**
     *
     * @param time
     * @return
     */
    public static String getTimefromNum(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long time2 = Long.valueOf(Long.parseLong(time));
        String d = format.format(time2);
        Date date = null;

        try {
            date = format.parse(d);
            String e = format.format(date);
            return e;
        } catch (ParseException var6) {
            var6.printStackTrace();
            return "";
        }
    }

    /**
     *
     * @param type
     * @param startTime
     * @param endTime
     * @return
     */
    public static int getTimesDiff(int type, String startTime, String endTime) {
        if(!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            try {
                SimpleDateFormat e = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                long from = e.parse(startTime).getTime();
                long to = e.parse(endTime).getTime();
                int times = 0;
                if(type == 1) {
                    times = (int)((to - from) / 86400000L);
                } else if(type == 2) {
                    times = (int)((to - from) / 3600000L);
                } else if(type == 3) {
                    times = (int)((to - from) / 60000L);
                }

                return times;
            } catch (ParseException var9) {
                var9.printStackTrace();
            }
        }

        return -1;
    }

    /**
     *
     * @param utc
     * @param format
     * @return
     */
    public static String utcToStr(Long utc, String format) {
        String result = "";
        if(null != utc && null != format) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);

            try {
                result = sdf.format(new Date(utc.longValue()));
            } catch (Exception var5) {
                var5.printStackTrace();
            }

            return result;
        } else {
            return result;
        }
    }


    /**
     * 一个日期转换为另一个日期
     * @param fromStr
     * @param fromFormat
     * @param toFormat
     * @return
     */
    public static String dateStrToStr(String fromStr,String fromFormat,String toFormat){
        String toStr="";
        SimpleDateFormat format = new SimpleDateFormat(fromFormat);
        SimpleDateFormat format2 = new SimpleDateFormat(toFormat);
        try {
            Date dateTwo = format.parse(fromStr);
            toStr=format2.format(dateTwo);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return toStr;
    }

    /**
     * 字符串按照指定格式转化为日期
     * @param dateStr
     * @param formatStr
     * @return
     */
    public static Date stringTodate(String dateStr, String formatStr) {
        // 如果时间为空则默认当前时间
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        if (dateStr != null && !dateStr.equals("")) {
            String time = "";
            try {
                Date dateTwo = format.parse(dateStr);
                time = format.format(dateTwo);
                date = format.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } else {
            String timeTwo = format.format(new Date());
            try {
                date = format.parse(timeTwo);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    /**
     * 计算两个日期相差年数
     * @param startDate
     * @param endDate
     * @param formatStr
     * @return
     */
    public static int yearDateDiff(String startDate,String endDate,String formatStr){
        Calendar calBegin = Calendar.getInstance(); //获取日历实例
        Calendar calEnd = Calendar.getInstance();
        calBegin.setTime(stringTodate(startDate,formatStr)); //字符串按照指定格式转化为日期
        calEnd.setTime(stringTodate(endDate,formatStr));
        return calEnd.get(Calendar.YEAR) - calBegin.get(Calendar.YEAR);
    }

    /**
     * 计算两个月份之间的差值
     * @param date1
     * @param date2
     * @param formatStr
     * @return
     * @throws ParseException
     */
    public static int getMonthSpace(String date1, String date2,String formatStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        try {
            bef.setTime(sdf.parse(date1));
            aft.setTime(sdf.parse(date2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
        int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;

        return Math.abs(month + result);
    }
    /**
     * 月份加一
     * @param date
     * @return
     */
    public static String monthAddFrist(String date,String formatStr){

        DateFormat df=new SimpleDateFormat(formatStr);
        try {
            Calendar ct=Calendar.getInstance();
            ct.setTime(df.parse(date));
            ct.add(Calendar.MONTH, +1);
            return df.format(ct.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 减去一个月
     * @param date
     * @param formatStr
     * @return
     */
    public static String monthMinusFrist(String date,String formatStr){

        DateFormat df=new SimpleDateFormat(formatStr);
        try {
            Calendar ct=Calendar.getInstance();
            ct.setTime(df.parse(date));
            ct.add(Calendar.MONTH, -1);
            return df.format(ct.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }


    public static String getDate(long second){
        SimpleDateFormat sdf = new SimpleDateFormat(FULL_ST_FORMAT);
        return sdf.format(new Date(second));
    }

    public static String getNow(){
        SimpleDateFormat sdf = new SimpleDateFormat(FULL_ST_FORMAT);
        return sdf.format(new Date());
    }

    public static String getNow(String str){
        SimpleDateFormat sdf = new SimpleDateFormat(str);
        return sdf.format(new Date());
    }

    public static String getShortDate(){
        SimpleDateFormat sdf=new SimpleDateFormat(DAY_FORMAT);
        return sdf.format(new Date());
    }

    public static String getKafkaDataSyncTime(){
        SimpleDateFormat sdf=new SimpleDateFormat(DATA_FORMAT);
        return sdf.format(new Date());
    }

    public static String getKafkaDataSyncTime(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat(DATA_FORMAT);
        return sdf.format(date);
    }

    public static Date getStringToDate(String date) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat(DATA_FORMAT);
        return sdf.parse(date);
    }

    public static Date strToDate(String date) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat(DAY_FORMAT);
        return sdf.parse(date);
    }

    public static String converseStr(String str)  {
        SimpleDateFormat sdf=new SimpleDateFormat(DATA_FORMAT);
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        return  formatTime(date, FULL_ST_FORMAT);
    }

    /**
     * 求两个时间中间间隔的天数
     * @param time1
     * @param time2
     * @return
     */
    public static long getQuot(String time1, String time2){
        long quot = 0;
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = ft.parse( time1 );
            Date date2 = ft.parse( time2 );
            quot = date1.getTime() - date2.getTime();
            quot = quot / 1000 / 60 / 60 / 24;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quot;
    }

    public static String formatTime(Date date,String format){
        if (date==null){
            return "";
        }
        if (format.equals(""))
            format = CURRENCY_ST_FORMAT;
        SimpleDateFormat ft = new SimpleDateFormat(format);
        return ft.format(date);
    }

    public static long getLongFromDate(String date) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat(FULL_ST_FORMAT);
        try {
            return sdf.parse(date).getTime();
        }
        catch (Exception e){
            sdf=new SimpleDateFormat(SHORT_DATE_FORMAT);
            return sdf.parse(date).getTime();
        }

    }

    public static String dayPlus(String date,int n){
        String ret = "";
        try {
            Date d1 = null;
            d1 = strToDate(date);
            Date d2 = new Date(d1.getTime()+(n*86400000L));
            ret = DateUtil.formatTime(d2, DateUtil.DAY_FORMAT);
            return ret;
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return ret;

    }

    public static Date  strToStr(String str,String str1){
        SimpleDateFormat format = new SimpleDateFormat(str1);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }
    /**
     * 日期转换成字符串
     * @param date
     * @return str
     */
    public static String DateToStr(Date date,String str) {

        SimpleDateFormat format = new SimpleDateFormat(str);
        String str1 = format.format(date);
        return str1;
    }

    /**
     * 将时间字符串转换为对应格式的data
     * @param str
     * @param formatter
     * @return
     */
    public static Date strToData(String str,String formatter) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(formatter);
        return sdf.parse(str);
    }

    /**
     * 获取当天的凌晨0点date
     * @return
     */
    public static Date getNowDataStart(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        return calendar.getTime();
    }


    /**
     *  获取当天的23点59分59秒date
     * @return
     */
    public static Date getNowDataLast() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     *计算两个时间的差
     * @param maxTime
     * @param minTime
     * @return
     */
    public static long  getNowDataDifference (String maxTime ,String minTime){
        SimpleDateFormat dfs = new SimpleDateFormat(FULL_ST_FORMAT);
        Date begin= null;
        Date end = null;
        long between = 0;//分钟

        try {
            end = dfs.parse(maxTime);
            begin = dfs.parse(minTime);
            between = (end.getTime()-begin.getTime())/1000/60;//分钟
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return  between;
    }

    /**
     * 判断time是否在from，to之内
     *
     * @param time 指定日期
     * @param from 开始日期
     * @param to   结束日期
     * @return
     */
    public static boolean belongCalendar(Date time, Date from, Date to) {
        Calendar date = Calendar.getInstance();
        date.setTime(time);

        Calendar after = Calendar.getInstance();
        after.setTime(from);

        Calendar before = Calendar.getInstance();
        before.setTime(to);

        if (date.after(after) && date.before(before)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据指定日期获取前一天
     * @param specifiedDay
     * @return
     */
    public static String getBeforeDay(String specifiedDay){
        Calendar c = Calendar.getInstance();
        Date date=null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day-1);
        String dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayBefore;
    }

    /***
     * 获取指定天前某天的日期
     * @param specifiedDay
     * @param num
     * @return
     */
    public static String getBeforeDay(String specifiedDay,int num){
        Calendar c = Calendar.getInstance();
        Date date=null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day-num);
        String dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayBefore;
    }

    /**
     * 获取指定日期的后一天
     * @param specifiedDay
     * @return
     */
    public static String getAfterDay(String specifiedDay){
        Calendar c = Calendar.getInstance();
        Date date=null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day+1);

        String dayAfter=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayAfter;
    }

    public static Date stringToDate(String str) {
        DateFormat format = new SimpleDateFormat(FULL_J_FORMAT);
        Date date = null;

        try {
            if("".equals(str)){
                date =new Date();
            }else {
                // Fri Feb 24 00:00:00 CST 2012
                date = format.parse(str);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 2012-02-24
        date = java.sql.Date.valueOf(str);

        return date;
    }
    /**
     * 获取该日期前一周的时间，不包括当天
     * @param date
     * @return
     */
    public static Date getLastWeekDate(Date date){
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(Calendar.DAY_OF_MONTH,instance.get(Calendar.DAY_OF_MONTH)-7);
        return instance.getTime();
    }

    /**
     * 格式化DATE
     *
     * @param date
     * @return 20140526173815
     */
    public static Long dateToLong(Date date) {
        String dateStr = formatTime(date, "yyyyMMddHHmmss");
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        } else {
            return Long.valueOf(dateStr);
        }
    }

    public static String dateBatch(String str)  {
        SimpleDateFormat sdf=new SimpleDateFormat(DATA_FORMAT);
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        return  formatTime(date, DATA_FORMAT);
    }

    public static String getCurrentYear(){
        SimpleDateFormat sdf = new SimpleDateFormat(YEAR_FORMAT);
        Date date = new Date();
        return sdf.format(date);
    }

}
