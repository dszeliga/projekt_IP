package com.example.lastwerewolf.projekt_ip;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.provider.BlockedNumberContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.logging.Level;

public class CountingGameActivity extends GameActivity {
public Button Level1;
public Button Level3;
public Button Level2;
    public boolean above7;
    private String gra;
    private int level;
    private int score;
    private int allPoints;

    // public int score=0;
    public void PlaySound() {}

    public Image ReadImage(String sciezka) {return null;}

    public void DisplayImage() {}

    public void RandomNumbers(){}

    public void CheckAsnwer() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counting_game);
        gra = getIntent().getStringExtra("Gra");
        level = getIntent().getIntExtra("level", 0);
        above7=getSharedPreferences("AGE_PREFERENCE", MODE_PRIVATE).getBoolean("wiek", true);
        Level1= findViewById(R.id.level1);
        allPoints = getSharedPreferences("POINTS_PREFERENCE", MODE_PRIVATE).getInt("points", 0);

        score = getIntent().getIntExtra("Odpowiedzi prawidłowe", 0);


        Level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCountingLevel1;  goToCountingLevel1 = new Intent(v.getContext(), LevelFirstCountingGameActivity.class);
          ///      goToCountingLevel1.putExtra("Odpowiedzi prawidłowe", score);
                startActivity(goToCountingLevel1);

                goToCountingLevel1.putExtra("gra", gra);
                goToCountingLevel1.putExtra("level", level);


            }
        });
        Level3= findViewById(R.id.level3);


        if (above7) {
            Level3.setVisibility(View.VISIBLE);
        } else {
            Level3.setVisibility(View.INVISIBLE);
        }

        Level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCountingLevel2;
                goToCountingLevel2 = new Intent(v.getContext(), Level3CountingActivity.class);
                // jeśli chcesz, zlikwidować poprzednie activity, aby nie było go na stosie.


                startActivity(goToCountingLevel2);
            }
        });
        Level2= findViewById(R.id.level2);
        Level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // LevelFirstCountingGameActivity score = new LevelFirstCountingGameActivity();
          //      if(score.score>=3){
                   // Level2.setEnabled(true);
                    Intent goToCountingLevel2;
                    goToCountingLevel2 = new Intent(v.getContext(), LevelThirdCountingGameActivity.class);
                    goToCountingLevel2.putExtra("gra", gra);
                    goToCountingLevel2.putExtra("level", level);
                    startActivity(goToCountingLevel2);

                   // goToCountingLevel2.putExtra("Odpowiedzi prawidłowe", score.score);



               /* }else{
                    Level2.setEnabled(false);
                }*/


            }
        }); if(allPoints<106) {
            Level2.setEnabled(false);
        }
        else
        {
            Level2.setEnabled(true);
            Level2.setBackgroundColor(Color.TRANSPARENT);
        }
        if(allPoints<112) {
            Level3.setEnabled(false);
        }
        else
        {
            Level3.setEnabled(true);
            Level3.setBackgroundColor(Color.TRANSPARENT);
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
