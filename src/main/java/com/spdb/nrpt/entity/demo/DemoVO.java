package com.spdb.nrpt.entity.demo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoVO {

    private String demo_pkid;
    private String apply_time;
    private String apply_deptid;
    private String apply_plate;
    private String plate_manage_name;
    private String business_name;
    private String apply_channel;
    private String contact_list_id;
    private String contact_list_name;
    private String report_name;
    private String requirement_type;
    private String realization;
    private String handler_name;
    private String handler_status;
    private String production_way;
    private String demand_time;
    private String implementation_p_time;
    private String demand_SLA;
    private String est_fir_submit_time;
    private String est_beg_time;
    private String fir_submit_time;
    private String actual_beg_search_time;
    private String search_SLA;
    private String gray_finish_time;
    private String gray_SLA;
    private String implementation_time;
    private String deliver_time;
    private String deliver_SLA;
    private Timestamp create_time;
    private Timestamp update_time;

}