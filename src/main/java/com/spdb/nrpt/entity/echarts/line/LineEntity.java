package com.spdb.nrpt.entity.echarts.line;

import com.spdb.nrpt.entity.echarts.Grid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineEntity {
    private Object legend;
    private Grid grid;
    private List<LineSeries> series;
}
