package com.crud.springboot.CRUD;

import com.crud.springboot.CRUD.component.ComponentDependency;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudApplication implements CommandLineRunner {
	private ComponentDependency componentDependency;

	public CrudApplication(ComponentDependency componentDependency) {
		this.componentDependency = componentDependency;
	}
	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

	@Override
	public void run(String... args) {
		componentDependency.saludar();
	}
}
