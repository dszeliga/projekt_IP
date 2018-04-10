package com.example.lastwerewolf.projekt_ip;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private Button resetButton;

    private RadioButton underFive;
    private RadioButton aboveFive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        resetButton = findViewById(R.id.resetPointsBtn);
        aboveFive = findViewById(R.id.aboveFive);
        underFive = findViewById(R.id.underFive);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder exitMessage = new AlertDialog.Builder(SettingsActivity.this);
                exitMessage.setMessage("Czy jesteś pewien, że chcesz zresetować ilość punktów? Spowoduje to utratę obecnego postępu w grze oraz dostępność wyższych leveli.")
                        .setTitle("UWAGA!");

                exitMessage.setPositiveButton("Resetuj", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(SettingsActivity.this, "Zresetowano", Toast.LENGTH_LONG).show();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
