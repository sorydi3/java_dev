package com.udacity.jwdnd.course1.cloudstorage.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.udacity.jwdnd.course1.cloudstorage.entities.File;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE fileId = #{filename}")
    File getFile(Integer filename);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES (#{filename}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int addFile(File file);


    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int deleteFile(Integer fileId);

    @Insert("UPDATE FILES SET filename = #{filename}, contenttype = #{contentType}, filesize = #{fileSize}, userid = #{userId}, filedata = #{fileData} WHERE fileid = #{fileId}")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int updateFile(File file);

    @Select("SELECT * FROM FILES WHERE filename = #{filename}")
    File getFileByName(String filename);

    @Select("SELECT * FROM FILES WHERE userid = #{userid}")
    List<File> getAllFiles(Integer userid);
}
