package com.spdb.nrpt.service.report.retailreport;


import com.spdb.nrpt.config.RetailCustomerViewContext;
import com.spdb.nrpt.entity.report.Retail.DivDataEntity;
import com.spdb.nrpt.entity.report.Retail.PageDataEntity;
import com.spdb.nrpt.entity.report.Retail.RetailReport2PO;
import com.spdb.nrpt.mapper.retailIndexMapper.RetailIndexDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class RetailIndexReport2Service {


    @Autowired
    private RetailIndexDataMapper retailIndexDataMapper;


    //存款流入流出  todo
    public List<RetailReport2PO> getDepositInAndFlow(){
        List<RetailReport2PO> report2POList = new ArrayList<>();

        try {
report2POList= retailIndexDataMapper.getdepInAndOut();
            if (report2POList.size()==0||report2POList==null){
                List<RetailReport2PO> list = new ArrayList<>();
                list.add(new RetailReport2PO());
//                System.out.println(10/0);
                return list;
            }
        }catch (Exception e){
            log.info("获取存款流入流出出错================================"+e.getMessage());
            log.error("获取存款流入流出出错=====================",e.getMessage(),e);
            return new ArrayList<RetailReport2PO>();


        }


        return addbranName(report2POList);
    }

    //个人存款 个人贷款   个人金融资产   非存资产等
    public List<PageDataEntity> getScreen2Data(){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM.dd");
        String date = simpleDateFormat.format(new Date());
        System.out.println(new Date());
        List<PageDataEntity> pageDataEntityList = new ArrayList<>();

        PageDataEntity pageDataEntityFin = new PageDataEntity();
        pageDataEntityFin.setDivdata(getFinancialAssetsPage());
        pageDataEntityFin.setPageName("个人金融资产");
        pageDataEntityFin.setPageDescription(date);
        pageDataEntityList.add(pageDataEntityFin);

        PageDataEntity pageDataEntityDep = new PageDataEntity();
        pageDataEntityDep.setDivdata(getDepositPage());
        pageDataEntityDep.setPageName("个人存款");
        pageDataEntityDep.setPageDescription("财务口径");
        pageDataEntityList.add(pageDataEntityDep);



        PageDataEntity pageDataEntityLoan = new PageDataEntity();
        pageDataEntityLoan.setDivdata(getLoanPage());
        pageDataEntityLoan.setPageName("个人贷款");
        pageDataEntityLoan.setPageDescription("财务口径");
        pageDataEntityList.add(pageDataEntityLoan);


        PageDataEntity pageDataEntityNon = new PageDataEntity();
        pageDataEntityNon.setDivdata(getNoRetainedAssetsPage());
        pageDataEntityNon.setPageName("非存资产");
        pageDataEntityNon.setPageDescription("（不含汇理财）"+date);
        pageDataEntityList.add(pageDataEntityNon);


        return pageDataEntityList;

    }


    //获取个人存款下所有div内容
    public DivDataEntity getDepositPage1(){


        DivDataEntity divDataEntity = new DivDataEntity();

        try{
            divDataEntity.setIncrTopFive(addbranName(retailIndexDataMapper.getdepIncTopFive()));
            divDataEntity.setIncrLastFive(addbranName(retailIndexDataMapper.getdepIncLastFive()));
            divDataEntity.setBalanceTopFive(addbranName(retailIndexDataMapper.getDepBalTopFive()));
            divDataEntity.setBalanceLastFive(addbranName(retailIndexDataMapper.getDepBalLastFive()));
            divDataEntity.setBalMapData(addProvinceName(retailIndexDataMapper.getDepBal()));
            divDataEntity.setIncMapData(addProvinceName(retailIndexDataMapper.getdepInc()));
            //比月初比年初
            List<RetailReport2PO> retailReport2POS = retailIndexDataMapper.getdepThan();

            if (retailReport2POS.size()!=0&&retailReport2POS!=null){
                RetailReport2PO report2POthan = retailReport2POS.get(0);
                if (report2POthan!=null){
                    divDataEntity.setThanMonthBegin(tobill(report2POthan.getThanMonthBegin()));
                    divDataEntity.setThanYearBegin(tobill(report2POthan.getThanYearBegin()));
                }

            }
            divDataEntity.setDepOrLoanIncr(tobill(retailIndexDataMapper.getdepIncSum()));
            divDataEntity.setBalance(tobill(retailIndexDataMapper.getdepBalSum()));



            //组合K线图和趋势图
            List<RetailReport2PO> KlineList = retailIndexDataMapper.getdepThreeMonKline();
            List<RetailReport2PO> trendList = retailIndexDataMapper.getdepThreeMonTrend();


            //地图数据  todo
            addTrendDataList(KlineList,trendList);
            divDataEntity.setKline(KlineList);

        }catch (Exception e){


            log.info("获取个人存款div出错================================"+e.getMessage());
            log.error("获取个人存款信息出错=====================",e.getMessage(),e);
            return addnullDiv();


        }

        return divDataEntity;
//        return new DivDataEntity();
    }

    //获取个人存款下所有div内容
    public DivDataEntity getDepositPage(){


        DivDataEntity divDataEntity = new DivDataEntity();

        try{
//
//            List<RetailReport2PO> balMapData = retailIndexDataMapper.getDepBal();
//            List<RetailReport2PO> incMapData = retailIndexDataMapper.getdepInc();
//
//            List<RetailReport2PO> incTopFive = incMapData.subList(0,5);
//            List<RetailReport2PO> incLastFive = incMapData.subList(incMapData.size()-5,incMapData.size());
//            List<RetailReport2PO> balTopFive = balMapData.subList(0,5);
//            List<RetailReport2PO> balLastFive = balMapData.subList(balMapData.size());
            divDataEntity.setIncrTopFive(addbranName(retailIndexDataMapper.getdepIncTopFive()));
            divDataEntity.setIncrLastFive(addbranName(retailIndexDataMapper.getdepIncLastFive()));
            divDataEntity.setBalanceTopFive(addbranName(retailIndexDataMapper.getDepBalTopFive()));
            divDataEntity.setBalanceLastFive(addbranName(retailIndexDataMapper.getDepBalLastFive()));


            divDataEntity.setBalMapData(addProvinceName(retailIndexDataMapper.getDepBal()));
            divDataEntity.setIncMapData(addProvinceName(retailIndexDataMapper.getdepInc()));
            //比月初比年初
            List<RetailReport2PO> retailReport2POS = retailIndexDataMapper.getdepThan();

            if (retailReport2POS.size()!=0&&retailReport2POS!=null){
                RetailReport2PO report2POthan = retailReport2POS.get(0);
                if (report2POthan!=null){
                    divDataEntity.setThanMonthBegin(tobill(report2POthan.getThanMonthBegin()));
                    divDataEntity.setThanYearBegin(tobill(report2POthan.getThanYearBegin()));
                }

            }
            divDataEntity.setDepOrLoanIncr(tobill(retailIndexDataMapper.getdepIncSum()));
            divDataEntity.setBalance(tobill(retailIndexDataMapper.getdepBalSum()));



            //组合K线图和趋势图
            List<RetailReport2PO> KlineList = retailIndexDataMapper.getdepThreeMonKline();
            List<RetailReport2PO> trendList = retailIndexDataMapper.getdepThreeMonTrend();

            addTrendDataList(KlineList,trendList);
            divDataEntity.setKline(KlineList);

        }catch (Exception e){


            log.info("获取个人存款div出错================================"+e.getMessage());
            log.error("获取个人存款信息出错=====================",e.getMessage(),e);
            return addnullDiv();


        }

        return divDataEntity;
//        return new DivDataEntity();
    }

    //获取个人贷款下所有div内容
    public DivDataEntity getLoanPage(){

        DivDataEntity divDataEntity = new DivDataEntity();

        try{
            divDataEntity.setIncrTopFive(addbranName(retailIndexDataMapper.getLoanIncTopFive()));
            divDataEntity.setIncrLastFive(addbranName(retailIndexDataMapper.getLoanIncLastFive()));
            divDataEntity.setBalanceTopFive(addbranName(retailIndexDataMapper.getLoanBalTopFive()));
            divDataEntity.setBalanceLastFive(addbranName(retailIndexDataMapper.getLoanBalLastFive()));
            divDataEntity.setBalMapData(addProvinceName(retailIndexDataMapper.getLoanBal()));
            divDataEntity.setIncMapData(addProvinceName(retailIndexDataMapper.getLoanInc()));

            //比月初比年初
            List<RetailReport2PO> retailReport2POS = retailIndexDataMapper.getLoanThan();

            if (retailReport2POS.size()!=0&&retailReport2POS!=null){
                RetailReport2PO report2POthan = retailReport2POS.get(0);
                if (report2POthan!=null){
                    divDataEntity.setThanMonthBegin(tobill(report2POthan.getThanMonthBegin()));
                    divDataEntity.setThanYearBegin(tobill(report2POthan.getThanYearBegin()));
                }

            }
            divDataEntity.setDepOrLoanIncr(tobill(retailIndexDataMapper.getLoanIncSum()));
            divDataEntity.setBalance(tobill(retailIndexDataMapper.getLoanBalSum()));

            List<RetailReport2PO> KlineList = retailIndexDataMapper.getLoanThreeMonKline();
            List<RetailReport2PO> trendList = retailIndexDataMapper.getLoanThreeMonTrend();

            addTrendDataList(KlineList,trendList);
            divDataEntity.setKline(KlineList);
        }catch (Exception e){


            log.info("获取个人贷款div出错================================"+e.getMessage());
            log.error("获取个人贷款出错=====================",e.getMessage(),e);
            return addnullDiv();


        }




        return divDataEntity;
    }

    //获取个人金融资产下所有div内容
    public DivDataEntity getFinancialAssetsPage(){

        DivDataEntity divDataEntity = new DivDataEntity();
        try{

            divDataEntity.setIncrTopFive(addbranName(retailIndexDataMapper.getFinAssIncTopFive()));
            divDataEntity.setIncrLastFive(addbranName(retailIndexDataMapper.getFinAssIncLastFive()));
            divDataEntity.setBalanceTopFive(addbranName(retailIndexDataMapper.getFinAssBalTopFive()));
            divDataEntity.setBalanceLastFive(addbranName(retailIndexDataMapper.getFinAssBalLastFive()));
            divDataEntity.setBalMapData(addProvinceName(retailIndexDataMapper.getFinAssBal()));
            divDataEntity.setIncMapData(new ArrayList<>());
            List<RetailReport2PO> retailReport2POS = retailIndexDataMapper.getFinAssSumData();

            if (retailReport2POS.size()!=0&&retailReport2POS!=null){
                RetailReport2PO report2POthan = retailReport2POS.get(0);
                if (report2POthan!=null){
                    divDataEntity.setThanMonthBegin(tobill(report2POthan.getThanMonthBegin()));
                    divDataEntity.setThanYearBegin(tobill(report2POthan.getThanYearBegin()));
                    divDataEntity.setBalance(tobill(report2POthan.getBalance()));
                }

            }
            List<RetailReport2PO> kline = retailIndexDataMapper.getFinAssThreeMonKline();
            List<RetailReport2PO> trendList = retailIndexDataMapper.getFinAssThreeMonTrend();
            addTrendDataList(kline,trendList);
            divDataEntity.setKline(kline);

        }catch (Exception e){


            log.info("获取个人金融资产div出错================================"+e.getMessage());
            log.error("获取个人金融资产信息出错=====================",e.getMessage(),e);
            return addnullDiv();


        }

        return divDataEntity;
    }

    //获取非存资产下所有div内容
    public DivDataEntity getNoRetainedAssetsPage(){
        DivDataEntity divDataEntity = new DivDataEntity();
        try{

            divDataEntity.setIncrTopFive(addbranName(retailIndexDataMapper.getNonDepIncTopFive()));
            divDataEntity.setIncrLastFive(addbranName(retailIndexDataMapper.getNonDepIncLastFive()));
            divDataEntity.setBalanceTopFive(addbranName(retailIndexDataMapper.getNonDepBalTopFive()));
            divDataEntity.setBalanceLastFive(addbranName(retailIndexDataMapper.getNonDepBalLastFive()));
            divDataEntity.setBalMapData(addProvinceName(retailIndexDataMapper.getNonDepBal()));
            divDataEntity.setIncMapData(new ArrayList<>());
            List<RetailReport2PO> retailReport2POS = retailIndexDataMapper.getNonDepSumData();

            if (retailReport2POS.size()!=0&&retailReport2POS!=null){
                RetailReport2PO report2POthan = retailReport2POS.get(0);
                if (report2POthan!=null){
                    divDataEntity.setBalance(tobill(report2POthan.getBalance()));
                    divDataEntity.setThanMonthBegin(tobill(report2POthan.getThanMonthBegin()));
                    divDataEntity.setThanYearBegin(tobill(report2POthan.getThanYearBegin()));
                }


            }
            divDataEntity.setKline(retailIndexDataMapper.getNonDepThreeMonKline());
            divDataEntity.setTrendData(retailIndexDataMapper.getNonDepThreeMonTrend());

            List<RetailReport2PO> kline = retailIndexDataMapper.getNonDepThreeMonKline();
            List<RetailReport2PO> trendList = retailIndexDataMapper.getNonDepThreeMonTrend();
            addTrendDataList(kline,trendList);
            divDataEntity.setKline(kline);

        }catch (Exception e){


            log.info("获取个人非存资产div出错================================"+e.getMessage());
            log.error("获取个人非存资产信息出错=====================",e.getMessage(),e);
            return addnullDiv();


        }


        return divDataEntity;
    }





    public static List<RetailReport2PO> addbranName(List<RetailReport2PO> list){

        if (list!=null&&list.size()!=0){
            for (RetailReport2PO retailPO:list) {

                retailPO.setBankName(RetailCustomerViewContext.OrgsCity.get(retailPO.getBankCode()));
            }
        }

        return list;
    }

    public List<RetailReport2PO> addProvinceName(List<RetailReport2PO> list){

        if (list!=null&&list.size()!=0){
//            添加省份名
            for (RetailReport2PO data:list) {
                String bankCode = data.getBankCode();
                data.setProvinceName(RetailCustomerViewContext.codeAndprovinceName.get(bankCode));
            }



            List<RetailReport2PO> copyList = new ArrayList<>();

            for (RetailReport2PO datasource:list) {
                RetailReport2PO add = new RetailReport2PO();
                BeanUtils.copyProperties(datasource,add);
                copyList.add(add);
            }
            //双层循环数据相加
            for (RetailReport2PO data:list){
                String provinceName1 = data.getProvinceName();

                Long dataValue1 = data.getDataValue();
                String bankCode1 = data.getBankCode();
                for (RetailReport2PO data2:copyList){
                    String province2 = data2.getProvinceName();
                    Long dataValue2 = data2.getDataValue();
                    String bankCode2 = data2.getBankCode();
                    if (province2!=null&&!province2.equals("")){
                        if ((province2.equals(provinceName1)&&!bankCode1.equals(bankCode2))){
                            Long returnamt = (dataValue1==null?0:dataValue1)+(dataValue2==null?0:dataValue2);
                            data.setDataValue(returnamt);
                            data.setBankCode(data.getBankCode()+","+data2.getBankCode());

                        }
                    }


                }
            }



        }

        Set<RetailReport2PO> set = new TreeSet<RetailReport2PO>(new Comparator<RetailReport2PO>() {
            @Override
            public int compare(RetailReport2PO a, RetailReport2PO b) {
                // 字符串则按照asicc码升序排列
                return a.getProvinceName().compareTo(b.getProvinceName());
            }
        });

        set.addAll(list);
        return new ArrayList<RetailReport2PO>(set);


    }
//合并趋势图和K线图
    public List<RetailReport2PO> addTrendDataList(List<RetailReport2PO> kline,List<RetailReport2PO> trendList){

        for (RetailReport2PO KlineData:kline){
            Date dateKline = KlineData.getDataDate();
            Long yest = KlineData.getYestdata();
            Long today = KlineData.getTodaydata();
            KlineData.setYestdata(tobill(yest));
            KlineData.setTodaydata(tobill(today));
            for (RetailReport2PO trendData:trendList){
                Date dateTrend = trendData.getDataDate();

                if (dateKline==dateTrend||dateKline.equals(dateTrend)){
                    KlineData.setDataValue(tobill(trendData.getDataValue()));
                }
            }
        }

        return kline;
    }

    public DivDataEntity addnullDiv(){
        DivDataEntity divDataEntity = new DivDataEntity();
        List<RetailReport2PO> list = new ArrayList<>();
        divDataEntity.setIncMapData(list);
        divDataEntity.setKline(list);
        divDataEntity.setBalMapData(list);
        divDataEntity.setBalanceTopFive(list);
        divDataEntity.setBalanceLastFive(list);
        divDataEntity.setIncrLastFive(list);
        divDataEntity.setIncrTopFive(list);
        return divDataEntity;
    }

    public Long tobill(Long amt){
        Long returnAmt = 0L;
        if (amt!=null&&amt!=0){
            returnAmt =amt/1000000 ;
        }
        return returnAmt;
    }

}
