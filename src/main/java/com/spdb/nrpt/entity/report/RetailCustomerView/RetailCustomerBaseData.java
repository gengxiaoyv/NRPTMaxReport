package com.spdb.nrpt.entity.report.RetailCustomerView;

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
    private Date data_date;//日期
    private String dims4;//选项名 如12-13岁  r1等级等
    private String org_id;//编号名
    private String org_name;//机构名

    private Double custPercent;//趋势图中占比

    private Long incrThanYearBegin;//较年初净增

    private Long incrThanLastMonthEnd;//较上月末净增

    private Double lossPercent;//流失率

    private Double branPercent;//分行比

    private Double headPercent;//总行比

    private Double amt_zb;//
    private Long dims5;
    private String provinceName;

    private String cityName;

}
