package com.spdb.nrpt.mapper.quartzMapper;

import com.spdb.nrpt.quartz.entity.ScheduleJobEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
@Lazy(value = false)
public interface QuartzMapper {

    List<ScheduleJobEntity> selectQuartzTable();

    ScheduleJobEntity selectQuartzTableByID(String id);

    Integer selectCountQuartzTable();

    void updateQuartzTableByID(ScheduleJobEntity scheduleJobEntity);
}
