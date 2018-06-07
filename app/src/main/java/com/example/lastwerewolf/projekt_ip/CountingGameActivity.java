package com.example.lastwerewolf.projekt_ip;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CountingGameActivity extends GameActivity {
public Button Level1;
public Button Level3;
public Button Level2;
public boolean above7;
private String gra;
private int level;
private int score;
private int allPoints;

    public void PlaySound() {}

    public Image ReadImage(String sciezka) {return null;}

    public void DisplayImage() {}

    public void RandomNumbers(){}

    public void CheckAsnwer() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counting_game);
        gra = getIntent().getStringExtra("Gra");//przekazanie informacji o grze
        level = getIntent().getIntExtra("level", 0);//przekazanie informacji o levelu
        above7=getSharedPreferences("AGE_PREFERENCE", MODE_PRIVATE).getBoolean("wiek", true);
        Level1= findViewById(R.id.level1);
        allPoints = getSharedPreferences("POINTS_PREFERENCE", MODE_PRIVATE).getInt("points", 0);
        score = getIntent().getIntExtra("Odpowiedzi prawidłowe", 0);

        //Deklaracja przycisku poziom 1 oraz pobranie informacji
        Level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCountingLevel1;  goToCountingLevel1 = new Intent(v.getContext(), LevelFirstCountingGameActivity.class);
                startActivity(goToCountingLevel1);//przejscie do ekranu levelu 1 nauki cyfr
                goToCountingLevel1.putExtra("gra", gra);//przekazanie informacji o grze
                goToCountingLevel1.putExtra("level", level);//przekazanie informacji o levelu
                MediaPlayer ring = MediaPlayer.create(CountingGameActivity.this, R.raw.soundlevel1);
                ring.start();//właczenie dźwięku
                startActivity(goToCountingLevel1);//przejscie do ekranu levelu 2 nauki cyfr

            }
        });

        Level2= findViewById(R.id.level2);
        //Deklaracja przycisku poziom 1 oraz pobranie informacji
        Level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent goToCountingLevel2;
                    goToCountingLevel2 = new Intent(v.getContext(), LevelSecondCountingGameActivity.class);
                    goToCountingLevel2.putExtra("gra", gra);//przekazanie informacji o grze
                    goToCountingLevel2.putExtra("level", level);//przekazanie informacji o levelu
                MediaPlayer ring = MediaPlayer.create(CountingGameActivity.this, R.raw.soundlevel2);
                ring.start();//właczenie dźwięku
                    startActivity(goToCountingLevel2);//przejscie do ekranu levelu 2 nauki cyfr
            }
        });
        Level3= findViewById(R.id.level3);

        if (above7) {
            Level3.setVisibility(View.VISIBLE);
        } else {
            Level3.setVisibility(View.INVISIBLE);
        }
        //Deklaracja przycisku poziom 1 oraz pobranie informacji
        Level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCountingLevel3;
                goToCountingLevel3 = new Intent(v.getContext(), Level3CountingActivity.class);
                MediaPlayer ring = MediaPlayer.create(CountingGameActivity.this, R.raw.soundlevel3);
                ring.start();//właczenie dźwięku
                startActivity(goToCountingLevel3);//przejscie do ekranu levelu 3 nauki cyfr

            }
        });

        if(allPoints<96) {
            Level2.setEnabled(false);
        }
        else
        {
            Level2.setEnabled(true);//właczenie możliwości ponownego kliknięcia
            Level2.setBackgroundColor(Color.TRANSPARENT);
        }
        if(allPoints<104) {
            Level3.setEnabled(false);
        }
        else
        {
            Level3.setEnabled(true);//właczenie możliwości ponownego kliknięcia
            Level3.setBackgroundColor(Color.TRANSPARENT);
        }

    }

    //przejście do głownego menu
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
        finish();
    }

}
