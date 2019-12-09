package com.spdb.nrpt.controller.report;

import com.spdb.nrpt.config.VariableContext;
import com.spdb.nrpt.entity.div.DivEntity;
import com.spdb.nrpt.entity.report.ReportReturnEntity;
import com.spdb.nrpt.entity.report.ReturnData;
import com.spdb.nrpt.service.report.NRPTDealDataService;
import com.spdb.nrpt.service.report.NRPTReport2Service;
import com.spdb.nrpt.service.report.NRPTReport3Service;
import com.spdb.nrpt.service.report.NRPTReportService;
import com.spdb.nrpt.service.webservice.NRPTWebService;
import com.spdb.nrpt.util.TimeUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class NRPTReportController {

    @Autowired
    private NRPTReportService nrptReportService;

    @Autowired
    private NRPTDealDataService nrptDealDataService;

    @Autowired
    private NRPTReport2Service nrptReport2Service;

    @Autowired
    private NRPTReport3Service nrptReport3Service;

    //根据reportID跳转到相应的页面
    @RequestMapping("/getReport1Page")
    public String getReport1Page(String reportID,HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        if (httpSession.getAttribute("orgID")==null){
            return "error/illegalLogin";
        }
        
        /*//后续拓展记录用户访问日志
        String userID = httpSession.getAttribute("userID").toString();
        String Date = TimeUtil.getNowDate();
        //插入日志记录 userID-reportID-Date*/
        
        return "NRPT/Report1/"+reportID;
    }

    //跳转大屏二
    @RequestMapping("/getReport2Page")
    public String getReport2Page(HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        if (httpSession.getAttribute("orgID")==null){
            return "error/illegalLogin";
        }
        return "NRPT/Report2/secondChart";
    }

    //跳转大屏三
    @RequestMapping("/getReport3Page")
    public String getReport3Page(HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        if (httpSession.getAttribute("orgID")==null){
            return "error/illegalLogin";
        }
        return "NRPT/Report3/thirdChart";
    }

    //获得大屏模块全部数据
    @RequestMapping("/getReportAllData")
    @ResponseBody
    public Object getReportAllData(String key, HttpServletRequest request){
        //获得当前用户的组织ID
        HttpSession httpSession = request.getSession();
        String orgID = httpSession.getAttribute("orgID").toString();
        //构建返回实体类
        ReportReturnEntity reportReturnEntity = new ReportReturnEntity();
        reportReturnEntity = dealOrg(orgID,reportReturnEntity);
        reportReturnEntity.setName(key);
        List<DivEntity> list = new ArrayList<>();
        //通过key获得对应的divID
        List<String> divIDList = VariableContext.Segmentation.get(key);
        for (int i=0; i< divIDList.size(); i++){
            String divID = divIDList.get(i);
            DivEntity divEntity = new DivEntity();
            //放入名称
            divEntity.setName(VariableContext.Segmentation.get(key.substring(0,5)+"名称").get(i));
            //放入echarts属性
            divEntity.setOption(nrptReportService.getDivEcharsData(divID,orgID));
            //放入数值  
            divEntity = nrptReportService.dealData(key,divID,divEntity,orgID);
            list.add(divEntity);
        }
        reportReturnEntity.setReportData(list);
        return reportReturnEntity;
    }

    //更新树图
    @RequestMapping("/changeTree")
    @ResponseBody
    public Object changeTree(String keyID,HttpServletRequest request){
        //获得当前用户的组织ID
        HttpSession httpSession = request.getSession();
        String orgID = httpSession.getAttribute("orgID").toString();
        List<String> keyIDs = Arrays.asList(keyID);
        ReportReturnEntity reportReturnEntity = new ReportReturnEntity();
        reportReturnEntity = dealOrg(orgID,reportReturnEntity);
        reportReturnEntity.setName("大屏二全部数据");
        List<DivEntity> divEntityList = new ArrayList<>();
        List<String> divList = Arrays.asList("100201","100202","100203");
        for (int i=0;i<divList.size();i++){
            DivEntity divEntity = new DivEntity();
            //放入名称
            divEntity.setName(VariableContext.Segmentation.get("大屏二全部名称").get(i));
            //放入echarts属性
            divEntity.setOption(nrptReportService.getDivEcharsData(divList.get(i),orgID));

            List<ReturnData> data = new ArrayList<>();
            //放入数值
            if (0==i){
                data = nrptReport2Service.get100201Data(keyIDs,orgID);
            }else if(1==i){
                data = nrptReport2Service.get100202Data(keyIDs,orgID);
            }else if (2==i){
                divEntity.setData(nrptReport2Service.get100203Data(keyIDs,orgID));
                divEntityList.add(divEntity);
                continue;
            }
            divEntity.setData(data);
            divEntity = nrptDealDataService.dealAxis(divEntity,data);
            divEntityList.add(divEntity);
        }
        reportReturnEntity.setReportData(divEntityList);
        return reportReturnEntity;
    }

    //更新云图
    @RequestMapping("/changeCloud")
    @ResponseBody
    public Object changeCloud(String keyID,HttpServletRequest request){
        //获得当前用户的组织ID
        HttpSession httpSession = request.getSession();
        String orgID = httpSession.getAttribute("orgID").toString();
        List<String> keyIDs = Arrays.asList(keyID);
        ReportReturnEntity reportReturnEntity = new ReportReturnEntity();
        //标题总分行处理
        reportReturnEntity = dealOrg(orgID,reportReturnEntity);
        reportReturnEntity.setName("大屏三全部数据");
        List<DivEntity> divEntityList = new ArrayList<>();
        List<String> divList = Arrays.asList("100301","100302","100303");
        for (int i=0;i<divList.size();i++){
            DivEntity divEntity = new DivEntity();
            //放入名称
            divEntity.setName(VariableContext.Segmentation.get("大屏三全部名称").get(i));
            //放入echarts属性
            divEntity.setOption(nrptReportService.getDivEcharsData(divList.get(i),orgID));
            List<ReturnData> data = new ArrayList<>();
            //放入数值
            if (0==i){
                data = nrptReport3Service.get100301Data(keyIDs,orgID);
                divEntity = nrptDealDataService.dealAxis(divEntity,data);
            }else if(1==i){
                data = nrptReport3Service.get100302Data(keyIDs,orgID);
                divEntity = nrptDealDataService.dealAxis(divEntity,data);
            }else if (2==i){
                divEntity.setData(nrptReport3Service.get100303Data(keyIDs,orgID));
                divEntityList.add(divEntity);
                continue;
            }
            divEntity.setData(data);
            divEntity = nrptDealDataService.dealAxis(divEntity,data);
            divEntityList.add(divEntity);
        }
        reportReturnEntity.setReportData(divEntityList);
        return reportReturnEntity;
    }

    public  ReportReturnEntity dealOrg(String orgID,ReportReturnEntity reportReturnEntity){
    	Map<String,String> orgData = VariableContext.Orgs;
       reportReturnEntity.setTitle(orgData.get(orgID));
        return reportReturnEntity;
    }
    
    //跳转大屏测试页面
    @RequestMapping("/toRepDemo")
    public String toRepDemo() {
    	return "/build/index";
    }

    //大屏三更新 将趋势图换为三个指标
    @RequestMapping("/getThreeindex")
    @ResponseBody
    public List<String> getThreeindex(HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        String orgID = httpSession.getAttribute("orgID").toString();
        //获取零售贷款和个人存款

        return nrptReport3Service.getThreeindex(orgID);
    }
}
