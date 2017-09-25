package com.example.abhirawat.yourfamily;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyAccountActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int GALLERY_INTENT = 1;
    @InjectView(R.id.profileEmail)
    TextView tViewProfileEmail;
    @InjectView(R.id.buttonClearAccountDetails)
    Button clrAccountBtn;
    private TextView tvBack, tvProfileName, tvProfilePhone;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private CircleImageView profileImage;
    private StorageReference storageReference;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        ButterKnife.inject(this);
        clrAccountBtn.setOnClickListener(this);
        tvBack = (TextView) findViewById(R.id.tvBack);
        tvBack.setOnClickListener(this);
        tvProfileName = (TextView) findViewById(R.id.profileName);
        tvProfilePhone = (TextView) findViewById(R.id.profilePhone);
        profileImage = (CircleImageView) findViewById(R.id.profileImage);
        profileImage.setOnClickListener(this);
        setReferences();
    }

    public void setReferences() {
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("USER_PROFILE");
        storageReference = FirebaseStorage.getInstance().getReference();
        setValuesOfTextViewFields();
    }

    public void setValuesOfTextViewFields() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserAccountClass classObject = dataSnapshot.child("Account").getValue(UserAccountClass.class);
                if (!(classObject == null)) {
                    tvProfileName.setText(classObject.getUsername());
                    tvProfilePhone.setText(classObject.getUserMobile());
                    tViewProfileEmail.setText(classObject.getGmail());
                    if (!classObject.getProfile().equals(""))

                    {
                        Glide.with(MyAccountActivity.this).load(classObject.getProfile()).into(profileImage);
                    }
                    Toast.makeText(MyAccountActivity.this, " Exists ", Toast.LENGTH_LONG).show();
                } else {
                    tvProfileName.setText("");
                    tvProfilePhone.setText("");


                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonClearAccountDetails:
                SharedPreferencesForMemberData sharedPreferencesForMemberData = new SharedPreferencesForMemberData();
                sharedPreferencesForMemberData.clearUserAccount(this);
                databaseReference.removeValue();
                Toast.makeText(this, "AccountCleared", Toast.LENGTH_LONG).show();
                break;
            case R.id.tvBack:
                finish();
                break;
            case R.id.profileImage:
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Seeting Profile Image");
        progressDialog.show();
        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            StorageReference newStorageReference = storageReference.child("PROFILE_PIC").child(uri.getLastPathSegment());
            newStorageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    databaseReference.child("Account").child("profile").setValue(taskSnapshot.getDownloadUrl().toString());
                    progressDialog.dismiss();
                    startActivity(new Intent(MyAccountActivity.this, MyAccountActivity.class));
                    finish();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.setMessage("Failed...");
                    progressDialog.dismiss();

                }
            });
        }


    }
}
