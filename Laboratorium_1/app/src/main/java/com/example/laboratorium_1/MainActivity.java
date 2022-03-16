package com.example.laboratorium_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button buttonClickMe;
    EditText textInsertText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonClickMe = (Button) findViewById(R.id.buttonClickMe);
        textInsertText = (EditText) findViewById(R.id.InsertText);

        buttonClickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredData = textInsertText.getText().toString();
                Intent intent = new Intent (MainActivity.this, UserActivity.class);
                intent.putExtra("data", enteredData);
                startActivity(intent);
            }
        });
    }
}