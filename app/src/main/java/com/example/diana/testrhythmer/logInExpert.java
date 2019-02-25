package com.example.diana.testrhythmer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class logInExpert extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_expert);
    }

    public void howTo(View view) {
        // Do something in response to button-go to howTo activity
        Intent intent = new Intent(this, metromome.class);
        startActivity(intent);

    }
}
