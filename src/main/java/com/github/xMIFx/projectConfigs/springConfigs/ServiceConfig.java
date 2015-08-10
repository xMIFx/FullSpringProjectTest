package com.github.xMIFx.projectConfigs.springConfigs;

import com.github.xMIFx.services.implementationForService.PersonServiceImpl;
import com.github.xMIFx.services.interfaceForService.PersonService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ServiceConfig {

    @Bean(name = "personService")
    public PersonService personService(){
        return new PersonServiceImpl();
    }

}
