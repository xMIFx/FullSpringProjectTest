package com.github.xMIFx.view.servlets.controllers;

import com.github.xMIFx.domain.Person;
import com.github.xMIFx.services.interfaceForService.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Vlad on 10.08.2015.
 */
@Controller
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);


    @Autowired
    @Qualifier("personService")
    private PersonService personService;


    @ModelAttribute("person")
    public Person createStudent() {
        return new Person();
    }

    @RequestMapping(value = "/")
    protected String doRedirect() {
        return "redirect:/main.do";
    }

    @RequestMapping(value = "main.do")
    protected ModelAndView doGet() {
        List<Person> personList = personService.getAll();
        ModelAndView model = new ModelAndView("main");
        model.addObject("personList", personList);
        logger.info(personList.toString());
        return model;
        /*return "main";*/
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("person")@Valid Person person,
                             BindingResult result) {
        logger.info("Start adding");
        if (result.hasErrors()) {
            logger.info("Erorr when binding");
            return "main";
        }
        personService.save(person);

        return "redirect:/main.do";
    }

    @RequestMapping("/delete/{personID}")
    public String deleteContact(@PathVariable("personID") Long personID) {

        Person person = personService.getById(personID);
        personService.remove(person);

        return "redirect:/main.do";
    }
}
