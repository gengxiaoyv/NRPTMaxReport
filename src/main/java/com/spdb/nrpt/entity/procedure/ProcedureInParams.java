package com.spdb.nrpt.entity.procedure;


import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;


public class ProcedureInParams implements Serializable {
    private static final long serialVersionUID = 1L;
    
    //上游的入参规则
    private String I_DATE;
    private String I_ORGIDS;
    private String I_KPIIDS;
    private String I_CURRENCY_CD;
    private String I_NUM;
    private String I_ORDER;

    public ProcedureInParams() {
    }

    public ProcedureInParams(String i_DATE, String i_ORGIDS, String i_KPIIDS, String i_CURRENCY_CD, String i_NUM, String i_ORDER) {
        I_DATE = i_DATE;
        I_ORGIDS = i_ORGIDS;
        I_KPIIDS = i_KPIIDS;
        I_CURRENCY_CD = i_CURRENCY_CD;
        I_NUM = i_NUM;
        I_ORDER = i_ORDER;
    }

    //如果不加该注解，则属性首字母默认小写    但是要求首字母必须大写
    @JSONField(name = "I_DATE")
    public String getI_DATE() {
        return I_DATE;
    }

    public void setI_DATE(String i_DATE) {
        I_DATE = i_DATE;
    }

    @JSONField(name = "I_ORGIDS")
    public String getI_ORGIDS() {
        return I_ORGIDS;
    }

    public void setI_ORGIDS(String i_ORGIDS) {
        I_ORGIDS = i_ORGIDS;
    }

    @JSONField(name = "I_KPIIDS")
    public String getI_KPIIDS() {
        return I_KPIIDS;
    }

    public void setI_KPIIDS(String i_KPIIDS) {
        I_KPIIDS = i_KPIIDS;
    }

    @JSONField(name = "I_CURRENCY_CD")
    public String getI_CURRENCY_CD() {
        return I_CURRENCY_CD;
    }

    public void setI_CURRENCY_CD(String i_CURRENCY_CD) {
        I_CURRENCY_CD = i_CURRENCY_CD;
    }

    @JSONField(name = "I_NUM")
    public String getI_NUM() {
        return I_NUM;
    }

    public void setI_NUM(String i_NUM) {
        I_NUM = i_NUM;
    }

    @JSONField(name = "I_ORDER")
    public String getI_ORDER() {
        return I_ORDER;
    }

    public void setI_ORDER(String i_ORDER) {
        I_ORDER = i_ORDER;
    }

    @Override
    public String toString() {
        return "ProcedureInParams{" +
                "I_DATE='" + I_DATE + '\'' +
                ", I_ORGIDS='" + I_ORGIDS + '\'' +
                ", I_KPIIDS='" + I_KPIIDS + '\'' +
                ", I_CURRENCY_CD='" + I_CURRENCY_CD + '\'' +
                ", I_NUM='" + I_NUM + '\'' +
                ", I_ORDER='" + I_ORDER + '\'' +
                '}';
    }
}
