package com.example.lastwerewolf.projekt_ip;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GiffActivity extends AppCompatActivity {

    private String game;
    private int level = 0;
    private boolean countPoints = false;
    private int nextLVL = 0;
    private int giff = 0;
    public int goodAnswers;
    public boolean ageAbove7;
    private String string;
    private TextView tv;
    private int allPoints;
    private boolean firstLvlUnlock, secondLvlUnlock, thirdLvlUnlock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giff);

        tv = findViewById(R.id.textView4);
        game = getIntent().getStringExtra("Gra");
        level = getIntent().getIntExtra("level", 0);
        giff = getIntent().getIntExtra("gif", 0);
        goodAnswers = getIntent().getIntExtra("Odpowiedzi prawidłowe", 0);
        ageAbove7 = getSharedPreferences("AGE_PREFERENCE", MODE_PRIVATE).getBoolean("wiek", true);
        allPoints = getSharedPreferences("POINTS_PREFERENCE", MODE_PRIVATE).getInt("points", 0);

        firstLvlUnlock = getSharedPreferences("LVL1_PREFERENCE", MODE_PRIVATE).getBoolean("lvl1", false);
        secondLvlUnlock = getSharedPreferences("LVL2_PREFERENCE", MODE_PRIVATE).getBoolean("lvl2", false);
        thirdLvlUnlock = getSharedPreferences("LVL3_PREFERENCE", MODE_PRIVATE).getBoolean("lvl3", false);


        List<Integer> lvls = Arrays.asList(0, 10, 40, 60, 70, 80, 100, 115, 130);
        int currentLVL = lvls.size();
        int nextLVL = lvls.size() + 1;
        for (int i = 0; i < lvls.size(); i++) {
            if(lvls.get(i) > allPoints) {
                currentLVL = i;
                break;
            }
        }
        for (int i = 0; i < lvls.size(); i++) {
            if(lvls.get(i) > allPoints + goodAnswers) {
                nextLVL = i;
                break;
            }
        }

        int countableLVL = 0;
        if(game.equals("dopasuj")) {
            countableLVL += 3;
        } else if(game.equals("cyfry") || game.equals("cyfry2") || game.equals("cyfry3")) {
            countableLVL += 6;
        }
        countableLVL += level;

        countPoints = countableLVL == currentLVL;

        if(countPoints && currentLVL != nextLVL) {
            this.nextLVL = nextLVL;
            if(currentLVL % 3 == 0) {
                if(currentLVL != lvls.size()) {
                    tv.setText("ODBLOKOWANO NOWĄ GRĘ");
                } else {
                    tv.setText("WYGRAŁEŚ!");
                }

            } else {
                tv.setText("ODBLOKOWANO NOWY POZIOM");
            }
        }

        final Handler handler = new Handler();

        if (countPoints && currentLVL != nextLVL) {

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    GoToResultView();
                    finish();
                }
            }, 2500);
            MediaPlayer ring = MediaPlayer.create(GiffActivity.this, R.raw.gifsound);
            ring.start();

        } else {
            GoToResultView();
        }
    }

    private void GoToResultView() {
        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
        intent.putExtra("Odpowiedzi prawidłowe", goodAnswers);//przekazanie informacji o ilości uzyskanych punktów
        intent.putExtra("Gra", game);//przekazanie informacji o grze
        intent.putExtra("level", level);//przekazanie informacji o levelu
        intent.putExtra("countPoints", countPoints);
        intent.putExtra("lvlUnlocked", nextLVL);
        startActivity(intent);
    }

}
