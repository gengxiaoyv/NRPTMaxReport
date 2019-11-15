package com.spdb.nrpt.entity.report.Retail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//接收数据库返回的值
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetailReport2PO {

    private Double dataValue;

    private String bankName;

    private String bankCode;

    private Double depIn;//存款流入

    private Double depOut;//存款流出

    private Date dataDate;//数据日期

    private Double thanMonthBegin;//比月初
    private Double thanYearBegin;//比年初
    private Double incr;//增量
    private Double balance;//余额

    private Double yestdata;
    private Double todaydata;







}
