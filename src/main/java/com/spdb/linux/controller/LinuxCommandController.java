package com.spdb.linux.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spdb.linux.service.LinuxCommandUtil;
import com.spdb.nrpt.util.RSAEncrypt;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/linux")
@Slf4j
public class LinuxCommandController {
	
	private static LinuxCommandUtil linuxCommandUtil;
	private static boolean flag = false;
	
	/*
	 * 进行页面的跳转
	 * */
	@RequestMapping("/toRestartPage")
	public String toRestartPage() {
		return "/Linux/ReStart";
	}
	
	
	/*
	 * 登入Linux服务器  需要传入ip,端口号，用户名，密码/key
	 * */
	@RequestMapping("/loginLinux")
	@ResponseBody
	public Map<String,String> loginLinux(String ip,String username,String count) {
		//linuxCommandUtil = new LinuxCommandUtil(ip,22,username,passWord);
		linuxCommandUtil = new LinuxCommandUtil();
		Map<String,String> rsaMap = linuxCommandUtil.getPWD();
		String pwd = rsaMap.get("pwd");
		String key = rsaMap.get("key");
		String privateKey = rsaMap.get("privateKey");
		
		linuxCommandUtil.setIp(ip);
		linuxCommandUtil.setPort(22);
		linuxCommandUtil.setUserName(username);
		try{
			//登入10.129.40.119服务器
			if(count!=null && !"".equals(count) && "1".equals(count)) {
				flag = linuxCommandUtil.login(ip,RSAEncrypt.decrypt(key, privateKey));
			}
			if(count!=null && !"".equals(count) && "2".equals(count)) {
				flag = linuxCommandUtil.login(ip,RSAEncrypt.decrypt(pwd, privateKey));
			}
		}catch(Exception e) {
			e.printStackTrace();
			log.error("登入Linux系统失败："+e.getMessage());
		}
		Map<String,String> resultMap = new HashMap<>();
		if(flag) {
			resultMap.put("msg", "success");
		}else {
			resultMap.put("msg", "fail");
		}
		return resultMap;
	}
	
	/*
	 * 杀死进程process  根据主机号和进程号
	 * */
	@RequestMapping("/killLinuxProcess")
	@ResponseBody
	public Map<String,String> updateLinux(String machineID,String proID) {
		
		//linuxCommandUtil = new LinuxCommandUtil(ip,port,userName,passWord);
		String cmd = "sh /home/biee/shell/kill.sh "+"\""+machineID+"\""+" \""+proID+"\"";
		log.info("需要执行的shell脚本为：{}",cmd);
		String result = "";
		if(flag && !"".equals(cmd)) {
			result = linuxCommandUtil.execute(cmd);
		}
		Map<String,String> returnMap = new HashMap<>();
		returnMap.put("msg",result);
		return returnMap;
	}
	
	/*
	 * 查询进程  
	 * */
	@RequestMapping("/queryProcess")
	@ResponseBody
	public Map<String,Object> queryProcess(){
		String cmd = "sh /home/biee/shell/3_ps_weblogic.sh";
		log.info("需要执行的shell脚本为：{}",cmd);
		String buffer = null;
		
		if(flag && !"".equals(cmd)) {
			buffer = linuxCommandUtil.executeFile(cmd);
		}
		Map<String,Object> result = new HashMap<>();
		result.put("msg",buffer);
		
		return result;
	}
	
	
	/*
	 * 重启服务器
	 * */
	@RequestMapping("restartService")
	@ResponseBody
	public Map<String,Object> restartService(String RemachineID,String ReproID){
		
		String cmd = "sh /home/biee/shell/startbi_server.sh "+"\""+RemachineID+"\""+" \""+ReproID+"\"";
		log.info("需要执行的shell脚本为：{}",cmd);
		String result = "";
		//StringBuffer result = null;
		if(flag && !"".equals(cmd)) {
			result = linuxCommandUtil.execute(cmd);
		}
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("msg",result);
		
		return returnMap;
	}
}
