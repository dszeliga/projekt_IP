package com.example.lastwerewolf.projekt_ip;

import android.content.Intent;
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
    Button btn_speaker2;
    Database_two_level mQuestion1;
    int questionsLength;
    int score = 0;


    ArrayList<Item> questionslist;
    int currentQuestion = 0;
    boolean winner = false;
    int turn = 1;
    int wrong=0;
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

        btn_speaker2 = findViewById(R.id.btn_speaker2);

        btn_speaker2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer ring = MediaPlayer.create(LevelThirdCountingGameActivity.this, R.raw.soundlevel2);
                ring.start();
            }
        });


        //sev q


        for (int i = 0; i < new Database_two_level().mAnswer1.length; i++) {

            questionslist.add(new Item(new Database_two_level().mAnswer1[i], new Database_two_level().mQuestion1[i]));
        }
        Collections.shuffle(questionslist);
        tv_question.setImageResource(questionslist.get(currentQuestion).getQuestions1());
        setQuestion1(currentQuestion);

        b_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
if(checkQuestion1(currentQuestion)){
    currentQuestion++;
    tv_question.setImageResource(questionslist.get(currentQuestion).getQuestions1());

    if(currentQuestion<questionsLength){
      setQuestion1(currentQuestion);
        MediaPlayer ring = MediaPlayer.create(LevelThirdCountingGameActivity.this, R.raw.bravo);
        ring.start();

        score= score +1;



    }
}else{
    //winner=false;
   currentQuestion++;
    tv_question.setImageResource(questionslist.get(currentQuestion).getQuestions1());
    // endGame();
    MediaPlayer ring = MediaPlayer.create(LevelThirdCountingGameActivity.this, R.raw.error);
    ring.start();


}if (currentQuestion==5){
                    getResults();
                }
            }
        });

        b_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((!checkQuestion1(currentQuestion))){
                    currentQuestion++;
                    tv_question.setImageResource(questionslist.get(currentQuestion).getQuestions1());
                    if(currentQuestion<questionsLength){
                        setQuestion1(currentQuestion)
                        ;MediaPlayer ring = MediaPlayer.create(LevelThirdCountingGameActivity.this, R.raw.bravo);
                        ring.start();
                        score= score +1;


                    }
                }else{
                    currentQuestion++;
                   tv_question.setImageResource(questionslist.get(currentQuestion).getQuestions1());
                   // winner=false;
                    MediaPlayer ring = MediaPlayer.create(LevelThirdCountingGameActivity.this, R.raw.error);
                    ring.start();
                }
                if (currentQuestion==5){
                    getResults();
                }
            }
        });

    }

    // show questions on the screen
    private void setQuestion1(int number) {
        tv_question.setImageResource(questionslist.get(number).getQuestions1());

    }

    private boolean checkQuestion1(int number) {
      String answers1= questionslist.get(number).getName();
      return answers1.equals("true");
    }



    public void getResults(){
        Intent intent = new Intent(getApplicationContext(),ResultActivity.class);
        intent.putExtra("Odpowiedzi prawidłowe",score);
        intent.putExtra("Gra", "cyfry2");
        startActivity(intent);
    }

}
