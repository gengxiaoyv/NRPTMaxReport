package com.spdb.nrpt.service.webservice;

import com.alibaba.fastjson.JSONObject;
import com.spdb.nrpt.config.VariableContext;
import com.spdb.nrpt.entity.procedure.OutData;
import com.spdb.nrpt.entity.procedure.ProcedureInParams;
import com.spdb.nrpt.entity.procedure.ProcedureOutParams;
import com.spdb.nrpt.entity.procedure.SoapInParams;
import com.spdb.nrpt.entity.report.ReturnData;
import com.spdb.nrpt.entity.report.branch.ReportBranchDataEntity;
import com.spdb.nrpt.mapper.reportDataMapper.NRPTReportDataMapper;
import com.spdb.nrpt.util.NumUtil;
import com.spdb.nrpt.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


@Service(value = "NRPTWebService")
@Lazy(value = false)
@Slf4j
public class NRPTWebService {

    @Autowired
    private  NRPTReportDataMapper nrptReportDataMapper;

    public String getAllData(){
        EsbSoap esbSoap = new EsbSoap();
        //通过webService交互获得最新的数据
        String YYYYMMDD = TimeUtil.getTime("YYYYMMDD");
        String mmssSSS = TimeUtil.getTime("mmssSSS");
        ProcedureInParams procedureInParams = new ProcedureInParams();
        procedureInParams.setI_CURRENCY_CD("199");
        procedureInParams.setI_DATE("");
        //放入总行和所有支行的id
        procedureInParams.setI_ORGIDS(VariableContext.Level1OrgIDs);
        procedureInParams.setI_NUM("100000");
        procedureInParams.setI_ORDER("asc");
        SoapInParams soapInParams = new SoapInParams();
        soapInParams.setProcedureName("proc_call_ma_screen");
        soapInParams.setSchemaName("NCMA");
        int flag = -1;
        //报文拆分
        for (int i=0;i<3;i++){
            flag = i;
            if (i==0){
                procedureInParams.setI_KPIIDS("YS01,GM01,GM03,GM030201,GM030202,GM030203,GM030204,GM030205,GM0102,GM010201,GM010202,GM010203");
            }else if (i==1){
                procedureInParams.setI_KPIIDS("GM010204,GM010205,GM0101,GM010101,GM0301,GM030101,GM01010101,GM01010102,GM0101010201,GM030102");
            }else if (i==2){
                procedureInParams.setI_KPIIDS("GM03010101,GM03010102,GM03010103,GM020101,GM020102,GM0202,GM0203,GM0204,YS01010101,YS01010102");
            }

            soapInParams.setValues(procedureInParams);
            //发送报文
            String SoapXml =  esbSoap.getsoapXml(YYYYMMDD, mmssSSS, JSONObject.toJSONString(soapInParams));
            //log.info("发送的报文为："+SoapXml);
            //回传结果
            String res = esbSoap.getData(SoapXml);

            //打印效果
            log.info(" 报文回传回来的结果数据字符串：{}  ",res);
            
            if ("0".equals(res)||res.contains("调用失败")){
                log.error("请求获取最新数据出错，详细信息为："+res);
                return "请求获取最新数据出错";
            }
            ProcedureOutParams procedureOutParams = JSONObject.parseObject(res, ProcedureOutParams.class);
            List<OutData> dataList= procedureOutParams.getO_REF();
            if (dataList.size()<1){
                log.error("当前调用存储过程成功，但无数据");
                return "当前调用存储过程成功，但无数据";
            }
            
            //单位处理
            for (OutData outData : dataList){
                //百万，两位小数
                String KeyID = outData.getKPI_ID();
                if ("YS01".equals(KeyID)||"GM01".equals(KeyID)||"GM03".equals(KeyID)||"YS01010101".equals(KeyID)||"YS01010102".equals(KeyID)||"GM030204".equals(KeyID)||"GM030202".equals(KeyID)||"GM030201".equals(KeyID)||"GM010202".equals(KeyID)||"GM010205".equals(KeyID)||"GM0302".equals(KeyID)||"GM0304".equals(KeyID)||"GM0104".equals(KeyID)||"GM0303".equals(KeyID)||"GM0103".equals(KeyID)){
                    outData.setKPI_VALUE(NumUtil.dealNum(outData.getKPI_VALUE(),1000000,2));
                    outData.setKPI_VALUE_ALLOT(NumUtil.dealNum(outData.getKPI_VALUE(),1000000,2));
                    if ("YS01010101".equals(KeyID)||"YS01010102".equals(KeyID)){
                            outData.setSTATIS_DT(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date())+":00");
                            nrptReportDataMapper.insertReportPastData(outData);
                    }
                    continue;
                }
                outData.setKPI_VALUE(NumUtil.dealNum(outData.getKPI_VALUE(),100000000,2));
                outData.setKPI_VALUE_ALLOT(NumUtil.dealNum(outData.getKPI_VALUE(),100000000,2));

            }
            //获得第一份报文后将表全部清空
            if (0==flag){
                //删除表数据
                nrptReportDataMapper.truncateReportDataTable();
            }
            //插入数据库
            nrptReportDataMapper.insertBatchReportAllData(dataList);

        }

        //计算主动负债+单位保本理财的数据
        Map<String,String> Orgs  = VariableContext.Orgs;
        Iterator<Map.Entry<String, String>> it = Orgs.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            String orgID = entry.getKey();
            //插入主动负债+单位保本理财	公式为（一般对公存款-结构性存款-对公基础存款)
            ReturnData returnData = nrptReportDataMapper.selectReportDataByDivIDAndOrgID("GM0101",orgID).get(0);
            ReturnData returnData1 = nrptReportDataMapper.selectReportDataByDivIDAndOrgID("GM01010102",orgID).get(0);
            ReturnData returnData2 = nrptReportDataMapper.selectReportDataByDivIDAndOrgID("GM01010101",orgID).get(0);
            String num1 = returnData.getValue();
            String num2 = returnData1.getValue();
            String num3 = returnData2.getValue();
            BigDecimal bigDecimal1 = new BigDecimal(num1);
            BigDecimal bigDecimal2 = new BigDecimal(num2);
            BigDecimal bigDecimal3 = new BigDecimal(num3);
            BigDecimal min = bigDecimal1.subtract(bigDecimal2);
            String resNumString = min.subtract(bigDecimal3).toString();
            OutData nrpt01 = new OutData();
            nrpt01.setKPI_VALUE(resNumString);
            nrpt01.setSTATIS_DT(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            nrpt01.setCURRENCY_CD("199");
            nrpt01.setORG_ID(orgID);
            nrpt01.setKPI_NAME("主动负债+单位保本理财");
            nrpt01.setORG_NAME("境内机构合计");
            nrpt01.setKPI_ID("NRPT01");
            nrpt01.setKPI_VALUE_ALLOT("0");//todo
            List<OutData> outDataList = Arrays.asList(nrpt01);
            nrptReportDataMapper.insertBatchReportAllData(outDataList);
        }

        //去重
        distinctReportDataTable();

        log.info("网点数据开始更新");
        
        //获得网点的（GM030201,GM030202,GM030203,GM030204,GM030205,GM010201,GM010202,GM010203,GM010204,GM010205,GM0101,GM010101,GM01010101,GM01010102,GM0101010201,GM0301,GM030101,GM030102）指标数值
        //和GM01,GM03全部数据
        Map<String,String> Orgss  = VariableContext.Orgs;
        Iterator<Map.Entry<String, String>> its = Orgss.entrySet().iterator();
        //遍历所有一级分行
        while (its.hasNext()) {
        	
            Map.Entry<String, String> entry = its.next();
            String level1orgID = entry.getKey();
            if ("9900".equals(level1orgID)){
                continue;
            }
           //获得分行旗下所有网点的数据
            String level2OrgID = getBranchID(level1orgID);
            //大于100个网点的分行，拆分获得数据
       	 	int length = level2OrgID.length()/500+1;
            String[] level2OrgIDs = new String[length];
            for(int i =0 ;i <length-1;i++) {
           	 	//每次从0开始截取500位，截取数据保存到数组
            	level2OrgIDs[i] = level2OrgID.substring(0,500).substring(1);
            	//自己保留后续数据
            	level2OrgID = level2OrgID.substring(500);
            }
            //最后剩余数据追加
            level2OrgIDs[length-1] = level2OrgID.substring(1);
            String  res = getBranchData(level1orgID,level2OrgIDs);
            log.info(level1orgID+"的网点数据获取结果为："+res);
        }

        log.info("请求获取5分钟最新数据结束");
        return "Success";
    }

    public String update30Data(String oldTime,String time){
        //30分钟更新YS01的数据
        List<OutData> dataList= null;
        try {
            dataList= (List<OutData>) getProcedureData(VariableContext.Level1OrgIDs,"YS01","10000","asc");
        }catch (Exception e){
            log.info("30分钟同步出错错误为"+e);
            return  "Error";
        }

        for (OutData outData : dataList){
            outData.setSTATIS_DT(time);
            //百万，两位小数
            outData.setKPI_VALUE(NumUtil.dealNum(outData.getKPI_VALUE(),1000000,2));
        }
        //删除表数据
        nrptReportDataMapper.deleteReportPastDataTableByDateInSomeKey(oldTime);
        //插入数据库
        nrptReportDataMapper.insertBatchReportPastData(dataList);
        log.info("请求获取30分钟最新数据结束");
        return "Success";
    }

    //每晚留存数据
    public String updateDayData(){
        //今天
        String nowTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        log.info("今天时间为："+nowTime);
        //查出今天的数据
        List<String> keyList = Arrays.asList("GM0102","GM010101","GM030101","GM0101","GM0101010201","GM01010102","GM0301","GM030102","GM01010101","GM010202","GM010203","GM030205","GM010201","GM030203","GM030204","GM030201","GM010204","GM010205","GM030202","GM0302","GM0304","GM0104");
        List<OutData> dataList = nrptReportDataMapper.selectBatchDataReportAllDataByDivIDAndOrgID(keyList);
        for (OutData data : dataList){
            data.setSTATIS_DT(nowTime+" 23:59:59");
        }
        //昨天
        String delTime = TimeUtil.getLastXDay(-1)+" 23:59:59";
        log.info("昨天待删除的时间为："+delTime);
        nrptReportDataMapper.deleteReportPastDataTableByKeyDate(delTime);
        nrptReportDataMapper.insertBatchReportPastData(dataList);
        //这月月末
        String delNowMonthTime = TimeUtil.getLastDay(0,0);
        log.info("这个月末日期为："+delNowMonthTime);
        //如果当天是月末留存当日数据作为比上月数据
        if(nowTime.equals(delNowMonthTime)){
            //上月月末
            String delMonthTime = TimeUtil.getLastDay(-1,0)+" 23:59:50";
            log.info("上个月末日期为"+delMonthTime);
            for (OutData data : dataList){
                data.setSTATIS_DT(delNowMonthTime+" 23:59:50");
            }
            nrptReportDataMapper.deleteReportPastDataTableByKeyDate(delMonthTime);
            nrptReportDataMapper.insertBatchReportPastData(dataList);
            //今天年末
            //String delNowYearTime = TimeUtil.getYearLastDay(0);
            /*if (nowTime.equals(delNowYearTime)){
                //上年年末
                String delYearTime = TimeUtil.getYearLastDay(1)+" 59:59:45";
                nrptReportDataMapper.deleteReportPastDataTableByKeyDate(delYearTime);
                nrptReportDataMapper.insertBatchReportPastData(dataList);
            }*/
        }
        return "Success";
    }

    //数据去重，防止上游下发重复数据
    public void distinctReportDataTable(){
        nrptReportDataMapper.dropRepeatReportDataTable1();
        nrptReportDataMapper.dropRepeatReportDataTable2();
        nrptReportDataMapper.dropRepeatReportDataTable3();
        nrptReportDataMapper.dropRepeatReportDataTable4();
        log.info("去重成功");
    }

    //调用存储过程获得数据
    public Object getProcedureData(String orgID,String keyID,String num,String order){
        EsbSoap esbSoap = new EsbSoap();
        //通过webService交互获得最新的数据
        String YYYYMMDD = TimeUtil.getTime("YYYYMMDD");
        String mmssSSS = TimeUtil.getTime("mmssSSS");
        ProcedureInParams procedureInParams = new ProcedureInParams();
        procedureInParams.setI_CURRENCY_CD("199");
        procedureInParams.setI_DATE("");
        //指标
        procedureInParams.setI_KPIIDS(keyID);
        //组织
        procedureInParams.setI_ORGIDS(orgID);
        procedureInParams.setI_NUM(num);
        procedureInParams.setI_ORDER(order);
        SoapInParams soapInParams = new SoapInParams();
        soapInParams.setProcedureName("proc_call_ma_screen");
        soapInParams.setSchemaName("NCMA");
        soapInParams.setValues(procedureInParams);
        //发送报文
        String SoapXml =  esbSoap.getsoapXml(YYYYMMDD, mmssSSS, JSONObject.toJSONString(soapInParams));
        //log.info("发送的报文为："+SoapXml);
        //回传结果
        String res = esbSoap.getData(SoapXml);
        if ("0".equals(res)||res.contains("调用失败")){
            log.error("请求获取最新数据出错，详细信息为："+res);
            return "Error";
        }
        ProcedureOutParams procedureOutParams = JSONObject.parseObject(res, ProcedureOutParams.class);
        List<OutData> dataList= procedureOutParams.getO_REF();
        if (dataList.size()<1){
            log.error("当前调用存储过程成功，但无数据");
            return "Error";
        }

        return dataList;
    }

    //分行数据处理,增加parentID;
    public List<ReportBranchDataEntity> dealBranchData(List<OutData> data,String parentID){
        List<ReportBranchDataEntity> dataList = new ArrayList();
        for (OutData outData : data){
            ReportBranchDataEntity reportBranchDataEntity = new ReportBranchDataEntity();
            reportBranchDataEntity.setParentID(parentID);
            reportBranchDataEntity.setSTATIS_DT(outData.getSTATIS_DT());
            reportBranchDataEntity.setKPI_VALUE(outData.getKPI_VALUE());
            reportBranchDataEntity.setCURRENCY_CD(outData.getCURRENCY_CD());
            reportBranchDataEntity.setKPI_ID(outData.getKPI_ID());
            reportBranchDataEntity.setKPI_NAME(outData.getKPI_NAME());
            reportBranchDataEntity.setORG_ID(outData.getORG_ID());
            reportBranchDataEntity.setORG_NAME(outData.getORG_NAME());
            dataList.add(reportBranchDataEntity);
        }
        return dataList;
    }

    //获得一级分行对应所有的网点机构号
    public String getBranchID(String parentId){
        String branchID = "";
        List<String> dataList = nrptReportDataMapper.selectReportOrgDataTableByParentID(parentId);
        for (String data : dataList){
            branchID += ","+data;
        }
        return branchID;
    }
    
    //网点获取数据处理
    public String getBranchData(String level1orgID,String... level2OrgIDs) {
    	
    	List<OutData> level2AscDataList = new ArrayList<OutData>();
        List<OutData> level2GM01DataList = new ArrayList<OutData>();
        List<OutData> level2GM03DataList = new ArrayList<OutData>();
        
       try {
    	   for(String level2OrgID : level2OrgIDs) {
    		   level2AscDataList.addAll((List<OutData>) getProcedureData(level2OrgID,VariableContext.Level2KeyIDs,"10000","adc"));
    		   level2GM01DataList.addAll((List<OutData>) getProcedureData(level2OrgID,"GM01","1000","asc"));
    		   level2GM03DataList.addAll((List<OutData>) getProcedureData(level2OrgID,"GM03","1000","asc"));
    	   }
       } catch (Exception e) {
		log.error("5分钟网点数据同步出错，错误为："+e);
		return "Fail";
       }
       
        List<ReportBranchDataEntity> level2AscDataListDeal = dealBranchData(level2AscDataList,level1orgID);
        List<ReportBranchDataEntity> level2GM01DataListDeal = dealBranchData(level2GM01DataList,level1orgID);
        List<ReportBranchDataEntity> level2GM03DataListDeal = dealBranchData(level2GM03DataList,level1orgID);

        //删除数据
        nrptReportDataMapper.deleteReportBranchDataTableByParentID(level1orgID);

        //插入数据
        nrptReportDataMapper.insertBatchReportBranchDataTable(level2AscDataListDeal);
        nrptReportDataMapper.insertBatchReportBranchDataTable(level2GM01DataListDeal);
        nrptReportDataMapper.insertBatchReportBranchDataTable(level2GM03DataListDeal);
        
        return "Success";
        
    }
}
