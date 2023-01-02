package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        ArrayList<Word> phrases = new ArrayList<>();
        phrases.add(new Word("Where are you going?", "minto wuksus", R.drawable.conversation));
        phrases.add(new Word("What is your name?", "tinnә oyaase'nә", R.drawable.conversation));
        phrases.add(new Word("My name is...", "oyaaset...", R.drawable.conversation));
        phrases.add(new Word("How are you feeling?", "michәksәs?", R.drawable.conversation));
        phrases.add(new Word("I’m feeling good.", "kuchi achit", R.drawable.conversation));
        phrases.add(new Word("Are you coming?", "әәnәs'aa?", R.drawable.conversation));
        phrases.add(new Word("Yes, I’m coming.", "hәә’ әәnәm", R.drawable.conversation));
        phrases.add(new Word("I’m coming.", "әәnәm", R.drawable.conversation));
        phrases.add(new Word("Let’s go.", "yoowutis", R.drawable.conversation));
        phrases.add(new Word("Come here.", "әnni'nem", R.drawable.conversation));

        WordAdapter items = new WordAdapter(this, phrases);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(items);
    }
}