package com.spdb.nrpt.mapper.backstageMapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.spdb.nrpt.entity.custom.Echarts.BackstageEchartsEntity;
import com.spdb.nrpt.entity.custom.Echarts.CustomEchartsEntity;

@Component
@Mapper
public interface BackstageMapper {

    //查询Report_CustomEchartsTable全部数据
    List<BackstageEchartsEntity> selectAllReportCustomEcharts();

    //查询divID对应的Echarts组件信息
    CustomEchartsEntity selectDivEchartsData(String divID,String orgID);

    //更新divID对应的Echarts组件id
    void updateEchartsID(String echartsid,String divid,String orgID);
    
    //通过divID和orgID得到div所对应的数据指标   江
    public String selectDivKeys(@Param("divID") String divID,@Param("orgID") String orgID);
}
