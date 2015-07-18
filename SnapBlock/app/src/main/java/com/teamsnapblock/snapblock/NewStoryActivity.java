package com.teamsnapblock.snapblock;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by zihao on 7/18/15.
 */
public class NewStoryActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.add_story);
        Bundle extras = getIntent().getExtras();
        ImageView preview = (ImageView) findViewById(R.id.cover_preview);
        preview.setImageBitmap((Bitmap) extras.get("data"));
    }

    public void done(View view) {
        EditText titleField = (EditText) findViewById(R.id.title_field);
        String enteredText = titleField.getText().toString();
        // send to server.
        finish();
    }

}
