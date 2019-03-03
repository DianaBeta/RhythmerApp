package com.example.diana.testrhythmer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class loginDone extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_done);
    }

    public void howToLD(View view) {
        // Do something in response to button-go to howTo activity
        Intent intent = new Intent(this,metronomeLD.class);
        startActivity(intent);
    }


    public void backtoBeginner(View view){
        Intent intent = new Intent(this, beginnerActivity.class);
        startActivity(intent);
    }

    public void backtoAdvanced(View view){
        Intent intent = new Intent(this, AdvancedActivity.class);
        startActivity(intent);
    }

    public void toExpert(View view){
        Intent intent = new Intent(this, ExpertActivity.class);
        startActivity(intent);
    }
}
