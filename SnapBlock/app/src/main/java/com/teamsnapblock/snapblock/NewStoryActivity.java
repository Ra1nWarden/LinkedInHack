package com.teamsnapblock.snapblock;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by zihao on 7/18/15.
 */
public class NewStoryActivity extends Activity {

    Bitmap cover;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.add_story);
        ImageView preview = (ImageView) findViewById(R.id.cover_preview);
        Bundle bundle = getIntent().getExtras();
        byte[] bytes = bundle.getByteArray("photo");
        cover = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        preview.setImageBitmap(cover);
    }

    public void done(View view) {
        EditText titleField = (EditText) findViewById(R.id.title_field);
        String enteredText = titleField.getText().toString();
        float lat = getIntent().getFloatExtra("lat", -1);
        float lon = getIntent().getFloatExtra("lon", -1);
        RestClientUser.createStory(cover, lon, lat, enteredText);
        // send to server.
        finish();
    }

}
