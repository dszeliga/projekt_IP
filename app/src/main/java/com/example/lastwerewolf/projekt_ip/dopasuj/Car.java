package com.example.lastwerewolf.projekt_ip.dopasuj;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.example.lastwerewolf.projekt_ip.R;

public class Car implements Item {
    private Colors color;
    private Drawable draw;
    private Drawable drawToGuess;
    private String soundFileName;

    public Car(Resources res) {
        this.color = Colors.NONE;
        this.drawToGuess = res.getDrawable(R.drawable.ic_sedan_car_model_gray);
        this.soundFileName = null;
        this.draw = res.getDrawable(R.drawable.ic_sedan_car_model_gray);
    }

    public Car(Resources res, Colors color) {
        this.color = color;
        this.drawToGuess = res.getDrawable(R.drawable.ic_sedan_car_model_gray);
        this.soundFileName = null;
        switch (color) {
            case RED:
                this.draw = res.getDrawable(R.drawable.ic_sedan_car_model_red);
                this.soundFileName = "samoch_czerwono.mp3";
                break;
            case GREEN:
                this.draw = res.getDrawable(R.drawable.ic_sedan_car_model_green);
                this.soundFileName = "samoch_zielono.mp3";
                break;
            case YELLOW:
                this.draw = res.getDrawable(R.drawable.ic_sedan_car_model_yellow);
                this.soundFileName = "samoch_zolto.mp3";
                break;
            case BLUE:
                this.draw = res.getDrawable(R.drawable.ic_sedan_car_model_blue);
                this.soundFileName = "samoch_niebiesko.mp3";
                break;
            case PINK:
                this.draw = res.getDrawable(R.drawable.ic_sedan_car_model_pink);
                this.soundFileName = "samoch_rozowo.mp3";
                break;
            case ORANGE:
                this.draw = res.getDrawable(R.drawable.ic_sedan_car_model_orange);
                this.soundFileName = "samoch_pomaranczowo.mp3";
                break;
            case NONE:
                this.draw = res.getDrawable(R.drawable.ic_sedan_car_model_gray);
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
