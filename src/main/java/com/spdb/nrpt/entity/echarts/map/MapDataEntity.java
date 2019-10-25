package com.spdb.nrpt.entity.echarts.map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapDataEntity {

    private String name;
    private String bank;
    private String value;
    private boolean selected;
}
