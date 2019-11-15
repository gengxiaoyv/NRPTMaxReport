package com.spdb.nrpt.service.report.retailreport;

import com.spdb.nrpt.config.DivNameConstants;
import com.spdb.nrpt.config.RetailCustomerViewContext;
import com.spdb.nrpt.entity.report.Retail.DivDataEntity;
import com.spdb.nrpt.entity.report.Retail.PageDataEntity;
import com.spdb.nrpt.entity.report.Retail.RetailReport2PO;
import com.spdb.nrpt.mapper.retailIndexMapper.RetailIndexDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RetailIndexReport2Service {


    @Autowired
    private RetailIndexDataMapper retailIndexDataMapper;


    //存款流入流出  todo
    public List<RetailReport2PO> getDepositInAndFlow(){

        List<RetailReport2PO> report2POList = retailIndexDataMapper.getdepInAndOut();


        return addbranName(report2POList);
    }

    //个人存款 个人贷款   个人金融资产   非存资产等
    public PageDataEntity getScreen2Data(){


        //获取个人存款页签内容
        PageDataEntity pageDataEntity = new PageDataEntity();
        pageDataEntity.setDepDivData(getDepositPage());
        pageDataEntity.setLoanDivData(getLoanPage());
        pageDataEntity.setFinanAsseData(getFinancialAssetsPage());
        pageDataEntity.setNonDepAssData(getNoRetainedAssetsPage());

        return pageDataEntity;

    }


    //获取个人存款下所有div内容
    public DivDataEntity getDepositPage(){


        DivDataEntity divDataEntity = new DivDataEntity();

        divDataEntity.setIncrTopFive(addbranName(retailIndexDataMapper.getdepIncTopFive()));
        divDataEntity.setIncrLastFive(addbranName(retailIndexDataMapper.getdepIncLastFive()));
        divDataEntity.setBalanceTopFive(addbranName(retailIndexDataMapper.getDepBalTopFive()));
        divDataEntity.setBalanceLastFive(addbranName(retailIndexDataMapper.getDepBalLastFive()));
        divDataEntity.setKline(retailIndexDataMapper.getdepThreeMonKline());
        divDataEntity.setTrendData(retailIndexDataMapper.getdepThreeMonTrend());
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



        //地图数据  todo

        return divDataEntity;
    }

    //获取个人贷款下所有div内容
    public DivDataEntity getLoanPage(){

        DivDataEntity divDataEntity = new DivDataEntity();

        divDataEntity.setIncrTopFive(addbranName(retailIndexDataMapper.getLoanIncTopFive()));
        divDataEntity.setIncrLastFive(addbranName(retailIndexDataMapper.getLoanIncLastFive()));
        divDataEntity.setBalanceTopFive(addbranName(retailIndexDataMapper.getLoanBalTopFive()));
        divDataEntity.setBalanceLastFive(addbranName(retailIndexDataMapper.getLoanBalLastFive()));
        divDataEntity.setKline(retailIndexDataMapper.getLoanThreeMonKline());
        divDataEntity.setTrendData(retailIndexDataMapper.getLoanThreeMonTrend());

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


        //地图数据  todo

        return divDataEntity;
    }

    //获取个人金融资产下所有div内容
    public DivDataEntity getFinancialAssetsPage(){

        DivDataEntity divDataEntity = new DivDataEntity();

        divDataEntity.setIncrTopFive(addbranName(retailIndexDataMapper.getFinAssIncTopFive()));
        divDataEntity.setIncrLastFive(addbranName(retailIndexDataMapper.getFinAssIncLastFive()));
        divDataEntity.setBalanceTopFive(addbranName(retailIndexDataMapper.getFinAssBalTopFive()));
        divDataEntity.setBalanceLastFive(addbranName(retailIndexDataMapper.getFinAssBalLastFive()));
        divDataEntity.setKline(retailIndexDataMapper.getFinAssThreeMonKline());
        divDataEntity.setTrendData(retailIndexDataMapper.getFinAssThreeMonTrend());

        List<RetailReport2PO> retailReport2POS = retailIndexDataMapper.getFinAssSumData();

        if (retailReport2POS.size()!=0&&retailReport2POS!=null){
            RetailReport2PO report2POthan = retailReport2POS.get(0);
            if (report2POthan!=null){
                divDataEntity.setThanMonthBegin(report2POthan.getThanMonthBegin());
                divDataEntity.setThanYearBegin(report2POthan.getThanYearBegin());
                divDataEntity.setBalance(report2POthan.getBalance());
            }

        }

        //地图数据  todo

        return divDataEntity;
    }

    //获取非存资产下所有div内容
    public DivDataEntity getNoRetainedAssetsPage(){
        DivDataEntity divDataEntity = new DivDataEntity();

        divDataEntity.setIncrTopFive(addbranName(retailIndexDataMapper.getNonDepIncTopFive()));
        divDataEntity.setIncrLastFive(addbranName(retailIndexDataMapper.getNonDepIncLastFive()));
        divDataEntity.setBalanceTopFive(addbranName(retailIndexDataMapper.getNonDepBalTopFive()));
        divDataEntity.setBalanceLastFive(addbranName(retailIndexDataMapper.getNonDepBalLastFive()));
        divDataEntity.setKline(retailIndexDataMapper.getNonDepThreeMonKline());
        divDataEntity.setTrendData(retailIndexDataMapper.getNonDepThreeMonTrend());


        List<RetailReport2PO> retailReport2POS = retailIndexDataMapper.getNonDepSumData();

        if (retailReport2POS.size()!=0&&retailReport2POS!=null){
            RetailReport2PO report2POthan = retailReport2POS.get(0);
            if (report2POthan!=null){
                divDataEntity.setBalance(report2POthan.getBalance());
                divDataEntity.setThanMonthBegin(report2POthan.getThanMonthBegin());
                divDataEntity.setThanYearBegin(report2POthan.getThanYearBegin());
            }


        }


        //地图数据  todo

        return divDataEntity;
    }





    public static List<RetailReport2PO> addbranName(List<RetailReport2PO> list){

//        if (list!=null&&list.size()!=0){
            for (RetailReport2PO retailPO:list) {

                retailPO.setBankName(RetailCustomerViewContext.OrgsCity.get(retailPO.getBankCode()));
            }
//        }

        return list;
    }


    public static void main(String[] args) {
        List<RetailReport2PO> list=new ArrayList<>();

        addbranName(list);

        System.out.println("111");
        System.out.println(addbranName(list));
    }

}
