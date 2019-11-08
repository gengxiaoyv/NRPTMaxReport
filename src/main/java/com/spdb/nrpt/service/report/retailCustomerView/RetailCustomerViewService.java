package com.spdb.nrpt.service.report.retailCustomerView;

import com.spdb.nrpt.config.RetailCustomerViewContext;
import com.spdb.nrpt.entity.report.Retail.ResponseData;
import com.spdb.nrpt.entity.report.RetailCustomerView.RetailCustomResponseData;
import com.spdb.nrpt.entity.report.RetailCustomerView.RetailCustomerBaseData;
import com.spdb.nrpt.entity.report.RetailCustomerView.RetailCustomerRequestVO;
import com.spdb.nrpt.entity.report.RetailCustomerView.RetailCustomerViewVO;
import com.spdb.nrpt.mapper.retailIndexMapper.RetailCustomerViewMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class RetailCustomerViewService {

    @Autowired
    private RetailCustomerViewMapper retailCustomerViewMapper;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


    //得到当前日期的前一天
    public static Date getLastDay(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        Date lastDay = calendar.getTime();
        return lastDay;


    }


    public static Date getLastMonth(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.DAY_OF_MONTH, 0);
        //calendar.add(Calendar.MONTH,-1);
        Date lastMonth = calendar.getTime();
        return lastMonth;


    }


    public Date getLastMonthLastDay() throws Exception {
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_MONTH, 0);//设置为1号,当前日期既为本月第一天
        return onlyGetDay(cale.getTime());

    }

    public Date onlyGetDay(Date date) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s = sdf.format(date);
        Date returnDate = sdf.parse(s);
        return returnDate;
    }

    public static Date getThisMonthLastDay(Date date) {

        final Calendar cal = Calendar.getInstance();

        cal.setTime(date);

        final int last = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        cal.set(Calendar.DAY_OF_MONTH, last);

        return cal.getTime();
    }

    //磁贴数据

    public List<RetailCustomerBaseData> getTileData(RetailCustomerViewVO retailCustomerViewVO) {

        SimpleDateFormat formatdate = new SimpleDateFormat("YYYY-MM-dd");//日期算换格式
        //判断日期是否等于当前日期
        //formatdate.format(retailCustomerViewVO.getDate())

        //如果是按月会传什么？ todo
        log.info("获取磁贴数据开始==============================================");
        RetailCustomerRequestVO retailCustomerRequestVO = new RetailCustomerRequestVO();
        retailCustomerRequestVO.setData_date(retailCustomerViewVO.getDate());
        retailCustomerRequestVO.setOrg_id(retailCustomerViewVO.getOrgId());
        retailCustomerRequestVO.setDims2(retailCustomerViewVO.getByDayOrMonth());
        retailCustomerRequestVO.setDims3(retailCustomerViewVO.getCustomerType());
        retailCustomerRequestVO.setTileNameList(RetailCustomerViewContext.tileName);
        if (retailCustomerViewVO.getByDayOrMonth().equals("02")) {
            retailCustomerRequestVO.setData_date(getThisMonthLastDay(retailCustomerViewVO.getDate()));
        }
        List<RetailCustomerBaseData> retailCustomerBaseDataList = retailCustomerViewMapper.selectTileDataByDayOrgTypeAndIndex(retailCustomerRequestVO);
        return retailCustomerBaseDataList;

    }


    //获取性别年龄等基础信息
    public Map<String, List<RetailCustomerBaseData>> getAgeSexInfoData(RetailCustomerViewVO retailCustomerViewVO) {

        log.info("获取性别、年龄、风险等级信息开始==============================");
        RetailCustomerRequestVO requestVO = new RetailCustomerRequestVO();
        requestVO.setData_date(retailCustomerViewVO.getDate());
        requestVO.setOrg_id(retailCustomerViewVO.getOrgId());
        requestVO.setDims2(retailCustomerViewVO.getByDayOrMonth());
        requestVO.setDims3(retailCustomerViewVO.getCustomerType());
        if (retailCustomerViewVO.getByDayOrMonth().equals("02")) {
            requestVO.setData_date(getThisMonthLastDay(retailCustomerViewVO.getDate()));
        }
        Map<String, List<RetailCustomerBaseData>> map = new HashMap<>();
        //性别
        requestVO.setIndex_name("性别情况_客户数");
        requestVO.setDims4("3");

        List<RetailCustomerBaseData> baseDataSex = retailCustomerViewMapper.getSexAgeRankData(requestVO);
        for (RetailCustomerBaseData data : baseDataSex) {
            String dims4 = data.getDims4();
            data.setDims4(RetailCustomerViewContext.sex.get(dims4));
        }
        map.put("sex", baseDataSex);
        //年龄
        requestVO.setDims4("8");
        requestVO.setIndex_name("年龄情况_客户数");

        List<RetailCustomerBaseData> baseDataAge = retailCustomerViewMapper.getSexAgeRankData(requestVO);

        for (RetailCustomerBaseData data : baseDataAge) {
            String dims4 = data.getDims4();
            data.setDims4(RetailCustomerViewContext.age.get(dims4));
        }
        map.put("age", baseDataAge);

        //风险等级
        requestVO.setDims4("其他");
        requestVO.setIndex_name("风险评级客户情况_客户数");

        List<RetailCustomerBaseData> baseDataRank = retailCustomerViewMapper.getSexAgeRankData(requestVO);

        map.put("rank", baseDataRank);

        return map;

    }


    //获取分行地图数据
    public RetailCustomResponseData getBranchMapData(RetailCustomerViewVO retailCustomerViewVO) {

        log.info("获取分行地图数据开始===============================");
        //DecimalFormat df = new DecimalFormat("0%");
        RetailCustomerRequestVO requestVO = new RetailCustomerRequestVO();
        requestVO.setData_date(retailCustomerViewVO.getDate());

        requestVO.setDims2(retailCustomerViewVO.getByDayOrMonth());
        requestVO.setDims3(retailCustomerViewVO.getCustomerType());
        if (retailCustomerViewVO.getByDayOrMonth().equals("02")) {
            requestVO.setData_date(getThisMonthLastDay(retailCustomerViewVO.getDate()));
        }

        RetailCustomResponseData responseData = new RetailCustomResponseData();
        //分行总客户数
        requestVO.setOrg_id(retailCustomerViewVO.getOrgId());
        requestVO.setIndex_name("客户数");
        Long branCustomerCount = retailCustomerViewMapper.getMapDataCount(requestVO);
        responseData.setBranCustomerCount(branCustomerCount);

        //全行总客户数
        requestVO.setOrg_id("9900");
        Long headCustomerCount = retailCustomerViewMapper.getMapDataCount(requestVO);
        responseData.setHeadBankCustomerCount(headCustomerCount);

        // 分行非零客户数
        requestVO.setIndex_name("非0客户数");
        requestVO.setOrg_id(retailCustomerViewVO.getOrgId());
        Long branNoZeroCustomerCount = retailCustomerViewMapper.getMapDataCount(requestVO);
        responseData.setBranNoZeroCustomer(branNoZeroCustomerCount);

        //全行非零客户数
        requestVO.setIndex_name("非0客户数");
        requestVO.setOrg_id("9900");
        Long headNoZeroCustomerCount = retailCustomerViewMapper.getMapDataCount(requestVO);
        responseData.setHeadBankNoZeroCustomer(headNoZeroCustomerCount);
        //分行非零客户数占比
        requestVO.setIndex_name("非0客户数占比");
        requestVO.setOrg_id(retailCustomerViewVO.getOrgId());
        Double branNoZeroCustomerPercent = retailCustomerViewMapper.getMapDataPercent(requestVO);
        responseData.setBranNoZeroCustomerPercent(branNoZeroCustomerPercent);


        //全行非零客户数占比
        requestVO.setIndex_name("非0客户数占比");
        requestVO.setOrg_id("9900");
        Double headNoZeroCustomerPercent = retailCustomerViewMapper.getMapDataPercent(requestVO);
        responseData.setHeadBankNoZeroCustomerPercent(headNoZeroCustomerPercent);


        //分行AUM资产规模
        requestVO.setIndex_name("资产规模");
        requestVO.setOrg_id(retailCustomerViewVO.getOrgId());
        Long branAum = retailCustomerViewMapper.getMapDataCount(requestVO);
        responseData.setBranAUMAsset(branAum);

        //分行AUM资产规模占比
        requestVO.setIndex_name("AUM规模占比");
        requestVO.setOrg_id(retailCustomerViewVO.getOrgId());
        Double AUMPercent = retailCustomerViewMapper.getMapDataPercent(requestVO);
        responseData.setBranAUMAssetsPercent(AUMPercent);
        String orgName = RetailCustomerViewContext.Orgs.get(retailCustomerViewVO.getOrgId());
        responseData.setOrgName(orgName);
        if (orgName != null) {

            System.out.println(orgName);
            if (orgName.contains("分行")) {
                if (orgName.startsWith("浦发银行")) {
                    orgName = orgName.substring(4);
                }
                String orgNameSub = orgName.substring(0, orgName.indexOf("分"));
                responseData.setProvinceName(RetailCustomerViewContext.provinceName.get(orgNameSub));
            } else {
                responseData.setProvinceName(RetailCustomerViewContext.provinceName.get("上海"));
            }
        }


        return responseData;

    }


    //获取近三十天趋势   此处所有时间均为当前时间加加减减，不由前端传值
    public List<RetailCustomResponseData> getOneMonthCustTrend(RetailCustomerViewVO retailCustomerViewVO) throws Exception {

        log.info("获取近三十天客户趋势开始======================");
        List<RetailCustomResponseData> retailCustomResponseDataList = new ArrayList<>();
        RetailCustomerRequestVO requestVO = new RetailCustomerRequestVO();
        requestVO.setData_date(onlyGetDay(getLastDay(new Date())));// todo 是否三十天趋势一定是最近的三十天，不会改变
        requestVO.setOrg_id(retailCustomerViewVO.getOrgId());
        requestVO.setDims2("01");
        requestVO.setDims3(retailCustomerViewVO.getCustomerType());
        //for循环，1-5为不同客户类型
        RetailCustomResponseData retailCustomResponseData = null;
        for (int i = 1; i <= 5; i++) {
            //查询不同客户的趋势图及数据
            requestVO.setDims4(i + "");
            retailCustomResponseData = getDifCustTrendData(requestVO);
            retailCustomResponseData.setDims4(RetailCustomerViewContext.CustSlice.get(i + ""));
            if(retailCustomResponseData.getRetailCustomerBaseDataList().size()!=0){
                retailCustomResponseDataList.add(retailCustomResponseData);
            }


        }

        return retailCustomResponseDataList;
    }


    //查询不同客户的趋势图及数据
    public RetailCustomResponseData getDifCustTrendData(RetailCustomerRequestVO requestVO) throws Exception {
        //DecimalFormat df = new DecimalFormat("0%");
        RetailCustomResponseData retailCustomResponseData = new RetailCustomResponseData();
        //查询趋势图基础数据
        requestVO.setIndex_name("客户情况及趋势_客户数");
        List<RetailCustomerBaseData> retailCustomerBaseDataList = retailCustomerViewMapper.selectOneMonthTrend(requestVO);
        retailCustomResponseData.setRetailCustomerBaseDataList(retailCustomerBaseDataList);


        //该日客户类型客户数与全行客户数占比

        requestVO.setIndex_name("账户情况_客户数");
        Double percent = retailCustomerViewMapper.selectCustPercent(requestVO);
        retailCustomResponseData.setCustPercent(percent);


        //查询较上月净增客户数
        requestVO.setIndex_name("客户趋势_较上月净增");
        Long incrthanlastmonth = retailCustomerViewMapper.getincrthan(requestVO);
        retailCustomResponseData.setIncrThanLastMonthEnd(incrthanlastmonth);

        //较年初净增客户数
        requestVO.setIndex_name("客户趋势_较年初净增");
        Long increThanYearBegin = retailCustomerViewMapper.getincrthan(requestVO);
        retailCustomResponseData.setIncrThanYearBegin(increThanYearBegin);


        //年初私行基准线  若传来的日期为月末，说明当天是一号，则传一号的日期 ，
        requestVO.setData_date(onlyGetDay(new Date()));
        System.out.println("========时间" + onlyGetDay(new Date()).toString());
        requestVO.setIndex_name("客户情况及趋势_客户数");
        Long yearLine = retailCustomerViewMapper.getYearBeginLine(requestVO);
        retailCustomResponseData.setYearDatLine(yearLine);

        //月初私行基准线   直接获取到當前時間上个月最后一天
        requestVO.setData_date(getLastMonthLastDay());
        requestVO.setIndex_name("客户情况及趋势_客户数");
        Long monthLine = retailCustomerViewMapper.getMonthBeginLine(requestVO);
        retailCustomResponseData.setMonthDatLine(monthLine);

        return retailCustomResponseData;

    }


    //获取近一年趋势
    public List<RetailCustomResponseData> getOneYearCustTrend(RetailCustomerViewVO retailCustomerViewVO) throws Exception {
        // TODO: 2019/11/3

        log.info("获取近一年客户趋势开始=============================================");
        List<RetailCustomResponseData> retailCustomResponseDataList = new ArrayList<>();

        RetailCustomerRequestVO requestVO = new RetailCustomerRequestVO();
        requestVO.setData_date(onlyGetDay(getLastDay(new Date())));// todo 是否三十天趋势一定是最近的三十天，不会改变
        requestVO.setOrg_id(retailCustomerViewVO.getOrgId());
        requestVO.setDims2("02");
        requestVO.setDims3(retailCustomerViewVO.getCustomerType());
        //for循环，1-5为不同客户类型
        RetailCustomResponseData retailCustomResponseData = null;
        for (int i = 1; i <= 5; i++) {
            //查询不同客户的趋势图及数据
            requestVO.setDims4(i + "");
            retailCustomResponseData = getDifCustYearTrendData(requestVO);
            retailCustomResponseData.setDims4(RetailCustomerViewContext.CustSlice.get(i + ""));
            if(retailCustomResponseData.getRetailCustomerBaseDataList().size()!=0){
                retailCustomResponseDataList.add(retailCustomResponseData);
            }

        }
        return retailCustomResponseDataList;
    }

    //查询不同客户近一年的趋势图及数据
    public RetailCustomResponseData getDifCustYearTrendData(RetailCustomerRequestVO requestVO) throws Exception {
        //DecimalFormat df = new DecimalFormat("0%");
        RetailCustomResponseData retailCustomResponseData = new RetailCustomResponseData();
        //查询趋势图基础数据
        requestVO.setIndex_name("客户情况及趋势_客户数");
        List<RetailCustomerBaseData> retailCustomerBaseDataList = retailCustomerViewMapper.selectOneYearTrend(requestVO);
        //循环list将每个月份节点需要的  客户类型客户数与全行客户数占比  较上月净增客户数  较年初净增客户数put进

        for (int i = 0; i < retailCustomerBaseDataList.size(); i++) {

            //每个月份里的日期get出来  ,获取月份的最后一天传入日期
            RetailCustomerBaseData retailCustomerBaseData = retailCustomerBaseDataList.get(i);
            Date data_date = retailCustomerBaseData.getData_date();//此处日期是当前时间往前推12个月的

            requestVO.setData_date(getThisMonthLastDay(data_date));

            //该月客户类型客户数与全行客户数占比

            requestVO.setIndex_name("账户情况_客户数");
            Double percent = retailCustomerViewMapper.selectCustPercent(requestVO);
            retailCustomerBaseData.setCustPercent(percent);

            //查询较上月净增客户数
            requestVO.setIndex_name("客户趋势_较上月净增");
            Long incrthanlastmonth = retailCustomerViewMapper.getincrthan(requestVO);
            retailCustomerBaseData.setIncrThanLastMonthEnd(incrthanlastmonth);

            //较年初净增客户数
            requestVO.setIndex_name("客户趋势_较年初净增");
            Long increThanYearBegin = retailCustomerViewMapper.getincrthan(requestVO);
            retailCustomerBaseData.setIncrThanYearBegin(increThanYearBegin);

            //该月该分层客户流失率
            requestVO.setIndex_name("流失率");
            Double lossPercent = retailCustomerViewMapper.selectCustLossPercent(requestVO);
            retailCustomerBaseData.setLossPercent(lossPercent);


        }
        retailCustomResponseData.setRetailCustomerBaseDataList(retailCustomerBaseDataList);


        //年初私行基准线  若传来的日期为月末，说明当天是一号，则传一号的日期 ，
        requestVO.setData_date(onlyGetDay(new Date()));
        System.out.println("========时间" + onlyGetDay(new Date()).toString());
        requestVO.setIndex_name("客户情况及趋势_客户数");
        Long yearLine = retailCustomerViewMapper.getYearBeginLine(requestVO);
        retailCustomResponseData.setYearDatLine(yearLine);

        //月初私行基准线   直接获取到上个月最后一天
        requestVO.setData_date(getLastMonthLastDay());
        requestVO.setIndex_name("客户情况及趋势_客户数");
        Long monthLine = retailCustomerViewMapper.getMonthBeginLine(requestVO);
        retailCustomResponseData.setMonthDatLine(monthLine);

        return retailCustomResponseData;

    }


    //总行地图信息
    public List<RetailCustomerBaseData> getHeadMapInfo(RetailCustomerViewVO retailCustomerViewVO) {

        log.info("总行获取地图信息开始======================================");
        List<RetailCustomResponseData> retailCustomResponseDataList = new ArrayList<>();
        RetailCustomerRequestVO requestVO = new RetailCustomerRequestVO();
        requestVO.setData_date(retailCustomerViewVO.getDate());
        requestVO.setDims2(retailCustomerViewVO.getByDayOrMonth());
        requestVO.setDims3(retailCustomerViewVO.getCustomerType());
        requestVO.setIndex_name("客户数");
        requestVO.setOrg_idList(RetailCustomerViewContext.orgidList);
        if (retailCustomerViewVO.getByDayOrMonth().equals("02")) {
            requestVO.setData_date(getThisMonthLastDay(retailCustomerViewVO.getDate()));
        }


        List<RetailCustomerBaseData> dataList = retailCustomerViewMapper.getHeadMapData(requestVO);
        List<RetailCustomerBaseData> returnList = new ArrayList<>();
        //循环放入城市名与省份名
        for (int i = 0; i < dataList.size(); i++) {
            RetailCustomerBaseData retailCustomerBaseData = dataList.get(i);
            String name = retailCustomerBaseData.getOrg_name();
            if (name.contains("分行")) {
                if (name.startsWith("浦发银行")) {
                    name = name.substring(4);
                }
                String cityName = name.substring(0, name.indexOf("分"));
                retailCustomerBaseData.setProvinceName(RetailCustomerViewContext.provinceName.get(cityName));
                retailCustomerBaseData.setCityName(cityName);
                //todo 同一省份中有两个分行  相加
                returnList.add(retailCustomerBaseData);
            }

        }
        List<RetailCustomerBaseData> returnList2 = new ArrayList<>();

        for (RetailCustomerBaseData datasource:returnList) {
            RetailCustomerBaseData add = new RetailCustomerBaseData();
            BeanUtils.copyProperties(datasource,add);
            returnList2.add(add);
        }
        //双层循环数据相加
        for (RetailCustomerBaseData data:returnList){
            String provinceName1 = data.getProvinceName();
            String cityName1 = data.getCityName();
            Long amt1 = data.getAmt();
            for (RetailCustomerBaseData data2:returnList2){
                String province2 = data2.getProvinceName();
                String cityName2 = data2.getCityName();
                Long amt2 = data.getAmt();
                if ((province2.equals(provinceName1)&&!cityName1.equals(cityName2))){
                    Long returnamt = amt1+amt2;
                    data.setAmt(returnamt);
                    data.setCityName(data.getCityName()+","+data2.getCityName());
                    data.setOrg_id(data.getOrg_id()+","+data2.getOrg_id());
                    data.setOrg_name(data.getOrg_name()+","+data2.getOrg_name());
                }

            }
        }

        Set<RetailCustomerBaseData> set = new TreeSet<RetailCustomerBaseData>(new Comparator<RetailCustomerBaseData>() {
            @Override
            public int compare(RetailCustomerBaseData a, RetailCustomerBaseData b) {
                // 字符串则按照asicc码升序排列
                return a.getProvinceName().compareTo(b.getProvinceName());
            }
        });

        set.addAll(returnList);
        return new ArrayList<RetailCustomerBaseData>(set);
       // return returnList;

    }

    //分层客户
    public List<RetailCustomerBaseData> getCustSlice(RetailCustomerViewVO retailCustomerViewVO) {

        log.info("获取客户分层信息开始=======================================");
        //DecimalFormat df = new DecimalFormat("0%");
        RetailCustomerRequestVO requestVO = new RetailCustomerRequestVO();
        requestVO.setData_date(retailCustomerViewVO.getDate());
        requestVO.setDims2(retailCustomerViewVO.getByDayOrMonth());
        requestVO.setDims3(retailCustomerViewVO.getCustomerType());
        requestVO.setOrg_id(retailCustomerViewVO.getOrgId());
        requestVO.setIndex_name("账户情况_客户数");
        if (retailCustomerViewVO.getByDayOrMonth().equals("02")) {
            requestVO.setData_date(getThisMonthLastDay(retailCustomerViewVO.getDate()));
        }
        List<RetailCustomerBaseData> returnList = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            requestVO.setDims4(i + "");
            //非0基客客户数
            Long custCount = retailCustomerViewMapper.selectCustSliceCount(requestVO);

            Double branPercent = retailCustomerViewMapper.selectCustSlicePercent(requestVO);
            requestVO.setOrg_id("9900");
            Double headPercent = retailCustomerViewMapper.selectCustSlicePercent(requestVO);

            RetailCustomerBaseData retailCustomerBaseData = new RetailCustomerBaseData();
            retailCustomerBaseData.setAmt(custCount);
            retailCustomerBaseData.setBranPercent(branPercent);
            retailCustomerBaseData.setHeadPercent(headPercent);
            retailCustomerBaseData.setDims4(RetailCustomerViewContext.CustSlice.get(i + ""));
            returnList.add(retailCustomerBaseData);

        }
        return returnList;

    }


    //卡分层
    public List<RetailCustomResponseData> getCardSlice(RetailCustomerViewVO retailCustomerViewVO) {
        log.info("获取卡分层&客户资产分层信息开始==============================================");
        RetailCustomerRequestVO requestVO = new RetailCustomerRequestVO();

        requestVO.setData_date(retailCustomerViewVO.getDate());
        requestVO.setDims2(retailCustomerViewVO.getByDayOrMonth());
        requestVO.setDims3(retailCustomerViewVO.getCustomerType());
        requestVO.setOrg_id(retailCustomerViewVO.getOrgId());
        if (retailCustomerViewVO.getByDayOrMonth().equals("02")) {
            requestVO.setData_date(getThisMonthLastDay(retailCustomerViewVO.getDate()));
        }

        //卡分层信息
        requestVO.setIndex_name("账户情况_客户数");
        List<RetailCustomResponseData> returnList = retailCustomerViewMapper.selectCardSlice(requestVO);

        for (RetailCustomResponseData data : returnList) {
            String dims5 = data.getDims5();
            data.setDims4(RetailCustomerViewContext.account.get(dims5));
            //客户资产分布的客户类型与数值
            requestVO.setDims5(dims5);
            List<RetailCustomerBaseData> countDataList = retailCustomerViewMapper.getAssetsSlice(requestVO);
            System.out.println(countDataList.toString());

            List<RetailCustomerBaseData> branPercent = retailCustomerViewMapper.getCardCustPercent(requestVO);
            List<RetailCustomerBaseData> headPercent = null;
            if (requestVO.getOrg_id() != "9900" && !requestVO.getOrg_id().equals("9900")) {
                RetailCustomerRequestVO headRequestvo = new RetailCustomerRequestVO();
                BeanUtils.copyProperties(requestVO, headRequestvo);
                headRequestvo.setOrg_id("9900");
                headPercent = retailCustomerViewMapper.getCardCustPercent(requestVO);

            }

            List<RetailCustomerBaseData> addDateList = new ArrayList<>();

            //RetailCustomerBaseData addata = new RetailCustomerBaseData();
            for (RetailCustomerBaseData baseData : countDataList) {
                RetailCustomerBaseData addata = new RetailCustomerBaseData();

                System.out.println(baseData);
                String dims4 = baseData.getDims4();

                System.out.println("dims4=======" + dims4);
                System.out.println(baseData.getAmt());
                addata.setAmt(baseData.getAmt());
                addata.setDims4(RetailCustomerViewContext.CustSlice.get(dims4));

                for (RetailCustomerBaseData dataBran : branPercent) {

                    String dims4bran = dataBran.getDims4();
                    if (dims4bran.equals(dims4) || dims4bran == dims4) {
                        addata.setBranPercent(dataBran.getAmt_zb());
                    }
                }

                if (headPercent != null && headPercent.size() != 0) {
                    for (RetailCustomerBaseData datahead : headPercent) {
                        String dims4bran = datahead.getDims4();
                        if (dims4bran.equals(dims4) || dims4bran == dims4) {
                            addata.setHeadPercent(datahead.getAmt_zb());
                        }
                    }
                }

                addDateList.add(addata);
            }

            data.setRetailCustomerBaseDataList(addDateList);

        }

        return returnList;
    }
}
