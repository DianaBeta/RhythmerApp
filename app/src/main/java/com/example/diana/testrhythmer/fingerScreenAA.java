package com.example.diana.testrhythmer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class fingerScreenAA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_screen_a);
    }
    public void backToGameAA(View view) {
        // Do something in response to button;from the help screen goes back to advanced activity
        Intent intent = new Intent(this, AdvancedActivity.class);
        startActivity(intent);
    }
}
