drop table Report_UnitTable;
create table Report_UnitTable(
  divID varchar(32) NOT NULL,
  orgID varchar (32),
  unitValue number
);

insert into Report_UnitTable values ('02','9800',50000);
insert into Report_UnitTable values ('02','9900',50000);
insert into Report_UnitTable values ('01','9800',500000);
insert into Report_UnitTable values ('01','9900',500000);
insert into Report_UnitTable values ('100207','9800',10000);
insert into Report_UnitTable values ('100207','9900',10000);
insert into Report_UnitTable values ('100307','9800',10000);
insert into Report_UnitTable values ('100307','9900',10000);