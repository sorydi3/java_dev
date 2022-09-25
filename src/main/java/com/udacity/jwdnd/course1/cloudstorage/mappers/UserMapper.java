package com.udacity.jwdnd.course1.cloudstorage.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.udacity.jwdnd.course1.cloudstorage.entities.User;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User getUser(String username);

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES (#{username}, #{salt}, #{password}, #{firstname}, #{lastname})")
    @Options(useGeneratedKeys = true, keyProperty = "userid")
    int addUser(User user);

    @Delete("DELETE FROM USERS WHERE userid = #{username}")
    @Options(useGeneratedKeys = true, keyProperty = "userid")
    int deleteUser(String username);

    @Insert("UPDATE USERS SET username = #{username}, salt = #{salt}, password = #{password}, firstname = #{firstname}, lastname = #{lastname} WHERE userid = #{userid}")
    @Options(useGeneratedKeys = true, keyProperty = "userid")
    int updateUser(User user);

}
