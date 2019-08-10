package com.epam.rd.validators;

import org.ini4j.Ini;
import org.ini4j.Profile;

import java.util.Set;

public class IniContextValidator {
    private BeanValidator beanValidator;

    public IniContextValidator(BeanValidator beanValidator) {
        this.beanValidator = beanValidator;
    }

    public void validate(Ini ini) {
        for (String sectionName : ini.keySet()) {
            int amountParameters = validateClass(sectionName);
            Profile.Section parametersSection = ini.get(sectionName);
            Set<String> parametersName = parametersSection.keySet();

            checkAmountParameters(amountParameters, parametersName.size());
            validateParameters(parametersName, parametersSection);
        }
    }

    private void validateParameters(Set<String> parameters, Profile.Section parametersSection) {
        for (String parameterName : parameters) {
            checkParameterName(parameterName);
            String paramValue = parametersSection.get(parameterName);
            validateParameterValue(paramValue);
        }
    }

    private void validateParameterValue(String parameterVal) {
        String[] paramTypeValue = parameterVal.split(":");
        checkParameterTypeValue(paramTypeValue);

        String parameterType = paramTypeValue[0];
        String parameterValue = paramTypeValue[1];

        checkParameterType(parameterType);
        checkParameterValue(parameterValue);
    }

    private int validateClass(String section) {
        String[] classAndId = section.split(":");
        checkClassAndId(classAndId);
        String className = classAndId[0];
        String id = classAndId[1];

        Class<?> beanClass = beanValidator.validateClass(className);
        int amountParameters = beanValidator.amountParameters(beanClass);

        return amountParameters;
    }

    private void checkAmountParameters(int parametersConstructor, int beanParameters) {
        if (parametersConstructor != beanParameters) {
            throw new IllegalArgumentException("Incorrect amount parameters");
        }
    }

    private void checkClassAndId(String[] classAndId) {
        if (classAndId.length != 2) {
            throw new IllegalArgumentException("Incorrect class/id declaration");
        }
    }

    private void checkParameterValue(String parameterValue) {
        if (parameterValue.length() < 1) {
            throw new IllegalArgumentException("Incorrect parameter value");
        }
    }

    private void checkParameterType(String parameterType) {
        if (!parameterType.equals("ref")) {
            if (!parameterType.equals("val")) {
                throw new IllegalArgumentException("Incorrect type parameter modifier");
            }
        }
    }

    private void checkParameterTypeValue(String[] paramTypeValue) {
        if (paramTypeValue.length != 2) {
            throw new IllegalArgumentException("Incorrect declaration parameter type/value");
        }
    }

    private void checkParameterName(String parameterName) {
        if (parameterName.length() < 1) {
            throw new IllegalArgumentException("Incorrect parameter name");
        }
    }

}
