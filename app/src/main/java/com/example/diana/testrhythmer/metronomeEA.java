package com.example.diana.testrhythmer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class metronomeEA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metronome_e);
    }

    public void nextEA(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, HowToPlayEA.class);
        startActivity(intent);
    }
}
