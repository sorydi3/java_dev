package com.udacity.jwdnd.course1.cloudstorage.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.entities.Credencial;
import com.udacity.jwdnd.course1.cloudstorage.entities.User;
import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;

@Service
public class CredencialService {
    @Autowired
    private CredentialMapper credentialMapper;
    @Autowired
    private HashService hashService;

    /**
     * @param credencial
     * @return user id  if credencial is added successfully
     */

    public int addCredencial(Credencial credencial,String encodedSalt,User user) {

        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
       credencial.setKey(hashedPassword);
       credencial.setUserid(user.getUserid());
       credencial.setPassword(user.getPassword());
       credencial.setUrl("https://www.udacity.com");
       credencial.setUsername(user.getUsername());
       return credentialMapper.addCredential(credencial);
    }


    /**
     * @param credencialId
     * @return credencial if credencial is found
     */
    public Credencial getCredencial(Integer credencialId) {
        return credentialMapper.getCredential(credencialId);
    }


    /**
     * @param credencialId
     * @return user id if credencial is deleted successfully
     */
    public int deleteCredencial(Integer credencialId) {
        return credentialMapper.deleteCredential(credencialId);
    }

    /**
     * @param credencial
     * @return user id if credencial is updated successfully
     */

    public int updateCredencial(Credencial credencial) {
        return credentialMapper.updateCredential(credencial);
    }

    /**
     * 
     * @param credencialId
     * @return  true if credencial is found
     */
    public boolean isFoundCredential(Integer credencialId) {
        return credentialMapper.getCredential(credencialId) != null;
    }


    /**
     * 
     * @param username
     * @return Credencial if credencial is found otherwise null,search by username
     */
    public Credencial getCredentialByUsername(String username) {
        return credentialMapper.getCredentialByUsername(username);
    }

}
