package com.epam.rd.beans;

public class Friend {
    public String name;
    public long age;

    public Friend(String name, long age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
