package com.spdb.nrpt.service.custom;

import com.spdb.nrpt.entity.custom.Echarts.CustomEchartsEntity;
import com.spdb.nrpt.entity.report.ReportEchartsEntity;
import com.spdb.nrpt.mapper.backstageMapper.BackstageMapper;
import com.spdb.nrpt.mapper.reportDataMapper.NRPTReportDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomBaseDateService {

    @Autowired
    private NRPTReportDataMapper nrptReportDataMapper;
    @Autowired
    private BackstageMapper backstageMapper;

    //修改Div对应的KeyID
    public String updateDivAndKeyID(String divID){
        return "Success";
    }

    //删除Div对应的KeyID
    public String deleteDivAndKeyID(String... divID){
        return "Success";
    }

    //获得该Div对应的Echarts组件信息
    public CustomEchartsEntity getDivAndEchars(String divID,String orgID){
        CustomEchartsEntity customEchartsEntity = backstageMapper.selectDivEchartsData(divID,orgID);
        return customEchartsEntity;
    }

    //更新divID对应的Echarts组件ID
    public String updateEchartsID(String echartsid,String divid,String orgID){
        try {
            backstageMapper.updateEchartsID(echartsid,divid,orgID);
        }catch (Exception e){
            log.error("更新divID对应的Echarts组件ID出错，错误为："+e);
        }
        return "Success";
    }

    //获得所有的Echars组件信息
    public Object getAllEchartsData() {
        return backstageMapper.selectAllReportCustomEcharts();
    }
    
    //通过divID和orgID得到该div对应的展示的数据指标      江
    public String getDivKeys(String divID,String orgID) {
    	return backstageMapper.selectDivKeys(divID,orgID);
    }
}
