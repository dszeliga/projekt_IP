package com.example.lastwerewolf.projekt_ip.dopasuj;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lastwerewolf.projekt_ip.ColoursGameActivity;
import com.example.lastwerewolf.projekt_ip.CountingGameActivity;
import com.example.lastwerewolf.projekt_ip.GiffActivity;
import com.example.lastwerewolf.projekt_ip.MemoGameActivity;
import com.example.lastwerewolf.projekt_ip.MenuActivity;
import com.example.lastwerewolf.projekt_ip.R;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MatchingPuzzlesGameActivity extends AppCompatActivity {

    ImageView iv1, iv2, iv3, iv4, iv5, iv6, iv7, iv8;
    ImageView emptyV[];
    ImageView filledV[];
    int correct = 0;

    int matching[] = {-1, -1, -1};

    Map<ShapeType, Shape> shapeMap = new HashMap<>();

    List<ShapeType> filledP;
    List<ShapeType> emptyP;

    private final int SCORE_FOR_WIN = 1;
    private final int SHOW_RESULT_AFTER = 16;
    private int winCounter = 4;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_puzzles_game);

        Resources res = getResources();

        for (ShapeType shapeType : ShapeType.values()) {
            shapeMap.put(shapeType, new Shape(shapeType, res));
        }

        setView();
    }

    private void setView() {
        filledV = new ImageView[]{
                iv1 = (ImageView) findViewById(R.id.imageView1),
                iv2 = (ImageView) findViewById(R.id.imageView2),
                iv3 = (ImageView) findViewById(R.id.imageView3),
                iv4 = (ImageView) findViewById(R.id.imageView4)
        };

        emptyV = new ImageView[]{
                iv5 = (ImageView) findViewById(R.id.imageView5),
                iv6 = (ImageView) findViewById(R.id.imageView6),
                iv7 = (ImageView) findViewById(R.id.imageView7),
                iv8 = (ImageView) findViewById(R.id.imageView8),
        };

        filledP = selectFourRandomShapes();
        emptyP = filledP;
        for (int i = 0; i < emptyV.length; i++) {
            emptyV[i].setImageDrawable(shapeMap.get(emptyP.get(i)).shapeEmpty);
            emptyV[i].setTag(i);
            emptyV[i].setOnTouchListener(new choiceTouchListner());
            emptyV[i].setOnDragListener(new choiceDraglistner());
        }
        for (int i = 0; i < filledV.length; i++) {
            filledV[i].setImageDrawable(shapeMap.get(filledP.get(i)).shapeFilled);
            filledV[i].setTag(i + 4);
            filledV[i].setOnTouchListener(new choiceTouchListner());
            filledV[i].setOnDragListener(new choiceDraglistner());
        }
    }

    private List<ShapeType> selectFourRandomShapes() {
        List<ShapeType> ptl = new LinkedList<>(Arrays.asList(ShapeType.values()));
        List<ShapeType> result = new LinkedList<>();
        ptl.remove(ShapeType.EX);

        Random rand = new Random();
        for (ImageView ignored : filledV) {
            int index = Math.abs(rand.nextInt()) % ptl.size();
            result.add(ptl.get(index));
            ptl.remove(index);
        }

        return result;
    }

    private List<ShapeType> selectFourRandomShapes(List<ShapeType> _ptl) {
        List<ShapeType> ptl = new LinkedList<>(_ptl);
        List<ShapeType> result = new LinkedList<>();
        Random rand = new Random();
        while (ptl.size() > 0) {
            int index = Math.abs(rand.nextInt()) % ptl.size();
            result.add(ptl.get(index));
            ptl.remove(index);
        }
        return result;
    }

    private class choiceTouchListner implements View.OnTouchListener {


        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if ((event.getAction() == MotionEvent.ACTION_DOWN) && ((ImageView) v).getDrawable() != null) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDrag(data, shadowBuilder, v, 0);
                return true;
            } else {
                return false;
            }
        }
    }

    private class choiceDraglistner implements View.OnDragListener {


        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:

                    break;

                case DragEvent.ACTION_DRAG_ENTERED:

                    break;

                case DragEvent.ACTION_DRAG_EXITED:

                    break;

                case DragEvent.ACTION_DROP:

                    ImageView view1 = (ImageView) event.getLocalState();

                    String s = ((ImageView) v).getTag().toString();

                    int x = Integer.parseInt(s);
                    int y = x + 4;

                    String s2 = Integer.toString(y);

                    if (view1.getTag().toString().equals(s2)) {
                        ((ImageView) v).setImageDrawable(view1.getDrawable());
                        ((ImageView) view1).setImageDrawable(null);
                        Toast.makeText(MatchingPuzzlesGameActivity.this, "Dobrze", Toast.LENGTH_SHORT).show();
                        correct++;
                        winCounter++;
                        if (correct == 4) {
                            finishGame();
                        }
                        break;

                    } else {
                        Toast.makeText(MatchingPuzzlesGameActivity.this, "Spróbuj jeszcze raz", Toast.LENGTH_SHORT).show();

                    }
                    break;
            }

            return true;
        }
    }

    public void onBackPressed() {
        AlertDialog.Builder exitMessage = new AlertDialog.Builder(this);
        exitMessage.setMessage("Czy jesteś pewien, że chcesz opuścić grę?")
                .setTitle("WYJŚCIE");
        exitMessage.setPositiveButton("Zakończ grę", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                startActivity(new Intent(MatchingPuzzlesGameActivity.this, MenuActivity.class));
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

    boolean isLeft(View v) {
        for (int i = 0; i < emptyV.length; i++) {
            if (emptyV[i].getId() == v.getId()) {
                return true;
            }
        }
        return false;
    }

    int getPoz(View v) {
        boolean isLeft = isLeft(v);
        ImageView iva[] = isLeft ? emptyV : filledV;
        for (int i = 0; i < emptyV.length; i++) {
            if (iva[i].getId() == v.getId()) {
                return i;
            }
        }
        return -1;
    }


    private void finishGame() {
        Toast.makeText(this, "Gratulacje!", Toast.LENGTH_SHORT).show();
        for (int i = 0; i < matching.length; i++) {
            matching[i] = -1;
        }

//        allPoints += SCORE_FOR_WIN;
//        getSharedPreferences("POINTS_PREFERENCE", MODE_PRIVATE).edit().putInt("points", allPoints).commit();
        playSound("bravo.mp3");
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
// JEŻELI MA 0p to idzie do GiffActivityFailActivity, w innym wypadku do ResultActivity
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (winCounter % SHOW_RESULT_AFTER == 0) {
                    Intent intent = new Intent(getApplicationContext(), GiffActivity.class);
                    intent.putExtra("Odpowiedzi prawidłowe", winCounter * SCORE_FOR_WIN);//przekazanie informacji o ilości uzyskanych punktów
                    intent.putExtra("Gra", "Dopasuj");
                    intent.putExtra("level", 2);

                    winCounter = 0;

                    startActivity(intent);
                    finish();
                } else {
                    // Recreate activity, aby rozpocząć grę od nowa.
                    recreate();
                }
            }
        }, 4000);
    }

    MediaPlayer m = new MediaPlayer();

    public void playSound(String fileName) {
        try {
            if (m.isPlaying()) {
                m.stop();
                m.release();
            }
            m = new MediaPlayer();

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
}
