package com.udacity.jwdnd.course1.cloudstorage.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import com.udacity.jwdnd.course1.cloudstorage.entities.Credencial;

@Mapper
public interface CredentialMapper {


    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    Credencial getCredential(Integer credentialId);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES (#{url}, #{username}, #{key}, #{password}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int addCredential(Credencial credentialId);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int deleteCredential(Integer credentialId);

    @Insert("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, key = #{key}, password = #{password} WHERE credentialid = #{credentialId}")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int updateCredential(Credencial credentialId);

    @Select("SELECT * FROM CREDENTIALS WHERE username = #{username}")
    Credencial getCredentialByUsername(String username);
    
}
