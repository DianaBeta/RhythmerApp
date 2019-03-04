package com.example.diana.testrhythmer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

private EditText editText;

boolean empty = false;

private static final String SHARED_PREF_NAME="username";
private static final String KEY_NAME="key_username";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText= findViewById(R.id.editText);
    }

    public void saveName(){ // saves the name of nickname of the user, throws and error if the user does not type anything
        String name = editText.getText().toString().trim();
        empty = false;

        if (name.isEmpty()){
            empty = true;
            editText.setError("Please fill in your name!");
            editText.requestFocus();
        }

        SharedPreferences sp= getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        SharedPreferences.Editor e= sp.edit();
        e.putString(KEY_NAME, name);
        e.apply();
        editText.setText("");
    }

    /** Called when the user taps the logIn button and sends him to logIn activity which is the activity where they choose their mode(beginner,advanced etc) */
    public void logIn(View view) {
        // Do something in response to button the sends the user to logIn activity which is the activity where they choose their mode(beginner,advanced etc

        Intent intent = new Intent(this,loginActivity.class);
       saveName();


        if (empty) {// if the name is empty is throws an error
            editText.setError("Please fill in your name!");
            editText.requestFocus();

        } else {// otherwise it starts the activity
            startActivity(intent);
        }
    }
}
