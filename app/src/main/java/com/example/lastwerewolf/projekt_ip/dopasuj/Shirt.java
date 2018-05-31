package com.example.lastwerewolf.projekt_ip.dopasuj;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.example.lastwerewolf.projekt_ip.R;

public class Shirt implements Item {
    private Colors color;
    private Drawable draw;
    private Drawable drawToGuess;
    private String soundFileName;

    public Shirt(Resources res) {
        this.color = Colors.NONE;
        this.drawToGuess = res.getDrawable(R.drawable.ic_t_shirt_lime_gray1_silhouette);
        this.soundFileName = null;
        this.draw = res.getDrawable(R.drawable.ic_t_shirt_lime_gray1_silhouette);
    }

    public Shirt(Resources res, Colors color) {
        this.color = color;
        this.drawToGuess = res.getDrawable(R.drawable.ic_t_shirt_lime_gray1_silhouette);
        this.soundFileName = null;
        switch (color) {
            case RED:
                this.draw = res.getDrawable(R.drawable.ic_t_shirt_red_silhouette);
                this.soundFileName = "koszulka_czerwono.mp3";
                break;
            case GREEN:
                this.draw = res.getDrawable(R.drawable.ic_t_shirt_lime_green_silhouette);
                this.soundFileName = "koszulka_zielono.mp3";
                break;
            case YELLOW:
                this.draw = res.getDrawable(R.drawable.ic_t_shirt_gold_silhouette);
                this.soundFileName = "koszulka_zolto.mp3";
                break;
            case BLUE:
                this.draw = res.getDrawable(R.drawable.ic_t_shirt_oblue_silhouette);
                this.soundFileName = "koszulka_niebiesko.mp3";
                break;
            case PINK:
                this.draw = res.getDrawable(R.drawable.ic_t_shirt_pink_silhouette);
                this.soundFileName = "koszulka_rozowo.mp3";
                break;
            case ORANGE:
                this.draw = res.getDrawable(R.drawable.ic_t_shirt_orange_silhouette);
                this.soundFileName = "koszulka_pomanczowo.mp3";
                break;
            case NONE:
                this.draw = res.getDrawable(R.drawable.ic_t_shirt_lime_gray1_silhouette);
        }
    }

    @Override
    public String getSoundFileName() { return soundFileName; }

    @Override
    public Colors getColor() {
        return color;
    }

    @Override
    public Drawable getImage() {
        return draw;
    }

    @Override
    public Drawable getToGuessImage() {
        return drawToGuess;
    }
}
