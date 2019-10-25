package com.spdb.nrpt.entity.div;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DivEntity {
    private String name;                        //Report-1_信息总览
    private Object data;
    private Object option;
    private Object axis;
}
