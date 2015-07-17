package com.example.tenorio.myapplication3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by tenorio on 7/7/2015.
 */
public class ImageAdapter extends BaseAdapter{
    private Context mContext;
    private String[] mImages;
    public ImageAdapter(Context c, String[] i) {
        mContext = c;
        mImages = i;
    }

    public int getCount() {
        return mImages.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView;
        if (convertView == null) {

            imageView = new ImageView(mContext);


        } else {
            imageView = (ImageView) convertView;
        }

        Picasso.with(mContext).load(mImages[position]).placeholder(R.drawable.placeholder).error(R.drawable.sorry).resize(200,200).noFade().centerCrop().into(imageView);

        return imageView;
    }

}
