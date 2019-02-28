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

public class ExpertActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer=new MediaPlayer();
    private static int result;
    private static int win_counter;
    MediaPlayer expertSong1;
    MediaPlayer expertSong2;
    MediaPlayer expertSong3;

    ArrayList<Long> reference_beat1 = new ArrayList<>();
    ArrayList<Long> user_beat = new ArrayList<>();
    ArrayList<MediaPlayer> songs = new ArrayList<>();
    int count;
    // int k = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expert);
        createMusic();
        fillSongs();
    }

    public void createMusic(){
        expertSong1 = MediaPlayer.create(ExpertActivity.this, R.raw.expert1);
        expertSong2 = MediaPlayer.create(ExpertActivity.this, R.raw.expert2);
        expertSong3 = MediaPlayer.create(ExpertActivity.this, R.raw.expert3);
    }
    //TODO make help screens
    public void backtoExpert(View view) {
        // Do something in response to button goes to beginner activity
        Intent intent = new Intent(this, ExpertActivity.class);
        startActivity(intent);
    }

    public void homeE(View view) {
        // Do something in response to button-goes to home which is login activity
        Intent intent = new Intent(this, logInExpert.class);
        startActivity(intent);
    }

    //TODO make help screens
    //TODO change onClick on XML
    public void help3(View view) {
        // Do something in response to button-goes to the other screen of how to play metromome
        Intent intent = new Intent(this, metronome_b.class);
        startActivity(intent);
    }

    public void fillSongs(){
        songs.add(expertSong1);
        songs.add(expertSong2);
        songs.add(expertSong3);
    }

    // fill reference_beat regarding to next Song
    public void fillReference1(){
        reference_beat1.clear();
        if (songs.size() == 3) {
            reference_beat1.add((long) 2664);
            reference_beat1.add((long) 3004);
            reference_beat1.add((long) 3709);
            reference_beat1.add((long) 3970);
            reference_beat1.add((long) 4597);

        } else if (songs.size() == 2) {
            reference_beat1.add((long) 2925);
            reference_beat1.add((long) 3578);
            reference_beat1.add((long) 4310);
            reference_beat1.add((long) 4675);
            reference_beat1.add((long) 5302);

        } else {
            reference_beat1.add((long) 3030);
            reference_beat1.add((long) 3369);
            reference_beat1.add((long) 3996);
            reference_beat1.add((long) 4440);
            reference_beat1.add((long) 4702);
            reference_beat1.add((long) 5381);


        }
    }

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


    public void play(View view) {
        udotsInvisible();
        user_beat.clear();

        // Play the first song of the ArrayList
        //createMusic();
        songs.get(0).start();

        // Make Restart invisible during song is playing again
        Button start_button = findViewById(R.id.startGame);
        start_button.setVisibility(View.INVISIBLE);


        fillReference1();
        long reference_beat_end = 7000;


        // add the points
        int line_width = this.findViewById(R.id.View03).getMeasuredWidth();
        int[] location = new int[2];
        this.findViewById(R.id.View03).getLocationInWindow(location);
        int line_start = location[0];



        ArrayList<Button> button_list = new ArrayList<Button>();
        button_list.add((Button)findViewById(R.id.dot_1));
        button_list.add((Button)findViewById(R.id.dot_2));
        button_list.add((Button)findViewById(R.id.dot_3));
        button_list.add((Button)findViewById(R.id.dot_4));
        button_list.add((Button)findViewById(R.id.dot_5));
        button_list.add((Button)findViewById(R.id.dot_6));


        System.out.println("Number of songs:" + songs.size());

        for (int i = 0; i < button_list.size(); ++i) {
            if (i < reference_beat1.size()) {
                double point_x_percent = (double) (reference_beat1.get(i) - reference_beat1.get(0)) /
                        (double) (reference_beat_end - reference_beat1.get(0));
                int point_x = (int) (point_x_percent * (double) line_width + (double) line_start);
                Log.i("BEGINNER_ACTIVITY_BLA", String.valueOf(point_x));
                button_list.get(i).setX(point_x);                               //set button x position
                button_list.get(i).setVisibility(View.VISIBLE);                 // make button visible
            } else {
                button_list.get(i).setVisibility(View.INVISIBLE);               // make button invisible
            }
        }

        //reaching the active imageButton (play)
        ImageButton imgButton = findViewById(R.id.imageButton);

        imgButton.setImageResource(android.R.drawable.ic_media_play);
        //deactivate the button after first play - for later

        //What should happen when the song is over for the first time? -> all comes here
        songs.get(0).setOnCompletionListener(new MediaPlayer.OnCompletionListener() {


            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

                //songs.get(0).stop();
                //songs.get(0).release();
                //mediaPlayer.release();
                //mediaPlayer=null;

                //adding the start game button after song finishes for the first time
                // Button start_button = findViewById(R.id.startGame);
                // start_button.setVisibility(View.VISIBLE);

                Button start_button = findViewById(R.id.startGame);
                start_button.setText(getString(R.string.START));
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
        if (array1.size() != array2.size()){ //if the input arrays aren't same size
            System.out.print("in here!");
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

    public void playAndMatch(final View view) {

        //createMusic();
        //reaching the start game button to make hide it when the user starts the game
        Button start_button = findViewById(R.id.startGame);
        start_button.setVisibility(View.INVISIBLE);

        final long start_time_ms = System.currentTimeMillis();
        System.out.println("start time: " + start_time_ms);

        //beginnerSong.start();
        songs.get(0).start();

        //adding the green user_input button after song finishes
        Button userButton = findViewById(R.id.user_button);
        userButton.setVisibility(View.VISIBLE);
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("green button pressed.");
                final long click_time_ms = System.currentTimeMillis();
                final long user_beat_ms = click_time_ms - start_time_ms;
                System.out.println("user beat ms being added: " + user_beat_ms);
                user_beat.add(user_beat_ms);
                count = user_beat.size();
            }
        });

        //reaching the active imageButton (play)
        ImageButton imgButton = findViewById(R.id.imageButton);

        //changing the play button to pause while playing
        imgButton.setImageResource(android.R.drawable.ic_media_play);

        final TextView result_display = findViewById(R.id.result_view);
        result_display.setVisibility(View.GONE);

        //What should happen when the song is over? -> all comes here
        songs.get(0).setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @SuppressLint({"SetTextI18n", "ResourceAsColor"})
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

                //songs.get(0).stop();
                //songs.get(0).release();
                //mediaPlayer.release();
                //mediaPlayer=null;

                Button userButton = findViewById(R.id.user_button);
                userButton.setVisibility(View.INVISIBLE);

                Button start_button = findViewById(R.id.startGame);
                start_button.setText(getString(R.string.RESTART));
                start_button.setVisibility(View.VISIBLE);


                //changing the play button to replay after song finishes
                ImageButton imgButton = findViewById(R.id.imageButton);
                imgButton.setImageResource(R.drawable.replay);

                //refresh(for the next round) and define reference beat values
                fillReference1();
                System.out.println("reference beat list: " + reference_beat1);

                //calculate and output the result
                int game_result = compareArrays(reference_beat1, user_beat);
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
                        System.out.println(result);
                }

                long reference_beat_end = 7000;
                // add the points
                int line_width = findViewById(R.id.View04).getMeasuredWidth();
                int[] location = new int[2];
                findViewById(R.id.View04).getLocationInWindow(location);
                int line_start = location[0];

                ArrayList<Button> ubutton_list = new ArrayList<>();
                ubutton_list.add((Button) findViewById(R.id.udot_1));
                ubutton_list.add((Button) findViewById(R.id.udot_2));
                ubutton_list.add((Button) findViewById(R.id.udot_3));
                ubutton_list.add((Button) findViewById(R.id.udot_4));
                ubutton_list.add((Button) findViewById(R.id.udot_5));
                ubutton_list.add((Button) findViewById(R.id.udot_6));

             /*   ArrayList<Button> ubuttonr_list = new ArrayList<>();
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
                */


                for (int j = 0; j < ubutton_list.size(); ++j) {

                    if (j < user_beat.size()) {
                        double point_x_percent = (double) (user_beat.get(j) - user_beat.get(0)) /
                                (double) (reference_beat_end -user_beat.get(0));
                        int point_x = (int) (point_x_percent * (double) line_width + (double) line_start);
                        Log.i("BEGINNER_ACTIVITY_BLA", String.valueOf(point_x));
                        // if(user_beat.get(j)!= reference_beat1.get(j)){
                        //Button ubbutton_list.get(j)= findViewById(R.id.udotr_10);
                        //   ubutton_list.get(j).setButton(R.drawable.reddot);
                        // } else {
                        //     ubutton_list.set(j, ubuttonr_list.get(k));
                        //    k++;
                        //}
                        ubutton_list.get(j).setX(point_x);//set button x position
                        ubutton_list.get(j).setVisibility(View.VISIBLE);               // make button visible
                        Log.i("user_beat", String.valueOf(user_beat.get(j)));
                        System.out.println("Anzahl user Tips" +  user_beat.size());
                    } else {
                        ubutton_list.get(j).setVisibility(View.INVISIBLE);             // make button invisible
                    }

                }
                user_beat.clear();
                ubutton_list.clear();
                //               ubuttonr_list.clear();
            }
        });
    }

    public void NextSong(View view) {


        // Logic following
        if (songs.size() == 3) {
            songs.get(0).stop();
            songs.get(0).release();
            mediaPlayer.release();
            mediaPlayer=null;
            //songs.get(0).reset();
            //mediaPlayer.reset();
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
        } else /*if (songs.size() ==1)*/ {
            // songs.get(0).release();
            // songs.remove(0);
            Button NextSong = findViewById(R.id.NextSong);
            NextSong.setVisibility(View.GONE);
            NextActivity();
        }



        // Make all reference nodes invisible
        dotsInvisible();
        // Make all user nodes invisible
        udotsInvisible();



        //TODO : Replay Button to Play Button!
        //changing the play button to replay after song finishes
        ImageButton imgButton = findViewById(R.id.imageButton);
        imgButton.setImageResource(R.drawable.ic_media_play);


    }

    public void NextActivity(){
        Intent intent = new Intent(this,Congrats2.class);
        startActivity(intent);
    }
    protected void onPause() {
        // pause the song when closing app
        super.onPause();
        //songs.get(0).release();
        songs.get(0).stop();
    }

}



