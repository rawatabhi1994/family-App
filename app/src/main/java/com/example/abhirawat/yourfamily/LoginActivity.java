package com.example.abhirawat.yourfamily;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editUsername, editPassword;
    private Button btnLogin, btnCreateAccount;
    private TextView tViewData;
    private FirebaseDatabase database;
    private DatabaseReference myParentRef, myRef;
    private UserAccountClass users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
       /* database = FirebaseDatabase.getInstance();
        myParentRef = database.getReference();
        myParentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, String> list = dataSnapshot.getValue(Map.class);
             //   tViewData.setText(" " + list.get("UserName") + "  " + list.get("Password"));
                Log.d("Log_e_value","Name "+list.get("UserName"));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
        SharedPreferencesForMemberData sharedPreferencesForMemberData = new SharedPreferencesForMemberData();
        String user = sharedPreferencesForMemberData.getUserAccount(this);
        if (!user.equals("")) {
            Gson gson = new Gson();
            users = gson.fromJson(user, UserAccountClass.class);
            editUsername.setText(users.getUsername());
            editPassword.setText(users.getPassword());
        }

    }

    public void findViews() {
        btnLogin = (Button) findViewById(R.id.btnlogin);
        btnLogin.setOnClickListener(this);
        btnCreateAccount = (Button) findViewById(R.id.btnCreate);
        btnCreateAccount.setOnClickListener(this);
        editUsername = (EditText) findViewById(R.id.tviewUsername);
        editPassword = (EditText) findViewById(R.id.tviewPassword);
        tViewData = (TextView) findViewById(R.id.tviewdata);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnlogin:
                if (editUsername.getText().toString().equals(users.getUsername()) || editPassword.getText().toString().equals(users.getPassword())) {
                    Intent intent = new Intent(this, DataActivity.class);
                    startActivity(intent);

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setCancelable(false)
                            .setMessage("Error in logging in Please Check Credentials").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    }).show();

                }
                break;
            case R.id.btnCreate:
                Intent intent = new Intent(this, CreateAccountActivity.class);
                startActivity(intent);
                break;

        }

    }
}
