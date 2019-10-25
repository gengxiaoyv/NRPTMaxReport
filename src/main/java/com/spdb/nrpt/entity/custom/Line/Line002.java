package com.spdb.nrpt.entity.custom.Line;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Line002 {
    private boolean smooth;
    private String symbol;
    private Object xAxisData;
}