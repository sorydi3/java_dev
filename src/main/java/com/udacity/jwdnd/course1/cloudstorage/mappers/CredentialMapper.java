package com.udacity.jwdnd.course1.cloudstorage.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.entities.Credencial;

@Mapper
public interface CredentialMapper {


    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    Credencial getCredential(Integer credentialId);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES (#{url}, #{username}, #{key}, #{password}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    int addCredential(Credencial credentialId);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int deleteCredential(Integer credentialId);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, key = #{key}, password = #{password} WHERE credentialid = #{credentialid}")
    int updateCredential(Credencial credential);

    @Select("SELECT * FROM CREDENTIALS WHERE username = #{username}")
    Credencial getCredentialByUsername(String username);

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    List<Credencial> getAllCredentials(Integer userId);

    @Select("SELECT * FROM CREDENTIALS")
    List<Credencial> getAlCredentials();


    
}
