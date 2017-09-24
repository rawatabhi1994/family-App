package com.example.abhirawat.yourfamily;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Abhi Rawat on 24-09-2017.
 */

public class RecyclerViewAdapterForImages extends RecyclerView.Adapter<RecyclerViewAdapterForImages.ViewHolder> {
    Context context;
    List<ImageUploadInfo> MainImageUploadInfoList;

    public RecyclerViewAdapterForImages(Context context, List<ImageUploadInfo> mainImageUploadInfoList) {
        this.context = context;
        this.MainImageUploadInfoList = mainImageUploadInfoList;
    }

    @Override
    public RecyclerViewAdapterForImages.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.downloaded_images_for_recycler_view, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterForImages.ViewHolder holder, int position) {
        ImageUploadInfo UploadInfo = MainImageUploadInfoList.get(position);
        Glide.with(context).load(UploadInfo.getImageURL()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return MainImageUploadInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.downloadedimageView);
        }
    }

}
