package com.spdb.nrpt.Initialize;

import com.alibaba.fastjson.JSONObject;
import com.spdb.nrpt.config.VariableContext;
import com.spdb.nrpt.entity.bridge.DivIDKeyID;
import com.spdb.nrpt.entity.procedure.OutData;
import com.spdb.nrpt.entity.procedure.ProcedureInParams;
import com.spdb.nrpt.entity.procedure.ProcedureOutParams;
import com.spdb.nrpt.entity.procedure.SoapInParams;
import com.spdb.nrpt.entity.echarts.Grid;
import com.spdb.nrpt.entity.echarts.Indicator;
import com.spdb.nrpt.entity.echarts.Legend;
import com.spdb.nrpt.entity.echarts.Radar;
import com.spdb.nrpt.entity.echarts.bar.BarEntity;
import com.spdb.nrpt.entity.echarts.line.LineEntity;
import com.spdb.nrpt.entity.echarts.pie.PieEntity;
import com.spdb.nrpt.entity.echarts.pie.PieSeries;
import com.spdb.nrpt.entity.echarts.radar.RadarEntity;
import com.spdb.nrpt.entity.report.ReportEchartsEntity;
import com.spdb.nrpt.mapper.reportDataMapper.NRPTReportDataMapper;
import com.spdb.nrpt.service.webservice.EsbSoap;
import com.spdb.nrpt.util.NumUtil;
import com.spdb.nrpt.util.StringUtil;
import com.spdb.nrpt.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@Slf4j
//投产初始化数据类
public class InitializeController {

    @Autowired
    private NRPTReportDataMapper nrptReportDataMapper;

    //初始化report1信息
    @RequestMapping("/InitializeReport1Echarts")
    @ResponseBody
    public String InitializeReport1Echarts() {

        nrptReportDataMapper.deleteEchartsTableByID("1001%");

        ReportEchartsEntity reportEchartsEntity1 = new ReportEchartsEntity();
        reportEchartsEntity1.setDivID("100101");
        reportEchartsEntity1.setDivName("大屏1信息总览");
        reportEchartsEntity1.setDivEchartsJson("none");
        reportEchartsEntity1.setDivEchartsType("none");
        nrptReportDataMapper.insertEcharts(reportEchartsEntity1);

        ReportEchartsEntity reportEchartsEntity2 = new ReportEchartsEntity();
        reportEchartsEntity2.setDivID("100102");
        reportEchartsEntity2.setDivName("大屏1全行存款");

        //Echarts拼装
        BarEntity barEntity = new BarEntity();
        Legend legend2 = new Legend();
        List<String> data21 = Arrays.asList("个人存款","对公存款");
        legend2.setData(data21);
        barEntity.setLegend(legend2);

        Grid grid2 = new Grid();
        grid2.setHeight("");
        grid2.setLeft("");
        grid2.setTop("");
        grid2.setWidth("");
        barEntity.setGrid(grid2);

        reportEchartsEntity2.setDivEchartsJson(JSONObject.toJSONString(barEntity));
        reportEchartsEntity2.setDivEchartsType("com.spdb.nrpt.entity.echarts.bar.BarEntity");
        nrptReportDataMapper.insertEcharts(reportEchartsEntity2);

        ReportEchartsEntity reportEchartsEntity3 = new ReportEchartsEntity();
        reportEchartsEntity3.setDivID("100103");
        reportEchartsEntity3.setDivName("大屏1全行贷款");

        PieEntity pieEntity = new PieEntity();
        Grid grid3 = new Grid();
        grid3.setHeight("");
        grid3.setLeft("");
        grid3.setTop("");
        grid3.setWidth("");
        pieEntity.setGrid(grid3);

        List<Legend> legend3List = new LinkedList<>();
        Legend legend31 = new Legend();
        List<String> data31 = Arrays.asList("住房按揭","经营性","商用房","消费","其他");
        legend31.setData(data31);
        legend3List.add(legend31);
        Legend legend32 = new Legend();
        List<String> data32 = Arrays.asList("逾期","对公中长期","对公短期","票据融资");
        legend32.setData(data32);
        legend3List.add(legend32);
        pieEntity.setLegend(legend3List);

        List<PieSeries> series3 = new ArrayList<>();
        PieSeries pieSeries31 = new PieSeries();
        List<String> radius31 = Arrays.asList("42%","60%");
        pieSeries31.setRadius(radius31);
        series3.add(pieSeries31);
        PieSeries pieSeries32 = new PieSeries();
        List<String> radius32 = Arrays.asList("0%","30%'");
        pieSeries32.setRadius(radius32);
        series3.add(pieSeries32);
        pieEntity.setSeries(series3);

        reportEchartsEntity3.setDivEchartsJson(JSONObject.toJSONString(pieEntity));
        reportEchartsEntity3.setDivEchartsType("com.spdb.nrpt.entity.echarts.pie.PieEntity");
        nrptReportDataMapper.insertEcharts(reportEchartsEntity3);

        ReportEchartsEntity reportEchartsEntity4 = new ReportEchartsEntity();
        reportEchartsEntity4.setDivID("100104");
        reportEchartsEntity4.setDivName("大屏1地图");

        reportEchartsEntity4.setDivEchartsJson("none");
        reportEchartsEntity4.setDivEchartsType("none");
        nrptReportDataMapper.insertEcharts(reportEchartsEntity4);

        ReportEchartsEntity reportEchartsEntity5 = new ReportEchartsEntity();
        reportEchartsEntity5.setDivID("100105");
        reportEchartsEntity5.setDivName("大屏1全行存款排名");

        BarEntity barEntity2 = new BarEntity();
        Grid grid5 = new Grid();
        grid5.setHeight("");
        grid5.setLeft("");
        grid5.setTop("");
        grid5.setWidth("");
        barEntity2.setGrid(grid5);

        reportEchartsEntity5.setDivEchartsJson(JSONObject.toJSONString(barEntity2));
        reportEchartsEntity5.setDivEchartsType("com.spdb.nrpt.entity.echarts.bar.BarEntity");
        nrptReportDataMapper.insertEcharts(reportEchartsEntity5);

        ReportEchartsEntity reportEchartsEntity6 = new ReportEchartsEntity();
        reportEchartsEntity6.setDivID("100106");
        reportEchartsEntity6.setDivName("大屏1全行贷款排名");

        BarEntity barEntity3 = new BarEntity();
        Grid grid6 = new Grid();
        grid6.setHeight("");
        grid6.setLeft("");
        grid6.setTop("");
        grid6.setWidth("");
        barEntity3.setGrid(grid6);

        reportEchartsEntity6.setDivEchartsJson(JSONObject.toJSONString(barEntity3));
        reportEchartsEntity6.setDivEchartsType("com.spdb.nrpt.entity.echarts.bar.BarEntity");
        nrptReportDataMapper.insertEcharts(reportEchartsEntity6);

        ReportEchartsEntity reportEchartsEntity7 = new ReportEchartsEntity();
        reportEchartsEntity7.setDivID("100107");
        reportEchartsEntity7.setDivName("大屏1营收趋势");

        LineEntity lineEntity = new LineEntity();
        Grid grid7 = new Grid();
        grid7.setHeight("");
        grid7.setLeft("");
        grid7.setTop("");
        grid7.setWidth("");
        lineEntity.setGrid(grid7);

        reportEchartsEntity7.setDivEchartsJson(JSONObject.toJSONString(lineEntity));
        reportEchartsEntity7.setDivEchartsType("com.spdb.nrpt.entity.echarts.line.LineEntity");
        nrptReportDataMapper.insertEcharts(reportEchartsEntity7);

        ReportEchartsEntity reportEchartsEntity8 = new ReportEchartsEntity();
        reportEchartsEntity8.setDivID("100108");
        reportEchartsEntity8.setDivName("大屏1分行存贷款情况");
        reportEchartsEntity8.setDivEchartsJson("none");
        reportEchartsEntity8.setDivEchartsType("none");
        nrptReportDataMapper.insertEcharts(reportEchartsEntity8);

        ReportEchartsEntity reportEchartsEntity9 = new ReportEchartsEntity();
        reportEchartsEntity9.setDivID("100109");
        reportEchartsEntity9.setDivName("大屏1同业负债结构");

        RadarEntity radarEntity = new RadarEntity();
        Radar radar = new Radar();
        Indicator indicator95 = new Indicator("同业存放定期","8000");
        Indicator indicator92 = new Indicator("同业存放活期","8000");
        Indicator indicator93 = new Indicator("同业定期存单","8000");
        Indicator indicator91 = new Indicator("货币市场交易","8000");
        Indicator indicator94 = new Indicator("卖出回购票据","8000");
        List<Indicator> indicatorList = Arrays.asList(indicator95,indicator92,indicator93,indicator91,indicator94);
        radar.setIndicator(indicatorList);
        radar.setRadius("50%");
        radarEntity.setRadar(radar);

        reportEchartsEntity9.setDivEchartsJson(JSONObject.toJSONString(radarEntity));
        reportEchartsEntity9.setDivEchartsType("com.spdb.nrpt.entity.echarts.radar.RadarEntity");
        nrptReportDataMapper.insertEcharts(reportEchartsEntity9);

        Map<String,String> orgs = VariableContext.Orgs;
        Iterator<Map.Entry<String, String>> car = orgs.entrySet().iterator();
        while (car.hasNext()) {
            Map.Entry<String, String> entry = car.next();
            ReportEchartsEntity reportEchartsEntity10 = new ReportEchartsEntity();
            reportEchartsEntity10.setDivID("100109"+entry.getKey());
            reportEchartsEntity10.setDivName("大屏1同业负债结构");

            RadarEntity radarEntity2 = new RadarEntity();
            Radar radar2 = new Radar();
            Indicator indicator101 = new Indicator("同业存放定期","200");
            Indicator indicator102 = new Indicator("同业存放活期","1700");
            Indicator indicator103 = new Indicator("同业定期存单","1700");
            Indicator indicator104 = new Indicator("货币市场交易","1700");
            Indicator indicator105 = new Indicator("卖出回购票据","100");
            List<Indicator> indicatorList2 = Arrays.asList(indicator101,indicator102,indicator103,indicator104,indicator105);
            radar2.setIndicator(indicatorList2);
            radar2.setRadius("50%");
            radarEntity2.setRadar(radar2);

            reportEchartsEntity10.setDivEchartsJson(JSONObject.toJSONString(radarEntity2));
            reportEchartsEntity10.setDivEchartsType("com.spdb.nrpt.entity.echarts.radar.RadarEntity");
            nrptReportDataMapper.insertEcharts(reportEchartsEntity10);
        }

        return "Success";
    }

    //初始化report2信息
    @RequestMapping("/InitializeReport2Echarts")
    @ResponseBody
    public String InitializeReport2Echarts() {

        nrptReportDataMapper.deleteEchartsTableByID("1002%");

        ReportEchartsEntity reportEchartsEntity1 = new ReportEchartsEntity();
        reportEchartsEntity1.setDivID("100201");
        reportEchartsEntity1.setDivName("大屏2柱状图左1");
        BarEntity barEntity1 = new BarEntity();
        Grid grid1 = new Grid();
        grid1.setHeight("");
        grid1.setLeft("");
        grid1.setTop("");
        grid1.setWidth("");
        barEntity1.setGrid(grid1);
        reportEchartsEntity1.setDivEchartsJson(JSONObject.toJSONString(barEntity1));
        reportEchartsEntity1.setDivEchartsType("com.spdb.nrpt.entity.echarts.bar.BarEntity");
        nrptReportDataMapper.insertEcharts(reportEchartsEntity1);

        ReportEchartsEntity reportEchartsEntity2 = new ReportEchartsEntity();
        reportEchartsEntity2.setDivID("100202");
        reportEchartsEntity2.setDivName("大屏2柱状图左2");
        BarEntity barEntity2 = new BarEntity();
        Grid grid2 = new Grid();
        grid2.setHeight("");
        grid2.setLeft("");
        grid2.setTop("");
        grid2.setWidth("");
        barEntity2.setGrid(grid1);
        reportEchartsEntity2.setDivEchartsJson(JSONObject.toJSONString(barEntity2));
        reportEchartsEntity2.setDivEchartsType("com.spdb.nrpt.entity.echarts.bar.BarEntity");
        nrptReportDataMapper.insertEcharts(reportEchartsEntity2);

        ReportEchartsEntity reportEchartsEntity3 = new ReportEchartsEntity();
        reportEchartsEntity3.setDivID("100203");
        reportEchartsEntity3.setDivName("大屏2树图");
        reportEchartsEntity3.setDivEchartsJson("none");
        reportEchartsEntity3.setDivEchartsType("none");
        nrptReportDataMapper.insertEcharts(reportEchartsEntity3);

        ReportEchartsEntity reportEchartsEntity4 = new ReportEchartsEntity();
        reportEchartsEntity4.setDivID("100204");
        reportEchartsEntity4.setDivName("大屏2公司贷款利息收入趋势");
        LineEntity lineEntity = new LineEntity();
        Grid grid4 = new Grid();
        lineEntity.setGrid(grid4);
        reportEchartsEntity4.setDivEchartsJson(JSONObject.toJSONString(lineEntity));
        reportEchartsEntity4.setDivEchartsType("com.spdb.nrpt.entity.echarts.line.LineEntity");
        nrptReportDataMapper.insertEcharts(reportEchartsEntity4);

        ReportEchartsEntity reportEchartsEntity5 = new ReportEchartsEntity();
        reportEchartsEntity5.setDivID("100205");
        reportEchartsEntity5.setDivName("大屏2存款分析");
        PieEntity pieEntity1 = new PieEntity();
        Legend legend1 = new Legend();
        List<String> data1 = Arrays.asList("对公基础性存款","对公结构性存款","主动负债+单位保本理财");
        legend1.setData(data1);
        pieEntity1.setLegend(legend1);
        PieSeries pieSeries1 = new PieSeries();
        List<String> radius1 = Arrays.asList("25%","50%");
        pieSeries1.setRadius(radius1);
        List<PieSeries> pieSeriesList1 = Arrays.asList(pieSeries1);
        pieEntity1.setSeries(pieSeriesList1);
        Grid grid5 = new Grid();
        pieEntity1.setGrid(grid5);
        reportEchartsEntity5.setDivEchartsJson(JSONObject.toJSONString(pieEntity1));
        reportEchartsEntity5.setDivEchartsType("com.spdb.nrpt.entity.echarts.pie.PieEntity");
        nrptReportDataMapper.insertEcharts(reportEchartsEntity5);

        ReportEchartsEntity reportEchartsEntity6 = new ReportEchartsEntity();
        reportEchartsEntity6.setDivID("100206");
        reportEchartsEntity6.setDivName("大屏2贷款分析");
        PieEntity pieEntity2 = new PieEntity();
        Legend legend2 = new Legend();
        List<String> data2 = Arrays.asList("对公短期贷款","对公中长期贷款","逾期贷款","票据融资");
        legend2.setData(data2);
        pieEntity2.setLegend(legend2);
        PieSeries pieSeries2 = new PieSeries();
        List<String> radius2 = Arrays.asList("25%","50%");
        pieSeries2.setRadius(radius2);
        List<PieSeries> pieSeriesList2 = Arrays.asList(pieSeries2);
        pieEntity2.setSeries(pieSeriesList2);
        Grid grid6 = new Grid();
        pieEntity2.setGrid(grid6);
        reportEchartsEntity6.setDivEchartsJson(JSONObject.toJSONString(pieEntity2));
        reportEchartsEntity6.setDivEchartsType("com.spdb.nrpt.entity.echarts.pie.PieEntity");
        nrptReportDataMapper.insertEcharts(reportEchartsEntity6);

        ReportEchartsEntity reportEchartsEntity7 = new ReportEchartsEntity();
        reportEchartsEntity7.setDivID("100207");
        reportEchartsEntity7.setDivName("大屏2公司客户数");
        BarEntity barEntity3 = new BarEntity();
        Grid grid7 = new Grid();
        barEntity3.setGrid(grid7);
        reportEchartsEntity7.setDivEchartsJson(JSONObject.toJSONString(barEntity3));
        reportEchartsEntity7.setDivEchartsType("com.spdb.nrpt.entity.echarts.bar.BarEntity");
        nrptReportDataMapper.insertEcharts(reportEchartsEntity7);
        return "Success";
    }

    //初始化report3信息
    @RequestMapping("/InitializeReport3Echarts")
    @ResponseBody
    public String InitializeReport3Echarts() {

        nrptReportDataMapper.deleteEchartsTableByID("1003%");

        ReportEchartsEntity reportEchartsEntity1 = new ReportEchartsEntity();
        reportEchartsEntity1.setDivID("100301");
        reportEchartsEntity1.setDivName("大屏3柱状图左1");
        BarEntity barEntity1 = new BarEntity();
        Grid grid1 = new Grid();
        grid1.setHeight("");
        grid1.setLeft("");
        grid1.setTop("");
        grid1.setWidth("");
        barEntity1.setGrid(grid1);
        reportEchartsEntity1.setDivEchartsJson(JSONObject.toJSONString(barEntity1));
        reportEchartsEntity1.setDivEchartsType("com.spdb.nrpt.entity.echarts.bar.BarEntity");
        nrptReportDataMapper.insertEcharts(reportEchartsEntity1);

        ReportEchartsEntity reportEchartsEntity2 = new ReportEchartsEntity();
        reportEchartsEntity2.setDivID("100302");
        reportEchartsEntity2.setDivName("大屏3柱状图左2");
        BarEntity barEntity2 = new BarEntity();
        Grid grid2 = new Grid();
        grid2.setHeight("");
        grid2.setLeft("");
        grid2.setTop("");
        grid2.setWidth("");
        barEntity2.setGrid(grid1);
        reportEchartsEntity2.setDivEchartsJson(JSONObject.toJSONString(barEntity2));
        reportEchartsEntity2.setDivEchartsType("com.spdb.nrpt.entity.echarts.bar.BarEntity");
        nrptReportDataMapper.insertEcharts(reportEchartsEntity2);

        ReportEchartsEntity reportEchartsEntity3 = new ReportEchartsEntity();
        reportEchartsEntity3.setDivID("100303");
        reportEchartsEntity3.setDivName("大屏2云图");
        reportEchartsEntity3.setDivEchartsJson("none");
        reportEchartsEntity3.setDivEchartsType("none");
        nrptReportDataMapper.insertEcharts(reportEchartsEntity3);

        ReportEchartsEntity reportEchartsEntity4 = new ReportEchartsEntity();
        reportEchartsEntity4.setDivID("100304");
        reportEchartsEntity4.setDivName("大屏2零售贷款利息趋势");
        LineEntity lineEntity = new LineEntity();
        Grid grid4 = new Grid();
        lineEntity.setGrid(grid4);
        reportEchartsEntity4.setDivEchartsJson(JSONObject.toJSONString(lineEntity));
        reportEchartsEntity4.setDivEchartsType("com.spdb.nrpt.entity.echarts.line.LineEntity");
        nrptReportDataMapper.insertEcharts(reportEchartsEntity4);

        ReportEchartsEntity reportEchartsEntity5 = new ReportEchartsEntity();
        reportEchartsEntity5.setDivID("100305");
        reportEchartsEntity5.setDivName("大屏3存款分析");
        PieEntity pieEntity1 = new PieEntity();
        Legend legend1 = new Legend();
        List<String> data1 = Arrays.asList("结算性存款","其他定期储蓄存款","大额存单","结构性存款");
        legend1.setData(data1);
        pieEntity1.setLegend(legend1);
        PieSeries pieSeries1 = new PieSeries();
        List<String> radius1 = Arrays.asList("25%","50%");
        pieSeries1.setRadius(radius1);
        List<PieSeries> pieSeriesList1 = Arrays.asList(pieSeries1);
        pieEntity1.setSeries(pieSeriesList1);
        Grid grid5 = new Grid();
        pieEntity1.setGrid(grid5);
        reportEchartsEntity5.setDivEchartsJson(JSONObject.toJSONString(pieEntity1));
        reportEchartsEntity5.setDivEchartsType("com.spdb.nrpt.entity.echarts.pie.PieEntity");
        nrptReportDataMapper.insertEcharts(reportEchartsEntity5);

        ReportEchartsEntity reportEchartsEntity6 = new ReportEchartsEntity();
        reportEchartsEntity6.setDivID("100306");
        reportEchartsEntity6.setDivName("大屏3贷款分析");
        PieEntity pieEntity2 = new PieEntity();
        Legend legend2 = new Legend();
        List<String> data2 = Arrays.asList("住房按揭贷款","经营性贷款","消费贷款","商用房贷款","其他贷款");
        legend2.setData(data2);
        pieEntity2.setLegend(legend2);
        PieSeries pieSeries2 = new PieSeries();
        List<String> radius2 = Arrays.asList("25%","50%");
        pieSeries2.setRadius(radius2);
        List<PieSeries> pieSeriesList2 = Arrays.asList(pieSeries2);
        pieEntity2.setSeries(pieSeriesList2);
        Grid grid6 = new Grid();
        pieEntity2.setGrid(grid6);
        reportEchartsEntity6.setDivEchartsJson(JSONObject.toJSONString(pieEntity2));
        reportEchartsEntity6.setDivEchartsType("com.spdb.nrpt.entity.echarts.pie.PieEntity");
        nrptReportDataMapper.insertEcharts(reportEchartsEntity6);

        ReportEchartsEntity reportEchartsEntity7 = new ReportEchartsEntity();
        reportEchartsEntity7.setDivID("100307");
        reportEchartsEntity7.setDivName("大屏2个人客户数");
        BarEntity barEntity3 = new BarEntity();
        Grid grid7 = new Grid();
        barEntity3.setGrid(grid7);
        reportEchartsEntity7.setDivEchartsJson(JSONObject.toJSONString(barEntity3));
        reportEchartsEntity7.setDivEchartsType("com.spdb.nrpt.entity.echarts.bar.BarEntity");
        nrptReportDataMapper.insertEcharts(reportEchartsEntity7);
        return "Success";
    }

    //初始化桥表数据
    @RequestMapping("/InitializeDivIDKeyID")
    @ResponseBody
    public String InitializeDivIDKeyID(){

        //清空Report_Div_KeyTable表
        nrptReportDataMapper.deleteDivKeyTable();

        DivIDKeyID divID_keyID1 = new DivIDKeyID();
        divID_keyID1.setId(StringUtil.getNextNum());
        divID_keyID1.setDivID("100101");
        divID_keyID1.setKeyID("YS01");

        DivIDKeyID divID_keyID2 = new DivIDKeyID();
        divID_keyID2.setId(StringUtil.getNextNum());
        divID_keyID2.setDivID("100101");
        divID_keyID2.setKeyID("GM01");

        DivIDKeyID divID_keyID3 = new DivIDKeyID();
        divID_keyID3.setId(StringUtil.getNextNum());
        divID_keyID3.setDivID("100101");
        divID_keyID3.setKeyID("GM03");

        //个人存款
        DivIDKeyID divID_keyID4 = new DivIDKeyID();
        divID_keyID4.setId(StringUtil.getNextNum());
        divID_keyID4.setDivID("100102");
        divID_keyID4.setKeyID("GM0102");

        //对公存款
        DivIDKeyID divID_keyID5 = new DivIDKeyID();
        divID_keyID5.setId(StringUtil.getNextNum());
        divID_keyID5.setDivID("100102");
        divID_keyID5.setKeyID("GM010101");

        DivIDKeyID divID_keyID6 = new DivIDKeyID();
        divID_keyID6.setId(StringUtil.getNextNum());
        divID_keyID6.setDivID("100103");
        divID_keyID6.setKeyID("GM030201");

        DivIDKeyID divID_keyID7 = new DivIDKeyID();
        divID_keyID7.setId(StringUtil.getNextNum());
        divID_keyID7.setDivID("100103");
        divID_keyID7.setKeyID("GM030202");

        DivIDKeyID divID_keyID8 = new DivIDKeyID();
        divID_keyID8.setId(StringUtil.getNextNum());
        divID_keyID8.setDivID("100103");
        divID_keyID8.setKeyID("GM030203");

        DivIDKeyID divID_keyID9 = new DivIDKeyID();
        divID_keyID9.setId(StringUtil.getNextNum());
        divID_keyID9.setDivID("100103");
        divID_keyID9.setKeyID("GM030204");

        DivIDKeyID divID_keyID10 = new DivIDKeyID();
        divID_keyID10.setId(StringUtil.getNextNum());
        divID_keyID10.setDivID("100103");
        divID_keyID10.setKeyID("GM030205");

        DivIDKeyID divID_keyID11 = new DivIDKeyID();
        divID_keyID11.setId(StringUtil.getNextNum());
        divID_keyID11.setDivID("100103");
        divID_keyID11.setKeyID("GM03010103");

        DivIDKeyID divID_keyID12 = new DivIDKeyID();
        divID_keyID12.setId(StringUtil.getNextNum());
        divID_keyID12.setDivID("100103");
        divID_keyID12.setKeyID("GM03010101");

        DivIDKeyID divID_keyID13 = new DivIDKeyID();
        divID_keyID13.setId(StringUtil.getNextNum());
        divID_keyID13.setDivID("100103");
        divID_keyID13.setKeyID("GM03010102");

        DivIDKeyID divID_keyID14 = new DivIDKeyID();
        divID_keyID14.setId(StringUtil.getNextNum());
        divID_keyID14.setDivID("100103");
        divID_keyID14.setKeyID("GM030102");

        //地图
        DivIDKeyID divID_keyID1501 = new DivIDKeyID();
        divID_keyID1501.setId(StringUtil.getNextNum());
        divID_keyID1501.setDivID("100104");
        divID_keyID1501.setKeyID("GM01");

        DivIDKeyID divID_keyID1502 = new DivIDKeyID();
        divID_keyID1502.setId(StringUtil.getNextNum());
        divID_keyID1502.setDivID("100104");
        divID_keyID1502.setKeyID("GM03");

        //全行存款排名
        DivIDKeyID divID_keyID16 = new DivIDKeyID();
        divID_keyID16.setId(StringUtil.getNextNum());
        divID_keyID16.setDivID("100105");
        divID_keyID16.setKeyID("GM01");

        //全行贷款排名
        DivIDKeyID divID_keyID17 = new DivIDKeyID();
        divID_keyID17.setId(StringUtil.getNextNum());
        divID_keyID17.setDivID("100106");
        divID_keyID17.setKeyID("GM03");

        //营收趋势
        DivIDKeyID divID_keyID18 = new DivIDKeyID();
        divID_keyID18.setId(StringUtil.getNextNum());
        divID_keyID18.setDivID("100107");
        divID_keyID18.setKeyID("YS01");

        //分行存贷款情况
        DivIDKeyID divID_keyID1901 = new DivIDKeyID();
        divID_keyID1901.setId(StringUtil.getNextNum());
        divID_keyID1901.setDivID("100108");
        divID_keyID1901.setKeyID("GM01");

        //分行存贷款情况
        DivIDKeyID divID_keyID1902 = new DivIDKeyID();
        divID_keyID1902.setId(StringUtil.getNextNum());
        divID_keyID1902.setDivID("100108");
        divID_keyID1902.setKeyID("GM03");

        //同业负债结构
        DivIDKeyID divID_keyID20 = new DivIDKeyID();
        divID_keyID20.setId(StringUtil.getNextNum());
        divID_keyID20.setDivID("100109");
        divID_keyID20.setKeyID("GM020101");

        DivIDKeyID divID_keyID21 = new DivIDKeyID();
        divID_keyID21.setId(StringUtil.getNextNum());
        divID_keyID21.setDivID("100109");
        divID_keyID21.setKeyID("GM020102");

        DivIDKeyID divID_keyID22 = new DivIDKeyID();
        divID_keyID22.setId(StringUtil.getNextNum());
        divID_keyID22.setDivID("100109");
        divID_keyID22.setKeyID("GM0203");

        DivIDKeyID divID_keyID23 = new DivIDKeyID();
        divID_keyID23.setId(StringUtil.getNextNum());
        divID_keyID23.setDivID("100109");
        divID_keyID23.setKeyID("GM0202");

        DivIDKeyID divID_keyID24 = new DivIDKeyID();
        divID_keyID24.setId(StringUtil.getNextNum());
        divID_keyID24.setDivID("100109");
        divID_keyID24.setKeyID("GM0204");

        List<DivIDKeyID> dataList = Arrays.asList(divID_keyID1,divID_keyID2,divID_keyID3,divID_keyID4,divID_keyID5,divID_keyID6,divID_keyID7,divID_keyID8,divID_keyID9,divID_keyID10,divID_keyID11,divID_keyID12,divID_keyID13,divID_keyID14,divID_keyID1501,divID_keyID1502,divID_keyID16,divID_keyID17,divID_keyID18,divID_keyID1901,divID_keyID1902,divID_keyID20,divID_keyID21,divID_keyID22,divID_keyID23,divID_keyID24);

        List lists=new ArrayList(dataList);

        //第二张报表初始化桥表
        //一般对公存款
        DivIDKeyID divIDKeyID100201 = new DivIDKeyID();
        divIDKeyID100201.setId(StringUtil.getNextNum());
        divIDKeyID100201.setDivID("100201");
        divIDKeyID100201.setKeyID("GM0101");
        lists.add(divIDKeyID100201);

        //一般对公存款
        DivIDKeyID divIDKeyID100202 = new DivIDKeyID();
        divIDKeyID100202.setId(StringUtil.getNextNum());
        divIDKeyID100202.setDivID("100202");
        divIDKeyID100202.setKeyID("GM0101");
        lists.add(divIDKeyID100202);

        //树图
        DivIDKeyID divIDKeyID100203 = new DivIDKeyID();
        divIDKeyID100203.setId(StringUtil.getNextNum());
        divIDKeyID100203.setDivID("100203");
        divIDKeyID100203.setKeyID("GM0101");
        lists.add(divIDKeyID100203);

        //公司贷款利息收入
        DivIDKeyID divIDKeyID100204 = new DivIDKeyID();
        divIDKeyID100204.setId(StringUtil.getNextNum());
        divIDKeyID100204.setDivID("100204");
        divIDKeyID100204.setKeyID("YS01010101");
        lists.add(divIDKeyID100204);

        //存款分析  对公基础性存款
        DivIDKeyID divIDKeyID10020501 = new DivIDKeyID();
        divIDKeyID10020501.setId(StringUtil.getNextNum());
        divIDKeyID10020501.setDivID("100205");
        divIDKeyID10020501.setKeyID("GM01010102");
        lists.add(divIDKeyID10020501);

        //存款分析  结构性存款
        DivIDKeyID divIDKeyID10020502 = new DivIDKeyID();
        divIDKeyID10020502.setId(StringUtil.getNextNum());
        divIDKeyID10020502.setDivID("100205");
        divIDKeyID10020502.setKeyID("GM01010101");
        lists.add(divIDKeyID10020502);

        //存款分析  主动负债+单位保本理财     对公存款-对公基础性存款-结构性存款
        DivIDKeyID divIDKeyID10020503 = new DivIDKeyID();
        divIDKeyID10020503.setId(StringUtil.getNextNum());
        divIDKeyID10020503.setDivID("100205");
        divIDKeyID10020503.setKeyID("NRPT01");
        lists.add(divIDKeyID10020503);

        //贷款分析  对公短期贷款
        DivIDKeyID divIDKeyID10020601 = new DivIDKeyID();
        divIDKeyID10020601.setId(StringUtil.getNextNum());
        divIDKeyID10020601.setDivID("100206");
        divIDKeyID10020601.setKeyID("GM03010102");
        lists.add(divIDKeyID10020601);

        //贷款分析  对公中长期贷款
        DivIDKeyID divIDKeyID10020602 = new DivIDKeyID();
        divIDKeyID10020602.setId(StringUtil.getNextNum());
        divIDKeyID10020602.setDivID("100206");
        divIDKeyID10020602.setKeyID("GM03010101");
        lists.add(divIDKeyID10020602);

        //贷款分析  逾期贷款
        DivIDKeyID divIDKeyID10020603 = new DivIDKeyID();
        divIDKeyID10020603.setId(StringUtil.getNextNum());
        divIDKeyID10020603.setDivID("100206");
        divIDKeyID10020603.setKeyID("GM03010103");
        lists.add(divIDKeyID10020603);

        //贷款分析  票据融资
        DivIDKeyID divIDKeyID10020604 = new DivIDKeyID();
        divIDKeyID10020604.setId(StringUtil.getNextNum());
        divIDKeyID10020604.setDivID("100206");
        divIDKeyID10020604.setKeyID("GM030102");
        lists.add(divIDKeyID10020604);

        //公司客户数
        DivIDKeyID divIDKeyID100207 = new DivIDKeyID();
        divIDKeyID100207.setId(StringUtil.getNextNum());
        divIDKeyID100207.setDivID("100207");
        divIDKeyID100207.setKeyID("02");
        lists.add(divIDKeyID100207);

        //第三张报表桥表
        //保证金存款排名前十
        DivIDKeyID divIDKeyID100301 = new DivIDKeyID();
        divIDKeyID100301.setId(StringUtil.getNextNum());
        divIDKeyID100301.setDivID("100301");
        divIDKeyID100301.setKeyID("GM010204");
        lists.add(divIDKeyID100301);

        //保证金存款排名后十
        DivIDKeyID divIDKeyID100302 = new DivIDKeyID();
        divIDKeyID100302.setId(StringUtil.getNextNum());
        divIDKeyID100302.setDivID("100302");
        divIDKeyID100302.setKeyID("GM010204");
        lists.add(divIDKeyID100302);

        //保证金存款
        DivIDKeyID divIDKeyID100303 = new DivIDKeyID();
        divIDKeyID100303.setId(StringUtil.getNextNum());
        divIDKeyID100303.setDivID("100303");
        divIDKeyID100303.setKeyID("GM010204");
        lists.add(divIDKeyID100303);

        //零售贷款利息收入
        DivIDKeyID divIDKeyID100304 = new DivIDKeyID();
        divIDKeyID100304.setId(StringUtil.getNextNum());
        divIDKeyID100304.setDivID("100304");
        divIDKeyID100304.setKeyID("YS01010102");
        lists.add(divIDKeyID100304);

        //存款分析
        //结算性存款
        DivIDKeyID divIDKeyID10030501 = new DivIDKeyID();
        divIDKeyID10030501.setId(StringUtil.getNextNum());
        divIDKeyID10030501.setDivID("100305");
        divIDKeyID10030501.setKeyID("GM0101010201");
        lists.add(divIDKeyID10030501);

        //其它定期储蓄存款
        DivIDKeyID divIDKeyID10030502 = new DivIDKeyID();
        divIDKeyID10030502.setId(StringUtil.getNextNum());
        divIDKeyID10030502.setDivID("100305");
        divIDKeyID10030502.setKeyID("GM010201");
        lists.add(divIDKeyID10030502);

        //大额存单
        DivIDKeyID divIDKeyID10030503 = new DivIDKeyID();
        divIDKeyID10030503.setId(StringUtil.getNextNum());
        divIDKeyID10030503.setDivID("100305");
        divIDKeyID10030503.setKeyID("GM010203");
        lists.add(divIDKeyID10030503);

        //结构性存款
        DivIDKeyID divIDKeyID10030504 = new DivIDKeyID();
        divIDKeyID10030504.setId(StringUtil.getNextNum());
        divIDKeyID10030504.setDivID("100305");
        divIDKeyID10030504.setKeyID("GM010205");
        lists.add(divIDKeyID10030504);

        //贷款分析
        //住房按揭贷款
        DivIDKeyID divIDKeyID10030601 = new DivIDKeyID();
        divIDKeyID10030601.setId(StringUtil.getNextNum());
        divIDKeyID10030601.setDivID("100306");
        divIDKeyID10030601.setKeyID("GM030201");
        lists.add(divIDKeyID10030601);

        //经营性贷款
        DivIDKeyID divIDKeyID10030602 = new DivIDKeyID();
        divIDKeyID10030602.setId(StringUtil.getNextNum());
        divIDKeyID10030602.setDivID("100306");
        divIDKeyID10030602.setKeyID("GM030202");
        lists.add(divIDKeyID10030602);

        //消费贷款
        DivIDKeyID divIDKeyID10030603 = new DivIDKeyID();
        divIDKeyID10030603.setId(StringUtil.getNextNum());
        divIDKeyID10030603.setDivID("100306");
        divIDKeyID10030603.setKeyID("GM030204");
        lists.add(divIDKeyID10030603);

        //商用房贷款
        DivIDKeyID divIDKeyID10030604 = new DivIDKeyID();
        divIDKeyID10030604.setId(StringUtil.getNextNum());
        divIDKeyID10030604.setDivID("100306");
        divIDKeyID10030604.setKeyID("GM030203");
        lists.add(divIDKeyID10030604);

        //其它贷款
        DivIDKeyID divIDKeyID10030605 = new DivIDKeyID();
        divIDKeyID10030605.setId(StringUtil.getNextNum());
        divIDKeyID10030605.setDivID("100306");
        divIDKeyID10030605.setKeyID("GM030205");
        lists.add(divIDKeyID10030605);

        //个人客户数
        DivIDKeyID divIDKeyID100307 = new DivIDKeyID();
        divIDKeyID100307.setId(StringUtil.getNextNum());
        divIDKeyID100307.setDivID("100307");
        divIDKeyID100307.setKeyID("01");
        lists.add(divIDKeyID100307);

        nrptReportDataMapper.insertBatchReportBridge(lists);


        return "Success";
    }

    //初始化数据
    @RequestMapping("/InitializeReportData")
    @ResponseBody
    public String InitializeReportData(String orgID){
        List<OutData> list = new ArrayList<>();
       Map<String,String> Keys = VariableContext.Keys;
        Iterator<Map.Entry<String, String>> it = Keys.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            OutData outData = new OutData();
            outData.setCURRENCY_CD("199");
            outData.setKPI_ID(entry.getKey());
            outData.setKPI_NAME(entry.getValue());
            outData.setKPI_VALUE("335.35");
            outData.setORG_ID("9900");
            outData.setORG_NAME("境内机构合计");
            outData.setSTATIS_DT("2019-05-06 21:22:00");
            list.add(outData);
        }

        Map<String,String> orgs = VariableContext.Orgs;
        Iterator<Map.Entry<String, String>> car = orgs.entrySet().iterator();
        int i = 1;
        while (car.hasNext()) {
            i++;
            Map.Entry<String, String> entry = car.next();
           OutData outData = new OutData();
            outData.setCURRENCY_CD("199");
            outData.setKPI_ID("GM01");
            outData.setKPI_NAME("全行存款");
            outData.setKPI_VALUE(1*i+"335.35");
            outData.setORG_ID(entry.getKey());
            outData.setORG_NAME(entry.getValue());
            outData.setSTATIS_DT("2019-05-06 21:22:00");
            list.add(outData);

            OutData outData2 = new OutData();
            outData2.setCURRENCY_CD("199");
            outData2.setKPI_ID("GM03");
            outData2.setKPI_NAME("全行贷款");
            outData2.setKPI_VALUE(1*i+"335.35");
            outData2.setORG_ID(entry.getKey());
            outData2.setORG_NAME(entry.getValue());
            outData2.setSTATIS_DT("2019-05-06 21:22:00");
            list.add(outData2);

            OutData outData3 = new OutData();
            outData3.setCURRENCY_CD("199");
            outData3.setKPI_ID("GM0101");
            outData3.setKPI_NAME("一般对公存款");
            outData3.setKPI_VALUE(1*i+"335.35");
            outData3.setORG_ID(entry.getKey());
            outData3.setORG_NAME(entry.getValue());
            outData3.setSTATIS_DT("2019-05-06 21:22:00");
            list.add(outData3);

            OutData outData4 = new OutData();
            outData4.setCURRENCY_CD("199");
            outData4.setKPI_ID("GM010204");
            outData4.setKPI_NAME("保证金存款");
            outData4.setKPI_VALUE(1*i+"335.35");
            outData4.setORG_ID(entry.getKey());
            outData4.setORG_NAME(entry.getValue());
            outData4.setSTATIS_DT("2019-05-06 21:22:00");
            list.add(outData4);

            List<String> dataList = Arrays.asList("GM03010103","GM03010101","GM03010102","GM0102","YS01","GM010101","GM0301","GM030101","GM01010101","GM01010102","GM0101010201","GM030102","GM010202","GM030205","GM010201","GM030201","GM010205","GM030202","GM030204","GM030203","GM010203");

            for (String data : dataList){
                OutData outData5 = new OutData();
                outData5.setCURRENCY_CD("199");
                outData5.setKPI_ID(data);
                outData5.setKPI_NAME(VariableContext.Keys.get(data));
                outData5.setKPI_VALUE(1*i+"335.35");
                outData5.setORG_ID(entry.getKey());
                outData5.setORG_NAME(entry.getValue());
                outData5.setSTATIS_DT("2019-05-06 21:22:00");
                list.add(outData5);
            }
        }
        nrptReportDataMapper.insertBatchReportAllData(list);
        return "Success";
    }

    @RequestMapping("/createTreeCloData")
    @ResponseBody
    //树图,云图造数
    public String insertTreeData(String orgID){
        List<String> dataList = Arrays.asList("GM010204","GM0101","GM0102","GM010101","GM0301","GM030101","GM01010101","GM01010102","GM0101010201","GM030102","GM010202","GM030205","GM010201","GM030201","GM010205","GM030202","GM030204","GM030203","GM010203");
        List<OutData> outDataList = new ArrayList<>();
        Map<String,String> map = VariableContext.Keys;
        for (int i=0;i<dataList.size();i++){
            OutData outData = new OutData();
            outData.setKPI_ID(dataList.get(i));
            outData.setSTATIS_DT(TimeUtil.getLastXDay(-1)+" 23:59:59");
            outData.setKPI_NAME(map.get(dataList.get(i)));
            outData.setORG_ID(orgID);
            outData.setORG_NAME("全行");
            outData.setCURRENCY_CD("199");
            outData.setKPI_VALUE((305+i*10)+"");
            outDataList.add(outData);

           OutData outData2 = new OutData();
            outData2.setKPI_ID(dataList.get(i));
            outData2.setKPI_NAME(map.get(dataList.get(i)));
            outData2.setORG_ID(orgID);
            outData2.setORG_NAME("全行");
            outData2.setCURRENCY_CD("199");
            outData2.setSTATIS_DT(TimeUtil.getLastDay(-1,0)+" 23:59:50");
            outData2.setKPI_VALUE((305+i*10)+"");
            outDataList.add(outData2);


        }
        nrptReportDataMapper.insertBatchReportPastData(outDataList);

        return "Success";
    }



    //初始化比上月数据
    @RequestMapping("/InitializeLastMonth")
    @ResponseBody
    public Object InitializeLastMonth() {
        EsbSoap esbSoap = new EsbSoap();
        //通过webService交互获得最新的数据
        String YYYYMMDD = TimeUtil.getTime("YYYYMMDD");
        String mmssSSS = TimeUtil.getTime("mmssSSS");
        ProcedureInParams procedureInParams = new ProcedureInParams();
        procedureInParams.setI_CURRENCY_CD("199");
        procedureInParams.setI_DATE(TimeUtil.getLastDay(-1, 0).replaceAll("-", ""));
        procedureInParams.setI_ORGIDS(VariableContext.Level1OrgIDs);
        procedureInParams.setI_NUM("10000");
        procedureInParams.setI_ORDER("asc");
        SoapInParams soapInParams = new SoapInParams();
        soapInParams.setProcedureName("proc_call_ma_screen");
        soapInParams.setSchemaName("NCMA");
        List<OutData> returnList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                procedureInParams.setI_KPIIDS("GM010204,GM0101,GM0102,GM010101,GM0301,GM030101,GM01010101,GM01010102,GM0101010201,GM030102,GM010202");
            } else if (i == 1) {
                procedureInParams.setI_KPIIDS("GM030205,GM010201,GM030201,GM010205,GM030202,GM030204,GM030203,GM010203");
            }

            soapInParams.setValues(procedureInParams);
            //发送报文
            String SoapXml = esbSoap.getsoapXml(YYYYMMDD, mmssSSS, JSONObject.toJSONString(soapInParams));
            log.info("发送的报文为：" + SoapXml);
            //回传结果
            String res = esbSoap.getData(SoapXml);
            if ("0".equals(res) || "调用失败".equals(res)) {
                log.error("请求获取最新数据出错,错误信息为" + res);
                return null;
            }
            ProcedureOutParams procedureOutParams = JSONObject.parseObject(res, ProcedureOutParams.class);
            List<OutData> dataList = procedureOutParams.getO_REF();
            if (dataList.size() < 1) {
                log.error("当前调用存储过程成功，但无数据");
                return null;
            }
            for (OutData outData : dataList) {
                outData.setKPI_VALUE(NumUtil.dealNum(outData.getKPI_VALUE(), 100000000, 0));
                outData.setSTATIS_DT(TimeUtil.getLastDay(-1, 0) + " 23:59:50");
                returnList.add(outData);
            }
            nrptReportDataMapper.insertBatchReportPastData(dataList);

        }
        return returnList;
    }

    //测试传输数据
    @RequestMapping("/testData")
    @ResponseBody
    public Object testData(String time,String orgID,String keyID,String number,String order){
        EsbSoap esbSoap = new EsbSoap();
        //通过webService交互获得最新的数据
        String YYYYMMDD = TimeUtil.getTime("YYYYMMDD");
        String mmssSSS = TimeUtil.getTime("mmssSSS");
        ProcedureInParams procedureInParams = new ProcedureInParams();
        procedureInParams.setI_CURRENCY_CD("199");
        procedureInParams.setI_DATE(time);
        procedureInParams.setI_ORGIDS(orgID);
        procedureInParams.setI_NUM(number);
        procedureInParams.setI_ORDER(order);
        SoapInParams soapInParams = new SoapInParams();
        soapInParams.setProcedureName("proc_call_ma_screen");
        soapInParams.setSchemaName("NCMA");
        procedureInParams.setI_KPIIDS(keyID);
        procedureInParams.setI_NUM(number);
        procedureInParams.setI_ORDER(order);
        soapInParams.setValues(procedureInParams);
        //发送报文
        String SoapXml = esbSoap.getsoapXml(YYYYMMDD, mmssSSS, JSONObject.toJSONString(soapInParams));
        log.info("发送的报文为：" + SoapXml);
        //回传结果
        String res = esbSoap.getData(SoapXml);
        if ("0".equals(res)||res.contains("调用失败")){
            log.error("请求获取最新数据出错，详细信息为："+res);
            return "请求获取最新数据出错";
        }
        ProcedureOutParams procedureOutParams = JSONObject.parseObject(res, ProcedureOutParams.class);
        List<OutData> dataList = procedureOutParams.getO_REF();
        if (dataList.size() < 1) {
            log.error("当前调用存储过程成功，但无数据");
            return "当前调用存储过程成功，但无数据";
        }
        return dataList;
    }

}
