package com.epam.rd.sorting;

import com.epam.rd.define_bean.BeanDefinition;
import com.epam.rd.define_bean.Parameter;
import com.epam.rd.define_bean.ParameterType;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class TopologySorting implements SortingStrategy {

    private List<BeanDefinition> beanDefinitions;

    public TopologySorting(List<BeanDefinition> beanDefinitions) {
        this.beanDefinitions = beanDefinitions;
    }

    @Override
    public Queue<BeanDefinition> sort() {

        Queue<BeanDefinition> sortedBeanDefinitions = new LinkedList<>();
        BeanDefinition beanDef;

        while(beanDefinitions.size() > 0) {
            for (BeanDefinition beanDefinition : beanDefinitions) {

                boolean referenceExists = false;
                boolean allValues = true;

                beanDef = beanDefinition;

                for(Map.Entry<String, Parameter> parameter : beanDefinition.parameters.entrySet()) {

                    Parameter param = parameter.getValue();

//                    if (param.type == ParameterType.VAL) {
//                        allValues = true;
//                        continue;
//
//                    } else
                        if (param.type == ParameterType.REF) {

                        String reference = param.value;
                        allValues = false;
                        referenceExists = referenceExists(sortedBeanDefinitions, reference);
                    }

                    if (!referenceExists) {
                        break;
                    }
                }

             //   if (referenceExists || allValues) {
                if (referenceExists) {
                    sortedBeanDefinitions.add(beanDef);
                    beanDefinitions.remove(beanDef);
                    break;
                }
            }
        }
        return sortedBeanDefinitions;
    }

    public boolean referenceExists(Queue<BeanDefinition> sortedBeanDefinitions, String reference) {
        boolean referenceExists = false;
        for (BeanDefinition sortedBeanDefinition : sortedBeanDefinitions) {
            if (reference.equals(sortedBeanDefinition.id)) {
                referenceExists = true;
                break;
            } else {
                referenceExists = false;
            }
        }
        return referenceExists;
    }

}
