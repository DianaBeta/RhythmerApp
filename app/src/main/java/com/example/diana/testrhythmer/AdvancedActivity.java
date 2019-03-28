package com.example.diana.testrhythmer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class AdvancedActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer = new MediaPlayer();
    private static int result;
    private static int win_counter;
    MediaPlayer advancedSong1;
    MediaPlayer advancedSong2;
    MediaPlayer advancedSong3;

    ArrayList<Long> referenceBeat = new ArrayList<>();
    ArrayList<Long> userBeat = new ArrayList<>();
    ArrayList<MediaPlayer> songs = new ArrayList<>();
    int count;
    // int k = 0;
    long start_time_ms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced);
        createMusic();
        fillSongs();
    }

    public void createMusic() {
        advancedSong1 = MediaPlayer.create(AdvancedActivity.this, R.raw.advanced1);
        advancedSong2 = MediaPlayer.create(AdvancedActivity.this, R.raw.advanced2);
        advancedSong3 = MediaPlayer.create(AdvancedActivity.this, R.raw.advanced3);
    }


    public void homeA(View view) {
        // Do something in response to button;goes to home which is where we can choose the levels and advanced and beginner are unlocked
        Intent intent = new Intent(this, loginAdvanced.class);
        startActivity(intent);
    }

    public void howToAA(View view) {
        // Do something in response to button-goes to the other screen of how to play metromome
        Intent intent = new Intent(this, metronomeAA.class);
        startActivity(intent);
    }

    // add songs to arrayList
    public void fillSongs() {
        songs.add(advancedSong1);
        songs.add(advancedSong2);
        songs.add(advancedSong3);
    }

    // fill referenceBeat for each song
    public void fillReference() {
        referenceBeat.clear();
        if (songs.size() == 3) {
            referenceBeat.add((long) 2711);
            referenceBeat.add((long) 3034);
            referenceBeat.add((long) 3681);
            referenceBeat.add((long) 4009);
            referenceBeat.add((long) 4655);
        } else if (songs.size() == 2) {
            referenceBeat.add((long) 2966);
            referenceBeat.add((long) 3652);
            referenceBeat.add((long) 4338);
            referenceBeat.add((long) 4691);
            referenceBeat.add((long) 5377);
        } else {
            referenceBeat.add((long) 3021);
            referenceBeat.add((long) 3371);
            referenceBeat.add((long) 4052);
            referenceBeat.add((long) 4407);
            referenceBeat.add((long) 4754);
            referenceBeat.add((long) 5435);
        }
    }

    //make referenceBeats invisible & clear referencebeat list
    public void dotsInvisible() {
        Button dot1 = findViewById(R.id.dot_1);
        dot1.setVisibility(View.INVISIBLE);
        Button dot2 = findViewById(R.id.dot_2);
        dot2.setVisibility(View.INVISIBLE);
        Button dot3 = findViewById(R.id.dot_3);
        dot3.setVisibility(View.INVISIBLE);
        Button dot4 = findViewById(R.id.dot_4);
        dot4.setVisibility(View.INVISIBLE);
        Button dot5 = findViewById(R.id.dot_5);
        dot5.setVisibility(View.INVISIBLE);
        Button dot6 = findViewById(R.id.dot_6);
        dot6.setVisibility(View.INVISIBLE);
        referenceBeat.clear();
    }

    //make userBeats invisible & clear userBeat list
    public void udotsInvisible() {
        Button udot1 = findViewById(R.id.udot_1);
        udot1.setVisibility(View.INVISIBLE);
        Button udot2 = findViewById(R.id.udot_2);
        udot2.setVisibility(View.INVISIBLE);
        Button udot3 = findViewById(R.id.udot_3);
        udot3.setVisibility(View.INVISIBLE);
        Button udot4 = findViewById(R.id.udot_4);
        udot4.setVisibility(View.INVISIBLE);
        Button udot5 = findViewById(R.id.udot_5);
        udot5.setVisibility(View.INVISIBLE);
        Button udot6 = findViewById(R.id.udot_6);
        udot6.setVisibility(View.INVISIBLE);
        userBeat.clear();
    }

    // Press PlayButton
    public void play(View view) {
        // Play the first song of the ArrayList
        songs.get(0).start();

        // Make Restart invisible during song is playing again
        Button start_button = findViewById(R.id.startGame);
        start_button.setVisibility(View.INVISIBLE);

        fillReference();
        // set referenceend for calculation
        long referenceBeatEnd = 6000;

        // add the points
        int line_width = this.findViewById(R.id.View03).getMeasuredWidth();
        int[] location = new int[2];
        this.findViewById(R.id.View03).getLocationInWindow(location);
        int line_start = location[0];

        // Create ArrayList with invisible referenceDots
        ArrayList<Button> buttonList = new ArrayList<Button>();
        buttonList.add((Button) findViewById(R.id.dot_1));
        buttonList.add((Button) findViewById(R.id.dot_2));
        buttonList.add((Button) findViewById(R.id.dot_3));
        buttonList.add((Button) findViewById(R.id.dot_4));
        buttonList.add((Button) findViewById(R.id.dot_5));
        buttonList.add((Button) findViewById(R.id.dot_6));

        //Go through invisible referenceDots and set as many visible as contained references in songs (according to computation)
        for (int i = 0; i < buttonList.size(); ++i) {
            if (i < referenceBeat.size()) {
                double point_x_percent = (double) (referenceBeat.get(i) - referenceBeat.get(0)) /
                        (double) (referenceBeatEnd - referenceBeat.get(0));
                int point_x = (int) (point_x_percent * (double) line_width + (double) line_start);
                buttonList.get(i).setX(point_x);                               //set button x position
                buttonList.get(i).setVisibility(View.VISIBLE);                 // make button visible
            } else {
                buttonList.get(i).setVisibility(View.INVISIBLE);               // make button invisible
            }
        }

        //reaching the active imageButton (play)
        ImageButton imgButton = findViewById(R.id.imageButton);

        imgButton.setImageResource(android.R.drawable.ic_media_play);

        //What should happen when the song is over for the first time? -> all comes here
        songs.get(0).setOnCompletionListener(new MediaPlayer.OnCompletionListener() {


            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

                //adding the start game button after song finishes for the first time
                Button start_button = findViewById(R.id.startGame);
                start_button.setText(getString(R.string.START));
                start_button.setVisibility(View.VISIBLE);

                //changing the play button to replay after song finishes
                ImageButton imgButton = findViewById(R.id.imageButton);
                imgButton.setImageResource(R.drawable.replay);
            }

        });
    }

    /*compare the beat input with the reference beat --> 0,1 or 2:
    2: LOST: User tapped too often or too rarely
    1: WIN: User tapped as required (amount + time)
    0: LOST: Amount as required but not the time*/

    public static int compareArrays(ArrayList<Long> amountClicks, ArrayList<Long> amountUserClicks) {
        result = 0; //final result
        win_counter = 0; //counter of the correct clicked beats
        int win_treshold = amountClicks.size(); // after how many correct clicks does the user win?
        int tolerance_rate_ms = 250; //increase if you want more WINs, decrease if you want game to be more strict
        if (amountClicks.size() != amountUserClicks.size()) { //if the input arrays aren't same size
            result = 2;
        } else {
            for (int i = 0; i < amountUserClicks.size(); i++) {
                long beat_diff_ms = amountUserClicks.get(i) - amountClicks.get(i); //check the diff between the first elements of each array
                if (beat_diff_ms > -tolerance_rate_ms && beat_diff_ms < tolerance_rate_ms) { //check if the diff is between a desired range(tolerance_rate_ms)
                    win_counter += 1; //if in range, then count this as a correctly pressed user beat
                }
            }
        }
        if (win_counter == win_treshold) { //if the count of timely pressed user beat is equal to desired winning treshold
            result = 1; //then make this round a winner
        }
        return result;
    }

    // Press Start or Try Again
    @SuppressLint("ClickableViewAccessibility")

    public void playAndMatch(final View view) {
        //reaching the start game button to make hide it when the user starts the game
        Button start_button = findViewById(R.id.startGame);
        start_button.setVisibility(View.INVISIBLE);

        // make note "You pressed before 4x metronome" invisible
        TextView early = findViewById(R.id.early);
        early.setVisibility(View.INVISIBLE);

        //adding the green user_input button after song finishes
        Button userButton = findViewById(R.id.user_button);
        userButton.setVisibility(View.VISIBLE);

        userButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int eventAction = event.getAction();
                final long click_time_ms_ = System.currentTimeMillis();
                if (eventAction == MotionEvent.ACTION_DOWN) {
                    final long userBeat_ms_ = click_time_ms_ - start_time_ms;
                    userBeat.add(userBeat_ms_);
                    count = userBeat.size();
                }
                return true;

            }
        });


        //reaching the active imageButton (play)
        ImageButton imgButton = findViewById(R.id.imageButton);

        //changing the play button to pause while playing
        imgButton.setImageResource(android.R.drawable.ic_media_play);

        // make result view gone
        final TextView result_display = findViewById(R.id.result_view);
        result_display.setVisibility(View.GONE);

        //What should happen when the song is over? -> all comes here
        songs.get(0).setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @SuppressLint({"SetTextI18n", "ResourceAsColor"})
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

                // make green userButton invisible// make StartButton to Restart and visible // play to replay
                Button userButton = findViewById(R.id.user_button);
                userButton.setVisibility(View.INVISIBLE);
                Button start_button = findViewById(R.id.startGame);
                start_button.setText(getString(R.string.RESTART));
                start_button.setVisibility(View.VISIBLE);
                ImageButton imgButton = findViewById(R.id.imageButton);
                imgButton.setImageResource(R.drawable.replay);

                //refresh(for the next try) and define reference beat values
                fillReference();

                //calculate and result output
                int game_result = compareArrays(referenceBeat, userBeat);
                switch (game_result) {
                    case 2: //when result is 2
                        userButton.setVisibility(View.INVISIBLE);
                        result_display.setVisibility(View.VISIBLE);
                        result_display.setText("YOU LOST :( Hit count: " + userBeat.size() + ". Expected hits: " + referenceBeat.size());

                        //Button start_button = findViewById(R.id.startGame);
                        start_button.setText(getString(R.string.RESTART));
                        start_button.setVisibility(View.VISIBLE);

                        break;
                    case 1://when result is 1
                        userButton.setVisibility(View.INVISIBLE);
                        //start_button.setText("NEXT LEVEL COMING SOON");
                        //start_button.setVisibility(View.VISIBLE);
                        start_button.setVisibility(View.INVISIBLE);
                        result_display.setVisibility(View.VISIBLE);
                        result_display.setText("YOU WON! :) " + userBeat.size() + "/" + referenceBeat.size());


                        // Make NextSong visible
                        Button NextSong = findViewById(R.id.NextSong);
                        NextSong.setVisibility(View.VISIBLE);
                        break;
                    case 0://when result is 0
                        userButton.setVisibility(View.INVISIBLE);

                        result_display.setVisibility(View.VISIBLE);
                        result_display.setText("YOU LOST :( " + win_counter + "/" + referenceBeat.size());
                        //Button start_button = findViewById(R.id.startGame);
                        start_button.setText(getString(R.string.RESTART));
                        start_button.setVisibility(View.VISIBLE);


                    default:
                }

                //define length for calculation
                long referenceBeatEnd = 6500;
                // add the points
                int line_width = findViewById(R.id.View04).getMeasuredWidth();
                int[] location = new int[2];
                findViewById(R.id.View04).getLocationInWindow(location);
                int line_start = location[0];

                //fill invisible userButtons
                ArrayList<Button> ubuttonList = new ArrayList<>();
                ubuttonList.add((Button) findViewById(R.id.udot_1));
                ubuttonList.add((Button) findViewById(R.id.udot_2));
                ubuttonList.add((Button) findViewById(R.id.udot_3));
                ubuttonList.add((Button) findViewById(R.id.udot_4));
                ubuttonList.add((Button) findViewById(R.id.udot_5));
                ubuttonList.add((Button) findViewById(R.id.udot_6));
                ubuttonList.add((Button) findViewById(R.id.udot_7));
                ubuttonList.add((Button) findViewById(R.id.udot_8));
                ubuttonList.add((Button) findViewById(R.id.udot_9));
                ubuttonList.add((Button) findViewById(R.id.udot_10));

                ArrayList<Button> ubuttonrList = new ArrayList<>();
                ubuttonrList.add((Button) findViewById(R.id.udotr_1));
                ubuttonrList.add((Button) findViewById(R.id.udotr_2));
                ubuttonrList.add((Button) findViewById(R.id.udotr_3));
                ubuttonrList.add((Button) findViewById(R.id.udotr_4));
                ubuttonrList.add((Button) findViewById(R.id.udotr_5));
                ubuttonrList.add((Button) findViewById(R.id.udotr_6));
                ubuttonrList.add((Button) findViewById(R.id.udotr_7));
                ubuttonrList.add((Button) findViewById(R.id.udotr_8));
                ubuttonrList.add((Button) findViewById(R.id.udotr_9));
                ubuttonrList.add((Button) findViewById(R.id.udotr_10));


                // go through all userbuttons and set as many visible as contained userbeats
                for (int j = 0; j < ubuttonList.size(); ++j) {
                    if (j < userBeat.size()) {
                        double userBeat_diff = userBeat.get(j) - referenceBeat.get(0);
                        double point_x_percent = userBeat_diff / (double) (referenceBeatEnd - referenceBeat.get(0)); //Ensures the same scala as at reference beat
                        int point_x = (int) (point_x_percent * (double) line_width + (double) line_start);

                        // ignore all dots before the 4x metronome (else) // except those inside the tolerance (if)
                        if (userBeat_diff < -250) {
                            ubuttonList.get(j).setVisibility(View.INVISIBLE);
                            TextView early = findViewById(R.id.early); // make note "you pressed before 4x metronome" visible
                            early.setVisibility(View.VISIBLE);
                        } else {
                            if (game_result == 1) {
                                ubuttonList.get(j).setX(point_x);//set button x position
                                ubuttonList.get(j).setVisibility(View.VISIBLE);
                                ubuttonrList.get(j).setVisibility(View.INVISIBLE);
                            } else {
                                ubuttonrList.get(j).setX(point_x);//set button x position
                                ubuttonrList.get(j).setVisibility(View.VISIBLE);
                                ubuttonList.get(j).setVisibility(View.INVISIBLE);
                            }
                        }
                    } else {
                        ubuttonList.get(j).setVisibility(View.INVISIBLE);
                        ubuttonrList.get(j).setVisibility(View.INVISIBLE);
                    }
                }
                userBeat.clear();
                ubuttonList.clear();
            }
        });

        //start song (in here: reduced computation time) & save ms
        songs.get(0).start();
        start_time_ms = System.currentTimeMillis();
    }

    public void NextSong(View view) {
        // Logic following
        // Delete first song in the array until no song is left // set NexSong and result invisible
        if (songs.size() == 3) {
            songs.get(0).release();
            songs.remove(0);
            Button NextSong = findViewById(R.id.NextSong);
            NextSong.setVisibility(View.INVISIBLE);
            final TextView result_display = findViewById(R.id.result_view);
            result_display.setVisibility(View.INVISIBLE);
        } else if (songs.size() == 2) {
            songs.get(0).release();
            songs.remove(0);
            Button NextSong = findViewById(R.id.NextSong);
            NextSong.setVisibility(View.INVISIBLE);
            final TextView result_display = findViewById(R.id.result_view);
            result_display.setVisibility(View.INVISIBLE);
        } else {
            Button NextSong = findViewById(R.id.NextSong);
            NextSong.setVisibility(View.GONE);
            NextActivity();
        }
        // Make all reference nodes invisible
        dotsInvisible();
        // Make all user nodes invisible
        udotsInvisible();

        //changing the play button to replay after song finishes
        ImageButton imgButton = findViewById(R.id.imageButton);
        imgButton.setImageResource(R.drawable.ic_media_play);
    }

    public void NextActivity() {
        //goes to next activity which is congratulations activity to continue with the next level, expert in this case
        Intent intent = new Intent(this, Congrats2.class);
        startActivity(intent);
    }

    protected void onPause() {
        // pause the song when closing app
        super.onPause();
        songs.get(0).stop();
    }
}



