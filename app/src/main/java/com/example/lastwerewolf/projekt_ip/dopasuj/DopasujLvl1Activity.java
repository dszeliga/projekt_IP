package com.example.lastwerewolf.projekt_ip.dopasuj;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.lastwerewolf.projekt_ip.MemoGameActivity;
import com.example.lastwerewolf.projekt_ip.MenuActivity;
import com.example.lastwerewolf.projekt_ip.R;

import java.util.Random;

public class DopasujLvl1Activity extends AppCompatActivity {

    private Colors currentColorToGuess = Colors.NONE;
    private Item currentColorToGuessObject;
    private Colors currentButtons[] = { Colors.NONE, Colors.NONE, Colors.NONE};
    private Button buttons[];
    private ImageView imv;
    private ImageView point;

    private void setColorButtonsAndTShirt() {
        Resources res = getResources();

        buttons = new Button[] {
                (Button) findViewById(R.id.btn_color1),
                (Button) findViewById(R.id.btn_color2),
                (Button) findViewById(R.id.btn_color3)
        };

        imv = (ImageView) findViewById(R.id.imageView2);
        point = (ImageView) findViewById(R.id.imageView);
    }

    public void playSound(String fileName) {

        MediaPlayer m = new MediaPlayer();

        try {
            if (m.isPlaying()) {
                m.stop();
                m.release();
                m = new MediaPlayer();
            }

            AssetFileDescriptor descriptor = getAssets().openFd(fileName);
            m.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();

            m.prepare();
            m.setVolume(1f, 1f);
            m.setLooping(false);
            m.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dopasuj_lvl1_activity);
        setColorButtonsAndTShirt();
    }

    public void onSpeakerClick(View view) {
        if(currentColorToGuess == Colors.NONE) {
            // New game
            setRandomColors();
        }
        playSound(currentColorToGuessObject.getSoundFileName());
        Log.i("Dopasuj:","onSpeakerClick " + currentColorToGuessObject.getColor().name());
        point.setVisibility(View.INVISIBLE);
    }

    public void shuffleColorArray(Colors d[]) {
        Random r = new Random();
        for(int i = 0; i<d.length; i++) {
            int j = r.nextInt(d.length-i) + i;
            Colors tmp = d[j];
            d[j] = d[i];
            d[i] = tmp;
        }
    }

    public void setRandomColors() {
        Resources res = getResources();

        Colors clrs[] = {
                Colors.YELLOW,
                Colors.RED,
                Colors.GREEN,
                Colors.BLUE,
                Colors.PINK,
                Colors.ORANGE};

        shuffleColorArray(clrs);

        for(int i=0; i<buttons.length; i++) {
            buttons[i].setBackground((new ColorButton(res, clrs[i])).getDrawable());
            currentButtons[i] = clrs[i];
        }

        Random r = new Random();
        int nextColor = r.nextInt(buttons.length);
        currentColorToGuess = currentButtons[nextColor];

        int nextObj = r.nextInt(3);
        switch (nextObj) {
            case 0:
                currentColorToGuessObject = new Baloon(res, currentButtons[nextColor]);
                break;
            case 1:
                currentColorToGuessObject = new Car(res, currentButtons[nextColor]);
                break;
            default:
                currentColorToGuessObject = new Shirt(res, currentButtons[nextColor]);
                break;
        }

        imv.setBackground(currentColorToGuessObject.getToGuessImage());
    }

    private  void resetGame() {
        Resources res = getResources();
        currentColorToGuess = Colors.NONE;
        for(int i=0; i<buttons.length; i++) {
            buttons[i].setBackground(new ColorButton(res, Colors.NONE).getDrawable());
            currentButtons[i] = Colors.NONE;
        }
    }

    public void onColorClick(View view) {
        if(currentColorToGuess == Colors.NONE) {
            Log.i("Dopasuj:","Game not started.");
            return;
        }

        Colors selectedColor = Colors.NONE;
        switch (view.getId()) {
            case  R.id.btn_color1: {
                selectedColor = currentButtons[0];
                break;
            }

            case R.id.btn_color2: {
                selectedColor = currentButtons[1];
                break;
            }

            case R.id.btn_color3: {
                selectedColor = currentButtons[2];
                break;
            }
        }

        if(selectedColor == currentColorToGuess) {
            Log.i("Dopasuj:","It's  " + selectedColor.name() + "! Congratulate!");
            imv.setBackground(currentColorToGuessObject.getImage());
            point.setVisibility(View.VISIBLE);
            playSound("bravo.mp3");
            resetGame();
        } else {
            Log.i("Dopasuj:"," " + selectedColor.name() + " is not right answer :(");
            playSound("error.mp3");
        }
    }

    public void onBackPressed() {


        AlertDialog.Builder exitMessage = new AlertDialog.Builder(this);
        exitMessage.setMessage("Czy jesteś pewien, że chcesz opuścić grę?")
                .setTitle("WYJŚCIE");

        exitMessage.setPositiveButton("Zakończ grę", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                startActivity(new Intent(DopasujLvl1Activity.this, MenuActivity.class));

            }
        });
        exitMessage.setNegativeButton("Pozostań w grze", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = exitMessage.create();
        dialog.show();

    }
}
