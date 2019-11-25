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

    private Long balance;//存款余额

    private Long depOrLoanIncr;//存款/贷款增量

    private Long thanMonthBegin;//较月初

    private Long thanYearBegin;//较年初

    private List<RetailReport2PO> balMapData;//余额地图数据

    private List<RetailReport2PO> incMapData;//增量地图数据   只有个人存款跟个人贷款有




}
