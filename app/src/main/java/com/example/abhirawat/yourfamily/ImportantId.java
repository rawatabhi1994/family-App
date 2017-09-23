package com.example.abhirawat.yourfamily;

import java.io.Serializable;

/**
 * Created by Abhi Rawat on 12-09-2017.
 */

public class ImportantId implements Serializable {
    private String AadharId;
    private String VoterId;
    private String panId;
    private String DlNo;


    public String getAadharId() {
        return AadharId;
    }

    public void setAadharId(String aadharId) {
        AadharId = aadharId;
    }

    public String getVoterId() {
        return VoterId;
    }

    public void setVoterId(String voterId) {
        VoterId = voterId;
    }

    public String getPanId() {
        return panId;
    }

    public void setPanId(String panId) {
        this.panId = panId;
    }

    public String getDlNo() {
        return DlNo;
    }

    public void setDlNo(String dlNo) {
        DlNo = dlNo;
    }
}
