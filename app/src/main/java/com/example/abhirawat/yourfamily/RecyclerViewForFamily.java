package com.example.abhirawat.yourfamily;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Abhi Rawat on 18-09-2017.
 */

public class RecyclerViewForFamily extends RecyclerView.Adapter<RecyclerViewForFamily.ViewHolder> {
    private List<MemberDataModel> memberList;
    private Context context;
    private DeleteRowOnClick deleteRowOnClick;
    private ShowData showData;
    private SetProfile setProfile;

    public RecyclerViewForFamily(Context context, List<MemberDataModel> memberList) {
        this.context = context;
        this.memberList = memberList;
        this.deleteRowOnClick = (RecyclerViewForFamily.DeleteRowOnClick) context;
        this.showData = (RecyclerViewForFamily.ShowData) context;
        this.setProfile = (RecyclerViewForFamily.SetProfile) context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.family_member_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Bitmap bitmap = null;
        MemberDataModel memberDataModel = memberList.get(position);
        holder.Name.setText(memberDataModel.getFirstName() + " " + memberDataModel.getMiddleName() + "  " + memberDataModel.getLastName());
        holder.Dob.setText(memberDataModel.getDob());
        Address address = memberDataModel.getAddress();
        holder.Address.setText(address.getHouseAdress() + " \n  " + address.getState() + " \n  " + address.getPincode());
        ContactInfo contact = memberDataModel.getContact();
        holder.Contact.setText(contact.getPhoneNumber1() + "  \n " + contact.getPhoneNumber2());
        if ((bitmap = memberDataModel.getImageBitmap()) != null) {
            holder.profile.setImageBitmap(bitmap);
        } else {
        }
    }


    @Override
    public int getItemCount() {
        return memberList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CircleImageView profile, delete, edit, view;
        private TextView Name;
        private TextView Dob;
        private TextView Address;
        private TextView Contact;

        public ViewHolder(View itemView) {
            super(itemView);
            profile = (CircleImageView) itemView.findViewById(R.id.imageProfile);
            delete = (CircleImageView) itemView.findViewById(R.id.imageTrash);
            edit = (CircleImageView) itemView.findViewById(R.id.imageEdit);
            view = (CircleImageView) itemView.findViewById(R.id.imageView);
            Name = (TextView) itemView.findViewById(R.id.tviewName);
            Dob = (TextView) itemView.findViewById(R.id.tviewDOB);
            Address = (TextView) itemView.findViewById(R.id.tviewAddress);
            Contact = (TextView) itemView.findViewById(R.id.tviewContactInfo);
            delete.setOnClickListener(this);
            view.setOnClickListener(this);
            profile.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.imageTrash:
                    deleteRowOnClick.deleteAt(getAdapterPosition());
                    break;
                case R.id.imageView:
                    showData.showDataAt(getAdapterPosition());
                    break;
                case R.id.imageProfile:
                    setProfile.setProfilePicActivity(view, getAdapterPosition(), view.getId());
                    break;
            }
        }
    }

    public interface DeleteRowOnClick {
        void deleteAt(int position);
    }

    public interface ShowData {
        void showDataAt(int position);
    }

    public interface SetProfile {
        void setProfilePicActivity(View view, int position, int id);
    }
}
