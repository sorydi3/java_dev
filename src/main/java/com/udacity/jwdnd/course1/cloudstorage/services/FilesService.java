package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.udacity.jwdnd.course1.cloudstorage.entities.File;
import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;

@Service
public class FilesService {
    @Autowired
    FileMapper fileMapper;

    public FilesService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }


    /**
     * @param file
     * @return user id  if file is added successfully
     */
    public int addFile(File file) {
       
        return fileMapper.addFile(file);
    }


    /**
     * @param fileId
     * @return file if file is found
     */
    public File getFile(Integer fileId) {
        return fileMapper.getFile(fileId);
    }


    /**
     * @param fileId
     * @return user id if file is deleted successfully
     */
    public int deleteFile(Integer fileId) {
        return fileMapper.deleteFile(fileId);
    }


    public File getFileByName(String filename) {
        return fileMapper.getFileByName(filename);
    }

    public List<File> getAllFiles(Integer userid) {
        return fileMapper.getAllFiles(userid);
    }


}
