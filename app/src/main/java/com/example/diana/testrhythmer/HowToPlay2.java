package com.example.diana.testrhythmer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HowToPlay2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play2);
    }

    public void fingerBeginner(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, fingerScreen.class);
        startActivity(intent);
    }

}