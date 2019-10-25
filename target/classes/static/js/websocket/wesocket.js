var host = document.location.host;

function ws(messages,fun) {
    webSocket = new WebSocket('ws://' + host + '/webSocket');
    // 建立 web socket 连接成功触发事件
    webSocket.onopen = function () {
        // 使用 send() 方法发送数据
        //发送东西
        //大屏二全部数据
        webSocket.send(messages)
        console.log("send message...");
    }

    // 接收服务端数据时触发事件
    webSocket.onmessage = function (evt) {
        //判断数据是那部分的
        console.log(evt)
        if(evt.data === 'Change') {
             console.log(11111111)
             fun()
        } else {
            var received_msg = JSON.parse(evt.data);
            rseolveData(received_msg)
        }

    };

    // 断开 web socket 连接成功触发事件
    webSocket.onclose = function () {
        console.log('close');
        setTimeout(
            function () {
                getServiceStatus();
            }, 10000);
    }
}
    function getServiceStatus() {
        try {
            var ajax = new XMLHttpRequest();
            ajax.open('GET','getServiceStatus',false)
            ajax.send()
            console.log(ajax.responseText);
            if (('Success')==ajax.responseText){
                window.location.reload();
            } else {
                setTimeout(
                    function(){
                        getServiceStatus();
                    },20000);
            }
        } catch (e) {
            console.log('error');
            setTimeout(
                function(){
                    getServiceStatus();
                },20000);
        }
    }


