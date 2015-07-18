package com.teamsnapblock.snapblock;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.zip.Inflater;

/**
 * Created by zihao on 7/17/15.
 */
public class PhotosGridAdapter extends ArrayAdapter<Bitmap> {

    private final Context context;

    public PhotosGridAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout photoViewHolder = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.photo_grid, parent, false);
        ImageView photoView = (ImageView) photoViewHolder.findViewById(R.id.photo);
        photoView.setImageBitmap(getItem(position));
        return photoView;
    }
}
