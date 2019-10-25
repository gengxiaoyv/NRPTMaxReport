//营收趋势


//折线图  中间下面


var lineEchart = {
    grid: {
        top:"14%",
        left:'6%',
        height:'68%',
        width:'90%',
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,         //坐标轴两边留白策略
        axisLine:{
            lineStyle:{
                color:'#fff',
                opacity:0.5         //透明度
            }
        },
        axisLabel:{
            interval:0,
            fontSize:10
        },
        axisTick:{
            show:false
        },
        data: []
    },
    yAxis: {
        type: 'value',
        boundaryGap: [0, '100%'],
        axisLine:{
            lineStyle:{
                color:'#fff',
                opacity:0.5
            }
        },
        axisTick:{
            show:false
        },
        splitLine:{
            lineStyle:{
                opacity:0.3
            }
        },
        axisLabel:{
            interval:0,
            fontSize:10
        },
    },
    series: [
        {
            name: '模拟数据',
            type: 'line',
            smooth: true,                       //是平滑曲线显示
            symbol: 'none',                     //标记的图形
            itemStyle: {
                color: 'rgba(0,255,255,1)'
            },
            areaStyle: {
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                    offset: 0,
                    color: 'rgba(0,255,255,0.5)'
                }, {
                    offset: 1,
                    color: 'rgba(0,255,255,0)'
                }])
            },
            data: []
        }
    ]
};


