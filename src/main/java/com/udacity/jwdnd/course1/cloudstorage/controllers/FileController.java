package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.apache.ibatis.logging.LogException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ser.std.FileSerializer;
import com.udacity.jwdnd.course1.cloudstorage.entities.File;
import com.udacity.jwdnd.course1.cloudstorage.entities.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
@RequestMapping("/file")
public class FileController {
    @Autowired
    FilesService filesService;

    @Autowired
    UserService userService;


    @PostMapping("/add")
    public String addFile(@ModelAttribute File file, Model model,Authentication authentication) {
        
        return "result";
    }

    @RequestMapping("/delete/{id}")
    public String deleteFile(Model model,@PathVariable Integer id) {


        return "result";
    }

    
}
