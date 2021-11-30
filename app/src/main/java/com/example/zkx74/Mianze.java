package com.example.zkx74;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.panpf.sketch.SketchImageView;

public class Mianze extends AppCompatActivity {



    @BindView(R.id.mianzeshengming)
    SketchImageView mianzeshengming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mianze);
        ButterKnife.bind(this);
        Mianze();
    }

    private void Mianze() {
        String name = "Mianzeshengming.png";
        mianzeshengming.displayAssetImage(name);
        mianzeshengming.setZoomEnabled(true);
        mianzeshengming.getZoomer().setReadMode(true);
    }
}
