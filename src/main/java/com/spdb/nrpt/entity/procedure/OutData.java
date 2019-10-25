package com.spdb.nrpt.entity.procedure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutData implements Serializable {
    private static final long serialVersionUID = 1L;

    private String STATIS_DT;
    private String ORG_ID;
    private String ORG_NAME;
    private String KPI_ID;
    private String KPI_NAME;
    private String CURRENCY_CD;
    private String KPI_VALUE;
}
