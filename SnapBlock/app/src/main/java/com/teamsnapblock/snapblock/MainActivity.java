package com.teamsnapblock.snapblock;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by zihao on 7/17/15.
 */
public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    public static final int REQUEST_IMAGE_CAPTURE = 1;
    private GridView gridView;
    private PhotosGridAdapter adapter;
    private ImageView cameraIcon;

    @Override
    public void onCreate(Bundle savedInstance) {
        Log.d(TAG, "onCreate()");
        super.onCreate(savedInstance);
        setContentView(R.layout.main);
        gridView = (GridView) findViewById(R.id.photos_grid);
        adapter = new PhotosGridAdapter(this, R.layout.photo_grid);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemSelected");
                Intent startStoryIntent = new Intent(MainActivity.this, StoryActivity.class);
                startActivity(startStoryIntent);
            }
        });
        setUpNewStory();
        addSamples();
    }

    private void setUpNewStory() {
        cameraIcon = (ImageView) findViewById(R.id.camera_icon);
        cameraIcon.setClickable(true);
        cameraIcon.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
    }

    private void addSamples() {
        Drawable sampleDrawable = getResources().getDrawable(R.drawable.green_mushroom, null);
        for (int i = 0; i < 9; i++) {
            adapter.add(sampleDrawable);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_IMAGE_CAPTURE) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        if (resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Intent storyIntent = new Intent(this, NewStoryActivity.class);
            storyIntent.putExtras(extras);
            startActivity(storyIntent);
        }
    }


}
