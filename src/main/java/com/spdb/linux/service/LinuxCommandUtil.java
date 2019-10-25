package com.spdb.linux.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.spdb.nrpt.util.RSAEncrypt;

//import org.apache.log4j.Logger;

import com.trilead.ssh2.Connection;
import com.trilead.ssh2.Session;
import com.trilead.ssh2.StreamGobbler;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data

public class LinuxCommandUtil {
	private Connection conn;
	private String ip;
	private int port;
	private String userName;
	private String passWord;
	public LinuxCommandUtil(String ip, int port, String userName,String passWord) {
		super();
		this.ip = ip;
		this.port = port;
		this.userName = userName;
		this.passWord = passWord;
	}
	public LinuxCommandUtil() {
	}
	
	
	/**
	 * ��¼������
	 * @return
	 * @throws Exception
	 */
	public boolean login(String ip,String pwdOrKey) throws Exception{
		boolean flag = false;
		conn = new Connection(ip, 22);
		
		try {
			conn.connect();
		}catch(Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		log.info(pwdOrKey);
		//测试10.129.40.119
		//生产10.200.177.84
		if("key".equalsIgnoreCase(pwdOrKey) && "10.200.177.84".equals(ip)){
			File file =new File("/home/biee/.ssh/id_rsa");
			//免密登入
			flag = conn.authenticateWithPublicKey(userName, file,null);
			log.info("服务器免密登陆状态: "+flag);
		}else {
			//非免密登入
			flag = conn.authenticateWithPassword(userName, pwdOrKey);
		}
		return flag;
	}
	
	
	/*
	 * public static void main(String[] args) throws Exception{ LinuxCommandUtil l =
	 * new LinuxCommandUtil(); Map<String,String> pwdMap = l.getPWD(); String pwd =
	 * pwdMap.get("pwd"); String privateKey = pwdMap.get("privateKey"); String
	 * password = RSAEncrypt.decrypt(pwd, privateKey); l.setUserName(password);
	 * 
	 * System.out.println(l.login("10.129.40.119",password)); }
	 */
	 
	/*
	 * 获得加密后的登入密码
	 * */
	 public Map<String,String> getPWD() {
		 Properties pro = new Properties();
		 InputStream is = null;
		 //is = this.getClass().getClassLoader().getResourceAsStream("pwd.properties");
		
		 String privateKey = null;
		 String pwd = null; 
		 String key = null;
		 
		 
		 Map<String,String> pwdMap = new HashMap<>();
		 try{
			 ClassPathResource resource = new ClassPathResource("pwd.properties");
			 is = resource.getInputStream();
			 pro.load(is);
			 privateKey = pro.getProperty("privateKey");
			 pwd = pro.getProperty("pwd");
			 key = pro.getProperty("key");
			 pwdMap.put("privateKey", privateKey);
			 pwdMap.put("pwd", pwd);
			 pwdMap.put("key",key);
		 }catch(Exception e) {
			 e.printStackTrace();
			 log.info(e.getMessage());
		 }
		 
		 return pwdMap;
	 }
	
	public String execute(String cmd){
		String result = "";
		//StringBuffer result = null;
		Session session = null;
		try {
			
				session = conn.openSession();
				log.info("启动session，开始执行命令");
				//执行命令
				session.execCommand(cmd);
				log.info("脚本执行结束!");
//				result = processStdout(session.getStdout(), "UTF-8");
//				//如果输出不为空，说明脚本出错
//				if(StringUtils.isBlank(result)){
//					result = processStdout(session.getStderr(), "UTF-8");
//				}
				result = processStdout(session.getStdout(), "UTF-8");
				//result = result.substring(result.length()-2, result.length()-1);
				log.info("返回执行结果：" + result);
				conn.close();
				session.close();
			    result = result+" success ";
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return "error";
			
		} finally {
			
			 if(conn != null){ 
				 conn.close();
			 } 
			 if(session != null){
				 session.close();
			 }
			 
		}
		return result;
	}
	
	public String executeFile(String cmd){
		String result = "";
		Session session = null;
		String buffer = null;
		try {
			
				session = conn.openSession();
				log.info("启动session，开始执行命令");
				//执行命令
				session.execCommand(cmd);
				log.info("脚本执行结束!");

				//optFile(session.getStdout(), "UTF-8");
				buffer = processStdout(session.getStdout(), "UTF-8");
				conn.close();
				session.close();
				
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			
		} finally {
			
			 if(conn != null){ 
				 conn.close();
			 } 
			 if(session != null){
				 session.close();
			 }
			 
		}
		return buffer;
	}
	
	//该方法没有使用
	public void optFile(InputStream in , String charset) {
		InputStream stdout = new StreamGobbler(in);
		InputStreamReader isr = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		//测试环境
		String savePath = "/nfs/upload/processRun.txt";
		//本地环境
		//String savePath = "D:/text/processRun.txt";
		//生产环境
		// String savePath = "/vrpd/biee12c/upload/processRun.txt";
		try{
			isr = new InputStreamReader(stdout,charset);
			br = new BufferedReader(isr);
			pw = new PrintWriter(new FileWriter(savePath));
			String line = null;
			while((line = br.readLine()) != null) {
				//log.info(line);
				pw.println(line);
			}
		}catch(Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}finally {
			try {
				if(pw != null) {
					pw.close();
				}
				if(br != null) {
					br.close();
				}
				if(isr != null) {
					isr.close();
				}
				if(stdout != null) {
					stdout.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
				log.info(e.getMessage());
			}
		}
	}
	
	public String processStdout(InputStream in,String charset){
		InputStream stdout = new StreamGobbler(in);
		StringBuffer buffer = new StringBuffer();
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			isr = new InputStreamReader(stdout, charset);
			br = new BufferedReader(isr);
			String line = null;
			while((line = br.readLine()) != null){
				buffer.append(line + "\n");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} finally{
			try {
				if(br != null){
					br.close();
				}
				if(isr != null){
					isr.close();
				}
				if(stdout != null){
					stdout.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return buffer.toString();
		
	}
		
}
