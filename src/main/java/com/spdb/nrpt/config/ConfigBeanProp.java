package com.spdb.nrpt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 读取config.properties的信息
 */
@Component(value = "ConfigBeanProp")
@ConfigurationProperties(prefix = "nrpt")
@PropertySource(value = "classpath:config/config.properties")
public class ConfigBeanProp {

   //执行定时任务IP
   private String ip;
   //ESB请求的URL
   private String url;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

