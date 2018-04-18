package com.example.lastwerewolf.projekt_ip.dopasuj;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.example.lastwerewolf.projekt_ip.R;

public class ColorButton {
    private Drawable draw;

    public ColorButton(Resources res, Colors color) {
        switch (color) {
            case ORANGE:
                draw = res.getDrawable(R.drawable.round_button_orange);
                break;
            case PINK:
                draw = res.getDrawable(R.drawable.round_button_pink);
                break;
            case BLUE:
                draw = res.getDrawable(R.drawable.round_shape_button_blue);
                break;
            case GREEN:
                draw = res.getDrawable(R.drawable.round_shape_button_green);
                break;
            case RED:
                draw = res.getDrawable(R.drawable.round_shape_button_red);
                break;
            case YELLOW:
                draw = res.getDrawable(R.drawable.round_shape_button_yellow);
                break;
            case NONE:
                draw = res.getDrawable(R.drawable.round_shape_button_gray);
                break;
        }
    }

    public Drawable getDrawable() {
        return draw;
    }
}
