package com.epam.rd.beans;

public class Person {
    public final String name;
    public final int age;
    public final float money;
    public final char nameClass;
    public final boolean married;
    public final Friend friend;

    public Person(String name, int age, float money, char nameClass, boolean married, Friend friend) {
        this.name = name;
        this.age = age;
        this.money = money;
        this.nameClass = nameClass;
        this.married = married;
        this.friend = friend;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", money=" + money +
                ", nameClass=" + nameClass +
                ", married=" + married +
                ", friend=" + friend +
                '}';
    }
}
