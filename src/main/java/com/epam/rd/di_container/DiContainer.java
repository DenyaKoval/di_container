package com.epam.rd.di_container;

import com.epam.rd.define_bean.BeanDefinition;
import com.epam.rd.define_bean.ParameterType;
import com.epam.rd.exception.ContainerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.*;

public class DiContainer {
    private Logger logger = LoggerFactory.getLogger(DiContainer.class);
    private Queue<BeanDefinition> beanDefinitions;
    public Map<String, Object> beans;

    public DiContainer(Queue<BeanDefinition> beanDefinitions) {
        this.beanDefinitions = beanDefinitions;
        beans = new HashMap<>();
        putToMap();
    }

    public void putToMap() {
        while (beanDefinitions.size() > 0) {
            BeanDefinition beanDefinition = beanDefinitions.poll();
            Object bean = createBean(beanDefinition);
            beans.put(beanDefinition.id, bean);
        }
    }

    public Object createBean(BeanDefinition beanDefinition) {
        List<Object> beanConstructorParameters = new ArrayList<>();
        Constructor<?> constructor = beanDefinition.beanClass.getDeclaredConstructors()[0];
        for (Parameter parameter : constructor.getParameters()) {

            String paramName = parameter.getName();
            Class<?> paramType = parameter.getType();

            com.epam.rd.define_bean.Parameter beanParameter = beanDefinition.parameters.get(paramName);
            addParam(beanParameter, beanConstructorParameters, paramType);
        }

        try {
            Object object = constructor.newInstance(beanConstructorParameters.toArray());
            logger.info("Created bean {}", object);
            return object;
        } catch (Exception ex) {
            throw new ContainerException(ex);
        }
    }

    public void addParam(com.epam.rd.define_bean.Parameter beanDefinitionParameter, List<Object> beanConstructorParameters, Class<?> paramType) {
        if (beanDefinitionParameter.type == ParameterType.VAL) {
            beanConstructorParameters.add(beanDefinitionParameter.asClass(paramType));
        } else if (beanDefinitionParameter.type == ParameterType.REF) {
            Object object = beans.get(beanDefinitionParameter.value);
            beanConstructorParameters.add(object);
        }
    }

    public Object getInstance(String id) {
        logger.info("Get bean by id: {}", id);
        return beans.get(id);
    }

    public <T> T getInstance(String id, Class<T> asClass) {
        logger.info("Get bean by id: {}", id);
        return asClass.cast(beans.get(id));
    }
}
