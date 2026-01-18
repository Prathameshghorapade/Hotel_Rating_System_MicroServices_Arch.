package com.userservice.controllers;

import com.userservice.entities.User;
import com.userservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

   private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User>createUser(@RequestBody User user){
        User savedUser=userService.saveUser(user);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<User>>getAllUsers(){

       List<User>users =userService.getAllUsers();

       return new ResponseEntity<>(users,HttpStatus.FOUND);

    }

    @GetMapping("{id}")
    public ResponseEntity<User>getUserById(@PathVariable String id){
       User userFound =userService.getUser(id);

       return new ResponseEntity<>(userFound,HttpStatus.FOUND);

    }

    @PutMapping("{id}")
    public ResponseEntity<User>updateUSer(@PathVariable String id,@RequestBody User user){

       User updatedUSer =userService.updateUSer(id,user);
       if (updatedUSer==null)
       {return new ResponseEntity<>(HttpStatus.NOT_FOUND);}

       return new ResponseEntity<>(updatedUSer,HttpStatus.OK);
    }

    @DeleteMapping("{id}")

    public ResponseEntity<User>deleteUser(@PathVariable String id){

      userService.deleteUser(id);

      return new ResponseEntity<>(HttpStatus.OK);


    }



}
