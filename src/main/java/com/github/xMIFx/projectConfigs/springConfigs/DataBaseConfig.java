package com.github.xMIFx.projectConfigs.springConfigs;


import com.github.xMIFx.repositories.implementationForDAO.PersonHibernateDAOImpl;
import com.github.xMIFx.repositories.implementationForDAO.PersonJPADAOImpl;
import com.github.xMIFx.repositories.interfacesForDAO.PersonDAO;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DriverManagerDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@PropertySource("classpath:jdbc.properties")
public class DataBaseConfig {
    private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";

    @Autowired
    Environment env;

    @Bean(name = "personDAO")
    public PersonDAO personDAO() {
        return new PersonJPADAOImpl();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setProperties(c3p0Properties());
        dataSource.setDriverClass(env.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setJdbcUrl(env.getRequiredProperty("jdbc.url"));
        dataSource.setUser(env.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    /*Hibernate*/
    @Bean(name = "sessionFact")
    public LocalSessionFactoryBean sessionFact() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setHibernateProperties(hibernateProperties());
        sessionFactory.setPackagesToScan("com.github.xMIFx.domain");
        sessionFactory.setDataSource(dataSource());

        //sessionFactory.setConfigLocation(new ClassPathResource(CONFIG_FILE_LOCATION));
        return sessionFactory;
    }

    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(
            SessionFactory sessionFact) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(
                sessionFact);
        return transactionManager;
    }

/*JPA*/
    @Bean(name = "entityMan")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{"com.github.xMIFx.domain"});
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateProperties());

        return em;
    }
    @Autowired
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }
    @Autowired
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    /*Properties*/
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
     /*   properties.put("hibernate.connection.provider_class", env.getRequiredProperty("hibernate.connection.provider_class"));*/
        return properties;
    }

    private Properties c3p0Properties() {
        Properties properties = new Properties();
        properties.put("hibernate.c3p0.min_size", env.getRequiredProperty("c3p0.min_size"));
        properties.put("hibernate.c3p0.max_size", env.getRequiredProperty("c3p0.max_size"));
        properties.put("hibernate.c3p0.timeout", env.getRequiredProperty("c3p0.timeout"));
        properties.put("hibernate.c3p0.max_statements", env.getRequiredProperty("c3p0.max_statements"));
        properties.put("hibernate.c3p0.idle_test_period", env.getRequiredProperty("c3p0.idle_test_period"));
        return properties;
    }
}
