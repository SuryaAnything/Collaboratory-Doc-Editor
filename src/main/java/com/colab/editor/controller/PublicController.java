package com.colab.editor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colab.editor.content.User;
import com.colab.editor.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired 
    private UserService userService;


    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody User user) { 
        if (userService.saveUser(user)) {
            return new ResponseEntity<User>(HttpStatus.CREATED);
        }
        return new ResponseEntity<User>(HttpStatus.NOT_ACCEPTABLE);
    }
    
    
}