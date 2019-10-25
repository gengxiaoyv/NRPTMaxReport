drop table Report_OrgDataTable;
create table Report_OrgDataTable(
  orgID varchar (32) not null,
  orgName varchar (100),
  parentOrgID varchar (32),
  parentName varchar (100)
);