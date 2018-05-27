package com.example.lastwerewolf.projekt_ip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lastwerewolf.projekt_ip.dopasuj.DopasujLvl1Activity;
import com.example.lastwerewolf.projekt_ip.dopasuj.DopasujLvl3Activity;

import java.util.logging.Level;

public class ResultActivity extends AppCompatActivity {
    public Button Yes, No;
    public ImageButton Replay;
    private int value = 0;
    private String gra;
    private int level;
    private boolean ageAbove7;
    private int allPoints;
public int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

         score = getIntent().getIntExtra("Odpowiedzi prawidłowe", 0);

        TextView tv_result = findViewById(R.id.tv_result);


        Yes = findViewById(R.id.tak);
        No = findViewById(R.id.nie);
        Replay = findViewById(R.id.refrash);
        gra = getIntent().getStringExtra("Gra");
        level = getIntent().getIntExtra("level", 0);
        ageAbove7=getSharedPreferences("AGE_PREFERENCE", MODE_PRIVATE).getBoolean("wiek", true);
        allPoints = getSharedPreferences("POINTS_PREFERENCE", MODE_PRIVATE).getInt("points", 0);

        if (gra.equals("memo")) {
            tv_result.setText("+" + score + " pkt.");
        } else if (gra.equals("cyfry") || gra.equals("cyfry2") || gra.equals("cyfry3")) {
            tv_result.setText(score + "/10");
        } else if(gra.equals("dopasuj")) {
            tv_result.setText("+" + score + " pkt.");
        }

        allPoints += score;
        getSharedPreferences("POINTS_PREFERENCE", MODE_PRIVATE).edit().putInt("points", allPoints).commit();


            Yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (gra.equals("memo")) {
                        Intent goToLevels = new Intent(v.getContext(), LevelsManagerActivity.class);
                        goToLevels.putExtra("gra", gra);
                        goToLevels.putExtra("level", level);
                        goToLevels.putExtra("wiek", ageAbove7);
                        startActivity(goToLevels);
                    } else if (gra.equals("cyfry")) {

                              Intent goToCountingLevel1;
                              goToCountingLevel1 = new Intent(v.getContext(), CountingGameActivity.class);
                              goToCountingLevel1.putExtra("gra", gra);
                        goToCountingLevel1.putExtra("level", level);
                              startActivity(goToCountingLevel1);



                    } else if (gra.equals("cyfry3")) {

                        Intent goToCountingLevel3;
                        goToCountingLevel3 = new Intent(v.getContext(), CountingGameActivity.class);
                        goToCountingLevel3.putExtra("gra", gra);
                        goToCountingLevel3.putExtra("level", level);
                        startActivity(goToCountingLevel3);


                    } else if(gra.equals("cyfry2")) {

                        Intent goToCountingLevel2;
                        goToCountingLevel2 = new Intent(v.getContext(), CountingGameActivity.class);
                        goToCountingLevel2.putExtra("gra", gra);
                        goToCountingLevel2.putExtra("level", level);
                        startActivity(goToCountingLevel2);

                    } else if(gra.equals("dopasuj")) {
                        Intent goToDopasujMenu = new Intent(v.getContext(), ColorsLevelsManagerActivity.class);
                        goToDopasujMenu.putExtra("wiek", ageAbove7);
                        startActivity(goToDopasujMenu);
                    }
                    finish();
                }
            });

        No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMenu = new Intent(v.getContext(), MenuActivity.class);
                goToMenu.putExtra("wiek", ageAbove7);
                startActivity(goToMenu);
                finish();
            }
        });

        Replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gra.equals("memo")) {
                    Bundle b = getIntent().getExtras();
                    if (b != null)
                        value = b.getInt("level");

                    Intent goToMemoGame = new Intent(v.getContext(), MemoGameActivity.class);
                    Bundle b1 = new Bundle();
                    b1.putInt("key", value); //Your id
                    b1.putBoolean("wiek", ageAbove7);
                    goToMemoGame.putExtras(b1);
                    startActivity(goToMemoGame);
                    finish();
                } else if (gra.equals("cyfry")) {
                    Bundle b = getIntent().getExtras();
                    if (b != null)
                        value = b.getInt("level");

                    Intent goToMemoGame = new Intent(v.getContext(), LevelFirstCountingGameActivity.class);
                    Bundle b1 = new Bundle();
                    b1.putInt("key", value); //Your id
                    goToMemoGame.putExtras(b1);
                    startActivity(goToMemoGame);
                    finish();
                } else if (gra.equals("cyfry3")) {
                    Bundle b = getIntent().getExtras();
                    if (b != null)
                        value = b.getInt("level");

                    Intent goToCountingLevel3 = new Intent(v.getContext(), Level3CountingActivity.class);
                    Bundle b1 = new Bundle();
                    b1.putInt("key", value); //Your id
                    goToCountingLevel3.putExtras(b1);
                    startActivity(goToCountingLevel3);
                    finish();
                } else if (gra.equals("cyfry2")) {
                    Bundle b = getIntent().getExtras();
                    if (b != null)
                        value = b.getInt("level");

                    Intent goToCountingLevel2 = new Intent(v.getContext(), LevelThirdCountingGameActivity.class);
                    Bundle b1 = new Bundle();
                    b1.putInt("key", value); //Your id
                    goToCountingLevel2.putExtras(b1);
                    startActivity(goToCountingLevel2);
                    finish();
                } else if (gra.equals("dopasuj")) {
                    Intent goToDopasuj;
                    switch (level) {
                        case 1:
                            goToDopasuj = new Intent(v.getContext(), DopasujLvl1Activity.class);
                            break;
                        case 3:
                            goToDopasuj = new Intent(v.getContext(), DopasujLvl3Activity.class);
                            break;
                        default:
                            Intent goToMenu = new Intent(v.getContext(), MenuActivity.class);
                            goToMenu.putExtra("wiek", ageAbove7);
                            startActivity(goToMenu);
                            return;
                    }
                    goToDopasuj.putExtra("gra", gra);
                    goToDopasuj.putExtra("level", level);
                    startActivity(goToDopasuj);
                    finish();
                }
            }


        });
    }
}

