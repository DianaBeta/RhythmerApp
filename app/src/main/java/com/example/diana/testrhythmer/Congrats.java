package com.example.diana.testrhythmer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class Congrats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats);
    }
    public void backHome(View view) {
        // Do something in response to button; goes to the screen where you can choose the levels beginner and Advanced unblocked as the user just finished beginner
        Intent intent = new Intent(this, loginAdvanced.class);
        startActivity(intent);
    }

    public void nextLevel(View view) {
        // Do something in response to button; goes to the next nevel in this case Advanced
        Intent intent = new Intent(this, AdvancedActivity.class);
        startActivity(intent);
    }

}
