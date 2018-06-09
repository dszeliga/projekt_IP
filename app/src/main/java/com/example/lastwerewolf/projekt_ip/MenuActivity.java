package com.example.lastwerewolf.projekt_ip;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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

    public boolean above7;
    public boolean isFirstRun;
    private boolean ageAbove7;
    private int allPoints;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //odwołanie do kontrolek w widoku
        memoGameBtn = findViewById(R.id.MemoGameBtn);
        coloursGameBtn = findViewById(R.id.ColoursGameBtn);
        countingGameBtn = findViewById(R.id.CountingGameBtn);
        settingsBtn = findViewById(R.id.settingsBtn);
        points = findViewById(R.id.txtPoints);

        //pobranie liczby ogólnych punktów
        allPoints = getSharedPreferences("POINTS_PREFERENCE", MODE_PRIVATE).getInt("points", 0);
        //wyświetlenie liczby punktów
        points.setText(allPoints + "");
        //pobranie informacji czy aplikacja jest uruchomiona po raz pierwszy
        isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isfirstrun", true);

        Bundle b = getIntent().getExtras();
        if (b != null)
            above7 = b.getBoolean("wiek");//pobranie informacji o module wieku

        if (isFirstRun) {
            //utworzenie okna wyboru wieku przy pierwszym uruchomieniu aplikacji
            AlertDialog.Builder chooseAge = new AlertDialog.Builder(this);
            chooseAge.setMessage("Wybierz moduł wieku, w którym chcesz rozpocząć przygodę! W każdej chwili możesz zmienić moduł w ustawieniach.")
                    .setTitle("Wybierz wiek");

            chooseAge.setPositiveButton("Powyżej 7 lat", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //ustawienie że wybrano wiek powyżej 7 lat
                    ageAbove7 = true;
                    getSharedPreferences("AGE_PREFERENCE", MODE_PRIVATE).edit().putBoolean("wiek", ageAbove7).commit();

                    //ustawienie dostępności gry w zależnosci od wieku
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
                    //ustawienie że wybrano wiek poniżej 7 lat
                    ageAbove7 = false;
                    getSharedPreferences("AGE_PREFERENCE", MODE_PRIVATE).edit().putBoolean("wiek", ageAbove7).commit();
                    dialog.cancel();
                }
            });

            AlertDialog dialog = chooseAge.create();//stworzenie okna
            dialog.show();//wyswietlenie okna
            //ustawienie informacji że aplikacja była już uruchomiona pierwszy raz
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isfirstrun", false).commit();
        }

        //pobranie informacji o wybranym module wieku
        above7 = getSharedPreferences("AGE_PREFERENCE", MODE_PRIVATE).getBoolean("wiek", true);


        memoGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToLevels = new Intent(v.getContext(), LevelsManagerActivity.class);
                goToLevels.putExtra("wiek", above7);//przekazanie informacji o module wieku
                finish();
                startActivity(goToLevels);//przejście do ekranu leveli memo
            }
        });

        // warunek odblokowania drugiej gry
        if (allPoints < 60) {
            coloursGameBtn.setEnabled(false);
        } else {
            coloursGameBtn.setEnabled(true);
            coloursGameBtn.setBackgroundColor(Color.TRANSPARENT);
        }

        coloursGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToLevels = new Intent(v.getContext(), ColorsLevelsManagerActivity.class);
                finish();
                startActivity(goToLevels);//przejscie do ekranu leveli gry dopasuj
            }
        });

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSettings = new Intent(v.getContext(), SettingsActivity.class);
                goToSettings.putExtra("wiek", above7);//przekazanie informacji o module wieku
                startActivity(goToSettings);//przejscie do ustawień gry
                finish();
            }
        });
        
        if(allPoints<110) {
            countingGameBtn.setEnabled(false);
        }
        else
        {
            countingGameBtn.setEnabled(true);
            countingGameBtn.setBackgroundColor(Color.TRANSPARENT);
        }



        //warunek odblokowania 3 gry
        if (allPoints < 110) {
            countingGameBtn.setEnabled(false);
        } else {
            countingGameBtn.setEnabled(true);
            countingGameBtn.setBackgroundColor(Color.TRANSPARENT);
        }

        countingGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCountingGame = new Intent(v.getContext(), CountingGameActivity.class);
                finish(); // jeśli chcesz, zlikwidować poprzednie activity, aby nie było go na stosie.
                startActivity(goToCountingGame);//przejscie do ekranu leveli nauki cyfr
            }
        });

    }

    //ustawienia okna wyjścia z aplikacji
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
