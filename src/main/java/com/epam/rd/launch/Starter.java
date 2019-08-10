package com.epam.rd.launch;

import com.epam.rd.beans.Window;
import com.epam.rd.define_bean.BeanDefinition;
import com.epam.rd.beans.Building;
import com.epam.rd.beans.Person;
import com.epam.rd.context_loaders.ContextLoader;
import com.epam.rd.context_loaders.IniLoader;
import com.epam.rd.di_container.DiContainer;
import com.epam.rd.sorting.SortingStrategy;
import com.epam.rd.sorting.TopologySorting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.epam.rd.beans.Car;

import java.util.List;
import java.util.Queue;

public class Starter {
    private static Logger logger = LoggerFactory.getLogger(Starter.class);
    public static void main(String[] args) throws Exception {

        ContextLoader contextLoader = new IniLoader("src/main/resources/context.ini");
        List<BeanDefinition> beanDefinitions = contextLoader.beanDefinitions();

        SortingStrategy sortingStrategy = new TopologySorting(beanDefinitions);
        Queue<BeanDefinition> sortedBeanDefinition =  sortingStrategy.sort();

        DiContainer context = new DiContainer(sortedBeanDefinition);

//        Person person = (Person) context.getInstance("person");
//        logger.info("{}", person);

//        Building building = (Building) context.getInstance("building");
//        logger.info("{}", building);

        Window window = context.getInstance("window", Window.class);
        logger.info("{}", window);

//        Car car = context.getInstance("car", Car.class);
//        logger.info("{}", car);
    }
}
