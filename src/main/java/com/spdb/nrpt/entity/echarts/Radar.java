package com.spdb.nrpt.entity.echarts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Radar {
    private String radius;
    private List<Indicator> indicator;
}
