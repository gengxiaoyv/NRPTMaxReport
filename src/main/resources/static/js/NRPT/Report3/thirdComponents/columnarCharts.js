
var columnarOptions = {
    grid:{
        top:"10%",    //位置的调整
        left:'16%',
        height:'70%',
        width:'70%',
        borderColor:"red",
    },
    xAxis: {
        type : 'category',
        data : ["2019", "2020", "2021", "2022", "2023", "2024", "2025"],
        axisTick:{
            show:false
        },
        boundaryGap: true,
        axisLabel: {
            interval:0,
            fontSize:10,
            rotate: 45, // 20度角倾斜显示(***这里是关键)
            margin:10,
            textStyle: {
                color: '#fff'   //折线的颜色
            }
        },
        axisLine:{
            lineStyle:{
                color:'#FFF', //坐标的颜色
                opacity:0.5
            },
        },
    },

    yAxis: {
        type: 'value',
        // max:1540352,  //1513632
        min:0,
        //1513632
        // splitNumber:5,
        // boundaryGap: [0.2, 0.2],
        axisTick:{
            show:false //取消坐标轴的点
        },
        axisLabel: {
            show: true,
            interval: 'center',
            formatter: '{value} ',
            margin:2,
            fontSize:10,
            textStyle: {
                color: '#fff'   //字体的颜色
            },
        },
        axisLine:{
            lineStyle:{
                color:'#FFF', //坐标的颜色
                opacity:0.5
            },
        },
        splitLine: { //网格线
            show: false
        },
    },
    series: [
        {
            //name: '辅助',
            type: 'bar',
            stack: '总量',
            barWidth:15,
            itemStyle: {
                normal: {
                    barBorderColor: 'rgba(0,0,0,0)',
                    color: 'rgba(0,0,0,0)'
                },
                emphasis: {
                    barBorderColor: 'rgba(0,0,0,0)',
                    color: 'rgba(0,0,0,0)'
                }
            },
            data: [0, 90, 444, 729, 575]
        },
        {
            // name: '收入',
            type: 'bar',
            barWidth:15,
            stack: '总量',
            data: [90, 345, 393, '-', '-'],
            itemStyle:{    //柱状图的自定义颜色
                normal:{
                    color:function(params) {
                        // build a color map as your need.
                        var colorList = [
                            '#025cd2','rgba(0,255,255,1)','rgba(0,255,255,1)','rgba(0,255,255,1)','rgba(0,255,255,1)',
                            'rgba(0,255,255,1)','rgba(0,255,255,1)','rgba(0,255,255,1)','rgba(0,255,255,1)'
                        ];
                        return colorList[params.dataIndex]
                    },
                }
            }

        },
        {
            // name: '支出',
            type: 'bar',
            stack: '总量',
            barWidth:15,

            // color:'',//默认颜色
            data: ['-', '-', '-', 108, 154]
        }
    ]
};


