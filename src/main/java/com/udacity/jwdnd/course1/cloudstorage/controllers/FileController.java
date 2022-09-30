package com.udacity.jwdnd.course1.cloudstorage.controllers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

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
    public String addFile(@RequestParam("fileUpload") MultipartFile fileUpload, Model model, Authentication authentication) {

        User loggeduser = userService.getUser(authentication.getName());
        File newFile = new File();

        String fileName = StringUtils.cleanPath(fileUpload.getOriginalFilename());

        File fileaux = filesService.getFileByName(fileName);
        int result = -1;
        if (fileaux != null) {
            model.addAttribute("result", false);
            model.addAttribute("message", "File already exists");
            return "result";    
        } else {
            try {
                newFile.setFilename(StringUtils.cleanPath(fileName));
                newFile.setContentType(fileUpload.getContentType());
                System.out.println("CONTENT TYPE OF THE FILE>>>>"+fileUpload.getContentType());
                newFile.setFileSize(String.valueOf(fileUpload.getSize()));
                newFile.setUserId(loggeduser.getUserid());
                newFile.setFileData(fileUpload.getBytes());
                result = filesService.addFile(newFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (result < 0) {
            model.addAttribute("result", false);
        } else {
            model.addAttribute("result", true);
        }

        return "result";
    }

    @RequestMapping("/delete/{id}")
    public String deleteFile(Model model, @PathVariable Integer id) {
        int result = filesService.deleteFile(id);
        if (result < 0) {
            model.addAttribute("result", false);
        } else {
            model.addAttribute("result", true);
        }
        return "result";
    }



    @RequestMapping("/download/{id}")
    public ResponseEntity<InputStreamResource> downloadFile(Model model, @PathVariable Integer id) {
        File result = filesService.getFile(id);
        InputStream in = new ByteArrayInputStream(result.getFileData());
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + result.getFilename() + "\"")
                .contentType(org.springframework.http.MediaType.parseMediaType(result.getContentType()))
                .body(new InputStreamResource(in));
    }

}
