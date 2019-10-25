//地图


var map = {
    grid: {
        // // containLabel: true,                    //grid 区域包含坐标轴的刻度标签
        //  top: '2%',
        // // top:"100%",
        //  left:'15%',
        //  bottom:"40%"
     },
    visualMap: {
        type: 'continuous',
        text: ['高', '低'],
        textGap:3,                  //两端文字主体之间的距离
        showLabel: true,
        min: 0,
        max: '',
        itemWidth:20,
        itemHeight:140,
        orient:'horizontal',
        left:'69%',
        bottom:'3%',
        borderColor:'rgba(0,181,255,1)',
        textStyle:{
            color:'#fff'
        },
        inRange: {
            color: ['rgba(0,156,255,0.8)', 'rgba(9,37,106,1)']
        }
    },
    series: [
        {
            name: '中国',
            type: 'map',
            mapType: 'china',
            selectedMode: 'multiple',       //选中模式（多选）
            label: {
                normal: {
                    show: false,//显示省份标签
                },
                emphasis: {
                    show: false//对应的鼠标悬浮效果
                }
            },
            itemStyle: {
                normal: {
                    borderWidth: 1.5,//区域边框宽度
                    borderColor: 'rgba(0,181,255,1)',   //区域边框颜色
                },
                emphasis: {
                    borderWidth: 2,
                    borderColor: 'rgba(0,246,255,1)',   //高亮区域边框颜色
                    areaColor: "rgba(2,129,219,1)",     //高亮区域颜色
                }
            },
            data: ''
        }
    ]
};
// 全国省份列表


//随机赋值

