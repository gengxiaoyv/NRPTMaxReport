package com.spdb.nrpt.service.custom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spdb.nrpt.entity.custom.CusReturnData;
import com.spdb.nrpt.entity.custom.CustomEntity;
import com.spdb.nrpt.entity.custom.Bar.Bar001;
import com.spdb.nrpt.mapper.backstageMapper.BackstageReturnMapper;

import lombok.extern.slf4j.Slf4j;

/*
 * Author:江羚嘉
 * 
 * */
@Service
@Slf4j
public class CustomDataDealService {
	
	@Autowired
	private BackstageReturnMapper backstageReturnMapper;
	
	public CustomEntity getDealEchartsJson(String key,String divID, String EchartsType,String keysID, String orgID,CustomEntity customEntity){
		//得到每一个Key 存入List集合中
    	List<String> keyList = Arrays.asList(keysID.split(","));
		switch(EchartsType){
            case "Bar001" :
                return dealBar001(divID,orgID,customEntity,keyList);
            case "Bar002" :
                return dealBar002(divID,orgID,customEntity,keyList);
            case "Bar003" :
                return dealBar003(divID,orgID,customEntity,keyList);
            case "Line001" :
                return dealLine001AndLine002(divID,orgID,customEntity,keyList);
            case "Line002" :
                return dealLine001AndLine002(divID,orgID,customEntity,keyList);
            case "Line003" :
                return dealPie001AndLine003(divID,orgID,customEntity,keyList);
            case "Waterfall001" :
                return dealWaterfall001(divID,orgID,customEntity,keyList);
            case "Radar001" :
                return dealRadar001(divID,orgID,customEntity,keyList);
            case "Pie001" :
                return dealPie001AndPie002(divID,orgID,customEntity,keyList);
            case "Pie002" :
                return dealPie001AndPie002(divID,orgID,customEntity,keyList);
            case "Pie003" :
                return dealPie003(divID,orgID,customEntity,keyList);
            case "Pie004" :
                return dealPie004(divID,orgID,customEntity,keyList);
            case "YBar001":
                return dealYBar001(divID,orgID,customEntity,keyList);
            case "YBar002":
                return dealYBar002(divID,orgID,customEntity,keyList);
            case "YBar003":
                return dealYBar003(divID,orgID,customEntity,keyList);
            case "Funnel001":
                return dealFunnel001(divID,orgID,customEntity,keyList);
            default :
                return null;
        }
    }
	
	
	public CustomEntity dealBar001(String divID,String orgID,CustomEntity customEntity,List<String> keyList) {
		//普通柱状图
		Bar001 bar001 = (Bar001)customEntity.getEchartsData();
		List<Map<String,Object>> aAxis = new ArrayList<>();
		Map<String,Object> objMap = new HashMap<>();
		objMap.put("type", "category");
		List<String> dataList = new ArrayList<>();
		for(String key:keyList) {
			CusReturnData cusReturnData = backstageReturnMapper.queryKPI_ValueAndKPI_Name(key,orgID);
			dataList.add(cusReturnData.getKpiName());
		}
		objMap.put("data", dataList);
		aAxis.add(objMap);
		bar001.setXAxis(aAxis);
		customEntity.setEchartsData(bar001);
		return null;
	}
	
	public CustomEntity dealBar002(String divID,String orgID,CustomEntity customEntity,List<String> keyList) {
		return null;
	}
	
	public CustomEntity dealBar003(String divID,String orgID,CustomEntity customEntity,List<String> keyList) {
		return null;
	}
	
	public CustomEntity dealLine001AndLine002(String divID,String orgID,CustomEntity customEntity,List<String> keyList) {
		return null;
	}
	
	public CustomEntity dealPie001AndLine003(String divID,String orgID,CustomEntity customEntity,List<String> keyList) {
		return null;
	}
	
	public CustomEntity dealWaterfall001(String divID,String orgID,CustomEntity customEntity,List<String> keyList) {
		return null;
	}
	
	public CustomEntity dealRadar001(String divID,String orgID,CustomEntity customEntity,List<String> keyList) {
		return null;
	}
	
	public CustomEntity dealPie001AndPie002(String divID,String orgID,CustomEntity customEntity,List<String> keyList) {
		return null;
	}
	
	public CustomEntity dealPie003(String divID,String orgID,CustomEntity customEntity,List<String> keyList) {
		return null;
	}
	
	public CustomEntity dealPie004(String divID,String orgID,CustomEntity customEntity,List<String> keyList) {
		return null;
	}
	
	public CustomEntity dealYBar001(String divID,String orgID,CustomEntity customEntity,List<String> keyList) {
		return null;
	}
	
	public CustomEntity dealYBar002(String divID,String orgID,CustomEntity customEntity,List<String> keyList) {
		return null;
	}
	
	public CustomEntity dealYBar003(String divID,String orgID,CustomEntity customEntity,List<String> keyList) {
		return null;
	}
	
	public CustomEntity dealFunnel001(String divID,String orgID,CustomEntity customEntity,List<String> keyList) {
		return null;
	}
}
