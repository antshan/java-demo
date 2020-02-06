package com.java.demo.bytecode.bean;

import java.io.Serializable;
import java.util.Date;

public class Person implements Nameable, Serializable {

    private static final long serialVersionUID = 7962478081612005672L;

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

    @Override
    public void setName(String name) throws Exception {

    }
}
