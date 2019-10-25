package com.spdb.nrpt.mapper.demoMapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.spdb.nrpt.entity.demo.BaseDataBank;
import com.spdb.nrpt.entity.demo.DemoReq;
import com.spdb.nrpt.entity.demo.DemoVO;

@Mapper
@Component
public interface DemoMapper {

	public List<DemoVO> queryDemoVOByApply_value(@Param("apply_value") String Apply_value);
	
	public boolean addDemoInfo(DemoReq demoReq);
	
	public List<DemoVO> queryDemoVO(@Param("handler_status") String handler_status,@Param("plate_manage_name") String plate_manage_name,@Param("handler_name") String handler_name,@Param("apply_deptid")String apply_deptid,@Param("apply_plate")String apply_plate,@Param("apply_time")String apply_time,@Param("apply_time2")String apply_time2,@Param("deliver_time")String deliver_time,@Param("deliver_time2")String deliver_time2);
	
	public boolean deleteInfoById(String id);
	
	public DemoVO queryOneById(String id);
	
	public boolean editInfoById(DemoVO demoVO);
	
	public BaseDataBank queryBaseDataByValue(@Param("data_type") String type,@Param("data_value") String value);
	
	public void addBaseData(@Param("data_type") String type,@Param("data_value") String value);
	
	public List<BaseDataBank> queryBaseDataByType(@Param("data_type") String type);
	
	public List<DemoVO> queryAllDemoVO(@Param("handler_status") String handler_status);
}
