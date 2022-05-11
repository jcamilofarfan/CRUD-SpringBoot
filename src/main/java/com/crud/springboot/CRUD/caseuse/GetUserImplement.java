package com.crud.springboot.CRUD.caseuse;

import com.crud.springboot.CRUD.entity.User;
import com.crud.springboot.CRUD.service.UserService;

import java.util.List;

public class GetUserImplement implements GetUser {
    private UserService userService;

    public GetUserImplement(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getAll(){
        return userService.getAllUsers();
    }
}
