package com.example.abhirawat.yourfamily;

import java.io.Serializable;

/**
 * Created by Abhi Rawat on 12-09-2017.
 */

public class MemberDataModel implements Serializable {
    private String firstName;
    private String middleName;
    private String lastName;
    private String relation;
    private String email;
    private String dob;
    private Address address;
    private ContactInfo contact;
    private ImportantId id;
    private EducationalQualification eduQualification;
    private EmploymentDetails empDetails;
    private String imageURL = "";

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ContactInfo getContact() {
        return contact;
    }

    public void setContact(ContactInfo contact) {
        this.contact = contact;
    }

    public ImportantId getId() {
        return id;
    }

    public void setId(ImportantId id) {
        this.id = id;
    }

    public EducationalQualification getEduQualification() {
        return eduQualification;
    }

    public void setEduQualification(EducationalQualification eduQualification) {
        this.eduQualification = eduQualification;
    }

    public EmploymentDetails getEmpDetails() {
        return empDetails;
    }

    public void setEmpDetails(EmploymentDetails empDetails) {
        this.empDetails = empDetails;
    }


    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
