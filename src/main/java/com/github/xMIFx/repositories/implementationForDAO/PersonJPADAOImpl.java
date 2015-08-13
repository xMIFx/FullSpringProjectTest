package com.github.xMIFx.repositories.implementationForDAO;

import com.github.xMIFx.domain.Person;
import com.github.xMIFx.repositories.interfacesForDAO.PersonDAO;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlad on 12.08.2015.
 */
public class PersonJPADAOImpl implements PersonDAO {
    @Autowired
    @Qualifier("entityManFac")
    private EntityManagerFactory entityManFac;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Person> getAll() {
        List<Person> personList = new ArrayList<>();
        /*EntityManager entityManager = entityManFac.createEntityManager();*/
          /*  personList = (List<Person>) entityManager.createC.createCriteria(Person.class).list();*/
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> q = cb.createQuery(Person.class);
        Root<Person> c = q.from(Person.class);
        q.select(c);
        TypedQuery<Person> tq = entityManager.createQuery(q);
        personList = tq.getResultList();
        return personList;
    }

    @Transactional
    @Override
    public boolean save(Person person) {
        /*EntityManager entityManager = entityManFac.createEntityManager();*/
        entityManager.persist(person);
        return true;
    }

    @Transactional
    @Override
    public boolean remove(Person person) {
        entityManager.remove(entityManager.merge(person));
        return true;
    }

    @Override
    public Person getById(Long id) {

       /* EntityManager entityManager = entityManFac.createEntityManager();*/
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> q = cb.createQuery(Person.class);
        Root<Person> c = q.from(Person.class);
        ParameterExpression<Long> p = cb.parameter(Long.class);
        q.select(c).where(cb.equal(c.get("id"), p));
        TypedQuery<Person> tq = entityManager.createQuery(q);
        tq.setParameter(p, id);
        Person person = tq.getSingleResult();
        return person;
    }
}
