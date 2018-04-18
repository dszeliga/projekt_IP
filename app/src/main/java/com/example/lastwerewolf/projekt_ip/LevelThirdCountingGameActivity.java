package com.example.lastwerewolf.projekt_ip;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class LevelThirdCountingGameActivity extends AppCompatActivity {
    ImageView tv_question;
    Button b_true, b_false;

    Database_two_level mQuestion1;
    int questionsLength;

    ArrayList<Item> questionslist;
    int currentQuestion = 0;
    boolean winner = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_third_counting_game);

        tv_question = (ImageView) findViewById(R.id.tv_question);
        b_true = (Button) findViewById(R.id.b_true);
        b_false = (Button) findViewById(R.id.b_false);

        mQuestion1 = new Database_two_level();
        questionsLength = mQuestion1.mQuestion1.length;

        questionslist = new ArrayList<>();

        //sev q


        for (int i = 0; i < new Database_two_level().mAnswer1.length; i++) {

            questionslist.add(new Item(new Database_two_level().mAnswer1[i], new Database_two_level().mQuestion1[i]));
        }
        Collections.shuffle(questionslist);

        setQuestion1(currentQuestion);

        b_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
if(checkQuestion1(currentQuestion)){
    currentQuestion++;
    if(currentQuestion<questionsLength){
      setQuestion1(currentQuestion);
        MediaPlayer ring = MediaPlayer.create(LevelThirdCountingGameActivity.this, R.raw.bravo);
        ring.start();
    }else {
    winner=true;
    endGame();

    }
}else{
    endGame();
    MediaPlayer ring = MediaPlayer.create(LevelThirdCountingGameActivity.this, R.raw.error);
    ring.start();
}
            }
        });

        b_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkQuestion1(currentQuestion)){
                    currentQuestion++;
                    if(currentQuestion<questionsLength){
                        setQuestion1(currentQuestion)
                        ;MediaPlayer ring = MediaPlayer.create(LevelThirdCountingGameActivity.this, R.raw.bravo);
                        ring.start();
                    }else {

                        winner=true;
                        endGame();

                    }
                }else{
                    endGame();
                    MediaPlayer ring = MediaPlayer.create(LevelThirdCountingGameActivity.this, R.raw.error);
                    ring.start();
                }
            }
        });
    }

    // show questions on the screen
    private void setQuestion1(int number) {
        tv_question.setImageResource(questionslist.get(number).getImage());

    }

    private boolean checkQuestion1(int number) {
      String answers1= questionslist.get(number).getName();
      return answers1.equals("true");
    }
private void endGame(){
        if(winner){
            Toast.makeText(this, "you win", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this,"you lost", Toast.LENGTH_SHORT).show();
           finish();
        }
}
}
