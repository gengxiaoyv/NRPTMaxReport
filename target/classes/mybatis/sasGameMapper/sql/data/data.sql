drop table Report_BaseDataTable;
create table Report_BaseDataTable(
  id varchar(100) NOT NULL,
  dataName varchar (32),
  dataGroup varchar (32),
  dataValue varchar (32),
  PRIMARY KEY (id)
);
