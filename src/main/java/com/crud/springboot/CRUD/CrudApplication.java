package com.crud.springboot.CRUD;

import com.crud.springboot.CRUD.bean.MyBean;
import com.crud.springboot.CRUD.bean.MyBeanWhitDependency;
import com.crud.springboot.CRUD.bean.MyBeanWithProperties;
import com.crud.springboot.CRUD.component.ComponentDependency;
import com.crud.springboot.CRUD.entity.User;
import com.crud.springboot.CRUD.pojo.UserPojo;
import com.crud.springboot.CRUD.repository.UserRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class CrudApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(CrudApplication.class);
	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWhitDependency myBeanWhitDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;

	public CrudApplication( @Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWhitDependency myBeanWhitDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo, UserRepository userRepository) {
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWhitDependency = myBeanWhitDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
	}
	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

	@Override
	public void run(String... args) {
		//ejemplosAnteriores();
		saveUserInDataBase();
		getInformationJpqlFromUser();
	}

	private void getInformationJpqlFromUser(){
		LOGGER.info("User found with email: " +
				userRepository.findByUserEmail("juan@mail.com")
						.orElseThrow(()-> new RuntimeException("User not found")));

		userRepository.findAndSort("User", Sort.by("id").ascending())
				.stream()
				.forEach(user -> LOGGER.info("User Sort By Id: " + user));
	}

	private void saveUserInDataBase() {
		User user1 = new User("UserJhon", "jhon@mail.com", LocalDate.of(1995, 01, 20));
		User user2 = new User("UserJuan", "juan@mail.com", LocalDate.of(1995, 02, 02));
		User user3 = new User("UserPedro", "pedro@mail.com", LocalDate.of(1995, 03, 25));
		User user4 = new User("UserMaria", "maria@mail.com", LocalDate.of(1995, 04, 18));
		User user5 = new User("UAna", "ana@mail.com", LocalDate.of(1995, 05, 12));
		User user6 = new User("UserWendy", "wendy@mail.com", LocalDate.of(1995, 06, 05));
		User user7 = new User("UserJose", "jose@mail.com", LocalDate.of(1995, 07, 29));
		User user8 = new User("UAndrea", "andrea@mail.com", LocalDate.of(1995, 8, 06));
		User user9 = new User("UserDaniel", "daniel@mail.com", LocalDate.of(1995, 9, 10));
		User user10 = new User("UserHector", "hector@mail.com", LocalDate.of(1995, 10, 31));
		List<User> list = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10);
		list.stream().forEach(userRepository::save);
	}

	private void ejemplosAnteriores() {
		componentDependency.saludar();
		myBean.print();
		myBeanWhitDependency.printWhiteDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail() + "-" + userPojo.getPassword());
		try {
			//throw new Exception("Error");
			int value = 10/0;
			LOGGER.debug("Value: " + value);
		} catch (Exception e) {
			LOGGER.error("Error: " + e.getMessage());
		}
	}
}
