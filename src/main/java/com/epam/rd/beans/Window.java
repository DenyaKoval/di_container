package com.epam.rd.beans;

public class Window {
    String name;
    Glass glass;

    public Window(String name, Glass glass) {
        this.name = name;
        this.glass = glass;
    }

    @Override
    public String toString() {
        return "Window{" +
                "name='" + name + '\'' +
                ", glass=" + glass +
                '}';
    }
}
