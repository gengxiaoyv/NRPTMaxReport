


var treeName = document.getElementById('tipBoxName')

//中间树的生成

let treeImage = document.getElementsByClassName("treeImage")[0]
let lastDay = document.getElementsByClassName("num1")[0] 
let lastMonth = document.getElementsByClassName("num1")[1]
let lastYear = document.getElementsByClassName("num1")[2]



let firstList = document.getElementsByClassName("left1_Label")[0]
let lastList = document.getElementsByClassName("left2_Label")[0]

var resolveDataMethods = {
    first:'',
    last:'',
    resolveColumnChart(item) {
       // 数据不对
             //   公司客户数画柱状图
            // console.log(999999999999999,item)
        columnarOptions.xAxis.data = item.axis
        columnarOptions.yAxis.min = item.data[0][0]
        columnarOptions.series[0].data = item.data[0]
        columnarOptions.series[1].data = item.data[1]
        columnarOptions.series[2].data = item.data[2]
         publick.piceChart('columnar',columnarOptions)
    },
    resolveLineChart(item) {
                    //  公营收入折线图
        linechartOption.yAxis.min = item.axis.min
        linechartOption.xAxis.data = item.axis.data
        linechartOption.series[0].data = item.data
            //位置
       // linechartOption.grid = item.option.grid
        publick.piceChart('linechart',linechartOption)
       
    },
    resolveListLoan(item) {
        var itemAxis = []
        var color = {
            color:'#fff'
        } 
        item.axis.forEach((item,index) => {
            let obj = {
                value:item,
                textStyle:color
            }
            itemAxis.push(obj)
        })
        listLoan.yAxis[1].data = itemAxis.reverse()
        listLoan.series[0].data = item.data.reverse()
        //位置
        //listLoan.grid = item.option.grid
        publick.piceChart('listLoan',listLoan)
        
    }, 
    resolveListStore(item) {
        //全行存款排名
        var itemAxis = []
        var color = {
            color:'#fff'
        } 
        item.axis.forEach((item,index) => {
            let obj = {
                value:item,
                textStyle:color
            }
            itemAxis.push(obj)
        })
        listStore.yAxis[1].data = itemAxis.reverse()
        listStore.series[0].data = item.data.reverse()
        
         //位置
        //listLoan.grid = item.option.grid
        publick.piceChart('listStore',listStore)
    },
    resolvePiceChart(item) {
        // 存储的饼状图
        item.data.forEach((item,index) => {
            storeOption.series[0].data[index].name = item.name
            storeOption.series[0].data[index].value= item.value 
        })
        item.option.legend.data.forEach((item,index) => {
            storeOption.legend[index].data[0] = item
        })

        storeOption.series[0].radius = item.option.series[0].radius
        publick.piceChart('store',storeOption)
    }, 
    resolvePiceChartTwo(item) {

        item.data.forEach((item,index) => {
            piceEchartTwoOption.series[0].data[index].name = item.name
            piceEchartTwoOption.series[0].data[index].value= item.value 
        })

       
        item.option.legend.data.forEach((item,index) => {
            piceEchartTwoOption.legend[index].data[0] = item
        })
       piceEchartTwoOption.series[0].radius = item.option.series[0].radius
        
        //  贷款的饼状图
        publick.piceChart('loan',piceEchartTwoOption)
    },
    resolveTree(item) {
        treeName.innerHTML = item.data[0].name
        // lastDay.innerHTML = item.data[0].value
        // lastMonth.innerHTML = item.data[1].value
        // lastYear.innerHTML = item.data[2].value

        lastDay.innerHTML = publick.formatNum( item.data[0] ? item.data[0].value : "0" )
        lastMonth.innerHTML = publick.formatNum( item.data[1] ? item.data[1].value  : "0")
        lastYear.innerHTML = publick.formatNum( item.data[2] ? item.data[2].value : "0" )
        // console.log(item.data[0].name)

        //排名的title
        this.first = `${item.data[0].name}排名前十`
        this.last = `${item.data[0].name}排名后十`
        firstList.innerHTML = this.first
        lastList.innerHTML = this.last
    }
}











