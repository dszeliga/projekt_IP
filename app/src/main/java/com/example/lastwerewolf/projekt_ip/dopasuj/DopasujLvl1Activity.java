package com.example.lastwerewolf.projekt_ip.dopasuj;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.lastwerewolf.projekt_ip.GiffActivity;
import com.example.lastwerewolf.projekt_ip.MemoGameActivity;
import com.example.lastwerewolf.projekt_ip.MenuActivity;
import com.example.lastwerewolf.projekt_ip.R;

import java.util.Random;

public class DopasujLvl1Activity extends AppCompatActivity {

    private final Handler handler = new Handler();
    private final int SCORE_FOR_WIN = 1;
    private final int SHOW_RESULT_AFTER = 3;

    private Colors currentColorToGuess = Colors.NONE;
    private Item currentColorToGuessObject;
    private Colors currentButtons[] = { Colors.NONE, Colors.NONE, Colors.NONE};
    private Button buttons[];
    private ImageView imv;
    private ImageView point;
    private Button speaker;
    private int winCounter = 0;

    private void setColorButtonsAndTShirt() {
        Resources res = getResources();

        buttons = new Button[] {
                (Button) findViewById(R.id.btn_color1),
                (Button) findViewById(R.id.btn_color2),
                (Button) findViewById(R.id.btn_color3)
        };

        imv = (ImageView) findViewById(R.id.imageView2);
        point = (ImageView) findViewById(R.id.imageView);
        speaker = (Button) findViewById(R.id.btn_speaker);
    }

    MediaPlayer m = new MediaPlayer();

    // Odtwarzanie dźwięków
    public void playSound(String fileName) {
        try {
            // Stwórz nową instancję odtwarzacza dźwięków, zatrzymując starą
            if (m.isPlaying()) {
                m.stop();
                m.release();
            }
            m = new MediaPlayer();

            // Odczytaj plik z pamięci na podstawie nazwy pliku
            AssetFileDescriptor descriptor = getAssets().openFd(fileName);
            m.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();

            // Odtwórz dźwięk
            m.prepare();
            m.setVolume(1f, 1f);
            m.setLooping(false);
            m.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Odtwarzanie dźwięków
    public void playSound(int resid) {
        try {
            // Stwórz nową instancję odtwarzacza dźwięków, zatrzymując starą
            if (m.isPlaying()) {
                m.stop();
                m.release();
            }
            m = new MediaPlayer();

            // Odczytaj plik z pamięci na podstawie id
            AssetFileDescriptor descriptor = getResources().openRawResourceFd(resid);
            m.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();

            // Odtwórz dźwięk
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
        this.onSpeakerClick(imv);
    }

    public void onSpeakerClick(View view) {
        if(currentColorToGuess == Colors.NONE) {
            // Ustaw losowe kolory dla nowej gry
            setRandomColors();
        }
        // Odtwórz dźwięk obiektu, który należy rozpoznać
        playSound(currentColorToGuessObject.getSoundFileName());
        point.setVisibility(View.INVISIBLE);
    }

    // Przemieszaj tablicy kolorów
    public void shuffleColorArray(Colors d[]) {
        Random r = new Random();
        for(int i = 0; i<d.length; i++) {
            int j = r.nextInt(d.length-i) + i;
            Colors tmp = d[j];
            d[j] = d[i];
            d[i] = tmp;
        }
    }

    // Wylosuj kolory oraz wybierz kolor do rozpoznania
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

        // Wylosuj rodzaj obiektu
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

    // Reset gry
    private  void resetGame() {
        Resources res = getResources();
        currentColorToGuess = Colors.NONE;
        for(int i=0; i<buttons.length; i++) {
            buttons[i].setBackground(new ColorButton(res, Colors.NONE).getDrawable());
            currentButtons[i] = Colors.NONE;
        }
    }

    // Obsługa kliknięcia przedmiotu
    public void onColorClick(View view) {
        if(currentColorToGuess == Colors.NONE) {
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

        // Obsługa poprawnego odgadnięcia koloru
        if(selectedColor == currentColorToGuess) {
            imv.setBackground(currentColorToGuessObject.getImage());
            point.setVisibility(View.VISIBLE);
            playSound(R.raw.bravo);

            resetGame();

            speaker.setEnabled(false);
            for(Button btn : buttons) {
                btn.setEnabled(false);
            }

            // Wykonaj z opóźnieniem włączenie nowej gry lub wyświetlenia ekranu z wynikiem
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    winCounter++;
                    if(winCounter % SHOW_RESULT_AFTER == 0) {
                        Intent intent = new Intent(getApplicationContext(), GiffActivity.class );
                        intent.putExtra("Odpowiedzi prawidłowe", winCounter * SCORE_FOR_WIN);//przekazanie informacji o ilości uzyskanych punktów
                        intent.putExtra("Gra", "dopasuj");
                        intent.putExtra("level", 1);

                        winCounter = 0;

                        startActivity(intent);
                        finish();
                    } else {
                        speaker.setEnabled(true);
                        for(Button btn : buttons) {
                            btn.setEnabled(true);
                        }
                        onSpeakerClick(imv);
                    }
                }
            }, 4000);
        } else {
            // W przypadku błędnej odpowiedzi odwtórz dźwięk błędu
            playSound(R.raw.error);
        }
    }

    // Obsługa wciśnięcia klawisza wstecz
    public void onBackPressed() {
        AlertDialog.Builder exitMessage = new AlertDialog.Builder(this);
        exitMessage.setMessage("Czy jesteś pewien, że chcesz opuścić grę?")
                .setTitle("WYJŚCIE");

        exitMessage.setPositiveButton("Zakończ grę", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                setResult(RESULT_OK);
                Intent goToMenu = new Intent(getApplicationContext(), MenuActivity.class);
                goToMenu.putExtra("wiek", getSharedPreferences("AGE_PREFERENCE", MODE_PRIVATE).getBoolean("wiek", true));
                startActivity(goToMenu);
                finish();
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
