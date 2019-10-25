package com.spdb.nrpt.entity.sasgame;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//SAS比赛日志数据
//给前端用的 返回数据给前端
public class SASGameLogData {
    private String teamID;              //队伍ID
    private String teamName;            //队伍名字
    private String teamChange;          //队伍分数
    private String updateTime;          //提交时间
    private String teamRank;            //队伍排名的当前排名
    private String imagePath;		    //头像路径
}
