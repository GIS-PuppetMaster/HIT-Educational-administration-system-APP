package com.example.zkx74;

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
                break;
            case R.id.check_score:
                break;
            case R.id.check_schedule:
                break;
            case R.id.map:
                break;
        }
    }
}
