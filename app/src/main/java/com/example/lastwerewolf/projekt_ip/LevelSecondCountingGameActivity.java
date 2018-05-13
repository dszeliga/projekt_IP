package com.example.lastwerewolf.projekt_ip;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lastwerewolf.projekt_ip.Adapter.GridViewAnswerAdapter;
import com.example.lastwerewolf.projekt_ip.Adapter.GridViewSuggestAdapter;
import com.example.lastwerewolf.projekt_ip.Common.Common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.example.lastwerewolf.projekt_ip.Common.Common.count;
import static com.example.lastwerewolf.projekt_ip.Common.Common.user_submit_answer;

public class LevelSecondCountingGameActivity extends AppCompatActivity {
    public List<String> suggestSource = new ArrayList<>();
    public GridViewAnswerAdapter answerAdapter;
    public GridViewSuggestAdapter suggestAdapter;

    public Button btnSubmit;

    public GridView gridViewAnswer,gridViewSuggest;

    public ImageView imgViewQuestion;

    int score = 0;
int turn=0;
    int[] image_list={
            R.drawable.motyl,
            R.drawable.ryba,
            R.drawable.biedronka,
            R.drawable.ciastko,
            R.drawable.cukierek,
            R.drawable.dom,
            R.drawable.lizak,
            R.drawable.lew,




    };


    public char[] answer;
    String correct_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_second_counting_game);

        initView();

    }

    private void initView() {
        gridViewAnswer = (GridView)findViewById(R.id.gridViewAnswer);
        gridViewSuggest = (GridView)findViewById(R.id.gridViewSuggest);

        imgViewQuestion = (ImageView)findViewById(R.id.imgLogo);

        //dodaje setupliste
        setupList();
        if (turn < suggestSource.size()) {
            turn++;


        } else {
            getResults();
        }

        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result="";
                for(int i=0;i< user_submit_answer.length;i++)
                    result +=String.valueOf(user_submit_answer[i]);
                if(result.equals(correct_answer))
                {
                   // Toast.makeText(getApplicationContext(),"Finish ! This is"+result,Toast.LENGTH_SHORT).show();
                    score = score+1;
                    //reset
                    Common.count=1;
                 //   Common.user_submit_answer = new char[correct_answer.length()];

                    //setAdapter

                    GridViewAnswerAdapter answerAdapter = new GridViewAnswerAdapter(setupNullList(),getApplicationContext());
                    gridViewAnswer.setAdapter(answerAdapter);
                    answerAdapter.notifyDataSetChanged();

                    GridViewSuggestAdapter suggestAdapter = new GridViewSuggestAdapter (suggestSource,getApplicationContext(),LevelSecondCountingGameActivity.this);
                    gridViewSuggest.setAdapter(suggestAdapter);
                    suggestAdapter.notifyDataSetChanged();

                    setupList();
                    if (6 == turn) {
                        getResults();
                    }
                }else{
                    score =+0;
                    //reset
                    Common.count=0;
                  //  Common.user_submit_answer = new char[correct_answer.length()];

                    //setAdapter



                    setupList();
                }if (6 ==score) {
                    getResults();
                }
            }
        });

    }

    private void setupList() {
        Random random = new Random();
        int imageSelected = image_list[random.nextInt(image_list.length)];
        imgViewQuestion.setImageResource(imageSelected);
        correct_answer = getResources().getResourceName(imageSelected);
        correct_answer = correct_answer.substring(correct_answer.lastIndexOf("/")+1);

        answer = correct_answer.toCharArray();

        user_submit_answer= new char[answer.length];

        suggestSource.clear();
        for(char item:answer)
        {
            //dodaj logo nazwe list
            suggestSource.add(String.valueOf(item));
        }
        //random character list
        for(int i = answer.length;i<answer.length*2;i++)
            suggestSource.add(Common.number_character[random.nextInt(Common.number_character.length)]);

        //wybieranie random
        Collections.shuffle(suggestSource);

        //set for gridview

        answerAdapter = new GridViewAnswerAdapter(setupNullList(),this);
        suggestAdapter= new GridViewSuggestAdapter(suggestSource,this,this);

        answerAdapter.notifyDataSetChanged();
        suggestAdapter.notifyDataSetChanged();

        gridViewSuggest.setAdapter(suggestAdapter);
        gridViewAnswer.setAdapter(answerAdapter);





    }

    private char[] setupNullList() {
        char result[] = new char[answer.length];
        for(int i=0;i<answer.length;i++)
            result[i]=' ';

        return result;
    }
    public void getResults() {
        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
        intent.putExtra("Odpowiedzi prawidłowe", score);
        intent.putExtra("Gra", "litery");
        startActivity(intent);
    }
}
