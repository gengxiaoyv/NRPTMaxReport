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

import java.util.*;

@Service
@Slf4j
public class RetailIndexReport2Service {


    @Autowired
    private RetailIndexDataMapper retailIndexDataMapper;


    //存款流入流出  todo
    public List<RetailReport2PO> getDepositInAndFlow(){

        List<RetailReport2PO> report2POList = retailIndexDataMapper.getdepInAndOut();


        return addbranName(report2POList);
    }

    //个人存款 个人贷款   个人金融资产   非存资产等
    public List<PageDataEntity> getScreen2Data(){

        List<PageDataEntity> pageDataEntityList = new ArrayList<>();

        PageDataEntity pageDataEntityFin = new PageDataEntity();
        pageDataEntityFin.setDivdata(getFinancialAssetsPage());
        pageDataEntityFin.setPageName("个人金融资产");
        pageDataEntityFin.setPageDescription("T+1");
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
        pageDataEntityLoan.setDivdata(getNoRetainedAssetsPage());
        pageDataEntityLoan.setPageName("非存资产");
        pageDataEntityLoan.setPageDescription("（不含汇理财）T+1");
        pageDataEntityList.add(pageDataEntityLoan);


        return pageDataEntityList;

    }


    //获取个人存款下所有div内容
    public DivDataEntity getDepositPage(){


        DivDataEntity divDataEntity = new DivDataEntity();

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
                divDataEntity.setThanMonthBegin(report2POthan.getThanMonthBegin());
                divDataEntity.setThanYearBegin(report2POthan.getThanYearBegin());
            }

        }
        divDataEntity.setDepOrLoanIncr(retailIndexDataMapper.getdepIncSum());
        divDataEntity.setBalance(retailIndexDataMapper.getdepBalSum());



        //组合K线图和趋势图
        List<RetailReport2PO> KlineList = retailIndexDataMapper.getdepThreeMonKline();
        List<RetailReport2PO> trendList = retailIndexDataMapper.getdepThreeMonTrend();


        //地图数据  todo
        addTrendDataList(KlineList,trendList);
        divDataEntity.setKline(KlineList);
        return divDataEntity;
    }

    //获取个人贷款下所有div内容
    public DivDataEntity getLoanPage(){

        DivDataEntity divDataEntity = new DivDataEntity();

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
                divDataEntity.setThanMonthBegin(report2POthan.getThanMonthBegin());
                divDataEntity.setThanYearBegin(report2POthan.getThanYearBegin());
            }

        }
        divDataEntity.setDepOrLoanIncr(retailIndexDataMapper.getLoanIncSum());
        divDataEntity.setBalance(retailIndexDataMapper.getLoanBalSum());

        List<RetailReport2PO> KlineList = retailIndexDataMapper.getLoanThreeMonKline();
        List<RetailReport2PO> trendList = retailIndexDataMapper.getLoanThreeMonTrend();

        addTrendDataList(KlineList,trendList);
        divDataEntity.setKline(KlineList);

        return divDataEntity;
    }

    //获取个人金融资产下所有div内容
    public DivDataEntity getFinancialAssetsPage(){

        DivDataEntity divDataEntity = new DivDataEntity();

        divDataEntity.setIncrTopFive(addbranName(retailIndexDataMapper.getFinAssIncTopFive()));
        divDataEntity.setIncrLastFive(addbranName(retailIndexDataMapper.getFinAssIncLastFive()));
        divDataEntity.setBalanceTopFive(addbranName(retailIndexDataMapper.getFinAssBalTopFive()));
        divDataEntity.setBalanceLastFive(addbranName(retailIndexDataMapper.getFinAssBalLastFive()));
        divDataEntity.setBalMapData(addProvinceName(retailIndexDataMapper.getFinAssBal()));

        List<RetailReport2PO> retailReport2POS = retailIndexDataMapper.getFinAssSumData();

        if (retailReport2POS.size()!=0&&retailReport2POS!=null){
            RetailReport2PO report2POthan = retailReport2POS.get(0);
            if (report2POthan!=null){
                divDataEntity.setThanMonthBegin(report2POthan.getThanMonthBegin());
                divDataEntity.setThanYearBegin(report2POthan.getThanYearBegin());
                divDataEntity.setBalance(report2POthan.getBalance());
            }

        }
        List<RetailReport2PO> kline = retailIndexDataMapper.getFinAssThreeMonKline();
        List<RetailReport2PO> trendList = retailIndexDataMapper.getFinAssThreeMonTrend();
        addTrendDataList(kline,trendList);
        divDataEntity.setKline(kline);
        return divDataEntity;
    }

    //获取非存资产下所有div内容
    public DivDataEntity getNoRetainedAssetsPage(){
        DivDataEntity divDataEntity = new DivDataEntity();


        divDataEntity.setIncrTopFive(addbranName(retailIndexDataMapper.getNonDepIncTopFive()));
        divDataEntity.setIncrLastFive(addbranName(retailIndexDataMapper.getNonDepIncLastFive()));
        divDataEntity.setBalanceTopFive(addbranName(retailIndexDataMapper.getNonDepBalTopFive()));
        divDataEntity.setBalanceLastFive(addbranName(retailIndexDataMapper.getNonDepBalLastFive()));
        divDataEntity.setBalMapData(addProvinceName(retailIndexDataMapper.getNonDepBal()));

        List<RetailReport2PO> retailReport2POS = retailIndexDataMapper.getNonDepSumData();

        if (retailReport2POS.size()!=0&&retailReport2POS!=null){
            RetailReport2PO report2POthan = retailReport2POS.get(0);
            if (report2POthan!=null){
                divDataEntity.setBalance(report2POthan.getBalance());
                divDataEntity.setThanMonthBegin(report2POthan.getThanMonthBegin());
                divDataEntity.setThanYearBegin(report2POthan.getThanYearBegin());
            }


        }
        divDataEntity.setKline(retailIndexDataMapper.getNonDepThreeMonKline());
        divDataEntity.setTrendData(retailIndexDataMapper.getNonDepThreeMonTrend());

        List<RetailReport2PO> kline = retailIndexDataMapper.getNonDepThreeMonKline();
        List<RetailReport2PO> trendList = retailIndexDataMapper.getNonDepThreeMonTrend();
        addTrendDataList(kline,trendList);
        divDataEntity.setKline(kline);
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

                Double dataValue1 = data.getDataValue();
                String bankCode1 = data.getBankCode();
                for (RetailReport2PO data2:copyList){
                    String province2 = data2.getProvinceName();
                    Double dataValue2 = data2.getDataValue();
                    String bankCode2 = data2.getBankCode();
                    if ((province2.equals(provinceName1)&&!bankCode1.equals(bankCode2))){
                        Double returnamt = (dataValue1==null?0:dataValue1)+(dataValue2==null?0:dataValue2);
                        data.setDataValue(returnamt);
                        data.setBankCode(data.getBankCode()+","+data2.getBankCode());

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

    public List<RetailReport2PO> addTrendDataList(List<RetailReport2PO> kline,List<RetailReport2PO> trendList){

        for (RetailReport2PO KlineData:kline){
            Date dateKline = KlineData.getDataDate();
            for (RetailReport2PO trendData:trendList){
                Date dateTrend = trendData.getDataDate();
                if (dateKline==dateTrend||dateKline.equals(dateTrend)){
                    KlineData.setDataValue(trendData.getDataValue());
                }
            }
        }

        return kline;
    }


}
