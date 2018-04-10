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
                finish();
                startActivity(goToMemoGame);
            }
        });

        secondLevel.setEnabled(false);
        thirdLevel.setEnabled((false));
    }
}
