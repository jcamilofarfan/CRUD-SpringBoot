package com.crud.springboot.CRUD.caseuse;

import com.crud.springboot.CRUD.entity.User;
import com.crud.springboot.CRUD.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UpdateUser {
    private UserService userService;

    public UpdateUser(UserService userService) {
        this.userService = userService;
    }

    public User update(User newUser, Long id) {
        return userService.update(newUser, id);
    }
}
