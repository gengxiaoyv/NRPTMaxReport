package com.spdb.nrpt.quartz;

import com.spdb.nrpt.quartz.entity.ScheduleJobEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class Task {
    /**
     * 通过反射调用scheduleJob中定义的方法
     */
    public static void invokeMethod(ScheduleJobEntity scheduleJobEntity) {

        Object object = null;
        Class clazz = null;

        if (StringUtils.isNotBlank(scheduleJobEntity.getBeanClass())) {
            try {
                clazz = Class.forName(scheduleJobEntity.getBeanClass());
                object = clazz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (object == null) {
            System.out.println("任务名称 = [" + scheduleJobEntity.getJobName() + "]---------------未启动成功，请检查是否配置正确！！！");
            return;
        }
        clazz = object.getClass();
        Method method = null;
        try {
            method = clazz.getDeclaredMethod(scheduleJobEntity.getMethodName());
        } catch (NoSuchMethodException e) {
            System.out.println("任务名称 = [" + scheduleJobEntity.getJobName() + "]---------------未启动成功，方法名设置错误！！！");
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        if (method != null) {
            try {
                method.invoke(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        log.info("任务名称 : [" + scheduleJobEntity.getJobName() + "]----------启动成功");
    }
}

