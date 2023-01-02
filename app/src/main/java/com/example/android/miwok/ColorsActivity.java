package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);
        ArrayList<Word> colors = new ArrayList<>();
        colors.add(new Word("red", "weṭeṭṭi", R.drawable.red));
        colors.add(new Word("green", "chokokki", R.drawable.green));
        colors.add(new Word("brown", "ṭakaakki", R.drawable.brown));
        colors.add(new Word("gray", "ṭopoppi", R.drawable.gray));
        colors.add(new Word("black", "kululli", R.drawable.black));
        colors.add(new Word("white", "kelelli", R.drawable.white));
        colors.add(new Word("dusty yellow", "ṭopiisә", R.drawable.dustyyellow));
        colors.add(new Word("mustard yellow", "chiwiiṭә", R.drawable.mustardyellow));

        WordAdapter items = new WordAdapter(this, colors);
        ListView listView = findViewById(R.id.colorsListView);
        listView.setAdapter(items);
    }
}