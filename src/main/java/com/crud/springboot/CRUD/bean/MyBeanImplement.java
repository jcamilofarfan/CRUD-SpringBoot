package com.crud.springboot.CRUD.bean;

public class MyBeanImplement implements MyBean {
    @Override
    public void print() {
        System.out.println("Hola desde MyBeanImplement");
    }
}
