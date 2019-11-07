package com.spdb.nrpt.entity.report.RetailCustomerView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetailCustomerViewVO {

    private String customerType;// 当年新增客户   存量客户

    private String byDayOrMonth;//按日或月

    private Date date;//若按日则传日期

    private String month;//按月则传月份

    private String orgId;//一级分行编号

    private String trendCustType;//趋势图中客户类型

    private String dateString;//日期字符串

}
