package com.spdb.nrpt.controller.custom;

import com.spdb.nrpt.service.custom.CustomBaseDateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class CustomBackstageController {

    @Autowired
    private CustomBaseDateService customBaseDateService;

    @RequestMapping("/getAllEchartsData")
    @ResponseBody
    //获得所有的Echars组件信息
    public Object getAllEchartsData(){
        return customBaseDateService.getAllEchartsData();
    }

    @RequestMapping("/updateEchartsID")
    @ResponseBody
    //更新divID对应的Echarts组件ID
    public String updateEchartsID(String echartsid,String divid){
    	//从session中获得组织ID
    	String orgID = "9900";
       return  customBaseDateService.updateEchartsID(echartsid,divid,orgID);
    }
}
