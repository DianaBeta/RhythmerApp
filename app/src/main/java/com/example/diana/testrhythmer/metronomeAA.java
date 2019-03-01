package com.example.diana.testrhythmer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class metronomeAA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metronome_a);
    }
    public void nextAA(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, HowToPlayAA.class);
        startActivity(intent);
    }
}
