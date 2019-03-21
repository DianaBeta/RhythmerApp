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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class beginnerActivity extends AppCompatActivity {


    MediaPlayer mediaPlayer=new MediaPlayer();
    private static int result;
    private static int win_counter;
    MediaPlayer beginnerSong;
    MediaPlayer beginnerSong2;
    MediaPlayer beginnerSong3;

    ArrayList<Long> reference_beat1 = new ArrayList<>();
    ArrayList<Long> user_beat = new ArrayList<>();
    ArrayList<MediaPlayer> songs = new ArrayList<>();
    int count;
   // int k = 0;
    long start_time_ms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginner);
        createMusic();
        fillSongs();
    }

    public void createMusic(){
        beginnerSong = MediaPlayer.create(beginnerActivity.this, R.raw.beginner_new);
        beginnerSong2 = MediaPlayer.create(beginnerActivity.this, R.raw.beginner2);
        beginnerSong3 = MediaPlayer.create(beginnerActivity.this, R.raw.beginner3);
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

    public void howToBA(View view) {
        // Do something in response to button-goes to the other screen of how to play metromome
        Intent intent = new Intent(this,metronomeBA.class);
        startActivity(intent);
    }

    // add songs to arrayList
    public void fillSongs(){
        songs.add(beginnerSong);
        songs.add(beginnerSong2);
        songs.add(beginnerSong3);
    }

    // fill reference_beat for each song
    public void fillReference1(){
        reference_beat1.clear();
        if (songs.size() == 3) {
            reference_beat1.add((long) 3221);
            reference_beat1.add((long) 4026);
            reference_beat1.add((long) 4831);
            reference_beat1.add((long) 5218);
            reference_beat1.add((long) 5627);
        } else if (songs.size() == 2) {
            reference_beat1.add((long) 3625);
            reference_beat1.add((long) 4022);
            reference_beat1.add((long) 4419);
            reference_beat1.add((long) 5238);
            reference_beat1.add((long) 5616);
            reference_beat1.add((long) 6033);
        } else {
            reference_beat1.add((long) 3506);
            reference_beat1.add((long) 4053);
            reference_beat1.add((long) 4461);
            reference_beat1.add((long) 4860);
            reference_beat1.add((long) 5259);
            reference_beat1.add((long) 5658);
        }
    }

    //make referenceBeats invisible & clear referencebeat list
    public void dotsInvisible(){
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
        reference_beat1.clear();
    }

    //make userBeats invisible & clear userBeat list
    public void udotsInvisible(){
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
        user_beat.clear();
    }

    // Press PlayButton
    public void play(View view) {
        udotsInvisible();

        // Play the first song of the ArrayList
        songs.get(0).start();

        // Make Restart invisible during song is playing again
        Button start_button = findViewById(R.id.startGame);
        start_button.setVisibility(View.INVISIBLE);


        fillReference1();
        // set referencebeat for calculation
        long reference_beat_end = 6500;


        // add the points
        int line_width = this.findViewById(R.id.View03).getMeasuredWidth();
        int[] location = new int[2];
        this.findViewById(R.id.View03).getLocationInWindow(location);
        int line_start = location[0];

        // Create ArrayList with invisible referenceDots
        ArrayList<Button> button_list = new ArrayList<Button>();
        button_list.add((Button)findViewById(R.id.dot_1));
        button_list.add((Button)findViewById(R.id.dot_2));
        button_list.add((Button)findViewById(R.id.dot_3));
        button_list.add((Button)findViewById(R.id.dot_4));
        button_list.add((Button)findViewById(R.id.dot_5));
        button_list.add((Button)findViewById(R.id.dot_6));

        //System.out.println("Number of songs:" + songs.size());
        // Go through invisible referenceDots and set as many visible as contained references in songs (according to computation)
        for (int i = 0; i < button_list.size(); ++i) {
            if (i < reference_beat1.size()) {
                double point_x_percent = (double) (reference_beat1.get(i) - reference_beat1.get(0)) /
                        (double) (reference_beat_end - reference_beat1.get(0));
                int point_x = (int) (point_x_percent * (double) line_width + (double) line_start);
                //Log.i("BEGINNER_ACTIVITY_BLA", String.valueOf(point_x));
                button_list.get(i).setX(point_x);                               //set button x position
                button_list.get(i).setVisibility(View.VISIBLE);                 // make button visible
            } else {
                button_list.get(i).setVisibility(View.INVISIBLE);               // make button invisible
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

     /* compare the beat input with the reference beat --> 0,1 or 2:
    2: LOST: User tapped too often or too rarely
    1: WIN: User tapped as required (amount + time)
    0: LOST: Amount as required but not the time */

    public static int compareArrays(ArrayList<Long> array1, ArrayList<Long> array2) {
        int result = 0; //final result
        win_counter = 0; //counter of the correct clicked beats
        int win_treshold = array1.size(); // after how many correct clicks does the user win?
        int tolerance_rate_ms = 250; //increase if you want more WINs, decrease if you want game to be more strict
        //System.out.println(array1.size()+"--"+array2.size());
        if (array1.size() != array2.size()){ //if the input arrays aren't same size
            //System.out.print("in here!");
            result = 2;
        } else {
            for (int i = 0; i < array2.size(); i++) {
                long beat_diff_ms = array2.get(i) - array1.get(i); //check the diff between the first elements of each array
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

 /*   public static ArrayList<Integer> compareArrays2(ArrayList<Long> user, ArrayList<Long> reference) {
        ArrayList<Integer> user_valid = new ArrayList<>();
        ArrayList<Long> user_valid_diff = new ArrayList<>();
        for (int i = 0; i < user.size(); ++i) {
            int closest_id = -1;
            long closest_diff = 250;
            for (int j = 0; j < reference.size(); ++j) {
                Long diff = Math.abs(user.get(i) - reference.get(j));
                if (diff < closest_diff) {
                    closest_id = j;
                    closest_diff = diff;
                }
            }
            user_valid.add(closest_id);
            user_valid_diff.add(closest_diff);
        }

        for (int i = 0; i < user_valid.size(); ++i) {
            Log.i("USER_VALID", String.valueOf(user_valid.get(i)));
            Log.i("USER_VALIDDIST", String.valueOf(user_valid_diff.get(i)));
        }

        for (int j = 0; j < reference.size(); ++j) {
            int closest_id = -1;
            long closest_diff = 1000;
            for (int i = 0; i < user.size(); ++i) {
                if (user_valid.get(i) != j)
                    continue;
                if (user_valid_diff.get(i) < closest_diff) {
                    if (closest_id >= 0) {
                        user_valid.set(closest_id, -1);
                    }
                    closest_id = i;
                    closest_diff = user_valid_diff.get(i);
                } else {
                    user_valid.set(i, -1);
                }
            }
        }

        for (int i = 0; i < user_valid.size(); ++i) {
            Log.i("USER_VALID2", String.valueOf(user_valid.get(i)));
        }

        return user_valid;

    }*/

    // Press Start or Try Again
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
        userButton.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                //System.out.println("green button pressed.");
                final long click_time_ms = System.currentTimeMillis();
                final long user_beat_ms = click_time_ms - start_time_ms;
                //System.out.println("user beat ms being added: " + user_beat_ms);
                user_beat.add(user_beat_ms);
                count = user_beat.size();
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
                fillReference1();
                //System.out.println("reference beat list: " + reference_beat1);

                //calculate and output the result
                int game_result = compareArrays(reference_beat1, user_beat);
                //ArrayList<Integer> user_valid = compareArrays2(user_beat, reference_beat1);
                switch (game_result) {
                    case 2: //when result is 2
                        userButton.setVisibility(View.INVISIBLE);
                        result_display.setVisibility(View.VISIBLE);
                        result_display.setText("YOU LOST :( Hit count: " + user_beat.size() + ". Expected hits: " + reference_beat1.size());

                        //Button start_button = findViewById(R.id.startGame);
                        start_button.setText(getString(R.string.RESTART));
                        start_button.setVisibility(View.VISIBLE);
                        // Just control output
                        /*System.out.println("YOU LOST :( ");
                        System.out.println("You should press green the button exactly " + reference_beat1.size() + " times.");
                        System.out.println("You pressed " + user_beat.size() + " times.");
                        System.out.println("TRY AGAIN NOW!");*/
                        break;
                    case 1://when result is 1
                        userButton.setVisibility(View.INVISIBLE);
                        //start_button.setText("NEXT LEVEL COMING SOON");
                        //start_button.setVisibility(View.VISIBLE);
                        start_button.setVisibility(View.INVISIBLE);
                        result_display.setVisibility(View.VISIBLE);
                        result_display.setText("YOU WON! :) " + user_beat.size() + "/" + reference_beat1.size());

                        // Just control output
                        /* System.out.println("YOU WON! :)");
                        System.out.println(reference_beat.size() + " out of " + reference_beat.size() + " beats were there correctly!");
                        System.out.println("NEXT LEVEL UNLOCKED. CLICK TO GO!");
                        //suggestion for the next level will be added as a feature here later on*/

                        // Make NextSong visible
                        Button NextSong = findViewById(R.id.NextSong);
                        NextSong.setVisibility(View.VISIBLE);
                        break;
                    case 0://when result is 0
                        userButton.setVisibility(View.INVISIBLE);

                        result_display.setVisibility(View.VISIBLE);
                        result_display.setText("YOU LOST :( " + win_counter + "/" + reference_beat1.size());
                        //Button start_button = findViewById(R.id.startGame);
                        start_button.setText(getString(R.string.RESTART));
                        start_button.setVisibility(View.VISIBLE);

                        // Just control output
                        /* System.out.println("YOU LOST :( ");
                        System.out.println("Only " + win_counter + " out of " + reference_beat.size() + " beats were right!");
                        System.out.println("TRY AGAIN NOW!"); //this will be implemented as a feature later on*/
                    default:
                       // System.out.println(result);
                }

                //define length for calculation
                long reference_beat_end = 6500;
                // add the points
                int line_width = findViewById(R.id.View04).getMeasuredWidth();
                int[] location = new int[2];
                findViewById(R.id.View04).getLocationInWindow(location);
                int line_start = location[0];

                //fill invisible userButtons
                ArrayList<Button> ubutton_list = new ArrayList<>();
                ubutton_list.add((Button) findViewById(R.id.udot_1));
                ubutton_list.add((Button) findViewById(R.id.udot_2));
                ubutton_list.add((Button) findViewById(R.id.udot_3));
                ubutton_list.add((Button) findViewById(R.id.udot_4));
                ubutton_list.add((Button) findViewById(R.id.udot_5));
                ubutton_list.add((Button) findViewById(R.id.udot_6));

                ArrayList<Button> ubuttonr_list = new ArrayList<>();
                ubuttonr_list.add((Button) findViewById(R.id.udotr_1));
                ubuttonr_list.add((Button) findViewById(R.id.udotr_2));
                ubuttonr_list.add((Button) findViewById(R.id.udotr_3));
                ubuttonr_list.add((Button) findViewById(R.id.udotr_4));
                ubuttonr_list.add((Button) findViewById(R.id.udotr_5));
                ubuttonr_list.add((Button) findViewById(R.id.udotr_6));
                ubuttonr_list.add((Button) findViewById(R.id.udotr_7));
                ubuttonr_list.add((Button) findViewById(R.id.udotr_8));
                ubuttonr_list.add((Button) findViewById(R.id.udotr_9));
                ubuttonr_list.add((Button) findViewById(R.id.udotr_10));


                // go through all userbuttons and set as many visible as contained userbeats
                for (int j = 0; j < ubutton_list.size(); ++j) {
                    if (j < user_beat.size()) {
                        double user_beat_diff = user_beat.get(j) - reference_beat1.get(0);
                        double point_x_percent = user_beat_diff / (double) (reference_beat_end - reference_beat1.get(0)); //Ensures the same scala as at reference beat
                        //Log.i("point_x_percent=", String.valueOf(point_x_percent));
                        int point_x = (int) (point_x_percent * (double) line_width + (double) line_start);
                        //Log.i("pointx", String.valueOf(point_x));
                        // if(user_beat.get(j)!= reference_beat1.get(j)){
                        //Button ubbutton_list.get(j)= findViewById(R.id.udotr_10);
                        //   ubutton_list.get(j).setButton(R.drawable.reddot);
                        // } else {
                        //     ubutton_list.set(j, ubuttonr_list.get(k));
                        //    k++;
                        //}
                        // ignore all dots before the 4x metronome (else) // except those inside the tolerance (if)

                        if (user_beat_diff < -250) {
                            ubutton_list.get(j).setVisibility(View.INVISIBLE);
                            TextView early = findViewById(R.id.early); // make note "you pressed before 4x metronome" visible
                            early.setVisibility(View.VISIBLE);
                        } else {
                            if (game_result == 1) {
                                ubutton_list.get(j).setX(point_x);//set button x position
                                ubutton_list.get(j).setVisibility(View.VISIBLE);
                                ubuttonr_list.get(j).setVisibility(View.INVISIBLE);
                            } else {
                                ubuttonr_list.get(j).setX(point_x);//set button x position
                                ubuttonr_list.get(j).setVisibility(View.VISIBLE);
                                ubutton_list.get(j).setVisibility(View.INVISIBLE);
                            }
                            //Log.i("user_beat", String.valueOf(user_beat.get(j)));
                        }
                       // System.out.println("Anzahl user Tips" + user_beat.size());
                    }  else{
                        ubutton_list.get(j).setVisibility(View.INVISIBLE);
                        ubuttonr_list.get(j).setVisibility(View.INVISIBLE);
                    }
                }
                user_beat.clear();
                ubutton_list.clear();
                //ubuttonr_list.clear();
            }
        });

       //start song (in here: reduced computation time) & save ms
        songs.get(0).start();
        start_time_ms = System.currentTimeMillis();
        //System.out.println("start time: " + start_time_ms);
    }

    public void NextSong(View view) {
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
        } else  {
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

    public void NextActivity(){
        //goes to next activity which is congratulations activity to continue with the next level, advanced in this case
        Intent intent = new Intent(this,Congrats.class);
        startActivity(intent);
    }

    protected void onPause() {
        // pause the song when closing app
        super.onPause();
        songs.get(0).stop();
    }

}
