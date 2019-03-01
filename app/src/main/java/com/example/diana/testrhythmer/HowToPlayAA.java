package com.example.diana.testrhythmer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HowToPlayAA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play_a);
    }
    public void ToFingerScreenAA(View view) {
        // Do something in response to button
        Intent intent = new Intent(this,fingerScreenAA.class);
        startActivity(intent);
    }

}
