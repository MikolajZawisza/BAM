package com.example.laboratorium_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UserActivity extends Activity {

    Button startService;
    Button stopService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        startService = (Button) findViewById(R.id.StartService);
        stopService = (Button) findViewById(R.id.StopService);

        String mess = getIntent().getStringExtra("data");
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();

        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent service = new Intent(UserActivity.this, SimpleService.class);
                startService(service);
            }
        });

        stopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent service = new Intent(UserActivity.this, SimpleService.class);
                stopService(service);
            }
        });


    }
}