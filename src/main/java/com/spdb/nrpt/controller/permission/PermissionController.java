package com.spdb.nrpt.controller.permission;

import com.spdb.nrpt.util.RSAEncrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class PermissionController {

    //通过portal获得用户的信息
    @RequestMapping("getNRPTSystem")
    public String getNRPTSystem(String token, HttpServletRequest request){
        String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJ+Hu0ObmXnmA/s8lzImBDzxg37RmYGQiuSb6y1l2rjw8C5SIzLsBFpCzxjdM1MvDY8aSxSSRqrTY/+eaYR4FD/Mn8ydDD0dNGRxy3C8WN88CAeQ6K8tayMRDU42q0kxZVwBTgnFLmZn1qdF+QnNoK2lbww1/3STCTCXlx+OQO3NAgMBAAECgYAIXwf9mTmpgt9snJZWhFYumIHM8tS6TsprCp+Bp1md4M7Jr3e3YVktH1pctKbP06VDgB62eqKIrkUOEdL5p49wYFz9oF7Rzj/xwAun0GyJLQqk+9KtNkdfHG/T9A4ttCXS0Xew9nR257m9f+zClxyBHEoQxZffmnvRG0owHbBI4QJBANdSGRVgNFFEf9ficYfNSZH0N/zGwtW/6IGksRr8S9mkXJvUQlLfvZuuz1C05hhQ2ITi2siLxYZOrUkvjRIBbKUCQQC9q1kO5ZBF0HqsamJtnWfXM5hPJtVrDehRZ4rhA8uEmiRyKsQV+DUaazH7M7lqykUs0THTPPSf8KlWWQayHOwJAkEAqW0AL8GzaP7tWYHks2blLONt6oi7ZlXLLbfZY9KCHI8oD7XFOlCzcXzrxCWTqC51MJsQbB0PH89oqt2vsytLwQJAGAGjEQ6fVybPEBmbAbLFnOhr48DPjHBDMmJ/ooFfdhYofMbK8Njsp75foiLm2gxl+wkzXEA/2iqU1FgKT+r3OQJBAJst/1TKJUGykBqf+lfXwz5YKOKX2CLdCK2R35k76UjgNkwhCaLVzMt3yi7HmshDlB7QTasZ5yFvdFSnZhTZToY=";
        try {
            token=token.replaceAll(" ","+");
            String res = RSAEncrypt.decrypt(token,privateKey);
            String decode = res;
            int flag1 = decode.indexOf("=")+1;
            int flag2 =  decode.indexOf("=",flag1)+1;
            int flag3 = decode.indexOf("=",flag2)+1;
            String userID = decode.substring(flag1,flag1+8);
            String userOrg = decode.substring(flag2,flag2+4);
            String resID = decode.substring(flag3);
            log.info("当前登录用户为："+userID+"---当前用户组织为："+userOrg+"---当前resID为"+resID);
            HttpSession httpSession = request.getSession();
            httpSession.setMaxInactiveInterval(60*60*24*365*10);
            httpSession.setAttribute("orgID",userOrg);
            httpSession.setAttribute("userID",userID);
            httpSession.setAttribute("resID",resID);
            return  "redirect:getEntrancePage";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  "error";
    }
    
    @RequestMapping("humanResources")
    public String humanResources(String token, HttpServletRequest request){
        String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJ+Hu0ObmXnmA/s8lzImBDzxg37RmYGQiuSb6y1l2rjw8C5SIzLsBFpCzxjdM1MvDY8aSxSSRqrTY/+eaYR4FD/Mn8ydDD0dNGRxy3C8WN88CAeQ6K8tayMRDU42q0kxZVwBTgnFLmZn1qdF+QnNoK2lbww1/3STCTCXlx+OQO3NAgMBAAECgYAIXwf9mTmpgt9snJZWhFYumIHM8tS6TsprCp+Bp1md4M7Jr3e3YVktH1pctKbP06VDgB62eqKIrkUOEdL5p49wYFz9oF7Rzj/xwAun0GyJLQqk+9KtNkdfHG/T9A4ttCXS0Xew9nR257m9f+zClxyBHEoQxZffmnvRG0owHbBI4QJBANdSGRVgNFFEf9ficYfNSZH0N/zGwtW/6IGksRr8S9mkXJvUQlLfvZuuz1C05hhQ2ITi2siLxYZOrUkvjRIBbKUCQQC9q1kO5ZBF0HqsamJtnWfXM5hPJtVrDehRZ4rhA8uEmiRyKsQV+DUaazH7M7lqykUs0THTPPSf8KlWWQayHOwJAkEAqW0AL8GzaP7tWYHks2blLONt6oi7ZlXLLbfZY9KCHI8oD7XFOlCzcXzrxCWTqC51MJsQbB0PH89oqt2vsytLwQJAGAGjEQ6fVybPEBmbAbLFnOhr48DPjHBDMmJ/ooFfdhYofMbK8Njsp75foiLm2gxl+wkzXEA/2iqU1FgKT+r3OQJBAJst/1TKJUGykBqf+lfXwz5YKOKX2CLdCK2R35k76UjgNkwhCaLVzMt3yi7HmshDlB7QTasZ5yFvdFSnZhTZToY=";
        try {
            token=token.replaceAll(" ","+");
            String res = RSAEncrypt.decrypt(token,privateKey);
            String decode = res;
            int flag1 = decode.indexOf("=")+1;
            int flag2 =  decode.indexOf("=",flag1)+1;
            String userID = decode.substring(flag1,flag1+8);
            String userOrg = decode.substring(flag2,flag2+4);
            log.info("当前登录用户为："+userID+"---当前用户组织为："+userOrg);
            HttpSession httpSession = request.getSession();
            httpSession.setMaxInactiveInterval(60*60*24*365*10);
            httpSession.setAttribute("orgID",userOrg);
            httpSession.setAttribute("userID",userID);
            return  "redirect:humanResourcesPower";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  "error";
    }

    //获取服务器状态
    @RequestMapping("/getServiceStatus")
    @ResponseBody
    public String getServiceStatus(HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        if (httpSession.getAttribute("orgID")==null){
            return "error";
        }
        return "Success";
    }

}
