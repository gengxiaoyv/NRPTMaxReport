//全行贷款排名


var listLoan = {
     //背景设为透明色
     grid: {
        containLabel: true,                    //grid 区域包含坐标轴的刻度标签
        height:'82.5%',
        width:'70%',
        top:'13.5%',
        left:'19%'
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
    }, {
        data: [{
               value:'广州1',
               textStyle:{
                 color:'#fff'
               }
             },
            {
                value:'2深圳',
                textStyle:{
                    color:'#fff'
                }
            },
            {
                value:'3北京',
                textStyle:{
                    color:'#fff'
                  }
            },
            {
                value:'4深圳',
                textStyle:{
                    color:'#fff'
                  }
            },
            {
                value:'5北京',
                textStyle:{
                    color:'#fff'
                  }
            },
            {
                value:'6杭州',
                textStyle:{
                    color:'#fff'
                  }
            },
            {
                value:'7北京',
                textStyle:{
                    color:'#fff'
                }
            },
            {
                value:'8杭州',

                textStyle:{
                    color:'#fff'
                }
            },
            {
                value:'9上海',
                textStyle:{
                    color:'#fff'
                }
            },
            {
                value:'10上海',
                textStyle:{
                    color:'#fff'
                }
            }
        ],
        offset: 9,
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
            data: [1218, 383, 431, 581, 111 ,153, 383, 431, 581, 121],
            barWidth: 10, 
            barHeight:10,                      //柱条的宽                 //不同系列的柱间距离
            itemStyle: {
                normal: {
                    color: publick.barColor('rgba(70,65,203,0)','rgba(70,65,203,1)')
                }
            }
        }
    ]
};


