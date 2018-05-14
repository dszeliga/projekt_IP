package com.example.lastwerewolf.projekt_ip.dopasuj;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.lastwerewolf.projekt_ip.R;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DopasujLvl3Activity extends AppCompatActivity {

    ImageView leftV[];

    ImageView rightV[];

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
        }
        for(int i = 0; i < rightV.length; i++) {
            rightV[i].setImageDrawable(profMap.get(rightP.get(i)).profTool);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dopasuj_lvl3);

        Resources res = getResources();

        for(ProfessionType pt : ProfessionType.values()) {
            profMap.put(pt, new Proffesion(pt, res));
        }

        setViews();
    }
}
