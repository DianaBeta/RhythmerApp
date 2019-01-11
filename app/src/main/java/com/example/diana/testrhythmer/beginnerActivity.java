package com.example.diana.testrhythmer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class beginnerActivity extends AppCompatActivity {
    MediaPlayer beginnerSong;

    ArrayList<Long> reference_beat = new ArrayList<Long>();
    ArrayList<Long> user_beat = new ArrayList<Long>();
    ArrayList<Long> real_beat = new ArrayList<Long>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginner);
        beginnerSong = MediaPlayer.create(beginnerActivity.this, R.raw.beginner_new);


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

       //reaching the active imageButton (play)
        ImageButton imgButton = findViewById(R.id.imageButton);

        imgButton.setImageResource(android.R.drawable.ic_media_play);
        //deactivate the button after first play - for later

        //What should happen when the song is over for the first time? -> all comes here
        beginnerSong.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

                //adding the start game button after song finishes for the first time



                Button start_button = findViewById(R.id.startGame);
                start_button.setVisibility(View.VISIBLE);

                //changing the play button to replay after song finishes
                ImageButton imgButton = findViewById(R.id.imageButton);
                imgButton.setImageResource(R.drawable.replay);

            }
        });

    }

    public void playAndMatch(View view) {

        //reaching the start game button to make hide it when the user starts the gane
        Button start_button = findViewById(R.id.startGame);
        start_button.setVisibility(View.GONE);

        final long start_time = System.currentTimeMillis();
         System.out.println(start_time + "start time");


        //adding the green user_input button after song finishes
        Button userButton = findViewById(R.id.user_button);
        userButton.setVisibility(View.VISIBLE);

        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                real_beat.add((long)910);
                real_beat.add((long)1700);
                real_beat.add((long)2510);
                real_beat.add((long)3400);
                real_beat.add((long)4180);
                real_beat.add((long)4990);
                real_beat.add((long)5410);
                real_beat.add((long)5800);

                System.out.println(real_beat);

                System.out.println("green button");
                final long click_time = System.currentTimeMillis();
                System.out.println(click_time+"this is the click time");
                user_beat.add(click_time - start_time);                                              // Time Distance for compare it
                System.out.print("This is user beat: " + user_beat);
                /*for (int k = 0; k < user_beat.size(); k++) {
                   for (int l = k; l < user_beat.size(); l++) {
                        reference_beat.add(real_beat.get(k-1));
                    }
                }*/


                for (int i = 0; i < user_beat.size(); i++) {
                   // user_beat.add(i, click_time - start_time);                                              // Time Distance for compare it
                    //System.out.print("This is user beat: " + user_beat);

                    for (int j = 0; j < real_beat.size(); j++) {
                        if(user_beat.get(i)< (real_beat.get(j)-500) && user_beat.get(i) > (real_beat.get(j)+500)) { // tolerance for accuracy
                            System.out.println(i);
                            System.out.println(j);
                        } else {
                            System.out.println("wrong");
                        }
                    }

                }
                user_beat.clear();
                real_beat.clear();






            }

   });



        // play the song
        beginnerSong.start();


        //reaching the active imageButton (play)
        ImageButton imgButton = findViewById(R.id.imageButton);

        //changing the play button to pause while playing
        imgButton.setImageResource(android.R.drawable.ic_media_play);

        //we moved this piece of code up because the click events on the user button are not happenning on song completion (but during the song) (Isil)
        // I commented this method because it was causing the app to crash
       /* userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void user_button_Onclick(View v) {
                // update user beat list
                long click_time = System.currentTimeMillis();
                user_beat.add(click_time);
                if (user_beat.size() == 0) {
                    user_beat.add((long) 0);
                } else {
                    //long click_time = System.currentTimeMillis();
                    user_beat.add(click_time - user_beat.get(0));   // TimeDistance to first click
                    Log.d("TAg", (click_time + ""));
                }
                // draw new points


            }
        });
        */


        //deactivate the button after first play - for later

        //try to dynamically put dots on the purple line
        //TO DO -> here we need to use the timeline and start putting notes on it
        // start a timer, mark the ,0.8th second, and 1.6 - 2 - 2.4
        //https://www.journaldev.com/1050/java-timer-timertask-example
        //https://www.compilejava.net/



        //What should happen when the song is over? -> all comes here
        beginnerSong.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                // initialize the user beat list
                // user_beat = new ArrayList<Long>();

                Button userButton = findViewById(R.id.user_button);
                userButton.setVisibility(View.GONE);

                Button start_button = findViewById(R.id.startGame);
                start_button.setText(getString(R.string.RESTART));
                start_button.setVisibility(View.VISIBLE);

   /*           userButton.setOnClickListener(new View.OnClickListener() {
                @Override
               public void onClick(View v) {
                    // update user beat list
                    //long click_time = System.currentTimeMillis();
                  //user_beat.add(click_time);
                    if (user_beat.size() == 0) {
                        user_beat.add((long) 0);
                    } else {
                        long click_time = System.currentTimeMillis();
                        user_beat.add(click_time - user_beat.get(0));   // TimeDistance to first click
                        Log.d("TAg", (click_time + ""));
                        System.out.println(click_time);
                    }
            // draw new points


        }
    });
              */
                //changing the play button to replay after song finishes
                ImageButton imgButton = findViewById(R.id.imageButton);
                imgButton.setImageResource(R.drawable.replay);


            }
        });

    }


    protected void onPause() {
        // pause the song
        super.onPause();
        beginnerSong.release();

    }


}
