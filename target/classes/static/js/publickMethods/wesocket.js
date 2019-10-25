
    // var webSocket = new WebSocket('')
    // // 建立 web socket 连接成功触发事件
    // webSocket.onopen = function () {
    //   // 使用 send() 方法发送数据
    //     //发送东西 
    //     webSocket.send('all')
    //     console.log("send message...");
    // }
    


    // // 接收服务端数据时触发事件
    // webSocket.onmessage = function (evt) {
    //   //判断数据是那部分的
     

    //   rseolveData(evt.data)
    // };
    



    // // 断开 web socket 连接成功触发事件
    // webSocket.onclose = function () {
    //      console.log('close')
    // }




//大屏一全部数据
//var str = {"name":"大屏一全部数据","reportData":[{"name":"大屏1信息总览","data":[[{"name":"全行营业收入","value":"335.35"},{"name":"全行存款","value":"335.35"},{"name":"全行贷款","value":"335.35"}]],"option":null,"axis":["全行营业收入","全行存款","全行贷款"]},{"name":"大屏1全行存款","data":[[{"name":"个人存款","value":"485.45"},{"name":"个人存款","value":"415.45"},{"name":"个人存款","value":"335.35"}],[{"name":"对公存款","value":"385.45"},{"name":"对公存款","value":"315.45"},{"name":"对公存款","value":"335.35"}]],"option":{"legend":{"data":["个人存款","对公存款"]},"grid":{"left":"","right":null,"top":"","bottom":null,"width":"","height":""}},"axis":["今日","比上日","比上月"]},{"name":"大屏1全行贷款","data":[[{"name":"住房按揭贷款","value":"335.35"},{"name":"经营性贷款","value":"335.35"},{"name":"商用房贷款","value":"335.35"},{"name":"消费贷款","value":"335.35"},{"name":"其它贷款","value":"335.35"}],[{"name":"逾期贷款","value":"335.35"},{"name":"对公中长期贷款","value":"335.35"},{"name":"对公短期贷款","value":"335.35"},{"name":"票据融资","value":"335.35"}]],"option":{"legend":[{"data":["住房按揭贷款","经营性贷款","商用房贷款","消费贷款","其它贷款"]},{"data":["逾期贷款","对公中长期贷款","对公短期贷款","票据融资"]}],"grid":{"left":"","right":null,"top":"","bottom":null,"width":"","height":""},"series":[{"radius":["42%","60%"]},{"radius":["0%","30%'"]}]},"axis":null},{"name":"大屏1地图","data":[[{"name":"四川","bank":"成都","value":"9335.35","selected":false},{"name":"山东","bank":"济南","value":"8335.35","selected":false},{"name":"辽宁","bank":"沈阳","value":"7335.35","selected":false},{"name":"北京","bank":"北京","value":"6335.35","selected":false},{"name":"陕西","bank":"西安","value":"5335.35","selected":false},{"name":"浙江","bank":"宁波","value":"4335.35","selected":false},{"name":"内蒙古","bank":"呼和浩特","value":"38335.35","selected":false},{"name":"贵州","bank":"贵阳","value":"37335.35","selected":false},{"name":"广东","bank":"深圳","value":"36335.35","selected":false},{"name":"安徽","bank":"合肥","value":"35335.35","selected":false},{"name":"天津","bank":"天津","value":"34335.35","selected":false},{"name":"江苏","bank":"南京","value":"3335.35","selected":false},{"name":"云南","bank":"昆明","value":"33335.35","selected":false},{"name":"宁夏","bank":"银川","value":"32335.35","selected":false},{"name":"海南","bank":"海口","value":"31335.35","selected":false},{"name":"青海","bank":"西宁","value":"30335.35","selected":false},{"name":"福建","bank":"厦门","value":"29335.35","selected":false},{"name":"江西","bank":"南昌","value":"28335.35","selected":false},{"name":"福建","bank":"福州","value":"27335.35","selected":false},{"name":"黑龙江","bank":"哈尔滨","value":"26335.35","selected":false},{"name":"广西","bank":"南宁","value":"25335.35","selected":false},{"name":"新疆","bank":"乌鲁木齐","value":"24335.35","selected":false},{"name":"湖北","bank":"武汉","value":"2335.35","selected":false},{"name":"吉林","bank":"长春","value":"23335.35","selected":false},{"name":"重庆","bank":"重庆","value":"22335.35","selected":false},{"name":"广东","bank":"广州","value":"21335.35","selected":false},{"name":"甘肃","bank":"兰州","value":"20335.35","selected":false},{"name":"江苏","bank":"苏州","value":"19335.35","selected":false},{"name":"山西","bank":"太原","value":"18335.35","selected":false},{"name":"山东","bank":"青岛","value":"17335.35","selected":false},{"name":"湖南","bank":"长沙","value":"16335.35","selected":false},{"name":"河北","bank":"石家庄","value":"15335.35","selected":false},{"name":"西藏","bank":"拉萨","value":"14335.35","selected":false},{"name":"辽宁","bank":"大连","value":"13335.35","selected":false},{"name":"浙江","bank":"杭州","value":"12335.35","selected":true},{"name":"河南","bank":"郑州","value":"11335.35","selected":false},{"name":"上海","bank":"上海","value":"10335.35","selected":false},{"name":"台湾","bank":null,"value":"0","selected":false},{"name":"香港","bank":null,"value":"0","selected":false},{"name":"澳门","bank":null,"value":"0","selected":false},{"name":"南海诸岛","bank":null,"value":"0","selected":false}],[{"name":"四川","bank":"成都","value":"9335.35","selected":false},{"name":"山东","bank":"济南","value":"8335.35","selected":false},{"name":"辽宁","bank":"沈阳","value":"7335.35","selected":false},{"name":"北京","bank":"北京","value":"6335.35","selected":false},{"name":"陕西","bank":"西安","value":"5335.35","selected":false},{"name":"浙江","bank":"宁波","value":"4335.35","selected":false},{"name":"内蒙古","bank":"呼和浩特","value":"38335.35","selected":false},{"name":"贵州","bank":"贵阳","value":"37335.35","selected":false},{"name":"广东","bank":"深圳","value":"36335.35","selected":false},{"name":"安徽","bank":"合肥","value":"35335.35","selected":false},{"name":"天津","bank":"天津","value":"34335.35","selected":false},{"name":"江苏","bank":"南京","value":"3335.35","selected":false},{"name":"云南","bank":"昆明","value":"33335.35","selected":false},{"name":"宁夏","bank":"银川","value":"32335.35","selected":false},{"name":"海南","bank":"海口","value":"31335.35","selected":false},{"name":"青海","bank":"西宁","value":"30335.35","selected":false},{"name":"福建","bank":"厦门","value":"29335.35","selected":false},{"name":"江西","bank":"南昌","value":"28335.35","selected":false},{"name":"福建","bank":"福州","value":"27335.35","selected":false},{"name":"黑龙江","bank":"哈尔滨","value":"26335.35","selected":false},{"name":"广西","bank":"南宁","value":"25335.35","selected":false},{"name":"新疆","bank":"乌鲁木齐","value":"24335.35","selected":false},{"name":"湖北","bank":"武汉","value":"2335.35","selected":false},{"name":"吉林","bank":"长春","value":"23335.35","selected":false},{"name":"重庆","bank":"重庆","value":"22335.35","selected":false},{"name":"广东","bank":"广州","value":"21335.35","selected":false},{"name":"甘肃","bank":"兰州","value":"20335.35","selected":false},{"name":"江苏","bank":"苏州","value":"19335.35","selected":false},{"name":"山西","bank":"太原","value":"18335.35","selected":false},{"name":"山东","bank":"青岛","value":"17335.35","selected":false},{"name":"湖南","bank":"长沙","value":"16335.35","selected":false},{"name":"河北","bank":"石家庄","value":"15335.35","selected":false},{"name":"西藏","bank":"拉萨","value":"14335.35","selected":false},{"name":"辽宁","bank":"大连","value":"13335.35","selected":false},{"name":"浙江","bank":"杭州","value":"12335.35","selected":true},{"name":"河南","bank":"郑州","value":"11335.35","selected":false},{"name":"上海","bank":"上海","value":"10335.35","selected":false},{"name":"台湾","bank":null,"value":"0","selected":false},{"name":"香港","bank":null,"value":"0","selected":false},{"name":"澳门","bank":null,"value":"0","selected":false},{"name":"南海诸岛","bank":null,"value":"0","selected":false}]],"option":null,"axis":null},{"name":"大屏1全行存款排名","data":[[{"name":"成都","value":"9335.35"},{"name":"济南","value":"8335.35"},{"name":"沈阳","value":"7335.35"},{"name":"北京","value":"6335.35"},{"name":"西安","value":"5335.35"}]],"option":{"legend":null,"grid":{"left":"","right":null,"top":"","bottom":null,"width":"","height":""}},"axis":["成都","济南","沈阳","北京","西安"]},{"name":"大屏1全行贷款排名","data":[[{"name":"成都","value":"9335.35"},{"name":"济南","value":"8335.35"},{"name":"沈阳","value":"7335.35"},{"name":"北京","value":"6335.35"},{"name":"西安","value":"5335.35"}]],"option":{"legend":null,"grid":{"left":"","right":null,"top":"","bottom":null,"width":"","height":""}},"axis":["成都","济南","沈阳","北京","西安"]},{"name":"大屏1营收趋势","data":[[{"name":"19:30","value":"305.45"},{"name":"19:00","value":"305.45"},{"name":"18:30","value":"305.45"},{"name":"18:00","value":"305.45"},{"name":"17:30","value":"305.45"},{"name":"17:00","value":"305.45"},{"name":"16:30","value":"305.45"},{"name":"16:00","value":"305.45"},{"name":"15:30","value":"305.45"},{"name":"15:00","value":"305.45"}]],"option":{"legend":null,"grid":{"left":"","right":null,"top":"","bottom":null,"width":"","height":""},"series":null},"axis":["19:30","19:00","18:30","18:00","17:30","17:00","16:30","16:00","15:30","15:00"]},{"name":"大屏1分行存贷款情况","data":[{"name":"成都","store":"9335.35","loan":"9335.35"},{"name":"济南","store":"8335.35","loan":"8335.35"},{"name":"沈阳","store":"7335.35","loan":"7335.35"},{"name":"北京","store":"6335.35","loan":"6335.35"},{"name":"西安","store":"5335.35","loan":"5335.35"},{"name":"宁波","store":"4335.35","loan":"4335.35"},{"name":"呼和浩特","store":"38335.35","loan":"38335.35"},{"name":"贵阳","store":"37335.35","loan":"37335.35"},{"name":"深圳","store":"36335.35","loan":"36335.35"},{"name":"合肥","store":"35335.35","loan":"35335.35"},{"name":"天津","store":"34335.35","loan":"34335.35"},{"name":"南京","store":"3335.35","loan":"3335.35"},{"name":"昆明","store":"33335.35","loan":"33335.35"},{"name":"银川","store":"32335.35","loan":"32335.35"},{"name":"海口","store":"31335.35","loan":"31335.35"},{"name":"西宁","store":"30335.35","loan":"30335.35"},{"name":"厦门","store":"29335.35","loan":"29335.35"},{"name":"南昌","store":"28335.35","loan":"28335.35"},{"name":"福州","store":"27335.35","loan":"27335.35"},{"name":"哈尔滨","store":"26335.35","loan":"26335.35"},{"name":"南宁","store":"25335.35","loan":"25335.35"},{"name":"乌鲁木齐","store":"24335.35","loan":"24335.35"},{"name":"武汉","store":"2335.35","loan":"2335.35"},{"name":"长春","store":"23335.35","loan":"23335.35"},{"name":"重庆","store":"22335.35","loan":"22335.35"},{"name":"广州","store":"21335.35","loan":"21335.35"},{"name":"兰州","store":"20335.35","loan":"20335.35"},{"name":"苏州","store":"19335.35","loan":"19335.35"},{"name":"太原","store":"18335.35","loan":"18335.35"},{"name":"青岛","store":"17335.35","loan":"17335.35"},{"name":"长沙","store":"16335.35","loan":"16335.35"},{"name":"石家庄","store":"15335.35","loan":"15335.35"},{"name":"拉萨","store":"14335.35","loan":"14335.35"},{"name":"大连","store":"13335.35","loan":"13335.35"},{"name":"杭州","store":"12335.35","loan":"12335.35"},{"name":"郑州","store":"11335.35","loan":"11335.35"},{"name":"上海","store":"10335.35","loan":"10335.35"}],"option":null,"axis":null},{"name":"大屏1同业负债结构","data":["335.35","335.35","335.35","335.35","335.35"],"option":{"radar":{"radius":"50%","indicator":[{"name":"货币市场交易","max":"52000"},{"name":"同业存放活期","max":"52000"},{"name":"同业定期存单","max":"52000"},{"name":"卖出回购票据","max":"52000"},{"name":"同业存放定期","max":"52000"}]}},"axis":null}]}
 

// var str = {"name":"大屏二全部数据","reportData":[{"name":"大屏2一般对公存款排名前十","data":[{"name":"呼和浩特","value":"38335.35"},{"name":"贵阳","value":"37335.35"},{"name":"深圳","value":"36335.35"},{"name":"合肥","value":"35335.35"},{"name":"天津","value":"34335.35"},{"name":"昆明","value":"33335.35"},{"name":"银川","value":"32335.35"},{"name":"海口","value":"31335.35"},{"name":"西宁","value":"30335.35"},{"name":"厦门","value":"29335.35"}],"option":{"legend":null,"grid":{"left":"","right":null,"top":"","bottom":null,"width":"","height":""}},"axis":["呼和浩特","贵阳","深圳","合肥","天津","昆明","银川","海口","西宁","厦门"]},{"name":"大屏2一般对公贷款排名后十","data":[{"name":"武汉","value":"2335.35"},{"name":"南京","value":"3335.35"},{"name":"宁波","value":"4335.35"},{"name":"西安","value":"5335.35"},{"name":"北京","value":"6335.35"},{"name":"沈阳","value":"7335.35"},{"name":"济南","value":"8335.35"},{"name":"成都","value":"9335.35"},{"name":"上海","value":"10335.35"},{"name":"郑州","value":"11335.35"}],"option":{"legend":null,"grid":{"left":"","right":null,"top":"","bottom":null,"width":"","height":""}},"axis":["武汉","南京","宁波","西安","北京","沈阳","济南","成都","上海","郑州"]},{"name":"大屏2树形图","data":[{"name":"一般对公存款","value":"-150.10"},{"name":"一般对公存款","value":"-80.10"},{"name":"一般对公存款","value":"-80.10"}],"option":null,"axis":null},{"name":"大屏2公司贷款利息收入趋势","data":[{"name":"19:30","value":"305.45"},{"name":"19:00","value":"305.45"},{"name":"18:30","value":"305.45"},{"name":"18:00","value":"305.45"},{"name":"17:30","value":"305.45"},{"name":"17:00","value":"305.45"},{"name":"16:30","value":"305.45"},{"name":"16:00","value":"305.45"},{"name":"15:30","value":"305.45"},{"name":"15:00","value":"305.45"}],"option":{"legend":null,"grid":{"left":null,"right":null,"top":null,"bottom":null,"width":null,"height":null},"series":null},"axis":{"data":["19:30","19:00","18:30","18:00","17:30","17:00","16:30","16:00","15:30","15:00"],"min":"305.45"}},{"name":"大屏2存款贷款分析（存款）","data":[{"name":"对公基础性存款","value":"335.35"},{"name":"对公结构性存款","value":"335.35"},{"name":"主动负责+单位保本理财","value":"335.35"}],"option":{"legend":{"data":["对公基础性存款","对公结构性存款","主动负责+单位保本理财"]},"grid":{"left":null,"right":null,"top":null,"bottom":null,"width":null,"height":null},"series":[{"radius":["25%","50%"]}]},"axis":null},{"name":"大屏2存款贷款分析（贷款）","data":[{"name":"对公短期贷款","value":"335.35"},{"name":"对公中长期贷款","value":"335.35"},{"name":"逾期贷款","value":"335.35"},{"name":"票据融资","value":"335.35"}],"option":{"legend":{"data":["对公短期贷款","对公中长期贷款","逾期贷款","票据融资"]},"grid":{"left":null,"right":null,"top":null,"bottom":null,"width":null,"height":null},"series":[{"radius":["25%","50%"]}]},"axis":null},{"name":"大屏2公司客户数","data":[["1413632","1513632","1525279","1504814","1500788","1504814"],["100000","11647","15073","-","-","19465"],["-","-","-","35538","4026","-"]],"option":{"legend":null,"grid":{"left":null,"right":null,"top":null,"bottom":null,"width":null,"height":null}},"axis":["20181130","20181231","20190131","20190228","20190331","20190430"]}]}
//
// //var str = {"name":"大屏三全部数据","reportData":[{"name":"大屏3保证金存款排名前十","data":[{"name":"成都","value":"9335.35"},{"name":"济南","value":"8335.35"},{"name":"沈阳","value":"7335.35"},{"name":"北京","value":"6335.35"},{"name":"西安","value":"5335.35"},{"name":"宁波","value":"4335.35"},{"name":"呼和浩特","value":"38335.35"},{"name":"贵阳","value":"37335.35"},{"name":"深圳","value":"36335.35"},{"name":"合肥","value":"35335.35"}],"option":{"legend":null,"grid":{"left":"","right":null,"top":"","bottom":null,"width":"","height":""}},"axis":["成都","济南","沈阳","北京","西安","宁波","呼和浩特","贵阳","深圳","合肥"]},{"name":"大屏3保证金存款排名后十","data":[{"name":"上海","value":"10335.35"},{"name":"郑州","value":"11335.35"},{"name":"杭州","value":"12335.35"},{"name":"大连","value":"13335.35"},{"name":"拉萨","value":"14335.35"},{"name":"石家庄","value":"15335.35"},{"name":"长沙","value":"16335.35"},{"name":"青岛","value":"17335.35"},{"name":"太原","value":"18335.35"},{"name":"苏州","value":"19335.35"}],"option":{"legend":null,"grid":{"left":"","right":null,"top":"","bottom":null,"width":"","height":""}},"axis":["上海","郑州","杭州","大连","拉萨","石家庄","长沙","青岛","太原","苏州"]},{"name":"大屏3云图","data":[{"name":"保证金存款","value":"-150.10"},{"name":"保证金存款","value":"-80.10"},{"name":"保证金存款","value":"-80.10"}],"option":null,"axis":null},{"name":"大屏3零售贷款利息收入趋势","data":[{"name":"19:30","value":"305.45"},{"name":"19:00","value":"305.45"},{"name":"18:30","value":"305.45"},{"name":"18:00","value":"305.45"},{"name":"17:30","value":"305.45"},{"name":"17:00","value":"305.45"},{"name":"16:30","value":"305.45"},{"name":"16:00","value":"305.45"},{"name":"15:30","value":"305.45"},{"name":"15:00","value":"305.45"}],"option":{"legend":null,"grid":{"left":null,"right":null,"top":null,"bottom":null,"width":null,"height":null},"series":null},"axis":{"data":["19:30","19:00","18:30","18:00","17:30","17:00","16:30","16:00","15:30","15:00"],"min":"305.45"}},{"name":"大屏3存款贷款分析（存款）","data":[{"name":"结算行存款","value":"335.35"},{"name":"其他定期存储存款","value":"335.35"},{"name":"大额存单","value":"335.35"},{"name":"结构性存款","value":"335.35"}],"option":{"legend":{"data":["结算性存款","其他定期存储存款","大额存单","结构性存款"]},"grid":{"left":null,"right":null,"top":null,"bottom":null,"width":null,"height":null},"series":[{"radius":["25%","50%"]}]},"axis":null},{"name":"大屏3存款贷款分析（贷款）","data":[{"name":"住房按揭贷款","value":"335.35"},{"name":"经营性贷款","value":"335.35"},{"name":"消费贷款","value":"335.35"},{"name":"商用房贷款","value":"335.35"},{"name":"其它贷款","value":"335.35"}],"option":{"legend":{"data":["住房按揭贷款","经营性贷款","消费贷款","商用房贷款","其它贷款"]},"grid":{"left":null,"right":null,"top":null,"bottom":null,"width":null,"height":null},"series":[{"radius":["25%","50%"]}]},"axis":null},{"name":"大屏3个人客户数","data":[{"name":"20181130","value":"67201407"},{"name":"20181231","value":"68192905"},{"name":"20190131","value":"69306897"},{"name":"20190228","value":"69798885"},{"name":"20190331","value":"70724496"},{"name":"20190430","value":"71724496"}],"option":{"legend":null,"grid":{"left":null,"right":null,"top":null,"bottom":null,"width":null,"height":null}},"axis":["20181130","20181231","20190131","20190228","20190331","20190430"]}]}
//
//
// rseolveData(str)








function WebSocketConnect(url) {
	var ws = null
	var msgJson = {
		evt:null,
		state:null	
	};
	WebSocketConnect.prototype.connect = function(callback,message) {
            ws = new WebSocket(url)
            WebSocketConnect.prototype.close = function(){
                ws.close()
            }
            ws.onclose = function(evt){
                    msgJson.evt = evt
                    msgJson.state = ws.readyStat
                    callback(msgJson)
            };
            
            /**
             * connect error
             */
            ws.onerror = function(evt){
                msgJson.evt = evt
                msgJson.state = ws.readyState
                callback(msgJson)
            };

            /**
             * connect open
             */
            ws.onopen = function(evt){
                msgJson.evt = evt
                msgJson.state = ws.readyState
                ws.send(message)
                callback(msgJson)

            }
            
            /**
             * connect return message
             */
            ws.onmessage = function(evt){
                msgJson.evt = evt
                msgJson.state = ws.readyState
                callback(msgJson)	
            }
	}
}


