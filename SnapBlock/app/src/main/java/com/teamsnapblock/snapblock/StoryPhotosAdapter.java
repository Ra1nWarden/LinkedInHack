package com.teamsnapblock.snapblock;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zihao on 7/17/15.
 */
public class StoryPhotosAdapter extends RecyclerView.Adapter<StoryPhotosAdapter.PhotoViewHolder> {

    private List<Drawable> photos;

    public StoryPhotosAdapter() {
        photos = new ArrayList<>();
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        FrameLayout view = (FrameLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.story_photo_grid, viewGroup, false);
        PhotoViewHolder holder = new PhotoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder photoViewHolder, int i) {
        photoViewHolder.actualPhoto.setImageDrawable(photos.get(i));
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {

        FrameLayout userPhotoHolder;
        ImageView actualPhoto;
        ImageView coverPhoto;

        public PhotoViewHolder(FrameLayout view) {
            super(view);
            userPhotoHolder = view;
            actualPhoto = (ImageView) view.findViewById(R.id.story_photo);
            coverPhoto = (ImageView) view.findViewById(R.id.cover_photo);
        }
    }

    public void addPhoto(Drawable photo) {
        photos.add(photo);
        notifyDataSetChanged();
    }
}
