package com.test;

import com.alibaba.fastjson.JSONObject;
import com.spdb.nrpt.entity.echarts.pie.PieEntity;

public class Test2 {
	public static void main(String[] args) {
		/*
		 * Pie003 pie003 = new Pie003(); pie003.setLegend("图例数据自动补全"); List<String> list
		 * = new ArrayList<>(); list.add("35%"); list.add("60%");
		 * pie003.setRadius(list); System.out.println(JSONObject.toJSON(pie003));
		 * 
		 * YBar002 yBar002 = new YBar002("图例数据自动补全","轴数据自动补全");
		 * System.out.println(JSONObject.toJSON(yBar002));
		 * 
		 * YBar003 yBar003 = new YBar003("图例数据自动补全","轴数据自动补全","1");
		 * System.out.println(JSONObject.toJSON(yBar003));
		 */
		
		
		/*
		 * Pie004 pie004 = new Pie004(); pie004.setLegend("图例数据自动补全");
		 * List<List<String>> lists = new ArrayList<>(); List<String> list1 = new
		 * ArrayList<>(); List<String> list2 = new ArrayList<>(); list1.add("40%");
		 * list1.add("55%"); list2.add("0%"); list2.add("28"); lists.add(list1);
		 * lists.add(list2); pie004.setRadius(lists);
		 * System.out.println(JSONObject.toJSON(pie004));
		 */
		
		/* System.out.println(UUID.randomUUID().toString().replaceAll("-", "")); */
		
		//System.out.println(TimeUtil.getNowDate().substring(11));
		
		String str = "{\"grid\":{\"height\":\"\",\"left\":\"\",\"top\":\"\",\"width\":\"\"},\"legend\":[{\"data\":[\"住房按揭\",\"经营性\",\"商用房\",\"消费\",\"其他\"]},{\"data\":[\"逾期\",\"对公中长期\",\"对公短期\",\"票据融资\"]}],\"series\":[{\"radius\":[\"42%\",\"60%\"]},{\"radius\":[\"0%\",\"30%'\"]}]}";
		try{
			Class clazz = Class.forName("com.spdb.nrpt.entity.echarts.pie.PieEntity");
			PieEntity obj = (PieEntity)JSONObject.parseObject(str,clazz);
			System.out.print(obj.getGrid());
			//System.out.print(obj.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
