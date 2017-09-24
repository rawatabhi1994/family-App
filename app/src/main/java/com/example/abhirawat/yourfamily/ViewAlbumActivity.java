package com.example.abhirawat.yourfamily;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.List;

public class ViewAlbumActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int GALLERY_INTENT = 1;
    private TextView tvBack;
    private Button uploadBtn;
    private EditText editImageName;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databasereference;
    private DatabaseReference datbaseImageReference;
    private StorageReference storageReference;
    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private RecyclerViewAdapterForImages imageAdapter;
    private List<ImageUploadInfo> info = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photos);
        tvBack = (TextView) findViewById(R.id.tvBack);
        tvBack.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
        uploadBtn = (Button) findViewById(R.id.uploadButton);
        uploadBtn.setOnClickListener(this);
        editImageName = (EditText) findViewById(R.id.editImageName);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewForDownloadedImage);
        storageReference = FirebaseStorage.getInstance().getReference();
        databasereference = FirebaseDatabase.getInstance().getReference("ALL_IMAGE_DATABASE");
        setAdapter();

    }

    public void setAdapter() {
        imageAdapter = new RecyclerViewAdapterForImages(this, info);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(imageAdapter);
        setRecyclerImageList();
    }

    public void setRecyclerImageList() {
        databasereference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    ImageUploadInfo imageUploadInfo = postSnapshot.getValue(ImageUploadInfo.class);
                    info.add(imageUploadInfo);
                }
                imageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvBack:
                finish();
                break;
            case R.id.uploadButton:
                getDatabaseConnection();
                if (editImageName.getText().toString().equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("please enter image name")
                            .setCancelable(false)
                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            }).show();
                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, GALLERY_INTENT);
                }
                break;

        }
    }

    public void getDatabaseConnection() {
        this.firebaseDatabase = new FireBaseConnection().getFirebaseConnection();
        storageReference = FirebaseStorage.getInstance().getReference();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {
            progressDialog.setMessage("Uploading.......");
            progressDialog.show();
            Uri uri = data.getData();
            StorageReference reference = storageReference.child("Album").child(uri.getLastPathSegment());
            reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    ImageUploadInfo imageUploadInfo = new ImageUploadInfo();
                    imageUploadInfo.setImageName(editImageName.getText().toString());
                    imageUploadInfo.setImageURL(taskSnapshot.getDownloadUrl().toString());
                    String ImageUploadId = databasereference.push().getKey();
                    databasereference.child(ImageUploadId).setValue(imageUploadInfo);

                    Toast.makeText(ViewAlbumActivity.this, "Successfull", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ViewAlbumActivity.this, "failed", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();

                }
            });
        }
    }
}
