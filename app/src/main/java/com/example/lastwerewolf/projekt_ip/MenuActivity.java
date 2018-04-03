package com.example.lastwerewolf.projekt_ip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    public ColoursGameActivity GenerateColoursGame() {
        return null;
    }

    public CountingGameActivity GenerateCountingGame() {
        return null;
    }

    public MemoGameActivity GenerateMemoGame() {
        return null;
    }

    public Button memoGameBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        memoGameBtn = findViewById(R.id.MemoGameBtn);

        memoGameBtn.setOnClickListener(new View.OnClickListener() {
         @Override
           public void onClick(View v) {
                Intent goToMemoGame = new Intent(v.getContext(), MemoGameActivity.class);
                finish(); // jeśli chcesz, zlikwidować poprzednie activity, aby nie było go na stosie.
                startActivity(goToMemoGame);
         }
       });
    }
}
