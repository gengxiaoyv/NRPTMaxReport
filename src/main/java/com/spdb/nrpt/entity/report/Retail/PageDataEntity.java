package com.spdb.nrpt.entity.report.Retail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDataEntity {

//    private String pageName;//页面标签名称：如个人存款个人贷款.eg

//    private DivDataEntity depDivData;//个人存款页面数据
//
//    private DivDataEntity loanDivData;//个人贷款页面数据
//
//    private DivDataEntity finanAsseData;//个人金融资产页面数据
//
//    private DivDataEntity nonDepAssData;//非存资产页面数据


    private String pageName;//页面名字   个人贷款存款等
    private String pageDescription;//页面描述，第二行小字
    private DivDataEntity divdata;//页面数据



}
