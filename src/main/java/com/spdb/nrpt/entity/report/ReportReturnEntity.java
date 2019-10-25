package com.spdb.nrpt.entity.report;

import com.spdb.nrpt.entity.div.DivEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportReturnEntity {

    private String title;

    private String name;

    private List<DivEntity> reportData;        //全部数据

}
