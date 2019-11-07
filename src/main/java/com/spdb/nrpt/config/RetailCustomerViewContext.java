package com.spdb.nrpt.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RetailCustomerViewContext {

    public static final List<String> tileName = Arrays.asList("客户数","资产规模","非0客户数","当年基础及以上客户净增","当年中高端客户净增","当年四绑客户净增","当年三方代理基础及以上客户净增","网均总客户数","网均非0客户数","网均金融资产","网均当年基础及以上客户净增","网均当年中高端客户净增","网均当年四绑客户净增","网均当年三方代理基础及以上客户净增");

    public static final List<String> orgidList = Arrays.asList("6908","9403","3200","3300","3400","3500","3600","3700","4300","4500","4800","5800","5900","6000","6100","6300","6400","6500","6600","6800","6900","7000","7100","7200","7300","7400","7500","7600","7700","7800","7900","8200","8300","8900","9100","9300","9400","9500","9800");

    public static Map<String,String> CustSlice = new HashMap<>();
    static {
        CustSlice.put("1","非0基客");
        CustSlice.put("2","基客");
        CustSlice.put("3","优客");
        CustSlice.put("4","贵宾");
        CustSlice.put("5","私行");
        CustSlice.put("6","0资产");

    }


    public static Map<String,String> sex = new HashMap<>();

    static {

        sex.put("1","男");
        sex.put("2","女");
    }

    public static Map<String,String> age = new HashMap<>();

    static {
        age.put("1","0-17岁");
        age.put("2","18-30岁");
        age.put("3","31-50岁");
        age.put("4","51-60岁");
        age.put("5","61-64岁");
        age.put("6","65-70岁");
        age.put("7","高于70岁");

    }

    public static Map<String,String> account = new HashMap<>();

    static {
        account.put("1","I类账户");
        account.put("2","II类账户");
        account.put("3","III类账户");
    }



    public static Map<String,String> Orgs = new HashMap<>();

    //分行编号与省份对应关系
    static {
        Orgs.put("3200","拉萨分行");
        Orgs.put("3300","银川分行");
        Orgs.put("3400","海口分行");
        Orgs.put("3500","西宁分行");
        Orgs.put("3600","厦门分行");
        Orgs.put("3700","贵阳分行");
        Orgs.put("4300","福州分行");
        Orgs.put("4500","石家庄分行");
        Orgs.put("4800","兰州分行");
        Orgs.put("5800","合肥分行");
        Orgs.put("5900","呼和浩特分行");
        Orgs.put("6000","乌鲁木齐分行");
        Orgs.put("6100","长春分行");
        Orgs.put("6300","南宁分行");
        Orgs.put("6400","南昌分行");
        Orgs.put("6500","哈尔滨分行");
        Orgs.put("6600","长沙分行");
        Orgs.put("6800","太原分行");
        Orgs.put("6900","青岛分行");
        Orgs.put("7000","武汉分行");
        Orgs.put("7100","沈阳分行");
        Orgs.put("7200","西安分行");
        Orgs.put("7300","成都分行");
        Orgs.put("7400","济南分行");
        Orgs.put("7500","大连分行");
        Orgs.put("7600","郑州分行");
        Orgs.put("7700","天津分行");
        Orgs.put("7800","昆明分行");
        Orgs.put("7900","深圳分行");
        Orgs.put("8200","广州分行");
        Orgs.put("8300","重庆分行");
        Orgs.put("8900","苏州分行");
        Orgs.put("9100","北京分行");
        Orgs.put("9300","南京分行");
        Orgs.put("9400","宁波分行");
        Orgs.put("9500","杭州分行");
        Orgs.put("9800","上海分行");
        Orgs.put("9900","全行");
        //Orgs.put("9940","总行本部");
    }


    public static Map<String,String> provinceName = new HashMap<>();
    static {
        provinceName.put("上海","上海");
        provinceName.put("北京","北京");
        provinceName.put("西安","陕西");
        provinceName.put("宁波","浙江");
        provinceName.put("南京","江苏");
        provinceName.put("福州","福建");
        provinceName.put("南昌","江西");
        provinceName.put("厦门","福建");
        provinceName.put("西宁","青海");
        provinceName.put("海口","海南");
        provinceName.put("银川","宁夏");
        provinceName.put("昆明","云南");
        provinceName.put("天津","天津");
        provinceName.put("合肥","安徽");
        provinceName.put("深圳","广东");
        provinceName.put("贵阳","贵州");
        provinceName.put("呼和浩特","内蒙古");
        provinceName.put("沈阳","辽宁");
        provinceName.put("济南","山东");
        provinceName.put("成都","四川");
        provinceName.put("郑州","河南");
        provinceName.put("杭州","浙江");
        provinceName.put("大连","辽宁");
        provinceName.put("拉萨","西藏");
        provinceName.put("石家庄","河北");
        provinceName.put("长沙","湖南");
        provinceName.put("青岛","山东");
        provinceName.put("太原","山西");
        provinceName.put("苏州","江苏");
        provinceName.put("兰州","甘肃");
        provinceName.put("广州","广东");
        provinceName.put("哈尔滨","黑龙江");
        provinceName.put("南宁","广西");
        provinceName.put("乌鲁木齐","新疆");
        provinceName.put("重庆","重庆");
        provinceName.put("武汉","湖北");
        provinceName.put("长春","吉林");
    }

}
