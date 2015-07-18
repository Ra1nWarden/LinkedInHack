package com.teamsnapblock.snapblock;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zihao on 7/17/15.
 */
public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    public static final int REQUEST_IMAGE_CAPTURE = 1;
    private GridView gridView;
    private PhotosGridAdapter adapter;
    private ImageView cameraIcon;
    private ImageView refreshIcon;
    private String photoPath;

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
        setUpRefresh();
        addSamples();
    }

    private void setUpNewStory() {
        cameraIcon = (ImageView) findViewById(R.id.camera_icon);
        cameraIcon.setClickable(true);
        cameraIcon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String imageFileName = "JPEG_" + timeStamp + "_";
                File storageDir = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES);
                try {
                    File image = File.createTempFile(
                            imageFileName,  /* prefix */
                            ".jpg",         /* suffix */
                            storageDir      /* directory */
                    );
                    // Save a file: path for use with ACTION_VIEW intents
                    photoPath = "file:" + image.getAbsolutePath();
                    if(image != null) {
                        Log.d(TAG, "path is " + photoPath);
                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                                Uri.fromFile(image));
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                    }
                } catch (Exception e) {
                    return;
                }
            }
        });
    }

    private void setUpRefresh() {
        refreshIcon = (ImageView) findViewById(R.id.refresh_icon);
        refreshIcon.setClickable(true);
        refreshIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Refresh
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
        Log.d(TAG, "request is " + requestCode + " results is " + resultCode + " data is null ? " + (data == null));
        if (resultCode == Activity.RESULT_OK) {
            Log.d(TAG, "result ok");
            try {
                ExifInterface dataShit = new ExifInterface(photoPath);
                Bundle extras = data.getExtras();
                Intent storyIntent = new Intent(this, NewStoryActivity.class);
                storyIntent.putExtras(extras);
                float[] results = new float[2];
                if (dataShit.getLatLong(results)) {
                    storyIntent.putExtra("longitude", results[1]);
                    storyIntent.putExtra("latitude", results[0]);
                    startActivity(storyIntent);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

        }
    }


}
