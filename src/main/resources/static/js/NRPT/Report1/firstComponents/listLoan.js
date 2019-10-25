//全行贷款排名


var listLoan = {
    backgroundColor:'rgba(0,0,0,0.1)',         //背景设为透明色
    grid: {
        containLabel: true,                    //grid 区域包含坐标轴的刻度标签
        top: '0%',
        left:'15%',
        height:'76%'
    },
    xAxis: {
        type: 'value',
        axisLine: {
            show: false                         //不显示坐标轴轴线
        },
        axisTick: {
            show: false                         //不显示坐标轴刻度
        },
        axisLabel: {
            show: false                         //不显示刻度标签
        },
        splitLine: {
            show: false                         //不显示分隔线
        }
    },
    yAxis: [{
        type: 'category',
        splitLine: {
            show: false
        },
        axisLine: {
            show: false
        },
        axisTick: {
            show: false
        },
        axisLabel:{
            show:false
        }
    }, {
        type: 'category',
        data: [],
        offset: 17,
        splitLine: {
            show: false
        },
        axisLine: {
            show: false
        },
        axisTick: {
            show: false
        },
        axisLabel:{
        	show:true,
        	fontWeight:'600'
        }
    }],
    series: [
        {
            name: '数量',
            type: 'bar',
            data: [],
            barWidth: 14,                       //柱条的宽度
            barGap: 100,                         //不同系列的柱间距离
            itemStyle: {
                normal: {
                    color: publick.barColor('rgba(0,110,255,0)','rgba(0,110,255,1)')
                }
            }
        }
    ]
};


