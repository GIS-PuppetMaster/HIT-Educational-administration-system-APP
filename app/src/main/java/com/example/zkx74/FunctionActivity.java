package com.example.zkx74;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FunctionActivity extends AppCompatActivity {


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
    @BindView(R.id.fragment1)
    FrameLayout fragment1;
    @BindView(R.id.RL_1)
    LinearLayout RL1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        requestWindowFeature((Window.FEATURE_NO_TITLE));
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        window.setFlags(flag, flag);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.activity_function);
        //获取屏幕宽高
        WindowManager manager=this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int height=outMetrics.heightPixels;
        int width=outMetrics.widthPixels;
        Context context=getApplicationContext();
        //dp转px
        int tmp=dp2px(context,54);
        //组件自适应屏幕大小
        fragment1= (FrameLayout) findViewById(R.id.fragment1);
        ViewGroup.LayoutParams lp;
        lp=fragment1.getLayoutParams();
        lp.height=height-tmp;
        lp.width=width;
        fragment1.setLayoutParams(lp);
        //
        ButterKnife.bind(this);
        FragmentOfSchedule fragmentSchedule = new FragmentOfSchedule();
        FragmentManager fragmentManagerSchedule = getFragmentManager();
        FragmentTransaction transactionSchedule = fragmentManagerSchedule.beginTransaction();
        transactionSchedule.replace(R.id.fragment1, fragmentSchedule);
        transactionSchedule.commit();

    }

    private int dp2px(Context context, float dpValue){
        float scale=context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale+0.5f);
    }


    @OnClick({R.id.check_schedule, R.id.check_score, R.id.bus, R.id.map, R.id.radioGroup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.check_schedule:
                FragmentOfSchedule fragmentSchedule = new FragmentOfSchedule();
                FragmentManager fragmentManagerSchedule = getFragmentManager();
                FragmentTransaction transactionSchedule = fragmentManagerSchedule.beginTransaction();
                transactionSchedule.replace(R.id.fragment1, fragmentSchedule);
                transactionSchedule.commit();
                break;
            case R.id.check_score:
                FragmentOfScore fragmentScore = new FragmentOfScore();
                FragmentManager fragmentManagerScore = getFragmentManager();
                FragmentTransaction transactionScore = fragmentManagerScore.beginTransaction();
                transactionScore.replace(R.id.fragment1, fragmentScore);
                transactionScore.commit();
                break;
            case R.id.bus:

                FragmentOfBus fragmentBus = new FragmentOfBus();
                FragmentManager fragmentManagerBus = getFragmentManager();
                FragmentTransaction transactionBus = fragmentManagerBus.beginTransaction();
                transactionBus.replace(R.id.fragment1, fragmentBus);
                transactionBus.commit();
                break;
            case R.id.map:
                FragmentOfMap fragmentMap = new FragmentOfMap();
                FragmentManager fragmentManagerMap = getFragmentManager();
                FragmentTransaction transactionMap = fragmentManagerMap.beginTransaction();
                transactionMap.replace(R.id.fragment1, fragmentMap);
                transactionMap.commit();
                break;
            case R.id.radioGroup:
                break;
        }
    }


}