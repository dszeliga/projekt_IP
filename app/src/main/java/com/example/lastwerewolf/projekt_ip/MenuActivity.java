package com.example.lastwerewolf.projekt_ip;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import static android.app.PendingIntent.getActivity;

public class MenuActivity extends AppCompatActivity {

    public Button countingGameBtn;
    private Button memoGameBtn;
    private Button coloursGameBtn;
    private ImageButton settingsBtn;
    private TextView points;
    private Button wordGamebtn;

    public boolean above7;
    public boolean isFirstRun;
    private boolean ageAbove7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        memoGameBtn = findViewById(R.id.MemoGameBtn);
        coloursGameBtn = findViewById(R.id.ColoursGameBtn);
        settingsBtn = findViewById(R.id.settingsBtn);
        points = findViewById(R.id.txtPoints);

        isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isfirstrun", true);

        Bundle b = getIntent().getExtras();

        if(b!=null)
           above7= b.getBoolean("wiek");

        if (isFirstRun) {
            AlertDialog.Builder chooseAge = new AlertDialog.Builder(this);
            chooseAge.setMessage("Wybierz moduł wieku, w którym chcesz rozpocząć przygodę! W każdej chwili możesz zmienić moduł w ustawieniach.")
                    .setTitle("Wybierz wiek");

            chooseAge.setPositiveButton("Powyżej 7 lat", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    ageAbove7=true;
                    getSharedPreferences("AGE_PREFERENCE", MODE_PRIVATE).edit().putBoolean("wiek", ageAbove7).commit();

                    if (ageAbove7) {
                        countingGameBtn.setVisibility(View.INVISIBLE);
                    } else {
                        countingGameBtn.setVisibility(View.VISIBLE);
                    }
                    dialog.cancel();
                }
            });
            chooseAge.setNegativeButton("Poniżej 7 lat", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    ageAbove7=false;
                    getSharedPreferences("AGE_PREFERENCE", MODE_PRIVATE).edit().putBoolean("wiek", ageAbove7).commit();

                    if (ageAbove7) {
                        countingGameBtn.setVisibility(View.INVISIBLE);
                    } else {
                        countingGameBtn.setVisibility(View.VISIBLE);
                    }
                    dialog.cancel();
                }
            });

            AlertDialog dialog = chooseAge.create();
            dialog.show();
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isfirstrun", false).commit();
        }


        above7=getSharedPreferences("AGE_PREFERENCE", MODE_PRIVATE).getBoolean("wiek", true);


        memoGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToLevels = new Intent(v.getContext(), LevelsManagerActivity.class);
                goToLevels.putExtra("wiek",above7);
                finish();
                startActivity(goToLevels);
            }
        });

        coloursGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToLevels = new Intent(v.getContext(), ColorsLevelsManagerActivity.class);
                finish();
                startActivity(goToLevels);
            }
        });

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSettings = new Intent(v.getContext(), SettingsActivity.class);
                goToSettings.putExtra("wiek", above7);
                startActivity(goToSettings);
                finish();
            }
        });

        countingGameBtn = findViewById(R.id.CountingGameBtn);
        countingGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCountingGame = new Intent(v.getContext(), CountingGameActivity.class);
                finish(); // jeśli chcesz, zlikwidować poprzednie activity, aby nie było go na stosie.
                startActivity(goToCountingGame);
            }
        });

/*        if (above7) {
            countingGameBtn.setVisibility(View.INVISIBLE);
        } else {
            countingGameBtn.setVisibility(View.VISIBLE);
        }*/
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
