package com.spdb.nrpt.controller.quartz;


import com.spdb.nrpt.quartz.ScheduleJobService;
import com.spdb.nrpt.quartz.entity.QuartzReturnEntity;
import com.spdb.nrpt.quartz.entity.ScheduleJobEntity;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class QuartzController {

    @Autowired
    private ScheduleJobService scheduleJobService;

    //跳转定时任务页面
    @RequestMapping("/getQuartzPage")
    public String getQuartzPage(){
        return "NRPT/NRPTQuartzPage";
    }

    @RequestMapping("/addJob")
    @ResponseBody
    public String addJob(ScheduleJobEntity scheduleJobEntity){
        System.out.println(scheduleJobService.validateJob(scheduleJobEntity));
        try {
            scheduleJobService.addJob(scheduleJobEntity);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "SUCCESS";
    }

    @RequestMapping("/updateCron")
    @ResponseBody
    public String updateCron(ScheduleJobEntity scheduleJobEntity){
        try {
            scheduleJobService.updateCron(scheduleJobEntity.getId(),scheduleJobEntity.getCronExpression());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "SUCCESS";
    }

    @RequestMapping("/getAllJob")
    @ResponseBody
    public QuartzReturnEntity getAllJob(){
        return scheduleJobService.getAllTableJobs();
    }

    @RequestMapping("/pauseJob")
    @ResponseBody
    public String pauseJob(ScheduleJobEntity scheduleJobEntity){
        try {
            scheduleJobService.pauseJob(scheduleJobEntity);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "SUCCESS";
    }

    @RequestMapping("/resumeJob")
    @ResponseBody
    public String resumeJob(ScheduleJobEntity scheduleJobEntity){
        try {
            scheduleJobService.resumeJob(scheduleJobEntity);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "SUCCESS";
    }

    @RequestMapping("/deleteJob")
    @ResponseBody
    public String deleteJob(ScheduleJobEntity scheduleJobEntity){
        try {
            scheduleJobService.deleteJob(scheduleJobEntity);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "SUCCESS";
    }

    @RequestMapping("/runAJobNow")
    @ResponseBody
    public String runAJobNow(ScheduleJobEntity scheduleJobEntity){
        try {
            scheduleJobService.runAJobNow(scheduleJobEntity);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "SUCCESS";
    }
}
