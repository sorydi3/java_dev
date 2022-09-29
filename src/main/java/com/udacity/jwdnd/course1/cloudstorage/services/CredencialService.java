package com.udacity.jwdnd.course1.cloudstorage.services;



import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.entities.Credencial;
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

    public int addCredencial(Credencial credencial) {
       String salt = getSalt();
       String hashedPassword = hashService.getHashedValue(credencial.getPassword(), salt);
       credencial.setKey(hashedPassword);
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
        System.out.println("deleting credencialId: " + credencialId);
        return credentialMapper.deleteCredential(credencialId);
    }


    /**
     * @param credencial
     * @return user id if credencial is updated successfully
     */

    public int updateCredencial(Credencial credencial) {
        String salt = getSalt();
        String hashedPassword = hashService.getHashedValue(credencial.getPassword(), salt);
        credencial.setKey(hashedPassword);
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



    /**
     * 
     * @param url
     * @return Credencial if credencial is found otherwise null,search by url
     */
    public static String getSalt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        return encodedSalt;
    }


    public List<Credencial> getAllCredencials(Integer userId) {
        return credentialMapper.getAllCredentials(userId);
    }

    public void displayCredentials(){
        List<Credencial> credencials = getAllCredencials();
        for (Credencial credencial : credencials) {
            System.out.println(credencial);
        }
    }



    public List<Credencial> getAllCredencials() {
        return credentialMapper.getAlCredentials();
    }

}
