package com.spdb.nrpt.entity.report;


public class ReturnData {

    private String name;            //指标名称
    private String value;           //值
   /* private String org;             //组织
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date time;            //时间*/
    private String orgid;
    
    
    public String getName() {
        return name;
    }

    public String getOrgid() {
    	return orgid;
    }
    
    public void setOrgid(String orgid) {
    	this.orgid = orgid;
    }
    
    public ReturnData() {
	super();
}



	public ReturnData(String name, String value) {
	super();
	this.name = name;
	this.value = value;
}



	public void setName(String name) {
        if(name.contains("-")){
            name = name.substring(11,16);
        }
        if (name.contains("分行")){
            name = name.replace("分行","");
        }
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
