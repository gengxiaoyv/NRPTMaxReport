package com.spdb.nrpt.service.custom;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.spdb.nrpt.entity.report.ReturnData;
import com.spdb.nrpt.mapper.reportDataMapper.NRPTReportDataMapper;

@Service
public class CustomDataStrategyService {
	
	 @Autowired
	 private NRPTReportDataMapper nrptReportDataMapper;

	//自定义报表数据处理类
	public Object getData(String type,String keyID,String orgID,String sql) {
		 switch(type){
		 	//实时数据
         	case "大屏实时数据" :
         		return getCurrentTimeData(keyID,orgID);
         		
         	//往期数据
         	case "大屏实时+往期数据" :
         		return getPastTimeData(keyID,orgID);
         		
         	//排名数据
         	case "排名数据":
         		return getRowData(keyID,orgID);
         		
         	//反射调用
         	case "特殊处理" :
         		return reflectionGetData();
         	
         	//自定义SQL
         	case "自定义SQL" :
         		return customSQLGetData();
         		
         default :
             return null;
     }
	}
	
	//获得某一指标的实时数据
	public Object getCurrentTimeData(String keyID,String orgID) {
		//获得该div对应的指标
        ReturnData data= nrptReportDataMapper.selectOneReportDataByDivIDAndOrgID(keyID,orgID);
		return data;
	}
	
	//获得实时+往期数据
	public Object getPastTimeData(String keyID,String orgID) {
	
	     List<ReturnData> list = new ArrayList<>();
	     //获得实时数据
	     ReturnData returnData = nrptReportDataMapper.selectReportDataByDivIDAndOrgID(keyID,orgID).get(0);
	     if(returnData!=null) {
	    	 list.add(returnData);
	     }
	     //获得往期数据
	     PageHelper.startPage(1,2);
	     List<ReturnData> dataList = nrptReportDataMapper.selectReportPastDataByDivIDAndOrgID(keyID,orgID);
	     for (ReturnData data :dataList){
	    	 list.add(data);
	     }
	     
		return list;
	}
	
	//排名数据(网点或分行)
	public Object getRowData(String keyID,String orgID) {
		
		List<ReturnData> dataList = new LinkedList<ReturnData>();
		//总行用户查询分行的排名
		if("9900".equals(orgID)) {
	        dataList = nrptReportDataMapper.selectReportDataByDivIDAndOrgIDDesc(keyID);
		}else {
			//分行用户查询网点的排名
            dataList = nrptReportDataMapper.selectDescReportBranchByParentIDAndKPIID(keyID,orgID);
		}
		return dataList;
	}
	
	//通过反射调用特殊数据处理
	public Object reflectionGetData() {
		
		//类
		String className = "";
		//方法名
		String method = "";
		
		//反射调用获得数据
		
		
		return "";
	}
	
	//自定义SQL调用
	public Object customSQLGetData() {
		return "";
	}
	
}
