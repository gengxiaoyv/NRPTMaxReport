

function rseolveData(data) {
   // console.log(data)
    document.title = data.title+"经营实时大屏 对公";
    document.getElementById('titleSpan').innerHTML=data.title+"经营实时大屏 对公"
    if(data.name === '大屏二全部数据') {
        var allData = data.reportData
        allData.forEach((item,index) => {
            if(item.name === "大屏2一般对公存款排名前十") {
                 //对公存款排名前十
                resolveDataMethods.resolveListStore(item)
            }
            if(item.name === '大屏2一般对公贷款排名后十') {
                //对公贷款排名前十
                resolveDataMethods.resolveListLoan(item)
            }
            if(item.name === "大屏2树形图") {
                
                resolveDataMethods.resolveTree(item)
                //大屏2树形图

            }
            if(item.name === "大屏2公司贷款利息收入趋势") {
                //大屏2树形图
                resolveDataMethods.resolveLineChart(item)
            }
            if(item.name === "大屏2存款贷款分析（存款）") {
                //大屏2树形图
                resolveDataMethods.resolvePiceChart(item)
            }
            if(item.name === "大屏2存款贷款分析（贷款）") {
                //大屏2树形图
                resolveDataMethods.resolvePiceChartTwo(item)
            }
            if(item.name === "大屏2公司客户数") {
                //大屏2树形图
                resolveDataMethods.resolveColumnChart(item)
            }
        })
    }
}

function nextPage() {
    window.location.href = 'getReport3Page_1'
}





var treeRequestData = ['GM010101','GM0301','GM030101','GM01010101','GM01010102','GM0101010201','GM030102']

var requestime = 0




function interVal() {
    setTimeout(() => {
        let obg = {
            keyID:treeRequestData[requestime]
        }
        requestime += 1
        request(obg)
    },8*1000)
}


interVal()





function request(data) {
    var xmlHttp=new XMLHttpRequest();
    xmlHttp.open("GET","changeTree_1?keyID="+data.keyID,true);
    xmlHttp.send(null);

    xmlHttp.onreadystatechange=function () {
        if (xmlHttp.readyState ==4 && xmlHttp.status ==200){
            var received_msg = JSON.parse(xmlHttp.responseText);
            rseolveData(received_msg)
            interVal()
            //树图
            console.log(data.keyID)
            treeImage.src =  `./image/forPublick/trendTree/${data.keyID}.png`
        }
    };
}



function nextPage() {
    window.location.href = 'getReport3Page_1'
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


//ws('大屏二全部数据',nextPage)
var data = {
}
publick.funAjax('GET','getReportAllData_1?key=大屏二全部数据',true,data,rseolveData)