package com.spdb.nrpt.entity.sasgame;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//SAS比赛排行榜数据
//返回比赛类型（比如：零售实时榜单）
public class SASGameRank {
    private String type;                //类型
    private Object data;                //数据
}
