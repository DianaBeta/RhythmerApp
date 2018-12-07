package com.example.diana.testrhythmer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class beginnerActivity extends AppCompatActivity {
MediaPlayer beginnerSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginner);
        beginnerSong=MediaPlayer.create(beginnerActivity.this,R.raw.beginner);
    }

    public void backtoBeginner(View view) {
        // Do something in response to button goes to beginner activity
        Intent intent = new Intent(this, beginnerActivity.class);
        startActivity(intent);
    }

    public void home(View view) {
        // Do something in response to button-goes to home which is login activity
        Intent intent = new Intent(this, loginActivity.class);
        startActivity(intent);
    }

    public void howToPlay(View view) {
        // Do something in response to button-goes to the other screen of how to play HowToPlay2
        Intent intent = new Intent(this, HowToPlay2.class);
        startActivity(intent);
    }
    public void play(View view) {
        // play the song
        beginnerSong.start();
    }

    protected void onPause(){
        // pause the song
        super.onPause();
        beginnerSong.release();

    }

}
