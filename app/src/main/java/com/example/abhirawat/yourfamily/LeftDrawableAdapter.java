package com.example.abhirawat.yourfamily;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Abhi Rawat on 20-09-2017.
 */

public class LeftDrawableAdapter extends BaseAdapter {
    List<LeftDrawableClass> list;
    Context context;
    private static final int TYPE_HEADER = 0;  // Declaring Variable to Understand which View is being worked on
    // IF the view under inflation and population is header or Item
    private static final int TYPE_ITEM = 1;
    private String name;        //String Resource for header View Name
    private int profile;        //int Resource for header view profile picture
    private String email;

    public LeftDrawableAdapter(Context context, List<LeftDrawableClass> list) {
        this.list = list;
        this.context = context;
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)

                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config);
        ImageLoaderConfiguration imageConfig = new ImageLoaderConfiguration.Builder(context).build();
        ImageLoader.getInstance().init(imageConfig);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return 1;
        else return 2;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.left_drawable, null);
            holder = new ViewHolder();
            holder.leftDrawableImage = (ImageView) view.findViewById(R.id.left_drawable_icon);
            holder.leftDrawableTextView = (TextView) view.findViewById(R.id.left_drawable_textView);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();

        }
        LeftDrawableClass leftDrawableInstance = list.get(i);
        holder.leftDrawableTextView.setText(leftDrawableInstance.getLeftDrawableText());
        holder.leftDrawableImage.setImageResource(leftDrawableInstance.getLeftDrawableIcon());
        return view;
    }

    class ViewHolder {
        private TextView leftDrawableTextView;
        private ImageView leftDrawableImage;
    }
}
