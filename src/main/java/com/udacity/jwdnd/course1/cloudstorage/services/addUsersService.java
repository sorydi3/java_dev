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

       User user1 = new User(null, "ibradi3", null, "1234", "ibrahima", "diallo");
       User user2 = new User(null, "sorydi3", null, "1234", "ibrahima", "diallo");
        userService.addUser(user1);
        userService.addUser(user2);
    }



    
    
}
