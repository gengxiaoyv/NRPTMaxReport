package com.spdb.nrpt.mapper.retailIndexMapper;

import com.spdb.nrpt.entity.report.Retail.ResponseData;
import com.spdb.nrpt.entity.report.RetailCustomerView.RetailCustomResponseData;
import com.spdb.nrpt.entity.report.RetailCustomerView.RetailCustomerRequestVO;
import com.spdb.nrpt.entity.report.RetailCustomerView.RetailCustomerViewVO;
import com.spdb.nrpt.entity.report.RetailCustomerView.RetailCustomerBaseData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface RetailCustomerViewMapper {

    public List<RetailCustomerBaseData> selectTileDataByDayOrgTypeAndIndex(RetailCustomerRequestVO retailCustomerRequestVO);

    List<RetailCustomerBaseData> selectOneMonthTrend(RetailCustomerRequestVO requestVO);

    Long getincrthan(RetailCustomerRequestVO requestVO);

    Long getIncreThanYearBegin(RetailCustomerRequestVO requestVO);

    Long getYearBeginLine(RetailCustomerRequestVO requestVO);

    Long getMonthBeginLine(RetailCustomerRequestVO requestVO);

    List<RetailCustomerBaseData> getSexAgeRankData(RetailCustomerRequestVO requestVO);


    Long getMapDataCount(RetailCustomerRequestVO requestVO);

    Double getMapDataPercent(RetailCustomerRequestVO requestVO);

    List<RetailCustomerBaseData> getHeadMapData(RetailCustomerRequestVO requestVO);

    Double selectCustPercent(RetailCustomerRequestVO requestVO);

    List<RetailCustomerBaseData> selectOneYearTrend(RetailCustomerRequestVO requestVO);

    List<RetailCustomerBaseData> selectCustSliceCount(RetailCustomerRequestVO requestVO);

    List<RetailCustomerBaseData> selectCustSlicePercent(RetailCustomerRequestVO requestVO);

    List<RetailCustomResponseData> selectCardSlice(RetailCustomerRequestVO requestVO);

    List<RetailCustomerBaseData> getAssetsSlice(RetailCustomerRequestVO requestVO);

    List<RetailCustomerBaseData> getCardCustPercent(RetailCustomerRequestVO requestVO);

    Double selectCustLossPercent(RetailCustomerRequestVO requestVO);


    //public List<> todo
}
