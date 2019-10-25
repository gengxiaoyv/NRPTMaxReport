        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));


        var data = [70, 34, 60, 78, 69]
        var titlename = ['TOP1 极客算法战队', 'TOP2 极客算法战队', 'TOP3 极客算法战队', 'TOP4 极客算法战队', 'TOP5 极客算法战队'];
        var valdata = [50, 60, 47, 33, 89]
        var option = {
            // backgroundColor: '#0e2147',
            xAxis: {
                show: false
            },
            yAxis: [{
                show: true,
                data: titlename,
                // inverse: true,
                axisLine: {
                    show: false
                },
                splitLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
                axisLabel: {
                    color: '#fff',
                    // formatter: function (value, index) {
                    //     return [
                    //         '{lg|' + (index + 1) + '}' + '{title|' + value + '} '
                    //     ].join('\n')
                    // },
                    // rich: {
                    //     lg: {
                    //         backgroundColor: '#339911',
                    //         color: '#fff',
                    //         borderRadius: 15,
                    //         // padding: 5,
                    //         align: 'center',
                    //         width: 15,
                    //         height: 15
                    //     },
                    // }
                },


            }
            // , {
            //     show: true,
            //     inverse: true,
            //     data: valdata,
            //     axisLabel: {
            //         textStyle: {
            //             fontSize: 12,
            //             color: '#fff',
            //         },
            //     },
            //     axisLine: {
            //         show: false
            //     },
            //     splitLine: {
            //         show: false
            //     },
            //     axisTick: {
            //         show: false
            //     },

            // }
        ],
            series: [{
                name: '条',
                type: 'bar',
                yAxisIndex: 0,
                data: data,
                barWidth: 8,
                itemStyle: {
                    normal: {
                        // barBorderRadius: 30,
                        color: 'red'
                    }
                },
                // label: {
                //     normal: {
                //         show: true,
                //         position: 'inside',
                //         formatter: '{c}%'
                //     }
                // },
            },
            //  {
            //     name: '框',
            //     type: 'bar',
            //     yAxisIndex: 1,
            //     barGap: '-100%',
            //     data: [100, 100, 100, 100, 100],
            //     barWidth: 40,
            //     itemStyle: {
            //         normal: {
            //             color: 'none',
            //             borderColor: '#00c1de',
            //             borderWidth: 3,
            //             barBorderRadius: 15,
            //         }
            //     }
            // }, 
        ]
        };

        // 使用刚指定的配置项和数据显示图表。
        // myChart.setOption(option);