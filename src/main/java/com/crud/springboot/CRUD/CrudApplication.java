package com.crud.springboot.CRUD;

import com.crud.springboot.CRUD.bean.MyBean;
import com.crud.springboot.CRUD.bean.MyBeanWhitDependency;
import com.crud.springboot.CRUD.bean.MyBeanWithProperties;
import com.crud.springboot.CRUD.component.ComponentDependency;
import com.crud.springboot.CRUD.entity.User;
import com.crud.springboot.CRUD.pojo.UserPojo;
import com.crud.springboot.CRUD.repository.UserRepository;
import com.crud.springboot.CRUD.service.UserService;
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
	private UserService userService;

	public CrudApplication( @Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWhitDependency myBeanWhitDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo, UserRepository userRepository, UserService userService) {
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWhitDependency = myBeanWhitDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}
	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

	@Override
	public void run(String... args) {
		//ejemplosAnteriores();
		saveUserInDataBase();
		saveWithErrorTransactional();
		LOGGER.info("User saved in database");
		getInformationJpqlFromUser();
	}

	private void saveWithErrorTransactional(){
		User test1 = new User("test1", "test1@mail.com", LocalDate.now());
		User test2 = new User("test2", "test2@mail.com", LocalDate.now());
		User test3 = new User("test3", "test1@mail.com", LocalDate.now());
		User test4 = new User("test4", "test4@mail.com", LocalDate.now());

		List<User> users = Arrays.asList(test1, test2, test3, test4);

		try {
			userService.saveTransactional(users);
		} catch (Exception e) {
			LOGGER.error("Error saving users in database " +e);
		}

		userService.getAllUsers().stream()
				.forEach(user ->
						LOGGER.info( "User transaction: " + user));
	}

	private void getInformationJpqlFromUser(){
		LOGGER.info("User found with email: " +
				userRepository.findByUserEmail("juan@mail.com")
						.orElseThrow(()-> new RuntimeException("User not found")));

		userRepository.findAndSort("User", Sort.by("id").ascending())
				.stream()
				.forEach(user -> LOGGER.info("User Sort By Id: " + user));

		userRepository.findByName("UserJuan")
				.stream()
				.forEach(user -> LOGGER.info("User found with name: " + user));

		LOGGER.info("User find with Email and Name " +
				userRepository.findByEmailAndName("pedro@mail.com", "UserPedro")
						.orElseThrow(() -> new RuntimeException("User not found")));

		userRepository.findByNameLike("%User%")
				.stream()
				.forEach(user -> LOGGER.info("User found with name like: " + user));

		userRepository.findByNameOrEmail("UAna" , null)
				.stream()
				.forEach(user -> LOGGER.info("User found with name or email: " + user));

		userRepository.findByBirthdateBetween(LocalDate.of(1995, 7, 1), LocalDate.of(1995, 12, 30))
				.stream()
				.forEach(user -> LOGGER.info("User found with birth date between: " + user));

		userRepository.findByNameContainingOrderByIdDesc("User")
				.stream()
				.forEach(user -> LOGGER.info("User found with name like order by id desc: " + user));

		LOGGER.info( "the user with BirthDate and email is: " +  userRepository.getAllByBirthdateAndEmail(LocalDate.of(1995, 05, 12),
						"ana@mail.com")
				.orElseThrow(() ->
						new RuntimeException("User not found between birth date and email")));

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
