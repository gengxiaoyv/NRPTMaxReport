package com.spdb.nrpt.entity.report.RetailCustomerView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetailCustomResponseData {


    private List<RetailCustomerBaseData> retailCustomerBaseDataList;

    private Double custPercent;//趋势图中占比

    private Long incrThanYearBegin;//较年初净增

    private Long incrThanLastMonthEnd;//较上月末净增

    private Long yearDatLine;//年初私行基准线

    private Long monthDatLine;//月基准线

    private Long branCustomerCount;//分行总客户数

    private Long headBankCustomerCount;//全行总客户数

    private Long branNoZeroCustomer;//分行非零客户数

    private Long headBankNoZeroCustomer;//全行非零客户数

    private Double branNoZeroCustomerPercent;//分行非零客户数占比

    private Double headBankNoZeroCustomerPercent;//全行非零客户数占比

    private Long branAUMAsset;//分行AUM资产规模

    private Double branAUMAssetsPercent;//分行AUM资产规模占比

    private String dims4;//趋势图中表示客户类型

    private String orgName;//分行名称

    private String provinceName;//省份

    private Long cardSliceSum;//卡分层信息amt

    private String dims5;//卡分层信息名称

    private Long amt;





}
