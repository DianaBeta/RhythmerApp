package com.example.diana.testrhythmer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class loginActivity extends AppCompatActivity {
    private TextView textView;
    private static final String SHARED_PREF_NAME="username";
    private static final String KEY_NAME="key_username";

    public void onAdvanced(View view) {
        //display a message that clarifies you can not start Advanced level without doing Beginner
        textView= findViewById(R.id.startB);
        textView.setVisibility(View.VISIBLE);
    }

    public void onExpert(View view) {
        //display a message that clarifies you can not start Expert level without doing Beginner and Advanced
        textView= findViewById(R.id.startB);
        textView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textView= findViewById(R.id.textView5);
        displayName();//this method displays the name previously saved in the MainActivity with the method "SaveName()"
    }

    //Called when the user taps the howTo button and send the user to how to play
    public void howToL(View view) {
        // Do something in response to button; goes to the first of the how to play screens
        Intent intent = new Intent(this, metromomeL.class);
        startActivity(intent);
    }

    public void beginner(View view) {
        textView= findViewById(R.id.startB);
        textView.setVisibility(View.INVISIBLE);
        // Do something in response to button-the button sends you to beginner activity
        Intent intent = new Intent(this, beginnerActivity.class);
        startActivity(intent);
    }

    private void displayName(){ //this method displays the name previously saved in the MainActivity with the method "SaveName()";
        SharedPreferences sp= getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String name= sp.getString(KEY_NAME,null);
        if(name !=null){
            textView.setText(String.format("%s %s ,\nget ready to train your rhythm skills!\nHave fun!", getString(R.string.welcomeToTheGame), name));
        }
    }
}

