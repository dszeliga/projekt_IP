package com.example.lastwerewolf.projekt_ip;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.lastwerewolf.projekt_ip.dopasuj.DopasujLvl1Activity;
import com.example.lastwerewolf.projekt_ip.dopasuj.DopasujLvl2Activity;
import com.example.lastwerewolf.projekt_ip.dopasuj.DopasujLvl3Activity;

public class ColorsLevelsManagerActivity extends AppCompatActivity {

    // Progi punktowe
    public final int pointsToUnlockFirstLevel = 60;
    public final int pointsToUnlockSecondLevel = 70;
    public final int pointsToUnlockThirdLevel = 90;

    // Liczba punktów zebranych w aplikacji
    private int allPoints;

    // Przyciski uruchamiające wybrany poziom gry Dopasuj
    public Button firstLevel;
    public Button secondLevel;
    public Button thirdLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors_levels_manager);

        allPoints = getSharedPreferences("POINTS_PREFERENCE", MODE_PRIVATE).getInt("points", 0);

        firstLevel = findViewById(R.id.firstLevel);
        secondLevel = findViewById(R.id.secondLevel);
        thirdLevel = findViewById(R.id.thirdLevel);

        // Uruchomienie gry dla poziomu 1
        firstLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToDopasujGame = new Intent(v.getContext(), DopasujLvl1Activity.class);
                Bundle b = new Bundle();
                b.putInt("key", 1); //Your id
                goToDopasujGame.putExtras(b);
                startActivity(goToDopasujGame);
                finish();
            }
        });

        // Odblokuj poziom 1, jeśli liczba punktów jest wystarczająca
        if(allPoints >= pointsToUnlockFirstLevel) {
            firstLevel.setBackground(new ColorDrawable(0x00000000));
            firstLevel.setEnabled(true);
        } else {
            firstLevel.setEnabled(false);
        }

        // Uruchomienie gry dla poziomu 2
        secondLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToPuzzlesGame = new Intent(v.getContext(), DopasujLvl2Activity.class);
                Bundle b = new Bundle();
                b.putInt("key", 2); //Your id
                goToPuzzlesGame.putExtras(b);
                startActivity(goToPuzzlesGame);
                finish();
            }
        });

        // Odblokuj poziom 2, jeśli liczba punktów jest wystarczająca
        if(allPoints >= pointsToUnlockSecondLevel) {
            secondLevel.setBackground(new ColorDrawable(0x00000000));
            secondLevel.setEnabled(true);
        } else {
            secondLevel.setEnabled(false);
        }

        // Uruchomienie gry dla poziomu 3
        thirdLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToLvl3 = new Intent(v.getContext(), DopasujLvl3Activity.class);
                Bundle b = new Bundle();
                b.putInt("key", 3); //Your id
                goToLvl3.putExtras(b);
                startActivity(goToLvl3);
                finish();
            }
        });

        // Odblokuj poziom 3, jeśli liczba punktów jest wystarczająca
        if(allPoints >= pointsToUnlockThirdLevel) {
            thirdLevel.setBackground(new ColorDrawable(0x00000000));
            thirdLevel.setEnabled(true);
        } else {
            thirdLevel.setEnabled(false);
        }
    }

    // Wróc do menu po wcisnięciu klawisza wstecz
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
