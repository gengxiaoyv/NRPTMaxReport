package com.spdb.nrpt.service.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.spdb.nrpt.entity.demo.BaseDataBank;
import com.spdb.nrpt.entity.demo.DemoReq;
import com.spdb.nrpt.entity.demo.DemoVO;
import com.spdb.nrpt.mapper.demoMapper.DemoMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DemoService {
	@Autowired
	private DemoMapper demoMapper;
	
	public List<DemoVO> queryDemoVOByApply_value(String apply_value){
		
		List<DemoVO> demoVOList = demoMapper.queryDemoVOByApply_value(apply_value);
		
		return demoVOList;
	}
	
	public boolean addDemoInfo(DemoReq demoReq) {
		return demoMapper.addDemoInfo(demoReq);
	}
	
	/*
	 * 根据value查询base_data_bank
	 * */
	public BaseDataBank queryBaseDataByValue(String type,String value) {
		return demoMapper.queryBaseDataByValue(type,value);
	}
	
	public void addBaseData(String type,String value) {
		demoMapper.addBaseData(type,value);
	}
	
	public List<BaseDataBank> queryBaseDataByType(String type){
		return demoMapper.queryBaseDataByType(type);
	}
	
	public List<DemoVO> queryDemoVO(Integer page,Integer limit,String handler_status,String plate_manage_name,String handler_name,String apply_deptid,String apply_plate,String apply_time,String apply_time2,String deliver_time,String deliver_time2){
		PageHelper.startPage(page, limit);
		return demoMapper.queryDemoVO(handler_status,plate_manage_name,handler_name,apply_deptid,apply_plate,apply_time,apply_time2,deliver_time,deliver_time2);
	}
	
	public boolean deleteInfoById(String id) {
		return demoMapper.deleteInfoById(id);
	}
	
	public DemoVO queryOneById(String id) {
		return demoMapper.queryOneById(id);
	}
	
	public boolean editInfoById(DemoVO demoVO) {
		return demoMapper.editInfoById(demoVO);
	}
	
	public List<DemoVO> queryAllDemoVO(String handler_status){
		return demoMapper.queryAllDemoVO(handler_status);
	}
}
