package com.spdb.nrpt.entity.sasgame;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//表SAS_GameDataTable对应的实体类
//存队伍信息
public class SASGameData{
    private String id;				//唯一ID
    private String teamName;		//队伍名称
    private String score;			//分数
    private String change;			//分数变化
    private String progress;		//进度
    private String teamType;		//队伍类型（01零售实时榜单,02运营实时榜单,03对公实时榜单,04风险实时榜单）
    private String imagePath;		//头像路径
}
