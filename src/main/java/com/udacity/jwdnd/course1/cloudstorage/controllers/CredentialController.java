package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.security.core.Authentication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udacity.jwdnd.course1.cloudstorage.entities.Credencial;
import com.udacity.jwdnd.course1.cloudstorage.entities.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredencialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
@RequestMapping("/credential")
public class CredentialController {
    @Autowired
    CredencialService credencialService;

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public String updateCredential(Credencial credential, Model model, Authentication authentication) {
        User loggeduser = userService.getUser(authentication.getName());
        int result = 0;
        credential.setUserid(loggeduser.getUserid());
        if (credential.getCredentialid() != null) {
            result = credencialService.updateCredencial(credential);
        } else {
            result = credencialService.addCredencial(credential);
        }

        if (result < 0) {
            model.addAttribute("result", false);
        } else {
            model.addAttribute("result", true);
        }

        return "result";
    }


    @GetMapping("/delete/{id}")
    public String deleteCredential(@PathVariable Integer id, Model model,Authentication authentication) {
        User loggeduser = userService.getUser(authentication.getName());
        List<Credencial> creds = credencialService.getAllCredencials(loggeduser.getUserid());
        System.out.println("CREDENTIALS>>>>>>> BEFORE"+creds.size());
        credencialService.displayCredentials();
        int result = credencialService.deleteCredencial(id);
        System.out.println("CREDENTIALS>>>>>>> AFTER"+creds.size()+">>>>>"+result);
        credencialService.displayCredentials();
        if (result < 0) {
            model.addAttribute("result", false);
        } else {
            model.addAttribute("result", true);
        }
        return "result";
    }
}
