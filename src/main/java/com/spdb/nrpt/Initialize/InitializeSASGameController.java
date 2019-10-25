package com.spdb.nrpt.Initialize;

import com.spdb.nrpt.entity.sasgame.SASGameData;
import com.spdb.nrpt.mapper.sasGameMapper.SASGameMapper;
import com.spdb.nrpt.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class InitializeSASGameController {

    @Autowired
    private SASGameMapper sasGameMapper;

    @RequestMapping("/InitializeSASGameData")
    @ResponseBody
    public String InitializeSASGameData(){
        for (int i=1;i<=32;i++){
            SASGameData sasGameData = new SASGameData();
            if (i <= 8) {
                sasGameData.setTeamType("01");
            } else if (i < 17 && i >= 9) {
                sasGameData.setTeamType("02");
            } else if (i < 25 && i >= 17) {
                sasGameData.setTeamType("03");
            } else if(i<33&&i>=25){
                sasGameData.setTeamType("04");
            }
            sasGameData.setChange("0");
            if(i>=10) {
            	 sasGameData.setId("10"+i);
            }else {
            	 sasGameData.setId("100"+i);
            }
            sasGameData.setScore("0");
            sasGameData.setProgress("0");
            sasGameData.setTeamName("测试"+i);
            sasGameData.setImagePath("css/sasGame/img/logTitle.png");
            sasGameMapper.insertSASGameDataTable(sasGameData);
        }
        return "SUCCESS";
    }
}
