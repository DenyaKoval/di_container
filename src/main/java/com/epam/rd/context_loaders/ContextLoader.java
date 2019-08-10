package com.epam.rd.context_loaders;

import com.epam.rd.define_bean.BeanDefinition;

import java.util.List;

public interface ContextLoader {
    List<BeanDefinition> beanDefinitions();
}
