package com.spdb.nrpt.entity.report.RetailCustomerView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetailCustomerRequestVO {

    private String index_name;//指标名称

    private List<String> org_idList;//机构id集合

    private String dims1;//0总行 1分行

    private String dims2;//01日 02月

    private String dims3;//0存量客户 1 当年新开客户

    private String dims4;//1男 2女 3未知

    private String dims5;//账户类型代码 1I类账户 2II类账户 3III类账户 4 其他

    private String org_id;//

    private Date data_date;

    private List<String> tileNameList;//磁贴的标签名集合


}
