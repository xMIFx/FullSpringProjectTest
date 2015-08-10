package com.github.xMIFx.projectConfigs.springConfigs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import({ServiceConfig.class, DataBaseConfig.class})
@EnableTransactionManagement
@ComponentScan(value={"com.github.xMIFx.services.implementationForService"
        ,"com.gitHub.xMIFx.repositories.implementationForDAO"
        ,"com.gitHub.xMIFx.view.servlets"})
public class MainSpringConfig {

}

