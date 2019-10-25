package com.spdb.nrpt.entity.sasgame;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//表SAS_GameDataLogTable的实体类，同时为接收高管发出的数据的实体类
//后端自己用的
public class SASGameSendDataEntity {

    private String id;
    private String teamName;
    private String score;
    private String change;
    private String progress;
    private String upteTime;

}
