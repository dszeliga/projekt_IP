package com.example.lastwerewolf.projekt_ip;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class GiffActivity extends AppCompatActivity {

    private String game;
    private int level = 0;
    private int giff = 0;
    public int goodAnswers;
    public boolean ageAbove7;
    private String string;
    private TextView tv;
    private int allPoints;
    private boolean firstLvlUnlock, secondLvlUnlock, thirdLvlUnlock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giff);

        tv = findViewById(R.id.textView4);
        game = getIntent().getStringExtra("Gra");
        level = getIntent().getIntExtra("level", 0);
        giff = getIntent().getIntExtra("gif", 0);
        goodAnswers = getIntent().getIntExtra("Odpowiedzi prawidłowe", 0);
        ageAbove7 = getSharedPreferences("AGE_PREFERENCE", MODE_PRIVATE).getBoolean("wiek", true);
        allPoints = getSharedPreferences("POINTS_PREFERENCE", MODE_PRIVATE).getInt("points", 0);

        firstLvlUnlock = getSharedPreferences("LVL1_PREFERENCE", MODE_PRIVATE).getBoolean("lvl1", false);
        secondLvlUnlock = getSharedPreferences("LVL2_PREFERENCE", MODE_PRIVATE).getBoolean("lvl2", false);
        thirdLvlUnlock = getSharedPreferences("LVL3_PREFERENCE", MODE_PRIVATE).getBoolean("lvl3", false);

        // %%%%%%%%%%%%%%%%%%%DO POPRAWY!!!!!!%%%%%%%%%%%%%%%%%%%%%
        // +  nową grę
        if (secondLvlUnlock == false && allPoints + goodAnswers >= 10) {
            tv.setText("ODBLOKOWANO NOWY POZIOM");
        }

        if (allPoints + goodAnswers > 80)
            tv.setText("ODBLOKOWANO NOWĄ GRĘ");
        //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

        final Handler handler = new Handler();
//USTAWEINIE KIEDY JEST ODBLOKOWANIE LEVELU
        if ((secondLvlUnlock == false && game.equals("memo") && level == 1 && allPoints + goodAnswers >= 10) ||
                (thirdLvlUnlock && game.equals("memo") && level == 2 && allPoints + goodAnswers >= 50)) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("Odpowiedzi prawidłowe", goodAnswers);//przekazanie informacji o ilości uzyskanych punktów
                    intent.putExtra("Gra", game);//przekazanie informacji o grze
                    intent.putExtra("level", level);//przekazanie informacji o levelu
                    startActivity(intent);

                    finish();
                }
            }, 2500);
            MediaPlayer ring = MediaPlayer.create(GiffActivity.this, R.raw.gifsound);
            ring.start();
        } else {
            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            intent.putExtra("Odpowiedzi prawidłowe", goodAnswers);//przekazanie informacji o ilości uzyskanych punktów
            intent.putExtra("Gra", game);//przekazanie informacji o grze
            intent.putExtra("level", level);//przekazanie informacji o levelu
            startActivity(intent);
        }
    }

}
