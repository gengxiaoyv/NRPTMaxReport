package com.spdb.nrpt.service.custom;

import com.spdb.nrpt.entity.custom.Bar.Bar001;
import com.spdb.nrpt.entity.custom.Bar.Bar002;
import com.spdb.nrpt.entity.custom.Bar.Bar003;
import com.spdb.nrpt.entity.custom.Bar.YBar001;
import com.spdb.nrpt.entity.custom.Bar.YBar002;
import com.spdb.nrpt.entity.custom.Bar.YBar003;
import com.github.pagehelper.PageHelper;
import com.spdb.nrpt.config.VariableContext;
import com.spdb.nrpt.entity.custom.CustomEntity;
import com.spdb.nrpt.entity.custom.Funnel.Funnel001;
import com.spdb.nrpt.entity.custom.Line.Line001;
import com.spdb.nrpt.entity.custom.Line.Line003;
import com.spdb.nrpt.entity.custom.Pie.Pie001;
import com.spdb.nrpt.entity.custom.Pie.Pie003;
import com.spdb.nrpt.entity.custom.Pie.Pie004;
import com.spdb.nrpt.entity.custom.radar.Indicator;
import com.spdb.nrpt.entity.custom.radar.Radar001;
import com.spdb.nrpt.entity.report.ReturnData;
import com.spdb.nrpt.mapper.reportDataMapper.NRPTReportDataMapper;
import com.spdb.nrpt.util.TimeUtil;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class CustomDealDataService {

    @Autowired
    private NRPTReportDataMapper nrptReportDataMapper;
    @Autowired
    private CustomDataStrategyService customDataStrategyService;

    public CustomEntity getReportData(String divID, String EchartsType, String orgID,CustomEntity customEntity){
        switch(EchartsType){
            case "Bar001" :
                return dealBar001(divID,orgID,customEntity);
            case "Bar002" :
                return dealBar002(divID,orgID,customEntity);
            case "Bar003" :
                return dealBar003(divID,orgID,customEntity);
            case "Line001" :
                return dealLine001AndLine002(divID,orgID,customEntity);
            case "Line002" :
                return dealLine001AndLine002(divID,orgID,customEntity);
            case "Line003" :
                return dealPie001AndLine003(divID,orgID,customEntity);
            case "Waterfall001" :
                return dealWaterfall001(divID,orgID,customEntity);
            case "Radar001" :
                return dealRadar001(divID,orgID,customEntity);
            case "Pie001" :
                return dealPie001AndPie002(divID,orgID,customEntity);
            case "Pie002" :
                return dealPie001AndPie002(divID,orgID,customEntity);
            case "Pie003" :
                return dealPie003(divID,orgID,customEntity);
            case "Pie004" :
                return dealPie004(divID,orgID,customEntity);
            case "YBar001":
                return dealYBar001(divID,orgID,customEntity);
            case "YBar002":
                return dealYBar002(divID,orgID,customEntity);
            case "YBar003":
                return dealYBar003(divID,orgID,customEntity);
            case "Funnel001":
                return dealFunnel001(divID,orgID,customEntity);
            default :
                return null;
        }
    }

    //环形图拼接饼图
    public CustomEntity dealPie004(String divID, String orgID, CustomEntity customEntity) {
    	//获得该Div对应的KeyID
    	//外圈
        List<String> keyID1s = nrptReportDataMapper.selectKeyIDByDivID(divID);
        //内圈
        List<String> keyID2s = nrptReportDataMapper.selectKeyIDByDivID(divID);
        //环形图图例
        List<List<String>> legendLists = new ArrayList<>();
        
        //环形图数据处理
        List<List<Map<String,String>>> dataLists = new ArrayList<>();
        
        //外圈数据
        for (String keyID : keyID1s){
        	List<String> legendList = new ArrayList<>();
        	List<Map<String,String>> dataList = new ArrayList<>(); 
            //获得KeyID对应的数据
            ReturnData data= nrptReportDataMapper.selectOneReportDataByDivIDAndOrgID(keyID,orgID);

            String name = data.getName();
            legendList.add(name);
            legendLists.add(legendList);
            
            Map<String,String> dataMap = new HashMap<>();
            dataMap.put("name",name);
            dataMap.put("value",data.getValue());
            dataList.add(dataMap);
            dataLists.add(dataList);
        }
        
        //内圈数据
        for (String keyID : keyID2s){
        	List<String> legendList = new ArrayList<>();
        	List<Map<String,String>> dataList = new ArrayList<>(); 
            //获得KeyID对应的数据
            ReturnData data= nrptReportDataMapper.selectOneReportDataByDivIDAndOrgID(keyID,orgID);

            String name = data.getName();
            legendList.add(name);
            legendLists.add(legendList);
            
            Map<String,String> dataMap = new HashMap<>();
            dataMap.put("name",name);
            dataMap.put("value",data.getValue());
            dataList.add(dataMap);
            dataLists.add(dataList);
        }
        
        Pie004 pie004 = (Pie004)customEntity.getEchartsData();
        pie004.setLegend(legendLists);
        
        customEntity.setReportData(dataLists);
    	return customEntity;
	}

	//堆叠条形图
    public CustomEntity dealYBar003(String divID, String orgID, CustomEntity customEntity) {
    	//获得该Div对应的KeyID
        List<String> keyIDs = nrptReportDataMapper.selectKeyIDByDivID(divID);
        //条形图图例
        List<String> legendList = new ArrayList<>();
		//条形图Y轴
        List<String> yDataList = new ArrayList<>();
        //条形图数据
        List<List<String>> YBarDataList = new ArrayList<>();
        
        int i = 0;
        for(String keyID : keyIDs) {
        	//图例名称（指标名称）
        	String name = VariableContext.Keys.get(keyID);
        	legendList.add(name);
        	//查询出数据
        	PageHelper.startPage(1,5);
        	List<ReturnData> dataList = nrptReportDataMapper.selectReportDataByDivIDAndOrgIDAsc(keyIDs.get(0));
        	List<String> datas = new ArrayList<>();
        	for(ReturnData data: dataList){
        		datas.add(data.getValue());
        		if(i==0) {
        			yDataList.add(data.getName());
        		}
        	}
        	YBarDataList.add(datas);
        	i++;
        }
        
        YBar003 yBar003 = (YBar003)customEntity.getEchartsData();
        yBar003.setLegend(legendList);
        yBar003.setYAxis(yDataList);
        
        customEntity.setReportData(YBarDataList);
		
        return customEntity;
	}

    //多指标条形图
    public CustomEntity dealYBar002(String divID, String orgID, CustomEntity customEntity) {
    	//获得该Div对应的KeyID
        List<String> keyIDs = nrptReportDataMapper.selectKeyIDByDivID(divID);
        //条形图图例
        List<String> legendList = new ArrayList<>();
		//条形图Y轴
        List<String> yDataList = new ArrayList<>();
        //条形图数据
        List<List<String>> YBarDataList = new ArrayList<>();
        
        int i = 0;
        for(String keyID : keyIDs) {
        	//图例名称（指标名称）
        	String name = VariableContext.Keys.get(keyID);
        	legendList.add(name);
        	//查询出数据
        	PageHelper.startPage(1,5);
        	List<ReturnData> dataList = nrptReportDataMapper.selectReportDataByDivIDAndOrgIDAsc(keyIDs.get(0));
        	List<String> datas = new ArrayList<>();
        	for(ReturnData data: dataList){
        		datas.add(data.getValue());
        		if(i==0) {
        			yDataList.add(data.getName());
        		}
        	}
        	YBarDataList.add(datas);
        	i++;
        }
        
        YBar002 yBar002 = (YBar002)customEntity.getEchartsData();
        yBar002.setLegend(legendList);
        yBar002.setYAxis(yDataList);
        
        customEntity.setReportData(YBarDataList);
        
        
        return customEntity;
	}

	//环形图
    public CustomEntity dealPie003(String divID, String orgID, CustomEntity customEntity) {
    	//获得该Div对应的KeyID
        List<String> keyIDs = nrptReportDataMapper.selectKeyIDByDivID(divID);
        //环形图图例
        List<String> legendList = new ArrayList<>();
        
        //环形图数据处理
        List<Map<String,String>> dataList = new ArrayList<>();

        for (String keyID : keyIDs){
            //获得KeyID对应的数据
            ReturnData data= nrptReportDataMapper.selectOneReportDataByDivIDAndOrgID(keyID,orgID);

            String name = data.getName();
            legendList.add(name);

            Map<String,String> dataMap = new HashMap<>();
            dataMap.put("name",name);
            dataMap.put("value",data.getValue());
            dataList.add(dataMap);
        }
        
        Pie003 pie003 = (Pie003)customEntity.getEchartsData();
        pie003.setLegend(legendList);
        
        customEntity.setReportData(dataList);
        
		return customEntity;
	}

	//多指标折线图（硬线，圆点）
    public CustomEntity dealPie001AndLine003(String divID, String orgID, CustomEntity customEntity) {
    	//获得该Div对应的KeyID
        List<String> keyIDs = nrptReportDataMapper.selectKeyIDByDivID(divID);
        //折线图图例数据
        List<String> legendList = new ArrayList<>();
        //折线图X轴数据
        List<String> xDataList = new ArrayList<>();
        List<String> timeList = TimeUtil.getAllPast30Time(5);
        for (int j =0; j<10; j++){
        	xDataList.add(timeList.get(j));
        }
        //堆叠柱状图
        List<List<ReturnData>> dataLists = new ArrayList<>();
        
        for(String keyID : keyIDs) {
        	List<ReturnData> datas = nrptReportDataMapper.selectReportPastDataByDivIDAndOrgID(keyID,orgID);
        	String name = datas.get(0).getName();
        	legendList.add(name);
        	dataLists.add(datas);
        }
        
        Line003 line003 =  (Line003)customEntity.getEchartsData();
        line003.setLegend(legendList);
        line003.setXAxisData(xDataList);
        
        customEntity.setEchartsData(line003);
        customEntity.setReportData(dataLists);
        
		return customEntity;
	}


	//多指标柱状图
    public CustomEntity dealBar003(String divID, String orgID, CustomEntity customEntity) {
    	//获得该Div对应的KeyID
        List<String> keyIDs = nrptReportDataMapper.selectKeyIDByDivID(divID);
        //柱状图legend
        List<String> legendList = new ArrayList<>();
        //堆叠柱状图
        List<List<ReturnData>> dataLists = new ArrayList<>();
          
        for (String keyID : keyIDs){
        	 //堆叠柱状图数据
            List<ReturnData> dataList = new ArrayList<>();
            
        	//今日
        	ReturnData data= nrptReportDataMapper.selectOneReportDataByDivIDAndOrgID(keyID,orgID);
    		//上日，上月
        	List<ReturnData> datas = nrptReportDataMapper.selectReportPastDataByDivIDAndOrgID(keyID,orgID);
        	
        	dataList.add(data);
        	dataList.addAll(datas);
        	
    		String name = datas.get(0).getName();
    		legendList.add(name); 
    		dataLists.add(dataList);
        }
        
        Bar003 bar003 = (Bar003)customEntity.getEchartsData();
        bar003.setLegend(legendList);
        
        customEntity.setEchartsData(bar003);
        customEntity.setReportData(dataLists);
        
		return customEntity;
	}

    //堆叠柱状图,今日，上日，上月
    public CustomEntity dealBar002(String divID, String orgID, CustomEntity customEntity) {
    	//获得该Div对应的KeyID
        List<String> keyIDs = nrptReportDataMapper.selectKeyIDByDivID(divID);
        //柱状图legend
        List<String> legendList = new ArrayList<>();
        //堆叠柱状图
        List<List<ReturnData>> dataLists = new ArrayList<>();
          
        for (String keyID : keyIDs){
        	 //堆叠柱状图数据
            List<ReturnData> dataList = new ArrayList<>();
            
        	//今日
        	ReturnData data= nrptReportDataMapper.selectOneReportDataByDivIDAndOrgID(keyID,orgID);
    		//上日，上月
        	List<ReturnData> datas = nrptReportDataMapper.selectReportPastDataByDivIDAndOrgID(keyID,orgID);
        	
        	dataList.add(data);
        	dataList.addAll(datas);
        	
    		String name = datas.get(0).getName();
    		legendList.add(name); 
    		dataLists.add(dataList);
        }
        
        Bar002 bar002 = (Bar002)customEntity.getEchartsData();
        bar002.setLegend(legendList);
        bar002.setStack("1");
        
        customEntity.setEchartsData(bar002);
        customEntity.setReportData(dataLists);
        
		return customEntity;
	}

    
    //漏斗图Funnel001的处理
    public CustomEntity dealFunnel001(String divID, String orgID, CustomEntity customEntity) {
        //获得该Div对应的KeyID
        List<String> keyIDs = nrptReportDataMapper.selectKeyIDByDivID(divID);

        //饼图legend
        List<String> legendList = new ArrayList<>();
        //饼图数据
        List<Map<String,String>> dataList = new ArrayList<>();

        for (String keyID : keyIDs){
            //获得KeyID对应的数据
            ReturnData data= nrptReportDataMapper.selectOneReportDataByDivIDAndOrgID(keyID,orgID);

            legendList.add(data.getName());

            Map<String,String> dataMap = new HashMap<>();
            dataMap.put("name",data.getName());
            dataMap.put("value",data.getValue());
            dataList.add(dataMap);
        }

        Funnel001 funnel001 = (Funnel001)customEntity.getEchartsData();
        funnel001.setLegend(legendList);

        customEntity.setReportData(dataList);
        return customEntity;
    }

    //条形图YBar001处理
    public CustomEntity dealYBar001(String divID, String orgID, CustomEntity customEntity) {
        //获得该Div对应的KeyID
        List<String> keyIDs = nrptReportDataMapper.selectKeyIDByDivID(divID);

        //Bar001返回的数据格式
        List<String> dataList = new ArrayList<>();
        //Bar001的轴返回的格式
        List<String> yDataList = new ArrayList<>();

        for (String keyID : keyIDs){
            //获得KeyID对应的数据
            ReturnData data= nrptReportDataMapper.selectOneReportDataByDivIDAndOrgID(keyID,orgID);
            //放入数据
            dataList.add(data.getValue());
            yDataList.add(data.getName());
        }

        //对Echarts组件参数做最后处理
        if(customEntity.getDivID()!=null){
            YBar001 yBar001 = (YBar001) customEntity.getEchartsData();
            yBar001.setYAxis(yDataList);
            customEntity.setEchartsData(yBar001);
        }

        customEntity.setReportData(dataList);

        return customEntity;
    }

    //饼图001数据处理
    private CustomEntity dealPie001AndPie002(String divID, String orgID, CustomEntity customEntity) {
        //获得该Div对应的KeyID
        List<String> keyIDs = nrptReportDataMapper.selectKeyIDByDivID(divID);

        //饼图legend
        List<String> legendList = new ArrayList<>();
        //饼图数据
        List<Map<String,String>> dataList = new ArrayList<>();

        //获得该报表的处理策略
        String type = "大屏实时数据";
        
        for (String keyID : keyIDs){
            //获得KeyID对应的数据
            ReturnData data= (ReturnData)customDataStrategyService.getData(type, keyID, orgID, null);

            String name = data.getName();
            legendList.add(name);

            Map<String,String> dataMap = new HashMap<>();
            dataMap.put("name",name);
            dataMap.put("value",data.getValue());
            dataList.add(dataMap);
        }

        Pie001 pie001 = (Pie001)customEntity.getEchartsData();
        pie001.setLegend(legendList);

        customEntity.setReportData(dataList);
        return customEntity;
    }

    //雷达图Radar001数据处理
    public CustomEntity dealRadar001(String divID, String orgID, CustomEntity customEntity) {
        //获得该Div对应的KeyID
        List<String> keyIDs = nrptReportDataMapper.selectKeyIDByDivID(divID);

        //雷达图indicator
        List<Indicator> indicatorList = new ArrayList<>();
        //雷达图数据
        List<String> dataList = new ArrayList<>();

        //雷达图Max
        String max = "1000";
        /*BigDecimal bigDecimal = new BigDecimal(value);
        BigDecimal multiplier = new BigDecimal("1.05");
        String max = bigDecimal.multiply(multiplier).toString();*/

        //获得该报表的处理策略
        String type = "大屏实时数据";
        
        for (String keyID : keyIDs){
            //获得KeyID对应的数据
            ReturnData data= (ReturnData)customDataStrategyService.getData(type,keyID,orgID,null);

            Indicator indicator = new Indicator();
            indicator.setName(data.getName());
            indicator.setMax(max);
            indicatorList.add(indicator);

            String value = data.getValue();
            dataList.add(value);
        }

        Radar001 radar001 = (Radar001)customEntity.getEchartsData();
        radar001.setIndicator(indicatorList);
        customEntity.setEchartsData(radar001);

        customEntity.setReportData(dataList);

        //雷达图数据
        return customEntity;
    }

    //类型为Line001和Line2的调用
    public CustomEntity dealLine001AndLine002(String divID, String orgID, CustomEntity customEntity) {
        //获得该Div对应的KeyID
        List<String> keyIDs = nrptReportDataMapper.selectKeyIDByDivID(divID);

        //Line001或Line002返回的数据格式
        List<String> dataList = new ArrayList<>();
        //Line001或Line002返回的X轴格式
        List<String> xDataList = new ArrayList<>();

        for(String keyID : keyIDs){
            //获得KeyID对应的数据
            ReturnData data= nrptReportDataMapper.selectOneReportDataByDivIDAndOrgID(keyID,orgID);
            //放入数据
            dataList.add(data.getValue());
            xDataList.add(data.getName());
        }

        //对Echarts组件参数做最后处理
        if(customEntity.getDivID()!=null){
            Line001 line = (Line001) customEntity.getEchartsData();
            line.setXAxisData(xDataList);
            customEntity.setEchartsData(line);
        }

        customEntity.setReportData(dataList);

        return customEntity;
    }

    //类型为Bar001的数据逻辑处理,单指标往期数据
    public CustomEntity dealBar001 (String divID,String orgID,CustomEntity customEntity){
        //获得该Div对应的KeyID
        List<String> keyIDs = nrptReportDataMapper.selectKeyIDByDivID(divID);

        //Bar001返回的数据格式
        List<String> dataList = new ArrayList<>();
        //Bar001的X轴返回的格式
        List<String> xDataList = new ArrayList<>();

        
        //获得当前div数据类型
        String type = "大屏实时+往期数据";
        
        List<ReturnData> list = (List<ReturnData>) customDataStrategyService.getData(type, keyIDs.get(0), orgID, null);
        for(ReturnData data : list) {
        	dataList.add(data.getValue());
            xDataList.add(data.getName());
        }

        //对Echarts组件参数做最后处理
        if(customEntity.getDivID()!=null){
            Bar001 bar001 = (Bar001) customEntity.getEchartsData();
            bar001.setXAxis(xDataList);
            customEntity.setEchartsData(bar001);
        }

        customEntity.setReportData(dataList);

        return customEntity;
    }

    //瀑布图001处理逻辑
    public CustomEntity dealWaterfall001(String divID,String orgID,CustomEntity customEntity){

        //获得该Div对应的KeyID
        List<String> keyIDs = nrptReportDataMapper.selectKeyIDByDivID(divID);

        //获得数据,瀑布图只能单指标，过往值
        List<ReturnData> list = nrptReportDataMapper.selectReportDataByDivIDAndOrgID(keyIDs.get(0),orgID);

        //瀑布图X轴
        List<String> xList = new ArrayList<>();
        for (ReturnData data : list){
            xList.add(data.getName());
        }

        //瀑布图Data
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
                    data1Res = (new Integer(dataList1.get(i)))+"";
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
        List<List<String>> lists = Arrays.asList(dataList1,dataList2,dataList3);

        customEntity.setEchartsData(xList);
        customEntity.setReportData(lists);

        return customEntity;
    }

}
