package com.example.lastwerewolf.projekt_ip;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GiffActivityFailActivity extends AppCompatActivity {
    private String game;
    private int level=0;
    public int gifffail=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giff_activity_fail);

        game = getIntent().getStringExtra("gra");
        level = getIntent().getIntExtra("level", 0);
gifffail=getIntent().getIntExtra("gifffail",0);
        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (game.equals("cyfry") || game.equals("cyfry2") || game.equals("cyfry3")) {
                    startActivity(new Intent(getApplicationContext(), CountingGameActivity.class));
                } else if (game.equals("memo")) {
                    startActivity(new Intent(getApplicationContext(), LevelsManagerActivity.class));
                }


                finish();
            }
        }, 2500);
        MediaPlayer ring = MediaPlayer.create(GiffActivityFailActivity.this, R.raw.sadsound);
        ring.start();
    }}




