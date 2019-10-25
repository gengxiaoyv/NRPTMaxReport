package com.spdb.nrpt.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class VariableContext {

    public static final List<String> Report1DivName =  Arrays.asList("大屏1信息总览", "大屏1全行存款", "大屏1全行贷款", "大屏1地图", "大屏1全行存款排名", "大屏1全行贷款排名", "大屏1营收趋势", "大屏1分行存贷款情况", "大屏1同业负债结构");

    public static final List<String> Report2DivName =  Arrays.asList("大屏2一般对公存款排名前十", "大屏2一般对公贷款排名后十", "大屏2树形图", "大屏2公司贷款利息收入趋势", "大屏2存款贷款分析（存款）", "大屏2存款贷款分析（贷款）", "大屏2公司客户数");

    public static final List<String> Report3DivName =  Arrays.asList("大屏3保证金存款排名前十", "大屏3保证金存款排名后十", "大屏3云图", "大屏3零售贷款利息收入趋势", "大屏3存款贷款分析（存款）", "大屏3存款贷款分析（贷款）", "大屏3个人客户数");

    public static final List<String> Report1DivID =  Arrays.asList("100101", "100102", "100103","100104","100105","100106","100107","100108","100109");

    public static final List<String> Report2DivID =  Arrays.asList("100201", "100202", "100203","100204","100205","100206","100207");

    public static final List<String> Report3DivID =  Arrays.asList("100301", "100302", "100303","100304","100305","100306","100307");

    public static final List<String> EnglishWeek =  Arrays.asList("Mon", "Tues", "Wed","Thur","Fri","Sat","Sun");

    public static final List<String> EnglishMonth = Arrays.asList("Jan", "Feb", "Mar","Apr","May","June","July","Aug","Sept","Oct","Nov","Dec");

    public static final List<String>  NRPTRule1 = Arrays.asList("同期","上日","上月");

    public static final List<String>  NRPTRule2 = Arrays.asList("逾期贷款","对公中长期贷款","对公短期贷款","票据融资");

    public static final List<String>  NRPTRule3 = Arrays.asList("住房按揭贷款","经营性贷款","商用房贷款","消费贷款","其它贷款");

    public static final List<String>  NRPTRule4 = Arrays.asList("货币市场交易","同业存放活期","同业定期存单","卖出回购票据","同业存放定期");

    //网点指标
    public static final List<String> Level2KeyListIDs = Arrays.asList("GM030201","GM030202","GM030203","GM030204","GM030205","GM010201","GM010202","GM010203","GM010204","GM010205","GM0101","GM010101","GM01010101","GM01010102","GM0101010201","GM0301","GM030101","GM030102");
    
    //
    public static final String Level2KeyIDs = "GM030201,GM030202,GM030203,GM030204,GM030205,GM010201,GM010202,GM010203,GM010204,GM010205,GM0101,GM010101,GM01010101,GM01010102,GM0101010201,GM0301,GM030101,GM030102";
    
    
    //总行和一级分行ID
    public static final String Level1OrgIDs = "9900,3200,3300,3400,3500,3600,3700,4300,4500,4800,5800,5900,6000,6100,6300,6400,6500,6600,6800,6900,7000,7100,7200,7300,7400,7500,7600,7700,7800,7900,8200,8300,8900,9100,9300,9400,9500,9800";

    //策略
    public static Map<String,List<String>> Segmentation = new HashMap<>();

    static {
        Segmentation.put("EnglishWeek",EnglishWeek);
        Segmentation.put("EnglishMonth",EnglishMonth);
        Segmentation.put("NRPTRule1",NRPTRule1);
        Segmentation.put("NRPTRule2",NRPTRule2);
        Segmentation.put("NRPTRule3",NRPTRule3);
        Segmentation.put("NRPTRule4",NRPTRule4);
        Segmentation.put("大屏一全部数据",Report1DivID);
        Segmentation.put("大屏二全部数据",Report2DivID);
        Segmentation.put("大屏三全部数据",Report3DivID);
        Segmentation.put("大屏一全部名称",Report1DivName);
        Segmentation.put("大屏二全部名称",Report2DivName);
        Segmentation.put("大屏三全部名称",Report3DivName);
    }

    //大屏指标
    public static Map<String,String> Keys = new HashMap<>();
    static {
        //全行维度
        Keys.put("YS01","全行营业收入");
        Keys.put("GM01","全行存款");
        Keys.put("GM03","全行贷款");
        Keys.put("YS01010101","公司贷款利息收入");
        Keys.put("YS01010102","零售贷款利息收入");

        //零售（个人）
        Keys.put("GM030201","住房按揭贷款");          //个贷
        Keys.put("GM030202","经营性贷款");            //个贷
        Keys.put("GM030203","商用房贷款");            //个贷
        Keys.put("GM030204","消费贷款");              //个贷
        Keys.put("GM030205","其他贷款");              //个贷-住房按揭贷款-经营性贷款-商用房贷款-消费贷款

        Keys.put("GM0102","个人存款");
        Keys.put("GM010201","其他定期存储存款");
        Keys.put("GM010202","结算性存款");
        Keys.put("GM010203","大额存单");
        Keys.put("GM010204","保证金存款");
        Keys.put("GM010205","结构性存款");
        //公司

        Keys.put("GM0101","一般对公存款");
        Keys.put("GM010101","对公存款");
        Keys.put("GM0301","一般对公贷款");        //对公贷款+票据融资
        Keys.put("GM030101","对公贷款");          //对公中长期贷款+对公短期贷款+逾期贷款
        Keys.put("GM01010101","结构性存款");
        Keys.put("GM01010102","对公基础性存款");
        Keys.put("GM0101010201","结算性存款");
        Keys.put("GM030102","票据融资");




        Keys.put("GM03010101","对公中长期贷款");
        Keys.put("GM03010102","对公短期贷款");
        Keys.put("GM03010103","逾期贷款");
        //金融市场
        Keys.put("GM020101","同业存款定期");
        Keys.put("GM020102","同业存款活期");
        Keys.put("GM0202","货币市场融入");
        Keys.put("GM0203","发行同业存单");
        Keys.put("GM0204","卖出回购票据");

        Keys.put("NRPT01","主动负债+单位保本理财");
        Keys.put("02","公司客户数");
        Keys.put("01","个人客户数");
    }

    public static Map<String,String> Orgs = new HashMap<>();

    //机构参数
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


    public static Map<String,String> NameOrgs = new HashMap<>();

    //机构参数
    static {
        NameOrgs.put("拉萨","3200");
        NameOrgs.put("银川","3300");
        NameOrgs.put("海口","3400");
        NameOrgs.put("西宁","3500");
        NameOrgs.put("厦门","3600");
        NameOrgs.put("贵阳","3700");
        NameOrgs.put("福州","4300");
        NameOrgs.put("石家庄","4500");
        NameOrgs.put("兰州","4800");
        NameOrgs.put("合肥","5800");
        NameOrgs.put("呼和浩特","5900");
        NameOrgs.put("乌鲁木齐","6000");
        NameOrgs.put("长春","6100");
        NameOrgs.put("南宁","6300");
        NameOrgs.put("南昌","6400");
        NameOrgs.put("哈尔滨","6500");
        NameOrgs.put("长沙","6600");
        NameOrgs.put("太原","6800");
        NameOrgs.put("青岛","6900");
        NameOrgs.put("武汉","7000");
        NameOrgs.put("沈阳","7100");
        NameOrgs.put("西安","7200");
        NameOrgs.put("成都","7300");
        NameOrgs.put("济南","7400");
        NameOrgs.put("大连","7500");
        NameOrgs.put("郑州","7600");
        NameOrgs.put("天津","7700");
        NameOrgs.put("昆明","7800");
        NameOrgs.put("深圳","7900");
        NameOrgs.put("广州","8200");
        NameOrgs.put("重庆","8300");
        NameOrgs.put("苏州","8900");
        NameOrgs.put("北京","9100");
        NameOrgs.put("南京","9300");
        NameOrgs.put("宁波","9400");
        NameOrgs.put("杭州","9500");
        NameOrgs.put("上海","9800");
        //Orgs.put("9900","境内机构合计");
        //Orgs.put("9940","总行本部");
    }
}
