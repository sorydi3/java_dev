package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        System.out.println("add note>>>>>>" + note.getNoteTitle()+" "+note.getNoteDescription()+" "+authentication.getName()+" "+loggeduser.getUserid()+" NOTE ID>> "+note.getNoteId());
        note.setUserId(loggeduser.getUserid());
        int result=0;
        if(note.getNoteId() == null) {
            result = notesService.addNote(note);
        }else {
            result = notesService.updateNoteById(note);
        }

        if(result > 0 ) {
            model.addAttribute("result", true);
        }else {
            model.addAttribute("result", false);
        }
        
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
