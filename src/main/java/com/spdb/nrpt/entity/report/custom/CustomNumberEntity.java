package com.spdb.nrpt.entity.report.custom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//组织计量单位实体类
public class CustomNumberEntity {
    String Statis_Dt;
    String Cust_Type_Cd;
    String Org_id_1_lev;
    String Org_name_1_lev;
    String Cust_Cnt;
}
