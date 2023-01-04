package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ColorsActivity extends AppCompatActivity {

    private MediaPlayer song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        ArrayList<Word> colors = new ArrayList<>();
        colors.add(new Word("red", "weṭeṭṭi", R.drawable.red, R.raw.color_red));
        colors.add(new Word("green", "chokokki", R.drawable.green, R.raw.color_green));
        colors.add(new Word("brown", "ṭakaakki", R.drawable.brown, R.raw.color_brown));
        colors.add(new Word("gray", "ṭopoppi", R.drawable.gray, R.raw.color_gray));
        colors.add(new Word("black", "kululli", R.drawable.black, R.raw.color_black));
        colors.add(new Word("white", "kelelli", R.drawable.white, R.raw.color_white));
        colors.add(new Word("dusty yellow", "ṭopiisә", R.drawable.dustyyellow, R.raw.color_dusty_yellow));
        colors.add(new Word("mustard yellow", "chiwiiṭә", R.drawable.mustardyellow, R.raw.color_mustard_yellow));

        WordAdapter items = new WordAdapter(this, colors, R.color.category_colors);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(items);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = colors.get(position);
                releaseMediaPlayer();
                song = MediaPlayer.create(ColorsActivity.this, word.getAudioID());
                song.start();
                song.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        releaseMediaPlayer();
                    }
                });
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
    private void releaseMediaPlayer(){
        if (song != null){
            song.release();
            song = null;
        }
    }
}