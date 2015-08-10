package com.github.xMIFx.repositories.implementationForDAO;

import com.github.xMIFx.domain.Person;
import com.github.xMIFx.repositories.interfacesForDAO.PersonDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Vlad on 10.08.2015.
 */
public class PersonHibernateDAOImpl implements PersonDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonHibernateDAOImpl.class.getName());
    @Autowired
    @Qualifier("sessionFact")
    private SessionFactory sessionFact;

    @Transactional
    @Override
    public List<Person> getAll() {
        Session session = sessionFact.getCurrentSession();
        List<Person> personList = (List<Person>) session.createCriteria(Person.class).list();
        return personList;

    }

    @Transactional
    @Override
    public boolean save(Person person) {
        Session session = sessionFact.getCurrentSession();
        session.save(person);
        return true;
    }

    @Transactional
    @Override
    public boolean remove(Person person) {
        Session session = sessionFact.getCurrentSession();
        session.delete(person);
        return true;
    }
}
