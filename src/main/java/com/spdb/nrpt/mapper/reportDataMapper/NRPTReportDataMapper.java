package com.spdb.nrpt.mapper.reportDataMapper;

import com.spdb.nrpt.entity.Unit.ReportUnit;
import com.spdb.nrpt.entity.bridge.DivIDKeyID;
import com.spdb.nrpt.entity.procedure.OutData;
import com.spdb.nrpt.entity.report.ReportEchartsEntity;
import com.spdb.nrpt.entity.report.ReturnData;
import com.spdb.nrpt.entity.report.branch.ReportBranchDataEntity;
import com.spdb.nrpt.entity.report.custom.CustomNumberEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
@Lazy(value = false)
public interface NRPTReportDataMapper {

    //插入最新数据
    void insertBatchReportAllData(List<OutData> list);

    //插入留存数据
    void insertBatchReportPastData(List<OutData> list);

    //初始化插入桥表数据
    void insertBatchReportBridge(List<DivIDKeyID> list);

    //插入Echarts数据
    void insertEcharts(ReportEchartsEntity reportEchartsEntity);

    //单个插入ReportPastData
    void insertReportPastData(OutData outData);

    //批量插入客户数
    void insetBatchCustomData(List<CustomNumberEntity> list);

    //批量插入分行网点数据
    void insertBatchReportBranchDataTable(List<ReportBranchDataEntity> list);

    //清空Report_DataTable
    void truncateReportDataTable();

    //Report_DataTable去重
    void dropRepeatReportDataTable1();
    void dropRepeatReportDataTable2();
    void dropRepeatReportDataTable3();
    void dropRepeatReportDataTable4();

    //删除部分数据重新初始化
    void deleteEchartsTableByID(String divID);

    //根据keyID和STATIS_DT删除Report_PastDataTable
    void deleteReportPastDataTableByKeyIDAndDate(String keyID, String Date);

    //根据STATIS_DT删除Report_PastDataTable
    void deleteReportPastDataTableByKeyDate(String Date);

    //删除小于某个时间段的keyID在（YS01,YS01010101,YS01010102）
    void deleteReportPastDataTableByDateInSomeKey(String Date);

    //删除桥表数据
    void deleteDivKeyTable();

    //依据ParentID，删除网点数据
    void deleteReportBranchDataTableByParentID(String ParentID);

    //根据divID在桥表中查询keyID
    List<String> selectKeyIDByDivID(String divID);

    //根据KeyID和组织ID查询数据查询数据
    ReturnData selectOneReportDataByDivIDAndOrgID(String keyID, String orgID);
    
    //根据KeyID和组织ID查询数据查询数据
    List<ReturnData> selectReportDataByDivIDAndOrgID(String keyID, String orgID);

    //根据KeyID查询各分行数据，，不包含9900，9940，（指标名，指标值）
    List<ReturnData> selectReportDataByDivIDAndNotInOrgID(String keyID);

    //通过KeyID查询各分行数据，，不包含9900，9940（分行名，指标值）
    List<ReturnData> selectReportDataByDivIDAndOrgIDDesc(String keyID);

    //通过KeyID查询各分行数据，，不包含9900，9940（分行名，指标值）
    List<ReturnData> selectReportDataByDivIDAndOrgIDAsc(String keyID);

    //批量根据KeyID返回数据，默认组织为9900，查询部分合计数据
    List<ReturnData> selectBatchReportDataByMoreKeyID(List<String> list);

    //批量根据OrgID和KeyID返回数据，默认组织为9900，查询部分合计数据
    List<ReturnData> selectBatchReportDataByMoreOrgIDAndKeyID(String keyID,List<String> orgID);

    //批量根据KeyID返回数据，查询全部合计数据
    List<OutData> selectBatchDataReportAllDataByDivIDAndOrgID(List<String> list);

    //根据KeyID查询过往数据（指标名，指标值），时间降序
    List<ReturnData> selectReportPastDataByDivIDAndOrgID(String keyID,String orgID);

    //根据KeyID查询过往数据（日期，指标值），时间降序
    List<ReturnData> selectReportPastDateDataByDivIDAndOrgID(String keyID);

    //根据KeyID查询过往数据（日期，指标值），时间升序
    List<ReturnData> selectReportPastDateDataByDivIDAndOrgIDAsc(String keyID,String orgID);

    //根据KeyID查询过往数据（日期，指标值），按值升序
    List<ReturnData> selectReportMinPastDateDataByDivIDAndOrgID(String keyID,String orgID);

    //根据divID返回该分区名字跟Echarts组件参数
    ReportEchartsEntity selectOptionByID(String divID);

    //根据Cust_Type_Cd，客户数特殊查询
    List<ReturnData> selectCustomerNumberByCust_Type_Cd(String Cust_Type_Cd,String orgID);

    //根据divID和orgID查询单位
    ReportUnit selectReportUnitByOrgID(String divID, String orgID);

    //根据ParentID，KPI_ID，查询分行网点数据,KPI_VALUE，降序
    List<ReturnData> selectDescReportBranchByParentIDAndKPIID(String keyID,String ParentID);
    
    //根据ORG_ID，KPI_ID，查询分行网点数据，KPI_VALUE，降序
    ReturnData selectDescReportBranchByOrgIDAndKPIID(String keyID,String orgID);

    //根据ParentID，KPI_ID，查询分行网点数据,KPI_VALUE，升序,值可以为空
    List<ReturnData> selectAscReportBranchByParentIDAndKPIID(String keyID,String ParentID);
    
    //根据ParentID，KPI_ID，查询分行网点数据,KPI_VALUE，升序,值不可以为空
    List<ReturnData> selectAscReportBranchByParentIDAndKPIIDNotNull(String keyID,String ParentID);

    //根据parentOrgID查询网点机构号
    List<String> selectReportOrgDataTableByParentID(String parentOrgID);
    
    //根据orgName在Report_OrgDataTable中查询orgID
    String selectReportOrgDataTableByOrgName(String orgName);


    //查询第三屏下方新增前两个指标
    List<String> selectThirdThreeIndex(OutData outData);
    //查询第三屏最后一个指标
    List<String> selectFinBal(OutData outData);

}
