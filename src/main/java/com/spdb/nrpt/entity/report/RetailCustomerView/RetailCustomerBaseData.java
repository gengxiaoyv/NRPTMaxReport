package com.spdb.nrpt.entity.report.RetailCustomerView;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetailCustomerBaseData {

    private String index_name;//指标名
    private Long amt;//数字值
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date data_date;//日期
    private String dims4;//选项名 如12-13岁  r1等级等
    private String org_id;//编号名
    private String org_name;//机构名

    private Double custPercent;//趋势图中占比

    private Long incrThanYearBegin;//较年初净增

    private Long incrThanLastMonthEnd;//较上月末净增

    private Double lossPercent;//流失率

    private Double branPercent;//分行比  卡分层 客户分层等用到

    private Double headPercent;//总行比

    private Double amt_zb;//
    private Long dims5;
    private String provinceName;

    private String cityName;




//    分行地图字段
    private Long branCustomerCount;//分行总客户数

    private Long headBankCustomerCount;//全行总客户数

    private Long branNoZeroCustomer;//分行非零客户数

    private Long headBankNoZeroCustomer;//全行非零客户数

    private Double branNoZeroCustomerPercent;//分行非零客户数占比

    private Double headBankNoZeroCustomerPercent;//全行非零客户数占比

    private Long branAUMAsset;//分行AUM资产规模

    private Double branAUMAssetsPercent;//分行AUM资产规模占比
}
