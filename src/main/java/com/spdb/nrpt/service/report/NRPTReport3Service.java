package com.spdb.nrpt.service.report;

import com.github.pagehelper.PageHelper;
import com.spdb.nrpt.config.VariableContext;
import com.spdb.nrpt.entity.Unit.ReportUnit;
import com.spdb.nrpt.entity.echarts.line.LineDataEntity;
import com.spdb.nrpt.entity.procedure.OutData;
import com.spdb.nrpt.entity.report.ReturnData;
import com.spdb.nrpt.mapper.reportDataMapper.NRPTReportDataMapper;
import com.spdb.nrpt.util.NumUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class NRPTReport3Service {

    @Autowired
    private NRPTReportDataMapper nrptReportDataMapper;
    @Autowired
    private NRPTDealDataService nrptDealDataService;

    //通过keyID获得divID对应的数据
    public List<ReturnData> getDivDataByKeyID(String divID, List<String> keyIDs,String orgID){
        switch(divID){
            case "100301" :
                return get100301Data(keyIDs,orgID);
            case "100302" :
                return get100302Data(keyIDs,orgID);
            case "100303" :
                return get100303Data(keyIDs,orgID);
            case "100304" :
                return null;
            case "100305" :
                return get100305Data(keyIDs,orgID);
            case "100306" :
                return get100306Data(keyIDs,orgID);
            case "100307" :
                return null;
            default :
                return null;
        }
    }

    //一般对公存款排名前十
    public List<ReturnData> get100301Data(List<String> keyIDs,String orgID){
        List<ReturnData> list = new ArrayList<>();
        //分页取前十
        if ("9900".equals(orgID)){
        	PageHelper.startPage(1,10);
            list = nrptReportDataMapper.selectReportDataByDivIDAndOrgIDDesc(keyIDs.get(0));
        }else {
        	PageHelper.startPage(1,10);
            list = nrptReportDataMapper.selectDescReportBranchByParentIDAndKPIID(keyIDs.get(0),orgID);
        }
        if(list.size()<10) {
        	for(int i=list.size();i<=10;i++) {
        		ReturnData returnData = new ReturnData("","0");
        		list.add(returnData);
        	}
        }
        return list;
    }

    //一般对公贷款排名后十
    public List<ReturnData> get100302Data(List<String> keyIDs,String orgID){
        List<ReturnData> list = new ArrayList<>();
        //分页取后十
        if ("9900".equals(orgID)){
        	PageHelper.startPage(1,10);
            list = nrptReportDataMapper.selectReportDataByDivIDAndOrgIDAsc(keyIDs.get(0));
        }else {
        	PageHelper.startPage(1,10);
            list = nrptReportDataMapper.selectAscReportBranchByParentIDAndKPIIDNotNull(keyIDs.get(0),orgID);
        }
        if(list.size()<10) {
        	list = nrptReportDataMapper.selectAscReportBranchByParentIDAndKPIID(keyIDs.get(0),orgID);
        	for(int i=list.size();i<=10;i++) {
        		ReturnData returnData = new ReturnData("","0");
        		list.add(returnData);
        	}
        }
        return list;
    }

    //树图
    public List<ReturnData> get100303Data(List<String> keyIDs,String orgID){
        return nrptDealDataService.compareData(keyIDs,orgID);
    }

    //零售贷款利息收入趋势
    public  List<LineDataEntity> get100304Data(List<String> keyIDs,String orgID){
        List<ReturnData> list = nrptReportDataMapper.selectReportPastDateDataByDivIDAndOrgIDAsc(keyIDs.get(0),orgID);
        return nrptDealDataService.dealTrendData(list);
    }

    //存款分析
    public List<ReturnData>  get100305Data(List<String> keyIDs,String orgID){
        List<ReturnData> list = new ArrayList<>();
        for (String keyID : keyIDs){
            try {
                list.add(nrptReportDataMapper.selectReportDataByDivIDAndOrgID(keyID, orgID).get(0));
            }catch (Exception e){
                log.error("当前指标"+keyID+"无数据");
                ReturnData returnData = new ReturnData();
                returnData.setName(VariableContext.Keys.get(keyID));
                returnData.setValue("0");
                list.add(returnData);
            }
        }
        return list;
    }

    //贷款分析
    public List<ReturnData> get100306Data(List<String> keyIDs,String orgID){
        List<ReturnData> list = new ArrayList<>();
        for (String keyID : keyIDs){
            try {
                list.add(nrptReportDataMapper.selectReportDataByDivIDAndOrgID(keyID, orgID).get(0));
            }catch (Exception e){
                log.error("当前指标"+keyID+"无数据");
                ReturnData returnData = new ReturnData();
                returnData.setName(VariableContext.Keys.get(keyID));
                returnData.setValue("0");
                list.add(returnData);
            }
        }
        return list;
    }

    //个人客户数
    public List<List<String>> get100307Data(List<String> keyIDs,String orgID){
        List<ReturnData> list = nrptReportDataMapper.selectCustomerNumberByCust_Type_Cd(keyIDs.get(0),orgID);

        List<String> dataList1 = new ArrayList<>();
        List<String> dataList2 = new ArrayList<>();
        List<String> dataList3 = new ArrayList<>();

        dataList1.add("0");
        dataList2.add(list.get(0).getValue());
        dataList3.add("-");
        for (int i=0;i<list.size()-1;i++){
            String value1 = list.get(i).getValue();
            String value2 = list.get(i+1).getValue();

            Integer integer1 = new Integer(value1);
            Integer integer2 = new Integer(value2);
            Integer res = integer2-integer1;

            if (res>0){
                String data1Res = "";
                String data2Res = "";
                String data3Res = "";
                if("-".equals(dataList2.get(i))){
                    data1Res = (new Integer(dataList1.get(i))+new Integer(dataList3.get(i)))+"";
                    data2Res = res.toString();
                    data3Res = "-";
                }else {
                    data1Res = (new Integer(dataList1.get(i))+new Integer(dataList2.get(i)))+"";
                    data2Res = res.toString();
                    data3Res = "-";
                }
                dataList1.add(data1Res);
                dataList2.add(data2Res);
                dataList3.add(data3Res);
            }else if (res<0){
                String data1Res = "";
                String data2Res = "";
                String data3Res = "";
                if("-".equals(dataList2.get(i))) {
                    data1Res = (new Integer(dataList1.get(i)) + res) + "";
                    data2Res = "-";
                    data3Res = (res.toString()).replace("-","");
                }else {
                    data1Res = (new Integer(dataList1.get(i))+new Integer(dataList2.get(i))+res)+"";
                    data2Res = "-";
                    data3Res = (res.toString()).replace("-","");
                }
                dataList1.add(data1Res);
                dataList2.add(data2Res);
                dataList3.add(data3Res);

            }else {

            }
        }
        Integer change1 = new Integer(dataList2.get(0));
        //获得初始化值
        ReportUnit reportUnit1 = nrptReportDataMapper.selectReportUnitByOrgID("01",orgID);
        //获得单位
        ReportUnit reportUnit2 = nrptReportDataMapper.selectReportUnitByOrgID("100307",orgID);
        Integer changeRes1 = change1-reportUnit1.getUnitValue();
        Integer changeRes2 = change1-changeRes1;
        dataList1.set(0,changeRes1.toString());
        dataList2.set(0,changeRes2.toString());

        for (int i=0; i<dataList1.size(); i++){
            String string1 = dataList1.get(i);
            if (!"-".equals(string1)){
                dataList1.set(i, NumUtil.dealNum(string1,reportUnit2.getUnitValue(),4));
            }
        }
        for (int i=0; i<dataList2.size(); i++){
            String string2 = dataList2.get(i);
            if (!"-".equals(string2)){
                dataList2.set(i,NumUtil.dealNum(string2,reportUnit2.getUnitValue(),4));
            }
        }
        for (int i=0; i<dataList3.size(); i++){
            String string3 = dataList3.get(i);
            if (!"-".equals(string3)){
                dataList3.set(i,NumUtil.dealNum(string3,reportUnit2.getUnitValue(),4));
            }
        }
        List<List<String>> lists = Arrays.asList(dataList1,dataList2,dataList3);
        return lists;
    }

    //获取大屏三新增的三个指标
    public List<String> getThreeindex(String org_id){
        OutData outData = new OutData();
        List<String> returnList = new ArrayList<>();
        outData.setORG_ID(org_id);
        outData.setKPI_ID("GM0303");
        List<String> data1 = nrptReportDataMapper.selectThirdThreeIndex(outData);
       returnList.add( (data1.size()==0||data1==null)?"0":data1.get(0));
        outData.setKPI_ID("GM0103");
        List<String> data2 = nrptReportDataMapper.selectThirdThreeIndex(outData);
        returnList.add((data2.size()==0||data2==null)?"0":data2.get(0));

        outData.setKPI_ID("PSL_FIN001");
        List<String> data3 = nrptReportDataMapper.selectFinBal(outData);
        returnList.add((data3.size()==0||data3==null)?"0":data3.get(0));
        return returnList;

    }
}
