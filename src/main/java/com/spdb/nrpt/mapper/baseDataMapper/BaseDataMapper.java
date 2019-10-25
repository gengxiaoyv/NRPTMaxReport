package com.spdb.nrpt.mapper.baseDataMapper;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.spdb.nrpt.entity.basedata.BaseDataEntity;

@Mapper
@Component
public interface BaseDataMapper {
	
	//根据ID查询基础数据
	BaseDataEntity selectAllReportBaseDataTableByID(String id);
	
	//根据数据名称查询基础数据
	BaseDataEntity selectAllReportBaseDataTableByDataName(String dataName);
	
	//根据ID更新dataValue值
	void updateReportBaseDataTableByID(String id,String dataValue);
	
	//插入Report_BaseDataTable值
	void insertReportBaseDataTable(BaseDataEntity data);
}
