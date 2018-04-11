package com.example.lastwerewolf.projekt_ip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class LevelsManagerActivity extends AppCompatActivity {

    public Button firstLevel;
    public Button secondLevel;
    public Button thirdLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels_manager);


        firstLevel = findViewById(R.id.firstLevel);
        secondLevel = findViewById(R.id.secondLevel);
        thirdLevel = findViewById(R.id.thirdLevel);


        firstLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMemoGame = new Intent(v.getContext(), MemoGameActivity.class);
                Bundle b = new Bundle();
                b.putInt("key", 1); //Your id
                goToMemoGame.putExtras(b);
                startActivity(goToMemoGame);
                finish();
            }
        });

        secondLevel.setEnabled(true);

        secondLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMemoGame = new Intent(v.getContext(), MemoGameActivity.class);
                Bundle b = new Bundle();
                b.putInt("key", 2); //Your id
                goToMemoGame.putExtras(b);
                startActivity(goToMemoGame);
                finish();
            }
        });


        thirdLevel.setEnabled(true);

        thirdLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMemoGame = new Intent(v.getContext(), MemoGameActivity.class);
                Bundle b = new Bundle();
                b.putInt("key", 3); //Your id
                goToMemoGame.putExtras(b);
                startActivity(goToMemoGame);
                finish();
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
