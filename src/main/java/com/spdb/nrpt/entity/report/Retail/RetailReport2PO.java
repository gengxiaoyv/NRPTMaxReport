package com.spdb.nrpt.entity.report.Retail;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//接收数据库返回的值
@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class RetailReport2PO {

    private Double dataValue;

    private String bankName;

    private String bankCode;

    private Double depIn;//存款流入

    private Double depOut;//存款流出

    private Date dataDate;//数据日期

    private Long thanMonthBegin;//比月初
    private Long thanYearBegin;//比年初
    private Long incr;//增量
    private Long balance;//余额

    private Long yestdata;
    private Long todaydata;

    private String provinceName;//省份名







}
