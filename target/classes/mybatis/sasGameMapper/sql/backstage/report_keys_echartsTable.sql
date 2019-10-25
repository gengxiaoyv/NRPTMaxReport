-- 该表是用来记录每一个orgID及其对应的DIVID所对应的指标
-- 对应的实体类为KeysEntity
create table Report_Keys_EchartsTable( 
       ID VARCHAR2(255),     --主键id，id格式 YYYYMMDDXXX
       DIVID VARCHAR2(255),  --divID 
       KEYID VARCHAR2(255),  --指标的英文缩写，多个指标之间用，隔开
       ORGID VARCHAR2(255),  --orgID
	   UPDATE_TIME DATE,     --用来记录最后修改的时间
       CONSTRAINT KEYS_ID_PK PRIMARY KEY(ID) 
)

insert into report_keys_echartstable values('20190906001','100102','GM0102,GM010101','9900',null)