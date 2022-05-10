package com.crud.springboot.CRUD.bean;

public class MyBeanWhitDependencyImplement implements MyBeanWhitDependency {
    private MyOperation myOperation;

    public MyBeanWhitDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWhiteDependency() {
        int number = 2;
        System.out.println(myOperation.sum(number));
        System.out.println("Hola Desde MyBeanWhitDependencyImplement");
    }
}
