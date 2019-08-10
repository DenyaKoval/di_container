package com.epam.rd.sorting;

import com.epam.rd.define_bean.BeanDefinition;

import java.util.Queue;

public interface SortingStrategy {
    Queue<BeanDefinition> sort();
}
