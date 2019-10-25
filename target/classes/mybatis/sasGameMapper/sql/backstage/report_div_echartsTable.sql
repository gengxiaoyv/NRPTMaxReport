drop table Report_Div_EchartsTable;
create table Report_Div_EchartsTable(
  id varchar(50) NOT NULL,
  divID varchar(32),
  orgID varchar(32).
  EchartsID varchar(32),
  PRIMARY KEY (id)
);

insert into Report_Div_EchartsTable values ('6cf64b80bfa94f54ac9ae93c1b0a5a3c','100109','9900','Radar001');
insert into Report_Div_EchartsTable values ('6cf64b80bfa94f54ac9ae93c1b0a5a3b','100102','9900','Bar001');