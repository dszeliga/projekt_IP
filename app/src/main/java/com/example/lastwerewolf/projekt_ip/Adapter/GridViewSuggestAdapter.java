package com.example.lastwerewolf.projekt_ip.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.example.lastwerewolf.projekt_ip.Common.Common;
import com.example.lastwerewolf.projekt_ip.LevelSecondCountingGameActivity;

import java.util.List;

public class GridViewSuggestAdapter extends BaseAdapter {
    private List<String> suggestSource;
    private Context context;
    private LevelSecondCountingGameActivity levelSecondCountingGameActivity;

    public GridViewSuggestAdapter( List<String> suggestSource, Context context, LevelSecondCountingGameActivity levelSecondCountingGameActivity) {
        this.suggestSource = suggestSource;
        this.context = context;
        this.levelSecondCountingGameActivity = levelSecondCountingGameActivity;
    }

    @Override
    public int getCount() {
        return suggestSource.size();

    }

    @Override
    public Object getItem(int position) {
        return suggestSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Button button;
        if(convertView==null)
        {
            if(suggestSource.get(position).equals("null"))
            {
                button = new Button(context);
                button.setLayoutParams(new GridView.LayoutParams(85,85));
                button.setPadding(8,8,8,8);
                button.setBackgroundColor(Color.DKGRAY);

            }
            else
            {
                button = new Button(context);
                button.setLayoutParams(new GridView.LayoutParams(85,85));
                button.setPadding(8,8,8,8);
                button.setBackgroundColor(Color.DKGRAY);
                button.setTextColor(Color.YELLOW);
                button.setText(suggestSource.get(position));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(String.valueOf(levelSecondCountingGameActivity.answer).contains(suggestSource.get(position)))
                        {
                            char compare = suggestSource.get(position).charAt(0); //ustawienie char
                            for(int i=0;i<levelSecondCountingGameActivity.answer.length;i++)
                            {
                                if(compare==levelSecondCountingGameActivity.answer[i])
                                    Common.user_submit_answer[i] = compare;
                            }
                            //update ui
                            GridViewAnswerAdapter answerAdapter= new GridViewAnswerAdapter(Common.user_submit_answer, context);
                            levelSecondCountingGameActivity.gridViewAnswer.setAdapter(answerAdapter);
                            answerAdapter.notifyDataSetChanged();


                           levelSecondCountingGameActivity.suggestSource.set(position,"null");
                           levelSecondCountingGameActivity.suggestAdapter = new GridViewSuggestAdapter(levelSecondCountingGameActivity.suggestSource,context,levelSecondCountingGameActivity);
                           levelSecondCountingGameActivity.gridViewSuggest.setAdapter(levelSecondCountingGameActivity.suggestAdapter);
                           levelSecondCountingGameActivity.suggestAdapter.notifyDataSetChanged();
                        }
                        else
                        {
                            levelSecondCountingGameActivity.suggestSource.set(position,"null");
                            levelSecondCountingGameActivity.suggestAdapter = new GridViewSuggestAdapter(levelSecondCountingGameActivity.suggestSource,context,levelSecondCountingGameActivity);
                            levelSecondCountingGameActivity.gridViewSuggest.setAdapter(levelSecondCountingGameActivity.suggestAdapter);
                            levelSecondCountingGameActivity.suggestAdapter.notifyDataSetChanged();
                        }

                    }
                });
            }
        }
        else
            button = (Button) convertView;
        return button;
    }
}
