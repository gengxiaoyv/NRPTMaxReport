package com.spdb.nrpt.entity.custom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CusReturnData {

	private String kpiId;
	private String kpiName;
	private String orgId;
	private String orgName;
	private String kpiValue;
	
}
