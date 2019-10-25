package com.spdb.nrpt.entity.Unit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportUnit {

    private String divID;
    private String orgID;
    private Integer unitValue;

}
