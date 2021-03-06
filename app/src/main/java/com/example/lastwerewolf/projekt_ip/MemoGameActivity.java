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

import java.util.Random;

import static java.lang.Math.round;

public class MemoGameActivity extends GameActivity implements View.OnClickListener {
    private Random rnd = new Random();
    private ImageButton ib1;
    private ImageButton ib2;
    private ImageButton ib3;
    private ImageButton ib4;
    private ImageButton ib5;
    private ImageButton ib6;
    private ImageButton ib7;
    private ImageButton ib8;
    private ImageButton ib9;
    private ImageButton ib10;
    private ImageButton ib11;
    private ImageButton ib12;
    private ImageButton ib13;
    private ImageButton ib14;
    private ImageButton ib15;
    private ImageButton ib16;

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
    private int timeOnLevel = 0;
    private int timeToRevert = 0;
    private CountDownTimer gameTime;

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


        Bundle b = getIntent().getExtras();
        if (b != null)
            value = b.getInt("key");


        if (value == 1) {
            setContentView(R.layout.activity_memo_game);
            timeOnLevel = 10000;
            timeToRevert = 4000;
        } else if (value == 2) {
            setContentView(R.layout.activity_memo_game_lvl2);
            timeOnLevel = 35000;
            timeToRevert = 7000;
        } else {
            setContentView(R.layout.activity_memo_game_lvl3);
            timeOnLevel = 70000;
            timeToRevert = 10000;
        }

        txt = findViewById(R.id.infoTxt);
        resetButton = findViewById(R.id.ResetButton);

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

        if (value == 1) {
            allImageButtons = new ImageButton[]{ib1, ib2, ib3, ib4};
        } else if (value == 2) {
            allImageButtons = new ImageButton[]{ib1, ib2, ib3, ib4, ib5, ib6, ib7, ib8};
        } else {
            allImageButtons = new ImageButton[]{ib1, ib2, ib3, ib4, ib5, ib6, ib7, ib8, ib9, ib10, ib11, ib12, ib13, ib14, ib15, ib16};
        }
        StartMemoryGame();
    }

    public void StartMemoryGame() {
        foundNumber = 0;
        MediaPlayer findPairs = MediaPlayer.create(MemoGameActivity.this, R.raw.znajdz);
        findPairs.start();
        resetButton.setVisibility(View.INVISIBLE);
        randomlyImages = RandomlyImages(); // Losowanie obrazów do wyswietlenia
        randomlyPlaces = RandomlyPlaces();
        imagesInPlaces = new int[randomlyPlaces.length];

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
        for (int i = 0; i < allImageButtons.length; i++) {
            allImageButtons[i].setImageResource(imagesInPlaces[i]);
        }


        RevertImages();

        for (int i = 0; i < allImageButtons.length; i++) {
            allImageButtons[i].setEnabled(true);
        }
        SetTimeGame();
        CompareImages();

    }

    private int[] RandomlyImages() {
        int[] tab = new int[allImageButtons.length];
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

            gameTime = new CountDownTimer(timeOnLevel, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    txt.setText("" + round(millisUntilFinished / 1000));
                }

                @Override
                public void onFinish() {
                    txt.setText("Czas minał!");
                    MediaPlayer timeUp = MediaPlayer.create(MemoGameActivity.this, R.raw.czasminal);
                    timeUp.start();
                    resetButton.setVisibility(View.VISIBLE);
                    score += 0;
                }
            }.start();

    }

    public void RevertImages() {

        new CountDownTimer(timeToRevert, 1000) {
            public void onTick(long millisUntilFinished) {
                for (int i = 0; i < allImageButtons.length; i++) {
                    allImageButtons[i].setEnabled(false);
                }
            }

            public void onFinish() {

                for (int i = 0; i < allImageButtons.length; i++) {
                    allImageButtons[i].setImageResource(R.drawable.tyl_kart);
                    allImageButtons[i].setEnabled(true);
                }
            }
        }.start();
    }

    public void CompareImages() {

        for (int i = 0; i < allImageButtons.length; i++) {
            allImageButtons[i].setOnClickListener(this);
        }

        if (choosenFirstImage != 0 && choosenSecondImage != 0) {
            if (choosenFirstImage == choosenSecondImage) {

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
            setRevertImages(choosenFirstButton);
            setRevertImages(choosenSecondButton);
            choosenSecondImage = 0;
            choosenFirstImage = 0;
        }

        int i;
        switch (v.getId()) {
            case R.id.firstImage:
                i = 0;
                break;
            case R.id.secondImage:
                i = 1;
                break;
            case R.id.thirdImage:
                i = 2;
                break;
            case R.id.fourthImage:
                i = 3;
                break;
            case R.id.fifthImage:
                i = 4;
                break;
            case R.id.sixthImage:
                i = 5;
                break;
            case R.id.seventhImage:
                i = 6;
                break;
            case R.id.eigthImage:
                i = 7;
                break;
            case R.id.ninthImage:
                i = 8;
                break;
            case R.id.tenthImage:
                i = 9;
                break;
            case R.id.eleventhImage:
                i = 10;
                break;
            case R.id.twelvethImage:
                i = 11;
                break;
            case R.id.thirteenthImage:
                i = 12;
                break;
            case R.id.fourteenthImage:
                i = 13;
                break;
            case R.id.fifteenthImage:
                i = 14;
                break;
            case R.id.sixteenthImage:
                i = 15;
                break;
            default:
                throw new RuntimeException("Unknown button ID");
        }
        choosePicture = imagesInPlaces[i];
        chooseButton = v.getId();
        allImageButtons[i].setImageResource(imagesInPlaces[i]);
        allImageButtons[i].setEnabled(false);

        if (choosenFirstImage == 0) {
            choosenFirstImage = choosePicture;
            choosenFirstButton = chooseButton;
        } else {
            choosenSecondImage = choosePicture;
            choosenSecondButton = chooseButton;
        }

        CompareImages();

        if (foundNumber == 16 && value == 3) {
            MediaPlayer ring = MediaPlayer.create(MemoGameActivity.this, R.raw.bravo);
            ring.start();
            txt.setText("BRAWO!!!");
            resetButton.setVisibility(View.VISIBLE); // Pojawia się klawisz "Reset"
            choosenSecondImage = 0;
            choosenFirstImage = 0;
            gameTime.cancel();
            score += 5;
        } else if (foundNumber == 8 && value == 2) {
            MediaPlayer ring = MediaPlayer.create(MemoGameActivity.this, R.raw.bravo);
            ring.start();
            txt.setText("BRAWO!!!");
            resetButton.setVisibility(View.VISIBLE); // Pojawia się klawisz "Reset"
            choosenSecondImage = 0;
            choosenFirstImage = 0;
            gameTime.cancel();
            score += 5;
        } else if (foundNumber == 4 && value == 1) {
            MediaPlayer ring = MediaPlayer.create(MemoGameActivity.this, R.raw.bravo);
            ring.start();
            txt.setText("BRAWO!!!");
            resetButton.setVisibility(View.VISIBLE); // Pojawia się klawisz "Reset"
            choosenSecondImage = 0;
            choosenFirstImage = 0;
            gameTime.cancel();
            score += 5;

        }

        if (score == 10) {
            getResults();
        }
    }

    public void getResults() {
        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
        intent.putExtra("Odpowiedzi prawidłowe", score);
        intent.putExtra("Gra", "memo");
        intent.putExtra("level", value);
        startActivity(intent);
    }

    public void setImages(int ButtonID) {

        int i;
        switch (ButtonID) {
            case R.id.firstImage:
                i = 0;
                break;
            case R.id.secondImage:
                i = 1;
                break;
            case R.id.thirdImage:
                i = 2;
                break;
            case R.id.fourthImage:
                i = 3;
                break;
            case R.id.fifthImage:
                i = 4;
                break;
            case R.id.sixthImage:
                i = 5;
                break;
            case R.id.seventhImage:
                i = 6;
                break;
            case R.id.eigthImage:
                i = 7;
                break;
            case R.id.ninthImage:
                i = 8;
                break;
            case R.id.tenthImage:
                i = 9;
                break;
            case R.id.eleventhImage:
                i = 10;
                break;
            case R.id.twelvethImage:
                i = 11;
                break;
            case R.id.thirteenthImage:
                i = 12;
                break;
            case R.id.fourteenthImage:
                i = 13;
                break;
            case R.id.fifteenthImage:
                i = 14;
                break;
            case R.id.sixteenthImage:
                i = 15;
                break;
            default:
                throw new RuntimeException("Unknown button ID");
        }
        allImageButtons[i].setImageResource(imagesInPlaces[i]);
        allImageButtons[i].setEnabled(false);
        foundNumber++;
    }


    public void setRevertImages(int ButtonID) {

        int i;
        switch (ButtonID) {
            case R.id.firstImage:
                i = 0;
                break;
            case R.id.secondImage:
                i = 1;
                break;
            case R.id.thirdImage:
                i = 2;
                break;
            case R.id.fourthImage:
                i = 3;
                break;
            case R.id.fifthImage:
                i = 4;
                break;
            case R.id.sixthImage:
                i = 5;
                break;
            case R.id.seventhImage:
                i = 6;
                break;
            case R.id.eigthImage:
                i = 7;
                break;
            case R.id.ninthImage:
                i = 8;
                break;
            case R.id.tenthImage:
                i = 9;
                break;
            case R.id.eleventhImage:
                i = 10;
                break;
            case R.id.twelvethImage:
                i = 11;
                break;
            case R.id.thirteenthImage:
                i = 12;
                break;
            case R.id.fourteenthImage:
                i = 13;
                break;
            case R.id.fifteenthImage:
                i = 14;
                break;
            case R.id.sixteenthImage:
                i = 15;
                break;
            default:
                throw new RuntimeException("Unknown button ID");
        }
        allImageButtons[i].setImageResource(R.drawable.tyl_kart);
        allImageButtons[i].setEnabled(true);

    }

    public void onClickReset(View view) {
        StartMemoryGame();
        txt.setText("Wyszukaj pary");
    }

    public void onBackPressed() {


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



