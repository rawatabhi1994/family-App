package com.example.abhirawat.yourfamily;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

public class ShowMemberData extends AppCompatActivity {
    private MemberDataModel dataModel;
    private Intent intent;
    private TextView tViewName, tViewDob, tViewEmail, tViewAddress, tViewPincode, tViewState, tViewContact1, tViewContact2;
    private TextView tViewAadharId, tViewVoterId, tViewPanId, tViewDl;
    private TextView tViewHighSchoolDetail, tViewHighSchoolPercent, tViewIntermediate, tViewIntermediatePercent, tViewGraduateCollg, tViewGradtuateYear;
    private TextView tViewPostGraduateCollg, tViewPostGraduateYear, tViewEmpCompanyName, tViewEmpCompanyAddress;
    private SharedPreferencesForMemberData sharedPreferencesForMemberData;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_member_data);
        getViews();
        intent = getIntent();
        if(intent.hasExtra("MemberDataModel")) {
            dataModel = (MemberDataModel) intent.getSerializableExtra("MemberDataModel");
            sharedPreferencesForMemberData = new SharedPreferencesForMemberData();
            setData();
        }
        else if(intent.hasExtra("MemberDataModelView"))
        {
            dataModel = (MemberDataModel) intent.getSerializableExtra("MemberDataModelView");
            sharedPreferencesForMemberData = new SharedPreferencesForMemberData();
            setDataForView();
        }
    }

    public void Home(View view) {
        finish();
    }

    public void getViews() {
        tViewName = (TextView) findViewById(R.id.tviewNameValue);
        tViewDob = (TextView) findViewById(R.id.tviewDobValue);
        tViewEmail = (TextView) findViewById(R.id.tviewEmailValue);
        tViewAddress = (TextView) findViewById(R.id.tviewHouseAddressValue);
        tViewPincode = (TextView) findViewById(R.id.tviewPincodeValue);
        tViewState = (TextView) findViewById(R.id.tviewStateValue);
        tViewContact1 = (TextView) findViewById(R.id.tviewContact1Value);
        tViewContact2 = (TextView) findViewById(R.id.tviewContact2Value);
        tViewAadharId = (TextView) findViewById(R.id.tviewAadharValue);
        tViewVoterId = (TextView) findViewById(R.id.tviewVoterIdValue);
        tViewPanId = (TextView) findViewById(R.id.tviewPanIdValue);
        tViewDl = (TextView) findViewById(R.id.tviewDlValue);
        tViewHighSchoolDetail = (TextView) findViewById(R.id.tviewHighSchoolValue);
        tViewHighSchoolPercent = (TextView) findViewById(R.id.tviewHighSchoolValue);
        tViewIntermediate = (TextView) findViewById(R.id.tviewIntermediateCollegeValue);
        tViewIntermediatePercent = (TextView) findViewById(R.id.tviewIntermediatePercentageValue);
        tViewGraduateCollg = (TextView) findViewById(R.id.tviewGraduateCollegeValue);
        tViewGradtuateYear = (TextView) findViewById(R.id.tviewGradtuateYearValue);
        tViewPostGraduateCollg = (TextView) findViewById(R.id.tviewPostGraduateCollegeValue);
        tViewPostGraduateYear = (TextView) findViewById(R.id.tviewPostGradtuateYearValue);
        tViewEmpCompanyName = (TextView) findViewById(R.id.tviewEmpCompanyValue);
        tViewEmpCompanyAddress = (TextView) findViewById(R.id.tviewEmpCompanyAddressValue);
    }

    public void setData() {
        tViewName.setText(dataModel.getFirstName() + " " + dataModel.getMiddleName() + " " + dataModel.getLastName());
        tViewDob.setText(dataModel.getDob());
        tViewEmail.setText(dataModel.getEmail());
        Address address = dataModel.getAddress();
        tViewAddress.setText(address.getHouseAdress());
        tViewPincode.setText(address.getPincode() + "");
        tViewState.setText(address.getState());
        ContactInfo contact = dataModel.getContact();
        tViewContact1.setText(contact.getPhoneNumber1());
        tViewContact2.setText(contact.getPhoneNumber2());
        ImportantId id = dataModel.getId();
        tViewAadharId.setText(id.getAadharId());
        tViewVoterId.setText(id.getVoterId());
        tViewPanId.setText(id.getPanId());
        tViewDl.setText(id.getDlNo());
        EducationalQualification qualification = dataModel.getEduQualification();
        tViewHighSchoolDetail.setText(qualification.getHighschoolDetails());
        tViewHighSchoolPercent.setText(qualification.getHighSchoolPercent());
        tViewIntermediate.setText(qualification.getInterDetails());
        tViewIntermediatePercent.setText(qualification.getInterPercent());
        tViewGraduateCollg.setText(qualification.getUndergraduateCollege());
        tViewGradtuateYear.setText(qualification.getUndergraduateYear() + "");
        tViewPostGraduateCollg.setText(qualification.getPostgraduateCollege());
        tViewPostGraduateYear.setText(qualification.getPostgraduateYear() + "");
        EmploymentDetails details = dataModel.getEmpDetails();
        tViewEmpCompanyName.setText(details.getEmpCompany() + "");
        tViewEmpCompanyAddress.setText(details.getEmpAddress() + "");
        setPreferences();
    }
    public void setDataForView()
    {
        tViewName.setText(dataModel.getFirstName() + " " + dataModel.getMiddleName() + " " + dataModel.getLastName());
        tViewDob.setText(dataModel.getDob());
        tViewEmail.setText(dataModel.getEmail());
        Address address = dataModel.getAddress();
        tViewAddress.setText(address.getHouseAdress());
        tViewPincode.setText(address.getPincode() + "");
        tViewState.setText(address.getState());
        ContactInfo contact = dataModel.getContact();
        tViewContact1.setText(contact.getPhoneNumber1());
        tViewContact2.setText(contact.getPhoneNumber2());
        ImportantId id = dataModel.getId();
        tViewAadharId.setText(id.getAadharId());
        tViewVoterId.setText(id.getVoterId());
        tViewPanId.setText(id.getPanId());
        tViewDl.setText(id.getDlNo());
        EducationalQualification qualification = dataModel.getEduQualification();
        tViewHighSchoolDetail.setText(qualification.getHighschoolDetails());
        tViewHighSchoolPercent.setText(qualification.getHighSchoolPercent());
        tViewIntermediate.setText(qualification.getInterDetails());
        tViewIntermediatePercent.setText(qualification.getInterPercent());
        tViewGraduateCollg.setText(qualification.getUndergraduateCollege());
        tViewGradtuateYear.setText(qualification.getUndergraduateYear() + "");
        tViewPostGraduateCollg.setText(qualification.getPostgraduateCollege());
        tViewPostGraduateYear.setText(qualification.getPostgraduateYear() + "");
        EmploymentDetails details = dataModel.getEmpDetails();
        tViewEmpCompanyName.setText(details.getEmpCompany() + "");
        tViewEmpCompanyAddress.setText(details.getEmpAddress() + "");
    }

    public void setPreferences() {

        if (sharedPreferencesForMemberData.getFamilyData(this).equals("")) {
            FamilyDao dao = new FamilyDao();
            dao.setMemberListData(dataModel);
            sharedPreferencesForMemberData.setFamilyData(this, dao);
        } else {
            String data = sharedPreferencesForMemberData.getFamilyData(this);
            Gson gson = new Gson();
            FamilyDao dao = gson.fromJson(data, FamilyDao.class);
            dao.setMemberListData(dataModel);
            sharedPreferencesForMemberData.setFamilyData(this, dao);
        }

    }

}
