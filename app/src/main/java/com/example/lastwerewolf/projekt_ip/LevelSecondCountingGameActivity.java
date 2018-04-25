package com.example.lastwerewolf.projekt_ip;

import android.annotation.SuppressLint;
import android.content.Intent;
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

public class LevelSecondCountingGameActivity extends AppCompatActivity {
    public List<String> suggestSource = new ArrayList<>();
    public GridViewAnswerAdapter answerAdapter;
    public GridViewSuggestAdapter suggestAdapter;

    public Button btnSubmit;

    public GridView gridViewAnswer,gridViewSuggest;

public ImageView imgViewQuestion;
int[] image_list= new int[]{
};
int rightAnswers = 0;

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

        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result="";
                for(int i=0;i< Common.user_submit_answer.length;i++)
                    result +=String.valueOf(Common.user_submit_answer[i]);
                if(result.equals(correct_answer))
                {
                    Toast.makeText(getApplicationContext(),"Finish ! This is"+result,Toast.LENGTH_SHORT).show();

                    //reset
                    Common.count=0;
                    Common.user_submit_answer = new char[correct_answer.length()];
                    rightAnswers = rightAnswers +1;
                    //setAdapter

                  //  GridViewAnswerAdapter answerAdapter = new GridViewAnswerAdapter(setupNullList(),getApplicationContext());
                    gridViewAnswer.setAdapter(answerAdapter);
                    answerAdapter.notifyDataSetChanged();

                    GridViewAnswerAdapter suggestAdapter = new GridViewAnswerAdapter (suggestSource,getApplicationContext(),LevelSecondCountingGameActivity.this);
                    gridViewAnswer.setAdapter(suggestAdapter);
                    suggestAdapter.notifyDataSetChanged();

                    setupList();
                    getResults();
                }
                else {
                    Toast.makeText(LevelSecondCountingGameActivity.this,"Incorret!!",Toast.LENGTH_SHORT).show();

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

        Common.user_submit_answer= new char[answer.length];

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

      //  answerAdapter = new GridViewAnswerAdapter(setupNullList(),this);
        suggestAdapter= new GridViewSuggestAdapter(suggestSource,this,this);

        answerAdapter.notifyDataSetChanged();;
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
    public void getResults(){
        Intent intent = new Intent(getApplicationContext(),ResultActivity.class);
        intent.putExtra("Odpowiedzi prawidłowe",rightAnswers);
        intent.putExtra("Gra", "cyfry2");
        startActivity(intent);
    }
}
