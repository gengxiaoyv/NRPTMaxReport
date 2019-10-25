package com.spdb.nrpt.service.report;

import com.spdb.nrpt.config.VariableContext;
import com.spdb.nrpt.entity.div.DivEntity;
import com.spdb.nrpt.entity.echarts.AxisEntity;
import com.spdb.nrpt.entity.echarts.line.LineDataEntity;
import com.spdb.nrpt.entity.report.ReturnData;
import com.spdb.nrpt.mapper.reportDataMapper.NRPTReportDataMapper;
import com.spdb.nrpt.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class NRPTDealDataService {

    @Autowired
    private NRPTReportDataMapper nrptReportDataMapper;

    //拼接轴数据
    public DivEntity dealAxis(DivEntity divEntity, List<ReturnData> data){
        List<String> axList = new ArrayList<>();
        for (ReturnData returnData: data){
            String name = returnData.getName();
            axList.add(name);
        }
        divEntity.setAxis(axList);
        return divEntity;
    }

    //比上月，比上日，	比年初（取消）
    public List<ReturnData> compareData(List<String> keyIDs,String orgID){
        List<ReturnData> returnList = new ArrayList<>();
        List<ReturnData> list = nrptReportDataMapper.selectReportPastDataByDivIDAndOrgID(keyIDs.get(0),orgID);
        ReturnData returnData = new ReturnData();
        try {
            returnData = nrptReportDataMapper.selectReportDataByDivIDAndOrgID(keyIDs.get(0),orgID).get(0);
        }catch (Exception e){
            log.error("该指标"+keyIDs.get(0)+"无");
            returnData.setName(VariableContext.Keys.get(keyIDs.get(0)));
            returnData.setValue("0");
            list.add(returnData);
            list.add(returnData);
            list.add(returnData);
            return list;
        }

        returnList.add(returnData);
        BigDecimal nowData = new BigDecimal (returnData.getValue());
        for (ReturnData data : list){
            BigDecimal  intData = new BigDecimal (data.getValue());
            BigDecimal res = nowData.subtract(intData);
            data.setValue(res.toString());
            returnList.add(data);
        }
        return returnList;
    }

    //趋势图特殊x轴
    public  List<String> trendAxis(){
        List<String> timeList = TimeUtil.getAllPast30Time(5);
        List<String> axisList = new ArrayList<>();
        for (int j =0; j<10; j++){
            axisList.add(timeList.get(j));
            for (int i=0;i<5;i++){
                axisList.add("");
            }
        }
        return axisList;
    }

    //趋势图数据特殊处理
    public List<LineDataEntity> dealTrendData(List<ReturnData> list){
        List<LineDataEntity> dataList = new ArrayList<>();
        for (int i=0; i<list.size(); i++){
            LineDataEntity lineDataEntity = new LineDataEntity();
            lineDataEntity.setValue(list.get(i).getValue());
            lineDataEntity.setName(list.get(i).getValue());
            lineDataEntity.setSymbolSize("3");
            if(i%6==0){
                lineDataEntity.setSymbolSize("8");
            }
            dataList.add(lineDataEntity);
        }
        return dataList;
    }
}
