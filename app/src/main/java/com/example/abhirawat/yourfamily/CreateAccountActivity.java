package com.example.abhirawat.yourfamily;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {
    private CircleImageView maleImage;
    private CircleImageView femaleImage;
    private TextView tvBack;
    private Button btnConfirm;
    private EditText editUsername;
    private EditText editMobile;
    private EditText editPassword;
    private EditText editConfirm;
    private String userNameValue;
    private String userContactValue;
    private String PasswordCheck;
    private String gender;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        setViews();
        setClickListeners();
        setReferences();

    }

    public void setReferences() {
        firebaseDatabase = new FireBaseConnection().getFirebaseConnection();
        databaseReference = firebaseDatabase.getInstance().getReference("USER_PROFILE");
    }

    public void setViews() {
        tvBack = (TextView) findViewById(R.id.tvBack);
        maleImage = (CircleImageView) findViewById(R.id.imageMale);
        femaleImage = (CircleImageView) findViewById(R.id.imageFeMale);
        editUsername = (EditText) findViewById(R.id.edituserName);
        editMobile = (EditText) findViewById(R.id.editMobile);
        editPassword = (EditText) findViewById(R.id.editPassword);
        editConfirm = (EditText) findViewById(R.id.editConfirmPassword);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvBack:
                finish();
                break;
            case R.id.imageMale:
                femaleImage.setVisibility(View.GONE);
                break;
            case R.id.imageFeMale:
                maleImage.setVisibility(View.GONE);
                break;
            case R.id.btnConfirm:
                setDataForUserAuthentication();

        }

    }

    public void setClickListeners() {
        tvBack.setOnClickListener(this);
        maleImage.setOnClickListener(this);
        femaleImage.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
    }

    public void setDataForUserAuthentication() {
        checkUserName();
        if (userNameValue.equals("")) {
            checkPhone();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false)
                    .setMessage(userNameValue)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    }).show();
        }
    }

    public void checkUserName() {
        String userName = editUsername.getText().toString();
        if (userName.equals("") || userName.length() < 5) {
            userNameValue = "Please Enter Username and Username length must be greater than 5";
        } else {
            userNameValue = "";
        }
    }

    public void checkPhone() {
        checkPhoneValue();
        if (userContactValue.equals("")) {
            checkPasswordValues();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false)
                    .setMessage(userContactValue)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    }).show();
        }
    }

    private void checkPasswordValues() {
        checkPassword();
        if (PasswordCheck.equals("")) {
            saveDataInUserAccount();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false)
                    .setMessage(PasswordCheck)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    }).show();
        }
    }

    private void saveDataInUserAccount() {
        SharedPreferencesForMemberData sharedPreferencesForMemberData = new SharedPreferencesForMemberData();
        UserAccountClass userAccountClass = new UserAccountClass();
        if (maleImage.getVisibility() == View.VISIBLE && femaleImage.getVisibility() == View.GONE) {
            userAccountClass.setGender("Male");
        } else if (femaleImage.getVisibility() == View.VISIBLE && maleImage.getVisibility() == View.GONE) {
            userAccountClass.setGender("Female");
        } else {
            userAccountClass.setGender("");
        }
        userAccountClass.setUsername(editUsername.getText().toString());
        userAccountClass.setUserMobile(editMobile.getText().toString());
        userAccountClass.setPassword(editPassword.getText().toString());
        sharedPreferencesForMemberData.setUserAccount(this, userAccountClass);
        databaseReference.child("Account").setValue(userAccountClass);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false)
                .setMessage("Your Account Has Been Made Please Login Now")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.cancel();
                        finish();
                    }
                }).show();

    }

    private void checkPhoneValue() {
        String contact = editMobile.getText().toString();
        if (contact.length() < 10) {
            userContactValue = "Please Enter Correct Number";
        } else {
            userContactValue = "";
        }
    }

    private void checkPassword() {
        String check = editPassword.getText().toString();
        String check2 = editConfirm.getText().toString();
        Pattern pattern;
        Matcher matcher, matcher2;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(check);
        matcher2 = pattern.matcher(check2);
        if ((!matcher.matches()) || (!matcher2.matches()) || (!check.equals(check2))) {
            PasswordCheck = "Both Password Field Must Be Same";

        } else {
            PasswordCheck = "";
        }
    }
}
