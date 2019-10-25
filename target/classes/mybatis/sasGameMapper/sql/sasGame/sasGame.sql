drop table SAS_GameDataTable;
create table SAS_GameDataTable(
  id varchar(100) NOT NULL,
  teamName varchar (100),
  score varchar (32),
  change varchar (32),
  progress varchar (32),
  teamType varchar (32),
  imagePath varchar(200),
  update_time Date,
  team_rank varchar2(32),
  PRIMARY KEY (id)
);