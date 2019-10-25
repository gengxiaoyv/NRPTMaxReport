package com.spdb.nrpt.mapper.reportDataMapper;

import com.spdb.nrpt.entity.entrance.EntranceListData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface NRPTAndPortalMapper {

	//查询portal中该用户的关于大屏的权限配置
    List<EntranceListData> selectPortalDataFormMenuOrderByUserIDAndResID(String userID,String resID);

}
