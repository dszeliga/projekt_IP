package com.example.lastwerewolf.projekt_ip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.logging.Level;

public class ResultActivity extends AppCompatActivity {
    public Button Yes, No;
    public ImageButton Replay;
    private int value = 0;
    private String gra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        /*TextView tv_result = (TextView) findViewById(R.id.tv_result);
        Intent intent = getIntent();
        int score = intent.getIntExtra("score",0);
        //tv_result.setText("Twoj wynik "+ score);
*/
      int score = getIntent().getIntExtra("Odpowiedzi prawidłowe", 0);

        TextView tv_result = (TextView) findViewById(R.id.tv_result);
        tv_result.setText(score + "/5");


        Yes = findViewById(R.id.tak);
        No = findViewById(R.id.nie);
        Replay = findViewById(R.id.refrash);
        gra = getIntent().getStringExtra("Gra");
        Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (gra.equals("memo")) {
                    Intent goToMemoGame = new Intent(v.getContext(), LevelsManagerActivity.class);
                    startActivity(goToMemoGame);
                } else if (gra.equals("cyfry")) {

                    Intent goToCountingLevel1;
                    goToCountingLevel1 = new Intent(v.getContext(), CountingGameActivity.class);
                    startActivity(goToCountingLevel1);
                }else if (gra.equals("cyfry3")){
                    Intent goToCountingLevel3;
                    goToCountingLevel3 = new Intent(v.getContext(), CountingGameActivity.class);
                    startActivity(goToCountingLevel3);

                }else if (gra.equals("cyfry2")){
                    Intent goToCountingLevel2;
                    goToCountingLevel2 = new Intent(v.getContext(), CountingGameActivity.class);
                    startActivity(goToCountingLevel2);

                }
            }
        });
        No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMenu = new Intent(v.getContext(), MenuActivity.class);
                startActivity(goToMenu);
            }
        });

        Replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gra.equals("memo"))
                {
                    Bundle b = getIntent().getExtras();
                    if (b != null)
                        value = b.getInt("level");

                    Intent goToMemoGame = new Intent(v.getContext(), MemoGameActivity.class);
                    Bundle b1 = new Bundle();
                    b1.putInt("key", value); //Your id
                    goToMemoGame.putExtras(b1);
                    startActivity(goToMemoGame);
                    finish();
                } else if (gra.equals("cyfry"))
                {
                    Bundle b = getIntent().getExtras();
                    if (b != null)
                        value = b.getInt("level");

                    Intent goToMemoGame = new Intent(v.getContext(), LevelFirstCountingGameActivity.class);
                    Bundle b1 = new Bundle();
                    b1.putInt("key", value); //Your id
                    goToMemoGame.putExtras(b1);
                    startActivity(goToMemoGame);
                    finish();
                }else if (gra.equals("cyfry3"))
                {
                    Bundle b = getIntent().getExtras();
                    if (b != null)
                        value = b.getInt("level");

                    Intent goToCountingLevel3 = new Intent(v.getContext(), Level3CountingActivity.class);
                    Bundle b1 = new Bundle();
                    b1.putInt("key", value); //Your id
                    goToCountingLevel3.putExtras(b1);
                    startActivity(goToCountingLevel3);
                    finish();
                }else if (gra.equals("cyfry2"))
                {
                    Bundle b = getIntent().getExtras();
                    if (b != null)
                        value = b.getInt("level");

                    Intent goToCountingLevel2 = new Intent(v.getContext(), LevelThirdCountingGameActivity.class);
                    Bundle b1 = new Bundle();
                    b1.putInt("key", value); //Your id
                    goToCountingLevel2.putExtras(b1);
                    startActivity(goToCountingLevel2);
                    finish();
                }
            }
        });

    }


}

