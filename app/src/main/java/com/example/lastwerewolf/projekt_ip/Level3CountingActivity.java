package com.example.lastwerewolf.projekt_ip;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Level3CountingActivity extends AppCompatActivity {
    Button b_countinue;
    Button btn_speaker3;
    ImageView tv_question3;
    EditText et_answer;

    int score = 0;
    private boolean ageAbove7;
    List<Item3> questions3list;
    int curQuestion3 = 0;
    int wrong = 0;
    int turn = 0;
    private int value = -1;
    private int allPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level3_counting);
        ageAbove7 = getSharedPreferences("AGE_PREFERENCE", MODE_PRIVATE).getBoolean("wiek", true); //pobranie informacji o module wieku
        b_countinue = (Button) findViewById(R.id.b_continue);
        tv_question3 = (ImageView) findViewById(R.id.tv_question3);
        et_answer = (EditText) findViewById(R.id.et_answer);
        b_countinue.setVisibility(View.VISIBLE);
        questions3list = new ArrayList<>();
        btn_speaker3 = findViewById(R.id.btn_speaker3);
        btn_speaker3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allPoints=getSharedPreferences("POINTS_PREFERENCE", MODE_PRIVATE).getInt("points", 0);

                MediaPlayer ring = MediaPlayer.create(Level3CountingActivity.this, R.raw.soundlevel3);
                ring.start();//właczenie dźwięku
            }
        });
        // przypisanie odpowiedzi do pytania
        for (int i = 0; i < new Database_three_level().answer3.length; i++) {
            questions3list.add(new Item3(Database_three_level.answer3[i], Database_three_level.questions3[i]));
        }
        Collections.shuffle(questions3list);
        tv_question3.setImageResource(questions3list.get(curQuestion3).getQuestions3());
        et_answer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et_answer.getText().toString().equalsIgnoreCase(questions3list.get(curQuestion3).getAnswer3())) {
                    b_countinue.setVisibility(View.VISIBLE);
                    score = score + 2; //dodawanie 2 punktów dla poprawnej odpowiedzi
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });
        //przechoszenie do następnego pytania za przycisku DALEJ i wpisywanie odpowiedzi w pole
        b_countinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (curQuestion3 < (Database_three_level.questions3.length - 1)) {
                    curQuestion3++;
                    tv_question3.setImageResource(questions3list.get(curQuestion3).getQuestions3());
                    b_countinue.setVisibility(View.VISIBLE);
                    et_answer.setText("");
                }
                //losowanie odpowiedzi z listy i przechodzenie do następnej
                if (turn < questions3list.size()) {
                    turn++;
                }
                //po pięciu odsłonach danego levelu przejscie do widoku z wynikiem
                if (turn == 5) {
                    getResults();
                }
            }

        });
    }

    public void getResults() {
        //przejście do ekranu wyniku
        if (score == 0) {
            Intent intent = new Intent(getApplicationContext(), GiffActivityFailActivity.class);
            intent.putExtra("Odpowiedzi prawidłowe", score);//przekazanie informacji o ilości uzyskanych punktów
          intent.putExtra("level", value);//przekazanie informacji o levelu

            intent.putExtra("Gra", "cyfry3");//przekazanie informacji o grze
            intent.putExtra("wiek", ageAbove7); //przekazanie informacji o module wieku
            startActivity(intent);
        } else {
            Intent intent = new Intent(getApplicationContext(), GiffActivity.class);
            intent.putExtra("Odpowiedzi prawidłowe", score);//przekazanie informacji o ilości uzyskanych punktów
           intent.putExtra("level", value);//przekazanie informacji o levelu

            intent.putExtra("Gra", "cyfry3");//przekazanie informacji o grze
            intent.putExtra("wiek", ageAbove7); //przekazanie informacji o module wieku
            startActivity(intent);
        }
    }
}
