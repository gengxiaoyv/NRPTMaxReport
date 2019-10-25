package com.spdb.nrpt.entity.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportEchartsEntity {
    private String divID;
    private String divName;
    private String divEchartsJson;
    private String divEchartsType;
}
