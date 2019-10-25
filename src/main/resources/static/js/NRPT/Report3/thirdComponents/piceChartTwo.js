
var piceEchartTwoOption = {
    title : {
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },    
    legend:[
        {
            orient: 'horizontal',  
            left: '6%',
            top: "1%",  
            data: ['消费贷款'],
            itemHeight:10,
            itemWidth:10,
            textStyle : {
                color:'#fff',
                fontSize:10
            },
       } ,
       {
        left: '35%',
        top: '1%', 
        orient: 'horizontal',    
        //data: ['住房按揭贷款'],
        data: ['商用房贷款'],
        itemHeight:10,
            itemWidth:10,
            textStyle : {
                color:'#fff',
                fontSize:10
            },
       } ,
        {
            rient: 'horizontal',  
            left: '68%',
            top: "1%", 
            data: ['经营性贷款'],
            itemHeight:10,
            itemWidth:10,
            textStyle : {
                color:'#fff',
                fontSize:10
            },
        } ,
        
      
        {
            orient: 'horizontal',  
            left: '6%',
            top: "11%",  
           data: ['住房按揭贷款'],
           itemHeight:10,
           itemWidth:10,
           textStyle : {
               color:'#fff',
               fontSize:10
           },
        },
        {
            orient: 'horizontal',  
            left: '35%',
            top: "11%",  
            data: ['其他贷款'],
            itemHeight:10,
            itemWidth:10,
            textStyle : {
                color:'#fff',
                fontSize:10
            },
         },
       
    ],
    calculable : true,
    series : [
            {
                name:'面积模式',
                type:'pie',
                hoverOffset:8,
                radius: ['25%','50%'], 
                center: ['50%', '50%'],
                labelLine:{
                    show:false,//指示线条
                    normal:{  
                        length:10
                    }  
                },
                roseType : 'radius',
                data:[
                    { value:45,
                      name:'住房按揭贷款',
                      label:{
                          show:false,
                          formatter: function (name) {
                              return piceEchartTwoOption.series[0].data[0].value;
                          },
                           color:'#fff'
                       },
                      itemStyle:{
                        color:publick.linearColor('rgba(255,186,64,1)','rgba(255,237,174,1)')
                      },
                      labelLine:{
                          show:false,//指示线的颜色
                        lineStyle:{
                            color:'#fff'
                        }
                      },
                    },
                    {value:27, name:'经营性贷款',
                      label:{
                          show:false,
                          formatter: function (name) {
                              return piceEchartTwoOption.series[0].data[1].value;
                          },
                           color:'#fff'
                       },
                       labelLine:{
                           show:false,//指示线的颜色
                            lineStyle:{
                                color:'#fff'
                            }
                      },
                      itemStyle:{
                            color:publick.linearColor('rgba(18,159,255,1)','rgba(0,255,255,1)')
                      }
                    },
                    {value:30, name:'商用房贷款',
                        label:{
                            show:false,
                            formatter: function (name) {
                                return piceEchartTwoOption.series[0].data[2].value;
                            },
                           // background:'rgba(122,51,240,1)'
                           color:'#fff'    //文字颜色
                        },
                        labelLine:{
                            show:false,//指示线的颜色
                            lineStyle:{
                                color:'rgba(122,51,240,1)'
                            }
                        },
                        itemStyle:{
                            color:publick.linearColor('rgba(122,51,240,1)','rgba(198,84,218,1)')
                        },
                        labelLine:{
                            show:false,//指示线的颜色
                            lineStyle:{
                                color:'#fff'
                            }
                        },
                    },
                    {value:25, name:'消费贷款',
                        label:{
                            show:false,
                            formatter: function (name) {
                                return piceEchartTwoOption.series[0].data[3].value;
                            },
                            color:'#fff'
                        },
                        labelLine:{
                            show:false,//指示线的颜色
                            lineStyle:{
                                color:'rgba(70,65,203,1)'
                            }
                        },
                        itemStyle:{
                            color:publick.linearColor('rgba(70,65,203,1)','rgba(65,119,255,1)')
                        },
                        labelLine:{
                            show:false,//指示线的颜色
                            lineStyle:{
                                color:'#fff'
                            }
                        },
                    },
                    {value:29, name:'其他贷款',
                    label:{
                        show:false,
                        formatter: function (name) {
                            return piceEchartTwoOption.series[0].data[4].value;
                        },
                        color:'#fff'
                    },
                    labelLine:{
                        show:false,//指示线的颜色
                        lineStyle:{
                            color:'rgba(70,65,203,1)'
                        }
                    },
                    itemStyle:{
                        color:publick.linearColor('rgba(245,47,85,1)','rgba(255,144,149,1)')
                    },
                    labelLine:{
                        show:false,//指示线的颜色
                        lineStyle:{
                            color:'#fff'
                        }
                    },
                },
                ]
         }
    ],
};

