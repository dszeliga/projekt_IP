package com.example.lastwerewolf.projekt_ip;

import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class MemoGameActivity extends GameActivity implements View.OnClickListener {
    private Random rnd = new Random();
    private ImageButton ib1;
    private ImageButton ib2;
    private ImageButton ib3;
    private ImageButton ib4;
    private TextView txt;

    private int choosePicture = 0;
    private int chooseButton = 0;
    private int choosenFirstImage = 0;
    private int choosenSecondImage = 0;
    private int choosenFirstButton = 0;
    private int choosenSecondButton = 0;

    private boolean[] allButtons = new boolean[4];
    private int[] imagesInPlaces = null;
    private int[] randomlyImages = null;
    private int[] randomlyPlaces = null;

    private int[] photos = {R.drawable.i1, R.drawable.i2, R.drawable.i3, R.drawable.i4, R.drawable.i5,
            R.drawable.i6, R.drawable.i7, R.drawable.i8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_game);

        ib1 = findViewById(R.id.firstImage);
        ib2 = findViewById(R.id.secondImage);
        ib3 = findViewById(R.id.thirdImage);
        ib4 = findViewById(R.id.fourthImage);
        txt = findViewById(R.id.infoTxt);

        allButtons[0]=true;
        allButtons[1]=true;
        allButtons[2]=true;
        allButtons[3]=true;

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
        ib1.setImageResource(imagesInPlaces[0]);
        ib2.setImageResource(imagesInPlaces[1]);
        ib3.setImageResource(imagesInPlaces[2]);
        ib4.setImageResource(imagesInPlaces[3]);

        RevertImages();

        CompareImages();
    }

    private int[] RandomlyImages() {
        int[] tab = new int[2];
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
        int[] tab = new int[4];
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

    public void RevertImages() {

        new CountDownTimer(5000, 1000) {
            public void onTick(long millisUntilFinished) {
                //tu można wyświetlic ubiegajacy czas czy coś jak bedziemy chcieli
            }

            public void onFinish() {
                ib1.setImageResource(R.drawable.tyl_kart);
                ib2.setImageResource(R.drawable.tyl_kart);
                ib3.setImageResource(R.drawable.tyl_kart);
                ib4.setImageResource(R.drawable.tyl_kart);
            }
        }.start();
    }

    public void CompareImages() {

        ib1.setOnClickListener(this);
        ib2.setOnClickListener(this);
        ib3.setOnClickListener(this);
        ib4.setOnClickListener(this);

        if (choosenFirstImage != 0 && choosenSecondImage != 0) {
            if (choosenFirstImage == choosenSecondImage) {

                setImages(choosenFirstButton, choosenSecondButton);
                choosenSecondImage = 0;
                choosenFirstImage = 0;

            } else if (choosenFirstImage != choosenSecondImage) {
                setRevertImages(choosenFirstButton, choosenSecondButton);
                choosenSecondImage = 0;
                choosenFirstImage = 0;
            }
        }


        boolean x = areAllFalse(allButtons);

        if (x == true) {
            txt.setText("KONIEC");
        }
    }


    public static boolean areAllFalse(boolean[] array)
    {
        for(boolean b : array) if(b) return false;
        return true;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.firstImage:
                choosePicture = imagesInPlaces[0];
                chooseButton = v.getId();
                ib1.setImageResource(imagesInPlaces[0]);
                ib1.setEnabled(false);
                CompareImages();
                break;
            case R.id.secondImage:
                choosePicture = imagesInPlaces[1];
                chooseButton = v.getId();
                ib2.setImageResource(imagesInPlaces[1]);
                ib2.setEnabled(false);
                CompareImages();
                break;
            case R.id.thirdImage:
                choosePicture = imagesInPlaces[2];
                chooseButton = v.getId();
                ib3.setImageResource(imagesInPlaces[2]);
                ib3.setEnabled(false);
                CompareImages();
                break;
            case R.id.fourthImage:
                choosePicture = imagesInPlaces[3];
                chooseButton = v.getId();
                ib4.setImageResource(imagesInPlaces[3]);
                ib4.setEnabled(false);
                CompareImages();
                break;
            default:
                throw new RuntimeException("Unknown button ID");
        }

        if (choosenFirstImage == 0) {
            choosenFirstImage = choosePicture;
            choosenFirstButton = chooseButton;
        } else {
            choosenSecondImage = choosePicture;
            choosenSecondButton = chooseButton;
        }
    }

    public void setImages(int firstButton, int secondButton) {

        switch (firstButton) {

            case R.id.firstImage:
                ib1.setImageResource(imagesInPlaces[0]);
                ib1.setEnabled(false);
                allButtons[0] = false;
                break;
            case R.id.secondImage:
                ib2.setImageResource(imagesInPlaces[1]);
                ib2.setEnabled(false);
                allButtons[1] = false;
                break;
            case R.id.thirdImage:
                ib3.setImageResource(imagesInPlaces[2]);
                ib3.setEnabled(false);
                allButtons[2] = false;
                break;
            case R.id.fourthImage:
                ib4.setImageResource(imagesInPlaces[3]);
                ib4.setEnabled(false);
                allButtons[3] = false;
                break;
            default:
                throw new RuntimeException("Unknown button ID");

        }
        switch (secondButton) {
            case R.id.firstImage:
                ib1.setImageResource(imagesInPlaces[0]);
                ib1.setEnabled(false);
                allButtons[0] = false;
                break;
            case R.id.secondImage:
                ib2.setImageResource(imagesInPlaces[1]);
                ib2.setEnabled(false);
                allButtons[1] = false;
                break;
            case R.id.thirdImage:
                ib3.setImageResource(imagesInPlaces[2]);
                ib3.setEnabled(false);
                allButtons[2] = false;
                break;
            case R.id.fourthImage:
                ib4.setImageResource(imagesInPlaces[3]);
                ib4.setEnabled(false);
                allButtons[3] = false;
                break;
            default:
                throw new RuntimeException("Unknown button ID");

        }
    }

    public void setRevertImages(int firstButton, int secondButton) {
        switch (firstButton) {
            case R.id.firstImage:
                ib1.setImageResource(R.drawable.tyl_kart);
                ib1.setEnabled(true);
                break;
            case R.id.secondImage:
                ib2.setImageResource(R.drawable.tyl_kart);
                ib2.setEnabled(true);
                break;
            case R.id.thirdImage:
                ib3.setImageResource(R.drawable.tyl_kart);
                ib3.setEnabled(true);
                break;
            case R.id.fourthImage:
                ib4.setImageResource(R.drawable.tyl_kart);
                ib4.setEnabled(true);
                break;
            default:
                throw new RuntimeException("Unknown button ID");

        }
        switch (secondButton) {
            case R.id.firstImage:
                ib1.setImageResource(R.drawable.tyl_kart);
                ib1.setEnabled(true);
                break;
            case R.id.secondImage:
                ib2.setImageResource(R.drawable.tyl_kart);
                ib2.setEnabled(true);
                break;
            case R.id.thirdImage:
                ib3.setImageResource(R.drawable.tyl_kart);
                ib3.setEnabled(true);
                break;
            case R.id.fourthImage:
                ib4.setImageResource(R.drawable.tyl_kart);
                ib4.setEnabled(true);
                break;
            default:
                throw new RuntimeException("Unknown button ID");

        }
    }
}



