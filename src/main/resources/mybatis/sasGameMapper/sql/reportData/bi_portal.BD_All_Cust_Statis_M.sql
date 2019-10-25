drop table BD_All_Cust_Statis_M;

create table BD_All_Cust_Statis_M(
  Statis_Dt Date,
  Cust_Type_Cd varchar2 (2),
  Org_id_1_lev varchar2 (6),
  Org_name_1_lev varchar2 (50),
  Cust_Cnt integer
);

comment on table BD_All_Cust_Statis_M is '全行客户数统计表';

comment on column BD_All_Cust_Statis_M.Statis_Dt        is  '统计日期';
comment on column BD_All_Cust_Statis_M.Cust_Type_Cd     is  '客户类型';
comment on column BD_All_Cust_Statis_M.Org_id_1_lev     is  '分行号';
comment on column BD_All_Cust_Statis_M.Org_name_1_lev   is  '分行名称';
comment on column BD_All_Cust_Statis_M.Cust_Cnt         is  '客户数';

create index BD_All_Cust_Statis_M_Idx on BD_All_Cust_Statis_M(STATIS_DT,Org_id_1_lev,Cust_Type_Cd);

insert into BD_All_Cust_Statis_M (Statis_Dt, Cust_Type_Cd, Org_id_1_lev, Org_name_1_lev, Cust_Cnt)
values (to_date('2018-11-30 00:00:00','YYYY-MM-DD HH24:MI:SS'),'01','','','67201407');
insert into BD_All_Cust_Statis_M (Statis_Dt, Cust_Type_Cd, Org_id_1_lev, Org_name_1_lev, Cust_Cnt)
values (to_date('2018-12-31 00:00:00','YYYY-MM-DD HH24:MI:SS'),'01','','','68192905');
insert into BD_All_Cust_Statis_M (Statis_Dt, Cust_Type_Cd, Org_id_1_lev, Org_name_1_lev, Cust_Cnt)
values (to_date('2019-01-31 00:00:00','YYYY-MM-DD HH24:MI:SS'),'01','','','69306897');
insert into BD_All_Cust_Statis_M (Statis_Dt, Cust_Type_Cd, Org_id_1_lev, Org_name_1_lev, Cust_Cnt)
values (to_date('2019-02-28 00:00:00','YYYY-MM-DD HH24:MI:SS'),'01','','','69798885');
insert into BD_All_Cust_Statis_M (Statis_Dt, Cust_Type_Cd, Org_id_1_lev, Org_name_1_lev, Cust_Cnt)
values (to_date('2019-03-31 00:00:00','YYYY-MM-DD HH24:MI:SS'),'01','','','70724496');
insert into BD_All_Cust_Statis_M (Statis_Dt, Cust_Type_Cd, Org_id_1_lev, Org_name_1_lev, Cust_Cnt)
values (to_date('2019-04-30 00:00:00','YYYY-MM-DD HH24:MI:SS'),'01','','','71724496');

insert into BD_All_Cust_Statis_M (Statis_Dt, Cust_Type_Cd, Org_id_1_lev, Org_name_1_lev, Cust_Cnt)
values (to_date('2018-11-30 00:00:00','YYYY-MM-DD HH24:MI:SS'),'02','','','1513632');
insert into BD_All_Cust_Statis_M (Statis_Dt, Cust_Type_Cd, Org_id_1_lev, Org_name_1_lev, Cust_Cnt)
values (to_date('2018-12-31 00:00:00','YYYY-MM-DD HH24:MI:SS'),'02','','','1525279');
insert into BD_All_Cust_Statis_M (Statis_Dt, Cust_Type_Cd, Org_id_1_lev, Org_name_1_lev, Cust_Cnt)
values (to_date('2019-01-31 00:00:00','YYYY-MM-DD HH24:MI:SS'),'02','','','1540352');
insert into BD_All_Cust_Statis_M (Statis_Dt, Cust_Type_Cd, Org_id_1_lev, Org_name_1_lev, Cust_Cnt)
values (to_date('2019-02-28 00:00:00','YYYY-MM-DD HH24:MI:SS'),'02','','','1504814');
insert into BD_All_Cust_Statis_M (Statis_Dt, Cust_Type_Cd, Org_id_1_lev, Org_name_1_lev, Cust_Cnt)
values (to_date('2019-03-31 00:00:00','YYYY-MM-DD HH24:MI:SS'),'02','','','1500788');
insert into BD_All_Cust_Statis_M (Statis_Dt, Cust_Type_Cd, Org_id_1_lev, Org_name_1_lev, Cust_Cnt)
values (to_date('2019-04-30 00:00:00','YYYY-MM-DD HH24:MI:SS'),'02','','','1520253');

