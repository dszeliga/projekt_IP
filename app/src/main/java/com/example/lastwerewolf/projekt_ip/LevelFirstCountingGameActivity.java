package com.example.lastwerewolf.projekt_ip;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static android.media.MediaPlayer.*;

public class LevelFirstCountingGameActivity extends AppCompatActivity {
Button b_answer1,b_answer2,b_answer3,b_answer4;

ImageView iv_zbiory;
List<StateModel>list;

Random r;
    int turn = 1;
    int rightAnswers = 0;
    private Typeface t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_first_counting_game);
        iv_zbiory = (ImageView) findViewById(R.id.iv_zbiory);
        r= new Random();



b_answer1= ( Button) findViewById(R.id.b_answer1);
b_answer2= ( Button) findViewById(R.id.b_answer2);
b_answer3= ( Button) findViewById(R.id.b_answer3);
b_answer4= ( Button) findViewById(R.id.b_answer4);

list = new ArrayList<>();

for( int i =0; i<new Database().answers.length;i++){

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
        if (b_answer1.getText().toString().equalsIgnoreCase(list.get(turn - 1).getName())) {
rightAnswers = rightAnswers +1;
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
                Toast.makeText(LevelFirstCountingGameActivity.this, "You finished the quiz", Toast.LENGTH_LONG).show();
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
        }
    }
});

        b_answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast toast;
                if (b_answer2.getText().toString().equalsIgnoreCase(list.get(turn - 1).getName())) {
                    // Toast.makeText(LevelFirstCountingGameActivity.this, "Correct", Toast.LENGTH_LONG).show();
                    rightAnswers = rightAnswers +1;
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
                        Toast.makeText(LevelFirstCountingGameActivity.this, " YOu finished the quiz", Toast.LENGTH_LONG).show();
                        getResults();
                    }
                }
            }
        });

        b_answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast toast;
                if (b_answer3.getText().toString().equalsIgnoreCase(list.get(turn - 1).getName())) {
                    //Toast.makeText(LevelFirstCountingGameActivity.this, "Correct", Toast.LENGTH_LONG).show();
                    rightAnswers = rightAnswers +1;
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
                        Toast.makeText(LevelFirstCountingGameActivity.this, " YOu finished the quiz", Toast.LENGTH_LONG).show();
                        getResults();
                    }
                }

            }
        });

        b_answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast;
                if (b_answer4.getText().toString().equalsIgnoreCase(list.get(turn - 1).getName())) {
                    // Toast.makeText(LevelFirstCountingGameActivity.this, "Correct", Toast.LENGTH_LONG).show();
                    rightAnswers = rightAnswers +1;
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
                        Toast.makeText(LevelFirstCountingGameActivity.this, " YOu finished the quiz", Toast.LENGTH_LONG).show();

                        getResults();
                    }
                }

            }
        });

    }
   private void newQuestion(int number){
        iv_zbiory.setImageResource(list.get(number - 1).getImage());

        int correct_answer = r.nextInt(4) + 1;
        int firstButton = number - 1;
        int secondButton;
        int thirdButton;
        int fourthButton;

        switch (correct_answer){
            case 1:
                b_answer1.setText(list.get(firstButton).getName());

                do {
                    secondButton = r.nextInt(list.size());
                }while (secondButton == firstButton);
                do {
                    thirdButton = r.nextInt(list.size());
                }while (thirdButton == firstButton || thirdButton == secondButton);
                do{
                    fourthButton = r.nextInt(list.size());
                }while (fourthButton == firstButton || fourthButton == secondButton || fourthButton == thirdButton);

                b_answer2.setText(list.get(secondButton).getName());
                b_answer3.setText(list.get(thirdButton).getName());
                b_answer4.setText(list.get(fourthButton).getName());

                break;
            case  2:
                b_answer2.setText(list.get(firstButton).getName());

                do {
                    secondButton = r.nextInt(list.size());
                }while (secondButton == firstButton);
                do {
                    thirdButton = r.nextInt(list.size());
                }while (thirdButton == firstButton || thirdButton == secondButton);
                do{
                    fourthButton = r.nextInt(list.size());
                }while (fourthButton == firstButton || fourthButton == secondButton || fourthButton == thirdButton);

                b_answer1.setText(list.get(secondButton).getName());
                b_answer3.setText(list.get(secondButton).getName());
                b_answer4.setText(list.get(fourthButton).getName());

                break;
            case 3:
                b_answer3.setText(list.get(firstButton).getName());

                do {
                    secondButton = r.nextInt(list.size());
                }while (secondButton == firstButton);
                do {
                    thirdButton = r.nextInt(list.size());
                }while (thirdButton == firstButton || thirdButton == secondButton);
                do{
                    fourthButton = r.nextInt(list.size());
                }while (fourthButton == firstButton || fourthButton == secondButton || fourthButton == thirdButton);

                b_answer2.setText(list.get(secondButton).getName());
                b_answer1.setText(list.get(thirdButton).getName());
                b_answer4.setText(list.get(fourthButton).getName());
                break;
            case 4:
                b_answer4.setText(list.get(firstButton).getName());

                do {
                    secondButton = r.nextInt(list.size());
                }while (secondButton == firstButton);
                do {
                    thirdButton = r.nextInt(list.size());
                }while (thirdButton == firstButton || thirdButton == secondButton);
                do{
                    fourthButton = r.nextInt(list.size());
                }while (fourthButton == firstButton || fourthButton == secondButton || fourthButton == thirdButton);

                b_answer2.setText(list.get(secondButton).getName());
                b_answer3.setText(list.get(thirdButton).getName());
                b_answer1.setText(list.get(fourthButton).getName());
                break;
        }

   }
   public void getResults(){
        Intent intent = new Intent(getApplicationContext(),ResultActivity.class);
        intent.putExtra("Odpowiedzi prawidłowe",rightAnswers);
        intent.putExtra("Gra", "cyfry");
        startActivity(intent);
   }
}
