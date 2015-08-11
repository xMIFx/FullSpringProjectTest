package com.github.xMIFx.services.interfaceForService;

import com.github.xMIFx.domain.Person;

import java.util.List;

/**
 * Created by Vlad on 10.08.2015.
 */
public interface PersonService {
    List<Person> getAll();

    boolean save(Person person);

    boolean remove(Person person);

    Person getById(Long id);
}
