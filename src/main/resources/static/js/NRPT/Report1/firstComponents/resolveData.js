var mapTime;
//地图初始化地图
var myChartConLeft = echarts.init(document.getElementById('conLeft'));
var city = document.getElementsByClassName("city")[0]
var num = document.getElementsByClassName("num")[0]



//第一次进入页面的有亮着的地图
function firstTime(dataMap,indexArr,index) {
    map.visualMap.max = dataMap[0].value
    // let k = Math.round(Math.random() * (dataMap.length-1))
    resolveMap(dataMap)                           //把省份数据赋值给  option

  //  myChartConLeft.setOption(map)
    interVal(dataMap,indexArr,index)                              //一直闪亮地图
    var arr = dataMap.forEach((item,index) => {
        if(item.selected === true) {
              city.innerHTML = item.bank
              num.innerHTML = publick.formatNum(item.value)
        }
    })
}

//地图一直亮着
function interVal(dataMap,indexArr,index) {
    clearTimeout(mapTime)
    mapTime = setTimeout(() => {
        for(let j = 0;j<dataMap.length;j++) {
            dataMap[j].selected = false
        }
        dataMap[indexArr[index]].selected = true
        //处理兼容多城市同一省份不亮问题
        for ( let k = 0; k < dataMap.length;k ++) {
            if (dataMap[indexArr[index]].name == dataMap[k].name) {
                dataMap[k].selected = true;
            }
        }
        city.innerHTML = dataMap[indexArr[index]].bank
        num.innerHTML = publick.formatNum(dataMap[indexArr[index]].value)
        index += 1
        interVal(dataMap,indexArr,index)                                  //随机处理处理省份数据，亮着的
        resolveMap(dataMap)

    },3*1000)
}
//处理地图的数据
function resolveMap(dataMap) {
	//给map里面的series的data赋值
    map.series[0].data = dataMap
     //绘制echart
    myChartConLeft.setOption(map);
    
}

setTimeout(() => {
    var img =  document.getElementsByClassName('store_loan')[0]
    img.className = 'loan_store'
    firstTime(resolveDataMethods.mapData.data[1],resolveDataMethods.secondIndexArr,resolveDataMethods.secondIndex)
},30*1000)

//resolveDataMethods.mapData.data[2]
var resolveDataMethods = {
     mapData:'',
     firstIndexArr:[],
     secondIndexArr:[],
    firstIndex:0,
    secondIndex:0,
     resolveAllBankLoan(allBankLoanData,allBankLoanOption) {
            //series和option.legend的data都没有清理，数据过来之后记得清理
            
           //处理seres.data里面的
           allBankLoanData.forEach((item,index) => {
               if(index == 0) {
                    for(let i = 0;i<item.length;i++) {
                        allBankLoan.series[index].data[i].name = item[i].name
                        allBankLoan.series[index].data[i].value = item[i].value
                    }
               }
               if(index == 1) {
                    for(let i = 0;i<item.length;i++) {
                        allBankLoan.series[index].data[i].name = item[i].name
                        allBankLoan.series[index].data[i].value = item[i].value
                    }
               }
           })
          
           //处理option的参数
         
      //图例数据和series数据有冲突，暂时不做处理，数据全后，直放开

      allBankLoanOption.legend.forEach((item,index) => {
            if(index == 0) {
                allBankLoan.series[index].radius = allBankLoanOption.series[index].radius
                allBankLoan.legend[index].data = item.data
            } 
            if (index == 1) {
                allBankLoan.series[index].radius = allBankLoanOption.series[index].radius
               allBankLoan.legend[index].data = item.data
            }
      }) 
            //位置调整
        //allBankLoan.grid = allBankLoanOption.grid
           //x轴调整
        //allBankLoan.axis = allBankLoanOption.axis
         publick.piceChart('left_3ECharts',allBankLoan)  //左下角 全行贷款圆环图  全行贷款

     }, 


     resolveAllBankStore(item) {
         allBanckStore.series[0].data = item.data[0]
         allBanckStore.series[1].data = item.data[1]
         allBanckStore.legend.data = item.option.data
        //  //位置调整
        // // allBanckStore.grid = item.option.grid
      //  console.log(item.option.axis)
         allBanckStore.xAxis[0].data = item.axis
         publick.piceChart('left_2ECharts',allBanckStore,"customed1")   //左边中间列 柱形图  全行存款
     },


     resolveBranchBanckLoan(item) {

     
        branchBankChart(item.data)
         // publick.piceChart('right_topECharts', branchBankLoan,"customed1")  //分行贷款情况

     },
     resolveLineEchart(item) {
        //   console.log(item)
          lineEchart.series[0].data = item.data[0]
              //位置调整
          //lineEchart.grid = item.option.grid
          //增加最小值
          lineEchart.yAxis.min = item.axis.min
          lineEchart.xAxis.data = item.axis.data
          publick.piceChart('middle_bottomECharts',lineEchart)    //营收趋势
     },
     resolveListLoan(item) {
        var data = item.data
        var option = item.option
        listLoan.series[0].data = data[0].reverse()
        listLoan.yAxis[1].data = item.axis.reverse()

        //位置调整

        //listLoan.grid = option.grid
        //listLoan.grid.containLabel = true
          publick.piceChart('con_bottomECharts',listLoan,"customed1")  //全行贷款排名
     },
     resolveListStore(item) {
          var data = item.data
          var option = item.option
          listStore.series[0].data = data[0].reverse()
          listStore.yAxis[1].data = item.axis.reverse()

          //位置调整

          //listStore.grid = option.grid
          //listStore.grid.containLabel = true
          publick.piceChart('con_topECharts',listStore,"customed1")     //全行存款排名
     },
     resolveRadarEchart(item) {
          //调整数据结构之后就可以放开了   -->放开后记得删除原有的数据  --->已经删除
           radarEchart.series[0].data[0].value = item.data
          //位置调整
          //radarEchart.grid = item.option.grid
         // radarEchart.radar.radius = item.option.radar.radius
          radarEchart.radar.indicator = item.option.radar.indicator
          
          publick.piceChart('right_bottomECharts',radarEchart)      //右下角雷达图
     },
     resolveMap(item) {
        //  console.log(item)
           this. mapData = item
           this.firstIndexArr = item.data[2]
             this.secondIndexArr = item.data[3]
          firstTime(this.mapData.data[0],this.firstIndexArr,this.firstIndex)
     },
     allMessage(item) {
         //信息总览
         let incomeNum = document.getElementsByClassName('incomeNum')[0]
         let storeNum = document.getElementsByClassName('storeNum')[0]
         let loanNum = document.getElementsByClassName('loanNum')[0]

         incomeNum.innerHTML = publick.formatNum(item[0][0].value)
         storeNum.innerHTML = publick.formatNum(item[0][1].value)
         loanNum.innerHTML = publick.formatNum(item[0][2].value)
     }
}



  
  