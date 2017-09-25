package com.example.abhirawat.yourfamily;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Created by Abhi Rawat on 18-09-2017.
 */

public class SharedPreferencesForMemberData {
    private static final String MEMBER_DATA = "FAMILY_MODEL";
    private static final String MEMBER = "FAMILY_MEMBER";
    private static final String ACCOUNT_HOLDER = "ACCOUNT_HOLDER";

    public void setFamilyData(Context context, FamilyDao dao) {
        Gson gson = new Gson();
        String familyData = gson.toJson(dao);
        SharedPreferences sharedPref = context.getSharedPreferences(MEMBER_DATA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(MEMBER, familyData);
        editor.commit();

    }

    public String getFamilyData(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(MEMBER_DATA, Context.MODE_PRIVATE);
        return sharedPref.getString(MEMBER, "");

    }

    public void clearMemberPreferences(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(MEMBER_DATA, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPref.edit();
        edit.clear();
    }

    public void clearUserAccount(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(MEMBER_DATA, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPref.edit();
        edit.putString(ACCOUNT_HOLDER, "");
        edit.commit();
    }

    public void setUserAccount(Context context, UserAccountClass user) {
        Gson gson = new Gson();
        String userData = gson.toJson(user);
        SharedPreferences sharedPreferences = context.getSharedPreferences(MEMBER_DATA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ACCOUNT_HOLDER, userData);
        editor.commit();

    }

    public String getUserAccount(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(MEMBER_DATA, Context.MODE_PRIVATE);
        return preferences.getString(ACCOUNT_HOLDER, "");
    }
}
