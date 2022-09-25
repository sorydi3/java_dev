package com.udacity.jwdnd.course1.cloudstorage.controllers;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udacity.jwdnd.course1.cloudstorage.entities.Credencial;
import com.udacity.jwdnd.course1.cloudstorage.entities.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredencialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
@RequestMapping("/signup")
public class signupController {
    
    @Autowired
    UserService userService;
    @Autowired
    CredencialService credentialService;

    @GetMapping()
    public String signup(User user) {
        return "signup";
    }

    @PostMapping()
    public String signupUser(@ModelAttribute User user, Model model) {
        String signupError = null;
        System.out.println(user);
        if (!userService.isUsernameAvailable(user.getUsername())) {
            signupError = "The username already exists.";
        }

        if (signupError == null) {
            String encodedSalt = getSalt();
            user.setSalt(encodedSalt);
            int rowsAdded = userService.addUser(user);

            if (rowsAdded < 0) {
                signupError = "There was an error signing you up. Please try again.";
            } else {
                user.setUserid(rowsAdded);
                credentialService.addCredencial(new Credencial(), encodedSalt, user);
            }
        }

        if (signupError == null) {
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("signupError", signupError);
        }

        return "signup";
    }

    private String getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
}
