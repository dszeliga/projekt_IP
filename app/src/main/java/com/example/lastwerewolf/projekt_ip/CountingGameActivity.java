package com.example.lastwerewolf.projekt_ip;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CountingGameActivity extends GameActivity {
public Button Level1;
public Button Level3;
public Button Level2;
    public void PlaySound() {}

    public Image ReadImage(String sciezka) {return null;}

    public void DisplayImage() {}

    public void RandomNumbers(){}

    public void CheckAsnwer() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counting_game);
        Level1= findViewById(R.id.level1);
        Level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCountingLevel1;
                goToCountingLevel1 = new Intent(v.getContext(), LevelFirstCountingGameActivity.class);
                // jeśli chcesz, zlikwidować poprzednie activity, aby nie było go na stosie.


                startActivity(goToCountingLevel1);
            }
        });
        Level3= findViewById(R.id.level3);
        Level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCountingLevel2;
                goToCountingLevel2 = new Intent(v.getContext(), Level3CountingActivity.class);
                // jeśli chcesz, zlikwidować poprzednie activity, aby nie było go na stosie.


                startActivity(goToCountingLevel2);
            }
        });
        Level2= findViewById(R.id.level2);
        Level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCountingLevel2;
                goToCountingLevel2 = new Intent(v.getContext(), LevelThirdCountingGameActivity.class);
                // jeśli chcesz, zlikwidować poprzednie activity, aby nie było go na stosie.


                startActivity(goToCountingLevel2);
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
