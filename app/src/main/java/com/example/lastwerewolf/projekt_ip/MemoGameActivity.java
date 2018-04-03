package com.example.lastwerewolf.projekt_ip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class MemoGameActivity extends GameActivity {
    private Random rnd = new Random();
    int[] photos = {R.mipmap.pilka, R.mipmap.proba};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_game);
        ImageView iv3 = findViewById(R.id.thirdImage);
        ImageView iv2 = findViewById(R.id.secondImage);
        ImageView iv1 = findViewById(R.id.firstImage);
        ImageView iv4 = findViewById(R.id.fourthImage);


        Random ran = new Random();
        //int i = ran.nextInt(photos.length);
        //int k = ran.nextInt(photos.length);

        //iv2.setImageResource(photos[k]);
        //iv.setImageResource(photos[i]);
        int[] randomlyImages = RandomlyImages(); // Losowanie obrazów do wyswietlenia

        iv2.setImageResource(randomlyImages[1]);
        iv1.setImageResource(randomlyImages[0]);
        iv3.setImageResource(randomlyImages[1]);
        iv4.setImageResource(randomlyImages[0]);

    }

    private int[] RandomlyImages()
    {
        int[] tab = new int[2];
        for (int i = 0; i < tab.length; i++)
        {
            int r = rnd.nextInt(photos.length);

            boolean isTheSameImage = true;
            for (int j = 0; j < tab.length; j++)
            {
                if(tab[j] != 0)
                    if (photos[r] == tab[j])
                        isTheSameImage = false;
            }
            if(isTheSameImage == true)
                tab[i] = photos[r];
            else
                i--;
        }
        return tab;
    }
}
