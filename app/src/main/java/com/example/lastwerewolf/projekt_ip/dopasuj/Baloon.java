package com.example.lastwerewolf.projekt_ip.dopasuj;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.example.lastwerewolf.projekt_ip.R;

public class Baloon implements Item {
    private Colors color;
    private Drawable draw;
    private Drawable drawToGuess;
    private String soundFileName;

    public Baloon(Resources res) {
        this.color = Colors.NONE;
        this.drawToGuess = res.getDrawable(R.drawable.ic_baloon_grey);
        this.soundFileName = null;
        this.draw = res.getDrawable(R.drawable.ic_baloon_grey);
    }

    public Baloon(Resources res, Colors color) {
        this.color = color;
        this.drawToGuess = res.getDrawable(R.drawable.ic_baloon_grey);
        this.soundFileName = null;
        switch (color) {
            case RED:
                this.draw = res.getDrawable(R.drawable.ic_baloon_red);
                this.soundFileName = "balon_czerwono.mp3";
                break;
            case GREEN:
                this.draw = res.getDrawable(R.drawable.ic_baloon_green);
                this.soundFileName = "balon_zielono.mp3";
                break;
            case YELLOW:
                this.draw = res.getDrawable(R.drawable.ic_baloon_yellow);
                this.soundFileName = "balon_zolto.mp3";
                break;
            case BLUE:
                this.draw = res.getDrawable(R.drawable.ic_baloon_blue);
                this.soundFileName = "balon_niebiesko.mp3";
                break;
            case PINK:
                this.draw = res.getDrawable(R.drawable.ic_baloon_pink);
                this.soundFileName = "balon_rozowo.mp3";
                break;
            case ORANGE:
                this.draw = res.getDrawable(R.drawable.ic_baloon_orange);
                this.soundFileName = "balon_pomaranczowo.mp3";
                break;
            case NONE:
                this.draw = res.getDrawable(R.drawable.ic_baloon_grey);
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
