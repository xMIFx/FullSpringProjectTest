package com.github.xMIFx.view.servlets.controllers;

import com.github.xMIFx.domain.Person;
import com.github.xMIFx.services.interfaceForService.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Vlad on 10.08.2015.
 */
@Controller
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);


    @Autowired
    @Qualifier("personService")
    private PersonService personService;


    @ModelAttribute("stud")
    public Person createStudent() {
        return new Person();
    }

    @RequestMapping(value = "/")
    protected String doRedirect(){
        return "redirect:/main.do";
    }

    @RequestMapping(value = "main.do" )
    protected ModelAndView doGet(){
        List<Person> personList = personService.getAll();
        ModelAndView model = new ModelAndView("main");
        model.addObject("personList", personList);
        return model;

        /*return "main";*/
    }
}
