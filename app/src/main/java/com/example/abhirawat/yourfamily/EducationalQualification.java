package com.example.abhirawat.yourfamily;

import java.io.Serializable;

/**
 * Created by Abhi Rawat on 12-09-2017.
 */

public class EducationalQualification implements Serializable {
    private String highschoolDetails;
    private String highSchoolPercent;
    private String InterDetails;
    private String InterPercent;
    private String undergraduateCollege;
    private int undergraduateYear;
    private String postgraduateCollege;
    private int postgraduateYear;
    public EducationalQualification()
    {
        this.undergraduateYear = 0;
        this.postgraduateYear = 0;
    }
    public String getHighschoolDetails() {
        return highschoolDetails;
    }

    public void setHighschoolDetails(String highschoolDetails) {
        this.highschoolDetails = highschoolDetails;
    }

    public String getHighSchoolPercent() {
        return highSchoolPercent;
    }

    public void setHighSchoolPercent(String highSchoolPercent) {
        this.highSchoolPercent = highSchoolPercent;
    }

    public String getInterDetails() {
        return InterDetails;
    }

    public void setInterDetails(String interDetails) {
        InterDetails = interDetails;
    }

    public String getInterPercent() {
        return InterPercent;
    }

    public void setInterPercent(String interPercent) {
        InterPercent = interPercent;
    }

    public String getUndergraduateCollege() {
        return undergraduateCollege;
    }

    public void setUndergraduateCollege(String undergraduateCollege) {
        this.undergraduateCollege = undergraduateCollege;
    }

    public int getUndergraduateYear() {
        return undergraduateYear;
    }

    public void setUndergraduateYear(int undergraduateYear) {
        this.undergraduateYear = undergraduateYear;
    }

    public String getPostgraduateCollege() {
        return postgraduateCollege;
    }

    public void setPostgraduateCollege(String postgraduateCollege) {
        this.postgraduateCollege = postgraduateCollege;
    }

    public int getPostgraduateYear() {
        return postgraduateYear;
    }

    public void setPostgraduateYear(int postgraduateYear) {
        this.postgraduateYear = postgraduateYear;
    }
}
