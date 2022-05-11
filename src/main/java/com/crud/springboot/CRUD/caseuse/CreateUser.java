package com.crud.springboot.CRUD.caseuse;

import com.crud.springboot.CRUD.entity.User;
import com.crud.springboot.CRUD.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class CreateUser {
    private UserService userService;

    public CreateUser(UserService userService) {
        this.userService = userService;
    }

    public User save(User newUser) {
        return userService.save(newUser);
    }
}
