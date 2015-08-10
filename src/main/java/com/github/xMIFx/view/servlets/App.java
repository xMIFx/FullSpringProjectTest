package com.github.xMIFx.view.servlets;

import com.github.xMIFx.domain.Person;
import com.github.xMIFx.projectConfigs.springConfigs.MainSpringConfig;
import com.github.xMIFx.services.interfaceForService.PersonService;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Created by Vlad on 10.08.2015.
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(MainSpringConfig.class);
        PersonService personService = (PersonService) context.getBean("personService");
        Person person = new Person();
        person.setName("MIKE");
        personService.save(person);
        List<Person> personList = personService.getAll();
        System.out.println(personList);

    }
}
