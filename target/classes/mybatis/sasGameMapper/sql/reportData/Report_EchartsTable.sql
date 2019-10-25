drop table Report_EchartsTable;
create table Report_EchartsTable(
  divID varchar(32) NOT NULL,
  divName varchar(32),
  divEchartsJson varchar2(4000),
  divEchartsType varchar(100),
  PRIMARY KEY (divID)
);