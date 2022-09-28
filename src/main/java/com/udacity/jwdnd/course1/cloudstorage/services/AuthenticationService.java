package com.udacity.jwdnd.course1.cloudstorage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.entities.Credencial;
import com.udacity.jwdnd.course1.cloudstorage.entities.User;

@Service
public class AuthenticationService implements AuthenticationProvider {

    @Autowired
    CredencialService credencialService;
    @Autowired
    HashService hashService;
    @Autowired
    UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        System.out.println("username: " + username);
        System.out.println("password: " + password);

        User user = userService.getUser(username);
        if (user != null) {
            String salt = user.getSalt();
            String hashedPassword = hashService.getHashedValue(password, salt);
            if (user.getPassword().equals(hashedPassword)) {
                return new UsernamePasswordAuthenticationToken(username, password, null);
            }

        }else{
            System.out.println("USER NOT FOUND, CREDENTIAL IS NULL");
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
