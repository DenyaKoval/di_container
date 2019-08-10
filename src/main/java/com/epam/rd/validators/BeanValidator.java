package com.epam.rd.validators;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

public class BeanValidator {

    public Class<?> validateClass(String className) {
        try {
           return Class.forName(className);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Class " + className + " not found", ex);
        }
    }

    public int amountParameters(Class<?> beanDeclared) {
        Constructor<?> constructor = beanDeclared.getDeclaredConstructors()[0];
        Parameter[] parameters = constructor.getParameters();
        return parameters.length;
    }
}
