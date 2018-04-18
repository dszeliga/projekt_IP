package com.example.lastwerewolf.projekt_ip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
public Button Tak;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Tak= findViewById(R.id.tak);
        Tak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCountingLevel1;
                goToCountingLevel1 = new Intent(v.getContext(), CountingGameActivity.class);
                // jeśli chcesz, zlikwidować poprzednie activity, aby nie było go na stosie.


                startActivity(goToCountingLevel1);
            }
        });

        TextView tv_result = (TextView) findViewById(R.id.tv_result);

        int score = getIntent().getIntExtra("Odpowiedzi prawidłowe",0);
        tv_result.setText(score+"/10");

    }
}
