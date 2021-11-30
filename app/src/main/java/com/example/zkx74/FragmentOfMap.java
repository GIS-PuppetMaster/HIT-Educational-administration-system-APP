package com.example.zkx74;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.panpf.sketch.SketchImageView;



public class FragmentOfMap extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fragment_of_map, container, false);
        SketchImageView mapview=(SketchImageView)view.findViewById(R.id.image_map);
        String assetResName = "map.jpg";
        mapview.displayAssetImage(assetResName);
        mapview.setZoomEnabled(true);
        return view;
    }




}
