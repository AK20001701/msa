package com.msa.module7.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class SimpleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String someString;

    private Integer someInteger;

    private String anotherString;

    public SimpleEntity() {
    }

    public SimpleEntity(Long id, String someString, Integer someInteger, String anotherString) {
        this.id = id;
        this.someString = someString;
        this.someInteger = someInteger;
        this.anotherString = anotherString;
    }

    @Override
    public String toString() {
        return "SimpleEntity{" +
                "id=" + id +
                ", someString='" + someString + '\'' +
                ", someInteger=" + someInteger +
                ", anotherString='" + anotherString + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleEntity that = (SimpleEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSomeString() {
        return someString;
    }

    public void setSomeString(String someString) {
        this.someString = someString;
    }

    public Integer getSomeInteger() {
        return someInteger;
    }

    public void setSomeInteger(Integer someInteger) {
        this.someInteger = someInteger;
    }

    public String getAnotherString() {
        return anotherString;
    }

    public void setAnotherString(String anotherString) {
        this.anotherString = anotherString;
    }
}
