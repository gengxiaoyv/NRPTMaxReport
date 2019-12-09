

function rseolveData(data) {
    document.title = data.title+"经营实时大屏 概览";
    document.getElementById('titleSpan').innerHTML=data.title+"经营实时大屏 概览"
    
    //增加全行判断
    if ( data.title !== "全行") {

    	document.getElementsByClassName("allBankFlag")[0].innerHTML = "网点存款排名"
    	document.getElementsByClassName("allBankFlag")[1].innerHTML = "网点贷款排名"
    	document.getElementsByClassName("allBankFlag")[2].innerHTML = "网点存、贷款情况"
    }
    
    
    if(data.name === '大屏一全部数据'){
		data.reportData.forEach((item,index) => {
			if(item.name === '大屏1全行贷款') {
				//console.log('大屏1全行贷款',item)
				resolveDataMethods.resolveAllBankLoan(item.data,item.option)
			}
			if(item.name === '大屏1全行存款排名') {
				resolveDataMethods.resolveListStore(item)
			}
			if(item.name === '大屏1全行贷款排名') {
				resolveDataMethods.resolveListLoan(item)
			}
			if(item.name === '大屏1营收趋势') {
				resolveDataMethods.resolveLineEchart(item)
			}
			if(item.name === '大屏1同业负债结构') {
				resolveDataMethods.resolveRadarEchart(item)
			}
			if(item.name === '大屏1分行存贷款情况') {
				resolveDataMethods.resolveBranchBanckLoan(item)
			}
			if(item.name === '大屏1全行存款') {
				resolveDataMethods.resolveAllBankStore(item)
			}
			if(item.name === '大屏1地图') {
			//	console.log('大屏1地图',item)
				resolveDataMethods.resolveMap(item)
			}
			if(item.name === '大屏1信息总览') {
				resolveDataMethods.allMessage(item.data)
			}
		});
	}
 }



 function nextPage() {
     window.location.href="getReport2Page_1";
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
//
// ws('大屏一全部数据',nextPage)


//ws('大屏二全部数据',nextPage)
var data = {
}
publick.funAjax('GET','getReportAllData_1?key=大屏一全部数据',true,data,rseolveData)