package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    private MediaPlayer song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        ArrayList<Word> family = new ArrayList<>();
        family.add(new Word("father", "әpә", R.drawable.father, R.raw.family_father));
        family.add(new Word("mother", "әṭa", R.drawable.mother, R.raw.family_mother));
        family.add(new Word("son", "angsi", R.drawable.son, R.raw.family_son));
        family.add(new Word("daughter", "tune", R.drawable.daughter, R.raw.family_daughter));
        family.add(new Word("older brother", "taachi", R.drawable.olderbrother, R.raw.family_older_brother));
        family.add(new Word("younger brother", "chalitti", R.drawable.youngerbrother, R.raw.family_younger_brother));
        family.add(new Word("older sister", "teṭe", R.drawable.oldersister, R.raw.family_older_sister));
        family.add(new Word("younger sister", "kolliti", R.drawable.youngersister, R.raw.family_younger_sister));
        family.add(new Word("grandmother", "ama", R.drawable.grandmother, R.raw.family_grandmother));
        family.add(new Word("grandfather", "paapa", R.drawable.grandfather, R.raw.family_grandfather));

        WordAdapter items = new WordAdapter(this, family, R.color.category_family);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(items);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = family.get(position);
                releaseMediaPlayer();
                song = MediaPlayer.create(FamilyActivity.this, word.getAudioID());
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