package com.example.lastwerewolf.projekt_ip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity {

    private String _name;

    public void Name(String name) {
        this._name = name;
    }

    public void RepeatGame() {}

    public void NextGame(int level) {}

    public void NewGame() {}

    public void Quit() {}

    public int Result() {
        return 0;
    }

    public void Success() {}

    public void Failure() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }
}
