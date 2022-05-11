package com.crud.springboot.CRUD.controller;

import com.crud.springboot.CRUD.caseuse.CreateUser;
import com.crud.springboot.CRUD.caseuse.DeleteUser;
import com.crud.springboot.CRUD.caseuse.GetUser;
import com.crud.springboot.CRUD.caseuse.UpdateUser;
import com.crud.springboot.CRUD.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    //create, get, delete, update
    private GetUser getUser;
    private CreateUser createUser;
    private DeleteUser deleteUser;
    private UpdateUser updateUser;

    public UserRestController(GetUser getUser, CreateUser createUser, DeleteUser deleteUser, UpdateUser updateUser) {
        this.getUser = getUser;
        this.createUser = createUser;
        this.deleteUser = deleteUser;
        this.updateUser = updateUser;

    }

    @GetMapping
    List<User> get(){
        return getUser.getAll();
    }

    @PostMapping
    ResponseEntity<User> newUse(@RequestBody User newUser){
        return new ResponseEntity<>(createUser.save(newUser), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity delete(@PathVariable Long id){
        deleteUser.remove(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user){
        return new ResponseEntity<>(updateUser.update(user, id), HttpStatus.OK);
    }
}
