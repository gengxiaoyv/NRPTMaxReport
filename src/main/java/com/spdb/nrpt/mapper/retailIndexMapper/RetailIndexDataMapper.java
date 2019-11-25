package com.spdb.nrpt.mapper.retailIndexMapper;

import com.spdb.nrpt.entity.report.Retail.RetailReport2PO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface RetailIndexDataMapper {

    //存款流入流出
    List<RetailReport2PO> getdepInAndOut();

    //当日存款增量排名前五
    List<RetailReport2PO> getdepIncTopFive();

    //当日存款增量排名后五
    List<RetailReport2PO> getdepIncLastFive();

    //当日存款余额排名前五
    List<RetailReport2PO> getDepBalTopFive();

    //当日存款余额排名后五
    List<RetailReport2PO> getDepBalLastFive();

    //个人存款余额近三月K线图
    List<RetailReport2PO> getdepThreeMonKline();

    //个人存款年日均余额近三月趋势图
    List<RetailReport2PO> getdepThreeMonTrend();

    //获取个人存款地图数据

    //个人存款增量和
    Long getdepIncSum();

    //个人存款比月初比年初
    List<RetailReport2PO> getdepThan();

    //个人存款余额和
    Long getdepBalSum();



    //当日贷款增量排名前五
    List<RetailReport2PO> getLoanIncTopFive();

    //当日贷款增量排名后五
    List<RetailReport2PO> getLoanIncLastFive();

    //当日贷款余额排名前五
    List<RetailReport2PO> getLoanBalTopFive();

    //当日贷款余额排名后五
    List<RetailReport2PO> getLoanBalLastFive();

    //个人贷款余额近三月K线图
    List<RetailReport2PO> getLoanThreeMonKline();

    //个人贷款年日均余额近三月趋势图
    List<RetailReport2PO> getLoanThreeMonTrend();



    //个人贷款增量和
    Long getLoanIncSum();

    //个人贷款比月初比年初
    List<RetailReport2PO> getLoanThan();

    //个人贷款余额和
    Long getLoanBalSum();


    //个人金融资产增量排名前五
    List<RetailReport2PO> getFinAssIncTopFive();

    //个人金融资产增量排名后五
    List<RetailReport2PO> getFinAssIncLastFive();

    //个人金融资产余额排名前五
    List<RetailReport2PO> getFinAssBalTopFive();

    //个人金融资产余额排名后五
    List<RetailReport2PO> getFinAssBalLastFive();

    //个人金融资产余额近三月K线图
    List<RetailReport2PO> getFinAssThreeMonKline();

    //个人金融资产年日均余额近三月趋势图
    List<RetailReport2PO> getFinAssThreeMonTrend();



    //个人金融资产比月初比年初余额和
    List<RetailReport2PO> getFinAssSumData();



    //非存资产增量排名前五
    List<RetailReport2PO> getNonDepIncTopFive();

    //非存资产增量排名后五
    List<RetailReport2PO> getNonDepIncLastFive();

    //非存资产余额排名前五
    List<RetailReport2PO> getNonDepBalTopFive();

    //非存资产余额排名后五
    List<RetailReport2PO> getNonDepBalLastFive();

    //非存资产余额近三月K线图
    List<RetailReport2PO> getNonDepThreeMonKline();

    //非存资产年日均余额近三月趋势图
    List<RetailReport2PO> getNonDepThreeMonTrend();

    //非存资产比月初比年初余额和

    List<RetailReport2PO> getNonDepSumData();



    List<RetailReport2PO> getdepInc();

    List<RetailReport2PO> getDepBal();

    List<RetailReport2PO> getLoanInc();

    List<RetailReport2PO> getLoanBal();

    List<RetailReport2PO> getFinAssBal();

    List<RetailReport2PO> getNonDepBal();

}
