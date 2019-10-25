
var publick = {
    //饼状图的线性渐变
    request:1,
    linearColor(starColor,endCorlor) {
        if(!starColor || !endCorlor) {
             console.log('参数不能为空')
             return 
        }
        let color = {
            type: 'linear',
            x: 0,
            y: 0,
            x2: 0,
            y2: 1,
            colorStops: [ 
                {
                    offset: 0, color: starColor        // 0% 处的颜色
                },
                {
                    offset: 1, color: endCorlor        // 100% 处的颜色
                }
            ],
            global: false // 缺省为 false
        };
        return color
    },
    //bar 的渐变色彩
    barColor(starColor,endCorlor) {
      return  new echarts.graphic.LinearGradient( 
            0, 0, 1, 0,
            [
                {offset: 0, color: starColor},
                {offset: 1, color: endCorlor},

            ]
        )
    
    },
        //初始化dom，以及传递参数，绘制图表
    piceChart(elementId,option,subject = null) {
        var store = echarts.init(document.getElementById(elementId),subject)
        var option = option
        store.setOption(option)
     },
    //处理数字加点
    formatNum(str){
        var newStr = "";
        var count = 0
        var isNegative = ''
        var arr = []
        if(str < 0) {
            isNegative = '-'
            arr = str.split("")
            arr.splice(0,1)
            str = arr.join('')
        }
        if(str.indexOf(".")==-1){
            for(var i=str.length-1;i>=0;i--){
                if(count % 3 == 0 && count != 0){
                    newStr = str.charAt(i) + "," + newStr;
                }else{
                    newStr = str.charAt(i) + newStr;
                }
                count++;
            }
            //  str = newStr + ".00";  //自动补小数点后两位
            str = newStr;
            return (isNegative + str)
        }  else {
            for(var i = str.indexOf(".")-1;i>=0;i--){
                if(count % 3 == 0 && count != 0){
                    newStr = str.charAt(i) + "," + newStr;
                }else{
                    newStr = str.charAt(i) + newStr; //逐个字符相接起来
                }
                count++;
            }
            str = newStr + (str + "00").substr((str + "00").indexOf("."),3);

            return (isNegative + str)
        }
    },
     //ajax请求
    funAjax(method,url,flag,data,calback) {
        var ajax = null
        if(window.XMLHttpRequest) {
            ajax = new XMLHttpRequest()
        }else{
            ajax = new ActiveXObject("Microsoft.XMLHttp")
        }
        method = method.toUpperCase()
        if(method == "GET"){
            ajax.open(method,url,flag)
            ajax.send()
        }else if(method == "POST"){
            ajax.open(method,url,flag)
            ajax.setRequestHeader("Content-type","application/x-www-form-urlencoded")
            ajax.send(data)
        }
        ajax.onreadystatechange = function() {
            if(ajax.readyState == 4){
                if(ajax.status == 200){
                    calback(JSON.parse(ajax.responseText))
                }
            }
        }
    },
    changColor(dom,origin,target) {
        console.log(dom,origin,target)
        dom.className = target
        console.log(dom.className,11111111111111111)
        setTimeout(() => {
            dom.className = origin
            console.log(dom.className)
        },5*1000)
    }
}

