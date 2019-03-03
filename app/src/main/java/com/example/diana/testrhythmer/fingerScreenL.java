package com.example.diana.testrhythmer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class fingerScreenL extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_screen);
    }

    public void backHomeL(View view) {
        // Do something in response to button;from the help screen goes back to loginActivity where you can choose the levels, beginner unblocked
        Intent intent = new Intent(this,loginActivity.class);
        startActivity(intent);
    }


}
