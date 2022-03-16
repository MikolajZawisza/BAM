package com.example.laboratorium_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class UserActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        String mess = getIntent().getStringExtra("data");
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();

    }
}