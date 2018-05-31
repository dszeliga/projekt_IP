package com.example.lastwerewolf.projekt_ip.dopasuj;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.example.lastwerewolf.projekt_ip.R;

public class Shape {
    ShapeType type;
    Drawable shapeEmpty;
    Drawable shapeFilled;

    public Shape(ShapeType type, Resources res) {
        this.type = type;
        switch (type) {
            case APPLE:
                shapeEmpty = res.getDrawable(R.drawable.empty_apple);
                shapeFilled = res.getDrawable(R.drawable.apple);
                break;
            case BANANAS:
                shapeEmpty = res.getDrawable(R.drawable.empty_bananas);
                shapeFilled = res.getDrawable(R.drawable.filled_bananas);
                break;
            case CIRCLE:
                shapeEmpty = res.getDrawable(R.drawable.empty_circle);
                shapeFilled = res.getDrawable(R.drawable.filled_circle);
                break;
            case DROP:
                shapeEmpty = res.getDrawable(R.drawable.empty_drop);
                shapeFilled = res.getDrawable(R.drawable.filled_drop);
                break;
            case HEART:
                shapeEmpty = res.getDrawable(R.drawable.empty_heart);
                shapeFilled = res.getDrawable(R.drawable.filled_heart);
                break;
            case KEY:
                shapeEmpty = res.getDrawable(R.drawable.empty_key);
                shapeFilled = res.getDrawable(R.drawable.filled_key);
                break;
            case PEAR:
                shapeEmpty = res.getDrawable(R.drawable.empty_pear);
                shapeFilled = res.getDrawable(R.drawable.filled_pear);
                break;
            case PENTOID:
                shapeEmpty = res.getDrawable(R.drawable.empty_pentoid);
                shapeFilled = res.getDrawable(R.drawable.filled_pentoid);
                break;
            case SQUARE:
                shapeEmpty = res.getDrawable(R.drawable.empty_square);
                shapeFilled = res.getDrawable(R.drawable.filled_square);
                break;
            case STAR:
                shapeEmpty = res.getDrawable(R.drawable.empty_star);
                shapeFilled = res.getDrawable(R.drawable.filled_star);
                break;
            case TRIANGLE:
                shapeEmpty = res.getDrawable(R.drawable.empty_triangle);
                shapeFilled = res.getDrawable(R.drawable.filled_triangle);
                break;
            case EX:
                shapeEmpty = res.getDrawable(R.drawable.ex);
                shapeFilled = res.getDrawable(R.drawable.ex);
                break;
        }
    }
}
