package com.spdb.nrpt.service.sasgame;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.spdb.nrpt.entity.basedata.BaseDataEntity;
import com.spdb.nrpt.entity.sasgame.SASGameData;
import com.spdb.nrpt.entity.sasgame.SASGameDataBean;
import com.spdb.nrpt.entity.sasgame.SASGameRankData;
import com.spdb.nrpt.entity.sasgame.SASGameSendDataEntity;
import com.spdb.nrpt.mapper.baseDataMapper.BaseDataMapper;
import com.spdb.nrpt.mapper.sasGameMapper.SASGameMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SASGameService {

    @Autowired
    private SASGameMapper sasGameMapper;
    @Autowired
    private BaseDataMapper baseDataMapper;

    //处理接收的SAS比赛数据
    public String dealSASGameData(SASGameSendDataEntity data){
        try {
            //插入更新
            sasGameMapper.insertSASGameDataLogTable(data);
            //更新数据
            sasGameMapper.updateSASGameDataByID(data);
        }catch (Exception e){
            log.error("sas数据存储错误，错误为："+e.toString());
            return "ERROR";
        }

        return "SUCCESS";
    }

    //获得SAS榜单数据
    public List<SASGameRankData> getSASGameData(String type){
        List<SASGameData> dataList = sasGameMapper.getSASGameAllDataOrderByScoreDesc(type);
        List<SASGameRankData> returnDataList = new ArrayList<>();
        
        for(SASGameData data : dataList) {
        	//log.info("查询出来的 数据是：{}",data.getProgress());
        	String score = data.getScore();
        	//Double totalScore = new Double("1100");
        	Integer totalScore = new Integer("900");
        	String percent = (new Double(score)*100)/totalScore+"";
        	//String percent = (new Integer(score)*100)/totalScore + "";
        	
        	SASGameRankData sasGameRankData = new SASGameRankData();
        	sasGameRankData.setId(data.getId());
        	sasGameRankData.setChange(data.getChange());
        	sasGameRankData.setPercent(percent);
        	sasGameRankData.setProgress(data.getProgress());
        	sasGameRankData.setScore(score);
        	sasGameRankData.setTeamName(data.getTeamName());
        	sasGameRankData.setTeamType(data.getTeamType());
        	
        	returnDataList.add(sasGameRankData);
        }
        return returnDataList;
    }

    //获得SAS比赛加分日志
    public List<SASGameSendDataEntity> getSASGameLogData(){
    	PageHelper.startPage(1,10);
        List<SASGameSendDataEntity> dataList = sasGameMapper.getSASGameAllLogDataOrderByScoreDesc();
        return dataList;
    }
    
    //获得当前某一队伍最新的排名  版本2
    public String getNowRankData_2(String teamId) {
    	//获得队伍的类型
    	String teamType = sasGameMapper.getTeamType(teamId); 
    	//当前队伍的排名
    	String team_rank;
    	//当前队伍的得分；
    	String score = sasGameMapper.getTeamScore(teamId);
    	 	
    	//获得当前队伍的排名
    	//根据ID和teamType,得到分数比自己大的队伍的数量
    	Integer bigger_count = sasGameMapper.getNowRankData(teamId, teamType);
    	//根据ID和teamType,得到和自己相同分数的队伍数量
		Integer same_score_count = sasGameMapper.getSeam_Score_Count(teamId,teamType);
		
    	if(bigger_count != null && bigger_count == 0) {
    		//如果没有分数比自己大的队伍，判断有没有和自己相同分数的队伍
    		if(same_score_count != null && same_score_count ==1) {
    			//没有和自己相同分数的队伍，那么队伍排名则是1
    			team_rank = "1";
    			//将队伍的排名存入数据库中
        		sasGameMapper.updateTeamRankById(teamId,team_rank);
    		}else {
    			//存在和自己相同分数的队伍
    			//得到和自己相同分数，提交时间比自己早的队伍数量
    			Integer earlier_count = sasGameMapper.getEarlierCount(score,teamId,teamType);
    			//队伍的排名为提交时间比自己早的队伍数量+1
    			team_rank = earlier_count+1+"";
    			//将队伍排名存入数据库中
    			sasGameMapper.updateTeamRankById(teamId,team_rank);
    		}
    	}else {
    		//如果存在分数比自己高的队伍
    		//判断有无和自己相同分数的队伍
    		if(same_score_count != null && same_score_count == 1) {
    			//没有和自己相同分数的队伍
    			team_rank = bigger_count + 1 + "";
    			sasGameMapper.updateTeamRankById(teamId, team_rank);
    		}else {
    			//存在和自己相同分数的队伍
    			//得到提交时间比自己早的队伍的数量
    			Integer earlier_count = sasGameMapper.getEarlierCount(score,teamId,teamType);
    			//队伍排名 -> bigger_count + earlier_count +1
    			team_rank = bigger_count + earlier_count + 1 + "";
    			sasGameMapper.updateTeamRankById(teamId, team_rank);
    		}
    	}
    	return team_rank;
    }
    
    //获得当前某一队伍最新的排名 版本1
    public String getNowRankData(String teamId) {
    	//获得队伍的类型
    	String teamType = sasGameMapper.getTeamType(teamId); 
    	//当前队伍的排名
    	String team_rank;
    	//当前队伍的得分；
    	String score = sasGameMapper.getTeamScore(teamId);
    	 	
    	//获得当前队伍的排名
    	//根据ID和teamType,得到分数比自己大的队伍的数量
    	Integer bigger_count = sasGameMapper.getNowRankData(teamId, teamType);
    	if(bigger_count != null && bigger_count == 0) {
    		//如果没有分数比自己大的队伍，判断有没有和自己相同分数的队伍
    		Integer same_score_count = sasGameMapper.getSeam_Score_Count(teamId,teamType);
    		if(same_score_count != null && same_score_count ==1) {
    			//没有和自己相同分数的队伍，那么队伍排名则是1
    			team_rank = "1";
    			//将队伍的排名存入数据库中
        		sasGameMapper.updateTeamRankById(teamId,team_rank);
    		}else {
    			//存在和自己相同分数的队伍
    			//得到和自己相同分数，提交时间比自己早的队伍数量
    			Integer earlier_count = sasGameMapper.getEarlierCount(score,teamId,teamType);
    			//队伍的排名为提交时间比自己早的队伍数量+1
    			team_rank = earlier_count+1+"";
    			//将队伍排名存入数据库中
    			sasGameMapper.updateTeamRankById(teamId,team_rank);
    		}
    	}else {
    		//如果存在分数比自己高的队伍
    		//得到分数比自己队伍高的队伍的排名，降序
    		List<Integer> rankList = sasGameMapper.getBiggerScoreTeamRank(score,teamType);
    		//判断是否存在和自己相同分数的队伍
    		Integer same_score_count = sasGameMapper.getSeam_Score_Count(teamId,teamType);
    		if(same_score_count != null && same_score_count == 1) {
    			//没有和自己相同分数的队伍
    			team_rank = rankList.get(0)+1+"";
    			//将队伍排名存入数据库中
    			sasGameMapper.updateTeamRankById(teamId,team_rank);
    		}else {
    			//存在和自己相同排名的队伍
    			//得到和自己相同分数，提交时间比自己早的队伍数量
    			Integer earlier_count = sasGameMapper.getEarlierCount(score,teamId,teamType);
    			if(earlier_count != null && earlier_count == 0) {
    				//没有提交时间比自己早的队伍
    				team_rank = rankList.get(0)+1+"";
        			//将队伍排名存入数据库中
        			sasGameMapper.updateTeamRankById(teamId,team_rank);
    			}else {
    				//存在提交时间比自己早的队伍
    				//得到相同分数下，提交时间比自己队伍早的队伍的排名，降序
    				List<Integer> earlyRankList = sasGameMapper.getSameScoreEarlierCount(score, teamId, teamType);
    				team_rank = earlyRankList.get(0)+1+"";
    				//将队伍排名存入数据库中
        			sasGameMapper.updateTeamRankById(teamId,team_rank);
    			}
    		}
    	}
    	return team_rank;
    	
		/*
		 * //根据ID和teamType判断有没有和自己相同分数的队伍 Integer same_score_count =
		 * sasGameMapper.getSeam_Score_Count(teamId,teamType); //如果没有和自己相同分数的队伍，则输出排名
		 * if(same_score_count!=null && same_score_count == 1) { team_rank =
		 * sasGameMapper.getNowRankData(teamId,teamType)+1+""; return team_rank; }
		 * //如果有和自己相同分数的队伍 //得到分数比自己大的队伍的数量 Integer bigger_count =
		 * sasGameMapper.getNowRankData(teamId, teamType); //获得队伍的分数 String score =
		 * sasGameMapper.getTeamScore(teamId); //得到相同分数，相同时间的队伍数量 Integer sameUPCount =
		 * sasGameMapper.getSameUPCount(score,teamId,teamType); //没有和自己同时提交的队伍
		 * if(sameUPCount != null && sameUPCount == 1) { //得到相同分数的情况下，提交时间比自己早的队伍的数量
		 * Integer earlier_count = sasGameMapper.getEarlierCount(score,teamId,teamType);
		 * return bigger_count+earlier_count+1+""; } //拥有和自己相同的提交时间的队伍
		 * 
		 * //return sasGameMapper.getNowRankData(teamId,teamType);
		 */    	
    }
    
    //获得队伍名称
    public String getTeamName(String teamId) {
    	return sasGameMapper.getTeamName(teamId);
    }
    
  //获得SAS比赛进行时间
    public long getSASGameTime() {
    	BaseDataEntity baseDataEntity = baseDataMapper.selectAllReportBaseDataTableByDataName("sas比赛结束时间");
    	//获得结束时间
    	long endTime = Long.parseLong(baseDataEntity.getDataValue());
    	//判断是否开始
    	if(0==endTime) {
    		//return 24*60*60*1000;
    		return 10*1000;
    	}
    	//当前时间
    	long nowTime = System.currentTimeMillis();
    	return endTime - nowTime;
    }
    
    //获得队伍头像
    public String getImagePath(String teamID) {
    	return sasGameMapper.getImagePath(teamID);
    }
    
    //获得某一分组下的所有队伍信息
    public List<SASGameDataBean> getTeamInfoByType(String teamType){
    	return sasGameMapper.getTeamInfoByType(teamType);
    }
}
