package com.example.diana.testrhythmer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class loginAdvanced extends AppCompatActivity {
    private TextView textView;
    private static final String SHARED_PREF_NAME = "username";
    private static final String KEY_NAME = "key_username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_advanced);
    }

    public void howToLA(View view) {
        // Do something in response to button-go to howTo activity
        Intent intent = new Intent(this,metronomeLA.class);
        startActivity(intent);
    }

    public void backtoBeginner(View view){
        Intent intent = new Intent(this, beginnerActivity.class);
        startActivity(intent);
        textView= findViewById(R.id.startA);
        textView.setVisibility(View.INVISIBLE);
    }

    public void toAdvanced(View view){
        Intent intent = new Intent(this, AdvancedActivity.class);
        startActivity(intent);
        textView= findViewById(R.id.startA);
        textView.setVisibility(View.INVISIBLE);
    }

    public void onExpert(View view) {
        textView= findViewById(R.id.startA);
        textView.setVisibility(View.VISIBLE);
    }
}

