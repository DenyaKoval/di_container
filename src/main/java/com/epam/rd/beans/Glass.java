package com.epam.rd.beans;

public class Glass {
    String name;

    public Glass(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Glass{" +
                "name='" + name + '\'' +
                '}';
    }
}
