package com.example.abhirawat.yourfamily;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddMemberActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private LinearLayout LinearAddContact, LinearHousehold, LinearIntermediateParent, LinearGraduateParent, LinearPostGraduateParent;
    private LinearLayout LinearIntermediate, LinearGraduate, LinearPostGraduate, LinearContact;
    private TextView dob, tvBack, tviewRelationSelect, tViewState, tViewQualification, tviewNext, tViewAddContact;
    private EditText edithighschoolDetail, editHighschoolpercent, editInterDetail, editInterPercent, editGradCollege, editGradYear, editPostGraduateColl, editPostGraduateYear;
    private EditText editFirstName, editLastName, editMiddleName, edemail, edhouseAddress, edPincode, editContact1, editContact2;
    private EditText editAadhar, editPan, editDl, editVoterId, editEmployementCompany, editEmploymentCompanyAddress;
    private DatePickerDialog.OnDateSetListener dateListner;
    private DatePickerDialog dpd;
    private TextView tViewIntermediateQuestion, tViewGraduateQuestion, tViewPostGraduateQuestion, tViewStreet, tViewContact;
    private TextView tViewImportantId, tViewEducationalQualification, tViewEmploye;
    private LinearLayout LinearImportantId, LinearEducation, LinearEmployment;
    private int tvYear;
    private int tvMonth;
    private int tvDay;
    private int pincode = 0;
    private int graduateYear = 0;
    private int postGraduateYear = 0;
    private RadioGroup RgAadhar, RgVoter, RgPan, RgDl, RgHighSchool, RgInter, RgGraduate, RgPostGraduate, RgEmployment;
    private Calendar calendar;
    private String[] relation;
    private String[] state;
    private String[] qualification;
    private MemberDataModel dataModel;
    private String firstName, lastName, relationship;
    private Intent intent;
    private boolean personalDetailFlag, addressFlag, importantIdFlag, contactFlag, EducationalQualificationFlag;
    private FirebaseDatabase firebaseDatabse;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);
        relation = getResources().getStringArray(R.array.relations);
        state = getResources().getStringArray(R.array.state);
        qualification = getResources().getStringArray(R.array.Qualification);
        dob = (TextView) findViewById(R.id.tviewdob);
        dob.setOnClickListener(this);
        dataModel = new MemberDataModel();
        calendar = Calendar.getInstance();
        tvYear = calendar.get(Calendar.YEAR);
        tvMonth = calendar.get(Calendar.MONTH);
        tvDay = calendar.get(Calendar.DAY_OF_MONTH);
        setViews();
        setRadioGroup();
        setViewVisibilityGone();
        setReferences();
    }

    public void setReferences() {
        firebaseDatabse = new FireBaseConnection().getFirebaseConnection();
        reference = firebaseDatabse.getReference("FAMILY_MEMBER");
    }

    public void setViews() {
        tvBack = (TextView) findViewById(R.id.tvBack);
        tvBack.setOnClickListener(this);
        tviewRelationSelect = (TextView) findViewById(R.id.tviewRelationSelect);
        LinearHousehold = (LinearLayout) findViewById(R.id.linearHousehold);
        tviewRelationSelect.setOnClickListener(this);
        editFirstName = (EditText) findViewById(R.id.editfirstName);
        editLastName = (EditText) findViewById(R.id.editlastName);
        editMiddleName = (EditText) findViewById(R.id.editmiddleName);
        edemail = (EditText) findViewById(R.id.edemail);
        edhouseAddress = (EditText) findViewById(R.id.edhouseadd);
        edPincode = (EditText) findViewById(R.id.edpincode);
        editContact1 = (EditText) findViewById(R.id.editcontact1);
        editContact2 = (EditText) findViewById(R.id.editcontact2);
        editAadhar = (EditText) findViewById(R.id.editAadhar);
        editDl = (EditText) findViewById(R.id.editDl);
        editPan = (EditText) findViewById(R.id.editPan);
        editVoterId = (EditText) findViewById(R.id.editVoter);
        edithighschoolDetail = (EditText) findViewById(R.id.editHighschool);
        editHighschoolpercent = (EditText) findViewById(R.id.editHighschoolPercent);
        editInterDetail = (EditText) findViewById(R.id.editInter);
        editInterPercent = (EditText) findViewById(R.id.editInterPercentage);
        editGradCollege = (EditText) findViewById(R.id.editGradCollege);
        editGradYear = (EditText) findViewById(R.id.editGradYear);
        editPostGraduateColl = (EditText) findViewById(R.id.editPostGradCollege);
        editPostGraduateYear = (EditText) findViewById(R.id.editPostGradYear);
        editEmployementCompany = (EditText) findViewById(R.id.editEmpCompany);
        editEmploymentCompanyAddress = (EditText) findViewById(R.id.editEmpCompanyAddress);
       /* tViewQualification = (TextView) findViewById(R.id.tViewQualification);
        tViewQualification.setOnClickListener(this);*/
        tViewState = (TextView) findViewById(R.id.tViewState);
        tViewState.setOnClickListener(this);
        tviewNext = (TextView) findViewById(R.id.tview_next);
        tviewNext.setOnClickListener(this);
        tViewStreet = (TextView) findViewById(R.id.tviewStreet);
        tViewContact = (TextView) findViewById(R.id.tViewContact);
        tViewImportantId = (TextView) findViewById(R.id.tviewImportantIDS);
        tViewEducationalQualification = (TextView) findViewById(R.id.tviewEducationQualification);
        tViewEmploye = (TextView) findViewById(R.id.tViewEmploy);
        //tViewStreet.setOnClickListener(this);
        // tViewAddContact = (TextView) findViewById(R.id.tViewaddContactText);
//        tViewAddContact.setOnClickListener(this);
        LinearAddContact = (LinearLayout) findViewById(R.id.li2);
        tViewGraduateQuestion = (TextView) findViewById(R.id.tViewGraduateQuestion);
        tViewIntermediateQuestion = (TextView) findViewById(R.id.tViewIntermediateQuestion);
        tViewPostGraduateQuestion = (TextView) findViewById(R.id.tviewPostGraduateQuestion);
        LinearGraduateParent = (LinearLayout) findViewById(R.id.linearGraduateParent);
        LinearIntermediateParent = (LinearLayout) findViewById(R.id.linearIntermediateParent);
        LinearPostGraduateParent = (LinearLayout) findViewById(R.id.linearPostGraduateParent);
        LinearGraduate = (LinearLayout) findViewById(R.id.linearGraduate);
        LinearIntermediate = (LinearLayout) findViewById(R.id.linearIntermediate);
        LinearPostGraduate = (LinearLayout) findViewById(R.id.linearPostGraduate);
        tViewContact = (TextView) findViewById(R.id.tViewContact);
        LinearContact = (LinearLayout) findViewById(R.id.linearContact);
        tViewImportantId = (TextView) findViewById(R.id.tviewImportantIDS);
        LinearImportantId = (LinearLayout) findViewById(R.id.linearImportantId);
        tViewEducationalQualification = (TextView) findViewById(R.id.tviewEducationQualification);
        LinearEducation = (LinearLayout) findViewById(R.id.lineareducationDetails);
        LinearEmployment = (LinearLayout) findViewById(R.id.linearEmploymentStatus);
    }

    public void setViewVisibilityGone() {
        editAadhar.setVisibility(View.GONE);
        editVoterId.setVisibility(View.GONE);
        editPan.setVisibility(View.GONE);
        editDl.setVisibility(View.GONE);
        edithighschoolDetail.setVisibility(View.GONE);
        editHighschoolpercent.setVisibility(View.GONE);
        editInterDetail.setVisibility(View.GONE);
        editInterPercent.setVisibility(View.GONE);
        editGradCollege.setVisibility(View.GONE);
        editGradYear.setVisibility(View.GONE);
        editPostGraduateColl.setVisibility(View.GONE);
        editPostGraduateYear.setVisibility(View.GONE);
        editEmployementCompany.setVisibility(View.GONE);
        editEmploymentCompanyAddress.setVisibility(View.GONE);
        tViewIntermediateQuestion.setVisibility(View.GONE);
        tViewGraduateQuestion.setVisibility(View.GONE);
        tViewPostGraduateQuestion.setVisibility(View.GONE);
        LinearGraduateParent.setVisibility(View.GONE);
        LinearIntermediateParent.setVisibility(View.GONE);
        LinearPostGraduateParent.setVisibility(View.GONE);
        LinearHousehold.setVisibility(View.GONE);
        LinearContact.setVisibility(View.GONE);
        LinearImportantId.setVisibility(View.GONE);
        LinearEducation.setVisibility(View.GONE);
        LinearEmployment.setVisibility(View.GONE);
    }

    public void setRadioGroup() {
        RgAadhar = (RadioGroup) findViewById(R.id.RgAadhar);
        RgVoter = (RadioGroup) findViewById(R.id.RgVoter);
        RgPan = (RadioGroup) findViewById(R.id.RgPan);
        RgDl = (RadioGroup) findViewById(R.id.RgDl);
        RgHighSchool = (RadioGroup) findViewById(R.id.RgHighSchool);
        RgInter = (RadioGroup) findViewById(R.id.RgInter);
        RgGraduate = (RadioGroup) findViewById(R.id.RgGraduate);
        RgPostGraduate = (RadioGroup) findViewById(R.id.RgPostGraduate);
        RgAadhar.setOnCheckedChangeListener(this);
        RgVoter.setOnCheckedChangeListener(this);
        RgPan.setOnCheckedChangeListener(this);
        RgDl.setOnCheckedChangeListener(this);
        RgHighSchool.setOnCheckedChangeListener(this);
        RgInter.setOnCheckedChangeListener(this);
        RgGraduate.setOnCheckedChangeListener(this);
        RgPostGraduate.setOnCheckedChangeListener(this);
        RgEmployment = (RadioGroup) findViewById(R.id.RgEmployment);
        RgEmployment.setOnCheckedChangeListener(this);
    }

    public void toggle_contents(View v) {
        if (LinearHousehold.isShown()) {
            slide_up(this, LinearHousehold);
            LinearHousehold.setVisibility(View.GONE);
            Drawable img = getResources().getDrawable(R.drawable.circle_plus);
            tViewStreet.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
        } else {
            LinearHousehold.setVisibility(View.VISIBLE);
            slide_down(this, LinearHousehold);
            Drawable img = getResources().getDrawable(R.drawable.circle_minus);
            tViewStreet.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
        }
    }

    public void toggle_employment(View view) {
        if (LinearEmployment.isShown()) {
            slide_up(this, LinearEmployment);
            LinearEmployment.setVisibility(View.GONE);
            Drawable img = getResources().getDrawable(R.drawable.circle_plus);
            tViewEmploye.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
        } else {
            LinearEmployment.setVisibility(View.VISIBLE);
            slide_down(this, LinearEmployment);
            Drawable img = getResources().getDrawable(R.drawable.circle_minus);
            tViewEmploye.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
        }
    }

    public void toggle_contact(View view) {
        if (LinearContact.isShown()) {
            slide_up(this, LinearContact);
            LinearContact.setVisibility(View.GONE);
            Drawable img = getResources().getDrawable(R.drawable.circle_plus);
            tViewContact.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
        } else {
            LinearContact.setVisibility(View.VISIBLE);
            slide_down(this, LinearContact);
            Drawable img = getResources().getDrawable(R.drawable.circle_minus);
            tViewContact.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
        }
    }

    public void toggle_Id(View view) {
        if (LinearImportantId.isShown()) {
            slide_up(this, LinearImportantId);
            LinearImportantId.setVisibility(View.GONE);
            Drawable img = getResources().getDrawable(R.drawable.circle_plus);
            tViewImportantId.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
        } else {
            LinearImportantId.setVisibility(View.VISIBLE);
            slide_down(this, LinearImportantId);
            Drawable img = getResources().getDrawable(R.drawable.circle_minus);
            tViewImportantId.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
        }
    }

    public void toggle_educationDetails(View view) {
        if (LinearEducation.isShown()) {
            slide_up(this, LinearEducation);
            LinearEducation.setVisibility(View.GONE);
            Drawable img = getResources().getDrawable(R.drawable.circle_plus);
            tViewEducationalQualification.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
        } else {
            LinearEducation.setVisibility(View.VISIBLE);
            slide_down(this, LinearEducation);
            Drawable img = getResources().getDrawable(R.drawable.circle_minus);
            tViewEducationalQualification.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        switch (radioGroup.getId()) {
            case R.id.RgAadhar:
                int id = radioGroup.getCheckedRadioButtonId();
                if (id == R.id.RbAadharyes) {
                    editAadhar.setVisibility(View.VISIBLE);
                } else {
                    editAadhar.setVisibility(View.GONE);
                }
                break;
            case R.id.RgVoter:
                int voterId = radioGroup.getCheckedRadioButtonId();
                if (voterId == R.id.RbVoteryes) {
                    editVoterId.setVisibility(View.VISIBLE);
                } else {
                    editVoterId.setVisibility(View.GONE);
                }
                break;
            case R.id.RgPan:
                int panId = radioGroup.getCheckedRadioButtonId();
                if (panId == R.id.RbPanyes) {

                    editPan.setVisibility(View.VISIBLE);
                } else {
                    editPan.setVisibility(View.GONE);
                }
            case R.id.RgDl:
                int dlId = radioGroup.getCheckedRadioButtonId();
                if (dlId == R.id.RbDlyes) {
                    editDl.setVisibility(View.VISIBLE);
                } else {
                    editDl.setVisibility(View.GONE);
                }
                break;
            case R.id.RgHighSchool:
                int highSchool = radioGroup.getCheckedRadioButtonId();
                if (highSchool == R.id.RbHighSchoolyes) {
                    edithighschoolDetail.setVisibility(View.VISIBLE);
                    editHighschoolpercent.setVisibility(View.VISIBLE);
                    LinearIntermediateParent.setVisibility(View.VISIBLE);
                    tViewIntermediateQuestion.setVisibility(View.VISIBLE);
                    LinearIntermediate.setVisibility(View.VISIBLE);

                } else {
                    edithighschoolDetail.setVisibility(View.GONE);
                    editHighschoolpercent.setVisibility(View.GONE);
                    LinearIntermediateParent.setVisibility(View.GONE);
                    LinearGraduateParent.setVisibility(View.GONE);
                    LinearPostGraduateParent.setVisibility(View.GONE);
                }
                break;
            case R.id.RgInter:
                int inter = radioGroup.getCheckedRadioButtonId();
                if (inter == R.id.RbInteryes) {
                    editInterDetail.setVisibility(View.VISIBLE);
                    editInterPercent.setVisibility(View.VISIBLE);
                    LinearGraduateParent.setVisibility(View.VISIBLE);
                    tViewGraduateQuestion.setVisibility(View.VISIBLE);
                    LinearGraduate.setVisibility(View.VISIBLE);
                } else {

                    editInterDetail.setVisibility(View.GONE);
                    editInterPercent.setVisibility(View.GONE);
                    LinearGraduateParent.setVisibility(View.GONE);
                    LinearPostGraduateParent.setVisibility(View.GONE);
                }
                break;
            case R.id.RgGraduate:
                int graduate = radioGroup.getCheckedRadioButtonId();
                if (graduate == R.id.RbGraduateyes) {
                    editGradCollege.setVisibility(View.VISIBLE);
                    editGradYear.setVisibility(View.VISIBLE);
                    LinearPostGraduateParent.setVisibility(View.VISIBLE);
                    RgPostGraduate.setVisibility(View.VISIBLE);
                    tViewPostGraduateQuestion.setVisibility(View.VISIBLE);
                } else {
                    editGradCollege.setVisibility(View.GONE);
                    editGradYear.setVisibility(View.GONE);
                    LinearPostGraduateParent.setVisibility(View.GONE);
                }
                break;
            case R.id.RgPostGraduate:
                int postGraduate = radioGroup.getCheckedRadioButtonId();
                if (postGraduate == R.id.RbPostGraduateyes) {
                    LinearPostGraduateParent.setVisibility(View.VISIBLE);
                    editPostGraduateColl.setVisibility(View.VISIBLE);
                    editPostGraduateYear.setVisibility(View.VISIBLE);
                } else {
                    editPostGraduateColl.setVisibility(View.GONE);
                    editPostGraduateYear.setVisibility(View.GONE);

                }
                break;
            case R.id.RgEmployment:
                int emp = radioGroup.getCheckedRadioButtonId();
                if (emp == R.id.RbEmploymentyes) {
                    LinearEmployment.setVisibility(View.VISIBLE);
                    editEmployementCompany.setVisibility(View.VISIBLE);
                    editEmploymentCompanyAddress.setVisibility(View.VISIBLE);
                } else {
                    editEmployementCompany.setVisibility(View.GONE);
                    editEmploymentCompanyAddress.setVisibility(View.GONE);

                }

        }
    }

    public boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void emailCheck() {
        boolean value = emailValidator(edemail.getText().toString());
        if (value && !edemail.getText().toString().equals("") || edemail.getText().toString().equals("")) {
            checkHouseAddress();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Please enter email correctly")
                    .setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    }).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tviewdob:
                dateListner = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        dob.setText("" + month + "/" + day + "/" + year);
                    }
                };
                dpd = new DatePickerDialog(this, dateListner, tvYear, tvMonth, tvDay);
                dpd.setButton(DialogInterface.BUTTON_POSITIVE, "Set", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        DatePicker datePicker = dpd.getDatePicker();

                        // The following clear focus did the trick of saving the date while the date is put manually by the edit text.
                        datePicker.clearFocus();
                        dateListner.onDateSet(datePicker, datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
//						hideSoftKeyboard(More.this);
                    }
                });
                dpd.show();
                break;
            case R.id.tvBack:
               /* Intent intent = new Intent(this, DataActivity.class);
                startActivity(intent);*/
                finish();
                break;
            case R.id.tviewRelationSelect:
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddMemberActivity.this, R.layout.dropdown_text, R.id.spinnerText, relation);
                AlertDialog.Builder builder = new AlertDialog.Builder(AddMemberActivity.this);
                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tviewRelationSelect.setText(relation[i].toString());
                    }
                });
                builder.show();
                break;
            case R.id.tViewState:
                ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(AddMemberActivity.this, R.layout.dropdown_text, R.id.spinnerText, state);
                AlertDialog.Builder stateBuilder = new AlertDialog.Builder(AddMemberActivity.this);
                stateBuilder.setAdapter(stateAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tViewState.setText(state[i].toString());
                    }
                });
                stateBuilder.show();
                break;
            case R.id.tview_next:
                relationCheck();
                if (relationship.equals("")) {
                    saveDataInModel();
                } else {
                    AlertDialog.Builder relationBuilder = new AlertDialog.Builder(this);
                    relationBuilder.setCancelable(false)
                            .setMessage(relationship).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    }).show();
                }
                break;
            /*case R.id.tViewQualification :
                ArrayAdapter<String> qualificationAdapter = new ArrayAdapter<String>(AddMemberActivity.this,R.layout.dropdown_text,R.id.spinnerText,qualification);
                AlertDialog.Builder qualifBuilder = new AlertDialog.Builder(AddMemberActivity.this);
                qualifBuilder.setAdapter(qualificationAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tViewQualification.setText(qualification[i].toString());
                    }
                });
                qualifBuilder.show();
                break;*/

        }
    }

    public void checkName() {
        firstNameCheck();
        lastNameCheck();
    }

    public void firstNameCheck() {
        String check = editFirstName.getText().toString();
        if (check.equals("") || check.equals(null)) {
            firstName = "Please Enter FirstName\n";

        } else {
            firstName = "";
        }

    }

    public void lastNameCheck() {
        String check = editLastName.getText().toString();
        if (check.equals("") || check.equals(null)) {
            lastName = "Please Enter LastName\n";

        } else {
            lastName = "";
        }
    }

    public String relationCheck() {
        relationship = tviewRelationSelect.getText().toString();
        if (relationship.equalsIgnoreCase("select") || relationship.equals("") || relationship == null) {
            relationship = "Please Enter RelationShip\n";
            return relationship;
        } else {
            relationship = "";
            return relationship;
        }
    }

    public void checkHouseAddress() {
        String check = edhouseAddress.getText().toString();
        if (check.equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false)
                    .setMessage("Please Specify House Address")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    }).show();
            edhouseAddress.requestFocus();

        } else {
            checkPinCode();
        }


    }

    public void checkPinCode() {
        String check = edPincode.getText().toString();
        if (check.equals("") || check.length() < 6 || check.length() > 6) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false)
                    .setMessage("Please Specify PinCode Correctly")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    }).show();
            edPincode.requestFocus();
        } else {
            importantIdsCheck();
        }
    }

    public void personalDetailCheck() {
        checkName();

    }

    public void addressCheck() {
        checkHouseAddress();
    }

    public void contactCheck() {
        contactFlag = true;
    }

    public void importantIdsCheck() {
        checkAadhar();
    }

    public void checkAadhar() {
        if (RgAadhar.getCheckedRadioButtonId() == R.id.RbAadharyes) {
            String check = editAadhar.getText().toString();
            if (check.equals("") || check.length() < 12 || check.length() > 12) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(false)
                        .setMessage("Please Specify Aadhar Id")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();
                editAadhar.requestFocus();
            } else {
                checkVoterId();
            }
        } else {
            checkVoterId();
        }

    }

    public void checkVoterId() {
        if (RgVoter.getCheckedRadioButtonId() == R.id.RbVoteryes) {
            String check = editVoterId.getText().toString();
            if (check.equals("")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(false)
                        .setMessage("Please Specify Voter")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();
                editVoterId.requestFocus();
            } else {
                checkPan();
            }
        } else {
            checkPan();
        }

    }

    public void checkPan() {
        if (RgPan.getCheckedRadioButtonId() == R.id.RbPanyes) {
            String check = editPan.getText().toString();
            if (check.equals("") || check.length() < 10 || check.length() > 10) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(false)
                        .setMessage("Please Specify Pan Id Correctly")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();
                editPan.requestFocus();
            } else {
                checkDl();
            }
        } else {
            checkDl();
        }
    }

    public void checkDl() {
        if (RgDl.getCheckedRadioButtonId() == R.id.RbDlyes) {
            String check = editDl.getText().toString();
            if (check.equals("") || check.length() < 17 || check.length() > 18) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(false)
                        .setMessage("Please Specify Dl Correctly")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();
                editDl.requestFocus();
            } else {
                educationalQualificationCheck();
            }
        } else {
            educationalQualificationCheck();
        }
    }

    public void educationalQualificationCheck() {
        checkHighSchool();
    }

    public void checkHighSchool() {
        if (RgHighSchool.getCheckedRadioButtonId() == R.id.RbHighSchoolyes) {
            String check1 = edithighschoolDetail.getText().toString();
            String check2 = editHighschoolpercent.getText().toString();
            if (check1.equals("") || check2.equals("")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(false)
                        .setMessage("Please Complete HighSchool Details")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();
                edithighschoolDetail.requestFocus();
            } else {
                checkIntermediate();
            }
        } else {
            saveFinalModelData();
        }
    }

    public void checkIntermediate() {
        if (LinearIntermediateParent.getVisibility() == View.VISIBLE && RgInter.getCheckedRadioButtonId() == R.id.RbInteryes) {
            String check1 = editInterDetail.getText().toString();
            String check2 = editInterPercent.getText().toString();
            if (check1.equals("") || check2.equals("")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(false)
                        .setMessage("Please Complete InterMediate Details")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();
                editInterDetail.requestFocus();
            } else {
                checkGraduation();
            }
        } else {
            saveFinalModelData();
        }
    }

    public void checkGraduation() {
        if (LinearGraduateParent.getVisibility() == View.VISIBLE && RgGraduate.getCheckedRadioButtonId() == R.id.RbGraduateyes) {
            String check1 = editGradCollege.getText().toString();
            String check2 = editGradYear.getText().toString();
            if (check1.equals("") || check2.equals("")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(false)
                        .setMessage("Please Complete Graduation Details")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();
                editGradCollege.requestFocus();
            } else {
                checkPostGraduation();
            }

        } else {
            saveFinalModelData();
        }
    }

    public void checkPostGraduation() {
        if (LinearPostGraduateParent.getVisibility() == View.VISIBLE && RgPostGraduate.getCheckedRadioButtonId() == R.id.RbPostGraduateyes) {
            String check1 = editPostGraduateColl.getText().toString();
            String check2 = editPostGraduateYear.getText().toString();
            if (check1.equals("") || check2.equals("")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(false)
                        .setMessage("Please Complete PostGraduation Details")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();
                editPostGraduateColl.requestFocus();
            } else {
                saveFinalModelData();
            }

        } else {
            saveFinalModelData();
        }
    }

    public void saveFinalModelData() {

        dataModel.setFirstName(editFirstName.getText().toString() + "");
        dataModel.setLastName(editLastName.getText().toString() + "");
        dataModel.setRelation(tviewRelationSelect.getText().toString());
        dataModel.setMiddleName(editMiddleName.getText().toString() + "");
        dataModel.setDob(dob.getText().toString() + "");
        dataModel.setEmail(edemail.getText().toString());
        Address address = new Address();
        address.setHouseAdress(edhouseAddress.getText().toString() + "");
        pincode = Integer.valueOf(edPincode.getText().toString());
        address.setPincode(pincode);
        address.setState(tViewState.getText().toString() + "");
        dataModel.setAddress(address);
        ContactInfo contact = new ContactInfo();
        contact.setPhoneNumber1(editContact1.getText().toString() + "");
        contact.setPhoneNumber2(editContact2.getText().toString() + "");
        dataModel.setContact(contact);
        ImportantId id = new ImportantId();
        int check1 = RgAadhar.getCheckedRadioButtonId();
        if (check1 == R.id.RbAadharyes) {
            id.setAadharId(editAadhar.getText().toString());
        } else {
            id.setAadharId("");
        }
        if (RgVoter.getCheckedRadioButtonId() == R.id.RbVoteryes) {
            id.setDlNo(editDl.getText().toString());
        } else {
            id.setDlNo("");
        }
        if (RgPan.getCheckedRadioButtonId() == R.id.RbPanyes) {
            id.setPanId(editPan.getText().toString() + "");
        } else {
            id.setPanId("");
        }
        if (RgVoter.getCheckedRadioButtonId() == R.id.RbVoteryes) {
            id.setVoterId(editVoterId.getText().toString() + "");
        } else {
            id.setVoterId("");
        }
        dataModel.setId(id);
        EducationalQualification eduQualif = new EducationalQualification();
        if (RgHighSchool.getCheckedRadioButtonId() == R.id.RbHighSchoolyes) {
            eduQualif.setHighschoolDetails(edithighschoolDetail.getText().toString() + "");
            eduQualif.setHighSchoolPercent(editHighschoolpercent.getText().toString() + "");
        } else {
            eduQualif.setHighschoolDetails("");
            eduQualif.setHighSchoolPercent("");
        }
        if (RgHighSchool.getCheckedRadioButtonId() == R.id.RbHighSchoolyes && RgInter.getCheckedRadioButtonId() == R.id.RbInteryes) {
            eduQualif.setInterDetails(editInterDetail.getText().toString() + "");
            eduQualif.setInterPercent(editInterPercent.getText().toString() + "");
        } else {
            eduQualif.setInterDetails("");
            eduQualif.setInterPercent("");
        }
        if (RgHighSchool.getCheckedRadioButtonId() == R.id.RbHighSchoolyes && RgInter.getCheckedRadioButtonId() == R.id.RbInteryes && RgGraduate.getCheckedRadioButtonId() == R.id.RbGraduateyes) {
            eduQualif.setUndergraduateCollege(editGradCollege.getText().toString() + "");
            graduateYear = Integer.parseInt(editGradYear.getText().toString());
            eduQualif.setUndergraduateYear(graduateYear);
        } else {
            eduQualif.setUndergraduateCollege("");
            eduQualif.setUndergraduateYear(0);
        }
        if (RgHighSchool.getCheckedRadioButtonId() == R.id.RbHighSchoolyes && RgInter.getCheckedRadioButtonId() == R.id.RbInteryes && RgGraduate.getCheckedRadioButtonId() == R.id.RbGraduateyes && RgPostGraduate.getCheckedRadioButtonId() == R.id.RbPostGraduateyes) {
            eduQualif.setPostgraduateCollege(editPostGraduateColl.getText().toString());
            postGraduateYear = Integer.parseInt(editPostGraduateYear.getText().toString());
            eduQualif.setPostgraduateYear(postGraduateYear);
        } else {
            eduQualif.setPostgraduateCollege("");
            eduQualif.setPostgraduateYear(0);
        }
        dataModel.setEduQualification(eduQualif);
        EmploymentDetails details = new EmploymentDetails();
        details.setEmpCompany(editEmployementCompany.getText().toString() + "");
        details.setEmpAddress(editEmploymentCompanyAddress.getText().toString() + "");
        dataModel.setEmpDetails(details);
        showDataModel();
    }

    public void saveDataInModel() {
        personalDetailCheck();
        if (firstName.equals("") && lastName.equals("")) {
            emailCheck();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false)
                    .setMessage(firstName + "" + lastName)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    }).show();
            editFirstName.requestFocus();
        }

    }

    public void showDataModel() {
        intent = new Intent(this, ShowMemberData.class);
        intent.putExtra("MemberDataModel", dataModel);
        String key = reference.push().getKey();
        reference.child(key).setValue(dataModel);
        startActivity(intent);
        finish();
    }

    public static void slide_down(Context ctx, View v) {
        Animation a = AnimationUtils.loadAnimation(ctx, R.anim.slide);
        if (a != null) {
            a.reset();
            if (v != null) {
                v.clearAnimation();
                v.startAnimation(a);
            }
        }
    }

    public static void slide_up(Context ctx, View v) {
        Animation a = AnimationUtils.loadAnimation(ctx, R.anim.slide_up);

        if (a != null) {
            a.reset();
            if (v != null) {
                v.clearAnimation();
                v.startAnimation(a);
            }
        }
    }
}
