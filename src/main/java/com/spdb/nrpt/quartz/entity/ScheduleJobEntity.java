package com.spdb.nrpt.quartz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleJobEntity {
    private String Id;
    private String jobName;		// 任务名称
    private String jobGroup;		// 任务分组
    private String jobStatus;		// 任务状态（1：正常；0：暂停）
    private String cronExpression;	// cron表达式
    private String beanClass;		// 任务执行时调用的方法,包名+类名
    private String methodName;		// 任务调用的方法名
}
