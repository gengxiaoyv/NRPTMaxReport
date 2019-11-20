package com.spdb.nrpt.controller.report;


import com.spdb.nrpt.entity.report.RetailCustomerView.RetailCustomResponseData;
import com.spdb.nrpt.entity.report.RetailCustomerView.RetailCustomerBaseData;
import com.spdb.nrpt.entity.report.RetailCustomerView.RetailCustomerViewVO;
import com.spdb.nrpt.mapper.demoMapper.DemoMapper;
import com.spdb.nrpt.mapper.demoMapper.Student;
import com.spdb.nrpt.service.report.retailCustomerView.RetailCustomerViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@RestController
public class RetailCustomerViewController {

    @Autowired
    private RetailCustomerViewService retailCustomerViewService;

    @Autowired
    private DemoMapper demoMapper;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    //判断该人员是分行还是总行
    @RequestMapping("/getStaffOrg")
    public void getStaffOrg(HttpServletRequest httpServletRequest)throws Exception{
        HttpSession session = httpServletRequest.getSession();

        String orgId = session.getAttribute("orgId").toString();

        //orgid=9900总行
        if (orgId.equals("9900")){

        }

    }

    //获取上方磁贴数据
    @RequestMapping("/getTopBaseData")
    public List<RetailCustomerBaseData> getTopBaseData(RetailCustomerViewVO retailCustomerViewVO, HttpServletRequest httpServletRequest)throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        //获取柜员机构号 取得权限是总行or分行   分行则
        HttpSession session = httpServletRequest.getSession();
       // String orgId = session.getAttribute("").toString(); // TODO: 2019/11/1 确认第三方传入的是机构号还是员工号
        retailCustomerViewVO.setDate(simpleDateFormat.parse(retailCustomerViewVO.getDateString()));
        //
        List<RetailCustomerBaseData> retailCustomerBaseDataList = retailCustomerViewService.getTileData(retailCustomerViewVO);

        return retailCustomerBaseDataList;


    }

    //获取年龄性别等基础占比信息
    @RequestMapping("/getBaseInfoData")
    public Map<String,List<RetailCustomerBaseData>> getBaseInfoData(RetailCustomerViewVO retailCustomerViewVO)throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        retailCustomerViewVO.setDate(simpleDateFormat.parse(retailCustomerViewVO.getDateString()));
        Map<String,List<RetailCustomerBaseData>> baseinfo = retailCustomerViewService.getAgeSexInfoData(retailCustomerViewVO);

        return baseinfo;
    }


    //总行地图信息
    @RequestMapping("/getMapInfo")
    public List<RetailCustomerBaseData> getHeadMapInfo(RetailCustomerViewVO retailCustomerViewVO)throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        retailCustomerViewVO.setDate(simpleDateFormat.parse(retailCustomerViewVO.getDateString()));
        return retailCustomerViewService.getMapInfo(retailCustomerViewVO);

    }



    //获取近30天客户趋势
    @RequestMapping("/getOneMonthCustTrend")
    public List<RetailCustomResponseData> getOneMonthCustTrend(RetailCustomerViewVO retailCustomerViewVO,HttpServletRequest request)throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        retailCustomerViewVO.setDate(simpleDateFormat.parse(retailCustomerViewVO.getDateString()));

        List<RetailCustomResponseData> retailCustomResponseDataList = retailCustomerViewService.getOneMonthCustTrend(retailCustomerViewVO);
        return retailCustomResponseDataList;

    }

    //获取近一年客户趋势
    @RequestMapping("/getOneYearCustTrend")
    public List<RetailCustomResponseData> getOneYearCustTrend(RetailCustomerViewVO retailCustomerViewVO)throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        retailCustomerViewVO.setDate(simpleDateFormat.parse(retailCustomerViewVO.getDateString()));
        List<RetailCustomResponseData> retailCustomResponseDataList = retailCustomerViewService.getOneYearCustTrend(retailCustomerViewVO);
        return retailCustomResponseDataList;

    }



    //获取客户分层信息
    @RequestMapping("/getCustomerSlice")
    public List<RetailCustomerBaseData> getCustomerSlice(RetailCustomerViewVO retailCustomerViewVO)throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        retailCustomerViewVO.setDate(simpleDateFormat.parse(retailCustomerViewVO.getDateString()));
        List<RetailCustomerBaseData> retailCustomResponseDataList = retailCustomerViewService.getCustSlice(retailCustomerViewVO);

        return retailCustomResponseDataList;
    }


    //获取卡分层信息
    @RequestMapping("/getCardSlice")
    public List<RetailCustomResponseData> getCardSlice(RetailCustomerViewVO retailCustomerViewVO)throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        retailCustomerViewVO.setDate(simpleDateFormat.parse(retailCustomerViewVO.getDateString()));

        List<RetailCustomResponseData> list = retailCustomerViewService.getCardSlice(retailCustomerViewVO);
        return list;
    }




    @RequestMapping("/test")
    public List<Student> test(){
        return demoMapper.getStudent();
    }
//    //分行地图信息
//    @RequestMapping("/getbranMapInfo")
//    public RetailCustomResponseData getMapInfo(RetailCustomerViewVO retailCustomerViewVO)throws Exception{
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//        retailCustomerViewVO.setDate(simpleDateFormat.parse(retailCustomerViewVO.getDateString()));
//        return retailCustomerViewService.getBranchMapData(retailCustomerViewVO);
//
//    }


}
