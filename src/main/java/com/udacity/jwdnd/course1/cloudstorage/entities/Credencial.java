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

    public Credencial(Integer credentialId, String url, String username, String key, String password, Integer userId) {
        this.credentialid = credentialId;
        this.url = url;
        this.username = username;
        this.key = key;
        this.password = password;
        this.userid = userId;
    }

    @Override
    public String toString() {
        return "Credencial [credentialId=" + credentialid + ", url=" + url + ", userid=" + userid + ", username="
                + username + "]";
    }

  
}
