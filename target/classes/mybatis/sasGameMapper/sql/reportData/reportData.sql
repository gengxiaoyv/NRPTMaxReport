drop table Report_DataTable;
create table Report_DataTable(
  KPI_ID varchar(32) NOT NULL,
  KPI_NAME varchar (32),
  STATIS_DT date,
  ORG_ID varchar (32),
  ORG_NAME varchar (32),
  CURRENCY_CD varchar (100),
  KPI_VALUE varchar (100)
);

insert into Report_DataTable values ('NRPT01','主动负债+单位保本理财',to_date('2019-05-06 21:22:00','YYYY-MM-DD HH24:MI:SS'),'9800','上海分行','199','234.56');



