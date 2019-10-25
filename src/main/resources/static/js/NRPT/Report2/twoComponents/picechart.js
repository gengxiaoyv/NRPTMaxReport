
var storeOption = {
    title : {
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend :[     // //图例信息分开写
        {
            orient: 'horizontal',  
            left: '6%',
            top: "1%",  
            data: [''],
            itemHeight:10,
            itemWidth:10,
            textStyle : {
                color:'#fff',
                fontSize:10
            },
       },
       {
            orient: 'horizontal',  
            left: '50%',
            top: "1%",  
            data: [''],
            itemHeight:10,
            itemWidth:10,
            textStyle : {
                color:'#fff',
                fontSize:10
            },
         },
         {
            orient: '',  
            left: '6%',
            top: "12%",  
            data: [''],
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
                radius: [], 
                center: ['50%', '50%'],
                labelLine:{  //指示线条
                    normal:{  
                        length:10
                    }  
                },
                hoverOffset:8,
                roseType : 'radius',
                data:[
                    {
                      value:'', 
                      name:'',
                      label:{
                          show:false,
                           formatter: function (name) {
                               return storeOption.series[0].data[0].value;
                           },
                           color:'#fff'
                       },
                      itemStyle:{
                        color:publick.linearColor('rgba(255,186,64,1)','rgba(255,237,174,1)')
                      },
                      labelLine:{
                          show:false,
                        lineStyle:{
                            color:'#fff'
                        }
                      }
                    },
                    {   value:'', 
                        name:'',
                        label:{
                            show:false,
                            formatter: function (name) {
                                return storeOption.series[0].data[1].value;
                            },
                            color:'#fff',
                        },
                        itemStyle:{
                            color:publick.linearColor('rgba(18,159,255,1)','rgba(0,255,255,1)'),
                        },
                        labelLine:{
                            show:false,
                            lineStyle:{
                                color:'#fff'
                            }
                        }
                    },
                    {value:'', name:'',
                        label:{
                            show:false,
                            formatter: function (name) {
                                return storeOption.series[0].data[2].value;
                            },
                            color:'#fff'
                        },
                        itemStyle:{
                            color:publick.linearColor('rgba(70,65,203,1)','rgba(65,119,255,1)')
                        },
                        labelLine:{
                            show:false,
                            lineStyle:{
                                color:'#fff'
                            }
                        }
                    }, 
                ]
         }
    ]
};

