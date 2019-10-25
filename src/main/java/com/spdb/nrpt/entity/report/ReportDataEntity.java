package com.spdb.nrpt.entity.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportDataEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date STATIS_DT;         //日期
    private String ORG_ID;          //机构编号
    private String ORG_NAME;        //机构名称
    private String KPI_ID;          //指标编号
    private String KPI_NAME;        //指标名称
    private String CURRENCY_CD;     //币种
    private Double KRI_VALUE;       //指标值

}
