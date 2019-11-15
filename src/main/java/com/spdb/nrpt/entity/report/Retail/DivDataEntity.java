package com.spdb.nrpt.entity.report.Retail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DivDataEntity {
    private List<RetailReport2PO> incrTopFive;//增量排名前五

    private List<RetailReport2PO> incrLastFive;//增量排名后五

    private List<RetailReport2PO> balanceTopFive;//余额排名前五

    private List<RetailReport2PO> balanceLastFive;//余额排名后五

    private List<RetailReport2PO> Kline;//K线图

    private List<RetailReport2PO> trendData;//趋势图

    private Double balance;//存款余额

    private Double depOrLoanIncr;//存款增量

    private Double thanMonthBegin;//较月初

    private Double thanYearBegin;//较年初

    private List<RetailReport2PO> mapData;//地图数据




}
