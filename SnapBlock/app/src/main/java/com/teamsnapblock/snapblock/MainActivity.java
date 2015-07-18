package com.teamsnapblock.snapblock;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by zihao on 7/17/15.
 */
public class MainActivity extends Activity {

    private GridView gridView;
    private PhotosGridAdapter adapter;
    private ImageView cameraIcon;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.main);
        gridView = (GridView) findViewById(R.id.photos_grid);
        adapter = new PhotosGridAdapter(this, R.layout.photo_grid);
        gridView.setAdapter(adapter);
        setUpNewStory();
        addSamples();
    }

    private void setUpNewStory() {
        cameraIcon = (ImageView) findViewById(R.id.camera_icon);
        cameraIcon.setClickable(true);
        cameraIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewStoryActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addSamples() {
        Drawable sampleDrawable = getResources().getDrawable(R.drawable.green_mushroom, null);
        for (int i = 0; i < 9; i++) {
            adapter.add(sampleDrawable);
        }
    }



}
