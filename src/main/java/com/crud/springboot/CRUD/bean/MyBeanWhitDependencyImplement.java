package com.crud.springboot.CRUD.bean;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

public class MyBeanWhitDependencyImplement implements MyBeanWhitDependency {

    Log LOGGER = LogFactory.getLog(MyBeanWhitDependencyImplement.class);
    private MyOperation myOperation;

    public MyBeanWhitDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWhiteDependency() {
        LOGGER.info("MyBeanWhitDependencyImplement.printWhiteDependency()");
        int number = 2;
        LOGGER.debug("number: " + number);
        System.out.println(myOperation.sum(number));
        System.out.println("Hola Desde MyBeanWhitDependencyImplement");
    }
}
