package com.spdb.nrpt.entity.custom.Line;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Line003 {
	private boolean smooth;
    private String symbol;
    private Object xAxisData;
    private Object legend;
}
