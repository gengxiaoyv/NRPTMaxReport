package com.spdb.nrpt.config.dataSourceConfig;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
//原有大屏的配置文件

@Configuration
@MapperScan(basePackages = {"com.spdb.nrpt.mapper.backstageMapper","com.spdb.nrpt.mapper.baseDataMapper","com.spdb.nrpt.mapper.demoMapper","com.spdb.nrpt.mapper.quartzMapper","com.spdb.nrpt.mapper.reportDataMapper","com.spdb.nrpt.mapper.sasGameMapper"},sqlSessionFactoryRef = "bieeSqlSessionFactory")
public class Biee_DataSourceConfig {

    // 将这个对象放入Spring容器中
    @Bean(name = "bieeDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.biee")
    public DataSource getDateSource1() {
        return DataSourceBuilder.create().build();
    }
    @Bean(name = "bieeSqlSessionFactory")
    @Primary
    public SqlSessionFactory test1SqlSessionFactory(@Qualifier("bieeDataSource") DataSource datasource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setMapperLocations(
                // 设置mybatis的xml所在位置
                new PathMatchingResourcePatternResolver().getResources("classpath*:mybatis/*/*.xml"));
        return bean.getObject();
    }
    @Bean("bieeSqlSessionTemplate")
    // 表示这个数据源是默认数据源
    @Primary
    public SqlSessionTemplate test1sqlsessiontemplate(
            @Qualifier("bieeSqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }
}
