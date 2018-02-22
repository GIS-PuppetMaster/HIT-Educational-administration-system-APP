package com.example.zkx74;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FunctionActivity extends AppCompatActivity {


    @BindView(R.id.bus)
    View bus;
    @BindView(R.id.check_score)
    View checkScore;
    @BindView(R.id.check_schedule)
    View checkSchedule;
    @BindView(R.id.map)
    View map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bus, R.id.check_score, R.id.check_schedule, R.id.map})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bus:
                Intent intent=new Intent(FunctionActivity.this,Bus.class);
                startActivity(intent);
                break;
            case R.id.check_score:
                Intent intent2=new Intent(FunctionActivity.this,CheckScore.class);
                startActivity(intent2);
                break;
            case R.id.check_schedule:
                Intent intent3=new Intent(FunctionActivity.this,CheckSchedule.class);
                startActivity(intent3);
                break;
            case R.id.map:
                Intent intent4=new Intent(FunctionActivity.this,Map.class);
                startActivity(intent4);
                break;
        }
    }
}
