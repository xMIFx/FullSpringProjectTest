package com.github.xMIFx.services.implementationForService;

import com.github.xMIFx.domain.Person;
import com.github.xMIFx.repositories.interfacesForDAO.PersonDAO;
import com.github.xMIFx.services.interfaceForService.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Created by Vlad on 10.08.2015.
 */
public class PersonServiceImpl implements PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class.getName());

    @Autowired
    @Qualifier("personDAO")
    private PersonDAO personDAO;

    @Override
    public List<Person> getAll() {
        return personDAO.getAll();
    }

    @Override
    public boolean save(Person person) {
        return personDAO.save(person);
    }

    @Override
    public boolean remove(Person person) {
        return personDAO.remove(person);
    }
}
