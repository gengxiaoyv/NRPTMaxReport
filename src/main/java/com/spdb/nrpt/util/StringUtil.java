package com.spdb.nrpt.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

//字符串工具类
public class StringUtil {

    //每三个数字一个逗号
    public static String NumberSplit(String num){
        String startNum = "";
        String endNum = "";
        int length = num.length();
        if (length%3!=0){
            startNum = num.substring(0,length%3);
            num = num.substring(length%3);
        }
        if (num.contains(".")){
            endNum = num.substring(num.indexOf("."));
            num.substring(0,num.indexOf("."));
        }
        StringBuffer res = new StringBuffer();
        while (length>3){
            res.append(num.substring(0,3)+",");
            num = num.substring(3);
            length = num.length();
        }
        res.append(num);
        res.append(endNum);
        if ("".equals(startNum)){
            return res.toString();
        }
        return startNum+","+res;
    }

    //20位id
    public static String getNextNum(){
        String id="";
        //获取当前时间戳
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        String temp = sf.format(new Date());
        /*//获取6位随机数
        int random=(int) ((Math.random()*9+1)*100000);
        id=temp+random;*/
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return temp;
    }

    //获得当前IP
    public static List<String> getAllAddress(){
        List<String> arr = new ArrayList<>();
        try {
            for (Enumeration<NetworkInterface> ENS = NetworkInterface.getNetworkInterfaces();ENS.hasMoreElements();){
                NetworkInterface iface = ENS.nextElement();
                for (Enumeration<InetAddress> EIS = iface.getInetAddresses(); EIS.hasMoreElements();){
                    InetAddress inetAddress = EIS.nextElement();
                    if (!inetAddress.isLoopbackAddress()){
                        if (inetAddress.isSiteLocalAddress()){
                            arr.add(inetAddress.getHostAddress());
                        }
                    }
                }
            }
            return arr;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
