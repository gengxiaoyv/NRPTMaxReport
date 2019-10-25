package com.spdb.nrpt.service.webservice;

import com.spdb.nrpt.config.ConfigBeanProp;
import com.spdb.nrpt.util.SpringUtil;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

@Slf4j
public class EsbSoap
{
	
	//发送报文，获得数据
	public static String getData(String SoapXml)
	{
		String msg = "";
		try
		{
			ConfigBeanProp configBeanProp = (ConfigBeanProp) SpringUtil.getBean("ConfigBeanProp");
			//System.out.println("当前ESB请求路径为："+configBeanProp.getUrl());
			String urlString = configBeanProp.getUrl();
			//String urlString = "http://10.129.9.63:7701/services/S120030572";
			URL url = new URL(urlString);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Pragma", "no-cache");
			connection.setRequestProperty("Cache-Control", "no-cache");
			connection.setRequestProperty("Content-Type", "text/xml;charset=utf-8");
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			out.write(new String(SoapXml.getBytes("UTF-8")));
			out.flush();
			out.close();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
			msg = "";
			if ((msg = br.readLine()) != null)
			{
				
				String ReturnCd = msg.substring(msg.indexOf("<s:ReturnCd>"), msg.indexOf("</s:ReturnCd>")).substring(12);
				if("1".equals(ReturnCd)) {
					return msg.substring(msg.indexOf("<s:RspMsg>"), msg.indexOf("</s:RspMsg>")).substring(10);
				}else if ("0".equals(ReturnCd)) {
					return "调用失败，请求报文为：+"+SoapXml+"返回报文为："+msg;
				}
			}
		}
		catch (Exception e)
		{
			log.error("回传的报文数据为："+msg);
			e.printStackTrace();
		}
		return "调用失败";
	}

	//发送的报文信息
	public static String getsoapXml(String YYYYMMDD, String mmssSSS,String param)
	{
		ConfigBeanProp configBeanProp = (ConfigBeanProp) SpringUtil.getBean("ConfigBeanProp");
		//System.out.println("当前ESB请求路径为："+configBeanProp.getUrl());
		String urlString = configBeanProp.getUrl();

		String SoapXml = "<?xml version='1.0' encoding='UTF-8'?>\r\n" + 
				"<soap:Envelope\r\n" + 
				"    xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"\r\n" + 
				"    xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\"\r\n" + 
				"    xmlns:d=\"http://esb.spdbbiz.com/metadata\"\r\n" + 
				"    xmlns:s=\"http://esb.spdbbiz.com/services/S120030572\"\r\n" + 
				"    xmlns:x=\"http://www.w3.org/2001/XMLSchema\">\r\n" + 
				"    <soap:Header>\r\n" + 
				"        <s:ReqHeader>\r\n" + 
				"            <d:Mac>0000000000000000</d:Mac>\r\n" + 
				"            <d:MacOrgId />\r\n" + 
				"            <d:MsgId>031e9619-bc6a-48a1-ba25-67cc3cbfff93</d:MsgId>\r\n" + 
				"            <d:SourceSysId>0365</d:SourceSysId>\r\n" + 
				"            <d:ConsumerId>0211</d:ConsumerId>\r\n" + 
				"            <d:ServiceAdr>"+urlString+"</d:ServiceAdr>\r\n" +
				"            <d:ServiceAction>urn:/CustInfoQry</d:ServiceAction>\r\n" + 
				"            <d:ExtendContent></d:ExtendContent>\r\n" + 
				"        </s:ReqHeader>\r\n" + 
				"    </soap:Header>\r\n" + 
				"    <soap:Body>\r\n" + 
				"        <s:ReqCustInfoQry>\r\n" + 
				"            <s:ReqSvcHeader>\r\n" + 
				"                <s:TranDate>"+YYYYMMDD+"</s:TranDate>\r\n" + 
				"                <s:TranTime>"+mmssSSS+"</s:TranTime>\r\n" + 
				"                <s:TranTellerNo>04371704251035010940000000</s:TranTellerNo>\r\n" + 
				"                <s:TranSeqNo>04371704251035011420000000</s:TranSeqNo>\r\n" + 
				"                <s:ConsumerId>0211</s:ConsumerId>\r\n" + 
				"                <s:GlobalSeqNo>04371704251035011420000000</s:GlobalSeqNo>\r\n" + 
				"                <s:SourceSysId>0365</s:SourceSysId>\r\n" + 
				"            </s:ReqSvcHeader>\r\n" + 
				"            <s:SvcBody>\r\n" + 
				"                <s:Vchr>UBn4aNvi7gseHA6sidEM+Wtmd2Ox7K9P</s:Vchr>\r\n" + 
				"                <s:EmployeeNo>99999999</s:EmployeeNo>\r\n" + 
				"                <s:SysSysNo>0211</s:SysSysNo>\r\n" + 
				"                <s:RuleName>MCustInfo00102001</s:RuleName>\r\n" + 
				"                <s:Parm>"+param+"</s:Parm>\r\n" + 
				"                <s:IntType>1001</s:IntType>\r\n" +
				"            </s:SvcBody>\r\n" + 
				"        </s:ReqCustInfoQry>\r\n" + 
				"    </soap:Body>\r\n" + 
				"</soap:Envelope>\r\n" + 
				"";
		return SoapXml;
	}

}



