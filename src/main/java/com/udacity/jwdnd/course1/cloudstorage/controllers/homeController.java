package com.udacity.jwdnd.course1.cloudstorage.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udacity.jwdnd.course1.cloudstorage.entities.Note;
import com.udacity.jwdnd.course1.cloudstorage.entities.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
@RequestMapping("/home")
public class homeController {
    @Autowired
    NotesService notesService;
    @Autowired
    UserService userService;


    @GetMapping()
    public String home(Model model,Authentication authentication) {
        User loggeduser  = userService.getUser(authentication.getName());
        List<Note> notes = notesService.getNotesByUserId(loggeduser.getUserid());
        System.out.println("home>>>>>>" + notes.size());
        model.addAttribute("notes", notesService.getNotesByUserId(loggeduser.getUserid()));
        return "home";
    }
}
