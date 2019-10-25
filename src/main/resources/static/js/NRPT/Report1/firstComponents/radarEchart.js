//右下角雷达图
//var myChartRight_bottom = echarts.init(document.getElementById('right_bottomECharts'));
var obj = {
    indexformatter:0,
    intJ:0
}

var radarEchart = {

    grid: {
       // containLabel: true,                    //grid 区域包含坐标轴的刻度标签
        top: '2%',
       // top:"100%",
        left:'15%',
        bottom:"40%"
    },
    radar: {
        center: ['50%', '40%'],
        radius: '60%',
        nameGap:5,
        name: {
            formatter: function (name) {
                // for(let i = 0; i < radarEchart.radar.indicator.length; i++){
                //     console.log(i)
                //     if (i==0){
                //         return radarEchart.radar.indicator[i].name+"："+radarEchart.series[0].data[0].value[i];
                //     }
                //         return radarEchart.radar.indicator[i].name+"\n"+radarEchart.series[0].data[0].value[i];
                // }
                var str;
                if (obj.indexformatter==0){
                    str = radarEchart.radar.indicator[obj.indexformatter].name+"："+radarEchart.series[0].data[0].value[obj.indexformatter];
                }else {
                    str =  radarEchart.radar.indicator[obj.indexformatter].name+"\n"+radarEchart.series[0].data[0].value[obj.indexformatter];
                }
                obj.indexformatter += 1;
                return str

            },
            textStyle: {
                color: '#fff',
                fontSize: 10,
            }
        },
        indicator: [],
        splitNumber: 3,
        splitLine:{
            show:false
        },
        axisLine:{
            lineStyle: {
                color: '#fff',
                width:0.5,
                opacity:0.3
            },
        },
        splitArea: {
            areaStyle: {
                color: ['rgba(255,125,145,0.5)',
                    'rgba(255,125,145,0.3)', 'rgba(255,125,145,0.25)'],
                shadowColor: 'rgba(0,0,0,0.3)',
                shadowBlur: 10
            }
        }
    },
    series: [{
        name: '同业负债结构',
        type: 'radar',
        itemStyle:{
            color:'#fff'
        },
        data: [
            {
                name: '同业负债结构',
                value:[],
                lineStyle: {
                    color: '#fff',
                    opacity:0.3
                },
                areaStyle: {
                    normal: {
                        opacity: 0.9,
                        color: new echarts.graphic.RadialGradient(0.5, 0.5, 1, [
                            {
                                color: 'rgba(255,116,67,0.7)',
                                offset: 0
                            },
                            {
                                color: 'rgba(233,55,98,0.7)',
                                offset: 1
                            }
                        ])
                    }
                }
            }
        ]
    }]
};

//myChartRight_bottom.setOption(optionRight_bottom);

