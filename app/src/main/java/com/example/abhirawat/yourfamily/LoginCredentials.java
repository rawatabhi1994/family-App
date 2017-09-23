package com.example.abhirawat.yourfamily;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Abhi Rawat on 17-09-2017.
 */

public class LoginCredentials {
    private String username;
    private int password;
    public  LoginCredentials()
    {

    }
    public LoginCredentials(String username,int password)
    {
        this.username = username;
        this.password = password;
    }
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put(this.username,this.username);
        result.put("Password",this.password);
        return result;
    }
}
