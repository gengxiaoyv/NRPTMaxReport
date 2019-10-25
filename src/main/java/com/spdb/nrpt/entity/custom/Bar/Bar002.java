package com.spdb.nrpt.entity.custom.Bar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//堆叠柱状图,今日，上日，上月
public class Bar002 {
	private Object legend;
	private Object xData;
	private Object stack;
}
