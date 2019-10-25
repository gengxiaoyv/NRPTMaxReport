package com.spdb.nrpt.entity.echarts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grid {
    private String left;
    private String right;
    private String top;
    private String bottom;
    private String width;
    private String height;
}
