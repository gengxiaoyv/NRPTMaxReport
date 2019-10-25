package com.spdb.nrpt.entity.basedata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//表Report_BaseDataTable实体类
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDataEntity {
	private String id;
	private String dataName;
	private String dataGroup;
	private String dataValue;
}
