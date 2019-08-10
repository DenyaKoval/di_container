package com.epam.rd.define_bean;

import java.util.Map;
import java.util.Objects;

public class BeanDefinition {

    public final String id;
    public final Class<?> beanClass;
    public final Map<String, Parameter> parameters;

    public BeanDefinition(String id, Class<?> beanClass, Map<String, Parameter> parameters) {
        this.id = Objects.requireNonNull(id, "id is null");
        this.beanClass = Objects.requireNonNull(beanClass);
        this.parameters = Objects.requireNonNull(parameters);
    }

    @Override
    public String toString() {
        return "\nBeanDefinition{" +
                "id='" + id + '\'' +
                ", clazz=" + beanClass +
                ", parameters=" + parameters +
                '}';
    }
}
