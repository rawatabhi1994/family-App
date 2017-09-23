package com.example.abhirawat.yourfamily;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
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

import com.google.gson.Gson;

import java.util.List;

public class ViewMemberActivity extends AppCompatActivity implements RecyclerViewForFamily.DeleteRowOnClick, RecyclerViewForFamily.ShowData, RecyclerViewForFamily.SetProfile {
    SharedPreferencesForMemberData memberData;
    List<MemberDataModel> list;
    RecyclerView memberList;
    ListView memberListView;
    String name[];
    String dummy[];
    MemberDataModel familData;
    RecyclerViewForFamily adapter;
    ImageView imageView;
    MemberDataModel memberDataModel;
    FamilyDao imageData;
    int imagePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_member);
        dummy = new String[]{"sorry no data found"};
        try {
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
        //       setViewVisibility();

        if (familData != null) {
            //memberListView.setVisibility(View.GONE);
            adapter = new RecyclerViewForFamily(this, list);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            memberList.setLayoutManager(mLayoutManager);
            memberList.setItemAnimator(new DefaultItemAnimator());
            memberList.setAdapter(adapter);
            adapter.notifyDataSetChanged();


        } else {
          /*  memberList.setVisibility(View.GONE);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dummy);
            memberListView.setAdapter(adapter);*/


        }

    }

    public void setViews() {
        memberList = (RecyclerView) findViewById(R.id.recyclerViewfamilyMember);
        // memberListView = (ListView) findViewById(R.id.listViewfamilyMember);
    }

    public void setViewVisibility() {
        memberList.setVisibility(View.GONE);
        memberListView.setVisibility(View.GONE);
    }

    public void setAdapter() {
      /*  ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, name);
        memberList.setAdapter(adapter);*/
    }

    public void Home(View view) {
        finish();
    }

    @Override
    public void deleteAt(int position) {
        final int pos = position;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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

    }

    @Override
    public void showDataAt(int position) {
        memberData = new SharedPreferencesForMemberData();
        String Data = memberData.getFamilyData(this);
        Gson gson = new Gson();
        FamilyDao data = gson.fromJson(Data, FamilyDao.class);
        MemberDataModel memberDataModel = data.getMemberFromListAt(position);
        Intent intent = new Intent(this, ShowMemberData.class);
        intent.putExtra("MemberDataModelView", memberDataModel);
        startActivity(intent);

    }

    @Override
    public void setProfilePicActivity(View view, int position, int id) {
        imagePosition = position;
        memberData = new SharedPreferencesForMemberData();
        String Data = memberData.getFamilyData(this);
        Gson gson = new Gson();
        imageData = gson.fromJson(Data, FamilyDao.class);
        memberDataModel = imageData.getMemberFromListAt(position);
        Intent galleryintent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryintent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bitmap bitmap = getPath(data.getData());
            imageData.setProfilePicAt(bitmap,imagePosition);
            memberData.setFamilyData(this,imageData);
        }
    }
    private Bitmap getPath(Uri uri) {

        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String filePath = cursor.getString(column_index);
        // cursor.close();
        // Convert file path into bitmap image using below line.
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        return bitmap;
    }
}


