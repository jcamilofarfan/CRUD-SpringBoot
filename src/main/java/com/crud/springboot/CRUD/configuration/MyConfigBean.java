package com.crud.springboot.CRUD.configuration;

import com.crud.springboot.CRUD.bean.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfigBean {
    @Bean
    public MyBean beanOperation(){
        return new MyBeanTwoImplement();
    }

    @Bean
    public MyOperation beanOperationOperation(){
        return new MyOperationImplement();
    }

    @Bean
    public MyBeanWhitDependency beanOperationOperationImplement(MyOperation myOperation){
        return new MyBeanWhitDependencyImplement(myOperation);
    }
}
