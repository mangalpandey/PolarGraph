package com.example.mangal.polargraph;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private PolarGraph polarGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        polarGraph = findViewById(R.id.polarGraph);

        ArrayList<PolarDataSet> polarDataSetArrayList = new ArrayList<>();
        polarDataSetArrayList.add(new PolarDataSet(Color.parseColor("#08E8DE"), Color.parseColor("#FF0800"), 70));
        polarDataSetArrayList.add(new PolarDataSet(Color.parseColor("#F4C2C2"), Color.parseColor("#FF0800"), 80));
        polarDataSetArrayList.add(new PolarDataSet(Color.parseColor("#FF7F50"), Color.parseColor("#FF0800"), 30));

        polarGraph.setPolarDataSet(polarDataSetArrayList);
    }


}
