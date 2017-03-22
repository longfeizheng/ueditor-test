package com.test.ueditor.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * Created on 2017/3/22 0022.
 *
 * @author zlf
 * @since 1.0
 */
public class DataSourceAutoConfiguration implements EnvironmentAware {

    private static Logger log = LoggerFactory.getLogger(DataSourceAutoConfiguration.class);

    private Environment environment;
    private RelaxedPropertyResolver datasourcePropertyResolver;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource(){
        DataSource dataSource = new DataSource();
        dataSource.setUrl(datasourcePropertyResolver.getProperty("url"));
        dataSource.setUsername(datasourcePropertyResolver.getProperty("username"));
        dataSource.setPassword(datasourcePropertyResolver.getProperty("password"));
        dataSource.setDriverClassName(datasourcePropertyResolver.getProperty("driver-class-name"));
        return dataSource;
    }
}
