package com.spdb.nrpt.entity.echarts.bar;

import com.spdb.nrpt.entity.echarts.Grid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BarEntity {
    private Object legend;
    private Grid grid;
}
