package com.teamsnapblock.snapblock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by zihao on 7/18/15.
 */
public class LoginActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.login);
    }

    public void login(View view) {
        EditText userNameField = (EditText) findViewById(R.id.username);
        String userName = userNameField.getText().toString();
        EditText passwordField = (EditText) findViewById(R.id.password);
        String password = passwordField.getText().toString();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
