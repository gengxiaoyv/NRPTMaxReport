package com.spdb.nrpt.quartz;


import com.spdb.nrpt.mapper.quartzMapper.QuartzMapper;
import com.spdb.nrpt.quartz.entity.QuartzReturnEntity;
import com.spdb.nrpt.quartz.entity.ScheduleJobEntity;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.*;

@Service
@Lazy(false)
@Slf4j
public class ScheduleJobService implements ApplicationContextAware {

    @Autowired
    private QuartzMapper quartzMapper;

    public static final String STATUS_RUNNING = "1";

    private static ApplicationContext appCtx;

    public static SchedulerFactoryBean schedulerFactoryBean = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (this.appCtx == null) {
            this.appCtx = applicationContext;
        }
    }

    static SchedulerFactoryBean getSchedulerFactory() {
        if(schedulerFactoryBean==null){
            schedulerFactoryBean = (SchedulerFactoryBean) appCtx.getBean(SchedulerFactoryBean.class);
        }
        return schedulerFactoryBean;
    }

    @PostConstruct  //被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器调用一次, Constructor >> @Autowired >> @PostConstruct
    public void init() throws Exception {
        Scheduler scheduler = getSchedulerFactory().getScheduler();
        // 这里获取任务信息数据
        List<ScheduleJobEntity> jobList = quartzMapper.selectQuartzTable();

        for (ScheduleJobEntity job : jobList) {
            addJob(job);
        }
        startAllJobs();
    }


    /**
     * 获得所有定时任务
     */
    public QuartzReturnEntity getAllTableJobs() {
        List<ScheduleJobEntity> rows = quartzMapper.selectQuartzTable();
        Integer total = quartzMapper.selectCountQuartzTable();
        QuartzReturnEntity quartzReturnEntity = new QuartzReturnEntity();
        quartzReturnEntity.setRows(rows);
        quartzReturnEntity.setTotal(total);
        return quartzReturnEntity;
    }



    /**
     * 启动所有定时任务
     */
    public void startAllJobs() {
        Scheduler scheduler = getSchedulerFactory().getScheduler();
        try {
            scheduler.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭所有定时任务
     */
    public void shutdownAllJobs() {
        Scheduler scheduler = getSchedulerFactory().getScheduler();
        try {
            if (!scheduler.isShutdown()) {
                scheduler.shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * 更改任务 cron表达式
     */
    public void updateCron(String jobId, String cron) throws SchedulerException {
        ScheduleJobEntity job = quartzMapper.selectQuartzTableByID(jobId);
        if (job == null) {
            return;
        }
        job.setCronExpression(cron);
        if (STATUS_RUNNING.equals(job.getJobStatus())) {
            updateJobCron(job);
        }
    }

    /**
     * 添加任务
     */
    public void addJob(ScheduleJobEntity job) throws SchedulerException {

        if (job == null || !STATUS_RUNNING.equals(job.getJobStatus())) {
            return;
        }

        Scheduler scheduler = this.getSchedulerFactory().getScheduler();

        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());

        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        // 不存在，创建一个
        if (null == trigger) {

            Class clazz = QuartzJobFactory.class;

            JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getJobName(), job.getJobGroup()).build();

            jobDetail.getJobDataMap().put("scheduleJob", job);

            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

            trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();

            scheduler.scheduleJob(jobDetail, trigger);
        } else {
            // Trigger已存在，那么更新相应的定时设置
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        }
    }


    /**
     * 获取所有计划中的任务列表
     */
    public List<ScheduleJobEntity> getAllJob() throws SchedulerException {
        Scheduler scheduler =  getSchedulerFactory().getScheduler();
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
        List<ScheduleJobEntity> jobList = new ArrayList<>();
        for (JobKey jobKey : jobKeys) {
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
            for (Trigger trigger : triggers) {
                ScheduleJobEntity job = new ScheduleJobEntity();
                job.setJobName(jobKey.getName());
                job.setJobGroup(jobKey.getGroup());
                //job.setRemarks("触发器:" + trigger.getKey());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                job.setJobStatus(triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    job.setCronExpression(cronExpression);
                }
                jobList.add(job);
            }
        }
        return jobList;
    }

    /**
     * 所有正在运行的job
     */
    public List<ScheduleJobEntity> getRunningJob() throws SchedulerException {
        Scheduler scheduler =  getSchedulerFactory().getScheduler();
        List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
        List<ScheduleJobEntity> jobList = new ArrayList<>(executingJobs.size());
        for (JobExecutionContext executingJob : executingJobs) {
            ScheduleJobEntity job = new ScheduleJobEntity();
            JobDetail jobDetail = executingJob.getJobDetail();
            JobKey jobKey = jobDetail.getKey();
            Trigger trigger = executingJob.getTrigger();
            job.setJobName(jobKey.getName());
            job.setJobGroup(jobKey.getGroup());
           // job.setRemarks("触发器:" + trigger.getKey());
            Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
            job.setJobStatus(triggerState.name());
            if (trigger instanceof CronTrigger) {
                CronTrigger cronTrigger = (CronTrigger) trigger;
                String cronExpression = cronTrigger.getCronExpression();
                job.setCronExpression(cronExpression);
            }
            jobList.add(job);
        }
        return jobList;
    }

    /**
     * 暂停一个job
     */
    public void pauseJob(ScheduleJobEntity scheduleJobEntity) throws SchedulerException {
        Scheduler scheduler =  getSchedulerFactory().getScheduler();
        JobKey jobKey = JobKey.jobKey(scheduleJobEntity.getJobName(), scheduleJobEntity.getJobGroup());
        scheduler.pauseJob(jobKey);
    }

    /**
     * 恢复一个job
     */
    public void resumeJob(ScheduleJobEntity scheduleJobEntity) throws SchedulerException {
        Scheduler scheduler =  getSchedulerFactory().getScheduler();
        JobKey jobKey = JobKey.jobKey(scheduleJobEntity.getJobName(), scheduleJobEntity.getJobGroup());
        scheduler.resumeJob(jobKey);
    }

    /**
     * 删除一个job
     */
    public void deleteJob(ScheduleJobEntity scheduleJobEntity) throws SchedulerException {
        Scheduler scheduler =  getSchedulerFactory().getScheduler();
        JobKey jobKey = JobKey.jobKey(scheduleJobEntity.getJobName(), scheduleJobEntity.getJobGroup());
        scheduler.deleteJob(jobKey);

    }

    /**
     * 立即执行job
     */
    public void runAJobNow(ScheduleJobEntity scheduleJobEntity) throws SchedulerException {
        Scheduler scheduler =  getSchedulerFactory().getScheduler();
        JobKey jobKey = JobKey.jobKey(scheduleJobEntity.getJobName(), scheduleJobEntity.getJobGroup());
        scheduler.triggerJob(jobKey);
    }

    /**
     * 更新job时间表达式
     */
    public void updateJobCron(ScheduleJobEntity scheduleJobEntity) throws SchedulerException {
        Scheduler scheduler =  getSchedulerFactory().getScheduler();

        TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJobEntity.getJobName(), scheduleJobEntity.getJobGroup());

        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJobEntity.getCronExpression());

        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

        scheduler.rescheduleJob(triggerKey, trigger);
    }

    /**
     * 验证cron表达式是否有效
     */
    public Map validateJob(ScheduleJobEntity scheduleJobEntity) {
        String flag="false";
        String msg="";
        Map resMap=new HashMap();
        try {
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJobEntity.getCronExpression());
        } catch (Exception e) {
            msg="cron表达式有误，不能被解析！";
        }
        Object obj = null;
        try {

                Class clazz = Class.forName(scheduleJobEntity.getBeanClass());
                obj = clazz.newInstance();
        } catch (Exception e) {
            // do nothing.........
        }
        if (obj == null) {
            msg="未找到目标类！";
        } else {
            Class clazz = obj.getClass();
            Method method = null;
            try {
                method = clazz.getMethod(scheduleJobEntity.getMethodName(), null);
            } catch (Exception e) {
                // do nothing.....
            }
            if (method == null) {
                msg="未找到目标类！";
            }
        }
        try {
            ///taskService.addTask(scheduleJobEntity);
        } catch (Exception e) {
            e.printStackTrace();
            msg="保存失败，检查 name group 组合是否有重复！";
        }
        flag="true";
        resMap.put("flag",flag);
        resMap.put("msg", msg);
        return resMap;
    }

}