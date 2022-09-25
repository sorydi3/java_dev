package com.udacity.jwdnd.course1.cloudstorage.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.entities.User;

@Service
public class addUsersService {
    @Autowired
    UserService userService;



   @PostConstruct()
    public void addUsers() {
        User user = new User();
        user.setFirstname("John");
        user.setFirstname("Doe");
        user.setUsername("ibradi3");
        user.setPassword("1234");
        //userService.addUser(user);
    }



    
    
}
