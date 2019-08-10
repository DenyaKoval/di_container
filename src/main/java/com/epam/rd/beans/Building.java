package com.epam.rd.beans;

public class Building {
    String name;
    Window window;

    public Building(String name, Window window) {
        this.name = name;
        this.window = window;
    }

    @Override
    public String toString() {
        return "Building{" +
                "name='" + name + '\'' +
                ", window=" + window +
                '}';
    }
}
