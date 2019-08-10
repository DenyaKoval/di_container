package com.epam.rd.beans;

public class Engine {

    public final String type;
    public final String model;

    public Engine(String type, String model)
    {
        this.type = type;
        this.model = model;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "type='" + type + '\'' +
                ", model='" + model + '\'' +
                '}';
    }

}
