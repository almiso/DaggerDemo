package org.almiso.daggerdemo.model;


public class Customer {

    private String name;
    private int age;

    public Customer() {
        this.name = "Alexander";
        this.age = 24;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer: " +
                "name='" + name + '\'' +
                ", age=" + age;
    }
}
