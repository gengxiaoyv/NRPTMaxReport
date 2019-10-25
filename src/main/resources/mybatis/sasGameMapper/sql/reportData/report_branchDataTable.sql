drop table Report_BranchDataTable;
create table Report_BranchDataTable(
  KPI_ID varchar(32) NOT NULL,
  KPI_NAME varchar (100),
  STATIS_DT date,
  ORG_ID varchar (32),
  ORG_NAME varchar (100),
  CURRENCY_CD varchar (100),
  KPI_VALUE varchar (100),
  ParentID varchar (32)
);

select * from Report_BranchDataTable

insert into Report_BranchDataTable values ('','')