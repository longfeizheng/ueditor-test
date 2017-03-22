package com.test.ueditor.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.servlet.MultipartConfigElement;

/**
 * Created on 2017/3/18 0018.
 *
 * @author zlf
 * @since 1.0
 */
@Configuration
public class WebAppConfig implements EnvironmentAware {

    private static Logger log = LoggerFactory.getLogger(WebAppConfig.class);

    @Value("${spring.http.multipart.max-file-size}")
    private String maxFileSize;
    @Value("${spring.http.multipart.max-request-size}")
    private String maxRequestSize;
//    @Value("${spring.http.multipart.location}")
//    private String fileLocation;

    private Environment environment;
    private RelaxedPropertyResolver datasourcePropertyResolver;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(this.maxFileSize);
        factory.setMaxRequestSize(this.maxRequestSize);
//        factory.setLocation(this.fileLocation);
        return factory.createMultipartConfig();
    }

}
