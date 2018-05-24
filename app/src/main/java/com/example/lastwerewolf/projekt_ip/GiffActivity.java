package com.example.lastwerewolf.projekt_ip;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GiffActivity extends AppCompatActivity {

    private String game;
    private int level = 0;
    private int giff = 0;
    public int goodAnswers;
    public boolean ageAbove7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giff);

        game = getIntent().getStringExtra("Gra");
        level = getIntent().getIntExtra("level", 0);
        giff = getIntent().getIntExtra("gif", 0);
        goodAnswers = getIntent().getIntExtra("Odpowiedzi prawidłowe", 0);
        ageAbove7=getSharedPreferences("AGE_PREFERENCE", MODE_PRIVATE).getBoolean("wiek", true);

        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (game.equals("cyfry") || game.equals("cyfry2") || game.equals("cyfry3")) {
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("Odpowiedzi prawidłowe", goodAnswers);//przekazanie informacji o ilości uzyskanych punktów
                    intent.putExtra("Gra", game);//przekazanie informacji o grze
                    intent.putExtra("level", level);//przekazanie informacji o levelu
                    startActivity(intent);
                } else if (game.equals("memo")) {
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("Odpowiedzi prawidłowe", goodAnswers);//przekazanie informacji o ilości uzyskanych punktów
                    intent.putExtra("Gra", "memo");//przekazanie informacji o grze
                    intent.putExtra("level", level);//przekazanie informacji o levelu
                    startActivity(intent);
                }


                finish();
            }
        }, 2500);
        MediaPlayer ring = MediaPlayer.create(GiffActivity.this, R.raw.gifsound);
        ring.start();
    }

}
