package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udacity.jwdnd.course1.cloudstorage.entities.File;

@Controller
@RequestMapping("/file")
public class FileController {
    
    @PostMapping("/add")
    public String addFile(@ModelAttribute File file, Model model) {
        return "result";
    }

    @PostMapping("/delete")
    public String deleteFile() {
        return "result";
    }

    
}
