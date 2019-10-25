package com.spdb.nrpt.controller.entrance;

import com.spdb.nrpt.entity.entrance.EntranceListData;
import com.spdb.nrpt.mapper.reportDataMapper.NRPTAndPortalMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class EntranceController {

    @Autowired
    private NRPTAndPortalMapper nrptAndPortalMapper;

    //大屏入口，获得当前用户关于大屏所拥有的报表权限
    @RequestMapping("/getEntranceListData")
    @ResponseBody
    public List<EntranceListData> getEntranceListData(HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        String userID = httpSession.getAttribute("userID").toString();
        String orgID = httpSession.getAttribute("orgID").toString();
        String redID = httpSession.getAttribute("resID").toString();
        log.info("用户信息为"+userID+"---"+orgID+"---"+redID);

        List<EntranceListData> dataList = nrptAndPortalMapper.selectPortalDataFormMenuOrderByUserIDAndResID(userID,redID);
        return dataList;
    }

    //portal端嵌入的大屏列表页面
    @RequestMapping("/getEntrancePage")
    public String getEntrancePage(){
        return "Home/HomeInit";
    }
    
    @RequestMapping("/humanResourcesPower")
    public String humanResourcesPower(HttpServletRequest request){
    	HttpSession httpSession = request.getSession();
        String userID = httpSession.getAttribute("userID").toString();
        String orgID = httpSession.getAttribute("orgID").toString();
        return "Home/HomeInit";
    }

}
