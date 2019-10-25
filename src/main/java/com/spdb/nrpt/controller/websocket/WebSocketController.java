package com.spdb.nrpt.controller.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebSocketController {

    @RequestMapping("/getWebSocketPage")
    public String getWebSocketPage(){
        return "websocket/Websocket";
    }

}
