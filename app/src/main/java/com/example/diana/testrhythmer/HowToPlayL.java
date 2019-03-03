package com.example.diana.testrhythmer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HowToPlayL extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play2);
    }

    public void ToFingerScreenL(View view) {
        // Do something in response to button; goes to one of the how to play screens
        Intent intent = new Intent(this,fingerScreenL.class);
        startActivity(intent);
    }














}