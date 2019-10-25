drop table Report_QuartzTable;
create table Report_QuartzTable(
  id varchar(32) NOT NULL,
  jobName varchar (32),
  jobGroup varchar (32),
  jobStatus varchar (32),
  cronExpression varchar (32),
  beanClass varchar (100),
  methodName varchar (32),
  PRIMARY KEY (id)
);


insert into QuartzTable values ('1554273748063263870','浦发5分钟数据更新','NRPT','1','0 0/5 * * * ? ',
'com.spdb.nrpt.quartz.TaskImpl.TaskReport','synchronizationData');
insert into Report_QuartzTable values ('1554273748063263873','浦发大屏30分钟更新策略',
                                       'NRPT','1','15 /30 * * * ?','com.spdb.nrpt.quartz.TaskImpl.TaskReport','synchronizationPastData');
insert into Report_QuartzTable values ('1554273748063263874','浦发大屏凌晨更新策略',
                                       'NRPT','1','0 57 23 * * ?','com.spdb.nrpt.quartz.TaskImpl.TaskReport','synchronizationDayData');