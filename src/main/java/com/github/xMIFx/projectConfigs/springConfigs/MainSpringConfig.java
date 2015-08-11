package com.github.xMIFx.projectConfigs.springConfigs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.nio.charset.Charset;

@Configuration
@Import({ServiceConfig.class, DataBaseConfig.class})
@EnableTransactionManagement
@ComponentScan(value = {"com.github.xMIFx.services.implementationForService"
        , "com.github.xMIFx.repositories.implementationForDAO"
        , "com.github.xMIFx.view.servlets"})
public class MainSpringConfig {
    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
        resource.setBasename("classpath:messages");
        resource.setDefaultEncoding("UTF-8");
        return resource;
    }

    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }

}

