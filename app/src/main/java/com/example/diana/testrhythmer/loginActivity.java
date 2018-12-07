package com.example.diana.testrhythmer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class loginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

    /**
     * Called when the user taps the howTo button and send the user to how to play
     */
    public void howTo(View view) {
        // Do something in response to button-go to howTo activity
        Intent intent = new Intent(this, howTo.class);
        startActivity(intent);

    }

    public void beginner(View view) {
        // Do something in response to button-the button sends you to beginner activity
        Intent intent = new Intent(this, beginnerActivity.class);
        startActivity(intent);


    }
}
