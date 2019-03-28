package com.example.diana.testrhythmer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class Congrats2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats2);
    }
    public void backHome2(View view) {
        // Do something in response to button; goes to the screen where you can choose the levels beginner, Advanced and Expert unblocked as the user just finished Advanced and can start with Expert
        Intent intent = new Intent(this, logInExpert.class);
        startActivity(intent);
    }

    public void nextLevel2(View view) {
        // Do something in response to button; goes to the next nevel in this case Expert
        Intent intent = new Intent(this, ExpertActivity.class);
        startActivity(intent);
    }

}
