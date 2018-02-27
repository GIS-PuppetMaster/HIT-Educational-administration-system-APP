package com.example.zkx74;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.panpf.sketch.Sketch;
import me.panpf.sketch.SketchImageView;
import me.panpf.sketch.request.DisplayOptions;

public class FunctionActivity extends AppCompatActivity   {


    @BindView(R.id.check_schedule)
    RadioButton checkSchedule;
    @BindView(R.id.check_score)
    RadioButton checkScore;
    @BindView(R.id.bus)
    RadioButton bus;
    @BindView(R.id.map)
    RadioButton map;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.check_schedule, R.id.check_score, R.id.bus, R.id.map, R.id.radioGroup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.check_schedule:
                FragmentOfSchedule fragmentSchedule = new FragmentOfSchedule();
                FragmentManager fragmentManagerSchedule=getFragmentManager();
                FragmentTransaction transactionSchedule = fragmentManagerSchedule.beginTransaction();
                transactionSchedule.replace(R.id.fragment1,fragmentSchedule);
                transactionSchedule.commit();
                break;
            case R.id.check_score:
                FragmentOfScore fragmentScore = new FragmentOfScore();
                FragmentManager fragmentManagerScore=getFragmentManager();
                FragmentTransaction transactionScore = fragmentManagerScore.beginTransaction();
                transactionScore.replace(R.id.fragment1,fragmentScore);
                transactionScore.commit();
                break;
            case R.id.bus:

                FragmentOfBus fragmentBus = new FragmentOfBus();
                FragmentManager fragmentManagerBus=getFragmentManager();
                FragmentTransaction transactionBus = fragmentManagerBus.beginTransaction();
                transactionBus.replace(R.id.fragment1,fragmentBus);
                transactionBus.commit();
                break;
            case R.id.map:
                FragmentOfMap fragmentMap = new FragmentOfMap();
                FragmentManager fragmentManagerMap=getFragmentManager();
                FragmentTransaction transactionMap = fragmentManagerMap.beginTransaction();
                transactionMap.replace(R.id.fragment1,fragmentMap);
                transactionMap.commit();
                break;
            case R.id.radioGroup:
                break;
        }
    }


}