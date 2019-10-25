package com.spdb.nrpt.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//时间工具类
public class TimeUtil {

    //获得当前时间，格式为yyyy-MM-dd HH:mm:ss
    public static String getNowDate(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    //获得不同格式当前时间，格式有（yyyyMMddHHmmssSSS，yyyyMMdd，mmssSSS）
    public static String getTime(String type) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String time = simpleDateFormat.format(date);
        String yyyyMMdd = time.substring(0, 8);
        String mmssSSS = time.substring(8);
        if ("yyyyMMdd".equals(type)) {
            return yyyyMMdd;
        } else if ("mmssSSS".equals(type)) {
            return mmssSSS;
        }
        return time;
    }

    //获取当前时间的前x小时整点30分钟时间(y=0.5是半个小时)
    public static String getPastTime(int x,double y){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
        Calendar cal=Calendar.getInstance();
        String time = "";
        String mString = "";
        if (y==0.5){
            int mi=cal.get(Calendar.MINUTE);
            if (mi<30){
                cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY)-(x+1));
                 time = simpleDateFormat.format(cal.getTime());
                mString = "30";
            }else {
                cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY)-(x));
                 time = simpleDateFormat.format(cal.getTime());
                mString = "00";
            }
        }else {
            cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY)-x);
             time = simpleDateFormat.format(cal.getTime());
            int mi=cal.get(Calendar.MINUTE);
            if (mi<30){
                mString = "00";
            }else {
                mString = "30";
            }
        }

        return time+":"+mString+":00";
    }

    //获得x小时内所有的整点时间
    public static List<String> getAllPast30Time(int x){
        List<String> dataList = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
        for (int i=x;i>=0;i--){
            Calendar cal=Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY)-i);
            String time = simpleDateFormat.format(cal.getTime());
            dataList.add(time+":00");
            dataList.add(time+":30");
        }
        Calendar cal=Calendar.getInstance();
        String time = simpleDateFormat.format(cal.getTime());
        int mi=cal.get(Calendar.MINUTE);
        if (mi>30){
            dataList = dataList.subList(2,12);
        }else {
            dataList = dataList.subList(1,11);
        }
        return dataList;
    }

    //获得月份的每天
    public static List<String> getMonthDay(int year, int month){
        List<String> data = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i=1; i<lastDay+1; i++){
            data.add(i+"");
        }
        return data;
    }

    //获得当前X月,X年第一天
    public static String getFirstDay(int x){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, x);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        String first = format.format(calendar.getTime());
       return first;
    }

    //获得当前X月，X年最后一天
    public static String getLastDay(int x,int y){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, y);
        calendar.add(Calendar.MONTH, x);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format.format(calendar.getTime());
        return last;
    }

    //获得当前日期的前x天
    public static String getLastXDay(int x){
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,x);
        Date time=cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time);
    }

    //获得某年的第一天
    public static String getYearFirstDay(int x){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, x);
        Date currYearFirst = calendar.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(currYearFirst);
    }

    //获得当年的第一天
    public static String getNowYearFirstDay(){
        Calendar currCal=Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirstDay(currentYear);
    }

    //获得前X年的最后一天
    public static String getYearLastDay(int x){
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        calendar.clear();
        calendar.set(Calendar.YEAR, currentYear-x);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(currYearLast);
    }

    //获得最近六个月月末(1月，2月，3月，12月)
    public static List<String> getSixMonthLastDay(){
        List<String> list = new ArrayList<>();
        for (int i =6 ;i>=1; i--){
            String date =getLastDay(-i,0).replaceAll("-","").substring(4,6);
            if (date.startsWith("0")){
                date = date.substring(1,2);
            }
            date = date+"月";
            list.add(date);
        }
        return list;
    }

    //获得当前分钟数
    public static int getMinute() {
        Calendar calendar = Calendar.getInstance();
        calendar = Calendar.getInstance();
        return calendar.get(Calendar.MINUTE);
    }
}

