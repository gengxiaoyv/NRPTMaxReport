package com.spdb.nrpt.controller.demo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.spdb.nrpt.entity.demo.BaseDataBank;
import com.spdb.nrpt.entity.demo.DemoReq;
import com.spdb.nrpt.entity.demo.DemoVO;
import com.spdb.nrpt.service.demo.DemoService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/demo")
public class DemoController {

	@Autowired
	private DemoService demoService;
	
	/*
	 * 根据传入的
	 * */
	@RequestMapping("/showApplyId")
	@ResponseBody
	public Map<String,String> showApplyId(String apply_value){
		String contact_id = "";
		Map<String,String> result ;
		List<DemoVO> DemoVOList = demoService.queryDemoVOByApply_value(apply_value);
		if(DemoVOList.isEmpty()) {
			if("联系单".equals(apply_value)) {
				contact_id = "LX0000001";
			}else if("联合团队".equals(apply_value)) {
				contact_id = "LH0000001";
			}
		}else {
			String pre_contact_id = DemoVOList.get(0).getContact_list_id();
			String sub_contact_id = pre_contact_id.substring(2);
			String sub_contact_prefix = pre_contact_id.substring(0, 2);
			DecimalFormat decimalFormat = new DecimalFormat("0000000");
			contact_id = sub_contact_prefix+decimalFormat.format(Long.parseLong(sub_contact_id)+1).toString();
		}
		result = new HashMap<String,String>();
		result.put("contact_id",contact_id);
		return result;
	}
	
	/*
	 * 新增记录
	 * */
	@RequestMapping("/addDemoInfo")
	@ResponseBody
	public Map<String,String> addDemoInfo(DemoReq demoReq){
		log.info("传入的数据是：{}",demoReq.toString());
		boolean flag = demoService.addDemoInfo(demoReq);
		Map<String,String> result = new HashMap<String,String>();
		if(flag) {
			result.put("msg", "success");
		}else {
			result.put("msg", "fail");
		}
		
		//将板块负责人，处理人存入base_data_bank表
		String plate_manage_name = demoReq.getPlate_manage_name();
		String handler_name = demoReq.getHandler_name();
		String apply_deptid = demoReq.getApply_deptid();
		String apply_plate = demoReq.getApply_plate();
				
		//根据type和value值，判断数据库中是否存在相对应的人
		BaseDataBank baseDataBank_1 = demoService.queryBaseDataByValue("板块负责人",plate_manage_name);
		BaseDataBank baseDataBank_2 = demoService.queryBaseDataByValue("处理人",handler_name);
		BaseDataBank baseDataBank_3 = demoService.queryBaseDataByValue("需求部门",apply_deptid);
		BaseDataBank baseDataBank_4 = demoService.queryBaseDataByValue("所属板块",apply_plate);
		
		if(baseDataBank_1 == null) {
			demoService.addBaseData("板块负责人",plate_manage_name);
		}
		if(baseDataBank_2 == null) {
			demoService.addBaseData("处理人",handler_name);
		}
		if(baseDataBank_3 == null) {
			demoService.addBaseData("需求部门",apply_deptid);
		}
		if(baseDataBank_4 == null) {
			demoService.addBaseData("所属板块",apply_plate);
		}
		
		return result;
	}
	
	/*
	 * 查询模块负责人
	 * */
	@RequestMapping("/showPlate_manage_name")
	@ResponseBody
	public List<BaseDataBank> showPlate_manage_name(){
		List<BaseDataBank> baseDataBankList =  demoService.queryBaseDataByType("板块负责人");
		return baseDataBankList;
	}
	
	/*
	 * 查询处理人
	 * */
	@RequestMapping("/showHandler_name")
	@ResponseBody
	public List<BaseDataBank> showHandler_name(){
		List<BaseDataBank> baseDataBankList =  demoService.queryBaseDataByType("处理人");
		return baseDataBankList;
	}
	
	/*
	 * 查询模块
	 * */
	@RequestMapping("/showApplyPlate")
	@ResponseBody
	public List<BaseDataBank> showApplyPlate(){
		List<BaseDataBank> baseDataBankList =  demoService.queryBaseDataByType("所属板块");
		return baseDataBankList;
	}
	
	/*
	 * 得到需求部门
	 * */
	@RequestMapping("/showApplyDeptid")
	@ResponseBody
	public List<BaseDataBank> showApplyDeptid(){
		List<BaseDataBank> baseDataBankList =  demoService.queryBaseDataByType("需求部门");
		return baseDataBankList;
	}
	
	
	/*
	 * 模糊分页查询
	 * */
	@RequestMapping("/showBankInfo")
	@ResponseBody
	public Map<String,Object> queryDemoInfo(Integer page,Integer limit,String handler_status,String plate_manage_name,String handler_name,String apply_deptid,String apply_plate,String apply_time,String deliver_time){
		log.info("status : "+handler_status+" manage_name : "+plate_manage_name+" handler_name : "+handler_name);
		
		String apply_time2 = apply_time;
		String deliver_time2 = deliver_time;
		
		if(!"".equals(apply_time) && apply_time != null) {
			String[] times = apply_time.split("/");
			if( "0".equals(times[1].substring(0,1))) {
				times[1] = times[1].substring(1,2);
			}
			if("0".equals(times[2].substring(0,1))) {
				times[2] = times[2].substring(1,2);
			}
			apply_time2 = times[0]+"/"+times[1]+"/"+times[2];
		}
		
		if(!"".equals(deliver_time) && deliver_time != null) {
			String[] times = deliver_time.split("/");
			if( "0".equals(times[1].substring(0,1))) {
				times[1] = times[1].substring(1,2);
			}
			if("0".equals(times[2].substring(0,1))) {
				times[2] = times[2].substring(1,2);
			}
			deliver_time2 = times[0]+"/"+times[1]+"/"+times[2];
		}
		log.info(deliver_time);
		log.info(deliver_time2);
		List<DemoVO> DemoVOList = demoService.queryDemoVO(page,limit,handler_status,plate_manage_name,handler_name,apply_deptid,apply_plate,apply_time,apply_time2,deliver_time,deliver_time2);
		PageInfo info = new PageInfo(DemoVOList);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("data",DemoVOList);
		result.put("count",info.getTotal());
		result.put("code",0);
		result.put("msg","");
		return result;
	}
	
	/*
	 * 根据ID删除
	 * */
	@RequestMapping("/deleteInfo")
	@ResponseBody
	public Map<String,String> deleteInfoById(String id){
		Map<String,String> result = new HashMap<String,String>();
		log.info("传进来的id为{}",id);
		
		boolean flag = demoService.deleteInfoById(id);
		if(flag) {
			result.put("msg","success");
		}else {
			result.put("msg","fail");
		}
		
		return result;
	}
	
	/*
	 * 根据id跟新修改INFO
	 * */
	@RequestMapping("/editBankInfo")
	@ResponseBody
	public Map<String,Object> editBankInfo(DemoVO demoVO){
		//log.info("传进来的数据为{} ",demoVO);
		Map<String,Object> result = new HashMap<String,Object>();
		boolean flag = demoService.editInfoById(demoVO);
		if(flag) {
			result.put("msg", "success");
		}else {
			result.put("msg", "fail");
		}
		return result;
	}
	
	/*
	 * 批量导出
	 * */
	@RequestMapping("/exportToExcel")
	public void exportToExcel(HttpServletResponse response,String handler_status){
		ByteArrayInputStream bais = null;
		OutputStream os = null;
		//log.info(handler_status);
		try{
			//查询出所有数据
			List<DemoVO> DemoVOList = demoService.queryAllDemoVO(handler_status);
			log.info(DemoVOList.size()+"");
			//创建workbook
			XSSFWorkbook wb = new XSSFWorkbook();
			//创建sheet
			Sheet sheet = wb.createSheet("Info");
			//创建首行
			Row row_0 = sheet.createRow(0);
			//给首行赋值
			row_0.createCell(0).setCellValue("申报时间");
			row_0.createCell(1).setCellValue("申请部门");
			row_0.createCell(2).setCellValue("所属板块");
			row_0.createCell(3).setCellValue("板块负责人");
			row_0.createCell(4).setCellValue("业务");
			row_0.createCell(5).setCellValue("申请渠道");
			row_0.createCell(6).setCellValue("联系单编号");
			row_0.createCell(7).setCellValue("联系单名称");
			row_0.createCell(8).setCellValue("报表（模型）名称");
			row_0.createCell(9).setCellValue("需求新增/修改");
			row_0.createCell(10).setCellValue("实现形式");
			row_0.createCell(11).setCellValue("处理人");
			row_0.createCell(12).setCellValue("处理状态");
			row_0.createCell(13).setCellValue("投产路径");
			row_0.createCell(14).setCellValue("需求口径确认时间");
			row_0.createCell(15).setCellValue("回复实施计划时间");
			row_0.createCell(16).setCellValue("需求回复SLA");
			row_0.createCell(17).setCellValue("预计首次提交数据探查/首次提交业务测试时间");
			row_0.createCell(18).setCellValue("预计开始数据探查时间");
			row_0.createCell(19).setCellValue("实际首次提交数据探查/首次提交业务测试时间");
			row_0.createCell(20).setCellValue("实际开始数据探查时间");
			row_0.createCell(21).setCellValue("数据探查SLA");
			row_0.createCell(22).setCellValue("灰色发布完成时间");
			row_0.createCell(23).setCellValue("灰色发布SLA");
			row_0.createCell(24).setCellValue("提供上线确认书/测试报告日期");
			row_0.createCell(25).setCellValue("投产交付日期");
			row_0.createCell(26).setCellValue("投产交付SLA");
			//循环遍历查出来的数据集合
			for(int i = 0;i < DemoVOList.size();i++) {
				DemoVO demoVO = DemoVOList.get(i);
				Row row_i = sheet.createRow(i+1);
				if(demoVO.getApply_time() == null || "".equals(demoVO.getApply_time())) {
					row_i.createCell(0).setCellValue("");
				}else {
					row_i.createCell(0).setCellValue(demoVO.getApply_time().toString());
				}
				if(demoVO.getApply_deptid() == null || "".equals(demoVO.getApply_deptid())) {
					row_i.createCell(1).setCellValue("");	
				}else {
					row_i.createCell(1).setCellValue(demoVO.getApply_deptid().toString());	
				}
				if(demoVO.getApply_plate() == null || "".equals(demoVO.getApply_plate())) {
					row_i.createCell(2).setCellValue("");	
				}else {
					row_i.createCell(2).setCellValue(demoVO.getApply_plate().toString());	
				}
				if(demoVO.getPlate_manage_name() == null || "".equals(demoVO.getPlate_manage_name())) {
					row_i.createCell(3).setCellValue("");	
				}else {
					row_i.createCell(3).setCellValue(demoVO.getPlate_manage_name().toString());	
				}
				if(demoVO.getBusiness_name() == null || "".equals(demoVO.getBusiness_name())) {
					row_i.createCell(4).setCellValue("");	
				}else {
					row_i.createCell(4).setCellValue(demoVO.getBusiness_name().toString());	
				}
				if(demoVO.getApply_channel() == null || "".equals(demoVO.getApply_channel())) {
					row_i.createCell(5).setCellValue("");	
				}else {
					row_i.createCell(5).setCellValue(demoVO.getApply_channel().toString());	
				}
				if(demoVO.getContact_list_id() == null || "".equals(demoVO.getContact_list_id())) {
					row_i.createCell(6).setCellValue("");	
				}else {
					row_i.createCell(6).setCellValue(demoVO.getContact_list_id().toString());	
				}
				if(demoVO.getContact_list_name() == null || "".equals(demoVO.getContact_list_name())) {
					row_i.createCell(7).setCellValue("");	
				}else {
					row_i.createCell(7).setCellValue(demoVO.getContact_list_name().toString());	
				}
				if(demoVO.getReport_name() == null || "".equals(demoVO.getReport_name())) {
					row_i.createCell(8).setCellValue("");	
				}else {
					row_i.createCell(8).setCellValue(demoVO.getReport_name().toString());	
				}
				if(demoVO.getRequirement_type() == null || "".equals(demoVO.getRequirement_type())) {
					row_i.createCell(9).setCellValue("");	
				}else {
					row_i.createCell(9).setCellValue(demoVO.getRequirement_type().toString());	
				}
				if(demoVO.getRealization() == null || "".equals(demoVO.getRealization())) {
					row_i.createCell(10).setCellValue("");	
				}else {
					row_i.createCell(10).setCellValue(demoVO.getRealization().toString());	
				}
				if(demoVO.getHandler_name() == null || "".equals(demoVO.getHandler_name())) {
					row_i.createCell(11).setCellValue("");	
				}else {
					row_i.createCell(11).setCellValue(demoVO.getHandler_name().toString());	
				}
				if(demoVO.getHandler_status() == null || "".equals(demoVO.getHandler_status())) {
					row_i.createCell(12).setCellValue("");	
				}else {
					row_i.createCell(12).setCellValue(demoVO.getHandler_status());	
				}
				if(demoVO.getProduction_way() == null || "".equals(demoVO.getProduction_way())) {
					row_i.createCell(13).setCellValue("");	
				}else {
					row_i.createCell(13).setCellValue(demoVO.getProduction_way());	
				}
				if(demoVO.getDemand_time() == null || "".equals(demoVO.getDemand_time())) {
					row_i.createCell(14).setCellValue("");	
				}else {
					row_i.createCell(14).setCellValue(demoVO.getDemand_time());	
				}
				if(demoVO.getImplementation_p_time() == null || "".equals(demoVO.getImplementation_p_time())) {
					row_i.createCell(15).setCellValue("");	
				}else {
					row_i.createCell(15).setCellValue(demoVO.getImplementation_p_time());	
				}
				if(demoVO.getDemand_SLA() == null || "".equals(demoVO.getDemand_SLA())) {
					row_i.createCell(16).setCellValue("");	
				}else {
					row_i.createCell(16).setCellValue(demoVO.getDemand_SLA());	
				}
				if(demoVO.getEst_fir_submit_time() == null || "".equals(demoVO.getEst_fir_submit_time())) {
					row_i.createCell(17).setCellValue("");	
				}else {
					row_i.createCell(17).setCellValue(demoVO.getEst_fir_submit_time());	
				}
				if(demoVO.getEst_beg_time() == null || "".equals(demoVO.getEst_beg_time())) {
					row_i.createCell(18).setCellValue("");	
				}else {
					row_i.createCell(18).setCellValue(demoVO.getEst_beg_time());	
				}
				if(demoVO.getFir_submit_time() == null || "".equals(demoVO.getFir_submit_time())) {
					row_i.createCell(19).setCellValue("");	
				}else {
					row_i.createCell(19).setCellValue(demoVO.getFir_submit_time());	
				}
				if(demoVO.getActual_beg_search_time() == null || "".equals(demoVO.getActual_beg_search_time())) {
					row_i.createCell(20).setCellValue("");	
				}else {
					row_i.createCell(20).setCellValue(demoVO.getActual_beg_search_time());	
				}
				if(demoVO.getSearch_SLA() == null || "".equals(demoVO.getSearch_SLA())) {
					row_i.createCell(21).setCellValue("");	
				}else {
					row_i.createCell(21).setCellValue(demoVO.getSearch_SLA());	
				}
				if(demoVO.getGray_finish_time() == null || "".equals(demoVO.getGray_finish_time())) {
					row_i.createCell(22).setCellValue("");	
				}else {
					row_i.createCell(22).setCellValue(demoVO.getGray_finish_time());	
				}
				if(demoVO.getGray_SLA() == null || "".equals(demoVO.getGray_SLA())) {
					row_i.createCell(23).setCellValue("");	
				}else {
					row_i.createCell(23).setCellValue(demoVO.getGray_SLA());	
				}
				if(demoVO.getImplementation_time() == null || "".equals(demoVO.getImplementation_time())) {
					row_i.createCell(24).setCellValue("");	
				}else {
					row_i.createCell(24).setCellValue(demoVO.getImplementation_time());	
				}
				if(demoVO.getDeliver_time() == null || "".equals(demoVO.getDeliver_time())) {
					row_i.createCell(25).setCellValue("");	
				}else {
					row_i.createCell(25).setCellValue(demoVO.getDeliver_time());	
				}
				if(demoVO.getDeliver_SLA() == null || "".equals(demoVO.getDeliver_SLA())) {
					row_i.createCell(26).setCellValue("");	
				}else {
					row_i.createCell(26).setCellValue(demoVO.getDeliver_SLA());	
				}
			}
			
			  response.reset(); 
			  ByteArrayOutputStream baos = new ByteArrayOutputStream();
			  wb.write(baos);
			  response.setContentType("application/x-download;charset=utf-8"); 
			  String excelName = new String("Info".getBytes("GB2312"),"ISO8859-1")+".xlsx";
			  response.addHeader("Content-Disposition", "attachment;filename="+excelName);
			  os = response.getOutputStream(); 
			  bais = new ByteArrayInputStream(baos.toByteArray()); 
			  byte[] b = new byte[1024];
			  while((bais.read(b)) > 0) { 
				  os.write(b); 
			  } 
			  bais.close(); 
			  os.flush();
			  os.close();
			//-----------
			/*
			 * response.reset(); response.setHeader("Content-Disposition",
			 * "attachment;filename="+URLEncoder.encode("Info", "utf-8")+".xlsx"); os =
			 * response.getOutputStream(); wb.write(os); wb.close(); os.close();
			 */
		}catch(Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			/*
			 * StringWriter sw = new StringWriter(); e.printStackTrace(new
			 * PrintWriter(sw,true)); String str = sw.toString();
			 * log.info("printStackTrace   =  "+str);
			 */
		}finally{
			if(null != bais) {
				IOUtils.closeQuietly(bais);
			}
			if(null != os) {
				IOUtils.closeQuietly(os);
			}
		}
		
	}
}
