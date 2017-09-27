package com.example.abhirawat.yourfamily;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ViewMemberActivity extends AppCompatActivity implements RecyclerViewForFamily.DeleteRowOnClick, RecyclerViewForFamily.ShowData, RecyclerViewForFamily.SetProfile {
    private static final int GALLERY_INTENT = 1;
    private SharedPreferencesForMemberData memberData;
    private List<MemberDataModel> list;
    private RecyclerView memberList;
    private ListView memberListView;
    private String name[];
    private String dummy[];
    private MemberDataModel familData;
    private RecyclerViewForFamily adapter;
    private ImageView imageView;
    private MemberDataModel memberDataModel;
    private FamilyDao imageData;
    private int imagePosition;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private StorageReference ref;
    final List<String> keyList = new ArrayList<>();
    final List<MemberDataModel> items = new ArrayList<>();
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_member);
        setRefference();
        dummy = new String[]{"sorry no data found"};
        list = new ArrayList<>();

        setViews();
        setAdapter();
        /*try {
            memberData = new SharedPreferencesForMemberData();
            String json = memberData.getFamilyData(this);
            Gson gson = new Gson();
            FamilyDao dao = gson.fromJson(json, FamilyDao.class);
            list = dao.getMemberList();
            familData = list.get(0);
        } catch (Exception e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false)
                    .setMessage("Sorry No Data Found")
                    .setPositiveButton(" Ok ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    }).show();
        }
        setViews();
        */
        //       setViewVisibility();
/*

        if (familData != null) {
            //memberListView.setVisibility(View.GONE);
            adapter = new RecyclerViewForFamily(this, list);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            memberList.setLayoutManager(mLayoutManager);
            memberList.setItemAnimator(new DefaultItemAnimator());
            memberList.setAdapter(adapter);
            adapter.notifyDataSetChanged();


        } else {
          */
/*  memberList.setVisibility(View.GONE);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dummy);
            memberListView.setAdapter(adapter);*//*



        }
*/

    }

    public void setRefference() {
        firebaseDatabase = new FireBaseConnection().getFirebaseConnection();
        reference = firebaseDatabase.getReference("FAMILY_MEMBER");
        ref = FirebaseStorage.getInstance().getReference();
        setChild();

    }

    public void setChild() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        keyList.add(postSnapshot.getKey());
                        MemberDataModel object = postSnapshot.getValue(MemberDataModel.class);
                        list.add(object);

                    }
                    adapter.notifyDataSetChanged();
                } else {
                    setAlertForNoData();
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void setAlertForNoData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false)
                .setMessage("Sorry NoData Found")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
    }

    public void setAdapter() {
        adapter = new RecyclerViewForFamily(this, list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        memberList.setLayoutManager(mLayoutManager);
        memberList.setItemAnimator(new DefaultItemAnimator());
        memberList.setAdapter(adapter);


    }

    public void setViews() {
        memberList = (RecyclerView) findViewById(R.id.recyclerViewfamilyMember);
        // memberListView = (ListView) findViewById(R.id.listViewfamilyMember);
    }

    public void setViewVisibility() {
        memberList.setVisibility(View.GONE);
        memberListView.setVisibility(View.GONE);
    }

  /*  public void setAdapter() {
      *//*  ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, name);
        memberList.setAdapter(adapter);*//*
    }*/

    public void Home(View view) {
        finish();
    }


    @Override
    public void deleteAt(int position) {
        final int pos = position;
       /* AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are You Confirm You Want To Delete This Member ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        memberData = new SharedPreferencesForMemberData();
                        String Data = memberData.getFamilyData(ViewMemberActivity.this);
                        Gson gson = new Gson();
                        FamilyDao data = gson.fromJson(Data, FamilyDao.class);
                        data.deleteMemberFromList(pos);
                        memberData.setFamilyData(ViewMemberActivity.this, data);
                        adapter.notifyDataSetChanged();
                        Intent intent = new Intent(ViewMemberActivity.this, ViewMemberActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).show();
*/
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false)
                .setMessage("Are you confirm you want to delete this member ?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        reference.child(keyList.get(pos)).removeValue();
                        list.remove(pos);
                        adapter.notifyDataSetChanged();
                        startActivity(new Intent(ViewMemberActivity.this, ViewMemberActivity.class));
                        finish();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        }).show();

    }

    @Override
    public void showDataAt(int position) {
        MemberDataModel memberDataModel = list.get(position);
        Intent intent = new Intent(this, ShowMemberData.class);
        intent.putExtra("MemberDataModelView", memberDataModel);
        startActivity(intent);

    }

    @Override
    public void setProfilePicActivity(ImageView view, int position, int id) {
        imageView = view;
        imagePosition = position;
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_INTENT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {
            progress = new ProgressDialog(this);
            progress.setMessage("Setting profile pic please wait....");
            progress.show();

            Uri uri = data.getData();
            StorageReference newStorageReference = ref.child("PROFILE_PIC").child(uri.getLastPathSegment());
            newStorageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    reference.child(keyList.get(imagePosition)).child("imageURL").setValue(taskSnapshot.getDownloadUrl().toString());
                    progress.dismiss();

                    startActivity(new Intent(ViewMemberActivity.this, ViewMemberActivity.class));
                    finish();
                }

            });

        }

    }
}


