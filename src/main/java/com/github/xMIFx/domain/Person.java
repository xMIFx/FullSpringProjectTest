package com.github.xMIFx.domain;

import com.github.xMIFx.utils.validators.Phone;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Vlad on 10.08.2015.
 */
@Entity
@Table(name = "PERSONS")
public class Person implements Serializable {
    private Long id;

    @NotEmpty
    @Size(min = 2, max = 30)
    private String name;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Past
    private Date birthDay;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Phone
    private String phoneNumber;
    @NotNull
    @Min(350)
    @Max(100000)
    private BigDecimal salary;

    public Person() {
        this.salary = BigDecimal.valueOf(0.0);
        this.birthDay = new Date();
    }

    public Person(String name, Date birthDay, String email, String phoneNumber, BigDecimal salary) {
        this.name = name;
        this.birthDay = birthDay;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }

    public Person(Long id, String name, Date birthDay, String email, String phoneNumber, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.birthDay = birthDay;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "birthDay")
    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "phone")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "salary")
    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal money) {
        this.salary = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return !(id != null ? !id.equals(person.id) : person.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return String.format("%d:[%s, %s] - [%s, %s] - %10.2f", id, name, birthDay, email, phoneNumber, salary);

    }
}
