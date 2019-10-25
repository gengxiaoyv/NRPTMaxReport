package com.spdb.nrpt.entity.custom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomEntity {

    private String divID;
    private String EchartsType;
    private Object EchartsData;
    private Object reportData;

}
