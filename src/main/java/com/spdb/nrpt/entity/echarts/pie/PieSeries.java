package com.spdb.nrpt.entity.echarts.pie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PieSeries {
    private List<String> radius;
}
