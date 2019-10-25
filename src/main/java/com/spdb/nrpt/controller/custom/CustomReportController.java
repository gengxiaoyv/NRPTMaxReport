package com.spdb.nrpt.controller.custom;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.spdb.nrpt.config.VariableContext;
import com.spdb.nrpt.entity.custom.CustomEntity;
import com.spdb.nrpt.entity.custom.ReportReturnCusEntity;
import com.spdb.nrpt.entity.custom.Echarts.CustomEchartsEntity;
import com.spdb.nrpt.service.custom.CustomBaseDateService;
import com.spdb.nrpt.service.custom.CustomDataDealService;

import lombok.extern.slf4j.Slf4j;

/*
 * 该controller类用来返回前端大屏显示的所有展示数据
 * */
@Controller
@Slf4j
public class CustomReportController {

	 @Autowired
	 private CustomBaseDateService customBaseDateService;
	 @Autowired
	 private CustomDataDealService customDataDealService;
	
	//获得大屏模块全部数据
    @RequestMapping("/getReportAllData_2")
    @ResponseBody
    public Object getReportAllData(String key, HttpServletRequest request) {
    	 //获得当前用户的组织ID
        HttpSession httpSession = request.getSession();
        String orgID = httpSession.getAttribute("orgID").toString();
        //构建返回实体类 ReportReturnCusEntity
        ReportReturnCusEntity returnCusEntity = new ReportReturnCusEntity();
        returnCusEntity.setName(key);
        returnCusEntity.setTitle(VariableContext.Orgs.get(orgID));
        //通过传入的key得到divIDList
        List<String> divIDList = VariableContext.Segmentation.get(key);
        //遍历divIDList
        for(String divID:divIDList) {
        	//创建返回给前端的CustomEntity
        	CustomEntity customEntity = new CustomEntity();
        	customEntity.setDivID(divID);
        	//获得对应的Echarts信息   获得DIV和组织对应的的Echarts信息
            CustomEchartsEntity customEchartsEntity = customBaseDateService.getDivAndEchars(divID,orgID);
            //组件详情
            String jsonEchartsData = customEchartsEntity.getEchartsJson();
            //详情对应的实体类
            String echartsClass = customEchartsEntity.getEchartsClass();
            //echartsID
            String echartsID = customEchartsEntity.getEchartsID();
            //放入该DivID对应的Echarts组件类型
            customEntity.setEchartsType(echartsID);
            //放入该DivID对应的Echarts组件参数数据
            try {
                customEntity.setEchartsData(JSONObject.parseObject(jsonEchartsData, Class.forName(echartsClass)));
            } catch (ClassNotFoundException e) {
                log.error("该："+jsonEchartsData+"-->转换为："+echartsClass+"异常");
            }
            //通过divID,orgID,得到返回的数据指标  数据指标为以','隔开的String字符串
        	String keysID = customBaseDateService.getDivKeys(divID, orgID);
        	//得到每一个指标Key
        	List<String> keyList = Arrays.asList(keysID.split(","));
        	//补全echartsData与reportData
            if (jsonEchartsData.contains("轴数据自动补全")||jsonEchartsData.contains("图例数据自动补全")){
                customEntity = customDataDealService.getDealEchartsJson(key,divID,echartsID,keysID,orgID,customEntity);
            }
            
           
        }
        
        
    	return null;
    }
	
}
