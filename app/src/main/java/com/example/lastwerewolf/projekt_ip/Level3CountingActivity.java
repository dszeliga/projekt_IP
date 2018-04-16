package com.example.lastwerewolf.projekt_ip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Level3CountingActivity extends AppCompatActivity {
Button b_countinue;
ImageView tv_question3;
EditText et_answer;

List<Item3> questions3list;
int curQuestion3= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level3_counting);

        b_countinue= (Button) findViewById(R.id.b_continue);
        tv_question3= (ImageView) findViewById(R.id.tv_question3);
        et_answer= (EditText) findViewById(R.id.et_answer);

        b_countinue.setVisibility(View.INVISIBLE);

       questions3list= new ArrayList<>();

        for (int i = 0; i < new Database_three_level().answer3.length; i++) {

          questions3list.add(new Item3(Database_three_level.answer3[i], Database_three_level.questions3[i]));
        }
        Collections.shuffle(questions3list);
        tv_question3.setImageResource(questions3list.get(curQuestion3).getQuestions3());


       //tv_question3.setImageResource(Integer.parseInt(String.valueOf(questions3list.get(curQuestion3))));
et_answer.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
if(et_answer.getText().toString().equalsIgnoreCase(questions3list.get(curQuestion3).getAnswer3())){
b_countinue.setVisibility(View.VISIBLE);

}else{
    b_countinue.setVisibility(View.INVISIBLE);

}
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
});
b_countinue.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       if(curQuestion3<(Database_three_level.questions3.length-1)){
           curQuestion3++;
           tv_question3.setImageResource(questions3list.get(curQuestion3).getQuestions3());
           b_countinue.setVisibility(View.INVISIBLE);
           et_answer.setText("");
       } else {
           Toast.makeText(Level3CountingActivity.this,"Wygrałeś",Toast.LENGTH_SHORT).show();
           finish();
       }

    }
});
    }

}
