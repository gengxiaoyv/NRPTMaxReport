package com.spdb.nrpt.entity.sasgame;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//SAS比赛返回页面的全部数据
public class SASPageData {

	//标题名称 比赛标题
    private String name;
    //排行榜数据
    private Object rankData;
    //日志数据
    private Object logData;
}
