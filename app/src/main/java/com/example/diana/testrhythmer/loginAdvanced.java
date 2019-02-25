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

    public void howTo(View view) {
        // Do something in response to button-go to howTo activity
        Intent intent = new Intent(this, metromome.class);
        startActivity(intent);

    }

    private void displayName() {

        SharedPreferences sp = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String name = sp.getString(KEY_NAME, null);

        if (name != null) {
            textView.setText(String.format("%s %s ,\nget ready to train your rhythm skills!\nHave fun!", getString(R.string.welcomeToTheGame), name));

        }
    }
}
