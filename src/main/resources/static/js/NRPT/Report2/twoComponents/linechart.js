
//图片二胡的折线图


              // 指定图表的配置项和数据
              var linechartOption = {
                xAxis: {
                    data: [  
                    ], 
                    axisLine:{  
                        lineStyle:{
                            color:'#fff',
                            opacity:0.5
                        }
                    },
                    axisTick:{
                        show:false
                    },
                },
                yAxis: {
                    type: 'value',
                    boundaryGap: [0.2, 0.2],
                    min:'',
                    axisLabel: {  
                        fontSize:10,
                        show: true,  
                        interval: 'auto',  
                        formatter: '{value}',     //显示
                        margin:2,
                       
                        textStyle: {
                            color: '#fff'   //字体的颜色
                        },
                        
                    }, 
                    axisLine:{           //坐标轴
                        lineStyle:{
                            color:'#FFF', //坐标的颜色
                            opacity:0.5
                        },
                    },
                    splitLine:{        //y轴标记线
                        lineStyle:{
                            opacity:0.3
                        }
                    },
                    axisTick:{
                        show:false
                    },
                },

                grid:{      //位置的调整
                  top:"14%",    
                  left:'6%',
                  height:'68%',
                  width:'90%',
                  borderColor:"black"    //折线颜色
                },
                series: [{

                    type: 'line',
                    itemStyle: {   
                                 normal: {
                                    borderWidth:1,
                                    color:'rgba(0,255,255,1)',
                                    shadowColor: 'rgba(0,255,255,1)',
                                    shadowBlur: 5,
                                   lineStyle: {
                                      color: "rgba(0,255,255,1)"//折线的颜色
                                  }
                                }
                    },
                    data: [],
                }]
            };
            