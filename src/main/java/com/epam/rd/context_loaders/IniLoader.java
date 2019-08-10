package com.epam.rd.context_loaders;

import com.epam.rd.define_bean.BeanDefinition;
import com.epam.rd.define_bean.Parameter;
import com.epam.rd.define_bean.ParameterType;
import com.epam.rd.validators.BeanValidator;
import com.epam.rd.validators.IniContextValidator;
import org.ini4j.Ini;
import org.ini4j.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class IniLoader implements ContextLoader {
    private Logger logger = LoggerFactory.getLogger(IniLoader.class);
    private Ini ini;
    private IniContextValidator contextValidator;

    public IniLoader(String fileName) {
        try {
            ini = new Ini(new FileReader(fileName));
            logger.info("Load ini file: {}", fileName);
        } catch (IOException ex) {
            throw new RuntimeException("Can't read file: " + fileName,  ex);
        }
        contextValidator = new IniContextValidator(new BeanValidator());
        contextValidator.validate(ini);
    }

    public List<BeanDefinition> beanDefinitions() {
        List<BeanDefinition> beanDefinitions = new ArrayList<>();
        Map<String, Parameter> parameters;

        for (String sectionName : ini.keySet()) {
            Class<?> beanClass = getBeanClass(sectionName);
            String beanId = getId(sectionName);

            Profile.Section section = ini.get(sectionName);
            parameters = new HashMap<>();

            for (String parameterName : section.keySet()) {
                String paramValue = section.get(parameterName);
                Parameter parameter = getParameter(paramValue);

                parameters.put(parameterName, parameter);
            }
            beanDefinitions.add(new BeanDefinition(beanId,  beanClass,  parameters));
        }
        logger.info("Get bean definitions {}", beanDefinitions);
        return beanDefinitions;
    }

    public Class<?> getBeanClass(String str) {
        String[] classAndId = str.split(":");
        String className = Objects.requireNonNull(classAndId[0], "Class name is empty");
        Class<?> beanClass = null;
        try {
            beanClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Bean class not found", e);
        }
        logger.info("Get bean class {} from string {}", beanClass.getName(), str);
        return beanClass;
    }

    public String getId(String str) {
        String[] classAndId = str.split(":");
        String id = Objects.requireNonNull(classAndId[1], "Id is empty");
        logger.info("Get bean id {} from string {}", id, str);
        return id;
    }

    public Parameter getParameter(String typeValue) {
        String[] typeAndValue = typeValue.split(":");
        String typeParam = typeAndValue[0];
        String value = typeAndValue[1];
        ParameterType parameterType = null;

        if (typeParam.equals("val")) {
            parameterType = ParameterType.VAL;
        } else if (typeParam.equals("ref")) {
            parameterType = ParameterType.REF;
        }

        Parameter parameter = new Parameter(parameterType, value);
        logger.info("Get parameter {} from string {}", parameter, typeAndValue);
        return parameter;
    }

}
