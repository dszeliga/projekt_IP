package com.example.lastwerewolf.projekt_ip;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.lastwerewolf.projekt_ip.dopasuj.DopasujLvl1Activity;
import com.example.lastwerewolf.projekt_ip.dopasuj.MatchingPuzzlesGameActivity;
import com.example.lastwerewolf.projekt_ip.dopasuj.DopasujLvl3Activity;

public class ColorsLevelsManagerActivity extends AppCompatActivity {

    public Button firstLevel;
    public Button secondLevel;
    public Button thirdLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors_levels_manager);


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
//                finish();
            }
        });

        secondLevel.setEnabled(true);

        secondLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToPuzzlesGame = new Intent(v.getContext(), MatchingPuzzlesGameActivity.class);
                Bundle b = new Bundle();
                b.putInt("key", 2); //Your id
                goToPuzzlesGame.putExtras(b);
                startActivity(goToPuzzlesGame);
//                finish();
            }
        });


        thirdLevel.setEnabled(true);

        thirdLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToLvl3 = new Intent(v.getContext(), DopasujLvl3Activity.class);
                Bundle b = new Bundle();
                b.putInt("key", 3); //Your id
                goToLvl3.putExtras(b);
                startActivity(goToLvl3);
                //                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
