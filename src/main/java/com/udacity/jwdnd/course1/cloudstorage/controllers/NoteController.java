package com.udacity.jwdnd.course1.cloudstorage.controllers;

import java.security.Principal;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.ObjectIdGenerators.StringIdGenerator;
import com.udacity.jwdnd.course1.cloudstorage.entities.Note;
import com.udacity.jwdnd.course1.cloudstorage.entities.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import org.springframework.security.core.Authentication;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
@RequestMapping("/note")
public class NoteController {
    @Autowired
    NotesService notesService;

    @Autowired
    UserService userService;
    
    @PostMapping("/add")
    public String addNote(@ModelAttribute Note note, Model model,Authentication authentication) {
        User loggeduser  = userService.getUser(authentication.getName());
        note.setUserId(loggeduser.getUserid());
        System.out.println("add note>>>>>>" + note.getNoteTitle()+" "+note.getNoteDescription()+" "+authentication.getName()+" "+loggeduser.getUserid());
        notesService.addNote(note);
        model.addAttribute("result", true);
        notesService.displayNotes();
        return "result"; 
    }

    @PostMapping("/update")
    public String updateNote(@ModelAttribute Note note, Model model) {
        return "result";
    }

    @GetMapping("/delete/{noteId}")
    public String deleteNote(@ModelAttribute Note note, Model model,@PathVariable Integer noteId) {
        System.out.println("delete note>>>>>>" + noteId);
        int result =  notesService.deleteNoteById(noteId);
        if(result >= 1){
            model.addAttribute("result", true);
        }else{
            model.addAttribute("result", false);
        }
        return "result";
    }

}
