drop table Report_CustomEchartsTable;
create table Report_CustomEchartsTable(
  echartsID varchar(32) NOT NULL,
  echartsName varchar(100),
  echartsJson varchar2(4000),
  echartsClass varchar (100),
  PRIMARY KEY (echartsID)
);

insert into Report_CustomEchartsTable VALUES ('Bar001','轴自动补全柱状图','{"xAxis":"轴数据自动补全"}','com.spdb.nrpt.entity.custom.Bar.Bar001');
insert into Report_CustomEchartsTable VALUES ('Line001','轴自动补全光滑折线图','{"symbol":"none","xAxisData":"轴数据自动补全","smooth":true}','com.spdb.nrpt.entity.custom.Line.Line001');
insert into Report_CustomEchartsTable VALUES ('Line002','轴自动补全焦点折线图','{"symbol":"circle","xAxisData":"轴数据自动补全","smooth":false}','com.spdb.nrpt.entity.custom.Line.Line002');
insert into Report_CustomEchartsTable VALUES ('Radar001','雷达图','{"indicator":"轴数据自动补全"}','com.spdb.nrpt.entity.custom.radar.Radar001');
insert into Report_CustomEchartsTable VALUES ('Pie001','无突出饼图','{"legend":"轴数据自动补全"}','com.spdb.nrpt.entity.custom.Pie.Pie001');
insert into Report_CustomEchartsTable VALUES ('Pie002','突出饼图','{"legend":"轴数据自动补全"}','com.spdb.nrpt.entity.custom.Pie.Pie001');
insert into Report_CustomEchartsTable VALUES ('Waterfall001','瀑布图','{"xAxis":"轴数据自动补全"}','com.spdb.nrpt.entity.custom.waterfall.Waterfall001');
insert into Report_CustomEchartsTable VALUES ('YBar001','条形图','{"yAxis":"轴数据自动补全"}','com.spdb.nrpt.entity.custom.Bar.YBar001');
insert into Report_CustomEchartsTable VALUES ('Funnel001','漏斗图','{"legend":"轴数据自动补全"}','com.spdb.nrpt.entity.custom.Funnel.Funnel001');
insert into Report_CustomEchartsTable VALUES ('Bar002','今日, 上日, 上月堆叠柱状图','{"stack":"","legend":"图例数据自动补全","xData":["今日","上日","上月"]}','com.spdb.nrpt.entity.custom.Bar.Bar002');
insert into Report_CustomEchartsTable VALUES ('Bar003','多指标柱状图','{"legend":"图例数据自动补全","xData":["今日","上日","上月"]}','com.spdb.nrpt.entity.custom.Bar.Bar003');
insert into Report_CustomEchartsTable VALUES ('Line003','多指标折线图','{"symbol":"circle","legend":"图例数据自动补全","xAxisData":"轴数据自动补全","smooth":false}','com.spdb.nrpt.entity.custom.Line.Line003');
insert into Report_CustomEchartsTable VALUES ('Pie003','环形图','{"legend":"图例数据自动补全","radius":["35%","60%"]}','com.spdb.nrpt.entity.custom.Pie.Pie003');
insert into Report_CustomEchartsTable VALUES ('YBar002','多指标条形图','{"yAxis":"轴数据自动补全","legend":"图例数据自动补全"}','com.spdb.nrpt.entity.custom.Bar.YBar002');
insert into Report_CustomEchartsTable VALUES ('YBar003','堆叠条形图','{"stack":"1","yAxis":"轴数据自动补全","legend":"图例数据自动补全"}','com.spdb.nrpt.entity.custom.Bar.YBar003');
insert into Report_CustomEchartsTable VALUES ('Pie004','环形图拼接饼图','{"legend":"图例数据自动补全","radius":[["40%","55%"],["0%","28"]]}','com.spdb.nrpt.entity.custom.Pie.Pie004');