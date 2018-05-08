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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_first_counting_game);
        iv_zbiory = (ImageView) findViewById(R.id.iv_zbiory);
        r = new Random();

        zbiory = new Database().zbiory;
        b_answer1 = (Button) findViewById(R.id.b_answer1);
        b_answer2 = (Button) findViewById(R.id.b_answer2);
        b_answer3 = (Button) findViewById(R.id.b_answer3);
        b_answer4 = (Button) findViewById(R.id.b_answer4);

        list = new ArrayList<>();
        textColorDefaultRb = b_answer1.getTextColors();

        allImageButtons = new Button[]{b_answer1, b_answer2, b_answer3, b_answer4};

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
                    score = score + 1;
                    //Toast.makeText(LevelFirstCountingGameActivity.this, "Correct", Toast.LENGTH_LONG).show();
                    toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.CENTER, 0, 0);

                    tv = new TextView(LevelFirstCountingGameActivity.this);
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
                        // Toast.makeText(LevelFirstCountingGameActivity.this, "You finished the quiz", Toast.LENGTH_LONG).show();
                        getResults();
                    }
                } else {
                    //Toast.makeText(LevelFirstCountingGameActivity.this, "Incorrect", Toast.LENGTH_LONG).show();
                    toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.CENTER, 0, 0);

                    tv = new TextView(LevelFirstCountingGameActivity.this);
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

                    if (turn < list.size()) {

                        turn++;
                        newQuestion(turn);

                    } else {
                        Toast.makeText(LevelFirstCountingGameActivity.this, " YOu finished the quiz", Toast.LENGTH_LONG).show();
                        getResults();
                    }
                    if (6 == turn) {
                        getResults();
                    }
                }
            }
        });

        b_answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast toast;
                if (b_answer2.getText().toString().equalsIgnoreCase(indexImage+ "")) {
                    // Toast.makeText(LevelFirstCountingGameActivity.this, "Correct", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(LevelFirstCountingGameActivity.this, "You finished the quiz", Toast.LENGTH_LONG).show();
                        getResults();
                    }
                } else {
                    //Toast.makeText(LevelFirstCountingGameActivity.this, "Incorrect", Toast.LENGTH_LONG).show();
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

                    if (turn < list.size()) {

                        turn++;
                        newQuestion(turn);

                    } else {
                        // Toast.makeText(LevelFirstCountingGameActivity.this, " YOu finished the quiz", Toast.LENGTH_LONG).show();
                        getResults();
                    }

                    if (6 == turn) {
                        getResults();
                    }
                }
            }
        });

        b_answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast toast;
                if (b_answer3.getText().toString().equalsIgnoreCase(indexImage + "")) {
                    //Toast.makeText(LevelFirstCountingGameActivity.this, "Correct", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(LevelFirstCountingGameActivity.this, "You finished the quiz", Toast.LENGTH_LONG).show();
                        getResults();
                    }
                } else {
                    //Toast.makeText(LevelFirstCountingGameActivity.this, "Incorrect", Toast.LENGTH_LONG).show();
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
                        // Toast.makeText(LevelFirstCountingGameActivity.this, " YOu finished the quiz", Toast.LENGTH_LONG).show();
                        getResults();
                    }
                    if (6 == turn) {
                        getResults();
                    }
                }

            }
        });

        b_answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast;
                if (b_answer4.getText().toString().equalsIgnoreCase(indexImage+"")) {
                    // Toast.makeText(LevelFirstCountingGameActivity.this, "Correct", Toast.LENGTH_LONG).show();
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

                        getResults();
                    }
                } else {
                    //Toast.makeText(LevelFirstCountingGameActivity.this, "Incorrect", Toast.LENGTH_LONG).show();
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


                        getResults();
                    }
                }
                if (6 == turn) {
                    getResults();
                }

            }
        });

    }


    private void newQuestion(int number) {
        iv_zbiory.setImageResource(list.get(number - 1).getImage());

        //int correct_answer = r.nextInt(4) + 1;
        answers = new int[new Database().zbiory.length];
        for (int i = 0; i < answers.length; i++) {
            answers[i] = i + 1;
        }
        indexImage = rnd.nextInt(16) + 1;
        int randomImage = zbiory[indexImage - 1];

        iv_zbiory.setImageResource(randomImage);
        randomAnswers = new int[4];

        Random randomPlaces = new Random();
        int a = randomPlaces.nextInt(4);
        randomAnswers[a] = indexImage;

        for (int i = 0; i < randomAnswers.length; i++) {

            if (randomAnswers[i] == 0) {
                boolean isTheSameAnswer = false;
                int answer = rnd.nextInt(16) + 1;

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

        int firstButton = number - 1;
        int secondButton;
        int thirdButton;
        int fourthButton;


        /*switch (correct_answer) {
            case 1:
                b_answer1.setText(list.get(firstButton).getName());

                do {
                    secondButton = r.nextInt(list.size());
                } while (secondButton == firstButton);
                do {
                    thirdButton = r.nextInt(list.size());
                } while (thirdButton == firstButton || thirdButton == secondButton);
                do {
                    fourthButton = r.nextInt(list.size());
                }
                while (fourthButton == firstButton || fourthButton == secondButton || fourthButton == thirdButton);

                b_answer2.setText(list.get(secondButton).getName());
                b_answer3.setText(list.get(thirdButton).getName());
                b_answer4.setText(list.get(fourthButton).getName());

                break;
            case 2:
                b_answer2.setText(list.get(firstButton).getName());

                do {
                    secondButton = r.nextInt(list.size());
                } while (secondButton == firstButton);
                do {
                    thirdButton = r.nextInt(list.size());
                } while (thirdButton == firstButton || thirdButton == secondButton);
                do {
                    fourthButton = r.nextInt(list.size());
                }
                while (fourthButton == firstButton || fourthButton == secondButton || fourthButton == thirdButton);

                b_answer1.setText(list.get(secondButton).getName());
                b_answer3.setText(list.get(thirdButton).getName());
                b_answer4.setText(list.get(fourthButton).getName());

                break;
            case 3:
                b_answer3.setText(list.get(firstButton).getName());

                do {
                    secondButton = r.nextInt(list.size());
                } while (secondButton == firstButton);
                do {
                    thirdButton = r.nextInt(list.size());
                } while (thirdButton == firstButton || thirdButton == secondButton);
                do {
                    fourthButton = r.nextInt(list.size());
                }
                while (fourthButton == firstButton || fourthButton == secondButton || fourthButton == thirdButton);

                b_answer2.setText(list.get(secondButton).getName());
                b_answer1.setText(list.get(thirdButton).getName());
                b_answer4.setText(list.get(fourthButton).getName());
                break;
            case 4:
                b_answer4.setText(list.get(firstButton).getName());

                do {
                    secondButton = r.nextInt(list.size());
                } while (secondButton == firstButton);
                do {
                    thirdButton = r.nextInt(list.size());
                } while (thirdButton == firstButton || thirdButton == secondButton);
                do {
                    fourthButton = r.nextInt(list.size());
                }
                while (fourthButton == firstButton || fourthButton == secondButton || fourthButton == thirdButton);

                b_answer2.setText(list.get(secondButton).getName());
                b_answer3.setText(list.get(thirdButton).getName());
                b_answer1.setText(list.get(fourthButton).getName());
                break;

        }*/


        if (6 == turn) {
            getResults();
        }
    }


    public void getResults() {
        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
        intent.putExtra("Odpowiedzi prawidłowe", score);
        intent.putExtra("Gra", "cyfry");
        startActivity(intent);
    }

}
