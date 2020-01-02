package com.java.demo.javabeans.properties;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {

    private static final long serialVersionUID = -551188913709789300L;

    private Long id;

    private String name;

    private int age;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
