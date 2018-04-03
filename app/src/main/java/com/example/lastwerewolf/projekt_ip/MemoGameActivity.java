package com.example.lastwerewolf.projekt_ip;

import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Random;

public class MemoGameActivity extends GameActivity {
    private Random rnd = new Random();
    private ImageButton ib1;
    private ImageButton ib2;
    private ImageButton ib3;
    private ImageButton ib4;

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

        //int firstPicture = ChoosePicture();

        int choosePicture = 0;
        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ib1.setImageResource(imagesInPlaces[0]);
                int choosePicture = v.getId();

            }
        });

        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ib2.setImageResource(imagesInPlaces[1]);
                int choosePicture = v.getId();

            }
        });

        ib3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ib3.setImageResource(imagesInPlaces[2]);
                int choosePicture = v.getId();
            }
        });

        ib4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ib4.setImageResource(imagesInPlaces[3]);
                int choosePicture = v.getId();
            }
        });

//        if(firstPicture!=choosePicture)
//        {
//
//        }
//        else
//        {
//
//        }


    }
//nie umiem wziąć biezącego widoku żeby to sprawdzić
//    public int ChoosePicture(View v) {
//        int choosePicture = 0;
//
//        switch (v.getId()) {
//            case R.id.firstImage:
//                choosePicture = v.getId();
//                break;
//            case R.id.secondImage:
//                choosePicture = v.getId();
//                break;
//            case R.id.thirdImage:
//                choosePicture = v.getId();
//                break;
//            case R.id.fourthImage:
//                choosePicture = v.getId();
//                break;
//            default:
//                throw new RuntimeException("Unknown button ID");
//        }
//        return choosePicture;
//    }
}


