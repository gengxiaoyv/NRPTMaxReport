package com.spdb.nrpt.controller.demo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spdb.nrpt.entity.demo.DemoVO;
import com.spdb.nrpt.service.demo.DemoService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ViewController {

	@Autowired
	private DemoService demoService;
	
	@RequestMapping("/to_bankList")
    public String to_bankList(){
        return "Demo/bank_list";
    }

    @RequestMapping("/to_addDemoInfo")
    public String to_addBankInfo(){
        return "Demo/add_demo";
    }

    /*
     * 跳转编辑页面，同时将必要的数据存入Request范围里
     * */
	@RequestMapping("/to_edit")
	public String to_edit(String id,HttpServletRequest request) {
		DemoVO demoVO = demoService.queryOneById(id);
		request.setAttribute("demoVO", demoVO);
		return "Demo/edit_demo";
	}
}
