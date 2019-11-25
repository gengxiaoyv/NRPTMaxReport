package com.spdb.nrpt.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RetailCustomerViewContext {

    public static final List<String> tileName = Arrays.asList("客户数","非0客户数","资产规模","当年基础及以上客户净增","当年中高端客户净增","当年四绑客户净增","当年三方代理基础及以上客户净增","网均总客户数","网均非0客户数","网均金融资产","网均当年基础及以上客户净增","网均当年中高端客户净增","网均当年四绑客户净增","网均当年三方代理基础及以上客户净增");

    public static final List<String> tileNamebill = Arrays.asList("客户数","非0客户数","资产规模（百万）","当年基础及以上客户净增","当年中高端客户净增","当年四绑客户净增","当年三方代理基础及以上客户净增","网均总客户数","网均非0客户数","网均金融资产（百万）","网均当年基础及以上客户净增","网均当年中高端客户净增","网均当年四绑客户净增","网均当年三方代理基础及以上客户净增");


    public static final List<String> orgidList = Arrays.asList("3200","3300","3400","3500","3600","3700","4300","4500","4800","5800","5900","6000","6100","6300","6400","6500","6600","6800","6900","7000","7100","7200","7300","7400","7500","7600","7700","7800","7900","8200","8300","8900","9100","9300","9400","9500","9800");

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
        account.put("1","I类卡");
        account.put("2","II类卡");
        account.put("3","III类卡");
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

    public static Map<String,String> OrgsCity = new HashMap<>();

    //分行编号与省份对应关系
    static {
        OrgsCity.put("3200","拉萨");
        OrgsCity.put("3300","银川");
        OrgsCity.put("3400","海口");
        OrgsCity.put("3500","西宁");
        OrgsCity.put("3600","厦门");
        OrgsCity.put("3700","贵阳");
        OrgsCity.put("4300","福州");
        OrgsCity.put("4500","石家庄");
        OrgsCity.put("4800","兰州");
        OrgsCity.put("5800","合肥");
        OrgsCity.put("5900","呼和浩特");
        OrgsCity.put("6000","乌鲁木齐");
        OrgsCity.put("6100","长春");
        OrgsCity.put("6300","南宁");
        OrgsCity.put("6400","南昌");
        OrgsCity.put("6500","哈尔滨");
        OrgsCity.put("6600","长沙");
        OrgsCity.put("6800","太原");
        OrgsCity.put("6900","青岛");
        OrgsCity.put("7000","武汉");
        OrgsCity.put("7100","沈阳");
        OrgsCity.put("7200","西安");
        OrgsCity.put("7300","成都");
        OrgsCity.put("7400","济南");
        OrgsCity.put("7500","大连");
        OrgsCity.put("7600","郑州");
        OrgsCity.put("7700","天津");
        OrgsCity.put("7800","昆明");
        OrgsCity.put("7900","深圳");
        OrgsCity.put("8200","广州");
        OrgsCity.put("8300","重庆");
        OrgsCity.put("8900","苏州");
        OrgsCity.put("9100","北京");
        OrgsCity.put("9300","南京");
        OrgsCity.put("9400","宁波");
        OrgsCity.put("9500","杭州");
        OrgsCity.put("9800","上海");
        OrgsCity.put("9900","全行");
        //Orgs.put("9940","总行本部");
    }
    public static Map<String,String> codeAndprovinceName = new HashMap<>();
    static {
        codeAndprovinceName.put("9800","上海");
        codeAndprovinceName.put("9100","北京");
        codeAndprovinceName.put("7200","陕西");
        codeAndprovinceName.put("9400","浙江");
        codeAndprovinceName.put("9300","江苏");
        codeAndprovinceName.put("4300","福建");
        codeAndprovinceName.put("6400","江西");
        codeAndprovinceName.put("3600","福建");
        codeAndprovinceName.put("3500","青海");
        codeAndprovinceName.put("3400","海南");
        codeAndprovinceName.put("3300","宁夏");
        codeAndprovinceName.put("7800","云南");
        codeAndprovinceName.put("7700","天津");
        codeAndprovinceName.put("5800","安徽");
        codeAndprovinceName.put("7900","广东");
        codeAndprovinceName.put("3700","贵州");
        codeAndprovinceName.put("5900","内蒙古");
        codeAndprovinceName.put("7100","辽宁");
        codeAndprovinceName.put("7400","山东");
        codeAndprovinceName.put("7300","四川");
        codeAndprovinceName.put("7600","河南");
        codeAndprovinceName.put("9500","浙江");
        codeAndprovinceName.put("7500","辽宁");
        codeAndprovinceName.put("3200","西藏");
        codeAndprovinceName.put("4500","河北");
        codeAndprovinceName.put("6600","湖南");
        codeAndprovinceName.put("6900","山东");
        codeAndprovinceName.put("6800","山西");
        codeAndprovinceName.put("8900","江苏");
        codeAndprovinceName.put("4800","甘肃");
        codeAndprovinceName.put("8200","广东");
        codeAndprovinceName.put("6500","黑龙江");
        codeAndprovinceName.put("6300","广西");
        codeAndprovinceName.put("6000","新疆");
        codeAndprovinceName.put("8300","重庆");
        codeAndprovinceName.put("7000","湖北");
        codeAndprovinceName.put("6100","吉林");
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
