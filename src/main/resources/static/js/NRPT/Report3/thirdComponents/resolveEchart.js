function rseolveData(data) {
    document.title = data.title+"经营实时大屏 零售";
    document.getElementById('titleSpan').innerHTML=data.title+"经营实时大屏 零售"
     if(data.name === '大屏三全部数据') {
         var allData = data.reportData
         allData.forEach((item,index) => {
            if(item.name === "大屏3云图") {
                resolveDataMethods.resolveTree(item)
             }
             if(item.name === "大屏3保证金存款排名前十") {
                  //对公存款排名前十
                 resolveDataMethods.resolveListStore(item)
             }
             if(item.name === "大屏3保证金存款排名后十") {
                 //对公贷款排名前十
                 resolveDataMethods.resolveListLoan(item)
             }
             if(item.name === "大屏3零售贷款利息收入趋势") {
             
                 resolveDataMethods.resolveLineChart(item)
             }
             if(item.name === "大屏3存款贷款分析（存款）") {

                resolveDataMethods.resolvePiceChart(item)
             }
             if(item.name === "大屏3存款贷款分析（贷款）") {

                resolveDataMethods.resolvePiceChartTwo(item)
             }
             if(item.name === "大屏3个人客户数") {
                 //大屏2树形图
                 resolveDataMethods.resolveColumnChart(item)
             }
         })
     }
 }



function nextPage() {
   window.location.href = 'getReport1Page?reportID=Report001'
}

function reloadPage1() {
    window.location.href = 'http://analytics.spdb.com/portal/getNRPTReport.do?reportID=Report001'
}

function getServiceStatus() {
    try {
        var ajax = new XMLHttpRequest();
        ajax.open('GET','getServiceStatus',false)
        ajax.send()
        console.log(ajax.responseText);
        if (('Success')==ajax.responseText){
            nextPage();
        }else {
            reloadPage1()
        }
    } catch (e) {
        console.log('error');
        setTimeout(getServiceStatus,1000*60*5);
    }
}

setTimeout(getServiceStatus,1000*60)



var clouldRequestData = ['GM030204','GM030203','GM010203','GM010202','GM030205','GM010201','GM030201','GM010205','GM030202']
var requesClouldTime = 0




function interVal() {
    setTimeout(() => {
        let obg = {
            keyID:clouldRequestData[requesClouldTime]
        }
        requesClouldTime += 1
        request(obg)
    },6*1000)
}


interVal()








function request(data) {
    var xmlHttp = new XMLHttpRequest();
    console.log(data)
    xmlHttp.open("GET", "changeCloud?keyID="+data.keyID, true);
    xmlHttp.send(null);

    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            var received_msg = JSON.parse(xmlHttp.responseText);
            rseolveData(received_msg)
            console.log(data.keyID)
            treeImage.src =  `./image/forPrivate/${data.keyID}.png`
            interVal()
        }
    };
}

//ws('大屏三全部数据',nextPage)
var data = {}
publick.funAjax('GET','getReportAllData?key=大屏三全部数据',true,data,rseolveData)