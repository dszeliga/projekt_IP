package com.example.lastwerewolf.projekt_ip;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class MemoGameActivity extends GameActivity {
    private Random rnd = new Random();
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private ImageView iv4;
    private int[] imagesInPlaces = null;
    private int[] randomlyImages = null;
    private int[] randomlyPlaces = null;
    private int[] photos = {R.drawable.i1, R.drawable.i2, R.drawable.i3, R.drawable.i4, R.drawable.i5,
            R.drawable.i6, R.drawable.i7, R.drawable.i8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_game);
        iv3 = findViewById(R.id.thirdImage);
        iv2 = findViewById(R.id.secondImage);
        iv1 = findViewById(R.id.firstImage);
        iv4 = findViewById(R.id.fourthImage);

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
        iv1.setImageResource(imagesInPlaces[0]);
        iv2.setImageResource(imagesInPlaces[1]);
        iv3.setImageResource(imagesInPlaces[2]);
        iv4.setImageResource(imagesInPlaces[3]);

        RevertImages();
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
                iv1.setImageResource(R.drawable.tyl_kart);
                iv2.setImageResource(R.drawable.tyl_kart);
                iv3.setImageResource(R.drawable.tyl_kart);
                iv4.setImageResource(R.drawable.tyl_kart);
            }
        }.start();
    }
}
