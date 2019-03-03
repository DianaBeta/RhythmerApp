package com.example.diana.testrhythmer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class loginAdvanced extends AppCompatActivity {
    private TextView textView;


    public void onExpert(View view) {
        //display a message that clarifies you can not start Expert level without doing Advanced and Beginner first
        textView= findViewById(R.id.startA);
        textView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_advanced);
    }

    public void howToLA(View view) {
        // Do something in response to button; goes to the first of the how to play screens
        Intent intent = new Intent(this,metronomeLA.class);
        startActivity(intent);
    }

    public void backtoBeginner(View view){
        // Do something in response to button; goes back to beginner activity
        Intent intent = new Intent(this, beginnerActivity.class);
        startActivity(intent);
        textView= findViewById(R.id.startA);
        textView.setVisibility(View.INVISIBLE);
    }

    public void toAdvanced(View view){
        // Do something in response to button; goes to advanced activity
        Intent intent = new Intent(this, AdvancedActivity.class);
        startActivity(intent);
        textView= findViewById(R.id.startA);
        textView.setVisibility(View.INVISIBLE);
    }
}

