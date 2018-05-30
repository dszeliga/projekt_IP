package com.example.lastwerewolf.projekt_ip;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class LevelsManagerActivity extends AppCompatActivity {

    private Button firstLevel;
    private Button secondLevel;
    private Button thirdLevel;
    private boolean ageAbove7;
    private int allPoints;
    private boolean firstLvlUnlock, secondLvlUnlock, thirdLvlUnlock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels_manager);


        firstLevel = findViewById(R.id.firstLevel);
        secondLevel = findViewById(R.id.secondLevel);
        thirdLevel = findViewById(R.id.thirdLevel);
        ageAbove7 = getIntent().getBooleanExtra("wiek", true);//pobranie informacji o module wieku
        allPoints = getSharedPreferences("POINTS_PREFERENCE", MODE_PRIVATE).getInt("points", 0);

        firstLvlUnlock = getSharedPreferences("LVL1_PREFERENCE", MODE_PRIVATE).getBoolean("lvl1", false);
        secondLvlUnlock = getSharedPreferences("LVL2_PREFERENCE", MODE_PRIVATE).getBoolean("lvl2", false);
        thirdLvlUnlock = getSharedPreferences("LVL3_PREFERENCE", MODE_PRIVATE).getBoolean("lvl3", false);

        firstLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMemoGame = new Intent(v.getContext(), MemoGameActivity.class);//przejście do gry
                Bundle b = new Bundle();
                b.putInt("key", 1); //ustalenie poziomu gry
                b.putBoolean("wiek", ageAbove7);//przekazanie informacji o module wieku
                goToMemoGame.putExtras(b);
                startActivity(goToMemoGame);//przejście do gry
                finish();
            }
        });

        if (allPoints < 10) {
            secondLevel.setEnabled(false);
        } else {
            secondLevel.setEnabled(true);
            secondLevel.setBackgroundColor(Color.TRANSPARENT);
        }
        secondLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMemoGame = new Intent(v.getContext(), MemoGameActivity.class);
                Bundle b = new Bundle();
                b.putInt("key", 2); //ustalenie poziomu gry
                b.putBoolean("wiek", ageAbove7);//przekazanie informacji o module wieku
                goToMemoGame.putExtras(b);
                startActivity(goToMemoGame);//przejście do gry
                finish();
            }
        });


        if (allPoints < 50) {
            thirdLevel.setEnabled(false);
        } else {
            thirdLevel.setEnabled(true);
            thirdLevel.setBackgroundColor(Color.TRANSPARENT);
            thirdLvlUnlock = true;
            getSharedPreferences("LVL3_PREFERENCE", MODE_PRIVATE).edit().putBoolean("lvl3", thirdLvlUnlock).commit();
        }

        thirdLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMemoGame = new Intent(v.getContext(), MemoGameActivity.class);
                Bundle b = new Bundle();
                b.putInt("key", 3); //ustalenie poziomu gry
                b.putBoolean("wiek", ageAbove7);//przekazanie informacji o module wieku
                goToMemoGame.putExtras(b);
                startActivity(goToMemoGame);//przejście do gry
                finish();
            }
        });
    }

    //powrót do menu po kliknięciu przycisku "wróć"
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
