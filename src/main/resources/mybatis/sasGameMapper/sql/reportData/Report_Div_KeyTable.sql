drop table Report_Div_KeyTable;
create table Report_Div_KeyTable(
  id varchar(32) NOT NULL,
  divID varchar(32),
  keyID varchar(32),
  PRIMARY KEY (id)
);