package com.example.diana.testrhythmer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class congrats3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats3);
    }
    public void backHome2(View view) {
        // Do something in response to button; goes to the screen where you can choose the levels, all levels unblocked as the user finished the game
        Intent intent = new Intent(this, loginDone.class);
        startActivity(intent);
    }

}
