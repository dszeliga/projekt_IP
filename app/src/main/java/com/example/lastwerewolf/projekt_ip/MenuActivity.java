package com.example.lastwerewolf.projekt_ip;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.app.PendingIntent.getActivity;

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
                Intent goToLevels = new Intent(v.getContext(), LevelsManagerActivity.class);
                finish(); // jeśli chcesz, zlikwidować poprzednie activity, aby nie było go na stosie.
                startActivity(goToLevels);
            }
        });
    }

    public void onBackPressed() {


        AlertDialog.Builder exitMessage = new AlertDialog.Builder(this);
        exitMessage.setMessage("Czy jesteś pewien, że chcesz opuścić grę?")
                .setTitle("WYJŚCIE");

        exitMessage.setPositiveButton("Zakończ grę", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        exitMessage.setNegativeButton("Pozostań w grze", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                startActivity(new Intent(MenuActivity.this, MenuActivity.class));

            }
        });

        AlertDialog dialog = exitMessage.create();
        dialog.show();

    }

}
