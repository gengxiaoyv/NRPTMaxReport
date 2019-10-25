package com.spdb.nrpt.quartz.TaskImpl;

import com.alibaba.fastjson.JSONArray;
import com.spdb.nrpt.config.ConfigBeanProp;
import com.spdb.nrpt.service.webservice.NRPTWebService;
import com.spdb.nrpt.util.SpringUtil;
import com.spdb.nrpt.util.StringUtil;
import com.spdb.nrpt.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class TaskReport {

    //判断主节点
    public Boolean getMainIP(){
        ConfigBeanProp configBeanProp = (ConfigBeanProp) SpringUtil.getBean("ConfigBeanProp");
        log.info("当前允许运行定时任务的ip为:"+configBeanProp.getIp());
        String MainIP = configBeanProp.getIp();
        List<String> allIP = StringUtil.getAllAddress();
        log.info("当前IP为"+JSONArray.toJSONString(allIP));
        if (allIP.indexOf(MainIP)<0){
        	//如果不是主节点，则返回true
            return true;
        }
        return false;
    }

    //每5：00分钟大屏数据更新
    public void synchronizationData(){
        NRPTWebService nrptWebService= (NRPTWebService) SpringUtil.getBean("NRPTWebService");
        log.info("大屏数据5分钟更新开始"+new Date());
        //如果不是主节点，getMainIP（）返回true,进入if判断之后，直接return
        if (getMainIP()){
            return;
        }
        String res = nrptWebService.getAllData();
        log.info("大屏数据五分钟更新结果为"+res);
    }

    //每30分钟15秒大屏留库数据更新
    public void synchronizationPastData(){
        NRPTWebService nrptWebService= (NRPTWebService) SpringUtil.getBean("NRPTWebService");
        log.info("大屏留库30分钟数据更新开始");
        if (getMainIP()){
            return;
        }
        //拿取时间
        String oldTime = TimeUtil.getPastTime(4,0.5);   //2019-05-10 10:00:00
        String time = TimeUtil.getPastTime(0,0);      //2019-05-10 15:00:00

        log.info("删除时间为"+oldTime);
        log.info("插入时间为"+time);


        String res = nrptWebService.update30Data(oldTime,time);
        log.info("大屏留库30分钟数据更新结果为"+res);
    }

    //每天凌晨23：57：00钟同步大屏留库数据
    public void synchronizationDayData(){
        NRPTWebService nrptWebService= (NRPTWebService) SpringUtil.getBean("NRPTWebService");

        log.info("大屏留库凌晨数据更新开始");
        if (getMainIP()){
            return;
        }

        String res = nrptWebService.updateDayData();
        log.info("大屏留库凌晨数据更新结果为"+res);

    }

    /*public void changeReport(){
        log.info("前台切换大屏，当前时间为"+new Date());
        try {
            nrptWebSocket.sendInfo("Change");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

}
