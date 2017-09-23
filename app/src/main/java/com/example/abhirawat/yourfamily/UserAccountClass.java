package com.example.abhirawat.yourfamily;

/**
 * Created by Abhi Rawat on 23-09-2017.
 */

public class UserAccountClass {
    private String Gender;
    private String username;
    private String userMobile;
    private String password;

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
