package com.example.diana.testrhythmer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class metronome_b extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metronome_b);
    }

    public void next3(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, how_to_play2_b.class);
        startActivity(intent);
    }

}
