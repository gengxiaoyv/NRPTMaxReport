//全行贷款   左下角


//optionLeft_3
var allBankLoan =  {
    backgroundColor:'rgba(0,0,0,0.1)',
    legend: [{
        show: true,
        orient: 'vertical',
        x: 'left',
        y: 'center',
        left:'3%',
        itemWidth: 12,
        itemHeight: 12,
        textStyle: {
            color:'#fff',
            fontSize: 9
        },
        data:[],
        formatter: function (name) {
                var str;
                str = name +'\n'+allBankLoan.series[0].data[obj.intJ].value;
                obj.intJ += 1;
                return str;
        },
    },{
        show: true,
        orient: 'vertical',
        y: 'center',
        left:'75%',
        itemWidth: 12,
        itemHeight: 12,
        textStyle: {
            color:'#fff',
            fontSize: 9
        },
        data: [],
        formatter: function (name) {
            var str;
            if(obj.intJ-5<=4){
                str = name +'\n'+allBankLoan.series[1].data[obj.intJ-5].value;
            }
            obj.intJ += 1;
            return str;
        }
    }],
    series:[
        {value:'',
        name: '',
        type: 'pie',
        x: '60%',
        width: '35%',
        funnelAlign: 'left',
        radius: ['42%', '60%'],
        data: [
            {value: '', name: '',
                label: {
                    show: false
                },
                labelLine:{
                    show:false
                },
                itemStyle:{
                    color: publick.linearColor('rgba(255,244,132,1)','rgba(255,185,75,1)')
                }
            },
            {value: '', name: '',
                label: {
                    show: false
                },
                labelLine:{
                    show:false
                },
                itemStyle:{
                    color: publick.linearColor('rgba(255,144,149,1)','rgba(247,47,85,1)')
                }
            },
            {value: '', name: '',
                label: {
                    show: false
                },
                labelLine:{
                    show:false
                },
                itemStyle:{
                    color: publick.linearColor('rgba(198,84,218,1)','rgba(101,60,240,1)')
                }
            },
            {value: '', name: '',
                label: {
                    show: false
                },
                labelLine:{
                    show:false
                },
                itemStyle:{
                    color: publick.linearColor('rgba(19,148,255,1)','rgba(0,86,205,1)')
                }
            },
            {value: '', name: '',
                label: {
                    formatter: [
                        '个贷'
                    ].join('\n'),
                    color:'#fff',
                    show: false,
                    backgroundColor: {
                        // image:'./image/img/全行贷款/个贷.png'
                         image:'./image/img/allBankLoan/personalLoan.png'
                    },
                    padding:7
                },
                labelLine:{
                    show:false,
                    length:55,
                    length2:10,
                    lineStyle:{
                        color:'rgb(6,131,149)'
                    }
                },
                itemStyle:{
                    color: publick.linearColor('rgba(0,255,255,1)','rgba(18,159,255,1)')
                }
            }
        ]
    },
    {   value:'',
        name: '',
        type: 'pie',
        selectedMode: 'single',
        x: '20%',
        width: '40%',
        funnelAlign: 'right',
        max: 1548,
        itemStyle: {
            normal: {
                label: {
                    show: false,
                    position: 'outside'
                },
                labelLine: {
                    show: false
                }
            }
        },
        radius: [0, '30%'],
        data: [
            {value: '', name: '',
                label: {
                    formatter: [
                        '一般对公贷款'
                    ].join('\n'),
                    show: false,
                    color:'#fff',
                    backgroundColor: {
                        // image:'./image/img/全行贷款/一般对公贷款.png'
                        image:'./image/img/allBankLoan/nomalForPublickStore.png'
                    },
                    padding:7
                },
                labelLine:{
                    show:false,
                    length:90,
                    length2:5,
                    lineStyle:{
                        color:'rgb(6,131,149)'
                    }
                },
                itemStyle:{
                    color: publick.linearColor('rgba(255,144,149,1)','rgba(247,47,85,1)')
                }
            },
            {value: '', name: '',
                label: {
                    show: false
                },
                labelLine:{
                    show:false
                },
                itemStyle:{
                    color:publick.linearColor('rgba(101,60,240,1)','rgba(198,84,218,1)')
                }
            },
            {value: '', name: '',
                label: {
                    show: false
                },
                labelLine:{
                    show:false
                },
                itemStyle:{
                    color: publick.linearColor('rgba(19,148,255,1)','rgba(0,86,205,1)')
                }
            },
            {value: '', name: '',
                label: {
                    show:false
                },
                labelLine:{
                    show:false
                },
                itemStyle:{
                    color: publick.linearColor('rgba(0,255,255,1)','rgba(18,159,255,1)')
                }
            }
        ]
    }]
};


