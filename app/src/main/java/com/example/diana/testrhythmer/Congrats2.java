package com.example.diana.testrhythmer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Congrats2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats2);
    }
    public void backHome2(View view) {
        // Do something in response to button-goes to the other screen of how to play metromome
        Intent intent = new Intent(this, logInExpert.class);
        startActivity(intent);
    }

    public void nextLevel2(View view) {
        // Do something in response to button-goes to the other screen of how to play metromome
        Intent intent = new Intent(this, ExpertActivity.class);
        startActivity(intent);
    }

}
