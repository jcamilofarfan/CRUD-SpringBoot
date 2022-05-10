package com.crud.springboot.CRUD;

import com.crud.springboot.CRUD.bean.MyBean;
import com.crud.springboot.CRUD.bean.MyBeanWhitDependency;
import com.crud.springboot.CRUD.component.ComponentDependency;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudApplication implements CommandLineRunner {
	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWhitDependency myBeanWhitDependency;

	public CrudApplication( @Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWhitDependency myBeanWhitDependency) {
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWhitDependency = myBeanWhitDependency;
	}
	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

	@Override
	public void run(String... args) {
		componentDependency.saludar();
		myBean.print();
		myBeanWhitDependency.printWhiteDependency();
	}
}
