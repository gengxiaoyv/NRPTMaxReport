package com.spdb.nrpt.mapper.backstageMapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.spdb.nrpt.entity.custom.CusReturnData;

@Component
@Mapper
public interface BackstageReturnMapper {

	//根据传入的指标key和orgid得到指标的值
	public CusReturnData queryKPI_ValueAndKPI_Name(@Param("key")String key,@Param("orgID")String orgID);
	
}
