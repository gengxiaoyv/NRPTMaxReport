package com.spdb.nrpt.quartz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuartzReturnEntity {
    private Integer total;
    private List<ScheduleJobEntity> rows;
}
