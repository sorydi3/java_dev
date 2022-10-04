package com.udacity.jwdnd.course1.cloudstorage.entities;

import lombok.Data;

@Data
public class Credencial {
    private Integer credentialid;
    private String url;
    private String username;
    private String key;
    private String password;
    private Integer userid;

    @Override
    public String toString() {
        return "{" +
            " credentialid='" + getCredentialid() + "'" +
            ", url='" + getUrl() + "'" +
            ", username='" + getUsername() + "'" +
            ", key='" + getKey() + "'" +
            ", password='" + getPassword() + "'" +
            ", userid='" + getUserid() + "'" +
            "}";
    }
   

  
}
