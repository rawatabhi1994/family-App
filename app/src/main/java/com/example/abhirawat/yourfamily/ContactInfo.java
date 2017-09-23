package com.example.abhirawat.yourfamily;

import java.io.Serializable;

/**
 * Created by Abhi Rawat on 12-09-2017.
 */

public class ContactInfo implements Serializable {
    private String phoneNumber1;
    private String phoneNumber2;

    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }
}
