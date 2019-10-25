package com.spdb.nrpt.quartz;

import com.spdb.nrpt.quartz.entity.ScheduleJobEntity;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzJobFactory implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        ScheduleJobEntity scheduleJobEntity = (ScheduleJobEntity) context.getMergedJobDataMap().get("scheduleJob");
        Task.invokeMethod(scheduleJobEntity);
    }
}
