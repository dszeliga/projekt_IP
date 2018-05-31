package com.example.lastwerewolf.projekt_ip;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private Button resetButton;

    private RadioButton underSeven;
    private RadioButton aboveSeven;
    private RadioGroup radioGroup;
    private boolean chooseAgeAbove7;
    private int allPoints;
    private TextView pointsTxt;
    private boolean firstLvlUnlock, secondLvlUnlock, thirdLvlUnlock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        resetButton = findViewById(R.id.resetPointsBtn);
        aboveSeven = findViewById(R.id.aboveFive);
        underSeven = findViewById(R.id.underFive);
        radioGroup = findViewById(R.id.radioGroup);
        pointsTxt = findViewById(R.id.txtPoints);

        chooseAgeAbove7 = getIntent().getBooleanExtra("wiek", true);
        allPoints = getSharedPreferences("POINTS_PREFERENCE", MODE_PRIVATE).getInt("points", 0);

        pointsTxt.setText("" + allPoints);

        if (chooseAgeAbove7) {
            aboveSeven.setChecked(true);
            underSeven.setChecked(false);
        } else {
            underSeven.setChecked(true);
            aboveSeven.setChecked(false);
        }


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = findViewById(checkedId);

                if (rb == aboveSeven) {
                    Toast.makeText(getApplicationContext(), "Ustawiono powyzej 7 lat", Toast.LENGTH_SHORT).show();
                    chooseAgeAbove7 = true;
                    putChoosenAge(chooseAgeAbove7);
                } else {
                    Toast.makeText(getApplicationContext(), "Ustawiono ponizej 7 lat", Toast.LENGTH_SHORT).show();
                    chooseAgeAbove7 = false;
                    putChoosenAge(chooseAgeAbove7);
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder exitMessage = new AlertDialog.Builder(SettingsActivity.this);
                exitMessage.setMessage("Czy jesteś pewien, że chcesz zresetować ilość punktów? Spowoduje to utratę obecnego postępu w grze oraz dostępność wyższych leveli.")
                        .setTitle("UWAGA!");

                exitMessage.setPositiveButton("Resetuj", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(SettingsActivity.this, "Zresetowano", Toast.LENGTH_LONG).show();
                        allPoints = 0;
                        getSharedPreferences("POINTS_PREFERENCE", MODE_PRIVATE).edit().putInt("points", allPoints).commit();
                        pointsTxt.setText("" + allPoints);
                        firstLvlUnlock = false;
                        secondLvlUnlock = false;
                        thirdLvlUnlock = false;
                        getSharedPreferences("LVL1_PREFERENCE", MODE_PRIVATE).edit().putBoolean("lvl1", firstLvlUnlock).commit();
                        getSharedPreferences("LVL2_PREFERENCE", MODE_PRIVATE).edit().putBoolean("lvl2", secondLvlUnlock).commit();
                        getSharedPreferences("LVL3_PREFERENCE", MODE_PRIVATE).edit().putBoolean("lvl3", thirdLvlUnlock).commit();
                    }
                });
                exitMessage.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(SettingsActivity.this, SettingsActivity.class));
                    }
                });

                AlertDialog dialog = exitMessage.create();
                dialog.show();
            }
        });
    }

    public void putChoosenAge(boolean chooseAgeAbove7) {
        getSharedPreferences("AGE_PREFERENCE", MODE_PRIVATE).edit().putBoolean("wiek", chooseAgeAbove7).commit();
        boolean age = getSharedPreferences("AGE_PREFERENCE", MODE_PRIVATE).getBoolean("wiek", true);

        Intent goToMenu = new Intent(getApplicationContext(), MenuActivity.class);
        goToMenu.putExtra("wiek", age);
        startActivity(goToMenu);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
