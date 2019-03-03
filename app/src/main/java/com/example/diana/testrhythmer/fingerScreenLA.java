package com.example.diana.testrhythmer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class fingerScreenLA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_screen_l);
    }
    public void backHomeLA(View view) {
        // Do something in response to button;from the help screen goes back to loginActivity where you can choose the levels, beginner and advanced unblocked
        Intent intent = new Intent(this,loginAdvanced.class);
        startActivity(intent);
    }
}
