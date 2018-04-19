package com.example.lastwerewolf.projekt_ip.dopasuj;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.lastwerewolf.projekt_ip.ColoursGameActivity;
import com.example.lastwerewolf.projekt_ip.CountingGameActivity;
import com.example.lastwerewolf.projekt_ip.MemoGameActivity;
import com.example.lastwerewolf.projekt_ip.MenuActivity;
import com.example.lastwerewolf.projekt_ip.R;

public class MatchingPuzzlesGameActivity extends AppCompatActivity {

    public ColoursGameActivity GenerateColoursGame() {
        return null;
    }

    public CountingGameActivity GenerateCountingGame() {
        return null;
    }

    public MemoGameActivity GenerateMemoGame() {
        return null;
    }

    ImageView iv1,iv2,iv3,iv4,iv5,iv6,iv7,iv8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_puzzles_game);

        iv1 = (ImageView)findViewById(R.id.imageView2);
        iv2 = (ImageView)findViewById(R.id.imageView5);
        iv3 = (ImageView)findViewById(R.id.imageView8);
        iv4 = (ImageView)findViewById(R.id.imageView9);

        iv5 = (ImageView)findViewById(R.id.imageView4);
        iv6 = (ImageView)findViewById(R.id.imageView6);
        iv7 = (ImageView)findViewById(R.id.imageView3);
        iv8 = (ImageView)findViewById(R.id.imageView11);


        iv1.setOnTouchListener(new choiceTouchListner());
        iv1.setOnDragListener(new choiceDraglistner());


        iv2.setOnTouchListener(new choiceTouchListner());
        iv2.setOnDragListener(new choiceDraglistner());

        iv3.setOnTouchListener(new choiceTouchListner());
        iv3.setOnDragListener(new choiceDraglistner());

        iv4.setOnTouchListener(new choiceTouchListner());
        iv4.setOnDragListener(new choiceDraglistner());

        iv5.setOnTouchListener(new choiceTouchListner());
        iv5.setOnDragListener(new choiceDraglistner());

        iv6.setOnTouchListener(new choiceTouchListner());
        iv6.setOnDragListener(new choiceDraglistner());

        iv7.setOnTouchListener(new choiceTouchListner());
        iv7.setOnDragListener(new choiceDraglistner());

        iv8.setOnTouchListener(new choiceTouchListner());
        iv8.setOnDragListener(new choiceDraglistner());


    }


    private class choiceTouchListner implements View.OnTouchListener{


        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if((event.getAction() == MotionEvent.ACTION_DOWN) && ((ImageView)v).getDrawable() !=null) {
               ClipData data =ClipData.newPlainText("","");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDrag(data, shadowBuilder, v, 0);
                return true;
            }else {
                return false;
            }
        }
    }

    private class choiceDraglistner implements View.OnDragListener{


        @Override
        public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()){
                    case DragEvent.ACTION_DRAG_STARTED:

                        break;

                    case DragEvent.ACTION_DRAG_ENTERED:

                        break;

                    case DragEvent.ACTION_DRAG_EXITED:

                        break;

                    case DragEvent.ACTION_DROP:



                        ImageView view = (ImageView)event.getLocalState();

                        String s = ((ImageView)v).getTag().toString();

                        int x = Integer.parseInt(s);
                        int y = x+4;

                        String s2 =Integer.toString(y);

                        if (view.getTag().toString().equals(s2)){
                            ((ImageView)v).setImageDrawable(view.getDrawable());
                            ((ImageView) view).setImageDrawable(null);
                            break;

                        }else
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
                Intent data = new Intent();
//                data.putExtra("", "")
//                setResult(RESULT_OK, data);
                setResult(RESULT_OK);
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
