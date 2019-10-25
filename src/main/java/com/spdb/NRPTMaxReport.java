package com.spdb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
//@MapperScan("com.spdb.nrpt.mapper.*.*")//扫描：该包下相应的class,主要是MyBatis的持久化类.
public class NRPTMaxReport extends SpringBootServletInitializer implements WebApplicationInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(NRPTMaxReport.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(NRPTMaxReport.class, args);
    }
}