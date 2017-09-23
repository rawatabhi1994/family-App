package com.example.abhirawat.yourfamily;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Abhi Rawat on 18-09-2017.
 */

public class FamilyDao implements Serializable {
    ArrayList<MemberDataModel> memberList = new ArrayList<>();

    public void setMemberListData(MemberDataModel data) {
        memberList.add(data);
    }

    public ArrayList<MemberDataModel> getMemberList() {
        return this.memberList;
    }

    public void deleteMemberFromList(int position) {
        this.memberList.remove(position);
    }

    public MemberDataModel getMemberFromListAt(int position) {
        return memberList.get(position);
    }
    public void setProfilePicAt(Bitmap bitmap, int pos)
    {
        MemberDataModel member = memberList.get(pos);
        member.setImageBitmap(bitmap);
    }
}
