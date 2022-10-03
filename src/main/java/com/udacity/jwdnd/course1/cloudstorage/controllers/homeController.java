package com.udacity.jwdnd.course1.cloudstorage.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udacity.jwdnd.course1.cloudstorage.entities.Credencial;
import com.udacity.jwdnd.course1.cloudstorage.entities.File;
import com.udacity.jwdnd.course1.cloudstorage.entities.Note;
import com.udacity.jwdnd.course1.cloudstorage.entities.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredencialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
@RequestMapping("/home")
public class homeController {
    @Autowired
    NotesService notesService;
    @Autowired
    UserService userService;

    @Autowired
    CredencialService credencialService;

    @Autowired
    FilesService filesService;

    @GetMapping()
    public String home(Model model, Authentication authentication) {
        User loggeduser = userService.getUser(authentication.getName());
        List<Note> notes = notesService.getNotesByUserId(loggeduser.getUserid());
        model.addAttribute("notes", notesService.getNotesByUserId(loggeduser.getUserid()));
        List<Credencial> creds = credencialService.getAllCredencials(loggeduser.getUserid());
        model.addAttribute("credentials", creds);
        model.addAttribute("passwords", decrypPasswords(creds));
        List<File> files = filesService.getAllFiles(loggeduser.getUserid());
        model.addAttribute("files", files);

        return "home";
    }

    private List<String> decrypPasswords(List<Credencial> creds) {
        List<String> creds2 = new ArrayList<>();
        for (Credencial credencial : creds) {
            creds2.add(credencialService.decrypPassword(credencial.getPassword(), credencial.getKey()));
        }
        return creds2;
    }
}
