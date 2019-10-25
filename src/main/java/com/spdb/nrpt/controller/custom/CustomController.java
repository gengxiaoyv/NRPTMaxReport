package com.spdb.nrpt.controller.custom;

import com.alibaba.fastjson.JSONObject;
import com.spdb.nrpt.config.VariableContext;
import com.spdb.nrpt.entity.custom.CustomEntity;
import com.spdb.nrpt.entity.custom.Echarts.CustomEchartsEntity;
import com.spdb.nrpt.entity.report.ReportEchartsEntity;
import com.spdb.nrpt.service.custom.CustomBaseDateService;
import com.spdb.nrpt.service.custom.CustomDealDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.faces.annotation.RequestMap;
import java.util.Map;

@Controller
@Slf4j
public class CustomController {

    @Autowired
    private CustomBaseDateService customBaseDateService;
    @Autowired
    private CustomDealDataService customDealDataService;

   
    @RequestMapping("/getCustomReportData")
    @ResponseBody
    //获得报表的全部信息
    public Object getCustomReportData(){
    	//从session中获得组织ID
        String orgID = "9900";

        //当前页面为
        
        //获得当前页面所有的DIVID
        
        //遍历拼装数据
        
        //返回给前端的数据就是CustomEntity
        CustomEntity customEntity = new CustomEntity();
        String divID = "100102";
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
        //最终的数据处理
        if (jsonEchartsData.contains("轴数据自动补全")||jsonEchartsData.contains("图例数据自动补全")){
            customEntity = customDealDataService.getReportData(divID,echartsID,orgID,customEntity);
        }else {
            CustomEntity customEntity1 = new CustomEntity();
            customEntity1 = customDealDataService.getReportData(divID,echartsID,orgID,customEntity1);
            customEntity.setReportData(customEntity1.getReportData());
        }
        return customEntity;
    }

}
