package com.spdb.nrpt.entity.custom.Echarts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//表Report_CustomEchartsTable的实体类
public class CustomEchartsEntity {

    private String echartsID;
    private String echartsName;
    private String echartsJson;
    private String echartsClass;

}
