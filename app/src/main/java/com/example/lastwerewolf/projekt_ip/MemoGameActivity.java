package com.example.lastwerewolf.projekt_ip;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

import static java.lang.Math.round;

public class MemoGameActivity extends GameActivity implements View.OnClickListener {
    private Random rnd = new Random();
    private ImageButton ib1, ib2, ib3, ib4, ib5, ib6, ib7, ib8, ib9, ib10, ib11, ib12, ib13, ib14, ib15, ib16;
    private Button resetButton;
    private TextView txt;

    private int choosePicture = 0;
    private int chooseButton = 0;
    private int choosenFirstImage = 0;
    private int choosenSecondImage = 0;
    private int choosenFirstButton = 0;
    private int choosenSecondButton = 0;
    private int foundNumber = 0;
    private int value = -1;
    private int score = 0;
    private int counterGames = 0;
    private int timeOnLevel = 0;
    private int timeToRevert = 0;
    private long timeToEnd = 0;
    private boolean isRunning = false;
    private boolean ageAbove7;
    private CountDownTimer gameTime, timeRevert;

    private int[] imagesInPlaces = null;
    private int[] randomlyImages = null;
    private int[] randomlyPlaces = null;
    private ImageButton[] allImageButtons = null;
    private int[] photos = {R.drawable.i1, R.drawable.i2, R.drawable.i3, R.drawable.i4, R.drawable.i5,
            R.drawable.i6, R.drawable.i7, R.drawable.i8, R.drawable.i9, R.drawable.i10, R.drawable.i11,
            R.drawable.i12, R.drawable.i13, R.drawable.i14, R.drawable.i15, R.drawable.i16, R.drawable.i17,
            R.drawable.i18, R.drawable.i19, R.drawable.i20, R.drawable.i21, R.drawable.i22};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //pobranie informacji o wybranym module wieku i poziomie gry
        Bundle b = getIntent().getExtras();
        if (b != null) {
            value = b.getInt("key");
            ageAbove7 = b.getBoolean("wiek");
        }
        //ustawienie czasu gry i widoku na podstawie wybranego modułu wieku
        if (value == 1) {
            setContentView(R.layout.activity_memo_game);
            if (ageAbove7) {
                timeOnLevel = 7000;
                timeToRevert = 4000;
            } else {
                timeOnLevel = 10000;
                timeToRevert = 4000;
            }

        } else if (value == 2) {
            setContentView(R.layout.activity_memo_game_lvl2);
            if (ageAbove7) {
                timeOnLevel = 25000;
                timeToRevert = 5000;
            } else {
                timeOnLevel = 35000;
                timeToRevert = 7000;
            }
        } else {
            setContentView(R.layout.activity_memo_game_lvl3);
            if (ageAbove7) {
                timeOnLevel = 50000;
                timeToRevert = 8000;
            } else {
                timeOnLevel = 70000;
                timeToRevert = 10000;
            }
        }
        txt = findViewById(R.id.infoTxt);
        resetButton = findViewById(R.id.ResetButton);
        //deklaracja liczby miejsc na wylosowane obrazki zależnie od poziomu trudności
        if (value == 3) {
            ib16 = findViewById(R.id.sixteenthImage);
            ib15 = findViewById(R.id.fifteenthImage);
            ib14 = findViewById(R.id.fourteenthImage);
            ib13 = findViewById(R.id.thirteenthImage);
            ib12 = findViewById(R.id.twelvethImage);
            ib11 = findViewById(R.id.eleventhImage);
            ib10 = findViewById(R.id.tenthImage);
            ib9 = findViewById(R.id.ninthImage);
        }
        if (value == 2 || value == 3) {
            ib8 = findViewById(R.id.eigthImage);
            ib7 = findViewById(R.id.seventhImage);
            ib6 = findViewById(R.id.sixthImage);
            ib5 = findViewById(R.id.fifthImage);
        }
        ib1 = findViewById(R.id.firstImage);
        ib2 = findViewById(R.id.secondImage);
        ib3 = findViewById(R.id.thirdImage);
        ib4 = findViewById(R.id.fourthImage);
        //stworzenie tablicy buttonów zależnie od wybranego poziomu trudności
        if (value == 1) {
            allImageButtons = new ImageButton[]{ib1, ib2, ib3, ib4};
        } else if (value == 2) {
            allImageButtons = new ImageButton[]{ib1, ib2, ib3, ib4, ib5, ib6, ib7, ib8};
        } else {
            allImageButtons = new ImageButton[]{ib1, ib2, ib3, ib4, ib5, ib6, ib7, ib8, ib9, ib10, ib11, ib12, ib13, ib14, ib15, ib16};
        }
        //uruchomienie gry
        StartMemoryGame();

    }

    public void StartMemoryGame() {
        foundNumber = 0;
        //uruchomienie dźwięku przy starcie gry
        MediaPlayer findPairs = MediaPlayer.create(MemoGameActivity.this, R.raw.znajdz);
        findPairs.start();
        resetButton.setVisibility(View.INVISIBLE);
        counterGames += 1;
        randomlyImages = RandomlyImages(); // Losowanie obrazów do wyswietlenia
        randomlyPlaces = RandomlyPlaces(); // Losowanie miejsc, w których wyświetlone będą obrazki
        imagesInPlaces = new int[randomlyPlaces.length]; //tablica obrazków na konretnych miejscach
        //ustawienie wylosowanych obrazków na wylosowanych miejscach
        int z = 0;
        int count = 0;
        for (int i = 0; i < imagesInPlaces.length; i++) {
            if (z == 2) {
                z = 0;
                count++;
            }
            imagesInPlaces[randomlyPlaces[i]] = randomlyImages[count];
            z++;
        }
        //wyświetlenie obrazków na buttonach
        for (int i = 0; i < allImageButtons.length; i++) {
            allImageButtons[i].setImageResource(imagesInPlaces[i]);
        }
        //odwrócenie obrazków
        RevertImages();
        //włączenie możliwości klikania na przyciski
        for (int i = 0; i < allImageButtons.length; i++) {
            allImageButtons[i].setEnabled(true);
        }
        //sprawdzenie czy wybrane obrazki są takie same
        CompareImages();
    }

    private int[] RandomlyImages() {
        int[] tab = new int[allImageButtons.length];
        //losowanie obrazków z puli i sprawdzenie czy obrazek nie został już wcześniej wylosowany
        for (int i = 0; i < tab.length; i++) {
            int r = rnd.nextInt(photos.length);
            boolean isTheSameImage = true;
            for (int j = 0; j < tab.length; j++) {
                if (tab[j] != 0)
                    if (photos[r] == tab[j])
                        isTheSameImage = false;
            }
            if (isTheSameImage == true)
                tab[i] = photos[r];
            else
                i--;
        }
        return tab;
    }

    private int[] RandomlyPlaces() {
        int[] tab = new int[allImageButtons.length];
        //losowanie miejsc z puli i sprawdzenie czy miejsce nie zostało już wcześniej wylosowane
        for (int i = 0; i < tab.length; i++) {
            int r = rnd.nextInt(tab.length) + 1;
            boolean isTheSamePlace = true;
            for (int j = 0; j < tab.length; j++) {
                if (r == tab[j]) {
                    isTheSamePlace = false;
                    j = tab.length;
                }
            }
            if (isTheSamePlace == true)
                tab[i] = r;
            else
                i--;
        }
        for (int i = 0; i < tab.length; i++)
            tab[i] = tab[i] - 1;
        return tab;
    }

    public void SetTimeGame() {
        //ustawienia czasu podczas gry
        gameTime = new CountDownTimer(timeOnLevel, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //ustawienie wyświetlania czasu co 1s
                if (isRunning == true)
                    timeToEnd = round(millisUntilFinished / 1000);
                txt.setText("" + timeToEnd);
            }
            //ustawienie wyświetlenia napisu i przycisku ponownego losowania oraz włączenia dźwięku po zakończeniu gry
            @Override
            public void onFinish() {
                if (!txt.equals("BRAWO!!!")) {
                    txt.setText("Czas minal!");
                    //wyłączenie możliwości klikania w przyciski
                    for (int i = 0; i < allImageButtons.length; i++) {
                        allImageButtons[i].setEnabled(false);
                    }
                    MediaPlayer timeUp = MediaPlayer.create(MemoGameActivity.this, R.raw.czasminal);
                    timeUp.start();
                    resetButton.setVisibility(View.VISIBLE);
                    score += 0;
                    isRunning = false;
                    //pobranie wyniku, jeśli gracz przeszedł odpowiednią ilość gier
                    if (counterGames == 2) {
                        getResults();
                    }
                }
            }
        }.start();
        isRunning = true;
    }

    public void RevertImages() {
        //ustawienie po jakim czasie obrazki mają się odwrócić
        timeRevert = new CountDownTimer(timeToRevert, 1000) {
            public void onTick(long millisUntilFinished) {
                //wyłączenie możliwości klikania na przyciski
                for (int i = 0; i < allImageButtons.length; i++) {
                    allImageButtons[i].setEnabled(false);
                }
            }
            public void onFinish() {
                //ustawienie obrazków pytajników i właczenie możliwości klikania na przyciski
                for (int i = 0; i < allImageButtons.length; i++) {
                    allImageButtons[i].setImageResource(R.drawable.tyl_kart);
                    allImageButtons[i].setEnabled(true);
                }
                //właczenie czasu gry
                SetTimeGame();
            }
        }.start();
    }

    public void CompareImages() {
        //ustawienie Listenere nasłuchującego który przycisk został wciśnięty
        for (int i = 0; i < allImageButtons.length; i++) {
            allImageButtons[i].setOnClickListener(this);
        }
        //porównanie obrazków wybranych przez użytkownika
        if (choosenFirstImage != 0 && choosenSecondImage != 0) {
            if (choosenFirstImage == choosenSecondImage) {
                //ustawienie obrazka na klikniętym buttonie
                setImages(choosenFirstButton);
                setImages(choosenSecondButton);
                choosenSecondImage = 0;
                choosenFirstImage = 0;
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (choosenFirstImage != 0 && choosenSecondImage != 0) {
            //odwrócenie obrazków jeśli nie były takie same
            setRevertImages(choosenFirstButton);
            setRevertImages(choosenSecondButton);
            choosenSecondImage = 0;
            choosenFirstImage = 0;
        }
        //sczytanie id wciśniętego obrazka
        int i = 0;
        for (int j = 0; j < allImageButtons.length; j++) {
            if (allImageButtons[j].getId() == v.getId()) {
                i = j;
                break;
            }
        }
        //sczytanie który obrazek został wybrany przez użytkownika
        choosePicture = imagesInPlaces[i];
        chooseButton = v.getId();
        //odwwrócenie wybranego obrazka
        allImageButtons[i].setImageResource(imagesInPlaces[i]);
        //wyłaczenie możliwości klikania wybranego już raz obrazka
        allImageButtons[i].setEnabled(false);
        //ustalenie który obrazek i button został wybrany
        if (choosenFirstImage == 0) {
            choosenFirstImage = choosePicture;
            choosenFirstButton = chooseButton;
        } else {
            choosenSecondImage = choosePicture;
            choosenSecondButton = chooseButton;
        }
        //porównanie obrazków
        CompareImages();
        //ustalenie kiedy wszystkie pary zostały znalezione
        if (foundNumber == 16 && value == 3) {
            SetSettingsOnEndOfLevel();
        } else if (foundNumber == 8 && value == 2) {
            SetSettingsOnEndOfLevel();
        } else if (foundNumber == 4 && value == 1) {
            SetSettingsOnEndOfLevel();
        }
    }

    public void SetSettingsOnEndOfLevel() {
        MediaPlayer ring = MediaPlayer.create(MemoGameActivity.this, R.raw.bravo);
        ring.start(); //właczenie dźwięki
        gameTime.cancel();//wyłaczenie timera gry
        gameTime = null; //ustawienie wartości timera na null
        isRunning = false; //ustalenie flagi dot. działania timera na false
        txt.setText("BRAWO!!!"); //wyświetlenie tekstu BRAWO
        resetButton.setVisibility(View.VISIBLE); // Pojawia się klawisz "Reset"
        choosenSecondImage = 0; //wyzerowanie wartosci wybranego obrazka 1
        choosenFirstImage = 0; //wyzerowanie wartosci wybranego obrazka 2
        if (timeToEnd > (timeOnLevel/2000) ){
            score += 5; //zakończenie gry przed upływem połowy czasu skutkuje przyznaniem 5 pkt.
        } else if(timeToEnd>0){
            score += 3; //zakończenie gry po upływie połowy czasu skutkuje przyznaniem 3 pkt.
        }
        else {
            score += 0; //niezakończenie gry w wyznaczonym czasie skutkuje przyznaniem 0 pkt.
        }
        //pobranie wyniku, jeśli gracz przeszedł odpowiednią ilość gier
        if (counterGames == 2) {
            getResults();
        }
    }

    public void getResults() {
        if(score<6)
        {
            //przejście do ekranu wyniku
            Intent intent = new Intent(getApplicationContext(), GiffActivityFailActivity.class);
            intent.putExtra("Odpowiedzi prawidłowe", score);//przekazanie informacji o ilości uzyskanych punktów
            intent.putExtra("Gra", "memo");//przekazanie informacji o grze
            intent.putExtra("level", value);//przekazanie informacji o levelu
            intent.putExtra("wiek", ageAbove7); //przekazanie informacji o module wieku
            startActivity(intent);
        }
        else {
            //przejście do ekranu wyniku
            Intent intent = new Intent(getApplicationContext(), GiffActivity.class);
            intent.putExtra("Odpowiedzi prawidłowe", score);//przekazanie informacji o ilości uzyskanych punktów
            intent.putExtra("Gra", "memo");//przekazanie informacji o grze
            intent.putExtra("level", value);//przekazanie informacji o levelu
            intent.putExtra("wiek", ageAbove7); //przekazanie informacji o module wieku
            startActivity(intent);
        }
    }

    public void setImages(int ButtonID) {
        //pobranie id wybranego buttonu
        int i = 0;
        for (int j = 0; j < allImageButtons.length; j++) {
            if (allImageButtons[j].getId() == ButtonID) {
                i = j;
                break;
            }
        }
        allImageButtons[i].setImageResource(imagesInPlaces[i]);//ustawienie obrazka na buttonie
        allImageButtons[i].setEnabled(false);//wyłączenie możliwości klikania na wybranym już obrazku
        foundNumber++;
    }

    public void setRevertImages(int ButtonID) {
        //pobranie id wybranego buttonu
        int i = 0;
        for (int j = 0; j < allImageButtons.length; j++) {
            if (allImageButtons[j].getId() == ButtonID) {
                i = j;
                break;
            }
        }
        allImageButtons[i].setImageResource(R.drawable.tyl_kart);//odwrócenie obrazka
        allImageButtons[i].setEnabled(true);//właczenie możliwości ponownego kliknięcia
    }

    public void onClickReset(View view) {
        //ponowne włączenie gry
        StartMemoryGame();
        txt.setText("Wyszukaj pary");
    }

    //przejście do głownego menu lub powrót do gry po kliknięciu przycisku "wróć"
    public void onBackPressed() {
        timeRevert.cancel();
        AlertDialog.Builder exitMessage = new AlertDialog.Builder(this);
        exitMessage.setMessage("Czy jesteś pewien, że chcesz opuścić grę?")
                .setTitle("WYJŚCIE");
        exitMessage.setPositiveButton("Zakończ grę", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                startActivity(new Intent(MemoGameActivity.this, MenuActivity.class));
            }
        });
        exitMessage.setNegativeButton("Pozostań w grze", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = exitMessage.create();
        dialog.show();
    }
}