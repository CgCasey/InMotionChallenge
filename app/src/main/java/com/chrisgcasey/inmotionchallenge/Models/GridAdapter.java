package com.chrisgcasey.inmotionchallenge.Models;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.chrisgcasey.inmotionchallenge.R;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 6/29/2016.
 */
public class GridAdapter extends ArrayAdapter<ImageItem> {
    //custom adapter for the messages listview

    //context member variable passed in from the constructor
    Context mContext;
    Animation animator;
    //list of parseObjects member variable passed in from the constructor
    List<String> mUrls;
    List<ImageItem> mImageItems;

    //create a constructor taking the context calling it, and the list in the parameters
    public GridAdapter(Context mContext, List<ImageItem> urls) {

        //call super() and pass in the context, the list item layout, and the list
        super(mContext, R.layout.image_item_layout, urls);
        this.mContext = mContext;
        this.mImageItems = urls;
        animator = AnimationUtils.loadAnimation(mContext, R.anim.grow);
        //this.mUrls = urls;
    }

    //overide the getView method
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.image_item_layout, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.thumbnail);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ImageItem item = mImageItems.get(position);
        String url = item.getThumbnail();
        //load the image with picasso
        Picasso.with(mContext).load(url).placeholder(R.drawable.placeholder)
                .into(holder.imageView);
        return convertView;
    }

    @Override
    public int getCount() {
        return mImageItems.size();
    }

    //implement the viewholder design pattern for performance
    private static class ViewHolder {
        ImageView imageView;
    }
    // inform adapter of data change and redraw gridview
    public void refill(List<ImageItem> urls) {
        mImageItems.clear();
        mImageItems.addAll(urls);
        this.notifyDataSetChanged();

    }


}
