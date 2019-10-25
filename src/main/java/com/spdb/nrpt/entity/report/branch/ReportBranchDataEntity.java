package com.spdb.nrpt.entity.report.branch;

import com.spdb.nrpt.entity.procedure.OutData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//分行实体类
public class ReportBranchDataEntity extends OutData {
    private String ParentID;
}
