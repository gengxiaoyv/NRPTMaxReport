//全行存款


//左边中间列 柱形图
//optionLeft_2
var  allBanckStore = {
    backgroundColor:'rgba(0,0,0,0.1)',
    legend: {
        orient: 'horizontal',
        top:'4%',
        itemWidth:14,
        itemHeight:14,
        data: ["个人存款", "对公存款"]
    },
    grid:[
       {
         left:'15%',
         bottom:"30%"
       },{
         left:'15%',
         bottom:"30%"
       }
    ],
    calculable: true,
    xAxis: [{
        type: 'category',
        data:["同期", "上日", "上月"],
        axisTick:{
            show:false
        }
    }],
    yAxis: [{
        type: 'value',
        splitLine: {
            show: false
        },
        axisTick:{
            show:false
        },
       axisLabel:{
            fontSize:9
       }
    }],
    series: [{
        name: '个人存款',
        barWidth: '30%',
        type: 'bar',
        stack: '存款',
        data: [320, 332, 301]
    },
        {
            name: '对公存款',
            barWidth: '30%',
            type: 'bar',
            stack: '存款',
            data: [120, 132, 101]
        }
    ]
};


