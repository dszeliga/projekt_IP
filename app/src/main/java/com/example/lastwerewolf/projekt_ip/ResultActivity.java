package com.example.lastwerewolf.projekt_ip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.logging.Level;

public class ResultActivity extends AppCompatActivity {
    public Button Yes;
    public Button No;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        int score = getIntent().getIntExtra("Odpowiedzi prawidłowe", 0);
        TextView tv_result = (TextView) findViewById(R.id.tv_result);
        tv_result.setText(score + "/10");

        Yes = findViewById(R.id.tak);
        No = findViewById(R.id.nie);
        Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gra = getIntent().getStringExtra("Gra");
                if (gra.equals("memo")) {
                    Intent goToMemoGame = new Intent(v.getContext(), LevelsManagerActivity.class);
                    startActivity(goToMemoGame);
                } else if(gra.equals("cyfry")) {

                    Intent goToCountingLevel1;
                    goToCountingLevel1 = new Intent(v.getContext(), CountingGameActivity.class);
                    startActivity(goToCountingLevel1);
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

    }
}

