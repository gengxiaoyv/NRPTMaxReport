package com.spdb.nrpt.entity.sasgame;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 该实体类是用来展示每支队伍的具体排名的VO类
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SASGameRankVO {
	
	private String id;
	private String teamName;
	private String teamScore;
	private String updateTime;
	private String teamRank;
	
}
