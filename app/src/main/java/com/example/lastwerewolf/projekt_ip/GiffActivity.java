package com.example.lastwerewolf.projekt_ip;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GiffActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giff);


        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),CountingGameActivity.class));

                finish();
            }
        },2500); MediaPlayer ring = MediaPlayer.create(GiffActivity.this, R.raw.gifsound);
        ring.start();
    }

}
