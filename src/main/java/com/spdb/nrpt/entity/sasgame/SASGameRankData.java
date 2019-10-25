package com.spdb.nrpt.entity.sasgame;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//SAS比赛排行榜详细数据
//把这个数据给SASGameRank的data属性赋值
public class SASGameRankData{
	private String id;
    private String teamName;
    private String score;
    private String change;
    private String progress;
    private String teamType;
	private String Percent;			//分数占总分数的百分比 就是队伍得分的那个条
}
