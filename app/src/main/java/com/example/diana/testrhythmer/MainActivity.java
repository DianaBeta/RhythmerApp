package com.example.diana.testrhythmer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
public static final String EXTRA_MESSAGE= "com.example.TestRhythmer.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /** Called when the user taps the logIn button and sends him to logIn activity which is the activity where they choose their mode(beginner,advanced etc) */
    public void logIn(View view) {
        // Do something in response to button
        Intent intent = new Intent(this,loginActivity.class);
        startActivity(intent);
    }
}
