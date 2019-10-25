package com.spdb.nrpt.entity.sasgame;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//表SAS_GameDataTable对应的实体类   该表进行过二次修改，该实体类对应二次修改过后的表
//存队伍信息
public class SASGameDataBean{
    private String id;				//唯一ID
    private String teamName;		//队伍名称
    private String score;			//分数
    private String change;			//分数变化
    private String progress;		//进度
    private String teamType;		//队伍类型（01零售实时榜单,02运营实时榜单,03对公实时榜单,04风险实时榜单）
    private String imagePath;		//头像路径
    private Timestamp update_time;     //最后一次提交分数时间
    private String team_rank;       //所属分组的排名
    private String showTime;
    
    public String getShowTime() {
    	if(update_time != null) {
    		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.update_time);
    	}
    	return null;
    }
}