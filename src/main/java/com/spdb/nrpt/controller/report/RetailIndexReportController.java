package com.spdb.nrpt.controller.report;

import com.spdb.nrpt.entity.report.Retail.PageDataEntity;
import com.spdb.nrpt.entity.report.Retail.ResponseData;
import com.spdb.nrpt.entity.report.Retail.RetailReport2PO;
import com.spdb.nrpt.service.report.retailreport.RetailIndexReport1Service;
import com.spdb.nrpt.service.report.retailreport.RetailIndexReport2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RetailIndexReportController {

    @Autowired
    private RetailIndexReport1Service retailIndexReport1Service;

    @Autowired
    private RetailIndexReport2Service retailIndexReport2Service;


    //大屏二右侧存款流入流出

    @ResponseBody
    @RequestMapping("/getDepositInAndFlow")
    public List<RetailReport2PO> getDepositInAndFlow(){


        return retailIndexReport2Service.getDepositInAndFlow();
    }

    //大屏二左侧根据页签（个人存款、个人贷款等）切换展示数据
    @ResponseBody
    @RequestMapping("/getScreen2Data")
    public PageDataEntity getScreen2Data(){
        PageDataEntity screen2Data = retailIndexReport2Service.getScreen2Data();
        return screen2Data;
    }



}
