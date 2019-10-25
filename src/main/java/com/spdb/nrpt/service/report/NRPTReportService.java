package com.spdb.nrpt.service.report;

import com.alibaba.fastjson.JSONObject;
import com.spdb.nrpt.entity.div.DivEntity;
import com.spdb.nrpt.entity.echarts.AxisEntity;
import com.spdb.nrpt.entity.report.ReportEchartsEntity;
import com.spdb.nrpt.entity.report.ReturnData;
import com.spdb.nrpt.mapper.reportDataMapper.NRPTReportDataMapper;
import com.spdb.nrpt.util.TimeUtil;
import com.sun.org.apache.xpath.internal.operations.Div;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class NRPTReportService {

    @Autowired
    private NRPTReportDataMapper nrptReportDataMapper;

    @Autowired
    private NRPTDealDataService nrptDealDataService;

    @Autowired
    private NRPTReport1Service nrptReport1Service;

    @Autowired
    private NRPTReport2Service nrptReport2Service;

    @Autowired
    private NRPTReport3Service nrptReport3Service;

    //获得divEcharts组件参数
    public Object getDivEcharsData(String divID,String orgID){
        if ("100109".equals(divID)){
            if (!("9900".equals(orgID))){
                divID="100109"+orgID;
            }
        }
        ReportEchartsEntity reportEchartsEntity = nrptReportDataMapper.selectOptionByID(divID);
        String jsonData = reportEchartsEntity.getDivEchartsJson();
        String type = reportEchartsEntity.getDivEchartsType();
        if (!("none".equals(jsonData))){
            try {
                Object option = JSONObject.parseObject(jsonData, Class.forName(type));
                log.info("返回出去的实体类是 ： ",option.toString());
                return option;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //大屏数据特殊处理
    public DivEntity dealData(String key, String divID, DivEntity divEntity,String orgID){
        List<String> keyIDs = getKeyIDByDivID(divID);
        if ("大屏一全部数据".equals(key)){
            List<List<ReturnData>> data =  nrptReport1Service.getDivDataByKeyID(divID,keyIDs,orgID);
            if (data==null){
                if ("100108".equals(divID)){
                    divEntity.setData(nrptReport1Service.get100108Data(keyIDs,orgID));
                }else if ("100109".equals(divID)){
                    divEntity.setData(nrptReport1Service.get100109Data(keyIDs,orgID));
                }else if ("100104".equals(divID)){
                    divEntity.setData(nrptReport1Service.get100104Data(keyIDs,orgID));
                }
            }else {
                divEntity.setData(data);
                if(data.size()==1&&(!"100109".equals(divID))||"100102".equals(divID)){
                    if ("100102".equals(divID)){
                        List<String> axisList = Arrays.asList("今日","上日","上月");
                        divEntity.setAxis(axisList);
                    } else {
                        List<String> axisList = new ArrayList<>();
                        for (ReturnData returnData : data.get(0)){
                            String name = returnData.getName();
                            axisList.add(name);
                        }
                        divEntity.setAxis(axisList);
                        if ("100107".equals(divID)){
                            AxisEntity axisEntity = new AxisEntity();
                            axisEntity.setData(axisList);
                            axisEntity.setMin(nrptReportDataMapper.selectReportMinPastDateDataByDivIDAndOrgID("YS01",orgID).get(0).getValue());
                            divEntity.setAxis(axisEntity);
                        }
                    }
                }
            }
        }else if ("大屏二全部数据".equals(key)){
            List<ReturnData> data2 = nrptReport2Service.getDivDataByKeyID(divID,keyIDs,orgID);
            if (data2==null){
                if ("100207".equals(divID)){
                    divEntity.setData(nrptReport2Service.get100207Data(keyIDs,orgID));
                    List<String> timeList = TimeUtil.getSixMonthLastDay();
                    divEntity.setAxis(timeList);
                }else if("100204".equals(divID)){
                    divEntity.setData(nrptReport2Service.get100204Data(keyIDs,orgID));
                    AxisEntity axisEntity = new AxisEntity();
                    axisEntity.setData(nrptDealDataService.trendAxis());
                    axisEntity.setMin(nrptReportDataMapper.selectReportMinPastDateDataByDivIDAndOrgID("YS01010101",orgID).get(0).getValue());
                    divEntity.setAxis(axisEntity);
                }
            }else {
                divEntity.setData(data2);
                if ("100201".equals(divID)||"100202".equals(divID)){
                    List<String> axisList = new ArrayList<>();
                    for (ReturnData returnData : data2){
                        String name = returnData.getName();
                        axisList.add(name);
                    }
                    divEntity.setAxis(axisList);
                }
            }
        }else if ("大屏三全部数据".equals(key)){
            List<ReturnData> data3 = nrptReport3Service.getDivDataByKeyID(divID,keyIDs,orgID);
            if (data3==null){
                if ("100307".equals(divID)){
                    divEntity.setData(nrptReport3Service.get100307Data(keyIDs,orgID));
                    List<String> timeList = TimeUtil.getSixMonthLastDay();
                    divEntity.setAxis(timeList);
                }else if ("100304".equals(divID)){
                    divEntity.setData(nrptReport3Service.get100304Data(keyIDs,orgID));
                    AxisEntity axisEntity = new AxisEntity();
                    axisEntity.setData(nrptDealDataService.trendAxis());
                    axisEntity.setMin(nrptReportDataMapper.selectReportMinPastDateDataByDivIDAndOrgID("YS01010102",orgID).get(0).getValue());
                    divEntity.setAxis(axisEntity);
                }
            }else {
                divEntity.setData(data3);
                if ("100301".equals(divID)||"100302".equals(divID)){
                    List<String> axisList = new ArrayList<>();
                    for (ReturnData returnData : data3){
                        String name = returnData.getName();
                        axisList.add(name);
                    }
                    divEntity.setAxis(axisList);
                }
            }
        }
        return divEntity;
    }

    //通过divID获得keyID
    public List<String> getKeyIDByDivID(String divID){
        return nrptReportDataMapper.selectKeyIDByDivID(divID);
    }

}

