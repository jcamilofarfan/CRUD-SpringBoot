package com.crud.springboot.CRUD.configuration;

import com.crud.springboot.CRUD.caseuse.GetUser;
import com.crud.springboot.CRUD.caseuse.GetUserImplement;
import com.crud.springboot.CRUD.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseUseConfiguration {
    @Bean
    GetUser getUser(UserService userService) {
        return new GetUserImplement(userService);
    }
}
