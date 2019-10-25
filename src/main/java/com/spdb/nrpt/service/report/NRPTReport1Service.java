package com.spdb.nrpt.service.report;

import com.github.pagehelper.PageHelper;
import com.spdb.nrpt.config.VariableContext;
import com.spdb.nrpt.entity.div.Div100107Entity;
import com.spdb.nrpt.entity.div.ReturnDiv1007Entity;
import com.spdb.nrpt.entity.echarts.map.MapDataEntity;
import com.spdb.nrpt.entity.report.ReturnData;
import com.spdb.nrpt.mapper.reportDataMapper.NRPTReportDataMapper;
import com.spdb.nrpt.util.NumUtil;
import com.spdb.nrpt.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class NRPTReport1Service {

    private List<ReturnData> depositList = new ArrayList();
    private List<ReturnData> loanList = new ArrayList<>();

    @Autowired
    private NRPTReportDataMapper nrptReportDataMapper;

    //通过keyID获得divID对应的数据
    public List<List<ReturnData>> getDivDataByKeyID(String divID, List<String> keyIDs,String orgID){
        switch(divID){
            case "100102" :
                return get100102Data(keyIDs,orgID);
            case "100103" :
                return get100103Data(orgID);
            case "100104" :
                return null;
            case "100105" :
                return get100105Data(keyIDs,orgID);
            case "100106" :
                return get100106Data(keyIDs,orgID);
            case "100107" :
                return get100107Data(keyIDs,orgID);
            case "100108" :
                return null;
            case "100109":
                return null;
            default :
                List<List<ReturnData>> lists = new ArrayList<>();
                List<ReturnData> list = new ArrayList<>();
                for (int i =0; i<keyIDs.size(); i++){
                    ReturnData returnData = nrptReportDataMapper.selectReportDataByDivIDAndOrgID(keyIDs.get(i),orgID).get(0);
                    //查询出百万，再做处理变成亿
                    returnData.setValue(NumUtil.dealNum(returnData.getValue(),100,2));
                    list.add(returnData);
                }
                lists.add(list);
                return lists;
        }
    }

    //全行存款
    public List<List<ReturnData>> get100102Data(List<String> keyIDs,String orgID){
        List<List<ReturnData>> lists = new ArrayList<>();
        //个人存款
        String keyID1 = keyIDs.get(0);
        List<ReturnData> list1 = new ArrayList<>();
        ReturnData returnData1 = nrptReportDataMapper.selectReportDataByDivIDAndOrgID(keyID1,orgID).get(0);
        list1.add(returnData1);
        PageHelper.startPage(1,2);
        for (ReturnData data :nrptReportDataMapper.selectReportPastDataByDivIDAndOrgID(keyID1,orgID)){
            list1.add(data);
        }

        //对公存款
        String keyID2 = keyIDs.get(1);
        List<ReturnData> list2 = new ArrayList<>();
        ReturnData returnData2 = nrptReportDataMapper.selectReportDataByDivIDAndOrgID(keyID2,orgID).get(0);
        list2.add(returnData2);
        PageHelper.startPage(1,2);
        for (ReturnData data :nrptReportDataMapper.selectReportPastDataByDivIDAndOrgID(keyID2,orgID)){
            list2.add(data);
        }
        lists.add(list1);
        lists.add(list2);
        return lists;
    }

    public List<List<ReturnData>> get100103Data(String orgID){
        //住房按揭贷款,经营性贷款,商用房贷款,消费贷款,其它贷款
        List<String> keyList1 = Arrays.asList("GM030201","GM030202","GM030203","GM030204","GM030205");
        List<ReturnData> list1 = new ArrayList<>();
        for (String key1: keyList1){
            ReturnData returnData1 = new ReturnData();
            try {
                returnData1 = nrptReportDataMapper.selectReportDataByDivIDAndOrgID(key1,orgID).get(0);
                String name = returnData1.getName();
                returnData1.setName(name.substring(0,name.length()-2));
            }catch (Exception e){
                log.error("该指标"+key1+"不存在");
                returnData1.setValue("0");
                String name = VariableContext.Keys.get(key1);
                returnData1.setName(name.substring(0,name.length()-2));
            }
            list1.add(returnData1);
        }
        //逾期贷款,对公中长期贷款,对公短期贷款,票据融资
        List<String> keyList2 = Arrays.asList("GM03010103","GM03010101","GM03010102","GM030102");
        List<ReturnData> list2 = new ArrayList<>();
        for (String key2: keyList2){
            ReturnData returnData2 = new ReturnData();
            try {
                returnData2 = nrptReportDataMapper.selectReportDataByDivIDAndOrgID(key2,orgID).get(0);
                String name = returnData2.getName();
                if ("票据融资".equals(name)){
                    list2.add(returnData2);
                    continue;
                }
                returnData2.setName(name.substring(0,name.length()-2));
            }catch (Exception e){
                log.error("该指标"+key2+"不存在");
                String name = VariableContext.Keys.get(key2);
                if ("票据融资".equals(name)){
                    list2.add(returnData2);
                    continue;
                }
                returnData2.setName(name.substring(0,name.length()-2));
            }
            list2.add(returnData2);
        }
        List<List<ReturnData> > lists = Arrays.asList(list1,list2);
        return lists;
    }

    //地图
    public List<Object> get100104Data(List<String> keyIDs,String orgID){
        MapDataEntity mapDataEntity01 = new MapDataEntity();
        mapDataEntity01.setName("台湾");
        mapDataEntity01.setValue("0");

        MapDataEntity mapDataEntity02 = new MapDataEntity();
        mapDataEntity02.setName("香港");
        mapDataEntity02.setValue("0");

        MapDataEntity mapDataEntity03 = new MapDataEntity();
        mapDataEntity03.setName("澳门");
        mapDataEntity03.setValue("0");

        MapDataEntity mapDataEntity04 = new MapDataEntity();
        mapDataEntity04.setName("南海诸岛");
        mapDataEntity04.setValue("0");

        MapDataEntity mapDataEntity05 = new MapDataEntity();
        mapDataEntity05.setName("台湾");
        mapDataEntity05.setValue("0");

        MapDataEntity mapDataEntity06 = new MapDataEntity();
        mapDataEntity06.setName("香港");
        mapDataEntity06.setValue("0");

        MapDataEntity mapDataEntity07 = new MapDataEntity();
        mapDataEntity07.setName("澳门");
        mapDataEntity07.setValue("0");

        MapDataEntity mapDataEntity08 = new MapDataEntity();
        mapDataEntity08.setName("南海诸岛");
        mapDataEntity08.setValue("0");

        List<Integer> i11 = new ArrayList<>();
        List<Integer> i21 = new ArrayList<>();
        List<Integer> i31 = new ArrayList<>();
        List<Integer> i41 = new ArrayList<>();
        List<MapDataEntity> list1 = new ArrayList<>();
        List<ReturnData> dataList1 = depositList = nrptReportDataMapper.selectReportDataByDivIDAndOrgIDDesc(keyIDs.get(0));
        for (int i=0;i<dataList1.size();i++){
            MapDataEntity mapEntity1 = new MapDataEntity();
            String bankName = dataList1.get(i).getName();
            //通过行名称获得省份
            mapEntity1.setBank(bankName);
            mapEntity1.setName(VariableContext.provinceName.get(bankName));
            mapEntity1.setValue(dataList1.get(i).getValue());
            mapEntity1.setSelected(false);
            if ("上海".equals(bankName)){
                mapEntity1.setSelected(true);
                list1.add(mapEntity1);
                continue;
            }
            list1.add(mapEntity1);
            if (i11.size()<9){
                i11.add(i);
            }else if (i21.size()<9){
                i21.add(i);
            }else if (i31.size()<9){
                i31.add(i);
            }else if (i41.size()<9){
                i41.add(i);
            }
        }

        List<Integer> i12 = new ArrayList<>();
        List<Integer> i22 = new ArrayList<>();
        List<Integer> i32 = new ArrayList<>();
        List<Integer> i42 = new ArrayList<>();
        List<MapDataEntity> list2 = new ArrayList<>();
        List<ReturnData> dataList2 = loanList = nrptReportDataMapper.selectReportDataByDivIDAndOrgIDDesc(keyIDs.get(1));
        int j = 0;
        for (j=0;j<dataList2.size();j++){
            MapDataEntity mapEntity2 = new MapDataEntity();
            String bankName = dataList2.get(j).getName();
            //通过行名称获得省份
            mapEntity2.setBank(bankName);
            mapEntity2.setName(VariableContext.provinceName.get(bankName));
            mapEntity2.setValue(dataList2.get(j).getValue());
            mapEntity2.setSelected(false);
            if ("上海".equals(bankName)){
                mapEntity2.setSelected(true);
                list2.add(mapEntity2);
                continue;
            }
            list2.add(mapEntity2);
            if (i12.size()<9){
                i12.add(j);
            }else if (i22.size()<9){
                i22.add(j);
            }else if (i32.size()<9){
                i32.add(j);
            }else if (i42.size()<9){
                i42.add(j);
            }
        }

        List<Integer> returnIndex1 = new ArrayList<>();
        List<Integer> returnIndex2 = new ArrayList<>();
        //分四个数组区分所有
        //获得当前分钟数
        int min = TimeUtil.getMinute();
        int falg = min%4;
        int x = 0;
        if (falg==0){
            returnIndex1 = i11;
            returnIndex2 = i21;
        }else if (falg==1){
            returnIndex1 = i21;
            returnIndex2 = i22;
        }else if (falg==2){
            returnIndex1 = i31;
            returnIndex2 = i32;
        }else if (falg==3){
            returnIndex1 = i41;
            returnIndex2 = i42;
        }


        list1.add(mapDataEntity01);
        list1.add(mapDataEntity02);
        list1.add(mapDataEntity03);
        list1.add(mapDataEntity04);
        list2.add(mapDataEntity05);
        list2.add(mapDataEntity06);
        list2.add(mapDataEntity07);
        list2.add(mapDataEntity08);
        List<Object> lists = Arrays.asList(list1,list2,returnIndex1,returnIndex2);
        return lists;
    }

    //存款前五
    public List<List<ReturnData>> get100105Data(List<String> keyIDs,String orgID){
        List<List<ReturnData>> lists = new ArrayList<>();
        if ("9900".equals(orgID)){
            List<ReturnData> list = depositList.subList(0,5);
            lists.add(list);
        }else {
            PageHelper.startPage(1,5);
            List<ReturnData> list = nrptReportDataMapper.selectDescReportBranchByParentIDAndKPIID(keyIDs.get(0),orgID);
            lists.add(list);
        }
        return lists;
    }

    //贷款前五
    public List<List<ReturnData>> get100106Data(List<String> keyIDs,String orgID){
        List<List<ReturnData>> lists = new ArrayList<>();
        if ("9900".equals(orgID)){
            List<ReturnData> list = loanList.subList(0,5);
            lists.add(list);
        }else {PageHelper.startPage(1,5);
            List<ReturnData> list = nrptReportDataMapper.selectDescReportBranchByParentIDAndKPIID(keyIDs.get(0),orgID);
            lists.add(list);}
        return lists;
    }

    //营收趋势
    public List<List<ReturnData>> get100107Data(List<String> keyIDs,String orgID){
        List<List<ReturnData>> lists = new ArrayList<>();
        List<ReturnData> list = nrptReportDataMapper.selectReportPastDateDataByDivIDAndOrgIDAsc("YS01",orgID);
        lists.add(list);
        return lists;
    }

    //贷款，存款
    public  ReturnDiv1007Entity get100108Data(List<String> keyIDs,String orgID){
        ReturnDiv1007Entity returnDiv1007Entity = new ReturnDiv1007Entity();
        List<Div100107Entity> list = new ArrayList<>();
        if ("9900".equals(orgID)){
            Map<String,String> OrgID = VariableContext.NameOrgs;
            for (int i=0; i<depositList.size();i++){
                Div100107Entity div100107Entity = new Div100107Entity();
                //沿用地图数据
                div100107Entity.setName(depositList.get(i).getName());
                div100107Entity.setStore(depositList.get(i).getValue());
                String org = OrgID.get(depositList.get(i).getName());
                //通过组织名称，补全贷款数据
                ReturnData data = nrptReportDataMapper.selectReportDataByDivIDAndOrgID("GM03",org).get(0);
                div100107Entity.setLoan(data.getValue());
                list.add(div100107Entity);
            }
            returnDiv1007Entity.setLoanMax(loanList.get(0).getValue());
            returnDiv1007Entity.setStoreMax(depositList.get(0).getValue());
        }else {
        	//存款
        	PageHelper.startPage(1,20);
            List<ReturnData> GM01List = nrptReportDataMapper.selectDescReportBranchByParentIDAndKPIID("GM01",orgID);
            for (int i=0;i<GM01List.size();i++){
                Div100107Entity div100107Entity = new Div100107Entity();
                //名称
                div100107Entity.setName(GM01List.get(i).getName());
                //存款
                div100107Entity.setStore(GM01List.get(i).getValue());
                
                //获得网点组织ID
                //ReturnData GM03 = nrptReportDataMapper.selectDescReportBranchByOrgIDAndKPIID("GM03",GM01List.get(i).getName());
                ReturnData GM03 = nrptReportDataMapper.selectDescReportBranchByOrgIDAndKPIID("GM03",GM01List.get(i).getOrgid());
                //通过该存款名称，补全贷款数据
                div100107Entity.setLoan(GM03.getValue());
                list.add(div100107Entity);
                
                

                //放入最大值
                returnDiv1007Entity.setLoanMax(GM01List.get(0).getValue());
                returnDiv1007Entity.setStoreMax(GM01List.get(0).getValue());
            }
        }
        if(list.size()<20) {
        	for(int i =list.size(); i<=20; i++ ) {
        		Div100107Entity data = new Div100107Entity("","","");
        		list.add(data);
        	}
        }
        returnDiv1007Entity.setDataList(list);
        return returnDiv1007Entity;

    }

    //同业负债结构
    public List<String> get100109Data(List<String> keyIDs,String orgID){
        List<String> list = new ArrayList<>();
        for (int i =0; i<keyIDs.size(); i++){
            ReturnData returnData = new ReturnData();
            try {
                 returnData =  nrptReportDataMapper.selectReportDataByDivIDAndOrgID(keyIDs.get(i),orgID).get(0);
            }catch (Exception e){
                log.error("该指标"+keyIDs.get(i)+"数据不存在");
                list.add("0");
                continue;
            }
            list.add(returnData.getValue());
        }
        return list;
    }
}
