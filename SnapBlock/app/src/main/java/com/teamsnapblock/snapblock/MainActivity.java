package com.teamsnapblock.snapblock;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.teamsnapblock.snapblock.model.Story;
import com.teamsnapblock.snapblock.model.StoryList;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
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
        StoryList storyList = RestClientUser.getStoryList();
        for (Story story : storyList.getStories()) {
            Log.d(TAG, "size is " + story.getUrls().size());
            if(story.getUrls().size() > 0) {
                adapter.add(story.getUrls().get(0));
            }
        }
    }

    private void setUpNewStory() {
        cameraIcon = (ImageView) findViewById(R.id.camera_icon);
        cameraIcon.setClickable(true);
        cameraIcon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
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

//    private void addSamples() {
//        Drawable sampleDrawable = getResources().getDrawable(R.drawable.green_mushroom, null);
//        for (int i = 0; i < 9; i++) {
//            adapter.add(sampleDrawable);
//        }
//    }

    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode != REQUEST_IMAGE_CAPTURE) {
//            super.onActivityResult(requestCode, resultCode, data);
//            return;
//        }
//        Log.d(TAG, "request is " + requestCode + " results is " + resultCode + " data is null ? " + (data == null));
//        if (resultCode == Activity.RESULT_OK) {
//            Log.d(TAG, "result ok");
//            try {
//                ExifInterface dataShit = new ExifInterface(photoPath);
//                if (data != null) {
//                    Bundle extras = data.getExtras();
//                }
//                Intent storyIntent = new Intent(this, NewStoryActivity.class);
//                //storyIntent.putExtras(extras);
//                float[] results = new float[2];
//                if (dataShit.getLatLong(results)) {
//                    storyIntent.putExtra("longitude", results[1]);
//                    storyIntent.putExtra("latitude", results[0]);
//                    startActivity(storyIntent);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//                return;
//            }
//
//        }

        if(data == null)
            return;

        Uri pickedPhoto = data.getData();
        try {
            Log.d(TAG, "I AM ALIVE");
            String filePath = getRealPathFromURI(this, pickedPhoto);
            ExifInterface dataShit = new ExifInterface(filePath);
            float[] results = new float[2];
            Log.d(TAG, "before if");
            if (dataShit.getLatLong(results)) {
                Log.d(TAG, "lat is " + results[0]);
                Log.d(TAG, "lon is " + results[1]);
                Bitmap photoData = BitmapFactory.decodeFile(filePath);
                Intent newStoryIntent = new Intent(this, NewStoryActivity.class);
                Bundle args = new Bundle();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                photoData.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);
                args.putByteArray("photo", outputStream.toByteArray());
                newStoryIntent.putExtras(args);
                newStoryIntent.putExtra("lat", results[0]);
                newStoryIntent.putExtra("lon", results[1]);
                Log.d(TAG, "before start");
                startActivity(newStoryIntent);
            } else {
                Log.d(TAG, "ERROR");
            }
        } catch (IOException e) {
            Log.d(TAG, "IO Exception");
            e.printStackTrace();
        }


    }


}
