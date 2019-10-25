package com.test;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.spdb.nrpt.entity.custom.Bar.Bar002;
import com.spdb.nrpt.entity.custom.Bar.Bar003;
import com.spdb.nrpt.entity.custom.Line.Line003;
import com.spdb.nrpt.util.RSAEncrypt;

public class Test {
    public static void main(String[] args) {
		
		  String publicKey =
		  "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCfh7tDm5l55gP7PJcyJgQ88YN+0ZmBkIrkm+stZdq48PAuUiMy7ARaQs8Y3TNTLw2PGksUkkaq02P/nmmEeBQ/zJ/MnQw9HTRkcctwvFjfPAgHkOivLWsjEQ1ONqtJMWVcAU4JxS5mZ9anRfkJzaCtpW8MNf90kwkwl5cfjkDtzQIDAQAB";
		  String privateKey =
		  "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJ+Hu0ObmXnmA/s8lzImBDzxg37RmYGQiuSb6y1l2rjw8C5SIzLsBFpCzxjdM1MvDY8aSxSSRqrTY/+eaYR4FD/Mn8ydDD0dNGRxy3C8WN88CAeQ6K8tayMRDU42q0kxZVwBTgnFLmZn1qdF+QnNoK2lbww1/3STCTCXlx+OQO3NAgMBAAECgYAIXwf9mTmpgt9snJZWhFYumIHM8tS6TsprCp+Bp1md4M7Jr3e3YVktH1pctKbP06VDgB62eqKIrkUOEdL5p49wYFz9oF7Rzj/xwAun0GyJLQqk+9KtNkdfHG/T9A4ttCXS0Xew9nR257m9f+zClxyBHEoQxZffmnvRG0owHbBI4QJBANdSGRVgNFFEf9ficYfNSZH0N/zGwtW/6IGksRr8S9mkXJvUQlLfvZuuz1C05hhQ2ITi2siLxYZOrUkvjRIBbKUCQQC9q1kO5ZBF0HqsamJtnWfXM5hPJtVrDehRZ4rhA8uEmiRyKsQV+DUaazH7M7lqykUs0THTPPSf8KlWWQayHOwJAkEAqW0AL8GzaP7tWYHks2blLONt6oi7ZlXLLbfZY9KCHI8oD7XFOlCzcXzrxCWTqC51MJsQbB0PH89oqt2vsytLwQJAGAGjEQ6fVybPEBmbAbLFnOhr48DPjHBDMmJ/ooFfdhYofMbK8Njsp75foiLm2gxl+wkzXEA/2iqU1FgKT+r3OQJBAJst/1TKJUGykBqf+lfXwz5YKOKX2CLdCK2R35k76UjgNkwhCaLVzMt3yi7HmshDlB7QTasZ5yFvdFSnZhTZToY=";
		  //String message = "userID=10001147&userOrgID=9800&resID=155851605679552144";
		  String message = "userID=11002158&userOrgID=9100&resID=155851605679552144";
		  //String message = "userID=12015057&userOrgID=9900&resID=155851605679552144";
		  String token=""; try { token = RSAEncrypt.encrypt(message,publicKey);
		  System.out.println(token); String res = RSAEncrypt.decrypt(token,privateKey);
		  System.out.println(res); String decode = res; int flag1 =
		  decode.indexOf("=")+1; int flag2 = decode.indexOf("=",flag1)+1; int flag3 =
		  decode.indexOf("=",flag2)+1; String userID = decode.substring(flag1,flag1+8);
		  String userOrg = decode.substring(flag2,flag2+4); String res_id =
		  decode.substring(flag3);
		  System.out.println("当前登录用户为："+userID+"---当前用户组织为："+userOrg+"---当前res_id为"+
		  res_id); } catch (Exception e) { e.printStackTrace(); }
		 
       /* System.out.println(TimeUtil.getAllPast30Time(6));*/
       /* Date date = new Date();
        System.out.println(date);
        System.out.println();*/
        //System.out.println(TimeUtil.getPastTime(4,0.5));
        //System.out.println(TimeUtil.getPastTime(0,0));
    	
		/*
		 * String level2OrgID = ",9900,9800,9700,9600,9500";
		 * 
		 * //大于100个网点的分行，拆分获得数据 int length = level2OrgID.length()/10+1;
		 * System.out.println(length);
		 * 
		 * String[] level2OrgIDs = new String[length]; for(int i =0 ;i <length-1;i++) {
		 * //每次从0开始截取10位，截取数据保存数组 level2OrgIDs[i] =
		 * level2OrgID.substring(0,10).substring(1); //自己保留后续数据 level2OrgID =
		 * level2OrgID.substring(10); } level2OrgIDs[length-1] =
		 * level2OrgID.substring(1); System.out.println(JSONArray.toJSON(level2OrgIDs));
		 */
		/*
		 * List<String> xData = new ArrayList<>(); xData.add("今日"); xData.add("上日");
		 * xData.add("上月"); System.out.println(JSONObject.toJSON(new
		 * Bar002("",xData,"")));
		 * 
		 * System.out.println(JSONObject.toJSON(new Bar003("图例数据自动补全","轴数据自动补全")));
		 * 
		 * System.out.println(JSONObject.toJSON(new
		 * Line003(false,"circle","轴数据自动补全","图例数据自动补全")));
		 */
    }
}
