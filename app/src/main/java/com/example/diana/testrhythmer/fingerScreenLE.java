package com.example.diana.testrhythmer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class fingerScreenLE extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_screen_le);
    }
    public void backHomeLE(View view) {
        // Do something in response to button-goes back to beginner activity
        Intent intent = new Intent(this,logInExpert.class);
        startActivity(intent);
    }
}
