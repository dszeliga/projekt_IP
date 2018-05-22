package com.example.lastwerewolf.projekt_ip.dopasuj;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.example.lastwerewolf.projekt_ip.R;

public class Proffesion {

    ProfessionType type;
    Drawable profPic;
    Drawable profTool;

    public Proffesion(ProfessionType type, Resources res) {
        this.type = type;
        switch (type) {
            case DIVER:
                profPic = res.getDrawable(R.drawable.ic_diver);
                profTool = res.getDrawable(R.drawable.ic_diver_match);
                break;
            case DOCTOR:
                profPic = res.getDrawable(R.drawable.ic_doctor);
                profTool = res.getDrawable(R.drawable.ic_doctor_match);
                break;
            case FARMER:
                profPic = res.getDrawable(R.drawable.ic_farmer);
                profTool = res.getDrawable(R.drawable.ic_farmer_match);
                break;
            case FIREFIGHTER:
                profPic = res.getDrawable(R.drawable.ic_firefighter);
                profTool = res.getDrawable(R.drawable.ic_firefighter_match);
                break;
            case STEWARDESS:
                profPic = res.getDrawable(R.drawable.ic_stewardess);
                profTool = res.getDrawable(R.drawable.ic_stewardess_match);
                break;
            case WAITRESS:
                profPic = res.getDrawable(R.drawable.ic_waitress);
                profTool = res.getDrawable(R.drawable.ic_waitress_match);
                break;
            case NONE:
                profPic = res.getDrawable(R.drawable.empty_circle);
                profTool = res.getDrawable(R.drawable.empty_circle);
                break;
        }
    }
}
