package com.example.diana.testrhythmer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class loginDone extends AppCompatActivity {
    private TextView textView;
    private static final String SHARED_PREF_NAME = "username";
    private static final String KEY_NAME = "key_username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_done);
        textView = findViewById(R.id.textViewGoodJob2);
        displayName();
    }

    public void howToLD(View view) {
        // Do something in response to button; goes to the first of the how to play screens
        Intent intent = new Intent(this, metronomeLD.class);
        startActivity(intent);
    }


    public void backtoBeginner(View view) {
        // Do something in response to button; goes to back to beginner activity
        Intent intent = new Intent(this, beginnerActivity.class);
        startActivity(intent);
    }

    public void backtoAdvanced(View view) {
        // Do something in response to button; goes to back to advanced activity
        Intent intent = new Intent(this, AdvancedActivity.class);
        startActivity(intent);
    }

    public void toExpert(View view) {
        // Do something in response to button; goes back to Expert activity
        Intent intent = new Intent(this, ExpertActivity.class);
        startActivity(intent);
    }

    private void displayName() { //this method displays the name previously saved in the MainActivity with the method "SaveName()";
        SharedPreferences sp = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String name = sp.getString(KEY_NAME, null);
        if (name != null) {
            textView.setText(String.format("%s %s \nYou completed all the levels!\nYou can choose a level to play again!", getString(R.string.goodJob), name));
        }

    }

}
