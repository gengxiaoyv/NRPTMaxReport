package com.spdb.nrpt.mapper.sasGameMapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.spdb.nrpt.entity.sasgame.SASGameData;
import com.spdb.nrpt.entity.sasgame.SASGameDataBean;
import com.spdb.nrpt.entity.sasgame.SASGameSendDataEntity;

@Mapper
@Component
public interface SASGameMapper {

    //查询SAS_GameDataTable   score降序
    List<SASGameData> getSASGameAllDataOrderByScoreDesc(String type);

    //查询SAS_GameDataLogTable    时间
    List<SASGameSendDataEntity> getSASGameAllLogDataOrderByScoreDesc();


    //更新
    void updateSASGameDataByID(SASGameSendDataEntity data);

    //插入SAS_GameDataTable
    void insertSASGameDataTable(SASGameData data);

    //插入SAS_GameDataLogTable
    void insertSASGameDataLogTable(SASGameSendDataEntity data);

    //根据ID将队伍的排名存入数据库中   ---江
    void updateTeamRankById(@Param("teamId") String teamId,@Param("teamRank") String team_rank);
    
    //得到分数比自己队伍高的队伍的排名，降序 ----江
    List<Integer> getBiggerScoreTeamRank(@Param("score") String score,@Param("teamType") String teamtype);
    
    //相同分数下，得到提交时间比自己队伍早的排名，降序   江
    List<Integer> getSameScoreEarlierCount(@Param("score") String score,@Param("teamId") String teamId,@Param("teamType") String teamType);
    
    //获得某一队伍的队伍类型
    String getTeamType(String id);
    
    //获得某一只队伍的分数  ---江
    String getTeamScore(@Param("teamId") String teamId);
    
    //根据传入的分数，id，type得到相同分数下，提交时间比自己早的队伍数量       ----江
    Integer getEarlierCount(@Param("score") String score,@Param("teamId") String teamId,@Param("teamType") String teamType);
    
    //根据传入的分数，id,type得到相同分数下，相同提交时间的队伍数量 ---江
    Integer getSameUPCount(@Param("score") String score,@Param("teamId") String teamId,@Param("teamType") String teamType);
    
    //根据传入的队伍ID和队伍Type判断有无相同分数的队伍   ---江
    Integer getSeam_Score_Count(@Param("teamId") String teamId,@Param("teamType") String teamType);
    
    //获得某一队伍的当前最新排名    ---江
    Integer getNowRankData(String id,String teamType);
    //String getNowRankData(String id,String teamType);
    
    //获得队伍名称
    String getTeamName(String teamID);
    
    //获得队伍头像
    String getImagePath(String teamID);
    
    //获得某一分组下的所有队伍的信息   江
    List<SASGameDataBean> getTeamInfoByType(@Param("teamType") String teamType);
}
