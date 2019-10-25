drop table SAS_GameDataLogTable;
create table SAS_GameDataLogTable(
  id varchar(100) NOT NULL,
  teamName varchar (100),
  score varchar (32),
  change varchar (32),
  progress varchar (32),
  upteTime Date
);