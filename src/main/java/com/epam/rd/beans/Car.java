package com.epam.rd.beans;

public class Car {

    public final String manufacturer;
    public final Engine engine;
    public final int year = 2015;

    public Car(String manufacturer, Engine engine) {
        this.manufacturer = manufacturer;
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "Car{" +
                "manufacturer=" + manufacturer +
                ", engine=" + engine +
                '}';
    }
}
