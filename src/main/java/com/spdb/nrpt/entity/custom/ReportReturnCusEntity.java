package com.spdb.nrpt.entity.custom;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportReturnCusEntity {
	
	public String title;
	public String name;
	public List<CustomEntity> reportData;
	
}
