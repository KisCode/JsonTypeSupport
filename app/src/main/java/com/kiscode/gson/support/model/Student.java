package com.kiscode.gson.support.model;

import java.io.Serializable;

/**
 * Description: User Model
 * Author: KrisKeno
 * Date : 2020/8/5 9:13 PM
 **/
public class Student implements Serializable {

    /**
     * name : keno
     * age : 29
     * email : keno@gmail.com
     */

    private String name;
    private int age;
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}

