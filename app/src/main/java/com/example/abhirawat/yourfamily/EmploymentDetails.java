package com.example.abhirawat.yourfamily;

import java.io.Serializable;

/**
 * Created by Abhi Rawat on 18-09-2017.
 */

public class EmploymentDetails implements Serializable {
    private String empCompany;
    private String empAddress;

    public String getEmpCompany() {
        return empCompany;
    }

    public void setEmpCompany(String empCompany) {
        this.empCompany = empCompany;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }
}
