package com.example.zkx74;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.panpf.sketch.SketchImageView;
import me.panpf.sketch.zoom.ImageZoomer;


public class FragmentOfBus extends Fragment {
    SketchImageView sketchImageView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_fragment_of_bus,container,false);
        SketchImageView sketchImageView = (SketchImageView)view.findViewById(R.id.image1);
        String assetResName = "calendar.jpg";
        sketchImageView.displayAssetImage(assetResName);
        sketchImageView.setZoomEnabled(true);
        // 开启阅读模式
        sketchImageView.getZoomer().setReadMode(true);
        return view;
    }

}
