package com.example.abhirawat.yourfamily;

import java.io.Serializable;

/**
 * Created by Abhi Rawat on 12-09-2017.
 */

public class Address implements Serializable {
    private String houseAdress;
    private int pincode;
    private String state;
    public Address()
    {
        this.pincode = 0;
    }
    public String getHouseAdress() {
        return houseAdress;
    }

    public void setHouseAdress(String houseAdress) {
        this.houseAdress = houseAdress;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
