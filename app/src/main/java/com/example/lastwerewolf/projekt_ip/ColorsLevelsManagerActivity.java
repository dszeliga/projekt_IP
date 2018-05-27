package com.example.lastwerewolf.projekt_ip;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.lastwerewolf.projekt_ip.dopasuj.DopasujLvl1Activity;
import com.example.lastwerewolf.projekt_ip.dopasuj.MatchingPuzzlesGameActivity;
import com.example.lastwerewolf.projekt_ip.dopasuj.DopasujLvl3Activity;

public class ColorsLevelsManagerActivity extends AppCompatActivity {

    public final int pointsToUnlockFirstLevel = 0;
    public final int pointsToUnlockSecondLevel = 10;
    public final int pointsToUnlockThirdLevel = 20;

    private int allPoints;

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
        if(allPoints >= pointsToUnlockFirstLevel) {
            firstLevel.setBackground(new ColorDrawable(0x00000000));
            firstLevel.setEnabled(true);
        } else {
            firstLevel.setEnabled(false);
        }

        secondLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToPuzzlesGame = new Intent(v.getContext(), MatchingPuzzlesGameActivity.class);
                Bundle b = new Bundle();
                b.putInt("key", 2); //Your id
                goToPuzzlesGame.putExtras(b);
                startActivity(goToPuzzlesGame);
                finish();
            }
        });
        if(allPoints >= pointsToUnlockSecondLevel) {
            secondLevel.setBackground(new ColorDrawable(0x00000000));
            secondLevel.setEnabled(true);
        } else {
            secondLevel.setEnabled(false);
        }

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
        if(allPoints >= pointsToUnlockThirdLevel) {
            thirdLevel.setBackground(new ColorDrawable(0x00000000));
            thirdLevel.setEnabled(true);
        } else {
            thirdLevel.setEnabled(false);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
