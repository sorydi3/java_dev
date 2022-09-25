package com.udacity.jwdnd.course1.cloudstorage.entities;

import lombok.Data;

@Data
public class User {
    /** */
   private Integer userid;
   private String username;
   private String salt;
   private String password;
   private String firstname;
   private String lastname;

   /**
    * 
    * @param userId
    * @param userName
    * @param salt
    * @param password
    * @param firstName
    * @param lastName
    */
    public User(Integer userId, String userName, String salt, String password, String firstName, String lastName) {
         this.userid = userId;
         this.username = userName;
         this.salt = salt;
         this.password = password;
         this.firstname = firstName;
         this.lastname = lastName;
    }
}
