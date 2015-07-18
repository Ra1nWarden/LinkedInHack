package com.teamsnapblock.snapblock;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.GridView;

/**
 * Created by zihao on 7/17/15.
 */
public class MainActivity extends Activity {

    private GridView gridView;
    private PhotosGridAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.main);
        gridView = (GridView) findViewById(R.id.photos_grid);
        adapter = new PhotosGridAdapter(this, R.layout.photo_grid);
        gridView.setAdapter(adapter);
        addSamples();
    }

    private void addSamples() {
        Drawable sampleDrawable = getResources().getDrawable(R.drawable.green_mushroom, null);
        for (int i = 0; i < 9; i++) {
            adapter.add(sampleDrawable);
        }
    }



}
