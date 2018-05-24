package com.example.lastwerewolf.projekt_ip;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static android.media.MediaPlayer.*;

public class LevelFirstCountingGameActivity extends AppCompatActivity {
    Button b_answer1, b_answer2, b_answer3, b_answer4;
    private Random rnd = new Random();
    ImageView iv_zbiory;
    List<StateModel> list;
    Button btn_speaker4;
    Random r;
    int turn = 1;
    int score = 0;


    int indexImage = 0;
    private Typeface t;
    private int[] zbiory;
    private int[] answers = null;
    private int[] randomAnswers = null;

    private int[] imagesInPlaces = null;
    private int[] randomlyImages = null;
    //private int[] randomlyPlaces = null;
    private Button[] allImageButtons = null;


    private ColorStateList textColorDefaultRb;
    private int questionCounter;
    private int questionCountTotal;
    private StateModel currentQuestion;
    private boolean ageAbove7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_first_counting_game);
        iv_zbiory = (ImageView) findViewById(R.id.iv_zbiory);
        ageAbove7=getIntent().getBooleanExtra("wiek", true);
        r = new Random();

        zbiory = new Database().zbiory;
        b_answer1 = (Button) findViewById(R.id.b_answer1);
        b_answer2 = (Button) findViewById(R.id.b_answer2);
        b_answer3 = (Button) findViewById(R.id.b_answer3);
        b_answer4 = (Button) findViewById(R.id.b_answer4);

        list = new ArrayList<>();
        textColorDefaultRb = b_answer1.getTextColors();

        allImageButtons = new Button[]{b_answer1, b_answer2, b_answer3, b_answer4};


        btn_speaker4 = findViewById(R.id.btn_speaker4);

        btn_speaker4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer ring = MediaPlayer.create(LevelFirstCountingGameActivity.this, R.raw.error);
                ring.start();
            }
        });

        //imagesInPlaces = new int[allImageButtons.length];
        for (int i = 0; i < new Database().answers.length; i++) {

            list.add(new StateModel(new Database().answers[i], new Database().zbiory[i]));
        }


        Collections.shuffle(list);

        newQuestion(turn);

        b_answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast toast;
                TextView tv;
                Typeface t;
                if (b_answer1.getText().toString().equalsIgnoreCase(indexImage+"")) {
                    bravoInformation();
                } else {
                    mistakeInformation();
                }
                if (6 == turn) {
                    if(score>=3){
                        getResults();
                    }else{
                        getResults1();
                    }
                }
            }
        });

        b_answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast toast;
                if (b_answer2.getText().toString().equalsIgnoreCase(indexImage+ "")) {
                    bravoInformation();
                } else {
                    mistakeInformation();
                }
                if (6 == turn) {
                    if(score>=3){
                        getResults();
                    }else{
                        getResults1();
                    }
                }
            }
        });

        b_answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast toast;
                if (b_answer3.getText().toString().equalsIgnoreCase(indexImage + "")) {
                    bravoInformation();
                } else {
                    mistakeInformation();
                }
                if (6 == turn) {
                    if(score>=3){
                        getResults();
                    }else{
                        getResults1();
                    }
                }
            }
        });

        b_answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast;
                if (b_answer4.getText().toString().equalsIgnoreCase(indexImage+"")) {
                    bravoInformation();
                } else {
                    mistakeInformation();
                }
                if (6 == turn) {
                    if(score>=3){
                        getResults();
                    }else{
                        getResults1();
                    }
                }
            }
        });

    }

    private void mistakeInformation(){
        Toast toast;
        toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);

        TextView tv = new TextView(LevelFirstCountingGameActivity.this);
        tv.setBackgroundColor(Color.RED);
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(36);

        t = Typeface.create("serif", Typeface.BOLD);
        tv.setTypeface(t);
        tv.setPadding(40, 40, 40, 40);
        tv.setText("Błąd!");
        toast.setView(tv);
        toast.show();
        MediaPlayer ring = MediaPlayer.create(LevelFirstCountingGameActivity.this, R.raw.error);
        ring.start();
        ring = MediaPlayer.create(LevelFirstCountingGameActivity.this, R.raw.error);
        ring.start();

        if (turn < list.size()) {
            turn++;
            newQuestion(turn);
        } else {
            if(score>=3){
                getResults();
            }else{
                getResults1();
            }
        }
    }

    private void bravoInformation(){
        Toast toast;
        score = score + 1;

        toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);

        TextView tv = new TextView(LevelFirstCountingGameActivity.this);
        tv.setBackgroundColor(Color.GREEN);
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(36);

        t = Typeface.create("serif", Typeface.BOLD);
        tv.setTypeface(t);
        tv.setPadding(40, 40, 40, 40);
        tv.setText("Brawo!");
        toast.setView(tv);
        toast.show();
        MediaPlayer ring = MediaPlayer.create(LevelFirstCountingGameActivity.this, R.raw.bravo);
        ring.start();
        if (turn < list.size()) {
            turn++;
            newQuestion(turn);
        } else {
            if(score>=3){
                getResults();
            }else{
                getResults1();
            }
        }

    }

    private void newQuestion(int number) {

        iv_zbiory.setImageResource(list.get(number - 1).getImage());

        answers = new int[new Database().zbiory.length];
        for (int i = 0; i < answers.length; i++) {
            answers[i] = i + 1;
        }
        indexImage = rnd.nextInt(24) + 1;
        int randomImage = zbiory[indexImage - 1];

        iv_zbiory.setImageResource(randomImage);
        randomAnswers = new int[4];

        int a = rnd.nextInt(4);
        if(indexImage > 16)
        {
            indexImage = indexImage - 16;
        }
        randomAnswers[a] = indexImage;

        for (int i = 0; i < randomAnswers.length; i++) {

            if (randomAnswers[i] == 0) {
                boolean isTheSameAnswer = false;
                int answer = rnd.nextInt(24) + 1;
               if(answer >= 16)
                {
                    if(answer == 16)
                    {answer = 16;}
                    else
                    {answer = answer - 16;}
                }

                for (int j = 0; j < randomAnswers.length; j++) {
                    if (randomAnswers[j] == answer) {
                        isTheSameAnswer = true;
                        i--;
                    }
                }
                if (!isTheSameAnswer) {
                    randomAnswers[i] = answer;
                }
            }
        }
        b_answer1.setText(randomAnswers[0] + "");
        b_answer2.setText(randomAnswers[1] + "");
        b_answer3.setText(randomAnswers[2] + "");
        b_answer4.setText(randomAnswers[3] + "");

        if (6 == turn) {
            if(score>=3){
                getResults();
            }else{
                getResults1();
            }
        }

    }

    public void getResults() {
        Intent intent = new Intent(getApplicationContext(), GiffActivity.class );
        intent.putExtra("Odpowiedzi prawidłowe", score);//przekazanie informacji o ilości uzyskanych punktów
        intent.putExtra("Gra", "cyfry");
        intent.putExtra("Giff",0);

        startActivity(intent);
    }
    public void getResults1() {
        Intent intent = new Intent(getApplicationContext(), GiffActivityFailActivity.class );
        intent.putExtra("Odpowiedzi prawidłowe", score);
        intent.putExtra("Gra", "cyfry");
        intent.putExtra("Giff",0);

        startActivity(intent);
    }

}
