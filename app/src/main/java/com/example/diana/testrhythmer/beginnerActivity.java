package com.example.diana.testrhythmer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import java.util.ArrayList;
import java.util.List;

public class beginnerActivity extends AppCompatActivity {
    private static int result;
    private static int win_counter;
    MediaPlayer beginnerSong;

    ArrayList<Long> reference_beat = new ArrayList<>();
    ArrayList<Long> user_beat = new ArrayList<>();


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
    // fill reference_beat
    public void fillReference(){
        reference_beat.clear();
        reference_beat.add((long) 3733);
        reference_beat.add((long) 4549);
        reference_beat.add((long) 5285);
        reference_beat.add((long) 5657);
        reference_beat.add((long) 6065);
    }


    public void play(View view) {
        // play the song
        beginnerSong.start();

        // Make Restart invisible during song is playing again
        Button start_button = findViewById(R.id.startGame);
        start_button.setVisibility(View.INVISIBLE);

        fillReference();
        long reference_beat_end = 7000;

        // add the points
        int line_width = this.findViewById(R.id.View03).getMeasuredWidth();
        int[] location = new int[2];
        this.findViewById(R.id.View03).getLocationInWindow(location);
      //  int bottom = this.findViewById(R.id.imageView9).getMeasuredHeight();
        int line_start = location[0];
       // int point_y = location[1];

        Log.i("BEGINNER_ACTIVITY", String.valueOf(line_width));
        Log.i("BEGINNER_ACTIVITY", String.valueOf(line_start));

        ArrayList<Button> button_list = new ArrayList<Button>();
        button_list.add((Button)findViewById(R.id.dot_1));
        button_list.add((Button)findViewById(R.id.dot_2));
        button_list.add((Button)findViewById(R.id.dot_3));
        button_list.add((Button)findViewById(R.id.dot_4));
        button_list.add((Button)findViewById(R.id.dot_5));

        for (int i = 0; i < button_list.size(); ++i)
        {

            if (i < reference_beat.size()) {
                double point_x_percent = (double) (reference_beat.get(i) - reference_beat.get(0)) /
                        (double) (reference_beat_end - reference_beat.get(0));
                int point_x = (int) (point_x_percent * (double) line_width + (double) line_start);
                Log.i("BEGINNER_ACTIVITY_BLA", String.valueOf(point_x));
                button_list.get(i).setX(point_x);                              //set button x position
                button_list.get(i).setVisibility(View.VISIBLE);               // make button visible
            } else {
                button_list.get(i).setVisibility(View.INVISIBLE);             // make button invisible
            }

        }

        // force redraw
       // this.findViewById(android.R.id.content).getRootView().invalidate();


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

    /*
    Compare Array Method is used to compare the beat input of the user with the reference beat
    It returns 0,1 or 2 depending on different scenarios:
    Scenario A: If the lists are empty or the users list is not equal to the reference beat (meaning the user
    had either pressed the green button too many or too few times) then the score is 2(lost).
    Scenario B: If the user has the same input length as the reference (meaning the user pressed as many times as
    he was supposed to) AND the beat miliseconds of each item in the list are within a tolerance range
    of the corresponding reference input item then the method returns 1(won).
    Scenario C: If the length of the inputs are equal but the user pressed the green button at the wrong milisecond, then
    the method returns 0(lost).
    */

    public static int compareArrays(ArrayList<Long> array1, ArrayList<Long> array2) {
        //result : 1 -> win,  2 -> lost(user beat count wrong) 0-> lost(time mismatch)
        result = 0; //final result
        win_counter = 0; //counter of the correct clicked beats
        int win_treshold = array1.size(); // after how many correct clicks does the user win?
        int tolerance_rate_ms = 250; //increase if you want more WINs, decrease if you want game to be more strict
        System.out.println(array1.size()+"--"+array2.size());
        if (array1.size() != array2.size()){ //if the input arrays arent same size
            System.out.print("in here!");
            result = 2;}
        else
            for (int i = 0; i < array2.size(); i++) {
                long beat_diff_ms = array2.get(i) - array1.get(i); //check the diff between the first elements of each array
                if (beat_diff_ms > -tolerance_rate_ms && beat_diff_ms < tolerance_rate_ms) { //check if the diff is between a desired range(tolerance_rate_ms)
                    win_counter += 1; //if in range, then count this as a correctly pressed user beat
                }
            }
        if (win_counter == win_treshold) { //if the count of timely pressed user beat is equal to desired winning treshold
            result = 1; //then make this round a winner
        }

        return result;
    }

    public void playAndMatch(View view) {

        //reaching the start game button to make hide it when the user starts the game
        Button start_button = findViewById(R.id.startGame);
        start_button.setVisibility(View.GONE);

        final long start_time_ms = System.currentTimeMillis();
        System.out.println("start time: " + start_time_ms);
        beginnerSong.start();


        //adding the green user_input button after song finishes
        Button userButton = findViewById(R.id.user_button);
        userButton.setVisibility(View.VISIBLE);
        user_beat.clear();
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("green button pressed.");

                final long click_time_ms = System.currentTimeMillis();

                final long user_beat_ms = click_time_ms - start_time_ms;
                System.out.println("user beat ms being added : " + user_beat_ms);
                user_beat.add(user_beat_ms);
                int count = user_beat.size();
                long user_point = user_beat.get(count-1);

                System.out.println("user beat list: " + user_beat);



            }


        });


        long reference_beat_end = 7000;

        // add the points
        int line_width = this.findViewById(R.id.View04).getMeasuredWidth();
        int[] location = new int[2];
        this.findViewById(R.id.View04).getLocationInWindow(location);
        //  int bottom = this.findViewById(R.id.imageView9).getMeasuredHeight();
        int line_start = location[0];
        // int point_y = location[1];

        Log.i("BEGINNER_ACTIVITY", String.valueOf(line_width));
        Log.i("BEGINNER_ACTIVITY", String.valueOf(line_start));



            ArrayList<Button> ubutton_list = new ArrayList<Button>();
            ubutton_list.add((Button) findViewById(R.id.udot_1));
            ubutton_list.add((Button) findViewById(R.id.udot_2));
            ubutton_list.add((Button) findViewById(R.id.udot_3));
            ubutton_list.add((Button) findViewById(R.id.udot_4));
            ubutton_list.add((Button) findViewById(R.id.udot_5));

            System.out.println("this is the user beat" + user_beat);

            for (int i = 0; i < ubutton_list.size(); ++i) {
                if (i < this.user_beat.size()) {
                    double point_x_percent = (double) (this.user_beat.get(i) - this.user_beat.get(0)) /
                            (double) (reference_beat_end - this.user_beat.get(0));
                    int point_x = (int) (point_x_percent * (double) line_width + (double) line_start);
                    Log.i("BEGINNER_ACTIVITY_BLA", String.valueOf(point_x));
                    ubutton_list.get(i).setX(point_x);                              //set button x position
                    ubutton_list.get(i).setVisibility(View.VISIBLE);               // make button visible
                    Log.i("user_beat", String.valueOf(this.user_beat.get(i)));
                } else {
                    ubutton_list.get(i).setVisibility(View.INVISIBLE);             // make button invisible
                }


        }


        //reaching the active imageButton (play)
        ImageButton imgButton = findViewById(R.id.imageButton);

        //changing the play button to pause while playing
        imgButton.setImageResource(android.R.drawable.ic_media_play);

        final TextView result_display = findViewById(R.id.result_view);
        result_display.setVisibility(View.GONE);


        // start a timer, mark the ,0.8th second, and 1.6 - 2 - 2.4
        //https://www.journaldev.com/1050/java-timer-timertask-example
        //https://www.compilejava.net/


        //What should happen when the song is over? -> all comes here
        beginnerSong.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

                Button userButton = findViewById(R.id.user_button);
                userButton.setVisibility(View.GONE);

                Button start_button = findViewById(R.id.startGame);
                start_button.setText(getString(R.string.RESTART));
                start_button.setVisibility(View.VISIBLE);

                //changing the play button to replay after song finishes
                ImageButton imgButton = findViewById(R.id.imageButton);
                imgButton.setImageResource(R.drawable.replay);

                //refresh(for the next round) and define reference beat values
                fillReference();
                System.out.println("reference beat list: " + reference_beat);



                //calculate and output the result

                int game_result = compareArrays(reference_beat, user_beat);

                switch (game_result) {
                    case 2: //when result is 2
                        userButton.setVisibility(View.GONE);

                        result_display.setVisibility(View.VISIBLE);
                        result_display.setText("YOU LOST :( Hit count: " + user_beat.size() + ". Expected hits: " + reference_beat.size());


                        //Button start_button = findViewById(R.id.startGame);
                        start_button.setText(getString(R.string.RESTART));
                        start_button.setVisibility(View.VISIBLE);

                        System.out.println("YOU LOST :( ");
                        System.out.println("You should press green the button exactly " + reference_beat.size() + " times.");
                        System.out.println("You pressed " + user_beat.size() + " times.");
                        System.out.println("TRY AGAIN NOW!");
                        break;
                    case 1://when result is 1

                        userButton.setVisibility(View.GONE);
                        start_button.setText("NEXT LEVEL COMING SOON");
                        start_button.setVisibility(View.VISIBLE);


                        result_display.setVisibility(View.VISIBLE);
                        result_display.setText("YOU WON! :) " + user_beat.size() + "/" + reference_beat.size());

                        System.out.println("YOU WON! :)");

                        System.out.println(reference_beat.size() + " out of " + reference_beat.size() + " beats were there correctly!");
                        System.out.println("NEXT LEVEL UNLOCKED. CLICK TO GO!");
                        //suggestion for the next level will be added as a feature here later on

                        break;
                    case 0://when result is 0
                        userButton.setVisibility(View.GONE);

                        result_display.setVisibility(View.VISIBLE);
                        result_display.setText("YOU LOST :( " + win_counter + "/" + reference_beat.size());


                        //Button start_button = findViewById(R.id.startGame);
                        start_button.setText(getString(R.string.RESTART));
                        start_button.setVisibility(View.VISIBLE);

                        System.out.println("YOU LOST :( ");
                        System.out.println("Only " + win_counter + " out of " + reference_beat.size() + " beats were right!");
                        System.out.println("TRY AGAIN NOW!"); //this will be implemented as a feature later on
                    default:
                        System.out.println(result);
                }

            }
        });

    }


    protected void onPause() {
        // pause the song
        super.onPause();
        beginnerSong.release();

    }


}
