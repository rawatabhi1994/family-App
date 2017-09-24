package com.example.abhirawat.yourfamily;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DataActivity extends AppCompatActivity implements View.OnClickListener {
    private CircleImageView addMember;
    private CircleImageView viewMember;
    private CircleImageView addPhotos;
    private ListView leftDrawableList;
    private DrawerLayout leftDrawable;
    private ActionBarDrawerToggle leftDrawableToggleListener;
    private LeftDrawableAdapter leftDrawableAdapter;
    private int[] icon;
    String NAME = "Akash Bangad";
    String EMAIL = "akash.bangad@android4devs.com";
    int PROFILE = R.drawable.add_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewMember = (CircleImageView) findViewById(R.id.circleViewMember);
        addMember = (CircleImageView) findViewById(R.id.circleViewAddMember);
        addPhotos = (CircleImageView) findViewById(R.id.circleViewAddPhotos);
        addPhotos.setOnClickListener(this);
        viewMember.setOnClickListener(this);
        addMember.setOnClickListener(this);
        icon = new int[]{R.drawable.myaccount, R.drawable.save, R.drawable.rateus, R.drawable.logout};
        leftDrawable = (DrawerLayout) findViewById(R.id.drawer_layout);
        leftDrawableList = (ListView) findViewById(R.id.list_left_drawer);
        setListeAdapterForDrawableList();
        leftDrawableToggleListener = new ActionBarDrawerToggle(this, leftDrawable, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        leftDrawableList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 1) {
                    Intent intent = new Intent(DataActivity.this, MyAccountActivity.class);
                    startActivity(intent);
                } else if (i == 2) {
                    Intent intent = new Intent(DataActivity.this, AboutActivity.class);
                    startActivity(intent);
                } else if (i == 4) {
                    Intent intent = new Intent(DataActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
        leftDrawable.setDrawerListener(leftDrawableToggleListener);
    }

    public void setListeAdapterForDrawableList() {
        List<LeftDrawableClass> list = new ArrayList<LeftDrawableClass>();
        LeftDrawableClass obj1 = new LeftDrawableClass();
        obj1.setLeftDrawableText("MyAccount");
        obj1.setLeftDrawableIcon(icon[0]);
        list.add(obj1);
        LeftDrawableClass obj2 = new LeftDrawableClass();
        obj2.setLeftDrawableText("About Us");
        obj2.setLeftDrawableIcon(icon[1]);
        list.add(obj2);
        LeftDrawableClass obj3 = new LeftDrawableClass();
        obj3.setLeftDrawableText("RateUs");
        obj3.setLeftDrawableIcon(icon[2]);
        list.add(obj3);
        LeftDrawableClass obj4 = new LeftDrawableClass();
        obj4.setLeftDrawableText("Logout");
        obj4.setLeftDrawableIcon(icon[3]);
        list.add(obj4);
        View view = getLayoutInflater().inflate(R.layout.header, leftDrawableList, false);
        leftDrawableList.addHeaderView(view);
        leftDrawableList.setAdapter(new LeftDrawableAdapter(this, list));
    }

    public void next(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        leftDrawableToggleListener.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        leftDrawableToggleListener.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (leftDrawableToggleListener.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.circleViewMember:
                Intent intentViewMember = new Intent(this, ViewMemberActivity.class);
                startActivity(intentViewMember);
                break;
            case R.id.circleViewAddMember:
                Intent intentAddMember = new Intent(this, AddMemberActivity.class);
                startActivity(intentAddMember);
                break;
            case R.id.circleViewAddPhotos:
                Intent intent = new Intent(this, ViewAlbumActivity.class);
                startActivity(intent);
                break;
        }
    }
}
