package com.spdb.nrpt.controller.sasgame;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.spdb.nrpt.entity.basedata.BaseDataEntity;
import com.spdb.nrpt.entity.sasgame.SASGameDataBean;
import com.spdb.nrpt.entity.sasgame.SASGameLogData;
import com.spdb.nrpt.entity.sasgame.SASGameRank;
import com.spdb.nrpt.entity.sasgame.SASGameRankVO;
import com.spdb.nrpt.entity.sasgame.SASGameSendDataEntity;
import com.spdb.nrpt.entity.sasgame.SASPageData;
import com.spdb.nrpt.mapper.baseDataMapper.BaseDataMapper;
import com.spdb.nrpt.service.sasgame.SASGameService;
import com.spdb.nrpt.util.TimeUtil;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SASGameController {

    @Autowired
    private SASGameService sasGameService;
    @Autowired
    private BaseDataMapper baseDataMapper;

    //获得SAS比赛页面
    @RequestMapping("/getSASGamePage")
    public String getSASGamePage(){
        return "SASGame/SASGamePage";
    }
    
    //进入真实排名页面
    @RequestMapping("/getRealRankPage")
    public String getRealRankPage() {
    	return "SASGame/RealRankPage";
    }

    //接收高管数据
    @RequestMapping("/getSASGameSendData")
    @ResponseBody
    public Object getSASGameSendData(String id, String score, String change, String progress, HttpServletResponse response, HttpServletRequest request){
    	//高管传队伍id，队伍得分，队伍加减分情况，队伍答题进度
    	//拿到数据看看 看看是不是空数据 看看是谁的错误。
        Map<String, String[]> params = request.getParameterMap();
        log.info(JSONObject.toJSONString(params));
        
        //跨域处理 
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.setHeader("Access-Control-Max-Age", "3600");

        //通过request拿数据
        String requestId = request.getParameter("id");
        String requestScore = request.getParameter("score");
        String requestChange = request.getParameter("change");
        String requestProgress = request.getParameter("progress");

        //将传来的数据全部转为2位小数，不足2位小数的补全0
        DecimalFormat decimalFormat = new DecimalFormat("0.00"); 
        
        //id全部转成小写
        id = id.toLowerCase();
        requestId = requestId.toLowerCase();
        
        SASGameSendDataEntity sasGameSendDataEntity = new SASGameSendDataEntity();
        
        //获得队伍名称
        String teamName =  sasGameService.getTeamName(id);
        sasGameSendDataEntity.setTeamName(teamName);
        sasGameSendDataEntity.setUpteTime(TimeUtil.getNowDate());

        if (id!=null){
            sasGameSendDataEntity.setId(id);
            sasGameSendDataEntity.setChange(decimalFormat.format(Double.parseDouble(change)));
            //sasGameSendDataEntity.setChange(change);
            sasGameSendDataEntity.setProgress(progress);
            sasGameSendDataEntity.setScore(decimalFormat.format(Double.parseDouble(score)));
            //sasGameSendDataEntity.setScore(score);
        }else {
            sasGameSendDataEntity.setId(requestId);
            sasGameSendDataEntity.setChange(decimalFormat.format(Double.parseDouble(requestChange)));
            //sasGameSendDataEntity.setChange(requestChange);
            sasGameSendDataEntity.setProgress(requestProgress);
            sasGameSendDataEntity.setScore(decimalFormat.format(Double.parseDouble(requestScore)));
            //sasGameSendDataEntity.setScore(requestScore);
        }

        log.info("获得的高管传输的实体类信息为："+JSONObject.toJSONString(sasGameSendDataEntity));
        return sasGameService.dealSASGameData(sasGameSendDataEntity);
    }

    //获得页面全部信息
    @RequestMapping("getSASGamePageData")
    @ResponseBody
    public Object getSASGamePageData(){

        SASPageData sasPageData = new SASPageData();
        //放入页面标题
        sasPageData.setName("数据极客算法建模拉力赛");

        //日志数据
        List<SASGameLogData> sasGameLogDataList = new ArrayList<>();

        List<SASGameSendDataEntity> dataLogList  = sasGameService.getSASGameLogData();
        
        //log.info("查出来的日志数据的数量是：{}",dataLogList.size());
        
        for (int i=0;i<dataLogList.size();i++){
        	
            //表数据
            SASGameSendDataEntity sasGameSendDataEntity = dataLogList.get(i);
            //队伍ID
            String teamID = sasGameSendDataEntity.getId();
            //各队答题日志
            SASGameLogData sasGameLogData = new SASGameLogData();
            
            sasGameLogData.setTeamChange(sasGameSendDataEntity.getChange());
            sasGameLogData.setTeamID(teamID);
            
            //获得队伍头像
            String imagePath = sasGameService.getImagePath(teamID);
            sasGameLogData.setImagePath(imagePath);
            
            //获得队伍名称
            String teamName =  sasGameService.getTeamName(teamID);
            sasGameLogData.setTeamName(teamName);
            sasGameLogData.setUpdateTime(sasGameSendDataEntity.getUpteTime().substring(11));
            //log.info("查看时间：{}",sasGameSendDataEntity.getUpteTime().substring(11));
            //获得当前该队伍的最新排名
            //sasGameLogData.setTeamRank(sasGameService.getNowRankData(teamID));
            sasGameLogData.setTeamRank(sasGameService.getNowRankData_2(teamID));
            sasGameLogDataList.add(sasGameLogData);
        }
        
        //放入日志信息
        sasPageData.setLogData(sasGameLogDataList);

        //实时排行榜
        List<SASGameRank> sasGameRankList = new ArrayList<>();

        
        //分别获得四种类型队伍的榜单数据
        SASGameRank sasGameRank1 = new SASGameRank();
        sasGameRank1.setType("零售");
        sasGameRank1.setData(sasGameService.getSASGameData("01"));
        sasGameRankList.add(sasGameRank1);

        SASGameRank sasGameRank2 = new SASGameRank();
        sasGameRank2.setType("运营");
        sasGameRank2.setData(sasGameService.getSASGameData("02"));
        sasGameRankList.add(sasGameRank2);

        SASGameRank sasGameRank3 = new SASGameRank();
        sasGameRank3.setType("对公");
        sasGameRank3.setData(sasGameService.getSASGameData("03"));
        sasGameRankList.add(sasGameRank3);

        SASGameRank sasGameRank4 = new SASGameRank();
        sasGameRank4.setType("风险");
        sasGameRank4.setData(sasGameService.getSASGameData("04"));
        sasGameRankList.add(sasGameRank4);

        //放入排行榜数据
        sasPageData.setRankData(sasGameRankList);

        //log.info("传到前端的数据是：{}",sasPageData);
        
        return sasPageData;
    }
    
    
    
    //SAS比赛开始
    @RequestMapping("/startSASGame")
    @ResponseBody
    public void startSASGame() {
    	BaseDataEntity baseDataEntity = baseDataMapper.selectAllReportBaseDataTableByDataName("sas比赛结束时间");
    	String id = baseDataEntity.getId();
    	//获得当前时间
    	//long endTime = System.currentTimeMillis()+24*60*60*1000;
    	long endTime = System.currentTimeMillis()+10000;
    	//将当前时间放入
    	baseDataMapper.updateReportBaseDataTableByID(id, endTime+"");
    }
    
    //SAS比赛充重置
    @RequestMapping("/resetSASGame")
    @ResponseBody
    public void resetSASGame() {
    	BaseDataEntity baseDataEntity = baseDataMapper.selectAllReportBaseDataTableByDataName("sas比赛结束时间");
    	String id = baseDataEntity.getId();
    	//将当前时间放入
    	baseDataMapper.updateReportBaseDataTableByID(id, 0+"");
    }
    
    //获得SAS比赛剩余时间
    @RequestMapping("/getSASGameTime")
    @ResponseBody
    public long getSASGameTime() {
    	return sasGameService.getSASGameTime();
    }
    
    //得到真实的队伍排名，显示在前端
    @RequestMapping("/getRealRank")
    @ResponseBody
    public Map<String,Object> getRealRank(String teamType){
    	log.info("传入的队伍ID为：{}",teamType);
    	
    	//定义返回给前台的Map集合
    	Map<String,Object> result = new HashMap<String,Object>();
    	//定义用于存放SASGameRankVO的List集合
    	List<SASGameRankVO> RankVOList = new ArrayList<SASGameRankVO>();
    	
    	//根据传入的teamType得到该类别下的所有队伍
    	List<SASGameDataBean> SASGameDataBeanList = sasGameService.getTeamInfoByType(teamType);
    	for(SASGameDataBean sasGame : SASGameDataBeanList) {
    		SASGameRankVO rankVO = new SASGameRankVO();
    		
    		//得到每一只队伍的ID
    		String teamId = sasGame.getId();
    		//获得每一只队伍的排名
    		String teamRank = sasGameService.getNowRankData_2(teamId);
    		//获得每一只队伍的队名
    		String teamName = sasGame.getTeamName();
    		//获得每一只队伍的最后提交时间
    		String updateTime = sasGame.getShowTime();
    		//获得每一只队伍的得分
    		String teamScore = sasGame.getScore();
    		
    		rankVO.setId(teamId);
    		rankVO.setTeamName(teamName);
    		rankVO.setTeamRank(teamRank);
    		rankVO.setTeamScore(teamScore);
    		rankVO.setUpdateTime(updateTime);
    		RankVOList.add(rankVO);
    	}
    	
    	result.put("data",RankVOList);
    	result.put("msg","");
    	result.put("code",0);
    	result.put("count",SASGameDataBeanList.size());
    	
    	return result;
    }
}
