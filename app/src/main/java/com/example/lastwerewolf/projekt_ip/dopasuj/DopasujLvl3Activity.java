package com.example.lastwerewolf.projekt_ip.dopasuj;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lastwerewolf.projekt_ip.GiffActivity;
import com.example.lastwerewolf.projekt_ip.MenuActivity;
import com.example.lastwerewolf.projekt_ip.R;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DopasujLvl3Activity extends AppCompatActivity implements View.OnClickListener {

    private final int SCORE_FOR_WIN = 1;
    private final int SHOW_RESULT_AFTER = 3;
    final Handler handler = new Handler();

//    private int allPoints;
    private int winCounter = 0;
    private int points = 0;

    ImageView leftV[];

    ImageView rightV[];

    ImageView mainView;

    int matching[] = {-1, -1, -1};
    ImageView activeView = null;

    List<ProfessionType> leftP;
    List<ProfessionType> rightP;

    Map<ProfessionType, Proffesion> profMap = new HashMap<>();

    List<ProfessionType> selectThreeRandomProfessions() {
        List<ProfessionType> ptl = new LinkedList<>(Arrays.asList(ProfessionType.values()));
        List<ProfessionType> result = new LinkedList<>();
        ptl.remove(ProfessionType.NONE);

        Random rand = new Random();
        for (ImageView ignored : rightV) {
            int index = Math.abs(rand.nextInt()) % ptl.size();
            result.add(ptl.get(index));
            ptl.remove(index);
        }

        return result;
    }

    List<ProfessionType> selectThreeRandomProfessions(List<ProfessionType> _ptl) {
        List<ProfessionType> ptl = new LinkedList<>(_ptl);
        List<ProfessionType> result = new LinkedList<>();
        Random rand = new Random();
        while (ptl.size() > 0) {
            int index = Math.abs(rand.nextInt()) % ptl.size();
            result.add(ptl.get(index));
            ptl.remove(index);
        }
        return result;
    }

    void setViews() {
        mainView = findViewById(R.id.dopasuj_lvl3_wrapper);
        rightV = new ImageView[] {
            findViewById(R.id.imageView19),
            findViewById(R.id.imageView21),
            findViewById(R.id.imageView23)
        };
        leftV = new ImageView[] {
            findViewById(R.id.imageView18),
            findViewById(R.id.imageView20),
            findViewById(R.id.imageView22)
        };
        leftP = selectThreeRandomProfessions();
        rightP = selectThreeRandomProfessions(leftP);
        for(int i = 0; i < leftV.length; i++) {
            leftV[i].setImageDrawable(profMap.get(leftP.get(i)).profPic);
            leftV[i].setOnClickListener(this);
        }
        for(int i = 0; i < rightV.length; i++) {
            rightV[i].setImageDrawable(profMap.get(rightP.get(i)).profTool);
            rightV[i].setOnClickListener(this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dopasuj_lvl3);

//        allPoints = getSharedPreferences("POINTS_PREFERENCE", MODE_PRIVATE).getInt("points", 0);

        Resources res = getResources();
        playSound(R.raw.dopasujlvl3);

        for(ProfessionType pt : ProfessionType.values()) {
            profMap.put(pt, new Proffesion(pt, res));
        }

        Intent in = getIntent();
        winCounter = in.getIntExtra("dl3w", 0);
        points = in.getIntExtra("dl3p", 0);

        setViews();
    }

    private int getViewMiddleX(View v) {
        Rect rect = new Rect();
        v.getGlobalVisibleRect(rect);
        return rect.left + rect.width()/2;
    }

    private int getViewMiddleY(View v) {
        Rect rect = new Rect();
        v.getGlobalVisibleRect(rect);
        return rect.top + rect.height()/2;
    }

    boolean isLeft(View v) {
        for(int i = 0; i < leftV.length; i++) {
            if(leftV[i].getId() == v.getId()) {
                return true;
            }
        }
        return false;
    }

    int getPoz(View v) {
        boolean isLeft = isLeft(v);
        ImageView iva[] = isLeft ? leftV : rightV;
        for(int i = 0; i < leftV.length; i++) {
            if(iva[i].getId() == v.getId()) {
                return i;
            }
        }
        return -1;
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

    // Obsłuż poprawne dopasowanie kompletu
    private void finishGame() {
        Toast.makeText(this, "Gratulacje!", Toast.LENGTH_SHORT).show();
        for(int i = 0; i < matching.length; i++) {
            matching[i] = -1;
        }

        // Odtwórz dźwięk brawo oraz wyświetl ekran wyniku/uruchom nową grę z opóźnieniem
        playSound(R.raw.bravo);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                winCounter++;
                if (winCounter % SHOW_RESULT_AFTER == 0) {
                    Intent intent = new Intent(getApplicationContext(), GiffActivity.class);
                    // Ochrona przed uzyskaniem ujemnej liczby punktów
                    intent.putExtra("Odpowiedzi prawidłowe", Math.max(points, 0));
                    intent.putExtra("Gra", "dopasuj");
                    intent.putExtra("level", 3);

                    winCounter = 0;

                    startActivity(intent);
                    finish();
                } else {
                    Intent in = getIntent();
                    in.putExtra("dl3w", winCounter);
                    in.putExtra("dl3p", points);
                    startActivity(in);
                    finish();
                }
            }
        }, 4000);
    }

    // Obsługa dopasowania
    @Override
    public void onClick(View v) {
        // Czy kliknięty obiekt jest po lewej stronie
        boolean vIsLeft = isLeft(v);
        int vIndex = getPoz(v);

        // Brak aktywnego obiektu. Ustaw kliknięty obiekt jako aktywny.
        if(activeView == null) {
            activeView = (ImageView) v;
            activeView.setBackground(getDrawable(R.drawable.ic_green_circle));
            return;
        }

        // Kliknięcie na już aktywny obiekt
        if(activeView.getId() == v.getId()) {
            return;
        }

        boolean aIsLeft = isLeft(activeView);
        int aIndex = getPoz(activeView);

        activeView.setBackground(null);
        // Jeśli aktywny i kliknięty obiekt są po tej samej stronie to zmień aktywny obiekt.
        if(vIsLeft == aIsLeft) {
            activeView = (ImageView) v;
            activeView.setBackground(getDrawable(R.drawable.ic_green_circle));
            return;
        }
        activeView = null;

        // Obiekty są po różnych stronach, więc stwórz dopasowanie
        int mIndex = aIsLeft ? aIndex : vIndex;
        int mVal = aIsLeft ? vIndex : aIndex;
        matching[mIndex] = mVal;
        // Sprawdź czy dopasowanie jest poprawne i przydziel adekwatną liczbę punktów
        boolean correctMatching = leftP.get(mIndex) == rightP.get(mVal);
        if(correctMatching) {
            points += 2;
        } else {
            points -= 1;
        }
        for(int i = 0; i < matching.length; i++) {
            if(i != mIndex && matching[i] == mVal) {
                matching[i] = -1;
            }
        }

        // Narysuj wszystkie połączenia
        Rect mrect = new Rect();
        mainView.getLocalVisibleRect(mrect);
        Bitmap bmp = Bitmap.createBitmap(mrect.width(), mrect.height(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint p = new Paint();
        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        canvas.translate(mrect.left, mrect.top);
        mainView.draw(canvas);
        mainView.setImageBitmap(bmp);
        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.ADD));
        p.setColor(Color.BLACK);
        p.setStrokeWidth(20);
        canvas.drawColor(Color.BLACK, PorterDuff.Mode.CLEAR);
        int correct = 0;
        for(int i = 0; i < matching.length; i++) {
            if(matching[i] >= 0) {
                if(leftP.get(i) == rightP.get(matching[i])) {
                    correct += 1;
                    p.setColor(Color.GREEN);
                } else {
                    p.setColor(Color.GRAY);
                }

                View a = leftV[i];
                View b = rightV[matching[i]];
                int xa = getViewMiddleX(a);
                int ya = getViewMiddleY(a);

                int xb = getViewMiddleX(b);
                int yb = getViewMiddleY(b);

                canvas.drawLine(xa, ya, xb, yb, p);
            }
        }

        canvas.translate(mrect.left, mrect.top);
        mainView.draw(canvas);
        mainView.setImageBitmap(bmp);
        mainView.invalidate();

        // Jeśli wszystkie dopasowania są poprawne obsłuż wygranie gry
        if(correct == matching.length) {
            finishGame();
        }
    }

    // Obsługa kliknięcia klawisza wstecz
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
